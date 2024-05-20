package app.web.pageControllers.controllers.users.buyFlow;


import app.web.constants.attributes.WebAttributes;
import app.web.constants.attributes.WebSessionAttributes;
import app.web.constants.routing.WebHtml;
import app.web.constants.routing.WebPages;
import app.web.entities.Order;
import app.web.entities.User;
import app.web.exceptions.DatabaseException;
import app.web.exceptions.NoIdKeyReturnedException;
import app.web.exceptions.UnexpectedResultDbException;
import app.web.pageControllers.models.users.buyFlow.CarportBillPayUpModel;
import io.javalin.Javalin;
import io.javalin.http.Context;


public class CarportBillPayUpController
{
    private static CarportBillPayUpModel carportBillPayUpModel;
    
    public static void startUp( CarportBillPayUpModel carportBillPayUpModel )
    {
        if ( CarportBillPayUpController.carportBillPayUpModel == null ) {
            CarportBillPayUpController.carportBillPayUpModel = carportBillPayUpModel;
        }
    }
    public static void addRoutes( Javalin app )
    {

        app.get( WebPages.CARPORT_BILL_PAY_UP_GET_PAGE, ctx -> getPage( ctx ) );
        
        app.post( WebPages.CARPORT_BILL_PAY_UP_BACK_POST_PAGE, ctx -> postBack( ctx ) );
        app.post( WebPages.CARPORT_BILL_PAY_UP_CONFIRM_POST_PAGE, ctx -> postConfirm( ctx ) );

    }
    
    
    public static void render( Context ctx )
    {
        ctx.render( WebHtml.CARPORT_BILL_PAY_UP_HTML );
    }
    
   
    public static void redirect( Context ctx )
    {
        ctx.redirect( WebPages.CARPORT_BILL_PAY_UP_GET_PAGE );
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

        Carport5ReceiptController.redirect( ctx );
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

        try {
            carportBillPayUpModel.setOrderDone( order );

        } catch (NoIdKeyReturnedException | UnexpectedResultDbException | DatabaseException e ) {
            ctx.attribute( WebAttributes.msg, e.getUserMessage() );
            render( ctx );
            return;
        }

        CarportBillPayUpController.redirect( ctx );
    }

}
