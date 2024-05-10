package app.web.pageControllers.controllers.users.buyFlow;


import app.web.constants.routing.WebHtml;
import app.web.constants.routing.WebPages;
import app.web.pageControllers.controllers.IndexController;
import app.web.pageControllers.models.users.buyFlow.Carport2DrawingModel;
import app.web.services.SvgCarport;
import io.javalin.Javalin;
import io.javalin.http.Context;


public class Carport2DrawingController
{
    private static Carport2DrawingModel carport2DrawingModel;
    
    public static void startUp( Carport2DrawingModel carport2DrawingModel )
    {
        if ( Carport2DrawingController.carport2DrawingModel == null ) {
            Carport2DrawingController.carport2DrawingModel = carport2DrawingModel;
        }
    }
    public static void addRoutes( Javalin app )
    {

        app.get( WebPages.CARPORT_2_DRAWING_GET_PAGE, ctx -> getPage( ctx ) );
        
        app.post( WebPages.CARPORT_2_DRAWING_BACK_POST_PAGE, ctx -> postBack( ctx ) );
        app.post( WebPages.CARPORT_2_DRAWING_CONFIRM_POST_PAGE, ctx -> postConfirm( ctx ) );
        
    }
    
    
    public static void render( Context ctx )
    {
        ctx.render( WebHtml.CARPORT_2_DRAWING_HTML );
    }
    
   
    public static void redirect( Context ctx )
    {
        ctx.redirect( WebPages.CARPORT_2_DRAWING_GET_PAGE );
    }
    
    
    private static void getPage( Context ctx )
    { //TODO

//        Locale.setDefault(new Locale("US"));
////        outer svg setup
//        Svg carportSvg = new Svg(0,0,"0 0 900 700", "100%","auto");
//        carportSvg.addRectangle(0,0,700,900,"stroke: #000000; stroke-width: 1px; fill: none");
//        carportSvg.addArrow(50, 620,50,20);
//        carportSvg.addArrow(80, 650,880,650);
//        carportSvg.addText(30,300,-90,"600 cm");
//        carportSvg.addText(470,680,0,"800 cm");
//
////        inner svg setup
//        carportSvg.addSvg(80,20,"0 0 800 600", "800", "600");
//        carportSvg.addRectangle(0,0,600,800,"stroke: #000000; stroke-width: 1px; fill: #ffffff");
//
//        ctx.attribute("svg", carportSvg.toString());
        
        SvgCarport svgCarport = new SvgCarport();
        
        ctx.attribute("svg", svgCarport.drawCarport(SvgCarport.getTestOrder()));
        
        render( ctx );
    }
    
    private static void postBack( Context ctx )
    {//TODO
        Carport1InfoController.redirect( ctx );
        
    }
    
    private static void postConfirm( Context ctx )
    {//TODO
        Carport3AccountInfoController.redirect( ctx );
        
    }
    

}
