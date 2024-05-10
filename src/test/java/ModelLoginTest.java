import app.web.entities.User;
import app.web.exceptions.DatabaseException;
import app.web.exceptions.UnexpectedResultDbException;
import app.web.exceptions.WebInvalidInputException;
import app.web.pageControllers.models.users.account.LoginModel;
import app.web.pageControllers.models.users.account.LoginModelImpl;
import org.junit.jupiter.api.*;
import testClasses.mappers.UserMapperTest;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ModelLoginTest
{
    UserMapperTest userMapperTest = new UserMapperTest();
    LoginModel loginModel;
    
    
    String email;
    String password;
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
        this.role = "user";
        
        this.expectedResUser = new User();
        this.expectedResUser.setUserId( 1 );
        this.expectedResUser.setEmail( this.email );
        this.expectedResUser.setPassword( this.password );
        this.expectedResUser.setRole( this.role );
        
        this.expectedTestMap = new LinkedHashMap<>();
        this.expectedTestMap.put( this.expectedResUser.getUserId(), this.expectedResUser );
        
        this.userMapperTest.setExpectedTestMap( this.expectedTestMap );
        this.userMapperTest.setExpectedCreateId( this.expectedResUser.getUserId() );

        this.loginModel = new LoginModelImpl( this.userMapperTest );
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
            this.actualResUser = this.loginModel.login( this.email, this.password);
            
        } catch ( DatabaseException | WebInvalidInputException | UnexpectedResultDbException e ) {
            fail();
        }
        
        assertNotNull( this.actualResUser );
        assertEquals( this.expectedResUser, this.actualResUser );
        
        assertEquals( this.expectedResUser.getEmail(), this.userMapperTest.getActualEmail() );
    }
    
    @Test
    void userDoesNotExist()
    {
        this.expectedTestMap.clear();
        
        try {
            this.actualResUser = this.loginModel.login( this.email, this.password);
            //Above method should never go through without throwing
            //Below should never run
            fail();
        } catch ( DatabaseException | WebInvalidInputException | UnexpectedResultDbException e ) {
            assertTrue( e instanceof WebInvalidInputException );
        }
        
    }
    
    @Test
    void wrongPassword()
    {
        this.password = "wrongPassword";
        
        try {
            this.actualResUser = this.loginModel.login( this.email, this.password);
            //Above method should never go through without throwing
            //Below should never run
            fail();
        } catch ( DatabaseException | WebInvalidInputException | UnexpectedResultDbException e ) {
            assertTrue( e instanceof WebInvalidInputException );
        }
    
    }
    
}
