package app.web.pageControllers.controllers.users.buyFlow;


import app.web.constants.routing.WebHtml;
import app.web.constants.routing.WebPages;
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
        app.post( WebPages.CARPORT_BILL_PAY_UP_BACK_POST_PAGE, ctx -> post( ctx ) );

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
        render( ctx );
    }

    private static void post( Context ctx )
    {//TODO

    }

}
