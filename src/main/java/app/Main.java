package app;

import app.web.config.SessionConfig;
import app.web.config.ThymeleafConfig;
import app.web.constants.Config;
import app.web.constants.attributes.WebGlobalAttributes;

import app.web.pageControllers.controllers.IndexController;
import app.web.pageControllers.controllers.admins.EditBuildingMaterialsController;
import app.web.pageControllers.controllers.users.*;
import app.web.pageControllers.controllers.users.account.CreateAccountController;
import app.web.pageControllers.controllers.users.account.LoginController;
import app.web.pageControllers.controllers.users.account.ResetAccountPasswordController;
import app.web.pageControllers.controllers.users.buyFlow.*;
import app.web.pageControllers.models.IndexModel;
import app.web.pageControllers.models.IndexModelImpl;


import app.web.pageControllers.models.admins.EditBuildingMaterialsModel;
import app.web.pageControllers.models.admins.EditBuildingMaterialsModelImpl;
import app.web.pageControllers.models.users.account.*;
import app.web.pageControllers.models.users.buyFlow.*;
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
        
        //Header Only
        HeaderController.addRoutes( app );
        
        //Account
        CreateAccountController.addRoutes( app );
        LoginController.addRoutes( app );
        ResetAccountPasswordController.addRoutes( app );
        
        //Buy Flow
        Carport1InfoController.addRoutes( app );
        Carport2DrawingController.addRoutes( app );
        Carport3AccountInfoController.addRoutes( app );
        Carport4ReviewAndConfirmController.addRoutes( app );
        Carport5ReceiptController.addRoutes( app );
        
        CarportBillPayUpController.addRoutes( app );
        CarportOrderHistoryController.addRoutes( app );
        
        //Admins
        EditBuildingMaterialsController.addRoutes( app );

    }
    
    private static void setUpWebServer( GetConnectionIf connectionPool )
    {
        //DataStore-------------------------------------------------------------
        DataStore dataStore = new DataStoreImpl( connectionPool );
        
        //Mappers------------------------------------------------------------------------
        UserMapper userMapper = new UserMapperImpl( dataStore );
        OrderMapper orderMapper = new OrderMapperImpl( dataStore );

        CarportMapper carportMapper = new CarportMapperImpl( dataStore );
        ShedMapper shedMapper = new ShedMapperImpl( dataStore );
        RoofMapper roofMapper = new RoofMapperImpl( dataStore );
        
        BomMapper bomMapper = new BomMapperImpl( dataStore );
        
        //Load Global Attributes--------------------------------------------------------
        WebGlobalAttributes.startUp( userMapper );
        
        //Models----------------------------------------------------------------
        IndexModel indexModel = new IndexModelImpl();
        
        //Account
        CreateAccountModel createAccountModel = new CreateAccountModelImpl( userMapper );
        LoginModel loginModel = new LoginModelImpl( userMapper );
        ResetAccountPasswordModel resetAccountPasswordModel = new ResetAccountPasswordModelImpl();
        
        
        //Buy Flow
        Carport1InfoModel carport1InfoModel = new Carport1InfoModelImpl();
        Carport2DrawingModel carport2DrawingModel = new Carport2DrawingModelImpl();
        Carport3AccountInfoModel carport3AccountInfoModel = new Carport3AccountInfoModelImpl();
        Carport4ReviewAndConfirmModel carport4ReviewAndConfirmModel = new Carport4ReviewAndConfirmModelImpl();
        Carport5ReceiptModel carport5ReceiptModel = new Carport5ReceiptModelImpl();
        
        CarportBillPayUpModel carportBillPayUpModel = new CarportBillPayUpModelImpl();
        CarportOrderHistoryModel carportOrderHistoryModel = new CarportOrderHistoryModelImpl();
        
        //Admins
        EditBuildingMaterialsModel editBuildingMaterialsModel = new EditBuildingMaterialsModelImpl();
        
        //Controllers--------------------------------------
        IndexController.startUp( indexModel );
        
        //Header Only
        HeaderController.startUp();
        
        //Account
        CreateAccountController.startUp( createAccountModel );
        LoginController.startUp( loginModel );
        ResetAccountPasswordController.startUp( resetAccountPasswordModel );
        
        //Buy Flow
        Carport1InfoController.startUp( carport1InfoModel );
        Carport2DrawingController.startUp( carport2DrawingModel );
        Carport3AccountInfoController.startUp( carport3AccountInfoModel );
        Carport4ReviewAndConfirmController.startUp( carport4ReviewAndConfirmModel );
        Carport5ReceiptController.startUp( carport5ReceiptModel );
        
        CarportBillPayUpController.startUp( carportBillPayUpModel );
        CarportOrderHistoryController.startUp( carportOrderHistoryModel );
        
        //Admins
        EditBuildingMaterialsController.startUp( editBuildingMaterialsModel );
    }
    
}