package app.web.pageControllers.views.users;

import app.web.constants.routing.WebHtml;
import app.web.constants.routing.WebPages;
import app.web.pageControllers.views.View;
import io.javalin.http.Context;

public class HistoryViewImpl implements View
{
    
    @Override
    public void display( Context ctx )
    {
        ctx.render( WebHtml.HISTORY_HTML );
    }
    
    @Override
    public void redirect( Context ctx )
    {
        ctx.redirect( WebPages.HISTORY_GET_PAGE );
    }
    
}
