import app.web.entities.Bom;
import app.web.entities.Carport;
import app.web.entities.Plank;
import app.web.exceptions.WebInvalidInputException;
import app.web.services.bom.planks.*;
import app.web.services.bom.planks.calculators.*;
import org.junit.jupiter.api.*;
import testClasses.services.SimpleBeamCalculator;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EntityCarport
{
    
    private static final ValidPlanks validPlanks = new ValidPlanksImpl();
    
    private PlankCalculator plankCalculator;
    private PostCalculator postCalculator;
    private BeamCalculator beamCalculator;
    private RafterCalculator rafterCalculator;
    
    private SimpleBeamCalculator simpleBeamCalculator;
    private Carport carport;
    
    
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
        rafters.put( id++, new Plank( id, 45, 195, 3700, Plank.RAFTER, 225 ) );
        rafters.put( id++, new Plank( id, 45, 195, 3900, Plank.RAFTER, 225 ) );
        rafters.put( id++, new Plank( id, 45, 195, 4000, Plank.RAFTER, 225 ) );
        rafters.put( id++, new Plank( id, 45, 195, 4100, Plank.RAFTER, 225 ) );
        
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
        posts.put( id++, new Plank( id, 19, 100, 3700, Plank.POST, 160 ) );
        posts.put( id++, new Plank( id, 19, 100, 3900, Plank.POST, 160 ) );
        posts.put( id++, new Plank( id, 19, 100, 4100, Plank.POST, 160 ) );
        posts.put( id++, new Plank( id, 19, 100, 4200, Plank.POST, 160 ) );
        posts.put( id++, new Plank( id, 19, 100, 5000, Plank.POST, 160 ) );
        posts.put( id++, new Plank( id, 19, 100, 5100, Plank.POST, 160 ) );
        posts.put( id++, new Plank( id, 19, 100, 5200, Plank.POST, 160 ) );
        posts.put( id++, new Plank( id, 19, 100, 5400, Plank.POST, 160 ) );
        posts.put( id++, new Plank( id, 19, 100, 5500, Plank.POST, 160 ) );
        posts.put( id++, new Plank( id, 19, 100, 5700, Plank.POST, 190 ) );
        posts.put( id++, new Plank( id, 19, 100, 5900, Plank.POST, 200 ) );
        posts.put( id++, new Plank( id, 19, 100, 6000, Plank.POST, 300 ) );
        posts.put( id++, new Plank( id, 19, 100, 9000, Plank.POST, 400 ) );
        
        validPlanks.setPosts( posts );
        
        
    }
    
    @BeforeEach
    void setUp()
    {
        //Calculator
        this.postCalculator = new PostCalculatorImpl();
        
        this.beamCalculator = new BeamCalculatorImpl();
        this.simpleBeamCalculator = new SimpleBeamCalculator();
        
        this.rafterCalculator = new RafterCalculatorImpl();
        
        
        this.plankCalculator = new PlankCalculatorImpl( this.postCalculator, this.beamCalculator, this.rafterCalculator );
        
        //Carport
        this.carport = new Carport( this.plankCalculator, validPlanks );
        this.carport.setHeight( 5000 );
        this.carport.setLength( 9000 );
        this.carport.setWidth( 7000 );
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
    void calcPostRows()
    {
        int rowAmount;
        
        rowAmount = this.plankCalculator.calcPostRows( validPlanks.getPosts(), 5000 );
        assertEquals( 3, rowAmount );
        
        rowAmount = this.plankCalculator.calcPostRows( validPlanks.getPosts(), 4000 );
        assertEquals( 2, rowAmount );
        
        rowAmount = this.plankCalculator.calcPostRows( validPlanks.getPosts(), 8000 );
        assertEquals( 4, rowAmount );
        
        rowAmount = this.plankCalculator.calcPostRows( validPlanks.getPosts(), 3000 );
        assertEquals( 2, rowAmount );
        
        rowAmount = this.plankCalculator.calcPostRows( validPlanks.getPosts(), 0 );
        assertEquals( 2, rowAmount );
        
        rowAmount = this.plankCalculator.calcPostRows( validPlanks.getPosts(), 7000 );
        assertEquals( 3, rowAmount );
    }
    
    @Test
    void calcPosts()
    {
    
    }
    
    @Test
    void calcBeamsOnPosts()
    {
        System.out.println( "------CALC BEAMS ON POSTS------" );
        
        int carportLength = 8000;
        int rowAmount = 2;
        int polePrice = 10000;
        
        beamTests( validPlanks.getBeams(), carportLength, rowAmount, polePrice, this.beamCalculator );
        
        
        //.........................
        
        carportLength = 6950;
        beamTests( validPlanks.getBeams(), carportLength, rowAmount, polePrice, this.beamCalculator );
        
        
        //.........................
        
        this.beamCalculator.setMinimumBatchSize( validPlanks.getBeams().size() );
        
        //......................... Search every combination
        
        carportLength = 8000;
        beamTests( validPlanks.getBeams(), carportLength, rowAmount, polePrice, this.beamCalculator );
        
        
        //.........................
        
        carportLength = 6950;
        beamTests( validPlanks.getBeams(), carportLength, rowAmount, polePrice, this.beamCalculator );
        
    }
    
    private static void beamTests( Map< Integer, Plank > validPlanksMap, int carportLength, int rowAmount, int polePrice, BeamCalculator beamCalculator )
    {
        
        SimpleBeamCalculator simpleBeamCalculator = new SimpleBeamCalculator();
        simpleBeamCalculator.calcBeamsOnPosts( validPlanksMap, carportLength, rowAmount, polePrice );
        
        List< Plank > cheapestBeams = beamCalculator.calcBeamsOnPosts( validPlanksMap, carportLength, rowAmount, polePrice );
        
        int prioritizeLeastWasteAtPriceDiff = beamCalculator.getPrioritizeLeastWasteAtPriceDiff();
        beamCalculator.setPrioritizeLeastWasteAtPriceDiff( 0 );
        List< Plank > leastWastefulBeams = beamCalculator.calcBeamsOnPosts( validPlanksMap, carportLength, rowAmount, polePrice );
        beamCalculator.setPrioritizeLeastWasteAtPriceDiff( prioritizeLeastWasteAtPriceDiff );
        
        int cheapestLength = 0;
        
        int cheapestBeamsWaste;
        int cheapestBeamsPrice = 0;
        
        for ( Plank beam : cheapestBeams ) {
            cheapestLength = cheapestLength + beam.getLength();
            cheapestBeamsPrice = cheapestBeamsPrice + beam.getPriceWithPole();
        }
        
        cheapestBeamsWaste = cheapestLength - carportLength;
        
        int leastWastefulLength = 0;
        
        int leastWastefulBeamsWaste;
        int leastWastefulBeamsPrice = 0;
        
        for ( Plank beam : leastWastefulBeams ) {
            leastWastefulLength = leastWastefulLength + beam.getLength();
            leastWastefulBeamsPrice = leastWastefulBeamsPrice + beam.getPriceWithPole();
        }
        
        leastWastefulBeamsWaste = leastWastefulLength - carportLength;
        
        //Console Printer
        
        System.out.println();
        System.out.println();
        System.out.println( "--CHEAPEST BEAMS--" );
        System.out.println( "Price = " + cheapestBeamsPrice );
        System.out.println( "Waste = " + cheapestBeamsWaste );
        for ( Plank beam : cheapestBeams ) {
            System.out.println( beam );
        }
        
        
        System.out.println();
        System.out.println( "--LEAST WASTEFUL BEAMS--" );
        System.out.println( "Price = " + leastWastefulBeamsPrice );
        System.out.println( "Waste = " + leastWastefulBeamsWaste );
        for ( Plank beam : leastWastefulBeams ) {
            System.out.println( beam );
        }
        
        
        //Automated Tests
        assertTrue( cheapestLength >= carportLength );
        assertTrue( leastWastefulLength >= carportLength );
        
        assertTrue( cheapestBeamsPrice > 0 );
        assertTrue( cheapestBeamsWaste >= 0 );
        
        assertTrue( cheapestBeamsPrice <= leastWastefulBeamsPrice );
        assertTrue( leastWastefulBeamsWaste <= cheapestBeamsWaste );
        
        assertTrue( cheapestBeamsPrice <= simpleBeamCalculator.getMinPrice() );
        assertTrue( cheapestBeamsPrice <= simpleBeamCalculator.getMaxPrice() );
        
        assertTrue( cheapestBeams.size() <= simpleBeamCalculator.getMaxAmount() );
        assertTrue( cheapestBeams.size() >= simpleBeamCalculator.getMinAmount() );
        
        assertTrue( leastWastefulBeams.size() <= simpleBeamCalculator.getMaxAmount() );
        assertTrue( leastWastefulBeams.size() >= simpleBeamCalculator.getMinAmount() );
        
        
    }
    
    @Test
    void calcRafters()
    {
    
    }
    
    @Test
    void calcBom()
    {
        Bom bom = null;
        try {
            bom = this.carport.calcBom();
            
        } catch ( WebInvalidInputException e ) {
            throw new RuntimeException( e );
        }
        
        System.out.println( bom );
        
    }
    
    
    
    
    
    
}
