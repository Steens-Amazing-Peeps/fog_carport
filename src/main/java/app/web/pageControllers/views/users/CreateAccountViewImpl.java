package app.web.pageControllers.views.users;

import app.web.constants.routing.WebHtml;
import app.web.constants.routing.WebPages;
import app.web.pageControllers.views.View;
import io.javalin.http.Context;

public class CreateAccountViewImpl implements View
{
    
    @Override
    public void display( Context ctx )
    {
        ctx.render( WebHtml.CREATE_ACCOUNT_HTML );
    }
    
    @Override
    public void redirect( Context ctx )
    {
        ctx.redirect( WebPages.CREATE_ACCOUNT_GET_PAGE );
    }
    
}
