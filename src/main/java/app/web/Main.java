package app.web;

import app.web.config.SessionConfig;
import app.web.config.ThymeleafConfig;
import app.web.constants.attributes.WebGlobalAttributes;
import app.web.controllers.IndexController;
import app.web.controllers.users.CreateAccountController;
import app.web.controllers.users.LoginController;
import app.web.controllers.users.LogoutController;
import app.web.persistence.ConnectionPool;
import app.web.persistence.mappers.TemplateMappersStartUp;
import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinThymeleaf;

public class Main
{
    private static final int PORT = 7072;
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    
    public static void main( String[] args )
    {
        // Initializing Javalin and Jetty webserver
        
        Javalin app = Javalin.create( config -> {
            config.staticFiles.add( "/web/public" );
            config.jetty.modifyServletContextHandler( handler -> handler.setSessionHandler( SessionConfig.sessionConfig() ) );
            config.fileRenderer( new JavalinThymeleaf( ThymeleafConfig.templateEngine() ) );
            
            TemplateMappersStartUp.startUpSetConnectionPoolAccess( CONNECTION_POOL );
            WebGlobalAttributes.startUp();
        } ).start( PORT );
        
        // Routing
        
        IndexController.addRoutes( app );
        
        CreateAccountController.addRoutes( app );
        LoginController.addRoutes( app );
        LogoutController.addRoutes( app );
    }
    
}