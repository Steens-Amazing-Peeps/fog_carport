package app.web.pageControllers.controllers.users;



import app.web.constants.attributes.WebAttributes;
import app.web.constants.attributes.WebSessionAttributes;
import app.web.constants.postRequest.WebFormParam;
import app.web.constants.routing.WebPages;
import app.web.pageControllers.controllers.IndexController;
import app.web.entities.User;
import app.web.exceptions.DatabaseException;
import app.web.exceptions.NoIdKeyReturnedException;
import app.web.exceptions.UnexpectedResultDbException;
import app.web.exceptions.WebInvalidInputException;
import app.web.pageControllers.models.IndexModel;
import app.web.pageControllers.models.users.CreateAccountModel;
import app.web.pageControllers.models.users.LoginModel;
import app.web.pageControllers.views.View;
import io.javalin.Javalin;
import io.javalin.http.Context;

public class CreateAccountController
{
    
    private static CreateAccountModel createAccountModel;
    private static View createAccountView;
    private static View indexView;
    
    
    public static void startUp( CreateAccountModel createAccountModel, View createAccountView, View indexView )
    {
        if ( CreateAccountController.createAccountModel == null ) {
            CreateAccountController.createAccountModel = createAccountModel;
        }
        
        if ( CreateAccountController.createAccountView == null ) {
            CreateAccountController.createAccountView = createAccountView;
        }
        
        if ( CreateAccountController.indexView == null ) {
            CreateAccountController.indexView = indexView;
        }
    }
    
    public static void addRoutes( Javalin app )
    {
        
        app.get( WebPages.CREATE_ACCOUNT_GET_PAGE, ctx -> getPage( ctx ) );
        
        app.post( WebPages.CREATE_ACCOUNT_POST_PAGE, ctx -> post( ctx ) );
    }
    
    private static void getPage( Context ctx )
    {
        createAccountView.display( ctx );
    }
    
    private static void post( Context ctx )
    {
        String email;
        String password;
        String passwordAgain;
        
        email = ctx.formParam( WebFormParam.email );
        password = ctx.formParam( WebFormParam.password );
        passwordAgain = ctx.formParam( WebFormParam.repeatPassword );
        
        try {
            User user = createAccountModel.createAccount( email, password, passwordAgain );
            ctx.sessionAttribute( WebSessionAttributes.currentUser, user );
            ctx.attribute( WebAttributes.msg, "" );
            
        } catch ( WebInvalidInputException | NoIdKeyReturnedException | UnexpectedResultDbException |
                  DatabaseException e ) {
            
            createAccountView.displayCommonError( ctx, e );
            return;
        }
        
        
        indexView.redirect( ctx );
    }
    
    
    
}
