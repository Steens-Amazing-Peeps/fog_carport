package app.web.constants.attributes;



import app.util.MetricConversion;
import app.web.constants.Config;
import app.web.entities.Plank;
import app.web.entities.User;
import app.web.exceptions.DatabaseException;
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
    Key< BigDecimal >  minimumHeightInM = new Key< BigDecimal >( "minimumHeightInM" );
    BigDecimal MINIMUM_HEIGHT_IN_M = MetricConversion.mmToM( Config.Carport.MINIMUM_HEIGHT_IN_MM ).setScale( MetricConversion.COMMA_DIGITS_IN_STRING_M, RoundingMode.HALF_UP );
    
    Key< BigDecimal > maximumHeightInM = new Key< BigDecimal >( "maximumHeightInM" );
    BigDecimal MAXIMUM_HEIGHT_IN_M = MetricConversion.mmToM( Config.Carport.MAXIMUM_HEIGHT_IN_MM ).setScale( MetricConversion.COMMA_DIGITS_IN_STRING_M, RoundingMode.HALF_UP );
    
    Key< BigDecimal >  minimumLengthInM = new Key< BigDecimal >( "minimumLengthInM" );
    BigDecimal MINIMUM_LENGTH_IN_M = MetricConversion.mmToM( Config.Carport.MINIMUM_LENGTH_IN_MM ).setScale( MetricConversion.COMMA_DIGITS_IN_STRING_M, RoundingMode.HALF_UP );
    
    Key< BigDecimal >  maximumLengthInM = new Key< BigDecimal >( "maximumLengthInM" );
    BigDecimal MAXIMUM_LENGTH_IN_M = MetricConversion.mmToM( Config.Carport.MAXIMUM_LENGTH_IN_MM ).setScale( MetricConversion.COMMA_DIGITS_IN_STRING_M, RoundingMode.HALF_UP );
    
    Key< BigDecimal >  minimumWidthInM = new Key< BigDecimal >( "minimumWidthInM" );
    BigDecimal MINIMUM_WIDTH_IN_M = MetricConversion.mmToM( Config.Carport.MINIMUM_WIDTH_IN_MM ).setScale( MetricConversion.COMMA_DIGITS_IN_STRING_M, RoundingMode.HALF_UP );
    
    Key< BigDecimal >  maximumWidthInM = new Key< BigDecimal >( "maximumWidthInM" );
    BigDecimal MAXIMUM_WIDTH_IN_M = MetricConversion.mmToM( Config.Carport.MAXIMUM_WIDTH_IN_MM ).setScale( MetricConversion.COMMA_DIGITS_IN_STRING_M, RoundingMode.HALF_UP );
    
    
    
    
    static void startUp( JavalinConfig config, UserMapper userMapper )
    {
        try {
            USER_MAP.putAll( userMapper.readAll() );
            
        } catch ( DatabaseException e ) {
            throw new RuntimeException( e );
        }
        
        tempValidPlanksSetup();

        //Carport1Info
        config.appData( minimumHeightInM, MINIMUM_HEIGHT_IN_M );
        config.appData( maximumHeightInM, MAXIMUM_HEIGHT_IN_M );
        
        config.appData( minimumLengthInM, MINIMUM_LENGTH_IN_M );
        config.appData( maximumLengthInM, MAXIMUM_LENGTH_IN_M );
        
        config.appData( minimumWidthInM, MINIMUM_WIDTH_IN_M );
        config.appData( maximumWidthInM, MAXIMUM_WIDTH_IN_M );
        
        
    }
    
    private static void tempValidPlanksSetup()
    {
        int id = 0;
        //Boards
        Map< Integer, Plank > boards = new TreeMap<>();
        boards.put( id++, new Plank( id, 25, 200, 500, Plank.BOARD, 200 ) );
        boards.put( id++, new Plank( id, 25, 200, 900, Plank.BOARD, 200 ) );
        boards.put( id++, new Plank( id, 25, 200, 1100, Plank.BOARD, 200 ) );
        boards.put( id++, new Plank( id, 25, 200, 1300, Plank.BOARD, 200 ) );
        boards.put( id++, new Plank( id, 25, 200, 1400, Plank.BOARD, 200 ) );
        boards.put( id++, new Plank( id, 25, 200, 1500, Plank.BOARD, 200 ) );
        boards.put( id++, new Plank( id, 25, 200, 1700, Plank.BOARD, 200 ) );
        boards.put( id++, new Plank( id, 25, 200, 2000, Plank.BOARD, 200 ) );
        boards.put( id++, new Plank( id, 25, 200, 2300, Plank.BOARD, 200 ) );
        boards.put( id++, new Plank( id, 25, 200, 2600, Plank.BOARD, 200 ) );
        boards.put( id++, new Plank( id, 25, 200, 2900, Plank.BOARD, 200 ) );
        boards.put( id++, new Plank( id, 25, 200, 3200, Plank.BOARD, 200 ) );
        boards.put( id++, new Plank( id, 25, 200, 3400, Plank.BOARD, 200 ) );
        
        VALID_PLANKS.setBoards( boards );
        
        //Laths
        Map< Integer, Plank > laths = new TreeMap<>();
        laths.put( id++, new Plank( id, 38, 73, 500, Plank.LATH, 180 ) );
        laths.put( id++, new Plank( id, 38, 73, 900, Plank.LATH, 180 ) );
        laths.put( id++, new Plank( id, 38, 73, 1100, Plank.LATH, 180 ) );
        laths.put( id++, new Plank( id, 38, 73, 1300, Plank.LATH, 180 ) );
        laths.put( id++, new Plank( id, 38, 73, 1400, Plank.LATH, 180 ) );
        laths.put( id++, new Plank( id, 38, 73, 1500, Plank.LATH, 180 ) );
        laths.put( id++, new Plank( id, 38, 73, 1700, Plank.LATH, 180 ) );
        laths.put( id++, new Plank( id, 38, 73, 2000, Plank.LATH, 180 ) );
        laths.put( id++, new Plank( id, 38, 73, 2300, Plank.LATH, 180 ) );
        laths.put( id++, new Plank( id, 38, 73, 2600, Plank.LATH, 180 ) );
        laths.put( id++, new Plank( id, 38, 73, 2900, Plank.LATH, 180 ) );
        laths.put( id++, new Plank( id, 38, 73, 3200, Plank.LATH, 180 ) );
        laths.put( id++, new Plank( id, 38, 73, 3400, Plank.LATH, 180 ) );
        
        
        VALID_PLANKS.setLaths( laths );
        
        //Beams
        Map< Integer, Plank > beams = new TreeMap<>();
        beams.put( id++, new Plank( id, 45, 95, 500, Plank.BEAM, 175 ) );
        beams.put( id++, new Plank( id, 45, 95, 900, Plank.BEAM, 175 ) );
        beams.put( id++, new Plank( id, 45, 95, 1100, Plank.BEAM, 175 ) );
        beams.put( id++, new Plank( id, 45, 95, 1300, Plank.BEAM, 175 ) );
        beams.put( id++, new Plank( id, 45, 95, 1400, Plank.BEAM, 175 ) );
        beams.put( id++, new Plank( id, 45, 95, 1500, Plank.BEAM, 175 ) );
        beams.put( id++, new Plank( id, 45, 95, 1700, Plank.BEAM, 175 ) );
        beams.put( id++, new Plank( id, 45, 95, 2000, Plank.BEAM, 175 ) );
        beams.put( id++, new Plank( id, 45, 95, 2300, Plank.BEAM, 175 ) );
        beams.put( id++, new Plank( id, 45, 95, 2600, Plank.BEAM, 175 ) );
        beams.put( id++, new Plank( id, 45, 95, 2900, Plank.BEAM, 175 ) );
        beams.put( id++, new Plank( id, 45, 95, 3200, Plank.BEAM, 175 ) );
        beams.put( id++, new Plank( id, 45, 95, 3400, Plank.BEAM, 175 ) );
        
        VALID_PLANKS.setBeams( beams );
        
        
        
        //Rafters
        Map< Integer, Plank > rafters = new TreeMap<>();
        rafters.put( id++, new Plank( id, 45, 195, 500, Plank.RAFTER, 225 ) );
        rafters.put( id++, new Plank( id, 45, 195, 900, Plank.RAFTER, 225 ) );
        rafters.put( id++, new Plank( id, 45, 195, 1100, Plank.RAFTER, 225 ) );
        rafters.put( id++, new Plank( id, 45, 195, 1300, Plank.RAFTER, 225 ) );
        rafters.put( id++, new Plank( id, 45, 195, 1400, Plank.RAFTER, 225 ) );
        rafters.put( id++, new Plank( id, 45, 195, 1500, Plank.RAFTER, 225 ) );
        rafters.put( id++, new Plank( id, 45, 195, 1700, Plank.RAFTER, 225 ) );
        rafters.put( id++, new Plank( id, 45, 195, 2000, Plank.RAFTER, 225 ) );
        rafters.put( id++, new Plank( id, 45, 195, 2300, Plank.RAFTER, 225 ) );
        rafters.put( id++, new Plank( id, 45, 195, 2600, Plank.RAFTER, 225 ) );
        rafters.put( id++, new Plank( id, 45, 195, 2900, Plank.RAFTER, 225 ) );
        rafters.put( id++, new Plank( id, 45, 195, 3200, Plank.RAFTER, 225 ) );
        rafters.put( id++, new Plank( id, 45, 195, 3400, Plank.RAFTER, 225 ) );
        rafters.put( id++, new Plank( id, 45, 195, 3700, Plank.RAFTER, 225 ) );
        rafters.put( id++, new Plank( id, 45, 195, 3900, Plank.RAFTER, 225 ) );
        rafters.put( id++, new Plank( id, 45, 195, 4000, Plank.RAFTER, 225 ) );
        rafters.put( id++, new Plank( id, 45, 195, 4100, Plank.RAFTER, 225 ) );
        rafters.put( id++, new Plank( id, 45, 195, 6000, Plank.RAFTER, 300 ) );
        rafters.put( id++, new Plank( id, 45, 195, 9000, Plank.RAFTER, 400 ) );
        
        VALID_PLANKS.setRafters( rafters );
        
        //Posts
        Map< Integer, Plank > posts = new TreeMap<>();
      
        posts.put( id++, new Plank( id, 97, 97, 500, Plank.POST, 160 ) );
        posts.put( id++, new Plank( id, 97, 97, 900, Plank.POST, 160 ) );
        posts.put( id++, new Plank( id, 97, 97, 1100, Plank.POST, 160 ) );
        posts.put( id++, new Plank( id, 97, 97, 1300, Plank.POST, 160 ) );
        posts.put( id++, new Plank( id, 97, 97, 1400, Plank.POST, 160 ) );
        posts.put( id++, new Plank( id, 97, 97, 1500, Plank.POST, 160 ) );
        posts.put( id++, new Plank( id, 97, 97, 1700, Plank.POST, 160 ) );
        posts.put( id++, new Plank( id, 97, 97, 2000, Plank.POST, 160 ) );
        posts.put( id++, new Plank( id, 97, 97, 2300, Plank.POST, 160 ) );
        posts.put( id++, new Plank( id, 97, 97, 2600, Plank.POST, 160 ) );
        posts.put( id++, new Plank( id, 97, 97, 2900, Plank.POST, 160 ) );
        posts.put( id++, new Plank( id, 97, 97, 3200, Plank.POST, 160 ) );
        posts.put( id++, new Plank( id, 97, 97, 3400, Plank.POST, 160 ) );
        posts.put( id++, new Plank( id, 97, 97, 3700, Plank.POST, 160 ) );
        posts.put( id++, new Plank( id, 97, 97, 3900, Plank.POST, 160 ) );
        posts.put( id++, new Plank( id, 97, 97, 4100, Plank.POST, 160 ) );
        posts.put( id++, new Plank( id, 97, 97, 4200, Plank.POST, 160 ) );
        posts.put( id++, new Plank( id, 97, 97, 5000, Plank.POST, 160 ) );
        posts.put( id++, new Plank( id, 97, 97, 5100, Plank.POST, 160 ) );
        posts.put( id++, new Plank( id, 97, 97, 5200, Plank.POST, 160 ) );
        posts.put( id++, new Plank( id, 97, 97, 5400, Plank.POST, 160 ) );
        posts.put( id++, new Plank( id, 97, 97, 5500, Plank.POST, 160 ) );
        posts.put( id++, new Plank( id, 97, 97, 5700, Plank.POST, 190 ) );
        posts.put( id++, new Plank( id, 97, 97, 5900, Plank.POST, 200 ) );
        posts.put( id++, new Plank( id, 97, 97, 6000, Plank.POST, 300 ) );
        posts.put( id++, new Plank( id, 97, 97, 9000, Plank.POST, 400 ) );

        VALID_PLANKS.setPosts( posts );
    }
    
}
