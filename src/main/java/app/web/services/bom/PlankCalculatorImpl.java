package app.web.services.bom;

import app.util.MapManipulator;
import app.web.constants.Config;
import app.web.entities.Plank;

import java.util.*;

public class PlankCalculatorImpl implements PlankCalculator  //TODO: Finish this
{
    
    //constants
    private int MinimumBatchSize = Config.Bom.MINIMUM_BATCH_SIZE;
    private int amountOfAcceptableWasteInMm = Config.Bom.AMOUNT_OF_WASTE_ACCEPTABLE_IN_MM;
    
    private int prioritizeLeastWasteAtPriceDiff = Config.Bom.USE_LEAST_WASTEFUL_OPTION_AT_PRICE_DIFFERENCE;
    
    //Initial Fori
    private int maxAmount;
    private int minAmount;
    
    private int maxPrice;
    private int minPrice;
    
    private int timesRun;
    
    
    //Is this the least wasteful option?
    private List< Plank > beamLeastWastefulPlankArrayList;
    private int beamLeastWastefulPrice;
    private int beamLeastWastefulWaste;
    
    
    //Is this the cheapest option?
    private List< Plank > beamCheapestPlankArrayList;
    private int beamCheapestPrice;
    private int beamCheapestWaste;
    
    //Difference
    private int beamPriceDff;
    private int beamWasteDiff;

    
    
    
    @Override
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
                
                plank.setPolePrice( polePrice );
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
        
        plankArraySortedByPrice = MapManipulator.sortByValuePlankPricePrMmToArray( validPlanks );
        
        int timesRun = 0;
        int runBatchUntil;
        if ( this.MinimumBatchSize != 0 ) {
            runBatchUntil = this.MinimumBatchSize;
        } else {
            runBatchUntil = validPlanks.size();
        }
        
        int currentPrice = 0;
        int currentLength = 0;
        List< Plank > currentPlankArrayList = new ArrayList<>();
        
        int firstIndex = 0;
        int highestIndexUsed = maxAmount;
        int indexToIncrease = 1;
        int[] arrayOfIndexes = new int[ maxAmount ];
        
        //Set to worst case scenario, guaranteed to find a better one
        List< Plank > leastWastefulPlankArrayList = new ArrayList<>();
        int leastWastefulPrice = maxPrice;
        int leastWastefulWaste = totalLength;
        
        //Set to worst case scenario, guaranteed to find a better one
        List< Plank > cheapestPlankArrayList = new ArrayList<>();
        int cheapestPrice = maxPrice;
        int cheapestWaste = totalLength;
        
        //Res
        List< Plank > resList;
        
        
        
