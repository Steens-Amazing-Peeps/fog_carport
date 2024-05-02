package app;

import app.web.config.SessionConfig;
import app.web.config.ThymeleafConfig;
import app.web.constants.attributes.WebGlobalAttributes;

import app.web.pageControllers.controllers.IndexController;
import app.web.pageControllers.controllers.users.CreateAccountController;
import app.web.pageControllers.controllers.users.LoginController;
import app.web.pageControllers.controllers.users.LogoutController;
import app.web.pageControllers.models.IndexModel;
import app.web.pageControllers.models.IndexModelImpl;
import app.web.pageControllers.models.users.CreateAccountModel;
import app.web.pageControllers.models.users.CreateAccountModelImpl;
import app.web.pageControllers.models.users.LoginModel;
import app.web.pageControllers.models.users.LoginModelImpl;
import app.web.pageControllers.views.IndexViewImpl;
import app.web.pageControllers.views.View;
import app.web.pageControllers.views.users.CreateAccountViewImpl;
import app.web.pageControllers.views.users.LoginViewImpl;


import app.web.controllers.users.*;

import app.web.persistence.ConnectionPool;
import app.web.persistence.GetConnectionIf;
import app.web.persistence.mappers.DataStore;
import app.web.persistence.mappers.DataStoreImpl;
import app.web.persistence.mappers.UserMapper;
import app.web.persistence.mappers.UserMapperImpl;
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
            
            setUpWebServer( CONNECTION_POOL );
        } ).start( PORT );
        
        // Routing
        
        IndexController.addRoutes( app );
        
        CreateAccountController.addRoutes( app );
        LoginController.addRoutes( app );
        LogoutController.addRoutes( app );

        CarportController.addRoutes( app );
        HistoryController.addRoutes( app );
    }
    
    private static void setUpWebServer( GetConnectionIf connectionPool )
    {
        //DataStore
        DataStore dataStore = new DataStoreImpl( connectionPool );
        
        //Mappers
        UserMapper userMapper = new UserMapperImpl( dataStore );
        
        //Load Global Attributes
        WebGlobalAttributes.startUp( userMapper );
        
        //Models
        IndexModel indexModel = new IndexModelImpl();
        
        LoginModel loginModel = new LoginModelImpl( userMapper );
        CreateAccountModel createAccountModel = new CreateAccountModelImpl( userMapper );
        
        //Views
        View indexView = new IndexViewImpl();
        
        View loginView = new LoginViewImpl();
        View createAccountView = new CreateAccountViewImpl();
        
        //Controllers
        IndexController.startUp( indexModel, indexView );
        
        LogoutController.startUp( indexView );
        LoginController.startUp( loginModel, loginView, indexView );
        CreateAccountController.startUp( createAccountModel, createAccountView, indexView );
    }
    
}