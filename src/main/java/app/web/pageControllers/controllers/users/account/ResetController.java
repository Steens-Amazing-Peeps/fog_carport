package app.web.pageControllers.controllers.users.account;


import app.web.constants.routing.WebHtml;
import app.web.constants.routing.WebPages;
import app.web.pageControllers.models.users.account.ResetModel;
import io.javalin.Javalin;
import io.javalin.http.Context;


public class ResetController
{
    private static ResetModel resetModel;
    
    public static void startUp( ResetModel resetModel)
    {
        if ( ResetController.resetModel == null ) {
            ResetController.resetModel = resetModel;
        }
    }
    public static void addRoutes( Javalin app )
    {

        app.get( WebPages.RESET_GET_PAGE, ctx -> getPage( ctx ) );

    }
    

    public static void render( Context ctx )
    {
        ctx.render( WebHtml.RESET_HTML );
    }
    
   
    public static void redirect( Context ctx )
    {
        ctx.redirect( WebPages.RESET_GET_PAGE );
    }
    
    
    private static void getPage( Context ctx )
    {
        render( ctx );
    }

    private static void post( Context ctx )
    {

    }

}
