package app.web.pageControllers.controllers.users.buyFlow;


import app.web.constants.routing.WebHtml;
import app.web.constants.routing.WebPages;
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
        app.post( WebPages.CARPORT_3_ACCOUNT_INFO_POST_PAGE, ctx -> post( ctx ) );
        
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
    {//TODO
        render( ctx );
    }

    private static void post( Context ctx )
    {//TODO

    }

}
