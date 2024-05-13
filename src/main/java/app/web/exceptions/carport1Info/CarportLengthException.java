package app.web.exceptions.carport1Info;

import app.web.exceptions.UserFriendyException;

public class CarportLengthException extends UserFriendyException
{
    
    public CarportLengthException( String userMessage ){
        super( userMessage );
    }
    
    public CarportLengthException( String userMessage, String systemMessage )
    {
        super( userMessage, systemMessage );
        
    }
}
