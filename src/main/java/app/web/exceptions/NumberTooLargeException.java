package app.web.exceptions;

public class NumberTooLargeException extends UserFriendyException
{
    
    public NumberTooLargeException( String userMessage ){
        super( userMessage );
    }
    
    public NumberTooLargeException( String userMessage, String systemMessage )
    {
        super( userMessage, systemMessage );
        
    }
}
