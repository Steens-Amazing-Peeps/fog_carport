package app.web.pageControllers.models.admins.buyFlow;

import app.web.entities.FullHistory;
import app.web.exceptions.DatabaseException;
import app.web.exceptions.UnexpectedResultDbException;
import app.web.persistence.mappers.FullHistoryMapper;
import app.web.persistence.mappers.OrderMapper;
import app.web.persistence.mappers.UserMapper;

import java.util.Map;

public class CarportAdminOrderHistoryModelImpl implements CarportAdminOrderHistoryModel
{
    FullHistoryMapper fullHistoryMapper;
    OrderMapper orderMapper;
    
    public CarportAdminOrderHistoryModelImpl( FullHistoryMapper fullHistoryMapper, OrderMapper orderMapper )
    {
        this.fullHistoryMapper = fullHistoryMapper;
        this.orderMapper = orderMapper;
    }
    @Override
    public Map<Integer, FullHistory> getFullHistory() throws DatabaseException
    {
        return this.fullHistoryMapper.readAllFull();
    }
    
    @Override
    public Map< Integer, FullHistory > approve( String doneOrderIdAsString ) throws UnexpectedResultDbException, DatabaseException
    {
        Integer orderId = Integer.parseInt( doneOrderIdAsString );
        this.orderMapper.updateApprove( orderId );
        
        return this.fullHistoryMapper.readAllFull();
    }
    
}
