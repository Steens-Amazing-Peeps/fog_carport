package app.web.controllers;


import app.web.constants.routing.WebHtml;
import app.web.constants.routing.WebPages;
import io.javalin.Javalin;
import io.javalin.http.Context;


public class IndexController
{
    
    public static void addRoutes( Javalin app )
    {
        
        app.get( WebPages.INDEX_GET_PAGE, ctx -> indexGet( ctx ) );
        
//        app.post( WebPages.INDEX_POST_PAGE, ctx -> indexPost( ctx ) );
    }
    
    public static void indexRedirect( Context ctx )
    
    {
        ctx.redirect( WebPages.INDEX_GET_PAGE );
    }
    
    
    public static void indexRender( Context ctx )
    {
        ctx.render( WebHtml.INDEX_HTML );
    }
    
    private static void indexGet( Context ctx )
    {
        indexRender( ctx );
    }
    
    private static void indexPost( Context ctx )
    {

    }
    
    
    
    
    
    
    
}
