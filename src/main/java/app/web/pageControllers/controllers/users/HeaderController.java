package app.web.pageControllers.controllers.users;


import app.web.constants.routing.WebPages;
import app.web.pageControllers.controllers.IndexController;
import io.javalin.Javalin;
import io.javalin.http.Context;

public class HeaderController
{
    
    public static void startUp()
    {
    
    }
    
    public static void addRoutes( Javalin app )
    {
        
        app.post( WebPages.LOGOUT_POST_PAGE, ctx -> post( ctx ) );
    }
    
    private static void post( Context ctx )
    {
        ctx.req().getSession().invalidate();
        
        IndexController.redirect( ctx );
    }
    
}
