package app.web.persistence.mappers;

import app.web.entities.Carport;
import app.web.exceptions.DatabaseException;
import app.web.exceptions.NoIdKeyReturnedException;
import app.web.exceptions.UnexpectedResultDbException;

import java.util.Map;


public interface CarportMapper
{
    void setDataStore( DataStore dataStore );
    int create( Carport carport ) throws DatabaseException, NoIdKeyReturnedException, UnexpectedResultDbException;

    Map< Integer, Carport > readAll() throws DatabaseException;

    Map< Integer, Carport > readAllByOrderId( Integer order_id ) throws DatabaseException;

    Carport readSingle( Integer carport_id ) throws DatabaseException;

    int update( Carport carport ) throws DatabaseException, UnexpectedResultDbException;

    int delete( Integer carport_id ) throws DatabaseException, UnexpectedResultDbException;

}
