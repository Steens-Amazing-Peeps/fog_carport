package app.web.services.users;


import app.web.constants.attributes.WebGlobalAttributes;
import app.web.entities.User;
import app.web.exceptions.DatabaseException;
import app.web.exceptions.NoIdKeyReturnedException;
import app.web.exceptions.UnexpectedResultDbException;
import app.web.exceptions.WebInvalidInputException;
import app.web.persistence.mappers.UserMapper;

import java.util.Map;
import java.util.Objects;

public class CreateAccountService
{
    
    public static User createAccount( String email, String password, String passwordAgain ) throws DatabaseException, WebInvalidInputException, UnexpectedResultDbException, NoIdKeyReturnedException
    {
        return createAccount( email, password, passwordAgain, null );
    }
    
    public static User createAccount( String email, String password, String passwordAgain, String role ) throws DatabaseException, WebInvalidInputException, UnexpectedResultDbException, NoIdKeyReturnedException
    {
        if ( !validEmail( email ) ) {
            throw new WebInvalidInputException( "Input Error: " + "Not a valid email. The email = '" + email + "'" );
        }
        
        if ( !Objects.equals( password, passwordAgain ) ) {
            throw new WebInvalidInputException( "Input Error: " + "Passwords do not match" );
        }
        
        if ( password.length() > UserMapper.PASSWORD_MAX_LENGTH ) {
            throw new WebInvalidInputException( "Input Error: " + "Password too long" );
        }
        
        if ( password.length() < UserMapper.PASSWORD_MIN_LENGTH ) {
            throw new WebInvalidInputException( "Input Error: " + "Password too short" );
        }
        
        Map< Integer, User > singleUserMap = UserMapper.readAllByEmail( email );
        
        if ( !singleUserMap.isEmpty() ) {
            throw new WebInvalidInputException( "Input Error: " + "Email already used, login instead. The email = '" + email + "'" );
        }
        
        if ( singleUserMap.size() > 1 ) {
            throw new UnexpectedResultDbException( "Database Error: " + "Found multiple accounts with the email, contact an administrator. The email = '" + email + "'", "Database Error: " + "at create account: " + "Found multiple accounts with the email, contact an administrator. The email = '" + email + "'" );
        }
        
        User user = new User();
        user.setEmail( email );
        user.setPassword( password );
        user.setRole( role );
        
        UserMapper.create( user );
        
        WebGlobalAttributes.USER_MAP.put( user.getUserId(), user );
        
        return user;
    }
    
    private static boolean validEmail( String email ) //TODO: Make this not suck
    {
        if ( email.contains( "@" ) && email.contains( "." ) ) {
            return true;
        }
        return false;
    }
    
}
