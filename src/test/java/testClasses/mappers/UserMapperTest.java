package testClasses.mappers;


import app.web.entities.User;
import app.web.exceptions.DatabaseException;
import app.web.exceptions.NoIdKeyReturnedException;
import app.web.exceptions.UnexpectedResultDbException;
import app.web.persistence.ConnectionPool;
import app.web.persistence.mappers.DataStore;
import app.web.persistence.mappers.DataStoreImpl;
import app.web.persistence.mappers.UserMapper;
import app.web.persistence.mappers.UserMapperImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

public class UserMapperTest implements UserMapper
{
    
    private String actualEmail;
    private int expectedCreateId;
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    private DataStore dataStore = new DataStoreImpl(CONNECTION_POOL);
    private User testUser;
    
    private Map< Integer, User > expectedTestMap;
    
    public String getActualEmail()
    {
        return this.actualEmail;
    }
    
    public int getExpectedCreateId()
    {
        return this.expectedCreateId;
    }
    
    public void setExpectedCreateId( int expectedCreateId )
    {
        this.expectedCreateId = expectedCreateId;
    }
    
    public Map< Integer, User > getExpectedTestMap()
    {
        return this.expectedTestMap;
    }
    
    public void setExpectedTestMap( Map< Integer, User > expectedTestMap )
    {
        this.expectedTestMap = expectedTestMap;
    }
    
    @Override
    public void setDataStore( DataStore dataStore )
    {
    }
    
    @Override
    public int create( User user ) throws DatabaseException, NoIdKeyReturnedException, UnexpectedResultDbException
    {
        user.setUserId( this.expectedCreateId );
        return 1;
    }
    
    @Override
    public Map< Integer, User > readAll() throws DatabaseException
    {
        return null;
    }
    
    @Override
    public Map< Integer, User > readAllByEmail( String email ) throws DatabaseException
    {
        this.actualEmail = email;
        return this.expectedTestMap;
    }
    
    @Override
    public User readSingle( Integer id ) throws DatabaseException
    {
        return null;
    }
    
    @Override
    public int update( User user ) throws DatabaseException, UnexpectedResultDbException
    {
        return 0;
    }
    
    @Override
    public int delete( Integer id ) throws DatabaseException, UnexpectedResultDbException
    {
        return 0;
    }

    @BeforeAll
    static void SetUpClass() {
        try (Connection connection = CONNECTION_POOL.getConnection()){

            try (Statement stmt = connection.createStatement()) {

                // The test schema is already created, so we only need to delete/create test tables
                stmt.execute("DROP TABLE IF EXISTS test.user");
                stmt.execute("DROP TABLE IF EXISTS test.order");
                stmt.execute("DROP SEQUENCE IF EXISTS test.user_user_id_seq CASCADE;");
                stmt.execute("DROP SEQUENCE IF EXISTS test.order_order_id_seq CASCADE;");
                // Create tables as copy of original public schema structure
                stmt.execute("CREATE TABLE test.user AS (SELECT * from fog_carport_dev.public.user) WITH NO DATA");
                stmt.execute("CREATE TABLE test.order AS (SELECT * from fog_carport_dev.public.order) WITH NO DATA");
                // Create sequences for auto generating id's for users and orders
                stmt.execute("CREATE SEQUENCE test.user_user_id_seq");
                stmt.execute("ALTER TABLE test.user ALTER COLUMN user_id SET DEFAULT nextval('test.user_user_id_seq')");
                stmt.execute("CREATE SEQUENCE test.order_order_id_seq");
                stmt.execute("ALTER TABLE test.order ALTER COLUMN order_id SET DEFAULT nextval('test.order_order_id_seq')");

            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @BeforeEach
    void setUp() {
        this.testUser = new User();
        this.testUser.setEmail("createTestUser@testmail.test");
        this.testUser.setPassword("test");
        this.testUser.setRole("user");
    }

    @Test
    void readAllUserTest() {
        try {
            Map<Integer, User> userAllTestMap = new UserMapperImpl(this.dataStore).readAll();
            assertEquals(3,userAllTestMap.size()); //3 is the number that was in the pgadmin database at the moment of this test's creation
        } catch (DatabaseException e) {
            System.out.println("database error :"+e.getMessage());
//            throw new RuntimeException(e);
        }
    }

    @Test
    void readSingleUserTest() {
        try {
            User singleUser = new UserMapperImpl(this.dataStore).readSingle(1);
            assertEquals(1,singleUser.getUserId());
        } catch (DatabaseException e) {
            System.out.println("database error :"+e.getMessage());
//            throw new RuntimeException(e);
        }
    }

    @Test
    void createUserTest() { //int is affected rows
        try {
            int rowsAffectedByCreate = new UserMapperImpl(this.dataStore).create(testUser);
            assertEquals(1,rowsAffectedByCreate);
            //TODO: possibly make a test database just for these test so the expected value isn't just a magic number
        } catch (DatabaseException e) {
            System.out.println("database error :"+e.getMessage());
//            throw new RuntimeException(e);
        } catch (NoIdKeyReturnedException e) {
            System.out.println("database error :"+e.getMessage());
//            throw new RuntimeException(e);
        } catch (UnexpectedResultDbException e) {
            System.out.println("database error :"+e.getMessage());
//            throw new RuntimeException(e);
        }
    }

    @Test
    void updateUserTest() { //int is affected rows
        try {
            int rowsAffectedByUpdate = new UserMapperImpl(this.dataStore).update(testUser);
            assertEquals(1,rowsAffectedByUpdate);
        } catch (DatabaseException e) {
            System.out.println("database error :"+e.getMessage());
//            throw new RuntimeException(e);
        } catch (UnexpectedResultDbException e) {
            System.out.println("database error :"+e.getMessage());
//            throw new RuntimeException(e);
        }
    }

    @Test
    void deleteUserTest() { //int is affected rows
        try {
            int rowsAffectedByDelete = new UserMapperImpl(this.dataStore).delete(testUser.getUserId());
            assertEquals(1,rowsAffectedByDelete);
        } catch (DatabaseException e) {
            System.out.println("database error :"+e.getMessage());
//            throw new RuntimeException(e);
        } catch (UnexpectedResultDbException e) {
            System.out.println("database error :"+e.getMessage());
//            throw new RuntimeException(e);
        }
    }

}
