import app.web.services.bom.PlankCalculator;
import app.web.services.bom.PlankCalculatorImpl;
import app.web.services.bom.ValidPlanks;
import app.web.services.bom.ValidPlanksImpl;
import app.web.entities.Carport;
import app.web.entities.Plank;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class EntityCarport
{
    
    private static ValidPlanks validPlanks = new ValidPlanksImpl();
    private static PlankCalculator plankCalculator;
    private static Carport carport;
    
    
    private static int id = 0;
    
    @BeforeAll
    static void beforeAll()
    {
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
        
        validPlanks.setBoards( boards );
        
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
        
        
        validPlanks.setLaths( laths );
        
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
        
        validPlanks.setBeams( beams );
        
        
        
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
        
        validPlanks.setRafters( rafters );
        
        //Posts
        Map< Integer, Plank > posts = new TreeMap<>();
        posts.put( id++, new Plank( id, 19, 100, 500, Plank.POST, 160 ) );
        posts.put( id++, new Plank( id, 19, 100, 900, Plank.POST, 160 ) );
        posts.put( id++, new Plank( id, 19, 100, 1100, Plank.POST, 160 ) );
        posts.put( id++, new Plank( id, 19, 100, 1300, Plank.POST, 160 ) );
        posts.put( id++, new Plank( id, 19, 100, 1400, Plank.POST, 160 ) );
        posts.put( id++, new Plank( id, 19, 100, 1500, Plank.POST, 160 ) );
        posts.put( id++, new Plank( id, 19, 100, 1700, Plank.POST, 160 ) );
        posts.put( id++, new Plank( id, 19, 100, 2000, Plank.POST, 160 ) );
        posts.put( id++, new Plank( id, 19, 100, 2300, Plank.POST, 160 ) );
        posts.put( id++, new Plank( id, 19, 100, 2600, Plank.POST, 160 ) );
        posts.put( id++, new Plank( id, 19, 100, 2900, Plank.POST, 160 ) );
        posts.put( id++, new Plank( id, 19, 100, 3200, Plank.POST, 160 ) );
        posts.put( id++, new Plank( id, 19, 100, 3400, Plank.POST, 160 ) );
        
        validPlanks.setPosts( posts );
        
        //Calculator
        plankCalculator = new PlankCalculatorImpl();
        
        //Carport
        carport = new Carport( plankCalculator, validPlanks );
    }
    
    @BeforeEach
    void setUp()
    {
    
    
    }
    
    @AfterEach
    void tearDown()
    {
    
    }
    
    @AfterAll
    static void afterAll()
    {
    
    }
    
    @Test
    void happyPath()
    {
    
    }
    
    @Test
    void calcPosts()
    {
    
    }
    
    @Test
    void calcBeamsOnPosts()
    {
        int carportLength = 8000;
        List< Plank > beams = plankCalculator.calcBeamsOnPosts( validPlanks.getBeams(), carportLength, 2, 10000 );
        
        System.out.println( "Amount of beams = " + beams.size() );
        
        int beamsTotalLength = 0;
        
        for ( Plank beam : beams ) {
            System.out.println( beam );
            beamsTotalLength = beamsTotalLength + beam.getLength();
        }
        
        System.out.println();
        System.out.println( plankCalculator );
        
        assertTrue( beamsTotalLength >= carportLength );
        
        assertTrue( plankCalculator.getBeamCheapestPrice() > 0 );
        assertTrue( plankCalculator.getBeamLeastWastefulWaste() >= 0 );
        
        assertTrue( plankCalculator.getBeamCheapestPrice() <= plankCalculator.getBeamLeastWastefulPrice() );
        assertTrue( plankCalculator.getBeamLeastWastefulWaste() <= plankCalculator.getBeamCheapestWaste() );
        
        assertTrue( plankCalculator.getBeamCheapestPrice() <= plankCalculator.getMinPrice() );
        assertTrue( plankCalculator.getBeamCheapestPrice() <= plankCalculator.getMaxPrice() );
        
        assertTrue( beams.size() <= plankCalculator.getMaxAmount() );
        assertTrue( beams.size() >= plankCalculator.getMinAmount() );
        
        
        
        //.........................
        
        carportLength = 6950;
        beams = plankCalculator.calcBeamsOnPosts( validPlanks.getBeams(), carportLength, 2, 10000 );
        
        System.out.println( "Amount of beams = " + beams.size() );
        
        beamsTotalLength = 0;
        
        for ( Plank beam : beams ) {
            System.out.println( beam );
            beamsTotalLength = beamsTotalLength + beam.getLength();
        }
        
        System.out.println();
        System.out.println( plankCalculator );
        
        assertTrue( beamsTotalLength >= carportLength );
        
        assertTrue( plankCalculator.getBeamCheapestPrice() > 0 );
        assertTrue( plankCalculator.getBeamLeastWastefulWaste() >= 0 );
        
        assertTrue( plankCalculator.getBeamCheapestPrice() <= plankCalculator.getBeamLeastWastefulPrice() );
        assertTrue( plankCalculator.getBeamLeastWastefulWaste() <= plankCalculator.getBeamCheapestWaste() );
        
        assertTrue( plankCalculator.getBeamCheapestPrice() <= plankCalculator.getMinPrice() );
        assertTrue( plankCalculator.getBeamCheapestPrice() <= plankCalculator.getMaxPrice() );
        
        assertTrue( beams.size() <= plankCalculator.getMaxAmount() );
        assertTrue( beams.size() >= plankCalculator.getMinAmount() );
        
        //.........................
        
        plankCalculator.setMinimumBatchSize( validPlanks.getBeams().size() );
        
        //......................... Search every combination
        
        carportLength = 8000;
        beams = plankCalculator.calcBeamsOnPosts( validPlanks.getBeams(), carportLength, 2, 10000 );
        
        System.out.println( "Amount of beams = " + beams.size() );
        
        beamsTotalLength = 0;
        
        for ( Plank beam : beams ) {
            System.out.println( beam );
            beamsTotalLength = beamsTotalLength + beam.getLength();
        }
        
        System.out.println();
        System.out.println( plankCalculator );
        
        assertTrue( beamsTotalLength >= carportLength );
        
        assertTrue( plankCalculator.getBeamCheapestPrice() > 0 );
        assertTrue( plankCalculator.getBeamLeastWastefulWaste() >= 0 );
        
        assertTrue( plankCalculator.getBeamCheapestPrice() <= plankCalculator.getBeamLeastWastefulPrice() );
        assertTrue( plankCalculator.getBeamLeastWastefulWaste() <= plankCalculator.getBeamCheapestWaste() );
        
        assertTrue( plankCalculator.getBeamCheapestPrice() <= plankCalculator.getMinPrice() );
        assertTrue( plankCalculator.getBeamCheapestPrice() <= plankCalculator.getMaxPrice() );
        
        assertTrue( beams.size() <= plankCalculator.getMaxAmount() );
        assertTrue( beams.size() >= plankCalculator.getMinAmount() );
        
        //.........................
        
        carportLength = 6950;
        beams = plankCalculator.calcBeamsOnPosts( validPlanks.getBeams(), carportLength, 2, 10000 );
        
        System.out.println( "Amount of beams = " + beams.size() );
        
        beamsTotalLength = 0;
        
        for ( Plank beam : beams ) {
            System.out.println( beam );
            beamsTotalLength = beamsTotalLength + beam.getLength();
        }
        
        System.out.println();
        System.out.println( plankCalculator );
        
        assertTrue( beamsTotalLength >= carportLength );
        
        assertTrue( plankCalculator.getBeamCheapestPrice() > 0 );
        assertTrue( plankCalculator.getBeamLeastWastefulWaste() >= 0 );
        
        assertTrue( plankCalculator.getBeamCheapestPrice() <= plankCalculator.getBeamLeastWastefulPrice() );
        assertTrue( plankCalculator.getBeamLeastWastefulWaste() <= plankCalculator.getBeamCheapestWaste() );
        
        assertTrue( plankCalculator.getBeamCheapestPrice() <= plankCalculator.getMinPrice() );
        assertTrue( plankCalculator.getBeamCheapestPrice() <= plankCalculator.getMaxPrice() );
        
        assertTrue( beams.size() <= plankCalculator.getMaxAmount() );
        assertTrue( beams.size() >= plankCalculator.getMinAmount() );
        
    }
    
    
    
    
    
    
}
