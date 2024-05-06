package app.web.pageControllers.controllers.users;


import app.web.constants.routing.WebPages;
import app.web.pageControllers.models.users.ResetModel;
import app.web.pageControllers.views.View;
import io.javalin.Javalin;
import io.javalin.http.Context;


public class ResetController
{
    private static ResetModel resetModel;
    private static View resetView;
    private static View indexView;
    
    public static void startUp( ResetModel resetModel, View resetView, View indexView )
    {
        if ( ResetController.resetModel == null ) {
            ResetController.resetModel = resetModel;
        }
        
        if ( ResetController.resetView == null ) {
            ResetController.resetView = resetView;
        }
        
        if ( ResetController.indexView == null ) {
            ResetController.indexView = indexView;
        }
    }
    public static void addRoutes( Javalin app )
    {

        app.get( WebPages.RESET_GET_PAGE, ctx -> getPage( ctx ) );

    }
    
    
    private static void getPage( Context ctx )
    {
        resetView.display( ctx );
    }

    private static void post( Context ctx )
    {

    }

}
