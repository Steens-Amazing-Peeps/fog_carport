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
    
    public static interface Bom
    {
        
        //Bom Logic
        int MINIMUM_BATCH_SIZE = 5; //Will try to find a valid result among these x cheapest planks, if not, increases the search by 1 planks //Cannot be negative //0 to search through all planks (can be slow)
        int AMOUNT_OF_WASTE_ACCEPTABLE_IN_MM= 100; //Do not allow waste beyond this amount of mm //Cannot be negative //0 to not care about waste and accept the cheapest option in the batch
        int USE_LEAST_WASTEFUL_OPTION_AT_PRICE_DIFFERENCE= 500; //Use the least wasteful option if the price difference is less than this //Cannot be negative //0 to always use least wasteful
        
        int SPLIT_CARPORT_SEGMENT_INTO_TWO_SEGMENTS_AT_THIS_WIDTH = 3000; //Split a segment into two at this width (mm)
        int MINIMUM_DISTANCE_BETWEEN_POLES_CARPORT_WIDTH = 1000; //Don't put poles in the middle unless the second segment is at least this size  (mm)
        
    }
    
}
