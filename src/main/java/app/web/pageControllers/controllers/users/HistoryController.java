package app.web.pageControllers.controllers.users;


import app.web.constants.routing.WebHtml;
import app.web.constants.routing.WebPages;
import app.web.pageControllers.models.users.HistoryModel;
import io.javalin.Javalin;
import io.javalin.http.Context;


public class HistoryController
{
    
    private static HistoryModel historyModel;
    
    public static void startUp( HistoryModel historyModel )
    {
        if ( HistoryController.historyModel == null ) {
            HistoryController.historyModel = historyModel;
        }
    }

    public static void addRoutes( Javalin app )
    {

        app.get( WebPages.HISTORY_GET_PAGE, ctx -> getPage( ctx ) );

    }
    
    
    public static void render( Context ctx )
    {
        ctx.render( WebHtml.HISTORY_HTML );
    }
    
    
    public static void redirect( Context ctx )
    {
        ctx.redirect( WebPages.HISTORY_GET_PAGE );
    }
    
    
    private static void getPage( Context ctx )
    {
        render( ctx );
    }

    private static void post( Context ctx )
    {

    }

}
