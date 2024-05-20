package app.web.pageControllers.models.users.account;


import app.util.Validators;
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
        StringBuilder combinedCommonErrors = new StringBuilder();
        int amountOfCommonErrors = 0;

        if ( !Validators.isEmailValid( email ) ) {
            combinedCommonErrors.append("- Emailen '" + email + "' er ikke en gyldig email" + System.lineSeparator());
            amountOfCommonErrors = amountOfCommonErrors + 1;
//            throw new WebInvalidInputException( "Emailen '" + email + "' er ikke en gyldig email" );
        }
        
        if ( !Objects.equals( password, passwordAgain ) ) {
            combinedCommonErrors.append("- Kodeordende var ikke ens" + System.lineSeparator());
            amountOfCommonErrors = amountOfCommonErrors + 1;
//            throw new WebInvalidInputException( "Kodeordende var ikke ens" );
        }
        
        if ( password.length() > Config.User.PASSWORD_MAX_LENGTH ) {
            combinedCommonErrors.append("- Kode ord for langt, max " + Config.User.PASSWORD_MAX_LENGTH + " tegn" + System.lineSeparator());
            amountOfCommonErrors = amountOfCommonErrors + 1;
//            throw new WebInvalidInputException( "Kode ord for langt, max " + Config.User.PASSWORD_MAX_LENGTH + " tegn" );
        }
        
        if ( password.length() < Config.User.PASSWORD_MIN_LENGTH ) {
            combinedCommonErrors.append("- Kode ord for kort, min " + Config.User.PASSWORD_MIN_LENGTH + " tegn");
            amountOfCommonErrors = amountOfCommonErrors + 1;
//            throw new WebInvalidInputException( "Kode ord for kort, min " + Config.User.PASSWORD_MIN_LENGTH + " tegn" );
        }

        if (!combinedCommonErrors.isEmpty()){
            combinedCommonErrors.insert(0,"Der er '" + amountOfCommonErrors + "' fejl med at oprette en bruger:" + System.lineSeparator());
            throw new WebInvalidInputException(combinedCommonErrors.toString());
        }
        
        Map< Integer, User > singleUserMap = this.userMapper.readAllByEmail( email );
        
        if ( !singleUserMap.isEmpty() ) {
            throw new WebInvalidInputException( "En bruger med emailen '" + email + "' findes allerede" );
        }
        
        if ( singleUserMap.size() > 1 ) {
            throw new UnexpectedResultDbException( "Database Fejl: Flere brugere fundet med samme email, contact en administrator. Emailen er '" + email + "'", "Database Error: " + "at create account: " + "Found multiple accounts with the email, contact an administrator. The email = '" + email + "'" );
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
    
    
}
