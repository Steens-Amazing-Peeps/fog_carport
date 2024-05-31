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

public class CalculatorBomTest
{
    
    private static final ValidPlanks validPlanks = new ValidPlanksImpl();
    
    private PlankCalculatorImpl plankCalculatorImpl;
    private PostCalculatorImpl postCalculatorImpl;
    private BeamCalculatorImpl beamCalculatorImpl;
    private RafterCalculatorImpl rafterCalculatorImpl;
    
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
        rafters.put( id++, new Plank( id, 45, 195, 6000, Plank.RAFTER, 300 ) );
        rafters.put( id++, new Plank( id, 45, 195, 9000, Plank.RAFTER, 400 ) );
        
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
        this.postCalculatorImpl = new PostCalculatorImpl();
        
        this.beamCalculatorImpl = new BeamCalculatorImpl();
        this.beamCalculatorImpl.setMinimumBatchSize( 5 );
        this.beamCalculatorImpl.setPrioritizeLeastWasteAtPriceDiff( 1000 );
        this.beamCalculatorImpl.setAmountOfAcceptableWasteInMm( 50 );
        
        this.simpleBeamCalculator = new SimpleBeamCalculator();
        
        this.rafterCalculatorImpl = new RafterCalculatorImpl();
        
        this.plankCalculatorImpl = new PlankCalculatorImpl( this.postCalculatorImpl, this.beamCalculatorImpl, this.rafterCalculatorImpl );
        this.plankCalculatorImpl.setMinimumDistanceBetweenPolesCarportWidthInMm( 1000 );
        this.plankCalculatorImpl.setSplitCarportSegmentIntoTwoSegmentsAtThisWidthInMm( 3000 );
        //Carport
        this.carport = new Carport( this.plankCalculatorImpl, validPlanks );
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
        
        rowAmount = this.plankCalculatorImpl.calcPostRows( validPlanks.getPosts(), 5000 );
        assertEquals( 3, rowAmount );
        
        rowAmount = this.plankCalculatorImpl.calcPostRows( validPlanks.getPosts(), 4000 );
        assertEquals( 2, rowAmount );
        
        rowAmount = this.plankCalculatorImpl.calcPostRows( validPlanks.getPosts(), 8000 );
        assertEquals( 4, rowAmount );
        
        rowAmount = this.plankCalculatorImpl.calcPostRows( validPlanks.getPosts(), 3000 );
        assertEquals( 2, rowAmount );
        
        rowAmount = this.plankCalculatorImpl.calcPostRows( validPlanks.getPosts(), 0 );
        assertEquals( 2, rowAmount );
        
