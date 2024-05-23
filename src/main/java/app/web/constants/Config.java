package app.web.constants;

public interface Config
{
    
    public static interface General
    {
        
        //Website General
        int PORT = 7070;
        
    }
    
    public static interface User
    {
        
        //User
        int PASSWORD_MAX_LENGTH = 100; //DB max = 100  //Cannot be negative
        int PASSWORD_MIN_LENGTH = 4; //Cannot be negative
        String USER_ROLE = "user"; //DB default = user  //Cannot be null //Only change these if you know what you are doing with databases
        String ADMIN_ROLE = "admin"; //Cannot be null //Only change these if you know what you are doing with databases
        
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
        int AMOUNT_OF_WASTE_ACCEPTABLE_IN_MM = 100; //Do not allow waste beyond this amount of mm //Cannot be negative //0 to not care about waste and accept the cheapest option in the batch
        int USE_LEAST_WASTEFUL_OPTION_AT_PRICE_DIFFERENCE = 500; //Use the least wasteful option if the price difference is less than this //Cannot be negative //0 to always use least wasteful
        
        int SPLIT_CARPORT_SEGMENT_INTO_TWO_SEGMENTS_AT_THIS_WIDTH_IN_MM = 6000; //Split a segment into two at this width (mm)
        //        int SPLIT_CARPORT_SEGMENT_INTO_TWO_SEGMENTS_AT_THIS_WIDTH = 3000; //Split a segment into two at this width (mm)
        int MINIMUM_DISTANCE_BETWEEN_POLES_CARPORT_WIDTH_IN_MM = 0; //Don't put poles in the middle unless the second segment is at least this size  (mm)
//        int MINIMUM_DISTANCE_BETWEEN_POLES_CARPORT_WIDTH = 1000; //Don't put poles in the middle unless the second segment is at least this size  (mm)
    
    }
    
    
    public static interface Carport
    {
        
        int MINIMUM_HEIGHT_IN_MM = 2000;
        int MAXIMUM_HEIGHT_IN_MM = 5000;
        
        int MINIMUM_LENGTH_IN_MM = 1000;
        int MAXIMUM_LENGTH_IN_MM = 50000;
        
        int MINIMUM_WIDTH_IN_MM = 1000;
        int MAXIMUM_WIDTH_IN_MM = 6000;
        
        Integer SERVICE_FEE_PERCENTAGE_FOR_SUGGESTED_PRICE = 50; //Suggested price will be 50 % higher than base materials
        
    }
    
    public static interface MetricUnits
    {
        int COMMA_DIGITS_IN_CM_IN_STRING = 1;
        int COMMA_DIGITS_IN_M_IN_STRING = 2;
    }

    public static interface AccountInfo
    {
        int MAXIMUM_FULLNAME_LENGTH = 100;
        int MAXIMUM_ADDRESS_LENGTH = 50;
        int REQUIRED_ZIP_DK_LENGTH = 4;
        int MAXIMUM_CITY_LENGTH = 50;
        int REQUIRED_TLF_DK_LENGTH = 8;
    }
    
}
