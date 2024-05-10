package app.web.pageControllers.controllers.users.account;


import app.web.constants.routing.WebHtml;
import app.web.constants.routing.WebPages;
import app.web.pageControllers.models.users.account.ResetAccountPasswordModel;
import io.javalin.Javalin;
import io.javalin.http.Context;


public class ResetAccountPasswordController
{
    private static ResetAccountPasswordModel resetAccountPasswordModel;
    
    public static void startUp( ResetAccountPasswordModel resetAccountPasswordModel )
    {
        if ( ResetAccountPasswordController.resetAccountPasswordModel == null ) {
            ResetAccountPasswordController.resetAccountPasswordModel = resetAccountPasswordModel;
        }
    }
    public static void addRoutes( Javalin app )
    {

        app.get( WebPages.RESET_ACCOUNT_PASSWORD_GET_PAGE, ctx -> getPage( ctx ) );
        app.post( WebPages.RESET_ACCOUNT_PASSWORD_POST_PAGE, ctx -> post( ctx ) );

    }
    

    public static void render( Context ctx )
    {
        ctx.render( WebHtml.RESET_ACCOUNT_PASSWORD_HTML );
    }
    
   
    public static void redirect( Context ctx )
    {
        ctx.redirect( WebPages.RESET_ACCOUNT_PASSWORD_GET_PAGE );
    }
    
    
    private static void getPage( Context ctx )
    { //TODO
        render( ctx );
    }

    private static void post( Context ctx )
    { //TODO

    }

}
