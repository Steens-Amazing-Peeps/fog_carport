package app.web.controllers.users;


import app.web.constants.routing.WebHtml;
import app.web.constants.routing.WebPages;
import io.javalin.Javalin;
import io.javalin.http.Context;


public class HistoryController
{

    public static void addRoutes( Javalin app )
    {

        app.get( WebPages.HISTORY_GET_PAGE, ctx -> getPage( ctx ) );

    }

    public static void redirect( Context ctx )

    {
        ctx.redirect( WebPages.HISTORY_GET_PAGE );
    }


    public static void render( Context ctx )
    {
        ctx.render( WebHtml.HISTORY_HTML );
    }

    private static void getPage( Context ctx )
    {
        render( ctx );
    }

    private static void post( Context ctx )
    {

    }

}
