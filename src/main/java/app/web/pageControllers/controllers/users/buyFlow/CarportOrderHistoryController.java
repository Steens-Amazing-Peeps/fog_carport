package app.web.pageControllers.controllers.users.buyFlow;


import app.web.constants.Config;
import app.web.constants.attributes.WebAttributes;
import app.web.constants.attributes.WebSessionAttributes;
import app.web.constants.postRequest.WebFormParam;
import app.web.constants.routing.WebHtml;
import app.web.constants.routing.WebPages;
import app.web.entities.FullHistory;
import app.web.entities.User;
import app.web.exceptions.DatabaseException;
import app.web.exceptions.UnexpectedResultDbException;
import app.web.pageControllers.controllers.admins.buyFlow.CarportAdminOrderHistoryController;
import app.web.pageControllers.controllers.users.account.LoginController;
import app.web.pageControllers.models.users.buyFlow.CarportOrderHistoryModel;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.Map;
import java.util.Objects;


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
        
        app.post( WebPages.CARPORT_ORDER_HISTORY_POST_PAGE, ctx -> post( ctx ) );
        
        
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
    {
        User user = ctx.sessionAttribute( WebSessionAttributes.currentUser );
        
        if ( user == null ) {
            LoginController.redirect( ctx );
            return;
        }
        
        if ( Objects.equals( user.getRole(), Config.User.ADMIN_ROLE ) ) {
            CarportAdminOrderHistoryController.redirect( ctx );
            return;
        }
        
        
        try {
            FullHistory fullHistory = carportOrderHistoryModel.getFullHistory( user );
            ctx.sessionAttribute( WebSessionAttributes.fullHistory, fullHistory );
       
        } catch ( DatabaseException e ) {
            ctx.attribute( WebAttributes.msg, e.getUserMessage() );
            render( ctx );
            return;
        }
        
        
        
        render( ctx );
    }
    
    private static void post( Context ctx )
    {
        User user = ctx.sessionAttribute( WebSessionAttributes.currentUser );
        
        if ( user == null ) {
            LoginController.redirect( ctx );
            return;
        }
        
        if ( Objects.equals( user.getRole(), Config.User.ADMIN_ROLE ) ) {
            CarportAdminOrderHistoryController.redirect( ctx );
            return;
        }
        
        String doneOrderIdAsString;
        
        doneOrderIdAsString = ctx.formParam( WebFormParam.orderId );
        
        try {
            FullHistory updatedFullHistory = carportOrderHistoryModel.finishTemp( doneOrderIdAsString, user );
            ctx.sessionAttribute( WebSessionAttributes.fullHistory, updatedFullHistory );
            ctx.attribute( WebAttributes.msg, "" );
            
        } catch ( UnexpectedResultDbException | DatabaseException e ) {
            
            ctx.attribute( WebAttributes.msg, e.getUserMessage() );
            render( ctx );
            return;
            
        }
        
        render( ctx );
    
    }
    
}
