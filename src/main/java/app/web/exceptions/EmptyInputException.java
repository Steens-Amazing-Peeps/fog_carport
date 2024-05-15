package app.web.exceptions;

public class EmptyInputException extends UserFriendyException
{
    
    public EmptyInputException( String userMessage )
    {
        super( userMessage );
    }
    
    public EmptyInputException( String userMessage, String systemMessage )
    {
        super( userMessage, systemMessage );
        
    }
    
}
