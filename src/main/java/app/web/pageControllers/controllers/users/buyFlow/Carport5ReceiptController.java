package app.web.pageControllers.controllers.users.buyFlow;


import app.web.constants.attributes.WebSessionAttributes;
import app.web.constants.routing.WebHtml;
import app.web.constants.routing.WebPages;
import app.web.entities.Order;
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
        
        app.post( WebPages.CARPORT_5_RECEIPT_BACK_POST_PAGE, ctx -> postBack( ctx ) );
        app.post( WebPages.CARPORT_5_RECEIPT_CONFIRM_POST_PAGE, ctx -> postConfirm( ctx ) );

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
        Order order = ctx.sessionAttribute( WebSessionAttributes.completedOrder );
        
        if ( order == null ) {
            Carport1InfoController.redirect( ctx );
            return;
        }
        
        if ( order.getCarport() == null ) {
            Carport1InfoController.redirect( ctx );
            return;
        }
        
        if ( order.getAccountInfo() == null ) {
            Carport1InfoController.redirect( ctx );
            return;
        }
        
        render( ctx );
    }
    
    private static void postBack( Context ctx )
    {//TODO
        
        Carport1InfoController.redirect( ctx );
        
    }
    
    private static void postConfirm( Context ctx )
    {//TODO
        Order order = ctx.sessionAttribute( WebSessionAttributes.completedOrder );
        
        if ( order == null ) {
            Carport1InfoController.redirect( ctx );
            return;
        }
        
        if ( order.getCarport() == null ) {
            Carport1InfoController.redirect( ctx );
            return;
        }
        
        if ( order.getAccountInfo() == null ) {
            Carport1InfoController.redirect( ctx );
            return;
        }
        
        CarportBillPayUpController.redirect( ctx );
        
    }
}
