package app.web.pageControllers.controllers.users;


import app.web.constants.routing.WebPages;
import app.web.pageControllers.models.users.CarportModel;
import app.web.pageControllers.views.View;
import io.javalin.Javalin;
import io.javalin.http.Context;


public class CarportController
{
    private static CarportModel carportModel;
    private static View carportView;
    private static View indexView;
    
    public static void startUp( CarportModel carportModel, View carportView, View indexView )
    {
        if ( CarportController.carportModel == null ) {
            CarportController.carportModel = carportModel;
        }
        
        if ( CarportController.carportView == null ) {
            CarportController.carportView = carportView;
        }
        
        if ( CarportController.indexView == null ) {
            CarportController.indexView = indexView;
        }
    }
    public static void addRoutes( Javalin app )
    {

        app.get( WebPages.CARPORT_GET_PAGE, ctx -> getPage( ctx ) );

    }
    
    
    private static void getPage( Context ctx )
    {
        carportView.display( ctx );
    }

    private static void post( Context ctx )
    {

    }

}
