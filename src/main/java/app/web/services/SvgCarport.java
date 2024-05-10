package app.web.services;

import app.util.UnitConversion;
import app.web.entities.Plank;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SvgCarport
{
    
    private static List< Plank > testOrder = new ArrayList<>();
    private static int id = 0;
    private UnitConversion unitConversion = new UnitConversion( 600, 780 );
    private Svg carportSvg = new Svg( 0, 0, "0 0 900 700", "100%", "auto" );
    
    private final String rectStandardStyle = "stroke: #000000; stroke-width: 1px; fill: #ffffff";
    
    private ArrayList< Plank > rafters = plankOrganiser( getTestOrder(), Plank.RAFTER );
    private ArrayList< Plank > beams = plankOrganiser( getTestOrder(), Plank.BEAM );
    private ArrayList< Plank > posts = plankOrganiser( getTestOrder(), Plank.POST );
    private double beamToPostMath;
    private double beamToPostLength;
    
    
    
    public static List< Plank > getTestOrder()
    {
        Plank beam = new Plank( id++, 25, 200, 4200, Plank.BEAM, 0 );
        beam.setAmount( 4 );
        testOrder.add( beam );
        
        Plank rafter = new Plank( id++, 45, 195, 6000, Plank.RAFTER, 0 );
        rafter.setAmount( 2 );
        testOrder.add( rafter );
        
        Plank post = new Plank( id++, 97, 97, 3000, Plank.POST, 0 );
        post.setAmount( 6 );
        testOrder.add( post );
        
        return testOrder;
    }
    
    public String drawCarport( List< Plank > Planks )
    {
        Locale.setDefault( new Locale( "US" ) );
//        outer svg setup
        carportSvg.addRectangle( 0, 0, 700, 900, "stroke: #000000; stroke-width: 1px; fill: none" );
        carportSvg.addArrow( 50, 620, 50, 20 );
        carportSvg.addArrow( 80, 650, 880, 650 );
        carportSvg.addText( 30, 300, -90, "600 cm" );
        carportSvg.addText( 470, 680, 0, "800 cm" );

//        inner svg setup
        carportSvg.addSvg( 80, 20, "0 0 800 600", "800", "600" );
        carportSvg.addRectangle( 0, 0, 600, 800, "stroke: #000000; stroke-width: 1px; fill: #ffffff" );
//        methods for drawing the actual carport
        rafterDrawer();
        beamDrawer();
        postDrawer();
        
        return carportSvg.toString();
    }
    
    public void rafterDrawer()
    {
        carportSvg.addRectangle( 0, 0, rafters.get( 0 ).getDrawHeight( unitConversion ), rafters.get( 0 ).getDrawWidth( unitConversion ), rectStandardStyle );
        carportSvg.addRectangle( 800 - ( rafters.get( 0 ).getDrawWidth( unitConversion ) ), 0, rafters.get( 0 ).getDrawHeight( unitConversion ), rafters.get( 0 ).getDrawWidth( unitConversion ), rectStandardStyle );
    }
    
    public void beamDrawer()
    {
        double totalDrawLength = beams.get( 0 ).getAmount() * beams.get( 0 ).getDrawWidth( unitConversion ); //4 is a placeholder for the amount
        System.out.println( beams.get( 0 ).getAmount() );
        System.out.println( beams.get( 0 ).getLength() );
        System.out.println( "usable length in total: " + totalDrawLength );
        System.out.println( "usable length for each side: " + totalDrawLength / 2 );
        System.out.println( "length needing to be filled for each board side: " + ( unitConversion.DRAW_WIDTH - ( rafters.get( 0 ).getDrawWidth( unitConversion ) * 2 ) ) );
        double boardDrawingFillLength = ( unitConversion.DRAW_WIDTH - ( rafters.get( 0 ).getDrawWidth( unitConversion ) * 2 ) );
        System.out.println( "extra length: " + ( ( totalDrawLength / 2 ) - boardDrawingFillLength ) );
        beamToPostMath = boardDrawingFillLength;
        beamToPostLength = beams.get( 0 ).getDrawWidth( unitConversion );
        
        if ( ( totalDrawLength / 2 ) >= ( unitConversion.DRAW_WIDTH - ( rafters.get( 0 ).getDrawWidth( unitConversion ) * 2 ) ) ) {
            carportSvg.addRectangle( rafters.get( 0 ).getDrawWidth( unitConversion ), 50, beams.get( 0 ).getDrawHeight( unitConversion ), boardDrawingFillLength, rectStandardStyle );
            carportSvg.addRectangle( rafters.get( 0 ).getDrawWidth( unitConversion ), 550, beams.get( 0 ).getDrawHeight( unitConversion ), boardDrawingFillLength, rectStandardStyle );
            System.out.println( "success for board" );
        }
        
        
    }
    
    public void postDrawer()
    {
        double postCheckerThing = beamToPostMath / beamToPostLength;
        System.out.println( postCheckerThing );
        System.out.println( postCheckerThing + 2 );
        
        carportSvg.addRectangle( 50 + rafters.get( 0 ).getDrawWidth( unitConversion ), 50 - ( 1.5 * beams.get( 0 ).getDrawHeight( unitConversion ) ), posts.get( 0 ).getDrawHeight( unitConversion ), posts.get( 0 ).getDrawWidth( unitConversion ), rectStandardStyle );
        carportSvg.addRectangle( 750 - rafters.get( 0 ).getDrawWidth( unitConversion ), 50 - ( 1.5 * beams.get( 0 ).getDrawHeight( unitConversion ) ), posts.get( 0 ).getDrawHeight( unitConversion ), posts.get( 0 ).getDrawWidth( unitConversion ), rectStandardStyle );
        carportSvg.addRectangle( 50 + rafters.get( 0 ).getDrawWidth( unitConversion ), 550 - ( 1.5 * beams.get( 0 ).getDrawHeight( unitConversion ) ), posts.get( 0 ).getDrawHeight( unitConversion ), posts.get( 0 ).getDrawWidth( unitConversion ), rectStandardStyle );
        carportSvg.addRectangle( 750 - rafters.get( 0 ).getDrawWidth( unitConversion ), 550 - ( 1.5 * beams.get( 0 ).getDrawHeight( unitConversion ) ), posts.get( 0 ).getDrawHeight( unitConversion ), posts.get( 0 ).getDrawWidth( unitConversion ), rectStandardStyle );
        
        System.out.println( "posts for each side (aside from 2 corner ones): " + ( int ) postCheckerThing );
        for ( int i = 0; i < ( int ) postCheckerThing; i++ ) {
            carportSvg.addRectangle( beamToPostLength + rafters.get( 0 ).getDrawWidth( unitConversion ), 50 - ( 1.5 * ( unitConversion.heightMmToDrawUnits( beams.get( 0 ).getHeight() ) ) ), unitConversion.heightMmToDrawUnits( posts.get( 0 ).getHeight() ), posts.get( 0 ).getDrawWidth( unitConversion ), rectStandardStyle );
            carportSvg.addRectangle( beamToPostLength + rafters.get( 0 ).getDrawWidth( unitConversion ), 550 - ( 1.5 * ( unitConversion.heightMmToDrawUnits( beams.get( 0 ).getHeight() ) ) ), unitConversion.heightMmToDrawUnits( posts.get( 0 ).getHeight() ), posts.get( 0 ).getDrawWidth( unitConversion ), rectStandardStyle );
        }
    }
    
    public ArrayList< Plank > plankOrganiser( List< Plank > planks, int type )
    {
        ArrayList< Plank > plankArrayList = new ArrayList<>();
        switch ( type ) {
            case 0: //board
                for ( Plank plank : planks ) {
                    if ( plank.getType().equals( 0 ) ) {
                        plankArrayList.add( plank );
                    }
                }
                break;
            case 1: //lath
                for ( Plank plank : planks ) {
                    if ( plank.getType().equals( 1 ) ) {
                        plankArrayList.add( plank );
                    }
                }
                break;
            case 2: //beam
                for ( Plank plank : planks ) {
                    if ( plank.getType().equals( 2 ) ) {
                        plankArrayList.add( plank );
                    }
                }
                break;
            case 3: //rafter
                for ( Plank plank : planks ) {
                    if ( plank.getType().equals( 3 ) ) {
                        plankArrayList.add( plank );
                    }
                }
                break;
            case 4: //post
                for ( Plank plank : planks ) {
                    if ( plank.getType().equals( 4 ) ) {
                        plankArrayList.add( plank );
                    }
                }
                break;
            default:
                //TODO: throw an error or something
        }
        return plankArrayList;
    }
    
}