package app.web.pageControllers.controllers.users.buyFlow;


import app.web.constants.routing.WebHtml;
import app.web.constants.routing.WebPages;
import app.web.pageControllers.models.users.buyFlow.ConfirmCarportModel;
import io.javalin.Javalin;
import io.javalin.http.Context;


public class ConfirmCarportController
{
    private static ConfirmCarportModel confirmCarportModel;
    
    public static void startUp( ConfirmCarportModel confirmCarportModel)
    {
        if ( ConfirmCarportController.confirmCarportModel == null ) {
            ConfirmCarportController.confirmCarportModel = confirmCarportModel;
        }
    }
    public static void addRoutes( Javalin app )
    {

        app.get( WebPages.CONFIRM_CARPORT_GET_PAGE, ctx -> getPage( ctx ) );

    }
    
    
    public static void render( Context ctx )
    {
        ctx.render( WebHtml.CONFIRM_CARPORT_HTML );
    }
    
   
    public static void redirect( Context ctx )
    {
        ctx.redirect( WebPages.CONFIRM_CARPORT_GET_PAGE );
    }
    
    
    private static void getPage( Context ctx )
    {
        render( ctx );
    }

    private static void post( Context ctx )
    {

    }

}
