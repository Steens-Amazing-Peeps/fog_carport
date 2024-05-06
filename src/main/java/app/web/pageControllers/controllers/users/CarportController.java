package app.web.pageControllers.controllers.users;


import app.web.constants.routing.WebHtml;
import app.web.constants.routing.WebPages;
import app.web.pageControllers.models.users.CarportModel;
import io.javalin.Javalin;
import io.javalin.http.Context;


public class CarportController
{
    private static CarportModel carportModel;
    
    public static void startUp( CarportModel carportModel )
    {
        if ( CarportController.carportModel == null ) {
            CarportController.carportModel = carportModel;
        }
    }
    public static void addRoutes( Javalin app )
    {

        app.get( WebPages.CARPORT_GET_PAGE, ctx -> getPage( ctx ) );
        app.post( WebPages.CARPORT_POST_PAGE, ctx -> post( ctx ) );

    }
    
   
    public static void render( Context ctx )
    {
        ctx.render( WebHtml.CARPORT_HTML );
    }
    
    
    public static void redirect( Context ctx )
    {
        ctx.redirect( WebPages.CARPORT_GET_PAGE );
    }
    
    
    private static void getPage( Context ctx )
    {
        render( ctx );
    }

    private static void post( Context ctx ) //TODO
    {

    }

}
