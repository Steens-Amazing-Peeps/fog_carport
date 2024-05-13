package app.web.persistence.mappers;

import app.web.entities.Plank;
import app.web.exceptions.DatabaseException;
import app.web.exceptions.NoIdKeyReturnedException;
import app.web.exceptions.UnexpectedResultDbException;

import java.util.Map;

public interface PlankMapper
{
    
    void setDataStore( DataStore dataStore );
    
    int create( Plank plank ) throws DatabaseException, NoIdKeyReturnedException, UnexpectedResultDbException;
    
    Map< Integer, Plank > readAll() throws DatabaseException;
    
    Map< Integer, Plank > readAllByUserId( Integer user_id ) throws DatabaseException;
    
    Plank readSingle( Integer plank_id ) throws DatabaseException;
    
    int update( Plank plank ) throws DatabaseException, UnexpectedResultDbException;
    
    int delete( Integer plank_id ) throws DatabaseException, UnexpectedResultDbException;
    
}
