package app.web.constants.attributes;



import app.util.MetricConversion;
import app.web.constants.Config;
import app.web.entities.Plank;
import app.web.entities.User;
import app.web.exceptions.DatabaseException;
import app.web.persistence.mappers.PlankMapper;
import app.web.persistence.mappers.UserMapper;
import app.web.services.bom.planks.ValidPlanks;
import app.web.services.bom.planks.ValidPlanksImpl;
import io.javalin.config.JavalinConfig;
import io.javalin.config.Key;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public interface WebGlobalAttributes
{
    
    //Should be global but we suck
    String userMap = "userMap";
    Map< Integer, User > USER_MAP = new LinkedHashMap<>();
    
    ValidPlanks VALID_PLANKS = new ValidPlanksImpl();
    
    
    //Carport1Info
    Key< BigDecimal > minimumHeightInM = new Key< BigDecimal >( "minimumHeightInM" );
    BigDecimal MINIMUM_HEIGHT_IN_M = MetricConversion.mmToM( Config.Carport.MINIMUM_HEIGHT_IN_MM ).setScale( MetricConversion.COMMA_DIGITS_IN_STRING_M, RoundingMode.HALF_UP );
    
    Key< BigDecimal > maximumHeightInM = new Key< BigDecimal >( "maximumHeightInM" );
    BigDecimal MAXIMUM_HEIGHT_IN_M = MetricConversion.mmToM( Config.Carport.MAXIMUM_HEIGHT_IN_MM ).setScale( MetricConversion.COMMA_DIGITS_IN_STRING_M, RoundingMode.HALF_UP );
    
    Key< BigDecimal > minimumLengthInM = new Key< BigDecimal >( "minimumLengthInM" );
    BigDecimal MINIMUM_LENGTH_IN_M = MetricConversion.mmToM( Config.Carport.MINIMUM_LENGTH_IN_MM ).setScale( MetricConversion.COMMA_DIGITS_IN_STRING_M, RoundingMode.HALF_UP );
    
    Key< BigDecimal > maximumLengthInM = new Key< BigDecimal >( "maximumLengthInM" );
    BigDecimal MAXIMUM_LENGTH_IN_M = MetricConversion.mmToM( Config.Carport.MAXIMUM_LENGTH_IN_MM ).setScale( MetricConversion.COMMA_DIGITS_IN_STRING_M, RoundingMode.HALF_UP );
    
    Key< BigDecimal > minimumWidthInM = new Key< BigDecimal >( "minimumWidthInM" );
    BigDecimal MINIMUM_WIDTH_IN_M = MetricConversion.mmToM( Config.Carport.MINIMUM_WIDTH_IN_MM ).setScale( MetricConversion.COMMA_DIGITS_IN_STRING_M, RoundingMode.HALF_UP );
    
    Key< BigDecimal > maximumWidthInM = new Key< BigDecimal >( "maximumWidthInM" );
    BigDecimal MAXIMUM_WIDTH_IN_M = MetricConversion.mmToM( Config.Carport.MAXIMUM_WIDTH_IN_MM ).setScale( MetricConversion.COMMA_DIGITS_IN_STRING_M, RoundingMode.HALF_UP );
    
    
    
    
    static void startUp( JavalinConfig config, UserMapper userMapper, PlankMapper plankMapper )
    {
        try {
            USER_MAP.putAll( userMapper.readAll() );
            
        } catch ( DatabaseException e ) {
            throw new RuntimeException( e );
        }
        
        //Valid Planks
        VALID_PLANKS.startUp( plankMapper );
        System.out.println( VALID_PLANKS.toString() );
        
        //Carport1Info
        config.appData( minimumHeightInM, MINIMUM_HEIGHT_IN_M );
        config.appData( maximumHeightInM, MAXIMUM_HEIGHT_IN_M );
        
        config.appData( minimumLengthInM, MINIMUM_LENGTH_IN_M );
        config.appData( maximumLengthInM, MAXIMUM_LENGTH_IN_M );
        
        config.appData( minimumWidthInM, MINIMUM_WIDTH_IN_M );
        config.appData( maximumWidthInM, MAXIMUM_WIDTH_IN_M );
        
        
    }
}
