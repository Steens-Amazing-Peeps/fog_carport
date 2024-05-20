package app.web.pageControllers.controllers.admins.buyFlow;

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
import app.web.exceptions.WebInvalidInputException;
import app.web.pageControllers.controllers.users.account.LoginController;
import app.web.pageControllers.controllers.users.buyFlow.CarportOrderHistoryController;
import app.web.pageControllers.models.admins.buyFlow.CarportAdminOrderHistoryModel;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.Map;
import java.util.Objects;

public class CarportAdminOrderHistoryController
{
    
    private static CarportAdminOrderHistoryModel carportAdminOrderHistoryModel;
    
    public static void startUp( CarportAdminOrderHistoryModel carportAdminOrderHistoryModel )
    {
        if ( CarportAdminOrderHistoryController.carportAdminOrderHistoryModel == null ) {
            CarportAdminOrderHistoryController.carportAdminOrderHistoryModel = carportAdminOrderHistoryModel;
        }
    }
    
    public static void addRoutes( Javalin app )
    {
        
        app.get( WebPages.CARPORT_ADMIN_ORDER_HISTORY_GET_PAGE, ctx -> getPage( ctx ) );
        
        app.post( WebPages.CARPORT_ADMIN_ORDER_HISTORY_POST_APPROVE_PAGE, ctx -> postApprove( ctx ) );
//        app.post( WebPages.CARPORT_ADMIN_ORDER_HISTORY_POST_DONE_PAGE, ctx -> post( ctx ) );
        app.post( WebPages.CARPORT_ADMIN_ORDER_HISTORY_POST_DELETE_PAGE, ctx -> postDelete( ctx ) );
    
    }
    

    
    
    public static void render( Context ctx )
    {
        ctx.render( WebHtml.CARPORT_ADMIN_ORDER_HISTORY_HTML );
    }
    
    
    public static void redirect( Context ctx )
    {
        ctx.redirect( WebPages.CARPORT_ADMIN_ORDER_HISTORY_GET_PAGE );
    }
    
    
    private static void getPage( Context ctx )
    {
        User user = ctx.sessionAttribute( WebSessionAttributes.currentUser );
        
        if ( user == null ) {
            LoginController.redirect( ctx );
            return;
        }
        
        if ( Objects.equals( user.getRole(), Config.User.USER_ROLE ) ) {
            CarportOrderHistoryController.redirect( ctx );
            return;
        }
        
        try {
            Map< Integer, FullHistory > fullHistory = carportAdminOrderHistoryModel.getFullHistory();
            ctx.sessionAttribute( WebSessionAttributes.fullHistory, fullHistory );
            
        } catch ( DatabaseException e ) {
            ctx.attribute( WebAttributes.msg, e.getUserMessage() );
            render( ctx );
            return;
        }
        
        render( ctx );
    }
    
    private static void postApprove( Context ctx )
    {
        User user = ctx.sessionAttribute( WebSessionAttributes.currentUser );
        
        if ( user == null ) {
            LoginController.redirect( ctx );
            return;
        }
        
        if ( Objects.equals( user.getRole(), Config.User.USER_ROLE ) ) {
            CarportOrderHistoryController.redirect( ctx );
            return;
        }

        String doneOrderIdAsString;
        
        doneOrderIdAsString = ctx.formParam( WebFormParam.orderId );
        
        try {
            Map<Integer, FullHistory> updatedFullHistoryMap = carportAdminOrderHistoryModel.approve( doneOrderIdAsString );
            ctx.sessionAttribute( WebSessionAttributes.fullHistory, updatedFullHistoryMap );
            ctx.attribute( WebAttributes.msg, "" );
            
        } catch ( UnexpectedResultDbException | DatabaseException e ) {
            
            ctx.attribute( WebAttributes.msg, e.getUserMessage() );
            render( ctx );
            return;
            
        }
        
        render( ctx );
    }
    
    private static void postDelete( Context ctx )
    {

    }
    

    
}
