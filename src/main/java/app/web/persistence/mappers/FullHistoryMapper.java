package app.web.persistence.mappers;

import app.web.entities.FullHistory;
import app.web.entities.User;
import app.web.exceptions.DatabaseException;

import java.util.List;
import java.util.Map;

public interface FullHistoryMapper
{
    
    
    
    Map<Integer, FullHistory > readAllFull() throws DatabaseException; //UserId, FullHistory
    
    FullHistory readSingle( Integer userId ) throws DatabaseException;
    
    FullHistory readSingle( User currentUser ) throws DatabaseException;
    
}
