package app.web.pageControllers.controllers.users.buyFlow;


import app.web.constants.routing.WebHtml;
import app.web.constants.routing.WebPages;
import app.web.entities.Bom;
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
        Bom bom = new Bom();
        SvgCarport svgCarport = new SvgCarport(bom);
        
        ctx.attribute("svg", svgCarport.drawCarport());
        
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
