package app.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class MapManipulator
{
    
    public static Map< Integer, BigDecimal > sortByValue( Map< Integer, BigDecimal > map )
    {
        ArrayList< Map.Entry< Integer, BigDecimal > > mapEntries = new ArrayList<>( map.entrySet() );
        mapEntries.sort( Map.Entry.comparingByValue() );
        
        Map< Integer, BigDecimal > mapSorted = new LinkedHashMap<>();
        for ( Map.Entry< Integer, BigDecimal > mapEntry : mapEntries ) {
            mapSorted.put( mapEntry.getKey(), mapEntry.getValue() );
        }
        return mapSorted;
    }
    
}
