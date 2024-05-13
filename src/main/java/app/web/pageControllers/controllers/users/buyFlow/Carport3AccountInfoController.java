package app.web.pageControllers.controllers.users.buyFlow;


import app.web.constants.attributes.WebSessionAttributes;
import app.web.constants.routing.WebHtml;
import app.web.constants.routing.WebPages;
import app.web.entities.Order;
import app.web.pageControllers.models.users.buyFlow.Carport1InfoModelImpl;
import app.web.pageControllers.models.users.buyFlow.Carport3AccountInfoModel;
import io.javalin.Javalin;
import io.javalin.http.Context;


public class Carport3AccountInfoController
{
    private static Carport3AccountInfoModel carport3AccountInfoModel;
    
    public static void startUp( Carport3AccountInfoModel carport3AccountInfoModel )
    {
        if ( Carport3AccountInfoController.carport3AccountInfoModel == null ) {
            Carport3AccountInfoController.carport3AccountInfoModel = carport3AccountInfoModel;
        }
    }
    public static void addRoutes( Javalin app )
    {

        app.get( WebPages.CARPORT_3_ACCOUNT_INFO_GET_PAGE, ctx -> getPage( ctx ) );
        
        app.post( WebPages.CARPORT_3_ACCOUNT_INFO_BACK_POST_PAGE, ctx -> postBack( ctx ) );
        app.post( WebPages.CARPORT_3_ACCOUNT_INFO_CONFIRM_POST_PAGE, ctx -> postConfirm( ctx ) );
        
    }
    
    
    public static void render( Context ctx )
    {
        ctx.render( WebHtml.CARPORT_3_ACCOUNT_INFO_HTML );
    }
    
   
    public static void redirect( Context ctx )
    {
        ctx.redirect( WebPages.CARPORT_3_ACCOUNT_INFO_GET_PAGE );
    }
    
    
    private static void getPage( Context ctx )
    { // TODO
        Order order = ctx.sessionAttribute( WebSessionAttributes.currentOrder );

        if ( ctx.sessionAttribute( WebSessionAttributes.currentOrder ) == null ) {
            Carport1InfoController.redirect( ctx );
            return;
        }

        assert order != null;
        order.getCarport();

        render( ctx );
    }
    
    private static void postBack( Context ctx )
    {//TODO
        Carport2DrawingController.redirect( ctx );
        
    }
    
    private static void postConfirm( Context ctx )
    {//TODO
        Carport4ReviewAndConfirmController.redirect( ctx );
        
    }

}
