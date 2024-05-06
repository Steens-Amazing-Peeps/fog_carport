package app.web.pageControllers.views.users;

import app.web.constants.routing.WebHtml;
import app.web.constants.routing.WebPages;
import app.web.pageControllers.views.View;
import io.javalin.http.Context;

public class ResetViewImpl implements View
{
    
    @Override
    public void display( Context ctx )
    {
        ctx.render( WebHtml.RESET_HTML );
    }
    
    @Override
    public void redirect( Context ctx )
    {
        ctx.redirect( WebPages.RESET_GET_PAGE );
    }
    
}
