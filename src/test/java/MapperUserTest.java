import app.web.entities.User;
import app.web.exceptions.DatabaseException;
import app.web.exceptions.NoIdKeyReturnedException;
import app.web.exceptions.UnexpectedResultDbException;
import app.web.persistence.ConnectionPool;
import app.web.persistence.mappers.DataStore;
import app.web.persistence.mappers.DataStoreImpl;
import app.web.persistence.mappers.UserMapper;
import app.web.persistence.mappers.UserMapperImpl;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

public class MapperUserTest
{
    
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    private DataStore dataStore = new DataStoreImpl( CONNECTION_POOL );
    private UserMapper userMapper = new UserMapperImpl( this.dataStore );
    private User testUser1;
    private User testUser2;
    
    @BeforeAll
    static void SetUpClass()
    {
        try ( Connection connection = CONNECTION_POOL.getConnection() ) {
            
            try ( Statement stmt = connection.createStatement() ) {
                
                // The test schema is already created, so we only need to delete/create test tables
                stmt.execute( "DROP TABLE IF EXISTS test.user" );
                stmt.execute( "DROP SEQUENCE IF EXISTS test.user_user_id_seq CASCADE;" );
                // Create tables as copy of original public schema structure
                stmt.execute( "CREATE TABLE test.user AS (SELECT * FROM fog_carport_dev.public.user) WITH NO DATA" );
                // Create sequences for auto generating id's for users and orders
                stmt.execute( "CREATE SEQUENCE test.user_user_id_seq" );
                stmt.execute( "ALTER TABLE IF EXISTS test.user ALTER COLUMN user_id SET DEFAULT NEXTVAL('test.user_user_id_seq')" );
                
            }
        } catch ( SQLException e ) {
            e.printStackTrace();
            throw new RuntimeException( e );
        }
    }
    
    @BeforeEach
    void setUp()
    {
        this.testUser1 = new User();
        this.testUser1.setUserId( 1 );
        this.testUser1.setEmail( "createTestUser@testmail.test" );
        this.testUser1.setPassword( "test" );
        this.testUser1.setRole( "user" );
        
        this.testUser2 = new User();
        this.testUser2.setUserId( 2 );
        this.testUser2.setEmail( "ensejbruger@enmail.dk" );
        this.testUser2.setPassword( "54985899849720" );
        this.testUser2.setRole( "user" );
        
        try ( Connection connection = CONNECTION_POOL.getConnection() ) {
            try ( Statement stmt = connection.createStatement() ) {
                // Remove all rows from all tables
                stmt.execute( "DELETE FROM test.user" );
                
                stmt.execute( "INSERT INTO test.user (user_id, email, password, role) " +
                              "VALUES ('" + this.testUser1.getUserId() + "', '" + this.testUser1.getEmail() + "', '" + this.testUser1.getPassword() + "', '" + this.testUser1.getRole() + "'), " +
                              "('" + this.testUser2.getUserId() + "', '" + this.testUser2.getEmail() + "', '" + this.testUser2.getPassword() + "', '" + this.testUser2.getRole() + "');" );
                
                // Set sequence to continue from the largest member_id
                stmt.execute( "SELECT SETVAL('test.user_user_id_seq', COALESCE((SELECT MAX(user_id) + 1 FROM test.user), 1), FALSE)" );
            }
        } catch ( SQLException e ) {
            e.printStackTrace();
            fail( "Database connection failed" );
        }
    }
    
    @AfterEach
    void tearDown()
    {
        try ( Connection connection = CONNECTION_POOL.getConnection() ) {
            try ( Statement stmt = connection.createStatement() ) {
                // Remove all rows from all tables
                stmt.execute( "DELETE FROM test.user" );
            }
        } catch ( SQLException e ) {
            e.printStackTrace();
            fail( "Database connection failed" );
        }
    }
    
    @AfterAll
    static void afterAll()
    {
        try ( Connection connection = CONNECTION_POOL.getConnection() ) {
            
            try ( Statement stmt = connection.createStatement() ) {
                
                // The test schema is already created, so we only need to delete/create test tables
                stmt.execute( "DROP TABLE IF EXISTS test.user" );
                stmt.execute( "DROP SEQUENCE IF EXISTS test.user_user_id_seq CASCADE;" );
            }
        } catch ( SQLException e ) {
            e.printStackTrace();
            throw new RuntimeException( e );
        }
    }
    
    @Test
    void readAllUserTest()
    {
        try {
            Map< Integer, User > userAllTestMap = userMapper.readAll();
            assertEquals( 2, userAllTestMap.size() );
            isUsersEqual( testUser1, userAllTestMap.get( 1 ) );
            isUsersEqual( testUser2, userAllTestMap.get( 2 ) );
        } catch ( DatabaseException e ) {
            System.out.println( "database error :" + e.getMessage() );
//            throw new RuntimeException(e);
        }
    }
    
    @Test
    void readSingleUserTest()
    {
        try {
            User singleUser = userMapper.readSingle( 1 );
            isUsersEqual( testUser1, singleUser );
        } catch ( DatabaseException e ) {
            System.out.println( "database error :" + e.getMessage() );
//            throw new RuntimeException(e);
        }
    }
    
    @Test
    void createUserTest()
    { //int is affected rows
        try {
            int rowsAffectedByCreate = userMapper.create( testUser1 );
            assertEquals( 1, rowsAffectedByCreate );
            //TODO: possibly make a test database just for these test so the expected value isn't just a magic number
        } catch ( DatabaseException e ) {
            System.out.println( "database error :" + e.getMessage() );
//            throw new RuntimeException(e);
        } catch ( NoIdKeyReturnedException e ) {
            System.out.println( "database error :" + e.getMessage() );
//            throw new RuntimeException(e);
        } catch ( UnexpectedResultDbException e ) {
            System.out.println( "database error :" + e.getMessage() );
//            throw new RuntimeException(e);
        }
    }
    
    @Test
    void updateUserTest()
    { //int is affected rows
        try {
            int rowsAffectedByUpdate = userMapper.update( testUser1 );
            assertEquals( 1, rowsAffectedByUpdate );
        } catch ( DatabaseException e ) {
            System.out.println( "database error :" + e.getMessage() );
//            throw new RuntimeException(e);
        } catch ( UnexpectedResultDbException e ) {
            System.out.println( "database error :" + e.getMessage() );
//            throw new RuntimeException(e);
        }
    }
    
    @Test
    void deleteUserTest()
    { //int is affected rows
        try {
            int rowsAffectedByDelete = userMapper.delete( testUser1.getUserId() );
            assertEquals( 1, rowsAffectedByDelete );
        } catch ( DatabaseException e ) {
            System.out.println( "database error :" + e.getMessage() );
//            throw new RuntimeException(e);
        } catch ( UnexpectedResultDbException e ) {
            System.out.println( "database error :" + e.getMessage() );
//            throw new RuntimeException(e);
        }
    }
    
    private void isUsersEqual( User expectedUser, User actualUser )
    {
        assertEquals( expectedUser.getUserId(), actualUser.getUserId() );
        assertEquals( expectedUser.getEmail(), actualUser.getEmail() );
        assertEquals( expectedUser.getPassword(), actualUser.getPassword() );
        assertEquals( expectedUser.getRole(), actualUser.getRole() );
    }
    
}
