package app.web.pageControllers.models.users.account;

import app.web.entities.User;
import app.web.exceptions.DatabaseException;
import app.web.exceptions.UnexpectedResultDbException;
import app.web.exceptions.WebInvalidInputException;

public interface LoginModel
{
    
    User login( String email, String password ) throws DatabaseException, WebInvalidInputException, UnexpectedResultDbException;
    
}
