package app.web.exceptions;

public class NumberTooSmallException extends UserFriendyException
{
    
    public NumberTooSmallException( String userMessage ){
        super( userMessage );
    }
    
    public NumberTooSmallException( String userMessage, String systemMessage )
    {
        super( userMessage, systemMessage );
        
    }
    
}
