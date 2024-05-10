package app.web.pageControllers.controllers.users.buyFlow;


import app.web.constants.routing.WebHtml;
import app.web.constants.routing.WebPages;
import app.web.pageControllers.controllers.IndexController;
import app.web.pageControllers.models.users.buyFlow.Carport1InfoModel;
import app.web.services.SvgCarport;
import io.javalin.Javalin;
import io.javalin.http.Context;


public class Carport1InfoController
{
    private static Carport1InfoModel carport1InfoModel;
    
    public static void startUp( Carport1InfoModel carport1InfoModel )
    {
        if ( Carport1InfoController.carport1InfoModel == null ) {
            Carport1InfoController.carport1InfoModel = carport1InfoModel;
        }
    }
    public static void addRoutes( Javalin app )
    {

        app.get( WebPages.CARPORT_1_INFO_GET_PAGE, ctx -> getPage( ctx ) );
        
        app.post( WebPages.CARPORT_1_INFO_BACK_POST_PAGE, ctx -> postBack( ctx ) );
        app.post( WebPages.CARPORT_1_INFO_CONFIRM_POST_PAGE, ctx -> postConfirm( ctx ) );
    }
    
   
    public static void render( Context ctx )
    {
        ctx.render( WebHtml.CARPORT_1_INFO_HTML );
    }
    
    
    public static void redirect( Context ctx )
    {
        ctx.redirect( WebPages.CARPORT_1_INFO_GET_PAGE );
    }
    
    
    private static void getPage( Context ctx )
    { //TODO:
        render( ctx );
    }

    private static void postBack( Context ctx )
    {//TODO
        IndexController.redirect( ctx );

    }
    
    private static void postConfirm( Context ctx )
    {//TODO
        Carport2DrawingController.redirect( ctx );
        
    }

}
