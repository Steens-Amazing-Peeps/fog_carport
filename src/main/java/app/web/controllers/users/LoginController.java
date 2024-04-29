package app.web.controllers.users;



import app.web.constants.attributes.WebAttributes;
import app.web.constants.attributes.WebSessionAttributes;
import app.web.constants.postRequest.WebFormParam;
import app.web.constants.routing.WebHtml;
import app.web.constants.routing.WebPages;
import app.web.controllers.IndexController;
import app.web.entities.User;
import app.web.exceptions.DatabaseException;
import app.web.exceptions.UnexpectedResultDbException;
import app.web.exceptions.WebInvalidInputException;
import app.web.services.users.LoginService;
import io.javalin.Javalin;
import io.javalin.http.Context;

public class LoginController
{
    
    public static void addRoutes( Javalin app ){
        
        app.get( WebPages.LOGIN_GET_PAGE, ctx -> loginGet( ctx ) );

        app.post( WebPages.LOGIN_POST_PAGE, ctx -> loginPost( ctx ) );
    }
    
    public static void loginRedirect( Context ctx )
    {
        ctx.redirect( WebPages.LOGIN_GET_PAGE );
    }
    
    public static void loginRender( Context ctx )
    {
        ctx.render( WebHtml.LOGIN_HTML );
    }
    
    private static void loginGet( Context ctx )
    {
        loginRender( ctx );
    }
    
    private static void loginPost( Context ctx )
    {
        String email;
        String password;
        
        email = ctx.formParam( WebFormParam.email );
        password = ctx.formParam(WebFormParam.password);
        
        try {
            User user = LoginService.login( email, password );
            ctx.sessionAttribute( WebSessionAttributes.currentUser, user );
            ctx.attribute( WebAttributes.msg, "" );
            
        } catch ( WebInvalidInputException | UnexpectedResultDbException | DatabaseException e ) {
            
            ctx.attribute( WebAttributes.msg, e.getUserMessage() );
            loginRender( ctx );
            return;
            
        }
        
        
        IndexController.indexRedirect( ctx );
    }
    
}
