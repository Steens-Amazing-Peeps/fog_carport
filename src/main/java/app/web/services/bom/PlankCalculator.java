package app.web.services.bom;

import app.web.entities.Plank;

import java.util.List;
import java.util.Map;

public interface PlankCalculator
{
    
    List< Plank > calcBeamsOnPosts( Map< Integer, Plank > validPlanks, int carportLength, int rowAmount, int polePrice );
    
    List< Plank > resList( int totalLength, List< Integer > lengths, int lengthOption, List< Integer > prices,
                           int priceOption );
    
    int calcPostRows( List< Plank > validPlanks, int width );
    
    //Getters and Setters----------------------------------
    int getMinimumBatchSize();
    
    void setMinimumBatchSize( int minimumBatchSize );
    
    int getAmountOfAcceptableWasteInMm();
    
    void setAmountOfAcceptableWasteInMm( int amountOfAcceptableWasteInMm );
    
    int getPrioritizeLeastWasteAtPriceDiff();
    
    void setPrioritizeLeastWasteAtPriceDiff( int prioritizeLeastWasteAtPriceDiff );
    
    List< Plank > getBeamLeastWastefulPlankArrayList();
    
    int getBeamLeastWastefulPrice();
    
    int getBeamLeastWastefulWaste();
    
    List< Plank > getBeamCheapestPlankArrayList();
    
    int getBeamCheapestPrice();
    
    int getBeamCheapestWaste();
    
    int getBeamPriceDff();
    
    int getBeamWasteDiff();
    
    int getMaxAmount();
    
    int getMinAmount();
    
    int getMaxPrice();
    
    int getMinPrice();
    
    int getTimesRun();
    
}