        while ( true ) {
            currentLength = 0;
            currentPrice = 0;
            currentPlankArrayList = new ArrayList<>();
            
            if ( firstIndex < validPlanks.size() ) {
                for ( int i = 0; i < maxAmount; i++ ) {
                    
                    //Sum up length
                    currentLength = currentLength + plankArraySortedByPrice[ arrayOfIndexes[ i ] ].getLength();
                    
                    //Sum up price
                    currentPrice = currentPrice + plankArraySortedByPrice[ arrayOfIndexes[ i ] ].getPriceWithPole();
                    
                    //List of planks we want
                    currentPlankArrayList.add( plankArraySortedByPrice[ arrayOfIndexes[ i ] ] );
                    
                    //Length sum is longer or equal to needed length
                    //Additional planks would just be waste
                    if ( currentLength >= totalLength ) {
                        //Aka, how many planks we used
                        highestIndexUsed = i;
                        
                        //If we are trying to increase a secondary plank beyond the index of the first plank, then we should instead increase the first plank's index
                        if ( arrayOfIndexes[ indexToIncrease ] + 1 > arrayOfIndexes[ 0 ] ) {
                            
                            firstIndex++;
                            
                            indexToIncrease = 1;
                            
                            arrayOfIndexes = new int[ maxAmount ];
                            arrayOfIndexes[ 0 ] = firstIndex;
                            
                            break;
                        }
                        
                        //Increase the scheduled index to increase by 1
                        arrayOfIndexes[ indexToIncrease ] = arrayOfIndexes[ indexToIncrease ] + 1;
                        
                        //Move the increase pointer further up the array by 1
                        if ( indexToIncrease + 1 <= highestIndexUsed && indexToIncrease + 1 < arrayOfIndexes.length ) {
                            indexToIncrease++;
                        } else {
                            indexToIncrease = 1;
                        }
                        
                        //Escape the fori loop
                        break;
                    }
                }
            } else {
                //We are done, gotta return what we have
                if ( leastWastefulPrice - cheapestPrice <= this.prioritizeLeastWasteAtPriceDiff || this.prioritizeLeastWasteAtPriceDiff == 0 ) {
                    resList = leastWastefulPlankArrayList;
                } else {
                    resList = cheapestPlankArrayList;
                }
                break;
            }
            
            //Is this the least wasteful option?
            if ( currentLength - totalLength < leastWastefulWaste ) {
                leastWastefulPlankArrayList = currentPlankArrayList;
                leastWastefulPrice = currentPrice;
                leastWastefulWaste = currentLength - totalLength;
            }
            
            //Is this the cheapest option?
            if ( currentPrice < cheapestPrice ) {
                cheapestPlankArrayList = currentPlankArrayList;
                cheapestPrice = currentPrice;
                cheapestWaste = currentLength - totalLength;
            }
            
            
            //ResCheck
            if ( firstIndex > runBatchUntil ) {
                
                //Price difference is small enough that we should prioritize least wasteful
                if ( ( leastWastefulPrice - cheapestPrice <= this.prioritizeLeastWasteAtPriceDiff || this.prioritizeLeastWasteAtPriceDiff == 0 ) && ( leastWastefulWaste <= this.amountOfAcceptableWasteInMm || this.amountOfAcceptableWasteInMm == 0 ) ) {
                    resList = leastWastefulPlankArrayList;
                    break;
                }
                
                //Waste is small enough for cheapest option that we can accept the cheapest option
                if ( leastWastefulWaste <= this.amountOfAcceptableWasteInMm || this.amountOfAcceptableWasteInMm == 0 ) {
                    resList = cheapestPlankArrayList;
                    break;
                }
                
                //We have run out combinations and need more to find a valid result
                if ( runBatchUntil + 1 < validPlanks.size() ) {
                    runBatchUntil = runBatchUntil + 1;
                } else {
                    runBatchUntil = validPlanks.size() - 1;
                }
                
                //We have run out of combinations and just gotta return the best result
                if ( firstIndex >= validPlanks.size() ) {
                    if ( leastWastefulPrice - cheapestPrice <= this.prioritizeLeastWasteAtPriceDiff || this.prioritizeLeastWasteAtPriceDiff == 0 ) {
                        resList = leastWastefulPlankArrayList;
                    } else {
                        resList = cheapestPlankArrayList;
                    }
                    break;
                }
                
            }
            
            
            //Run again until a ResCheck passes
            timesRun++;
        }
        
        this.beamLeastWastefulPlankArrayList = leastWastefulPlankArrayList;
        this.beamLeastWastefulPrice = leastWastefulPrice;
        this.beamLeastWastefulWaste = leastWastefulWaste;
        
        this.beamCheapestPlankArrayList = cheapestPlankArrayList;
        this.beamCheapestPrice = cheapestPrice;
        this.beamCheapestWaste = cheapestWaste;
        
        this.beamPriceDff = leastWastefulPrice - cheapestPrice;
        this.beamWasteDiff = cheapestWaste - leastWastefulWaste;
        
        this.timesRun = timesRun;
        
