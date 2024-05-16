package app.web.persistence.mappers;

import app.web.entities.Carport;
import app.web.exceptions.DatabaseException;
import app.web.exceptions.NoIdKeyReturnedException;
import app.web.exceptions.UnexpectedResultDbException;

import java.util.Map;


public interface CarportMapper
{
    
    
    void setDataStore( DataStore dataStore );
    
    int create( Carport carport, Integer orderId ) throws DatabaseException, NoIdKeyReturnedException, UnexpectedResultDbException;
    
    int createFull( Carport carport, Integer orderId ) throws DatabaseException, NoIdKeyReturnedException, UnexpectedResultDbException;
    
    Map< Integer, Carport > readAll() throws DatabaseException;
    
    Map< Integer, Carport > readAllByOrderId( Integer orderId ) throws DatabaseException;
    
    Carport readSingle( Integer carportId ) throws DatabaseException;
    
    int update( Carport carport, Integer orderId ) throws DatabaseException, UnexpectedResultDbException;
    
    int delete( Integer carportId ) throws DatabaseException, UnexpectedResultDbException;
    
}
