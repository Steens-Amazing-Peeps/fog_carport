package app.web.pageControllers.models.users.buyFlow;

import app.web.entities.Carport;
import app.web.entities.Order;
import app.web.exceptions.WebInvalidInputException;

public interface Carport1InfoModel
{
    
    Carport checkConfirmInfo( Carport carport, String carportHeight, String carportWidth, String carportLength ) throws WebInvalidInputException;
    
}
