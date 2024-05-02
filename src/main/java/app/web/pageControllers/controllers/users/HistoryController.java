package app.web.pageControllers.controllers.users;


import app.web.constants.routing.WebPages;
import app.web.pageControllers.models.users.HistoryModel;
import app.web.pageControllers.views.View;
import io.javalin.Javalin;
import io.javalin.http.Context;


public class HistoryController
{
    
    private static HistoryModel historyModel;
    private static View historyView;
    private static View indexView;
    
    public static void startUp( HistoryModel historyModel, View historyView, View indexView )
    {
        if ( HistoryController.historyModel == null ) {
            HistoryController.historyModel = historyModel;
        }
        
        if ( HistoryController.historyView == null ) {
            HistoryController.historyView = historyView;
        }
        
        if ( HistoryController.indexView == null ) {
            HistoryController.indexView = indexView;
        }
    }

    public static void addRoutes( Javalin app )
    {

        app.get( WebPages.HISTORY_GET_PAGE, ctx -> getPage( ctx ) );

    }
    
    
    private static void getPage( Context ctx )
    {
        historyView.display( ctx );
    }

    private static void post( Context ctx )
    {

    }

}
