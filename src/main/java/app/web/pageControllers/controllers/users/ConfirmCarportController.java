package app.web.pageControllers.controllers.users;


import app.web.constants.routing.WebPages;
import app.web.pageControllers.models.users.ConfirmCarportModel;
import app.web.pageControllers.views.View;
import io.javalin.Javalin;
import io.javalin.http.Context;


public class ConfirmCarportController
{
    private static ConfirmCarportModel confirmCarportModel;
    private static View confirmCarportView;
    private static View indexView;
    
    public static void startUp( ConfirmCarportModel confirmCarportModel, View confirmCarportView, View indexView )
    {
        if ( ConfirmCarportController.confirmCarportModel == null ) {
            ConfirmCarportController.confirmCarportModel = confirmCarportModel;
        }
        
        if ( ConfirmCarportController.confirmCarportView == null ) {
            ConfirmCarportController.confirmCarportView = confirmCarportView;
        }
        
        if ( ConfirmCarportController.indexView == null ) {
            ConfirmCarportController.indexView = indexView;
        }
    }
    public static void addRoutes( Javalin app )
    {

        app.get( WebPages.CONFIRM_CARPORT_GET_PAGE, ctx -> getPage( ctx ) );

    }
    
    
    private static void getPage( Context ctx )
    {
        confirmCarportView.display( ctx );
    }

    private static void post( Context ctx )
    {

    }

}
