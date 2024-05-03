package app.web.constants;

public interface Config
{
    
    public static interface General
    {
        
        //Website General
        int PORT = 7072;
        
    }
    
    public static interface User
    {
        
        //User
        int PASSWORD_MAX_LENGTH = 100; //DB max = 100  //Cannot be negative
        int PASSWORD_MIN_LENGTH = 4; //Cannot be negative
        String DEFAULT_USER_ROLE = "user"; //DB default = user  //Cannot be null
        
    }
    
    public static interface ConnectionPool
    {
        
        //ConnectionPool for DB
        int CONNECTION_POOL_SIZE = 10; //Min 3
        String JDBC_USER = System.getenv( "JDBC_USER" );
        String JDBC_PASSWORD = System.getenv( "JDBC_PASSWORD" );
        String JDBC_CONNECTION_STRING = System.getenv( "JDBC_CONNECTION_STRING" );
        String JDBC_DB = System.getenv( "JDBC_DB" );
        
    }
    
}
