package app.web.services.bom.planks.calculators;

import app.web.constants.Config;
import app.web.entities.Bom;
import app.web.entities.Carport;
import app.web.entities.Plank;
import app.web.exceptions.WebInvalidInputException;
import app.web.services.bom.planks.ValidPlanks;

import java.util.*;

public class PlankCalculatorImpl implements PlankCalculator  //TODO: Finish this
{
    
    int splitCarportSegmentIntoTwoSegmentsAtThisWidth = Config.Bom.SPLIT_CARPORT_SEGMENT_INTO_TWO_SEGMENTS_AT_THIS_WIDTH;
    
    PostCalculator postCalculator;
    BeamCalculator beamCalculator;
    RafterCalculator rafterCalculator;
    
    public PlankCalculatorImpl( PostCalculator postCalculator, BeamCalculator beamCalculator, RafterCalculator rafterCalculator )
    {
        this.postCalculator = postCalculator;
        this.beamCalculator = beamCalculator;
        this.rafterCalculator = rafterCalculator;
    }
    
    @Override
    public int calcPostRows( Map< Integer, Plank > validPlanks, int carportWidth )
    {
        int rowAmount = 2;
        
        while ( carportWidth > ( this.splitCarportSegmentIntoTwoSegmentsAtThisWidth + Config.Bom.MINIMUM_DISTANCE_BETWEEN_POLES_CARPORT_WIDTH ) ) {
            rowAmount++;
            carportWidth = carportWidth - this.splitCarportSegmentIntoTwoSegmentsAtThisWidth;
        }
        
        return rowAmount;
        
    }
    
    @Override
    public Bom calcBom( ValidPlanks validPlanks, Carport carport ) throws WebInvalidInputException
    {
        Bom bom = new Bom();
        
        //Posts
        Plank post = this.postCalculator.findShortestUsablePost( validPlanks.getPosts(), carport.getHeight() );
        
        if ( post == null ) {
            throw new WebInvalidInputException( "Carportens højden er for høj!" );
        }
        
        int rowAmount = this.calcPostRows( validPlanks.getRafters(), carport.getWidth() );
        
        List< Plank > beams = this.beamCalculator.calcBeamsOnPosts( validPlanks.getBeams(), carport.getLength(), rowAmount, post.getPrice() );
        
        int postsPrRow = ( beams.size() + 1 );
        int postsAmount = postsPrRow * rowAmount;
        
        post.setAmount( postsAmount );
        
        Map< Integer, Plank > posts = new LinkedHashMap<>();
        posts.put( post.getId(), post );
        bom.setPosts( posts );
        
        //Beams
        Map< Integer, Plank > beamsWithAmounts = new LinkedHashMap<>();
        
        for ( Plank beam : beams ) {
            beam.setAmount( rowAmount );
        }
        
        int currentAmount;
        for ( Plank beam : beams ) {
            
            if ( beamsWithAmounts.putIfAbsent( beam.getId(), beam ) != null ) {
                currentAmount = beamsWithAmounts.get( beam.getId() ).getAmount();
                beam.setAmount( currentAmount + beam.getAmount() );
                beamsWithAmounts.put( beam.getId(), beam );
            }
        }
        
        bom.setBeams( beamsWithAmounts );
        
        //Rafters
        List< Plank > raftersPrRow = this.rafterCalculator.findShortestUsableRafter( validPlanks.getRafters(), carport.getWidth(), rowAmount, this.splitCarportSegmentIntoTwoSegmentsAtThisWidth );
        
        Map< Integer, Plank > raftersWithAmounts = new LinkedHashMap<>();
        
        for ( Plank rafter : raftersPrRow ) {
            
            if ( rafter.getAmount() == null ) {
                rafter.setAmount( 0 );
            }
            
            raftersWithAmounts.putIfAbsent( rafter.getId(), rafter );
            
            currentAmount = raftersWithAmounts.get( rafter.getId() ).getAmount();
            rafter.setAmount( currentAmount + postsPrRow );
            raftersWithAmounts.put( rafter.getId(), rafter );
        }
        
        bom.setRafters( raftersWithAmounts );
        
        System.out.println( rowAmount );
        
        return bom;
    }
    
    
    
}
