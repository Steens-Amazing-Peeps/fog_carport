package app.web.pageControllers.controllers.users;


import app.web.constants.routing.WebPages;
import app.web.pageControllers.controllers.IndexController;
import app.web.pageControllers.models.IndexModel;
import app.web.pageControllers.views.View;
import io.javalin.Javalin;
import io.javalin.http.Context;

public class LogoutController
{
    private static View indexView;
    
    public static void startUp(  View indexView )
    {
        if ( LogoutController.indexView == null ) {
            LogoutController.indexView = indexView;
        }
    }
    public static void addRoutes( Javalin app ){
        
        app.post( WebPages.LOGOUT_POST_PAGE, ctx -> post( ctx ) );
    }
    
    private static void post( Context ctx )
    {
        ctx.req().getSession().invalidate();
        
        indexView.redirect( ctx );
    }
}
