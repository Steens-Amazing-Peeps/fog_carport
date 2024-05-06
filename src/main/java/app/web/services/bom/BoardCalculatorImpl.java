package app.web.services.bom;

import app.util.MapManipulator;
import app.web.entities.Plank;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class BoardCalculatorImpl implements BoardCalculator //TODO: Finish this
{
    
    public Plank calcBeamsOnPosts( Map< Integer, Plank > validPlanks, int length, int rowAmount )
    {
        
        int totalLength = length * rowAmount;
        
        int amount;
        int totalPrice;
        
        int maxAmount = 0;
        int maxPrice = 0;
        
        int minAmount = -1;
        int minPrice = -1;
        
        int leftOver;
        int smallestLeftOverAmount = totalLength;
        Plank smallestLeftOverPlank = null;
        
        Map< Integer, BigDecimal > idPricePrMm = new TreeMap<>();
        
        
        
        //Max Amount, Min Amount and map of prices
        for ( Plank plank : validPlanks.values() ) {
            
            if ( plank != null ) {
                
                //Amount
                amount = totalLength / plank.getLength();
                if ( amount > maxAmount ) {
                    maxAmount = amount;
                }
                
                if ( minAmount == -1 || amount < minAmount ) {
                    minAmount = amount;
                }
                
                //Price
                totalPrice = plank.getPrice() * amount;
                
                if ( totalPrice > maxPrice ) {
                    maxPrice = totalPrice;
                }
                
                if ( minPrice == -1 || totalPrice < minPrice ) {
                    minPrice = totalPrice;
                }
                
                idPricePrMm.put( plank.getId(), plank.getPricePrMm() );
                
            }
        }
        
        
        idPricePrMm = MapManipulator.sortByValue( idPricePrMm );
        
        
        return null;
    }
    
    public List< Plank > resList( int totalLength, List< Integer > lengths, int lengthOption, List< Integer > prices, int priceOption )
    {
        
        if ( lengthOption + 1 >= lengths.size() && priceOption + 1 >= prices.size() ) {
//            resList( lengths.get( lengthOption + 1 ) )
        }
        return null;
    }
    
    public int calcPostRows( List< Plank > validPlanks, int width )
    {
        
        
        
        
        return -1;
    }
    
    
    
}
