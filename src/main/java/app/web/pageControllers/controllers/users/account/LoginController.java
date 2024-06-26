package app.web.pageControllers.controllers.users.account;



import app.web.constants.attributes.WebAttributes;
import app.web.constants.attributes.WebSessionAttributes;
import app.web.constants.postRequest.WebFormParam;
import app.web.constants.routing.WebHtml;
import app.web.constants.routing.WebPages;
import app.web.entities.User;
import app.web.exceptions.DatabaseException;
import app.web.exceptions.UnexpectedResultDbException;
import app.web.exceptions.WebInvalidInputException;
import app.web.pageControllers.controllers.IndexController;
import app.web.pageControllers.models.users.account.LoginModel;
import io.javalin.Javalin;
import io.javalin.http.Context;

public class LoginController
{
    
    private static LoginModel loginModel;
    
    public static void startUp( LoginModel loginModel)
    {
        if ( LoginController.loginModel == null ) {
            LoginController.loginModel = loginModel;
        }
    }
    
    public static void addRoutes( Javalin app )
    {
        
        app.get( WebPages.LOGIN_GET_PAGE, ctx -> getPage( ctx ) );
        
        app.post( WebPages.LOGIN_POST_PAGE, ctx -> post( ctx ) );
    }
    

    public static void render( Context ctx )
    {
        ctx.render( WebHtml.LOGIN_HTML );
    }
    

    public static void redirect( Context ctx )
    {
        ctx.redirect( WebPages.LOGIN_GET_PAGE );
    }
    
    private static void getPage( Context ctx )
    {
        render( ctx );
    }
    
    private static void post( Context ctx )
    {
        String email;
        String password;
        
        email = ctx.formParam( WebFormParam.email );
        password = ctx.formParam( WebFormParam.password );
        
        try {
            User user = loginModel.login( email, password );
            ctx.sessionAttribute( WebSessionAttributes.currentUser, user );
            ctx.attribute( WebAttributes.msg, "" );
            
        } catch ( WebInvalidInputException | UnexpectedResultDbException | DatabaseException e ) {
            
            ctx.attribute( WebAttributes.msg, e.getUserMessage() );
            render( ctx );
            return;
            
        }
        
        
        IndexController.redirect( ctx );
    }
    
    
    
}
