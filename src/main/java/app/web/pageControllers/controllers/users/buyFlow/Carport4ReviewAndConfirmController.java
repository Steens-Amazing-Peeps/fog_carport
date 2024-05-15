package app.web.pageControllers.controllers.users.buyFlow;


import app.web.constants.attributes.WebSessionAttributes;
import app.web.constants.routing.WebHtml;
import app.web.constants.routing.WebPages;
import app.web.pageControllers.models.users.buyFlow.Carport4ReviewAndConfirmModel;
import io.javalin.Javalin;
import io.javalin.http.Context;


public class Carport4ReviewAndConfirmController
{
    
    private static Carport4ReviewAndConfirmModel carport4ReviewAndConfirmModel;
    
    public static void startUp( Carport4ReviewAndConfirmModel carport4ReviewAndConfirmModel )
    {
        if ( Carport4ReviewAndConfirmController.carport4ReviewAndConfirmModel == null ) {
            Carport4ReviewAndConfirmController.carport4ReviewAndConfirmModel = carport4ReviewAndConfirmModel;
        }
    }
    
    public static void addRoutes( Javalin app )
    {
        
        app.get( WebPages.CARPORT_4_REVIEW_AND_CONFIRM_GET_PAGE, ctx -> getPage( ctx ) );
        
        app.post( WebPages.CARPORT_4_REVIEW_AND_CONFIRM_BACK_POST_PAGE, ctx -> postBack( ctx ) );
        app.post( WebPages.CARPORT_4_REVIEW_AND_CONFIRM_CONFIRM_POST_PAGE, ctx -> postConfirm( ctx ) );
        
        
    }
    
    
    public static void render( Context ctx )
    {
        ctx.render( WebHtml.CARPORT_4_REVIEW_AND_CONFIRM_HTML );
    }
    
    
    public static void redirect( Context ctx )
    {
        ctx.redirect( WebPages.CARPORT_4_REVIEW_AND_CONFIRM_GET_PAGE );
    }
    
    
    private static void getPage( Context ctx )
    {//TODO
        if ( ctx.sessionAttribute( WebSessionAttributes.currentOrder ) == null ) {
            Carport1InfoController.redirect( ctx );
        }
        
        render( ctx );
    }
    
    private static void postBack( Context ctx )
    {//TODO
        Carport3AccountInfoController.redirect( ctx );
        
    }
    
    private static void postConfirm( Context ctx )
    {//TODO
        Carport5ReceiptController.redirect( ctx );
        
    }
    
}
