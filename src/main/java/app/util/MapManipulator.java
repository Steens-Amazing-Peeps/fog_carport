package app.util;

import app.web.entities.Plank;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class MapManipulator
{
    
    public static Plank[] sortByValuePlankPricePrMmToArray( Map< Integer, Plank > map )
    {
        ArrayList< Plank > arrayList = new ArrayList<>( map.values() );
        arrayList.sort( Plank::compareTo );
        
        return arrayList.toArray( new Plank[ 0 ] );
    }
    
    public static Map< Integer, Plank > sortByValuePlankPricePrMm( Map< Integer, Plank > map )
    {
        ArrayList< Map.Entry< Integer, Plank > > mapEntries = new ArrayList<>( map.entrySet() );
        mapEntries.sort( Map.Entry.comparingByValue() );
        
        Map< Integer, Plank > mapSorted = new LinkedHashMap<>();
        for ( Map.Entry< Integer, Plank > mapEntry : mapEntries ) {
            mapSorted.put( mapEntry.getKey(), mapEntry.getValue() );
        }
        return mapSorted;
    }
    
    public static Map< Integer, BigDecimal > sortByValueBigDec( Map< Integer, BigDecimal > map )
    {
        ArrayList< Map.Entry< Integer, BigDecimal > > mapEntries = new ArrayList<>( map.entrySet() );
        mapEntries.sort( Map.Entry.comparingByValue() );
        
        Map< Integer, BigDecimal > mapSorted = new LinkedHashMap<>();
        for ( Map.Entry< Integer, BigDecimal > mapEntry : mapEntries ) {
            mapSorted.put( mapEntry.getKey(), mapEntry.getValue() );
        }
        return mapSorted;
    }
    
    public static Map< Integer, Integer > sortByValueInt( Map< Integer, Integer > map )
    {
        ArrayList< Map.Entry< Integer, Integer > > mapEntries = new ArrayList<>( map.entrySet() );
        mapEntries.sort( Map.Entry.comparingByValue() );
        
        Map< Integer, Integer > mapSorted = new LinkedHashMap<>();
        for ( Map.Entry< Integer, Integer > mapEntry : mapEntries ) {
            mapSorted.put( mapEntry.getKey(), mapEntry.getValue() );
        }
        return mapSorted;
    }
    
}
