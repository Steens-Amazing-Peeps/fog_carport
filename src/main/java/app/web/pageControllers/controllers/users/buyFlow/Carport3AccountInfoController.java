package app.web.pageControllers.controllers.users.buyFlow;


import app.web.constants.attributes.WebAttributes;
import app.web.constants.attributes.WebSessionAttributes;
import app.web.constants.postRequest.WebFormParam;
import app.web.constants.routing.WebHtml;
import app.web.constants.routing.WebPages;
import app.web.entities.ContactInfo;
import app.web.entities.Order;
import app.web.exceptions.DatabaseException;
import app.web.exceptions.NoIdKeyReturnedException;
import app.web.exceptions.UnexpectedResultDbException;
import app.web.exceptions.WebInvalidInputException;
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
        if ( ctx.sessionAttribute( WebSessionAttributes.currentOrder ) == null ) {
            Carport1InfoController.redirect( ctx );
            return;
        }

        render( ctx );

    }
    
    private static void postBack( Context ctx )
    {//TODO
        Carport2DrawingController.redirect( ctx );
        
    }
    
    private static void postConfirm( Context ctx )
    {
        Order order = ctx.sessionAttribute( WebSessionAttributes.currentOrder );

        String fullName = ctx.formParam(WebFormParam.fullName);
        String address = ctx.formParam(WebFormParam.address);
        String zip = ctx.formParam(WebFormParam.zip);
        String city = ctx.formParam(WebFormParam.city);
        String phoneNumber = ctx.formParam(WebFormParam.phoneNumber);
        String email = ctx.formParam(WebFormParam.email);

        String user = ctx.sessionAttribute( WebSessionAttributes.currentUser );

        try {
            assert order != null;
            ContactInfo contactInfo = carport3AccountInfoModel.createContactInfo(order.getContactInfo(), fullName, address,
                    zip, city, phoneNumber, email, user);
            order.setContactInfo(contactInfo);

            ctx.sessionAttribute(WebSessionAttributes.currentOrder, order);
            ctx.attribute(WebAttributes.msg, "");

        } catch ( WebInvalidInputException | DatabaseException | UnexpectedResultDbException | NoIdKeyReturnedException e ) {
            ctx.attribute( WebAttributes.msg, e.getUserMessage() );
            render( ctx );

            return;
        }

        Carport4ReviewAndConfirmController.redirect( ctx );
        
    }

}
