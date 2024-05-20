package app.web.pageControllers.models.admins.buyFlow;

import app.web.entities.FullHistory;
import app.web.exceptions.DatabaseException;
import app.web.exceptions.UnexpectedResultDbException;

import java.util.Map;

public interface CarportAdminOrderHistoryModel
{
    
    Map<Integer, FullHistory> getFullHistory() throws DatabaseException;
    
    Map< Integer, FullHistory > approve( String doneOrderIdAsString, String priceActualInOereAsString ) throws UnexpectedResultDbException, DatabaseException;
    
}
