package app.web.pageControllers.controllers.users.buyFlow;


import app.util.MetricConversion;
import app.web.constants.Config;
import app.web.constants.attributes.WebAttributes;
import app.web.constants.attributes.WebGlobalAttributes;
import app.web.constants.attributes.WebSessionAttributes;
import app.web.constants.postRequest.WebFormParam;
import app.web.constants.routing.WebHtml;
import app.web.constants.routing.WebPages;
import app.web.entities.Carport;
import app.web.entities.Order;
import app.web.exceptions.*;
import app.web.pageControllers.controllers.IndexController;
import app.web.pageControllers.models.users.buyFlow.Carport1InfoModel;
import app.web.pageControllers.models.users.buyFlow.Carport2DrawingModel;
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
        Order order = ctx.sessionAttribute( WebSessionAttributes.currentOrder );
        
        if ( order == null ) {
            order = new Order();
        }
        
        if ( order.getCarport() == null ) {
            order.setCarport( new Carport() );
        }
        
        ctx.sessionAttribute( WebSessionAttributes.currentOrder, order );
        
        //Should be application scope but we suck
        if ( ctx.sessionAttribute( WebGlobalAttributes.minimumHeightInM.getId() ) == null ) {
            ctx.sessionAttribute( WebGlobalAttributes.minimumHeightInM.getId(), ctx.appData( WebGlobalAttributes.minimumHeightInM ) );
        }
        
        if ( ctx.sessionAttribute( WebGlobalAttributes.maximumHeightInM.getId() ) == null ) {
            ctx.sessionAttribute( WebGlobalAttributes.maximumHeightInM.getId(), ctx.appData( WebGlobalAttributes.maximumHeightInM ) );
        }
        
        
        if ( ctx.sessionAttribute( WebGlobalAttributes.minimumLengthInM.getId() ) == null ) {
            ctx.sessionAttribute( WebGlobalAttributes.minimumLengthInM.getId(), ctx.appData( WebGlobalAttributes.minimumLengthInM ) );
        }
        
        if ( ctx.sessionAttribute( WebGlobalAttributes.maximumLengthInM.getId() ) == null ) {
            ctx.sessionAttribute( WebGlobalAttributes.maximumLengthInM.getId(), ctx.appData( WebGlobalAttributes.maximumLengthInM ) );
        }
        
        
        if ( ctx.sessionAttribute( WebGlobalAttributes.minimumWidthInM.getId() ) == null ) {
            ctx.sessionAttribute( WebGlobalAttributes.minimumWidthInM.getId(), ctx.appData( WebGlobalAttributes.minimumWidthInM ) );
        }
        
        if ( ctx.sessionAttribute( WebGlobalAttributes.maximumWidthInM.getId() ) == null ) {
            ctx.sessionAttribute( WebGlobalAttributes.maximumWidthInM.getId(), ctx.appData( WebGlobalAttributes.maximumWidthInM ) );
        }
        
        render( ctx );
    }
    
    private static void postBack( Context ctx )
    {
        Order order = ctx.sessionAttribute( WebSessionAttributes.currentOrder );
        
        if ( order == null ) {
            IndexController.redirect( ctx );
            return;
        }
        
        Carport carport = order.getCarport();
        
        if ( carport == null ) {
            IndexController.redirect( ctx );
            return;
        }
        
        //Form Inputs
        String carportHeight = ctx.formParam( WebFormParam.carportHeight );
        String carportWidth = ctx.formParam( WebFormParam.carportWidth );
        String carportLength = ctx.formParam( WebFormParam.carportLength );
        String carportComment = ctx.formParam( WebFormParam.Message );
        
        Integer res;
        int minSizeInMm;
        int maxSizeInMm;
        
        //Height
        try {
            minSizeInMm = MetricConversion.mToMm( ctx.appData( WebGlobalAttributes.minimumHeightInM ) );
            maxSizeInMm = MetricConversion.mToMm( ctx.appData( WebGlobalAttributes.maximumHeightInM ) );
            res = carport1InfoModel.checkNumberAttributeValidity( carportHeight, minSizeInMm, maxSizeInMm );
            carport.setHeight( res );
        } catch ( NumberTooSmallException | EmptyInputException | NumberTooLargeException ignored ) {
            //We don't care, we are just trying to save some valid values to be nice.
        }
        
        //Length
        try {
            minSizeInMm = MetricConversion.mToMm( ctx.appData( WebGlobalAttributes.minimumLengthInM ) );
            maxSizeInMm = MetricConversion.mToMm( ctx.appData( WebGlobalAttributes.maximumLengthInM ) );
            res = carport1InfoModel.checkNumberAttributeValidity( carportLength, minSizeInMm, maxSizeInMm );
            carport.setLength( res );
        } catch ( NumberTooSmallException | EmptyInputException | NumberTooLargeException ignored ) {
            //We don't care, we are just trying to save some valid values to be nice.
        }
        
        //Width
        try {
            minSizeInMm = MetricConversion.mToMm( ctx.appData( WebGlobalAttributes.minimumWidthInM ) );
            maxSizeInMm = MetricConversion.mToMm( ctx.appData( WebGlobalAttributes.maximumWidthInM ) );
            res = carport1InfoModel.checkNumberAttributeValidity( carportWidth, minSizeInMm, maxSizeInMm );
            carport.setWidth( res );
        } catch ( NumberTooSmallException | EmptyInputException | NumberTooLargeException ignored ) {
            //We don't care, we are just trying to save some valid values to be nice.
        }
        
        //Comment
        order.setComment( carportComment );
        
        //Done
        order.setCarport( carport );
        ctx.sessionAttribute( WebSessionAttributes.currentOrder, order );
        
        //Happy Path, should always run in this case
        IndexController.redirect( ctx );
    }
    
    private static void postConfirm( Context ctx )
    {
        
        Order order = ctx.sessionAttribute( WebSessionAttributes.currentOrder );
        
        if ( order == null ) {
            order = new Order();
        }
        
        Carport carport = order.getCarport();
        
        if ( carport == null ) {
            carport = new Carport();
        }
        
        //Form Inputs
        String carportHeight = ctx.formParam( WebFormParam.carportHeight );
        String carportWidth = ctx.formParam( WebFormParam.carportWidth );
        String carportLength = ctx.formParam( WebFormParam.carportLength );
        String carportComment = ctx.formParam( WebFormParam.Message );
        
        int res;
        int maxSizeInMm;
        int minSizeInMm;
        
        
        boolean hasThrownException = false;
        StringBuilder stringBuilderExceptionMessage = new StringBuilder();
        String errorMsg;
        int errorCounter = 0;
        
        //Height
        try {
            minSizeInMm = MetricConversion.mToMm( ctx.appData( WebGlobalAttributes.minimumHeightInM ) );
            maxSizeInMm = MetricConversion.mToMm( ctx.appData( WebGlobalAttributes.maximumHeightInM ) );
            res = carport1InfoModel.checkNumberAttributeValidity( carportHeight, minSizeInMm, maxSizeInMm );
            carport.setHeight( res );
        } catch ( NumberTooSmallException e ) {
            errorMsg = "Højden er for lav, minimum højden er " + ( WebGlobalAttributes.MINIMUM_HEIGHT_IN_M ) + " m";
            
            hasThrownException = true;
            errorCounter++;
            stringBuilderExceptionMessage.append( errorCounter ).append( ". " ).append( errorMsg ).append( System.lineSeparator() );
            
            
            ctx.attribute( WebAttributes.msgCarportHeight, errorMsg );
            
        } catch ( NumberTooLargeException e ) {
            errorMsg = "Højden er for høj, maximum højden er " + ( WebGlobalAttributes.MAXIMUM_HEIGHT_IN_M ) + " m";
            
            hasThrownException = true;
            errorCounter++;
            stringBuilderExceptionMessage.append( errorCounter ).append( ". " ).append( errorMsg ).append( System.lineSeparator() );
            
            
            ctx.attribute( WebAttributes.msgCarportHeight, errorMsg );
            
        } catch ( EmptyInputException e ) {
            errorMsg = "Højden var ikke indtastet";
            
            hasThrownException = true;
            errorCounter++;
            stringBuilderExceptionMessage.append( errorCounter ).append( ". " ).append( errorMsg ).append( System.lineSeparator() );
            
            
            ctx.attribute( WebAttributes.msgCarportHeight, errorMsg );
            
        } catch ( RuntimeException e ) {
            errorMsg = "Højden var ikke gyldig";
            
            hasThrownException = true;
            errorCounter++;
            stringBuilderExceptionMessage.append( errorCounter ).append( ". " ).append( errorMsg ).append( System.lineSeparator() );
            
            
            ctx.attribute( WebAttributes.msgCarportHeight, errorMsg );
        }
        
        
        //Length
        try {
            minSizeInMm = MetricConversion.mToMm( ctx.appData( WebGlobalAttributes.minimumLengthInM ) );
            maxSizeInMm = MetricConversion.mToMm( ctx.appData( WebGlobalAttributes.maximumLengthInM ) );
            res = carport1InfoModel.checkNumberAttributeValidity( carportLength, minSizeInMm, maxSizeInMm );
            carport.setLength( res );
        } catch ( NumberTooSmallException e ) {
            errorMsg = "Længden er for kort, minimum længden er " + ( WebGlobalAttributes.MINIMUM_LENGTH_IN_M ) + " m";
            
            hasThrownException = true;
            errorCounter++;
            stringBuilderExceptionMessage.append( errorCounter ).append( ". " ).append( errorMsg ).append( System.lineSeparator() );
            
            ctx.attribute( WebAttributes.msgCarportLength, errorMsg );
            
        } catch ( NumberTooLargeException e ) {
            errorMsg = "Længden er for lang, maximum længden er " + ( WebGlobalAttributes.MAXIMUM_LENGTH_IN_M ) + " m";
            
            hasThrownException = true;
            errorCounter++;
            stringBuilderExceptionMessage.append( errorCounter ).append( ". " ).append( errorMsg ).append( System.lineSeparator() );
            
            
            ctx.attribute( WebAttributes.msgCarportLength, errorMsg );
            
        } catch ( EmptyInputException e ) {
            errorMsg = "Længden var ikke indtastet";
            
            hasThrownException = true;
            errorCounter++;
            stringBuilderExceptionMessage.append( errorCounter ).append( ". " ).append( errorMsg ).append( System.lineSeparator() );
            
            
            ctx.attribute( WebAttributes.msgCarportLength, errorMsg );
            
        } catch ( RuntimeException e ) {
            errorMsg = "Længden var ikke gyldig";
            
            hasThrownException = true;
            errorCounter++;
            stringBuilderExceptionMessage.append( errorCounter ).append( ". " ).append( errorMsg ).append( System.lineSeparator() );
            
            
            ctx.attribute( WebAttributes.msgCarportLength, errorMsg );
        }
        
        //Width
        try {
            minSizeInMm = MetricConversion.mToMm( ctx.appData( WebGlobalAttributes.minimumWidthInM ) );
            maxSizeInMm = MetricConversion.mToMm( ctx.appData( WebGlobalAttributes.maximumWidthInM ) );
            res = carport1InfoModel.checkNumberAttributeValidity( carportWidth, minSizeInMm, maxSizeInMm );
            carport.setWidth( res );
        } catch ( NumberTooSmallException e ) {
            errorMsg = "Bredden er for smal, minimum bredden er " + ( WebGlobalAttributes.MINIMUM_WIDTH_IN_M ) + " m";
            
            hasThrownException = true;
            errorCounter++;
            stringBuilderExceptionMessage.append( errorCounter ).append( ". " ).append( errorMsg ).append( System.lineSeparator() );
            
            
            ctx.attribute( WebAttributes.msgCarportWidth, errorMsg );
            
        } catch ( NumberTooLargeException e ) {
            errorMsg = "Bredden er for smal, maximum bredden er " + ( WebGlobalAttributes.MAXIMUM_WIDTH_IN_M ) + " m";
            
            hasThrownException = true;
            errorCounter++;
            stringBuilderExceptionMessage.append( errorCounter ).append( ". " ).append( errorMsg ).append( System.lineSeparator() );
            
            
            ctx.attribute( WebAttributes.msgCarportWidth, errorMsg );
            
        } catch ( EmptyInputException e ) {
            errorMsg = "Bredden var ikke indtastet";
            
            hasThrownException = true;
            errorCounter++;
            stringBuilderExceptionMessage.append( errorCounter ).append( ". " ).append( errorMsg ).append( System.lineSeparator() );
            
            
            ctx.attribute( WebAttributes.msgCarportWidth, errorMsg );
            
        } catch ( RuntimeException e ) {
            errorMsg = "Bredden var ikke gyldig";
            
            hasThrownException = true;
            errorCounter++;
            stringBuilderExceptionMessage.append( errorCounter ).append( ". " ).append( errorMsg ).append( System.lineSeparator() );
            
            
            ctx.attribute( WebAttributes.msgCarportWidth, errorMsg );
        }
        
        //Comment
        order.setComment( carportComment );
        
        //Done
        order.setCarport( carport );
        ctx.sessionAttribute( WebSessionAttributes.currentOrder, order );
        
        //Error?
        if ( hasThrownException ) {
            if ( errorCounter > 1 ) {
                stringBuilderExceptionMessage.insert( 0, System.lineSeparator() ).insert( 0, " Fejl, De er:" ).insert( 0, errorCounter );
            } else {
                stringBuilderExceptionMessage.delete( 0, 3 ).insert( 0, System.lineSeparator() ).insert( 0,"En Fejl:" );
            }
            
            ctx.attribute( WebAttributes.msg, stringBuilderExceptionMessage.toString() );
            render( ctx );
            return;
        }
        
        //Get the Bom
        try {
            order.getCarport().calcBom();
        } catch ( WebInvalidInputException e ) {
            errorMsg = e.getUserMessage();
            
            errorCounter++;
            stringBuilderExceptionMessage.append( errorCounter ).append( ". " ).append( errorMsg ).append( System.lineSeparator() );
            
            ctx.attribute( WebAttributes.msg, stringBuilderExceptionMessage.toString() );
            render( ctx );
            return;
        }
        //Get the price
        order.calcPriceSuggested();
        
        //Happy Path
        ctx.attribute( WebAttributes.msg, "" );
        Carport2DrawingController.redirect( ctx );
    }
    
}
