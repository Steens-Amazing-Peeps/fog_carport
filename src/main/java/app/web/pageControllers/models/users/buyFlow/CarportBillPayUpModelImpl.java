package app.web.pageControllers.models.users.buyFlow;

import app.web.entities.Order;
import app.web.exceptions.DatabaseException;
import app.web.exceptions.NoIdKeyReturnedException;
import app.web.exceptions.UnexpectedResultDbException;
import app.web.persistence.mappers.OrderMapper;

public class CarportBillPayUpModelImpl implements CarportBillPayUpModel
{
    OrderMapper orderMapper;

    public CarportBillPayUpModelImpl( OrderMapper orderMapper )
    {
        this.orderMapper = orderMapper;
    }

    @Override
    public void setOrderDone( Order order ) throws NoIdKeyReturnedException, UnexpectedResultDbException, DatabaseException
    {
        if ( order.getStatus().isEmpty() ) {
            throw new DatabaseException("Order status error");
        }

        if ( order.getStatus().equals("pending") ) {
            order.setDateFinishedToNow();
            order.setStatusToFinished();
        }

    }
}
