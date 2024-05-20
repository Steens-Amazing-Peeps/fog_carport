package app.web.pageControllers.models.users.buyFlow;

import app.web.entities.Order;
import app.web.exceptions.DatabaseException;
import app.web.exceptions.NoIdKeyReturnedException;
import app.web.exceptions.UnexpectedResultDbException;

public interface CarportBillPayUpModel
{

    void setOrderDone( Order order ) throws NoIdKeyReturnedException, UnexpectedResultDbException, DatabaseException;
}
