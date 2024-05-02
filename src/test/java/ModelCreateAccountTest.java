import app.web.entities.User;
import app.web.exceptions.DatabaseException;
import app.web.exceptions.NoIdKeyReturnedException;
import app.web.exceptions.UnexpectedResultDbException;
import app.web.exceptions.WebInvalidInputException;
import app.web.pageControllers.models.users.CreateAccountModel;
import app.web.pageControllers.models.users.CreateAccountModelImpl;
import org.junit.jupiter.api.*;
import testClasses.mappers.UserMapperTest;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class ModelCreateAccountTest
{
    
    UserMapperTest userMapperTest = new UserMapperTest();
    CreateAccountModel createAccountModel;
    
    
    String email;
    String password;
    String repeatPassword;
    Map< Integer, User > globalUserMap;
    String role;
    
    User expectedResUser;
    Map< Integer, User > expectedTestMap;
    
    User actualResUser = null;
    
    
    @BeforeAll
    static void beforeAll()
    {
    
    }
    
    @BeforeEach
    void setUp()
    {
        this.email = "valid@email.com";
        this.password = "superSafePassword_123";
        this.repeatPassword = this.password;
        this.globalUserMap = new LinkedHashMap<>();
        this.role = "user";
        
        this.expectedTestMap = new LinkedHashMap<>();
        
        this.expectedResUser = new User();
        this.expectedResUser.setUserId( 1 );
        this.expectedResUser.setEmail( this.email );
        this.expectedResUser.setPassword( this.password );
        this.expectedResUser.setRole( this.role );
        
        this.userMapperTest.setExpectedTestMap( this.expectedTestMap );
        this.userMapperTest.setExpectedCreateId( this.expectedResUser.getUserId() );
        
        this.createAccountModel = new CreateAccountModelImpl( this.userMapperTest );
    }
    
    @AfterEach
    void tearDown()
    {
    
    }
    
    @AfterAll
    static void afterAll()
    {
    
    }
    
    @Test
    void happyPath()
    {
        try {
            this.actualResUser = this.createAccountModel.createAccount( this.email, this.password, this.repeatPassword, this.globalUserMap, this.role );
            
        } catch ( DatabaseException | WebInvalidInputException | UnexpectedResultDbException |
                  NoIdKeyReturnedException e ) {
            fail();
        }
        
        assertNotNull( this.actualResUser );
        assertEquals( this.expectedResUser, this.actualResUser );
        
        assertEquals( this.globalUserMap.get( this.actualResUser.getUserId() ), this.actualResUser );
        assertEquals( this.expectedResUser.getEmail(), this.userMapperTest.getActualEmail() );
    }
    
    @Test
    void emailTaken()
    {
        //Email Taken
        this.expectedTestMap = new LinkedHashMap<>();
        this.expectedTestMap.put( this.expectedResUser.getUserId(), this.expectedResUser );
        
        this.userMapperTest.setExpectedTestMap( this.expectedTestMap );
        
        try {
            this.createAccountModel.createAccount( this.email, this.password, this.repeatPassword, this.globalUserMap, this.role );
            //Above method should never go through without throwing
            //Below should never run
            fail();
        } catch ( DatabaseException | UnexpectedResultDbException |
                  NoIdKeyReturnedException | WebInvalidInputException e ) {
            assertTrue( e instanceof WebInvalidInputException );
        }
    }
    
    
    @Test
    void passwordsDoNotMatch(){
        //Passwords do not match
        this.repeatPassword = this.password + " ";
        
        try {
            this.createAccountModel.createAccount( this.email, this.password, this.repeatPassword, this.globalUserMap, this.role );
            //Above method should never go through without throwing
            //Below should never run
            fail();
        } catch ( DatabaseException | UnexpectedResultDbException |
                  NoIdKeyReturnedException | WebInvalidInputException e ) {
            assertTrue( e instanceof WebInvalidInputException );
        }
        
        this.repeatPassword = this.password + "s";
        
        try {
            this.createAccountModel.createAccount( this.email, this.password, this.repeatPassword, this.globalUserMap, this.role );
            //Above method should never go through without throwing
            //Below should never run
            fail();
        } catch ( DatabaseException | UnexpectedResultDbException |
                  NoIdKeyReturnedException | WebInvalidInputException e ) {
            assertTrue( e instanceof WebInvalidInputException );
        }
        
    }
    
    @Test
    void passwordTooShort(){
        //Password too short
        this.password = "123";
        
        try {
            this.createAccountModel.createAccount( this.email, this.password, this.repeatPassword, this.globalUserMap, this.role );
            //Above method should never go through without throwing
            //Below should never run
            fail();
        } catch ( DatabaseException | UnexpectedResultDbException |
                  NoIdKeyReturnedException | WebInvalidInputException e ) {
            assertTrue( e instanceof WebInvalidInputException );
        }
    }
    
    @Test
    void passwordTooLong(){
        //Password too long
        this.password = "9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999";
        
        try {
            this.createAccountModel.createAccount( this.email, this.password, this.repeatPassword, this.globalUserMap, this.role );
            //Above method should never go through without throwing
            //Below should never run
            fail();
        } catch ( DatabaseException | UnexpectedResultDbException |
                  NoIdKeyReturnedException | WebInvalidInputException e ) {
            assertTrue( e instanceof WebInvalidInputException );
        }
    }
    
    @Test
    void emailInvalid(){
        //Email invalid
        this.email = "dddaaaa.com";
        
        try {
            this.createAccountModel.createAccount( this.email, this.password, this.repeatPassword, this.globalUserMap, this.role );
            //Above method should never go through without throwing
            //Below should never run
            fail();
        } catch ( DatabaseException | UnexpectedResultDbException |
                  NoIdKeyReturnedException | WebInvalidInputException e ) {
            assertTrue( e instanceof WebInvalidInputException );
        }
        
        this.email = "dddaaaa@com";
        
        try {
            this.createAccountModel.createAccount( this.email, this.password, this.repeatPassword, this.globalUserMap, this.role );
            //Above method should never go through without throwing
            //Below should never run
            fail();
        } catch ( DatabaseException | UnexpectedResultDbException |
                  NoIdKeyReturnedException | WebInvalidInputException e ) {
            assertTrue( e instanceof WebInvalidInputException );
        }
        
        this.email = "dddaaaacom";
        
        try {
            this.createAccountModel.createAccount( this.email, this.password, this.repeatPassword, this.globalUserMap, this.role );
            //Above method should never go through without throwing
            //Below should never run
            fail();
        } catch ( DatabaseException | UnexpectedResultDbException |
                  NoIdKeyReturnedException | WebInvalidInputException e ) {
            assertTrue( e instanceof WebInvalidInputException );
        }
        
 
    }
    
    @Test
    void emailInvalidOptionalReq(){
        //Additional, Optional email validation
        
        this.email = "dddaaaa@.com";
        
        try {
            this.createAccountModel.createAccount( this.email, this.password, this.repeatPassword, this.globalUserMap, this.role );
            //Above method should never go through without throwing
            //Below should never run
            fail();
        } catch ( DatabaseException | UnexpectedResultDbException |
                  NoIdKeyReturnedException | WebInvalidInputException e ) {
            assertTrue( e instanceof WebInvalidInputException );
        }
        
        this.email = "dddaaaa@das@d.com";
        
        try {
            this.createAccountModel.createAccount( this.email, this.password, this.repeatPassword, this.globalUserMap, this.role );
            //Above method should never go through without throwing
            //Below should never run
            fail();
        } catch ( DatabaseException | UnexpectedResultDbException |
                  NoIdKeyReturnedException | WebInvalidInputException e ) {
            assertTrue( e instanceof WebInvalidInputException );
        }
        
        this.email = "dddaaaa@dasd.com.dk.com";
        
        try {
            this.createAccountModel.createAccount( this.email, this.password, this.repeatPassword, this.globalUserMap, this.role );
            
        } catch ( DatabaseException | UnexpectedResultDbException |
                  NoIdKeyReturnedException | WebInvalidInputException e ) {
            fail();
        }
    }
    
}
