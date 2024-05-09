package app.web.services.bom;

import app.web.constants.Config;
import app.web.entities.Plank;

import java.util.*;

public class PlankCalculatorImpl implements PlankCalculator  //TODO: Finish this
{
    
    int splitCarportSegmentIntoTwoSegmentsAtThisWidth = Config.Bom.SPLIT_CARPORT_SEGMENT_INTO_TWO_SEGMENTS_AT_THIS_WIDTH;
    
    @Override
    public List< Plank > resList( int totalLength, List< Integer > lengths, int lengthOption, List< Integer > prices,
                                  int priceOption )
    {
        
        if ( lengthOption + 1 >= lengths.size() && priceOption + 1 >= prices.size() ) {
//            resList( lengths.get( lengthOption + 1 ) )
        }
        return null;
    }
    
    @Override
    public int calcPostRows( Map<Integer, Plank > validPlanks, int carportWidth )
    {
        int rowAmount = 2;
        
        while ( carportWidth > ( splitCarportSegmentIntoTwoSegmentsAtThisWidth + 1000 ) ) {
            rowAmount++;
            carportWidth = carportWidth - splitCarportSegmentIntoTwoSegmentsAtThisWidth;
        }
        
        return rowAmount;
        
    }
    
    
    
}
