package app.web.persistence.mappers;

import app.web.entities.Order;
import app.web.exceptions.DatabaseException;
import app.web.exceptions.NoIdKeyReturnedException;
import app.web.exceptions.UnexpectedResultDbException;

import java.util.Map;


public interface OrderMapper
{
    
    
    void setDataStore( DataStore dataStore );
    
    int create( Order order, Integer contact_info_id ) throws DatabaseException, NoIdKeyReturnedException, UnexpectedResultDbException;
    
    int createFull( Order order, Integer userId ) throws DatabaseException, NoIdKeyReturnedException, UnexpectedResultDbException;
    
    Map< Integer, Order > readAll() throws DatabaseException;
    
    Map< Integer, Order > readAllByUserId( Integer user_id ) throws DatabaseException;
    
    Order readSingle( Integer order_id ) throws DatabaseException;
    
    int update( Order order ) throws DatabaseException, UnexpectedResultDbException;
    
    int delete( Integer order_id ) throws DatabaseException, UnexpectedResultDbException;
    
}
