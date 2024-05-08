package app;

import app.web.config.SessionConfig;
import app.web.config.ThymeleafConfig;
import app.web.constants.Config;
import app.web.constants.attributes.WebGlobalAttributes;

import app.web.pageControllers.controllers.IndexController;
import app.web.pageControllers.controllers.users.*;
import app.web.pageControllers.models.IndexModel;
import app.web.pageControllers.models.IndexModelImpl;
import app.web.pageControllers.models.users.*;


import app.web.persistence.ConnectionPool;
import app.web.persistence.GetConnectionIf;
import app.web.persistence.mappers.*;
import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinThymeleaf;

public class Main
{
    
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    
    public static void main( String[] args )
    {
        // Initializing Javalin and Jetty webserver
        
        Javalin app = Javalin.create( config -> {
            config.staticFiles.add( "/web/public" );
            config.jetty.modifyServletContextHandler( handler -> handler.setSessionHandler( SessionConfig.sessionConfig() ) );
            config.fileRenderer( new JavalinThymeleaf( ThymeleafConfig.templateEngine() ) );
            
            setUpWebServer( CONNECTION_POOL );
        } ).start( Config.General.PORT );
        
        // Routing
        
        IndexController.addRoutes( app );
        
        HeaderController.addRoutes( app );
        CreateAccountController.addRoutes( app );
        LoginController.addRoutes( app );
        ResetController.addRoutes( app );
        
        CarportController.addRoutes( app );
        ConfirmCarportController.addRoutes( app );
        
        HistoryController.addRoutes( app );
    }
    
    private static void setUpWebServer( GetConnectionIf connectionPool )
    {
        //DataStore
        DataStore dataStore = new DataStoreImpl( connectionPool );
        
        //Mappers
        UserMapper userMapper = new UserMapperImpl( dataStore );
        OrderMapper orderMapper = new OrderMapperImpl( dataStore );
        
        //Load Global Attributes
        WebGlobalAttributes.startUp( userMapper );
        
        //Models
        IndexModel indexModel = new IndexModelImpl();
        
        CreateAccountModel createAccountModel = new CreateAccountModelImpl( userMapper );
        LoginModel loginModel = new LoginModelImpl( userMapper );
        ResetModel resetModel = new ResetModelImpl();
        
        HistoryModel historyModel = new HistoryModelImpl();
        
        CarportModel carportModel = new CarportModelImpl();
        ConfirmCarportModel confirmCarportModel = new ConfirmCarportModelImpl();
        
        //Controllers
        IndexController.startUp( indexModel );
        
        HeaderController.startUp();
        CreateAccountController.startUp( createAccountModel );
        LoginController.startUp( loginModel );
        ResetController.startUp( resetModel );
        
        HistoryController.startUp( historyModel );
        
        CarportController.startUp( carportModel );
        ConfirmCarportController.startUp( confirmCarportModel );
    }
    
}