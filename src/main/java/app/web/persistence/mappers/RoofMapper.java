package app.web.persistence.mappers;

import app.web.entities.Roof;
import app.web.exceptions.DatabaseException;
import app.web.exceptions.NoIdKeyReturnedException;
import app.web.exceptions.UnexpectedResultDbException;

import java.util.Map;


public interface RoofMapper
{
    void setDataStore( DataStore dataStore );
    int create( Roof roof ) throws DatabaseException, NoIdKeyReturnedException, UnexpectedResultDbException;

    Map< Integer, Roof > readAll() throws DatabaseException;

    Map< Integer, Roof > readAllByCarportId( Integer carport_id ) throws DatabaseException;

    Roof readSingle( Integer roof_id ) throws DatabaseException;

    int update( Roof roof ) throws DatabaseException, UnexpectedResultDbException;

    int delete( Integer roof_id ) throws DatabaseException, UnexpectedResultDbException;

}
