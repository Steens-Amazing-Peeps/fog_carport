package app.web.pageControllers.views;

import app.web.constants.routing.WebHtml;
import app.web.constants.routing.WebPages;
import io.javalin.http.Context;

public class IndexViewImpl implements View
{
    
    @Override
    public void display( Context ctx )
    {
        ctx.render( WebHtml.INDEX_HTML );
    }
    
    @Override
    public void redirect( Context ctx )
    {
        ctx.redirect( WebPages.INDEX_GET_PAGE );
    }
    
}
