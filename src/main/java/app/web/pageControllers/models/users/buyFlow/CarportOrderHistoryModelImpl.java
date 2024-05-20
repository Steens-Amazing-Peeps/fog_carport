package app.web.pageControllers.models.users.buyFlow;

import app.web.entities.FullHistory;
import app.web.entities.User;
import app.web.exceptions.DatabaseException;
import app.web.exceptions.UnexpectedResultDbException;
import app.web.persistence.mappers.FullHistoryMapper;
import app.web.persistence.mappers.OrderMapper;

import java.util.Map;

public class CarportOrderHistoryModelImpl implements CarportOrderHistoryModel
{
    FullHistoryMapper fullHistoryMapper;
    OrderMapper orderMapper;
    
    public CarportOrderHistoryModelImpl( FullHistoryMapper fullHistoryMapper, OrderMapper orderMapper )
    {
        this.fullHistoryMapper = fullHistoryMapper;
        this.orderMapper = orderMapper;
    }
    @Override
    public FullHistory getFullHistory( User currentUser ) throws DatabaseException
    {
        return this.fullHistoryMapper.readSingle( currentUser );
    }
    
    @Override
    public FullHistory finishTemp( String doneOrderIdAsString, User currentUser ) throws DatabaseException, UnexpectedResultDbException
    {
        Integer orderId = Integer.parseInt( doneOrderIdAsString );
        this.orderMapper.updateFinish( orderId );
        
        return this.fullHistoryMapper.readSingle(currentUser);
    }
    
}
