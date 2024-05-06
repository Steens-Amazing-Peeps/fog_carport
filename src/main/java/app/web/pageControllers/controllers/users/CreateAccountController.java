package app.web.pageControllers.controllers.users;



import app.web.constants.attributes.WebAttributes;
import app.web.constants.attributes.WebGlobalAttributes;
import app.web.constants.attributes.WebSessionAttributes;
import app.web.constants.postRequest.WebFormParam;
import app.web.constants.routing.WebHtml;
import app.web.constants.routing.WebPages;
import app.web.pageControllers.controllers.IndexController;
import app.web.entities.User;
import app.web.exceptions.DatabaseException;
import app.web.exceptions.NoIdKeyReturnedException;
import app.web.exceptions.UnexpectedResultDbException;
import app.web.exceptions.WebInvalidInputException;
import app.web.pageControllers.models.users.CreateAccountModel;
import io.javalin.Javalin;
import io.javalin.http.Context;

public class CreateAccountController
{
    
    private static CreateAccountModel createAccountModel;
    
    
    public static void startUp( CreateAccountModel createAccountModel )
    {
        if ( CreateAccountController.createAccountModel == null ) {
            CreateAccountController.createAccountModel = createAccountModel;
        }
    }
    
    public static void addRoutes( Javalin app )
    {
        
        app.get( WebPages.CREATE_ACCOUNT_GET_PAGE, ctx -> getPage( ctx ) );
        
        app.post( WebPages.CREATE_ACCOUNT_POST_PAGE, ctx -> post( ctx ) );
    }
    
    
    public static void render( Context ctx )
    {
        ctx.render( WebHtml.CREATE_ACCOUNT_HTML );
    }
    
    
    public static void redirect( Context ctx )
    {
        ctx.redirect( WebPages.CREATE_ACCOUNT_GET_PAGE );
    }
    
    private static void getPage( Context ctx )
    {
        render( ctx );
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
            User user = createAccountModel.createAccount( email, password, passwordAgain, WebGlobalAttributes.USER_MAP );
            ctx.sessionAttribute( WebSessionAttributes.currentUser, user );
            ctx.attribute( WebAttributes.msg, "" );
            
        } catch ( WebInvalidInputException | NoIdKeyReturnedException | UnexpectedResultDbException |
                  DatabaseException e ) {
            
            ctx.attribute( WebAttributes.msg, e.getUserMessage() );
            render( ctx );
            return;
        }
        
        
        IndexController.redirect( ctx );
    }
    
    
    
}
