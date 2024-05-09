package app.web.services.bom;

import app.web.entities.Plank;

import java.util.List;
import java.util.Map;

public interface BeamCalculator
{
    
    List< Plank > calcBeamsOnPosts( Map< Integer, Plank > validPlanks, int carportLength, int rowAmount, int polePrice );
    
    //Getters and Setters----------------------------------
    int getMinimumBatchSize();
    
    void setMinimumBatchSize( int minimumBatchSize );
    
    int getAmountOfAcceptableWasteInMm();
    
    void setAmountOfAcceptableWasteInMm( int amountOfAcceptableWasteInMm );
    
    int getPrioritizeLeastWasteAtPriceDiff();
    
    void setPrioritizeLeastWasteAtPriceDiff( int prioritizeLeastWasteAtPriceDiff );
    
}
