package app.web.pageControllers.models.users.buyFlow;

import app.web.entities.FullHistory;
import app.web.entities.User;
import app.web.exceptions.DatabaseException;
import app.web.exceptions.UnexpectedResultDbException;

public interface CarportOrderHistoryModel
{
    
    FullHistory getFullHistory( User currentUser ) throws DatabaseException;
    
    FullHistory finishTemp( String doneOrderIdAsString, User user ) throws DatabaseException, UnexpectedResultDbException;
    
}
