package app.web.exceptions;

public class WebGenericException extends UserFriendyException
{
    
    public WebGenericException( String userMessage )
    {
        super( userMessage );
    }
    
    public WebGenericException( String userMessage, String systemMessage )
    {
        super( userMessage, systemMessage );
        
    }
}
