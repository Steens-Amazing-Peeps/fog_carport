package app.web.controllers.users;


import app.web.constants.routing.WebPages;
import app.web.controllers.IndexController;
import io.javalin.Javalin;
import io.javalin.http.Context;

public class LogoutController
{
    public static void addRoutes( Javalin app ){
        
        app.post( WebPages.LOGOUT_POST_PAGE, ctx -> logoutPost( ctx ) );
    }
    
    private static void logoutPost( Context ctx )
    {
        ctx.req().getSession().invalidate();
        
        IndexController.indexRedirect( ctx );
    }
}