        return resList;
    }
    
    
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
    public int calcPostRows( List< Plank > validPlanks, int width )
    {
        
        
        
        
        return -1;
    }
    
    @Override
    public String toString()
    {
        return "PlankCalculatorImpl{" + System.lineSeparator() +
               "MinimumBatchSize=" + this.MinimumBatchSize + System.lineSeparator() +
               ", amountOfAcceptableWasteInMm=" + this.amountOfAcceptableWasteInMm + System.lineSeparator() +
               ", prioritizeLeastWasteAtPriceDiff=" + this.prioritizeLeastWasteAtPriceDiff + System.lineSeparator() +
               ", beamLeastWastefulPlankArrayList=" + Arrays.toString( this.beamLeastWastefulPlankArrayList.toArray( new Plank[ 0 ] ) ) + System.lineSeparator() +
               ", beamLeastWastefulPrice=" + this.beamLeastWastefulPrice + System.lineSeparator() +
               ", beamLeastWastefulWaste=" + this.beamLeastWastefulWaste + System.lineSeparator() +
               ", beamCheapestPlankArrayList=" + Arrays.toString( this.beamCheapestPlankArrayList.toArray( new Plank[ 0 ] ) ) + System.lineSeparator() +
               ", beamCheapestPrice=" + this.beamCheapestPrice + System.lineSeparator() +
               ", beamCheapestWaste=" + this.beamCheapestWaste + System.lineSeparator() +
               ", beamPriceDff=" + this.beamPriceDff + System.lineSeparator() +
               ", beamWasteDiff=" + this.beamWasteDiff + System.lineSeparator() +
               ", maxAmount=" + this.maxAmount + System.lineSeparator() +
               ", minAmount=" + this.minAmount + System.lineSeparator() +
               ", maxPrice=" + this.maxPrice + System.lineSeparator() +
               ", minPrice=" + this.minPrice + System.lineSeparator() +
               ", timesRun = " + this.timesRun + System.lineSeparator() +
               '}';
    }
    
    
    //Getters and Setters----------------------------------
    @Override
    public int getMinimumBatchSize()
    {
        return this.MinimumBatchSize;
    }
    
    @Override
    public void setMinimumBatchSize( int minimumBatchSize )
    {
        this.MinimumBatchSize = minimumBatchSize;
    }
    
    @Override
    public int getAmountOfAcceptableWasteInMm()
    {
        return this.amountOfAcceptableWasteInMm;
    }
    
    @Override
    public void setAmountOfAcceptableWasteInMm( int amountOfAcceptableWasteInMm )
    {
        this.amountOfAcceptableWasteInMm = amountOfAcceptableWasteInMm;
    }
    
    @Override
    public int getPrioritizeLeastWasteAtPriceDiff()
    {
        return this.prioritizeLeastWasteAtPriceDiff;
    }
    
    @Override
    public void setPrioritizeLeastWasteAtPriceDiff( int prioritizeLeastWasteAtPriceDiff )
    {
        this.prioritizeLeastWasteAtPriceDiff = prioritizeLeastWasteAtPriceDiff;
    }
    
    @Override
    public List< Plank > getBeamLeastWastefulPlankArrayList()
    {
        return this.beamLeastWastefulPlankArrayList;
    }
    
    @Override
    public int getBeamLeastWastefulPrice()
    {
        return this.beamLeastWastefulPrice;
    }
    
    @Override
    public int getBeamLeastWastefulWaste()
    {
        return this.beamLeastWastefulWaste;
    }
    
    @Override
    public List< Plank > getBeamCheapestPlankArrayList()
    {
        return this.beamCheapestPlankArrayList;
    }
    
    @Override
    public int getBeamCheapestPrice()
    {
        return this.beamCheapestPrice;
    }
    
    @Override
    public int getBeamCheapestWaste()
    {
        return this.beamCheapestWaste;
    }
    
    @Override
    public int getBeamPriceDff()
    {
        return this.beamPriceDff;
    }
    
    @Override
    public int getBeamWasteDiff()
    {
        return this.beamWasteDiff;
    }
    
    @Override
    public int getMaxAmount()
    {
        return this.maxAmount;
    }
    
    @Override
    public int getMinAmount()
    {
        return this.minAmount;
    }
    
    @Override
    public int getMaxPrice()
    {
        return this.maxPrice;
    }
    
    @Override
    public int getMinPrice()
    {
        return this.minPrice;
    }
    
    @Override
    public int getTimesRun()
    {
        return this.timesRun;
    }
    
}
