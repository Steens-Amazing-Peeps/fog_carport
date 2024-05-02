package app.web.controllers.users;


import app.web.constants.routing.WebHtml;
import app.web.constants.routing.WebPages;
import io.javalin.Javalin;
import io.javalin.http.Context;


public class CarportController
{

    public static void addRoutes( Javalin app )
    {

        app.get( WebPages.CARPORT_GET_PAGE, ctx -> getPage( ctx ) );

    }

    public static void redirect( Context ctx )

    {
        ctx.redirect( WebPages.CARPORT_GET_PAGE );
    }


    public static void render( Context ctx )
    {
        ctx.render( WebHtml.CARPORT_HTML );
    }

    private static void getPage( Context ctx )
    {
        render( ctx );
    }

    private static void post( Context ctx )
    {

    }

}
