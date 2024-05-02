package app.web.persistence.mappers;

import app.web.entities.User;
import app.web.exceptions.DatabaseException;
import app.web.exceptions.NoIdKeyReturnedException;
import app.web.exceptions.UnexpectedResultDbException;

import java.util.Map;


public interface UserMapper
{
    void setDataStore( DataStore dataStore );
    int create( User user ) throws DatabaseException, NoIdKeyReturnedException, UnexpectedResultDbException;
    
    Map< Integer, User > readAll() throws DatabaseException;
    
    Map< Integer, User > readAllByEmail( String email ) throws DatabaseException;
    
    User readSingle( Integer id ) throws DatabaseException;
    
    int update( User user ) throws DatabaseException, UnexpectedResultDbException;
    
    int delete( Integer id ) throws DatabaseException, UnexpectedResultDbException;
    
}
