package app.web.persistence.mappers;

import app.web.entities.Shed;
import app.web.exceptions.DatabaseException;
import app.web.exceptions.NoIdKeyReturnedException;
import app.web.exceptions.UnexpectedResultDbException;

import java.util.Map;


public interface ShedMapper
{
    void setDataStore( DataStore dataStore );
    int create( Shed shed ) throws DatabaseException, NoIdKeyReturnedException, UnexpectedResultDbException;

    Map< Integer, Shed > readAll() throws DatabaseException;

    Map< Integer, Shed > readAllByCarportId( Integer carport_id ) throws DatabaseException;

    Shed readSingle( Integer shed_id ) throws DatabaseException;

    int update( Shed shed ) throws DatabaseException, UnexpectedResultDbException;

    int delete( Integer shed_id ) throws DatabaseException, UnexpectedResultDbException;

}
