package app.web.pageControllers.models.users.buyFlow;

import app.web.entities.Order;
import app.web.exceptions.DatabaseException;
import app.web.exceptions.NoIdKeyReturnedException;
import app.web.exceptions.UnexpectedResultDbException;
import app.web.persistence.mappers.*;

public class Carport4ReviewAndConfirmModelImpl implements Carport4ReviewAndConfirmModel
{
    OrderMapper orderMapper;

    
    public Carport4ReviewAndConfirmModelImpl( OrderMapper orderMapper )
    {
        this.orderMapper = orderMapper;

    }
    
    @Override
    public void addNewOrder( Order order, Integer userId ) throws NoIdKeyReturnedException, UnexpectedResultDbException, DatabaseException
    {
        order.calcPriceSuggested();
        order.setDateRequestedToNow();
        order.setStatusToPending();
        
        this.orderMapper.createFull( order, userId );

    }
    
}