        rowAmount = this.plankCalculatorImpl.calcPostRows( validPlanks.getPosts(), 7000 );
        assertEquals( 3, rowAmount );
    }
    
    @Test
    void calcPosts()
    {
        Plank post;
        
        this.carport.setHeight( 1000 );
        post = this.postCalculatorImpl.findShortestUsablePost( validPlanks.getPosts(), this.carport.getHeight() );
        assertEquals( 1100, post.getLength() );
        
        this.carport.setHeight( 2000 );
        post = this.postCalculatorImpl.findShortestUsablePost( validPlanks.getPosts(), this.carport.getHeight() );
        assertEquals( 2000, post.getLength() );
        
        this.carport.setHeight( 3201 );
        post = this.postCalculatorImpl.findShortestUsablePost( validPlanks.getPosts(), this.carport.getHeight() );
        assertEquals( 3400, post.getLength() );
    }
    
    @Test
    void calcBeamsOnPosts()
    {
        System.out.println( "------CALC BEAMS ON POSTS------" );
        
        int carportLength = 8000;
        int rowAmount = 2;
        int polePrice = 10000;
        
        //Checker 5 planker og deres kombinationer ud af 13 planker
        beamTests( validPlanks.getBeams(), carportLength, rowAmount, polePrice, this.beamCalculatorImpl );
        
        
        //.........................
        
        carportLength = 6950;
        beamTests( validPlanks.getBeams(), carportLength, rowAmount, polePrice, this.beamCalculatorImpl );
        
        
        //.........................
        
        //Checker alle planker og deres kombinationer ud af 13 planker
        this.beamCalculatorImpl.setMinimumBatchSize( validPlanks.getBeams().size() );
        
        //......................... Search every combination
        
        carportLength = 8000;
        beamTests( validPlanks.getBeams(), carportLength, rowAmount, polePrice, this.beamCalculatorImpl );
        
        
        //.........................
        
        carportLength = 6950;
        beamTests( validPlanks.getBeams(), carportLength, rowAmount, polePrice, this.beamCalculatorImpl );
        
    }
    
    private static void beamTests( Map< Integer, Plank > validPlanksMap, int carportLength, int rowAmount, int polePrice, BeamCalculatorImpl beamCalculatorImpl )
    {
        
        SimpleBeamCalculator simpleBeamCalculator = new SimpleBeamCalculator();
        simpleBeamCalculator.calcBeamsOnPosts( validPlanksMap, carportLength, rowAmount, polePrice );
        
        List< Plank > cheapestBeams = beamCalculatorImpl.calcBeamsOnPosts( validPlanksMap, carportLength, rowAmount, polePrice );
        
        int prioritizeLeastWasteAtPriceDiff = beamCalculatorImpl.getPrioritizeLeastWasteAtPriceDiff();
        beamCalculatorImpl.setPrioritizeLeastWasteAtPriceDiff( 0 );
        List< Plank > leastWastefulBeams = beamCalculatorImpl.calcBeamsOnPosts( validPlanksMap, carportLength, rowAmount, polePrice );
        beamCalculatorImpl.setPrioritizeLeastWasteAtPriceDiff( prioritizeLeastWasteAtPriceDiff );
        
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
        List< Plank > rafters;
        this.carport.setWidth( 1000 );
        try {
            rafters = this.rafterCalculatorImpl.findShortestUsableRafter( validPlanks.getRafters(), this.carport.getWidth(), 2, this.plankCalculatorImpl.getSplitCarportSegmentIntoTwoSegmentsAtThisWidthInMm() );
        } catch ( WebInvalidInputException e ) {
            throw new RuntimeException( e );
        }
        
        assertEquals( 1, rafters.size() );
        for ( Plank rafter : rafters ) {
            assertEquals( 1100, rafter.getLength() );
        }
        
        
        this.carport.setWidth( 5000 );
        try {
            rafters = this.rafterCalculatorImpl.findShortestUsableRafter( validPlanks.getRafters(), this.carport.getWidth(), 3, this.plankCalculatorImpl.getSplitCarportSegmentIntoTwoSegmentsAtThisWidthInMm() );
        } catch ( WebInvalidInputException e ) {
            throw new RuntimeException( e );
        }
        
        assertEquals( 2, rafters.size() );
        int indexCount = 0;
        for ( Plank rafter : rafters ) {
            
            if ( indexCount == 0 ) {
                assertEquals( 3200, rafter.getLength() );
            } else if ( indexCount == 1 ) {
                assertEquals( 2000, rafter.getLength() );
            }
            
            indexCount++;
        }
        
    }
    
    @Test
    void calcBom()
    {
        int height;
        int length;
        int width;
        
        height = 2200;
        length = 7800;
        width = 6000;
        
        this.carport.setHeight( height );
        this.carport.setLength( length );
        this.carport.setWidth( width );
        this.plankCalculatorImpl.setMinimumDistanceBetweenPolesCarportWidthInMm( 0 );
        this.plankCalculatorImpl.setSplitCarportSegmentIntoTwoSegmentsAtThisWidthInMm( 6000 );
        
        Bom bom = null;
        try {
            bom = this.carport.calcBom();
            
        } catch ( WebInvalidInputException e ) {
            throw new RuntimeException( e );
        }
        
        System.out.println( bom );
        
        System.out.println( "height = " + height );
        System.out.println( "length = " + length );
        System.out.println( "width = " + width );
        
        int sumBeamLength = 0;
        for ( Plank beam : bom.getBeams().values() ) {
            sumBeamLength = sumBeamLength + ( beam.getLength() * beam.getAmount() );
        }
        
        sumBeamLength = sumBeamLength / bom.getRowAmount();
        
        System.out.println( "Beam sum length = " + sumBeamLength );
        
        assertEquals( bom.getTotalBeams() + 2, bom.getTotalPosts() );
        assertEquals( bom.getTotalPosts() - 2, bom.getTotalBeams() );
        
        assertEquals( bom.getTotalPosts() / 2, bom.getTotalRafters() );
        
        //Beam length, is it within acceptable waste and long enough?
        assertTrue( sumBeamLength >= this.carport.getLength() && sumBeamLength <= ( this.carport.getLength() + this.beamCalculatorImpl.getAmountOfAcceptableWasteInMm() ) );
        
        //Post and Rafter Length
        for ( Plank post : bom.getPosts().values()) {
            assertEquals( 2300, post.getLength() );
        };
        
        for ( Plank rafter : bom.getRafters().values()) {
            assertEquals( 6000, rafter.getLength() );
        };
        
        
    }
    
    
    
    
    
    
}
