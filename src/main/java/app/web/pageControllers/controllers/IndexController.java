package app.web.pageControllers.controllers;


import app.web.constants.routing.WebPages;
import app.web.pageControllers.models.IndexModel;
import app.web.pageControllers.views.View;
import io.javalin.Javalin;
import io.javalin.http.Context;


public class IndexController
{
    
    private static IndexModel indexModel;
    private static View indexView;
    
    public static void startUp( IndexModel indexModel, View indexView )
    {
        if ( IndexController.indexModel == null ) {
            IndexController.indexModel = indexModel;
        }
        
        if ( IndexController.indexView == null ) {
            IndexController.indexView = indexView;
        }
    }
    
    public static void addRoutes( Javalin app )
    {
        
        app.get( WebPages.INDEX_GET_PAGE, ctx -> getPage( ctx ) );

//        app.post( WebPages.INDEX_POST_PAGE, ctx -> indexPost( ctx ) );
    }
    
    private static void getPage( Context ctx )
    {
        indexView.display( ctx );
    }
    
    private static void post( Context ctx )
    {
    
    }
    
    

    
}
