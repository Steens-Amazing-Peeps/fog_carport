package app.web.pageControllers.views.users;

import app.web.constants.routing.WebHtml;
import app.web.constants.routing.WebPages;
import app.web.pageControllers.views.View;
import io.javalin.http.Context;

public class LoginViewImpl implements View
{
    
    
    @Override
    public void display( Context ctx )
    {
        ctx.render( WebHtml.LOGIN_HTML );
    }
    
    @Override
    public void redirect( Context ctx )
    {
        ctx.redirect( WebPages.LOGIN_GET_PAGE );
    }
    
}
