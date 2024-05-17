package app.web.pageControllers.controllers.users.buyFlow;


import app.web.constants.attributes.WebAttributes;
import app.web.constants.attributes.WebSessionAttributes;
import app.web.constants.postRequest.WebFormParam;
import app.web.constants.routing.WebHtml;
import app.web.constants.routing.WebPages;
import app.web.entities.AccountInfo;
import app.web.entities.Order;
import app.web.entities.User;
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
    {
        Order order = ctx.sessionAttribute( WebSessionAttributes.currentOrder );
        
        if ( order == null ) {
            Carport1InfoController.redirect( ctx );
            return;
        }
        
        if ( order.getCarport() == null ) {
            Carport1InfoController.redirect( ctx );
            return;
        }
        
        User user = ctx.sessionAttribute( WebSessionAttributes.currentUser );
        carport3AccountInfoModel.getLastAccountInfo( order, user );
        
        ctx.sessionAttribute( WebSessionAttributes.currentOrder, order );
        render( ctx );
    }
    
    private static void postBack( Context ctx )
    {
        Order order = ctx.sessionAttribute( WebSessionAttributes.currentOrder );
        
        if ( order == null ) {
            Carport1InfoController.redirect( ctx );
            return;
        }
        
        if ( order.getCarport() == null ) {
            Carport1InfoController.redirect( ctx );
            return;
        }
        
        AccountInfo accountInfo = order.getAccountInfo();
        
        if ( accountInfo == null ) {
            accountInfo = new AccountInfo();
            order.setAccountInfo( accountInfo );
        }
        
        //Form Param
        String fullName = ctx.formParam( WebFormParam.fullName );
        String address = ctx.formParam( WebFormParam.address );
        String zip = ctx.formParam( WebFormParam.zip );
        String city = ctx.formParam( WebFormParam.city );
        String phoneNumber = ctx.formParam( WebFormParam.phoneNumber );
        String email = ctx.formParam( WebFormParam.email );
        
        String consentToSpam = ctx.formParam( WebFormParam.consentToSpam );
        
        try {
            carport3AccountInfoModel.setFullName( accountInfo, fullName );
        } catch ( RuntimeException | WebInvalidInputException ignored ) {
            //We don't care, we are just trying to save some valid values to be nice.
        }
        
        try {
            carport3AccountInfoModel.setAddress( accountInfo, address );
        } catch ( RuntimeException | WebInvalidInputException ignored ) {
            //We don't care, we are just trying to save some valid values to be nice.
        }
        
        try {
            carport3AccountInfoModel.setZip( accountInfo, zip );
        } catch ( RuntimeException | WebInvalidInputException ignored ) {
            //We don't care, we are just trying to save some valid values to be nice.
        }
        
        try {
            carport3AccountInfoModel.setCity( accountInfo, city );
        } catch ( RuntimeException | WebInvalidInputException ignored ) {
            //We don't care, we are just trying to save some valid values to be nice.
        }
        
        try {
            carport3AccountInfoModel.setPhoneNumber( accountInfo, phoneNumber );
        } catch ( RuntimeException | WebInvalidInputException ignored ) {
            //We don't care, we are just trying to save some valid values to be nice.
        }
        
        try {
            carport3AccountInfoModel.setEmail( accountInfo, email );
        } catch ( RuntimeException | WebInvalidInputException ignored ) {
            //We don't care, we are just trying to save some valid values to be nice.
        }
        
        try {
            carport3AccountInfoModel.setConsentToSpam( accountInfo, consentToSpam );
        } catch ( RuntimeException | WebInvalidInputException ignored ) {
            //We don't care, we are just trying to save some valid values to be nice.
        }
        
        //Done
        order.setAccountInfo( accountInfo );
        ctx.sessionAttribute( WebSessionAttributes.currentOrder, order );
        
        //Happy Path, in this case, it should always happen
        Carport2DrawingController.redirect( ctx );
    }
    
    private static void postConfirm( Context ctx )
    {
        Order order = ctx.sessionAttribute( WebSessionAttributes.currentOrder );
        
        if ( order == null ) {
            Carport1InfoController.redirect( ctx );
            return;
        }
        
        if ( order.getCarport() == null ) {
            Carport1InfoController.redirect( ctx );
            return;
        }
        
        AccountInfo accountInfo = order.getAccountInfo();
        
        if ( accountInfo == null ) {
            accountInfo = new AccountInfo();
            order.setAccountInfo( accountInfo );
            redirect( ctx );
            return;
        }
        
        //Form Param
        String fullName = ctx.formParam( WebFormParam.fullName );
        String address = ctx.formParam( WebFormParam.address );
        String zip = ctx.formParam( WebFormParam.zip );
        String city = ctx.formParam( WebFormParam.city );
        String phoneNumber = ctx.formParam( WebFormParam.phoneNumber );
        String email = ctx.formParam( WebFormParam.email );
        
        String consentToSpam = ctx.formParam( WebFormParam.consentToSpam );
        
        //Error Handling
        boolean hasThrownException = false;
        StringBuilder stringBuilderExceptionMessage = new StringBuilder();
        String errorMsg;
        int errorCounter = 0;
        
        //Process Inputs
        try {
            carport3AccountInfoModel.setFullName( accountInfo, fullName );
        } catch ( WebInvalidInputException e ) {
            errorMsg = e.getUserMessage();
            
            hasThrownException = true;
            errorCounter++;
            stringBuilderExceptionMessage.append( errorCounter ).append( " " ).append( errorMsg ).append( System.lineSeparator() );
            
            ctx.attribute( WebAttributes.msgFullName, errorMsg );
            
        } catch ( RuntimeException e ) {
            errorMsg = "En uforventet fejl skete, prøv igen eller contact adminstrator";
            
            hasThrownException = true;
            errorCounter++;
            stringBuilderExceptionMessage.append( errorCounter ).append( ". " ).append( errorMsg ).append( System.lineSeparator() );
            
            ctx.attribute( WebAttributes.msgFullName, errorMsg );
        }
        
        try {
            carport3AccountInfoModel.setAddress( accountInfo, address );
        } catch ( WebInvalidInputException e ) {
            errorMsg = e.getUserMessage();
            
            hasThrownException = true;
            errorCounter++;
            stringBuilderExceptionMessage.append( errorCounter ).append( ". " ).append( errorMsg ).append( System.lineSeparator() );
            
            ctx.attribute( WebAttributes.msgAddress, errorMsg );
            
        } catch ( RuntimeException e ) {
            errorMsg = "En uforventet fejl skete, prøv igen eller contact adminstrator";
            
            hasThrownException = true;
            errorCounter++;
            stringBuilderExceptionMessage.append( errorCounter ).append( ". " ).append( errorMsg ).append( System.lineSeparator() );
            
            ctx.attribute( WebAttributes.msgAddress, errorMsg );
        }
        
        try {
            carport3AccountInfoModel.setZip( accountInfo, zip );
        } catch ( WebInvalidInputException e ) {
            errorMsg = e.getUserMessage();
            
            hasThrownException = true;
            errorCounter++;
            stringBuilderExceptionMessage.append( errorCounter ).append( ". " ).append( errorMsg ).append( System.lineSeparator() );
            
            ctx.attribute( WebAttributes.msgZipCode, errorMsg );
            
        } catch ( RuntimeException e ) {
            errorMsg = "En uforventet fejl skete, prøv igen eller contact adminstrator";
            
            hasThrownException = true;
            errorCounter++;
            stringBuilderExceptionMessage.append( errorCounter ).append( ". " ).append( errorMsg ).append( System.lineSeparator() );
            
            ctx.attribute( WebAttributes.msgZipCode, errorMsg );
        }
        
        try {
            carport3AccountInfoModel.setCity( accountInfo, city );
        } catch ( WebInvalidInputException e ) {
            errorMsg = e.getUserMessage();
            
            hasThrownException = true;
            errorCounter++;
            stringBuilderExceptionMessage.append( errorCounter ).append( ". " ).append( errorMsg ).append( System.lineSeparator() );
            
            ctx.attribute( WebAttributes.msgCity, errorMsg );
            
        } catch ( RuntimeException e ) {
            errorMsg = "En uforventet fejl skete, prøv igen eller contact adminstrator";
            
            hasThrownException = true;
            errorCounter++;
            stringBuilderExceptionMessage.append( errorCounter ).append( ". " ).append( errorMsg ).append( System.lineSeparator() );
            
            ctx.attribute( WebAttributes.msgCity, errorMsg );
        }
        
        try {
            carport3AccountInfoModel.setPhoneNumber( accountInfo, phoneNumber );
        } catch ( WebInvalidInputException e ) {
            errorMsg = e.getUserMessage();
            
            hasThrownException = true;
            errorCounter++;
            stringBuilderExceptionMessage.append( errorCounter ).append( ". " ).append( errorMsg ).append( System.lineSeparator() );
            
            ctx.attribute( WebAttributes.msgPhone, errorMsg );
            
        } catch ( RuntimeException e ) {
            errorMsg = "En uforventet fejl skete, prøv igen eller contact adminstrator";
            
            hasThrownException = true;
            errorCounter++;
            stringBuilderExceptionMessage.append( errorCounter ).append( ". " ).append( errorMsg ).append( System.lineSeparator() );
            
            ctx.attribute( WebAttributes.msgPhone, errorMsg );
            
        }
        
        try {
            carport3AccountInfoModel.setEmail( accountInfo, email );
        } catch ( WebInvalidInputException e ) {
            errorMsg = e.getUserMessage();
            
            hasThrownException = true;
            errorCounter++;
            stringBuilderExceptionMessage.append( errorCounter ).append( ". " ).append( errorMsg ).append( System.lineSeparator() );
            
            ctx.attribute( WebAttributes.msgEmail, errorMsg );
            
        } catch ( RuntimeException e ) {
            errorMsg = "En uforventet fejl skete, prøv igen eller contact adminstrator";
            
            hasThrownException = true;
            errorCounter++;
            stringBuilderExceptionMessage.append( errorCounter ).append( ". " ).append( errorMsg ).append( System.lineSeparator() );
            
            ctx.attribute( WebAttributes.msgEmail, errorMsg );
        }
        
        try {
            carport3AccountInfoModel.setConsentToSpam( accountInfo, consentToSpam );
        } catch ( WebInvalidInputException e ) {
            errorMsg = e.getUserMessage();
            
            hasThrownException = true;
            errorCounter++;
            stringBuilderExceptionMessage.append( errorCounter ).append( ". " ).append( errorMsg ).append( System.lineSeparator() );
            
            ctx.attribute( WebAttributes.msgConsentToSpam, errorMsg );
            
        } catch ( RuntimeException e ) {
            errorMsg = "En uforventet fejl skete, prøv igen eller contact adminstrator";
            
            hasThrownException = true;
            errorCounter++;
            stringBuilderExceptionMessage.append( errorCounter ).append( ". " ).append( errorMsg ).append( System.lineSeparator() );
            
            ctx.attribute( WebAttributes.msgConsentToSpam, errorMsg );
        }
        
        
        
        //Done
        order.setAccountInfo( accountInfo );
        ctx.sessionAttribute( WebSessionAttributes.currentOrder, order );
        
        //Error?
        if ( hasThrownException ) {
            if ( errorCounter > 1 ) {
                stringBuilderExceptionMessage.insert( 0, System.lineSeparator() ).insert( 0, " Fejl, De er:" ).insert( 0, errorCounter );
            } else {
                stringBuilderExceptionMessage.delete( 0, 3 ).insert( 0, System.lineSeparator() ).insert( 0, "En Fejl:" );
                
            }
            
            ctx.attribute( WebAttributes.msg, stringBuilderExceptionMessage.toString() );
            render( ctx );
            return;
        }
        
        //Happy Path
        Carport4ReviewAndConfirmController.redirect( ctx );
    }
    
}
