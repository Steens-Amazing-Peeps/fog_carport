package app.web.pageControllers.controllers.users.buyFlow;


import app.web.constants.routing.WebHtml;
import app.web.constants.routing.WebPages;
import app.web.pageControllers.models.users.buyFlow.Carport2DrawingModel;
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
        app.post( WebPages.CARPORT_2_DRAWING_POST_PAGE, ctx -> post( ctx ) );
        
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
        render( ctx );
    }

    private static void post( Context ctx )
    { //TODO

    }

}
