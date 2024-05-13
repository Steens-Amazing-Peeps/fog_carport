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
    //Below exists purely for testing reasons------------------------------------
    //'constants'
    //This middleman instant variable exists to make tests easier
    private int splitCarportSegmentIntoTwoSegmentsAtThisWidthInMm = Config.Bom.SPLIT_CARPORT_SEGMENT_INTO_TWO_SEGMENTS_AT_THIS_WIDTH_IN_MM;
    private int minimumDistanceBetweenPolesCarportWidthInMm = Config.Bom.MINIMUM_DISTANCE_BETWEEN_POLES_CARPORT_WIDTH_IN_MM;
    
    //End of purely for testing reasons------------------------------------
    
    PostCalculator postCalculator;
    BeamCalculator beamCalculator;
    RafterCalculator rafterCalculator;

    
    public PlankCalculatorImpl( PostCalculator postCalculator, BeamCalculator beamCalculator, RafterCalculator rafterCalculator )
    {
        this.postCalculator = postCalculator;
        this.beamCalculator = beamCalculator;
        this.rafterCalculator = rafterCalculator;
    }
    

    public int calcPostRows( Map< Integer, Plank > validRafters, int carportWidth )
    {
        int rowAmount = 2;
        
        while ( carportWidth > ( this.splitCarportSegmentIntoTwoSegmentsAtThisWidthInMm + this.minimumDistanceBetweenPolesCarportWidthInMm ) ) {
            rowAmount++;
            carportWidth = carportWidth - this.splitCarportSegmentIntoTwoSegmentsAtThisWidthInMm;
        }
        
        return rowAmount;
        
    }
    
    @Override
    public Bom calcBom( ValidPlanks validPlanks, Carport carport ) throws WebInvalidInputException
    {
        Bom bom = new Bom();
        
        //Row Amount
        int rowAmount = this.calcPostRows( validPlanks.getRafters(), carport.getWidth() );
        
        bom.setRowAmount( rowAmount );
        
        //Posts
        Plank post = this.postCalculator.findShortestUsablePost( validPlanks.getPosts(), carport.getHeight() );
        
        if ( post == null ) {
            throw new WebInvalidInputException( "Carportens højden er for høj!" );
        }
        
        //Beams
        List< Plank > beams = this.beamCalculator.calcBeamsOnPosts( validPlanks.getBeams(), carport.getLength(), rowAmount, post.getPrice() );
        
        //Posts again, we needed to know the amount of beams pr. row
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
        List< Plank > raftersPrRow = this.rafterCalculator.findShortestUsableRafter( validPlanks.getRafters(), carport.getWidth(), rowAmount, this.splitCarportSegmentIntoTwoSegmentsAtThisWidthInMm );
        
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

        
        return bom;
    }
    
    //Below exists purely for testing reasons------------------------------------
    //Getters and Setters
    public int getSplitCarportSegmentIntoTwoSegmentsAtThisWidthInMm()
    {
        return this.splitCarportSegmentIntoTwoSegmentsAtThisWidthInMm;
    }
    
    public void setSplitCarportSegmentIntoTwoSegmentsAtThisWidthInMm( int splitCarportSegmentIntoTwoSegmentsAtThisWidthInMm )
    {
        this.splitCarportSegmentIntoTwoSegmentsAtThisWidthInMm = splitCarportSegmentIntoTwoSegmentsAtThisWidthInMm;
    }
    
    public int getMinimumDistanceBetweenPolesCarportWidthInMm()
    {
        return this.minimumDistanceBetweenPolesCarportWidthInMm;
    }
    
    public void setMinimumDistanceBetweenPolesCarportWidthInMm( int minimumDistanceBetweenPolesCarportWidthInMm )
    {
        this.minimumDistanceBetweenPolesCarportWidthInMm = minimumDistanceBetweenPolesCarportWidthInMm;
    }
    
}
