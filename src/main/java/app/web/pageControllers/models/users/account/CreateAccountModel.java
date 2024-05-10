package app.web.pageControllers.models.users.account;

import app.web.entities.User;
import app.web.exceptions.DatabaseException;
import app.web.exceptions.NoIdKeyReturnedException;
import app.web.exceptions.UnexpectedResultDbException;
import app.web.exceptions.WebInvalidInputException;

import java.util.Map;

public interface CreateAccountModel
{
    
    User createAccount( String email, String password, String passwordAgain, Map< Integer, User > globalUserMap, String role ) throws DatabaseException, WebInvalidInputException, UnexpectedResultDbException, NoIdKeyReturnedException;
    
    User createAccount( String email, String password, String passwordAgain, Map<Integer, User> globalUserMap ) throws DatabaseException, WebInvalidInputException, UnexpectedResultDbException, NoIdKeyReturnedException;
    
}
