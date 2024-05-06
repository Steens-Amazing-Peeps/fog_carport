package app.web.pageControllers.controllers;


import app.web.constants.routing.WebHtml;
import app.web.constants.routing.WebPages;
import app.web.pageControllers.models.IndexModel;
import io.javalin.Javalin;
import io.javalin.http.Context;


public class IndexController
{
    
    private static IndexModel indexModel;
    
    public static void startUp( IndexModel indexModel)
    {
        if ( IndexController.indexModel == null ) {
            IndexController.indexModel = indexModel;
        }
    }
    
    public static void addRoutes( Javalin app )
    {
        
        app.get( WebPages.INDEX_GET_PAGE, ctx -> getPage( ctx ) );

//        app.post( WebPages.INDEX_POST_PAGE, ctx -> indexPost( ctx ) );
    }
    
    
    public static void render( Context ctx )
    {
        ctx.render( WebHtml.INDEX_HTML );
    }
    

    public static void redirect( Context ctx )
    {
        ctx.redirect( WebPages.INDEX_GET_PAGE );
    }
    
    private static void getPage( Context ctx )
    {
        render( ctx );
    }
    
    private static void post( Context ctx )
    {
    
    }
    
    

    
}
