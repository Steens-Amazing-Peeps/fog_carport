package app.web.services.bom.planks.calculators;

import app.util.MapManipulator;
import app.web.entities.Plank;

import java.util.Map;

public class PostCalculatorImpl implements PostCalculator
{
    
    @Override
    public Plank findShortestUsablePost( Map< Integer, Plank > validPosts, int carportHeight )
    {
        Plank pickedPole;
        
        Plank[] planksSortedLength = MapManipulator.sortByValuePlankLengthToArray( validPosts );
        
        for ( Plank plank : planksSortedLength ) {
            if ( plank.getLength() >= carportHeight ) {
                pickedPole = plank;
                return new Plank( pickedPole );
            }
        }
        
        //No valid poles found, height is too high!
        return null;
    }
    
}
