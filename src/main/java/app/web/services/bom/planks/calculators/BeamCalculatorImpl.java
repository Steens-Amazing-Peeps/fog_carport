package app.web.services.bom.planks.calculators;

import app.util.MapManipulator;
import app.util.MetricConversion;
import app.util.demo.FileIO;
import app.web.constants.Config;
import app.web.entities.Plank;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class BeamCalculatorImpl implements BeamCalculator
{
    
    //Below exists purely for testing reasons------------------------------------
    //'constants'
    //These middleman instant variable exists to make tests easier
    private int minimumBatchSize = Config.Bom.MINIMUM_BATCH_SIZE;
    private int amountOfAcceptableWasteInMm = Config.Bom.AMOUNT_OF_WASTE_ACCEPTABLE_IN_MM;
    
    private int prioritizeLeastWasteAtPriceDiff = Config.Bom.USE_LEAST_WASTEFUL_OPTION_AT_PRICE_DIFFERENCE;
    private int checkAllCombinationsForCarportsEqualToOrShorterThanThisInMm = Config.Bom.CHECK_ALL_COMBINATIONS_FOR_CARPORTS_EQUAL_TO_OR_SHORTER_THAN_THIS_IN_MM;
    
    
    //End of purely for testing reasons------------------------------------
    
    //Calculations
    @Override
    public List< Plank > calcBeamsOnPosts( Map< Integer, Plank > validBeams, int carportLength, int rowAmount, int postPrice )
    {

//        int totalLength = carportLength * rowAmount;
        int totalLength = carportLength; //TODO: Should we output a finished list or a more row one like atm?
        //TODO: Use rowAmount or remove it from input
        
        int amount;
        int totalPrice;
        
        int maxAmount = -1;
        int maxPrice = -1;
        
        Plank plankNew; //Exists to avoid multi-threading bugs
        Map< Integer, Plank > validBeamsNew = new LinkedHashMap<>();
        
        //Calc Price, Max Price and Max Amount
        for ( Plank plank : validBeams.values() ) { //TODO: Figure out a way to skip this loop
            
            if ( plank != null ) {
                plankNew = new Plank( plank );
                validBeamsNew.put( plankNew.getId(), plankNew );
                
                plankNew.setPostPrice( postPrice );
                plankNew.calcPricePrMm();  //TODO: Where can this happen, if not in a preliminary loop? Maybe when we sort by price?... no probably wouldn't work
                
                //Amount
                amount = totalLength / plankNew.getLength();
                if ( totalLength % plankNew.getLength() != 0 ) {
                    amount = amount + 1;
                }
                
                if ( amount > maxAmount ) {
                    maxAmount = amount; //TODO: Can just use an arrayList in calcBeamsOnPostsLogic instead, but if we have this loop anyway, this might be better?
                }
                
                //Price
                totalPrice = plankNew.getPriceWithPole() * amount;
                
                if ( totalPrice > maxPrice ) {
                    maxPrice = totalPrice;  //TODO: Can just use the first plank as a starting point in calcBeamsOnPostsLogic instead, but if we have this loop anyway, this might be better?
                }
                
            }
            
        } //End - Initial fori
        
        List< Plank > resList = this.calcBeamsOnPostsLogic( validBeamsNew, maxAmount, maxPrice, totalLength );
        
        return resList;
    }
    
    @NotNull
    public List< Plank > calcBeamsOnPostsLogic( Map< Integer, Plank > validBeams, int maxAmount, int maxPrice, int totalLength )
    {
        //Initialize variables needed for the real logic
        Plank[] plankArraySortedByPrice = MapManipulator.sortByValuePlankPricePrMmToArray( validBeams );
        
        System.out.println("-------Cheapest to most Exspensive beams-------");
        for ( int i = 0; i < plankArraySortedByPrice.length; i++ ) {
            System.out.println( plankArraySortedByPrice[ i ] );
        }
        
        int runBatchUntil;
        if ( this.minimumBatchSize > 0 ) {
            runBatchUntil = this.minimumBatchSize;
        } else {
            runBatchUntil = validBeams.size();
        }
        
        if ( totalLength <= this.checkAllCombinationsForCarportsEqualToOrShorterThanThisInMm ) {
            runBatchUntil = validBeams.size();
        }
        
        int currentPrice;
        int currentLength;
        List< Plank > currentPlankArrayList;
        
        int firstIndex = 0;
        int highestIndexUsed;
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
        
        //Demo
        int combinationsChecked = 0;
        StringBuilder demoStringBuilder = new StringBuilder();
        
        //The Real Logic
        while ( true ) {
            currentLength = 0;
            currentPrice = 0;
            currentPlankArrayList = new ArrayList<>();
            
            if ( firstIndex < validBeams.size() ) {
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
                        
                        //Demo
                        combinationsChecked++;
                        demoStringBuilder.append( "Combination: " ).append( combinationsChecked ).append( " - Længden: " ).append( MetricConversion.mmToM( currentLength ) ).append( " m Combination: " ).append( Arrays.toString( Arrays.copyOf( arrayOfIndexes, highestIndexUsed + 1 ) ) ).append( " Længder: [ " );
                        for ( Plank plank : currentPlankArrayList ) {
                            demoStringBuilder.append( "( " ).append( plank.getId() ).append( ", " ).append( plank.getLength() ).append( " )" ).append( ", " );
                        }
                        demoStringBuilder.delete( demoStringBuilder.length() - 2, demoStringBuilder.length() );
                        demoStringBuilder.append( " ]" );
                        demoStringBuilder.append( System.lineSeparator() );
                        //Demo End
                        
                        
                        //If we are trying to increase a secondary plank beyond the index of the first plank, then we should instead increase the first plank's index
                        if ( arrayOfIndexes[ indexToIncrease ] + 1 > arrayOfIndexes[ 0 ] || highestIndexUsed == 0 ) {
                            
                            firstIndex++;
                            
                            indexToIncrease = 1;
                            
                            arrayOfIndexes = new int[ maxAmount ];
                            arrayOfIndexes[ 0 ] = firstIndex;
                            
                            break;
                        }
                        
                        //Increase the scheduled index to increase by 1
                        arrayOfIndexes[ indexToIncrease ] = arrayOfIndexes[ indexToIncrease ] + 1;
                        
                        //Reset all subsequent numbers to 0
                        Arrays.fill( arrayOfIndexes, indexToIncrease + 1, arrayOfIndexes.length, 0 );
                        
                        //Move the increase pointer further up the array by 1
                        if ( indexToIncrease + 1 <= highestIndexUsed && indexToIncrease + 1 < arrayOfIndexes.length ) {
                            indexToIncrease++;
                        } else {
                            for ( int j = 1; j < highestIndexUsed + 1; j++ ) {
                                if ( arrayOfIndexes[ j ] + 1 <= arrayOfIndexes[ 0 ] && arrayOfIndexes[ j ] + 1 <= arrayOfIndexes[ highestIndexUsed ] + 1 ) {
                                    indexToIncrease = j;
                                    break;
                                }
                            }
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
            
            //Calc the waste
            int currentWaste = currentLength - totalLength;
            
            //Is this the least wasteful option?
            //Option 1, this is the least wasteful option
            //Option 2, this is equally least wasteful as the leastwasteful, but also cheaper
            if ( currentWaste < leastWastefulWaste || ( currentWaste == leastWastefulWaste && currentPrice < leastWastefulPrice ) ) {
                leastWastefulPlankArrayList = currentPlankArrayList;
                leastWastefulPrice = currentPrice;
                leastWastefulWaste = currentWaste;
            }
            
            //Is this the cheapest option?
            //Option 1, this is the cheapest option
            //Option 2, this is equally cheap as the cheapest, but also less wasteful
            if ( currentPrice < cheapestPrice || ( currentPrice == cheapestPrice && currentWaste < cheapestWaste ) ) {
                cheapestPlankArrayList = currentPlankArrayList;
                cheapestPrice = currentPrice;
                cheapestWaste = currentWaste;
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
                if ( runBatchUntil + 1 < validBeams.size() ) {
                    runBatchUntil = runBatchUntil + 1;
                } else {
                    runBatchUntil = validBeams.size() - 1;
                }
                
                //We have run out of combinations and just gotta return the best result
                if ( firstIndex >= validBeams.size() ) {
                    if ( leastWastefulPrice - cheapestPrice <= this.prioritizeLeastWasteAtPriceDiff || this.prioritizeLeastWasteAtPriceDiff == 0 ) {
                        resList = leastWastefulPlankArrayList;
                    } else {
                        resList = cheapestPlankArrayList;
                    }
                    break;
                }
                
            }
            
            
            //Run again until a ResCheck passes
        }
        
        List< Plank > copyResList = new ArrayList<>();
        
        for ( Plank plank : resList ) {
            copyResList.add( new Plank( plank ) );
        }
        
        FileIO.txtOverWrite( "demoLog", demoStringBuilder.toString() );
        System.out.println();
        System.out.println( "Tjekkede " + combinationsChecked + " combinationer!" );
        return copyResList;
    }
    
    @Override
    public String toString()
    {
        return "PlankCalculatorImpl{" + System.lineSeparator() +
               "MinimumBatchSize=" + this.minimumBatchSize + System.lineSeparator() +
               ", amountOfAcceptableWasteInMm=" + this.amountOfAcceptableWasteInMm + System.lineSeparator() +
               ", prioritizeLeastWasteAtPriceDiff=" + this.prioritizeLeastWasteAtPriceDiff + System.lineSeparator() +
               '}';
    }
    
    
    //Below exists purely for testing reasons------------------------------------
    //Getters and Setters----------------------------------
    
    public int getMinimumBatchSize()
    {
        return this.minimumBatchSize;
    }
    
    
    public void setMinimumBatchSize( int minimumBatchSize )
    {
        this.minimumBatchSize = minimumBatchSize;
    }
    
    
    public int getAmountOfAcceptableWasteInMm()
    {
        return this.amountOfAcceptableWasteInMm;
    }
    
    
    public void setAmountOfAcceptableWasteInMm( int amountOfAcceptableWasteInMm )
    {
        this.amountOfAcceptableWasteInMm = amountOfAcceptableWasteInMm;
    }
    
    
    public int getPrioritizeLeastWasteAtPriceDiff()
    {
        return this.prioritizeLeastWasteAtPriceDiff;
    }
    
    
    public void setPrioritizeLeastWasteAtPriceDiff( int prioritizeLeastWasteAtPriceDiff )
    {
        this.prioritizeLeastWasteAtPriceDiff = prioritizeLeastWasteAtPriceDiff;
    }
    
}
