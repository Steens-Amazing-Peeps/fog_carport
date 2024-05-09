package testClasses.services;

import app.util.MapManipulator;
import app.web.entities.Plank;

import java.util.List;
import java.util.Map;

public class SimpleBeamCalculator
{
    
    //Initial Fori
    private int maxAmount;
    private int minAmount;
    
    private int maxPrice;
    private int minPrice;
    
   
    public List< Plank > calcBeamsOnPosts( Map< Integer, Plank > validPlanks, int carportLength, int rowAmount, int polePrice )
    {

//        int totalLength = carportLength * rowAmount;
        int totalLength = carportLength;
        
        int amount;
        int totalPrice;
        
        int maxAmount = -1;
        int maxPrice = -1;
        int maxPriceId = -1;
        
        int minAmount = -1;
        int minPrice = -1;
        
        int leftOver;
        int smallestLeftOverAmount = totalLength;
        Plank smallestLeftOverPlank = null;
        
        Plank[] plankArraySortedByPrice;
        
        
        
        //Calc Price, Max Amount and Min Amount
        for ( Plank plank : validPlanks.values() ) {
            
            if ( plank != null ) {
                
                plank.setPostPrice( polePrice );
                plank.calcPricePrMm();
                
                //Amount
                amount = totalLength / plank.getLength();
                if ( totalLength % plank.getLength() != 0 ) {
                    amount = amount + 1;
                }
                
                if ( amount > maxAmount ) {
                    maxAmount = amount;
                }
                
                if ( minAmount == -1 || amount < minAmount ) {
                    minAmount = amount;
                }
                
                //Price
                totalPrice = plank.getPriceWithPole() * amount;
                
                if ( totalPrice > maxPrice ) {
                    maxPrice = totalPrice;
                }
                
                if ( minPrice == -1 || totalPrice < minPrice ) {
                    minPrice = totalPrice;
                }
                
            }
            
        } //End - Initial fori
        
        this.maxAmount = maxAmount;
        this.minAmount = minAmount;
        
        this.maxPrice = maxPrice;
        this.minPrice = minPrice;
        
        return List.of( MapManipulator.sortByValuePlankPricePrMmToArray( validPlanks ) );
    }
    
    //Getters and Setters
    
    
    public int getMaxAmount()
    {
        return this.maxAmount;
    }
    
    public int getMinAmount()
    {
        return this.minAmount;
    }
    
    public int getMaxPrice()
    {
        return this.maxPrice;
    }
    
    public int getMinPrice()
    {
        return this.minPrice;
    }
    
}
