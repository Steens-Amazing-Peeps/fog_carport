package app.web.pageControllers.models.users;


import app.web.constants.Config;
import app.web.entities.User;
import app.web.exceptions.DatabaseException;
import app.web.exceptions.NoIdKeyReturnedException;
import app.web.exceptions.UnexpectedResultDbException;
import app.web.exceptions.WebInvalidInputException;
import app.web.persistence.mappers.UserMapper;

import java.util.Map;
import java.util.Objects;

public class CreateAccountModelImpl implements CreateAccountModel
{
    UserMapper userMapper;
    
    public CreateAccountModelImpl( UserMapper userMapper )
    {
        this.userMapper = userMapper;
    }
    @Override
    public User createAccount( String email, String password, String passwordAgain, Map<Integer, User> globalUserMap, String role ) throws DatabaseException, WebInvalidInputException, UnexpectedResultDbException, NoIdKeyReturnedException
    {
        if ( !isEmailValid( email ) ) {
            throw new WebInvalidInputException( "Input Error: " + "Not a valid email. The email = '" + email + "'" );
        }
        
        if ( !Objects.equals( password, passwordAgain ) ) {
            throw new WebInvalidInputException( "Input Error: " + "Passwords do not match" );
        }
        
        if ( password.length() > Config.PASSWORD_MAX_LENGTH ) {
            throw new WebInvalidInputException( "Input Error: " + "Password too long" );
        }
        
        if ( password.length() < Config.PASSWORD_MIN_LENGTH ) {
            throw new WebInvalidInputException( "Input Error: " + "Password too short" );
        }
        
        Map< Integer, User > singleUserMap = this.userMapper.readAllByEmail( email );
        
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
        
        this.userMapper.create( user );
        
        globalUserMap.put( user.getUserId(), user );
        
        return user;
    }
    
    @Override
    public User createAccount( String email, String password, String passwordAgain, Map<Integer, User> globalUserMap ) throws DatabaseException, WebInvalidInputException, UnexpectedResultDbException, NoIdKeyReturnedException
    {
        return this.createAccount( email, password, passwordAgain, globalUserMap, null );
    }
    
    private static boolean isEmailValid( String email ) //TODO: Make this not suck
    {
        if ( email.contains( "@" ) && email.contains( "." ) ) {
            return true;
        }
        return false;
    }
    
    
}
