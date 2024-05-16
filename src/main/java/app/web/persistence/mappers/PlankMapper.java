package app.web.persistence.mappers;

import app.web.entities.Plank;
import app.web.exceptions.DatabaseException;
import app.web.exceptions.NoIdKeyReturnedException;
import app.web.exceptions.UnexpectedResultDbException;

import java.util.Map;

public interface PlankMapper
{
    
    
    void setDataStore( DataStore dataStore );
    
    Map< Integer, Plank > readAll() throws DatabaseException;
    
    Map< Integer, Plank > readAllByType( Integer type ) throws DatabaseException;
    
    Plank readSingle( Integer plankId ) throws DatabaseException;
    
    int delete( Integer plankId ) throws DatabaseException, UnexpectedResultDbException;
    
}
