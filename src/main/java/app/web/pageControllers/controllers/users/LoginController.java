package app.web.pageControllers.controllers.users;



import app.web.constants.attributes.WebAttributes;
import app.web.constants.attributes.WebSessionAttributes;
import app.web.constants.postRequest.WebFormParam;
import app.web.constants.routing.WebPages;
import app.web.pageControllers.controllers.IndexController;
import app.web.entities.User;
import app.web.exceptions.DatabaseException;
import app.web.exceptions.UnexpectedResultDbException;
import app.web.exceptions.WebInvalidInputException;
import app.web.pageControllers.models.IndexModel;
import app.web.pageControllers.models.users.LoginModel;
import app.web.pageControllers.views.View;
import io.javalin.Javalin;
import io.javalin.http.Context;

public class LoginController
{
    
    private static LoginModel loginModel;
    private static View loginView;
    private static View indexView;
    
    public static void startUp( LoginModel loginModel, View loginView, View indexView )
    {
        if ( LoginController.loginModel == null ) {
            LoginController.loginModel = loginModel;
        }
        
        if ( LoginController.loginView == null ) {
            LoginController.loginView = loginView;
        }
        
        if ( LoginController.indexView == null ) {
            LoginController.indexView = indexView;
        }
    }
    
    public static void addRoutes( Javalin app )
    {
        
        app.get( WebPages.LOGIN_GET_PAGE, ctx -> getPage( ctx ) );
        
        app.post( WebPages.LOGIN_POST_PAGE, ctx -> post( ctx ) );
    }
    
    private static void getPage( Context ctx )
    {
        loginView.display( ctx );
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
            
            loginView.displayCommonError( ctx, e );
            return;
            
        }
        
        
        indexView.redirect( ctx );
    }
    
    
    
}
