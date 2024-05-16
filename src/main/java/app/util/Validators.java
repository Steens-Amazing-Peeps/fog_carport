package app.util;

public class Validators
{
    
    public static boolean isEmailValid( String email ) //TODO: Make this not suck
    {
        if ( email == null  || email.isBlank()) {
            return false;
        }
        
        if ( !email.contains( "@" ) || !email.contains( "." ) ) {
            return false;
        }
        
        return true;
    }
    
}
