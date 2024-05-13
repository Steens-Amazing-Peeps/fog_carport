package app.web.pageControllers.models.users.buyFlow;

import app.web.entities.Carport;
import app.web.entities.Order;
import app.web.exceptions.WebInvalidInputException;
import app.web.exceptions.carport1Info.CarportHeightException;
import app.web.exceptions.carport1Info.CarportLengthException;
import app.web.exceptions.carport1Info.CarportWidthException;

public interface Carport1InfoModel
{
    
    Carport checkConfirmInfo( Carport carport, String carportHeight, String carportWidth, String carportLength ) throws WebInvalidInputException, CarportWidthException, CarportLengthException, CarportHeightException;
    
    Carport checkBackInfo( Carport carport, String carportHeight, String carportWidth, String carportLength );
    
}
