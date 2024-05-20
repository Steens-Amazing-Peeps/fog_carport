package app;

import app.web.config.SessionConfig;
import app.web.config.ThymeleafConfig;
import app.web.constants.Config;
import app.web.constants.attributes.WebGlobalAttributes;

import app.web.entities.FullHistory;
import app.web.exceptions.DatabaseException;
import app.web.pageControllers.controllers.IndexController;
import app.web.pageControllers.controllers.admins.EditBuildingMaterialsController;
import app.web.pageControllers.controllers.admins.buyFlow.CarportAdminOrderHistoryController;
import app.web.pageControllers.controllers.users.*;
import app.web.pageControllers.controllers.users.account.CreateAccountController;
import app.web.pageControllers.controllers.users.account.LoginController;
import app.web.pageControllers.controllers.users.account.ResetAccountPasswordController;
import app.web.pageControllers.controllers.users.buyFlow.*;
import app.web.pageControllers.models.IndexModel;
import app.web.pageControllers.models.IndexModelImpl;


import app.web.pageControllers.models.admins.EditBuildingMaterialsModel;
import app.web.pageControllers.models.admins.EditBuildingMaterialsModelImpl;
import app.web.pageControllers.models.admins.buyFlow.CarportAdminOrderHistoryModel;
import app.web.pageControllers.models.admins.buyFlow.CarportAdminOrderHistoryModelImpl;
import app.web.pageControllers.models.users.FeedbackModel;
import app.web.pageControllers.models.users.FeedbackModelImpl;
import app.web.pageControllers.models.users.account.*;
import app.web.pageControllers.models.users.buyFlow.*;
import app.web.persistence.ConnectionPool;
import app.web.persistence.GetConnectionIf;
import app.web.persistence.mappers.*;
import io.javalin.Javalin;
import io.javalin.config.JavalinConfig;
import io.javalin.rendering.template.JavalinThymeleaf;

import java.util.List;
import java.util.Map;

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
            
            setUpWebServer( config, CONNECTION_POOL );
        } ).start( Config.General.PORT );
        
        // Routing
        
        IndexController.addRoutes( app );
        
        //Header Only
        HeaderController.addRoutes( app );
        
        //Feedback
        FeedbackController.addRoutes( app );
        
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
        
        CarportAdminOrderHistoryController.addRoutes( app );
        
    }
    
    private static void setUpWebServer( JavalinConfig config, GetConnectionIf connectionPool )
    {
        //DataStore-------------------------------------------------------------
        DataStore dataStore = new DataStoreImpl( connectionPool );
        
        //Mappers------------------------------------------------------------------------
        UserMapper userMapper = new UserMapperImpl( dataStore );
        AccountInfoMapper accountInfoMapper = new AccountInfoMapperImpl( dataStore );
        
        PlankMapper plankMapper = new PlankMapperImpl( dataStore );
        BomMapper bomMapper = new BomMapperImpl( dataStore );
        
        RoofMapper roofMapper = new RoofMapperImpl( dataStore );
        ShedMapper shedMapper = new ShedMapperImpl( dataStore );
        CarportMapper carportMapper = new CarportMapperImpl( dataStore, bomMapper );
        
        OrderMapper orderMapper = new OrderMapperImpl( dataStore, carportMapper, bomMapper, accountInfoMapper );
        
        FullHistoryMapper fullHistoryMapper = new FullHistoryMapperImpl( accountInfoMapper, orderMapper );
        
        //Load Global Attributes--------------------------------------------------------
        WebGlobalAttributes.startUp( config, userMapper, plankMapper );
        
        //Models----------------------------------------------------------------
        IndexModel indexModel = new IndexModelImpl();
        
        //Feedback
        FeedbackModel feedbackModel = new FeedbackModelImpl();
        
        //Account
        CreateAccountModel createAccountModel = new CreateAccountModelImpl( userMapper );
        LoginModel loginModel = new LoginModelImpl( userMapper );
        ResetAccountPasswordModel resetAccountPasswordModel = new ResetAccountPasswordModelImpl();
        
        
        //Buy Flow
        Carport1InfoModel carport1InfoModel = new Carport1InfoModelImpl();
        Carport2DrawingModel carport2DrawingModel = new Carport2DrawingModelImpl();
        Carport3AccountInfoModel carport3AccountInfoModel = new Carport3AccountInfoModelImpl( accountInfoMapper );
        Carport4ReviewAndConfirmModel carport4ReviewAndConfirmModel = new Carport4ReviewAndConfirmModelImpl( orderMapper );
        Carport5ReceiptModel carport5ReceiptModel = new Carport5ReceiptModelImpl();
        

        CarportBillPayUpModel carportBillPayUpModel = new CarportBillPayUpModelImpl( orderMapper );
        
        CarportOrderHistoryModel carportOrderHistoryModel = new CarportOrderHistoryModelImpl(fullHistoryMapper, orderMapper);
        CarportAdminOrderHistoryModel carportAdminOrderHistoryModel = new CarportAdminOrderHistoryModelImpl( fullHistoryMapper, orderMapper );

        
        //Admins
        EditBuildingMaterialsModel editBuildingMaterialsModel = new EditBuildingMaterialsModelImpl();
        
        //Controllers--------------------------------------
        IndexController.startUp( indexModel );
        
        //Header Only
        HeaderController.startUp();
        
        //Feedback
        FeedbackController.startUp( feedbackModel );
        
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
        
        CarportAdminOrderHistoryController.startUp(carportAdminOrderHistoryModel);
        
        //TODO: DELETE THIS THIS IS TEMP
        try {
            
            System.out.println( "FULL HISTORY TEST" );
            Map< Integer, FullHistory > fullHistoryMap = fullHistoryMapper.readAllFull();
            
            for ( FullHistory fullHistory : fullHistoryMap.values() ) {
                System.out.println( fullHistory.toString() );
            }
            
        } catch ( DatabaseException e ) {
            e.printStackTrace();
        }
    }
    
}