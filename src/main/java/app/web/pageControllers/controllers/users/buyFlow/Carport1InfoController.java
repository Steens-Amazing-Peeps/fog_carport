package app.web.pageControllers.controllers.users.buyFlow;


import app.web.constants.attributes.WebAttributes;
import app.web.constants.attributes.WebSessionAttributes;
import app.web.constants.postRequest.WebFormParam;
import app.web.constants.routing.WebHtml;
import app.web.constants.routing.WebPages;
import app.web.entities.Carport;
import app.web.entities.Order;
import app.web.exceptions.DatabaseException;
import app.web.exceptions.UnexpectedResultDbException;
import app.web.exceptions.WebInvalidInputException;
import app.web.exceptions.carport1Info.CarportHeightException;
import app.web.exceptions.carport1Info.CarportLengthException;
import app.web.exceptions.carport1Info.CarportWidthException;
import app.web.pageControllers.controllers.IndexController;
import app.web.pageControllers.models.users.buyFlow.Carport1InfoModel;
import io.javalin.Javalin;
import io.javalin.http.Context;


public class Carport1InfoController
{
    
    private static Carport1InfoModel carport1InfoModel;
    
    public static void startUp( Carport1InfoModel carport1InfoModel )
    {
        if ( Carport1InfoController.carport1InfoModel == null ) {
            Carport1InfoController.carport1InfoModel = carport1InfoModel;
        }
    }
    
    public static void addRoutes( Javalin app )
    {
        
        app.get( WebPages.CARPORT_1_INFO_GET_PAGE, ctx -> getPage( ctx ) );
        
        app.post( WebPages.CARPORT_1_INFO_BACK_POST_PAGE, ctx -> postBack( ctx ) );
        app.post( WebPages.CARPORT_1_INFO_CONFIRM_POST_PAGE, ctx -> postConfirm( ctx ) );
    }
    
    
    public static void render( Context ctx )
    {
        ctx.render( WebHtml.CARPORT_1_INFO_HTML );
    }
    
    
    public static void redirect( Context ctx )
    {
        ctx.redirect( WebPages.CARPORT_1_INFO_GET_PAGE );
    }
    
    
    private static void getPage( Context ctx )
    {
        render( ctx );
    }
    
    private static void postBack( Context ctx )
    {//TODO
        Order order = ctx.sessionAttribute( WebSessionAttributes.currentOrder );
        
        if ( order == null ) {
            order = new Order();
        }
        
        String carportHeight = ctx.formParam( WebFormParam.carportHeight );
        String carportWidth = ctx.formParam( WebFormParam.carportWidth );
        String carportLength = ctx.formParam( WebFormParam.carportLength );
        
//        try {
            Carport carport = carport1InfoModel.checkBackInfo( order.getCarport(), carportHeight, carportWidth, carportLength );
            order.setCarport( carport );
            ctx.sessionAttribute( WebSessionAttributes.currentOrder, order );
            ctx.attribute( WebAttributes.msg, "" );
            
//        } catch ( WebInvalidInputException e ) {
//
//            ctx.attribute( WebAttributes.msg, e.getUserMessage() );
//            render( ctx );
//            return;
//
//        }
        
        IndexController.redirect( ctx );
        
    }
    
    private static void postConfirm( Context ctx )
    {//TODO
        
        Order order = ctx.sessionAttribute( WebSessionAttributes.currentOrder );
        
        if ( order == null ) {
            order = new Order();
        }
        
        String carportHeight = ctx.formParam( WebFormParam.carportHeight );
        String carportWidth = ctx.formParam( WebFormParam.carportWidth );
        String carportLength = ctx.formParam( WebFormParam.carportLength );
        
        try {
            Carport carport = carport1InfoModel.checkConfirmInfo( order.getCarport(), carportHeight, carportWidth, carportLength );
            order.setCarport( carport );
            ctx.sessionAttribute( WebSessionAttributes.currentOrder, order );
            ctx.attribute( WebAttributes.msg, "" );
            
        } catch ( WebInvalidInputException e ) {
            
            ctx.attribute( WebAttributes.msg, e.getUserMessage() );
            render( ctx );
            return;
            
        } catch ( CarportWidthException e ) {
            
            ctx.attribute( WebAttributes.msg, e.getUserMessage() );
            render( ctx );
            return;
            
        } catch ( CarportLengthException e ) {
            
            ctx.attribute( WebAttributes.msg, e.getUserMessage() );
            render( ctx );
            return;
            
        } catch ( CarportHeightException e ) {
            
            ctx.attribute( WebAttributes.msg, e.getUserMessage() );
            render( ctx );
            return;
        }
        
        Carport2DrawingController.redirect( ctx );
    }
    
}
