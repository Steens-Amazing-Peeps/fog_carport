package app.web.persistence.mappers;

import app.web.entities.AccountInfo;
import app.web.entities.Order;
import app.web.exceptions.DatabaseException;
import app.web.exceptions.NoIdKeyReturnedException;
import app.web.exceptions.UnexpectedResultDbException;

import java.util.Map;


public interface OrderMapper
{
    
    
    
    void setDataStore( DataStore dataStore );
    
    int create( Order order, Integer contactInfoId ) throws DatabaseException, NoIdKeyReturnedException, UnexpectedResultDbException;
    
    int createFull( Order order ) throws DatabaseException, NoIdKeyReturnedException, UnexpectedResultDbException;
    
    Map< Integer, Order > readAll() throws DatabaseException;
    

    
    Map< Integer, Order > readAllByAccountInfoId( Integer accountInfoId ) throws DatabaseException;
    
    
    Map< Integer, Order > readAllByAccountInfoIdFull( AccountInfo accountInfo ) throws DatabaseException;
    
    Order readSingle( Integer orderId ) throws DatabaseException;
    
    int update( Order order, Integer accountInfoId ) throws DatabaseException, UnexpectedResultDbException;
    
    int delete( Integer orderId ) throws DatabaseException, UnexpectedResultDbException;
    
}
