package app.web.constants;

public interface Config
{
    //General
    int PORT = 7072;
    
    //User
    int PASSWORD_MAX_LENGTH = 100; //DB max = 100  //Cannot be negative
    int PASSWORD_MIN_LENGTH = 4; //Cannot be negative
    String DEFAULT_USER_ROLE = "user"; //DB default = user  //Cannot be null
    
    
    //ConnectionPool for DB
    int CONNECTION_POOL_SIZE = 10; //Min 3
}
