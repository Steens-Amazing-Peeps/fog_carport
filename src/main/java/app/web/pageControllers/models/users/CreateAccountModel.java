package app.web.pageControllers.models.users;

import app.web.entities.User;
import app.web.exceptions.DatabaseException;
import app.web.exceptions.NoIdKeyReturnedException;
import app.web.exceptions.UnexpectedResultDbException;
import app.web.exceptions.WebInvalidInputException;

public interface CreateAccountModel
{
    
    User createAccount( String email, String password, String passwordAgain, String role ) throws DatabaseException, WebInvalidInputException, UnexpectedResultDbException, NoIdKeyReturnedException;
    
    User createAccount( String email, String password, String passwordAgain ) throws DatabaseException, WebInvalidInputException, UnexpectedResultDbException, NoIdKeyReturnedException;
    
}
