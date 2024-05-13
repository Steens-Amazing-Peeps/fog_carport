package app.web.exceptions.carport1Info;

import app.web.exceptions.UserFriendyException;

public class CarportHeightException extends UserFriendyException
{
    
    public CarportHeightException( String userMessage ){
        super( userMessage );
    }
    
    public CarportHeightException( String userMessage, String systemMessage )
    {
        super( userMessage, systemMessage );
        
    }
}
