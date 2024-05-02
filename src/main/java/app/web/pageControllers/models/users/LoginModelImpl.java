package app.web.pageControllers.models.users;



import app.web.entities.User;
import app.web.exceptions.DatabaseException;
import app.web.exceptions.UnexpectedResultDbException;
import app.web.exceptions.WebInvalidInputException;
import app.web.persistence.mappers.UserMapper;

import java.util.Map;
import java.util.Objects;

public class LoginModelImpl implements LoginModel
{
    
    UserMapper userMapper;
    
    public LoginModelImpl( UserMapper userMapper )
    {
        this.userMapper = userMapper;
    }
    
    
    @Override
    public User login( String email, String password ) throws DatabaseException, WebInvalidInputException, UnexpectedResultDbException
    {
        Map< Integer, User > singleUserMap = this.userMapper.readAllByEmail( email );
        
        if ( singleUserMap.isEmpty() ) {
            throw new WebInvalidInputException( "Input Error: " + "User with the email was not found. The email = '" + email + "'" );
        }
        
        if ( singleUserMap.size() > 1 ) {
            throw new UnexpectedResultDbException( "Database Error: " + "Found multiple accounts with the email, contact an administrator. The email = '" + email + "'", "Database Error: " + "at login: " + "Found multiple accounts with the email, contact an administrator. The email = '" + email + "'" );
        }
        
        User user = null;
        for ( User userInMap : singleUserMap.values() ) {
            user = userInMap;
        }
        
        if ( !Objects.equals( user.getPassword(), password ) ) {
            throw new WebInvalidInputException( "Input Error: " + "Wrong Password" );
        }
        
        return user;
    }
    
}
