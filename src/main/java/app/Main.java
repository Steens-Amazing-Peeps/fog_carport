package app;

import app.web.config.SessionConfig;
import app.web.config.ThymeleafConfig;
import app.web.constants.attributes.WebGlobalAttributes;

import app.web.pageControllers.controllers.IndexController;
import app.web.pageControllers.controllers.users.*;
import app.web.pageControllers.models.IndexModel;
import app.web.pageControllers.models.IndexModelImpl;
import app.web.pageControllers.models.users.*;
import app.web.pageControllers.views.IndexViewImpl;
import app.web.pageControllers.views.View;
import app.web.pageControllers.views.users.CarportViewImpl;
import app.web.pageControllers.views.users.CreateAccountViewImpl;
import app.web.pageControllers.views.users.HistoryViewImpl;
import app.web.pageControllers.views.users.LoginViewImpl;


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
        HeaderController.addRoutes( app );

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
        
        CarportModel carportModel = new CarportModelImpl();
        HistoryModel historyModel = new HistoryModelImpl();
        
        //Views
        View indexView = new IndexViewImpl();
        
        View loginView = new LoginViewImpl();
        View createAccountView = new CreateAccountViewImpl();
        
        View carportView = new CarportViewImpl();
        View historyView = new HistoryViewImpl();
        
        //Controllers
        IndexController.startUp( indexModel, indexView );
        
        HeaderController.startUp( indexView );
        LoginController.startUp( loginModel, loginView, indexView );
        CreateAccountController.startUp( createAccountModel, createAccountView, indexView );
        
        CarportController.startUp( carportModel, carportView, indexView );
        HistoryController.startUp( historyModel, historyView, indexView );
    }
    
}