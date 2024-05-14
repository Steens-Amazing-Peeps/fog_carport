package app.web.exceptions.carport1Info;

import app.web.exceptions.UserFriendyException;

public class CarportWidthException extends UserFriendyException
{
    
    public CarportWidthException( String userMessage ){
        super( userMessage );
    }
    
    public CarportWidthException( String userMessage, String systemMessage )
    {
        super( userMessage, systemMessage );
        
    }
    
}
