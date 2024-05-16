package app.web.persistence.mappers;

import app.web.entities.FullHistory;
import app.web.exceptions.DatabaseException;

import java.util.List;
import java.util.Map;

public interface FullHistoryMapper
{
    
    
    
    List< FullHistory > readAllFull() throws DatabaseException;
    
}
