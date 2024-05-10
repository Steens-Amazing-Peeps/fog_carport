package app.web.pageControllers.controllers.users.buyFlow;


import app.web.constants.routing.WebHtml;
import app.web.constants.routing.WebPages;
import app.web.pageControllers.models.users.buyFlow.CarportOrderHistoryModel;
import io.javalin.Javalin;
import io.javalin.http.Context;


public class CarportOrderHistoryController
{
    
    private static CarportOrderHistoryModel carportOrderHistoryModel;
    
    public static void startUp( CarportOrderHistoryModel carportOrderHistoryModel )
    {
        if ( CarportOrderHistoryController.carportOrderHistoryModel == null ) {
            CarportOrderHistoryController.carportOrderHistoryModel = carportOrderHistoryModel;
        }
    }

    public static void addRoutes( Javalin app )
    {

        app.get( WebPages.CARPORT_ORDER_HISTORY_GET_PAGE, ctx -> getPage( ctx ) );
//        app.post( WebPages.CARPORT_ORDER_HISTORY_POST_PAGE, ctx -> post( ctx ) );

    
    }
    
    
    public static void render( Context ctx )
    {
        ctx.render( WebHtml.CARPORT_ORDER_HISTORY_HTML );
    }
    
    
    public static void redirect( Context ctx )
    {
        ctx.redirect( WebPages.CARPORT_ORDER_HISTORY_GET_PAGE );
    }
    
    
    private static void getPage( Context ctx )
    { //TODO
        render( ctx );
    }

    private static void post( Context ctx )
    { //TODO

    }

}
