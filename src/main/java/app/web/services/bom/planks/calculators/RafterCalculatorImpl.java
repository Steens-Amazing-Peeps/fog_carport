package app.web.services.bom.planks.calculators;

import app.util.MapManipulator;
import app.web.constants.Config;
import app.web.entities.Plank;
import app.web.exceptions.WebInvalidInputException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RafterCalculatorImpl implements RafterCalculator
{
    
    private int minimumDistanceBetweenPolesCarportWidth = Config.Bom.MINIMUM_DISTANCE_BETWEEN_POLES_CARPORT_WIDTH_IN_MM;
    
    @Override
    public Plank findShortestUsableRafter( Map< Integer, Plank > validRafters, int carportSegmentWidth ) throws WebInvalidInputException
    {
        Plank pickedPole;
        
        Plank[] planksSortedByLength = MapManipulator.sortByValuePlankLengthToArray( validRafters );
        
        for ( Plank plank : planksSortedByLength ) {
            if ( plank.getLength() >= carportSegmentWidth ) {
                pickedPole = plank;
                return new Plank( pickedPole );
            }
        }
        
        //No valid poles found, width is too long!
        throw new WebInvalidInputException( "Bredde på carport segment var for lang" );
    }
    
    @Override
    public List< Plank > findShortestUsableRafter( Map< Integer, Plank > validRafters, Integer carportWidth, int rowAmount, int maxSegmentWidth ) throws WebInvalidInputException
    {
        List< Plank > chosenRafters = new ArrayList<>();
        
        while ( true ) {
            if ( carportWidth > ( maxSegmentWidth + this.minimumDistanceBetweenPolesCarportWidth ) ) {
                carportWidth = carportWidth - maxSegmentWidth;
                chosenRafters.add( this.findShortestUsableRafter( validRafters, maxSegmentWidth ) );
            } else {
                chosenRafters.add( this.findShortestUsableRafter( validRafters, carportWidth ) );
                return chosenRafters;
            }
        }
        
        
    }
    
}
