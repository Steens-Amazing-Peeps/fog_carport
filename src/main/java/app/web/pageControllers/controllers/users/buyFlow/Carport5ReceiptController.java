package app.web.pageControllers.controllers.users.buyFlow;


import app.web.constants.routing.WebHtml;
import app.web.constants.routing.WebPages;
import app.web.pageControllers.models.users.buyFlow.Carport5ReceiptModel;
import io.javalin.Javalin;
import io.javalin.http.Context;


public class Carport5ReceiptController
{
    private static Carport5ReceiptModel carport5ReceiptModel;
    
    public static void startUp( Carport5ReceiptModel carport5ReceiptModel )
    {
        if ( Carport5ReceiptController.carport5ReceiptModel == null ) {
            Carport5ReceiptController.carport5ReceiptModel = carport5ReceiptModel;
        }
    }
    public static void addRoutes( Javalin app )
    {

        app.get( WebPages.CARPORT_5_RECEIPT_GET_PAGE, ctx -> getPage( ctx ) );
        app.post( WebPages.CARPORT_5_RECEIPT_POST_PAGE, ctx -> post( ctx ) );

    }
    
    
    public static void render( Context ctx )
    {
        ctx.render( WebHtml.CARPORT_5_RECEIPT_HTML );
    }
    
   
    public static void redirect( Context ctx )
    {
        ctx.redirect( WebPages.CARPORT_5_RECEIPT_GET_PAGE );
    }
    
    
    private static void getPage( Context ctx )
    {//TODO
        render( ctx );
    }

    private static void post( Context ctx )
    {//TODO

    }

}
