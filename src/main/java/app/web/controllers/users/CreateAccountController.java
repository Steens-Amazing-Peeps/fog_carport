package app.web.controllers.users;



import app.web.constants.attributes.WebAttributes;
import app.web.constants.attributes.WebSessionAttributes;
import app.web.constants.postRequest.WebFormParam;
import app.web.constants.routing.WebHtml;
import app.web.constants.routing.WebPages;
import app.web.controllers.IndexController;
import app.web.entities.User;
import app.web.exceptions.DatabaseException;
import app.web.exceptions.NoIdKeyReturnedException;
import app.web.exceptions.UnexpectedResultDbException;
import app.web.exceptions.WebInvalidInputException;
import app.web.services.users.CreateAccountService;
import io.javalin.Javalin;
import io.javalin.http.Context;

public class CreateAccountController
{
    
    public static void addRoutes( Javalin app )
    {
        
        app.get( WebPages.CREATE_ACCOUNT_GET_PAGE, ctx -> createAccountGet( ctx ) );

        app.post( WebPages.CREATE_ACCOUNT_POST_PAGE, ctx -> createAccountPost( ctx ) );
    }
    
    public static void createAccountRedirect( Context ctx )
    {
        ctx.redirect( WebPages.CREATE_ACCOUNT_GET_PAGE );
    }
    
    public static void createAccountRender( Context ctx )
    {
        ctx.render( WebHtml.CREATE_ACCOUNT_HTML );
    }
    
    private static void createAccountGet( Context ctx )
    {
        createAccountRender( ctx );
    }
    
    private static void createAccountPost( Context ctx )
    {
        String email;
        String password;
        String passwordAgain;
        
        email = ctx.formParam( WebFormParam.email );
        password = ctx.formParam(WebFormParam.password);
        passwordAgain = ctx.formParam(WebFormParam.repeatPassword);

        try {
            User user = CreateAccountService.createAccount( email, password, passwordAgain );
            ctx.sessionAttribute( WebSessionAttributes.currentUser, user );
            ctx.attribute( WebAttributes.msg, "" );
            
        } catch ( WebInvalidInputException | NoIdKeyReturnedException | UnexpectedResultDbException |
                  DatabaseException e ) {
            
            ctx.attribute( WebAttributes.msg, e.getUserMessage() );
            createAccountRender(ctx);
            return;
            
        }
        
        
        IndexController.indexRedirect( ctx );
    }
    
}
