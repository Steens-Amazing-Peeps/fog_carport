package app.web.pageControllers.models.users.buyFlow;

import app.web.entities.Order;
import app.web.exceptions.DatabaseException;
import app.web.exceptions.NoIdKeyReturnedException;
import app.web.exceptions.UnexpectedResultDbException;
import app.web.persistence.mappers.*;

public class Carport4ReviewAndConfirmModelImpl implements Carport4ReviewAndConfirmModel
{
    OrderMapper orderMapper;
    CarportMapper carportMapper;
    BomMapper bomMapper;
    ContactMapper contactMapper;
    
    public Carport4ReviewAndConfirmModelImpl( OrderMapper orderMapper, CarportMapper carportMapper, BomMapper bomMapper, ContactMapper contactMapper )
    {
        this.orderMapper = orderMapper;
        this.carportMapper = carportMapper;
        this.bomMapper = bomMapper;
        this.contactMapper = contactMapper;
    }
    
    @Override
    public void addNewOrder( Order order ) throws NoIdKeyReturnedException, UnexpectedResultDbException, DatabaseException
    {
//        this.orderMapper.create( order );
//        this.carportMapper.create( order.getCarport() );
//        this.bomMapper.create( order.getCarport().getBom() );
//        this.contactMapper.create( order.getAccountInfo() );
    }
    
}
