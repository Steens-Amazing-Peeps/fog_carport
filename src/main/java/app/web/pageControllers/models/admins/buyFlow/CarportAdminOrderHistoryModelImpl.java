package app.web.pageControllers.models.admins.buyFlow;

import app.web.entities.FullHistory;
import app.web.exceptions.DatabaseException;
import app.web.exceptions.UnexpectedResultDbException;
import app.web.persistence.mappers.FullHistoryMapper;
import app.web.persistence.mappers.OrderMapper;

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
    public Map< Integer, FullHistory > approve( String doneOrderIdAsString, String priceActualInOereAsString ) throws UnexpectedResultDbException, DatabaseException
    {
        Integer orderId = Integer.parseInt( doneOrderIdAsString );
        Integer orderPrice = Integer.parseInt( priceActualInOereAsString );
        
        this.orderMapper.updateApprove( orderId, orderPrice );
        
        return this.fullHistoryMapper.readAllFull();
    }
    
}
