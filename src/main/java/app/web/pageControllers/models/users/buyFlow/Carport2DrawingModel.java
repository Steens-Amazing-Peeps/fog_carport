package app.web.pageControllers.models.users.buyFlow;


import app.web.entities.Carport;
import app.web.exceptions.WebInvalidInputException;

public interface Carport2DrawingModel
{
    String drawCarport (Carport carport) throws WebInvalidInputException;

}
