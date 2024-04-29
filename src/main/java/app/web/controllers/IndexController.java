package app.web.controllers;


import app.web.constants.routing.WebHtml;
import app.web.constants.routing.WebPages;
import io.javalin.Javalin;
import io.javalin.http.Context;


public class IndexController
{
    
    public static void addRoutes( Javalin app )
    {
        
        app.get( WebPages.INDEX_GET_PAGE, ctx -> getPage( ctx ) );
        
//        app.post( WebPages.INDEX_POST_PAGE, ctx -> indexPost( ctx ) );
    }
    
    public static void redirect( Context ctx )
    
    {
        ctx.redirect( WebPages.INDEX_GET_PAGE );
    }
    
    
    public static void render( Context ctx )
    {
        ctx.render( WebHtml.INDEX_HTML );
    }
    
    private static void getPage( Context ctx )
    {
        render( ctx );
    }
    
    private static void post( Context ctx )
    {

    }
    
    
    
    
    
    
    
}
