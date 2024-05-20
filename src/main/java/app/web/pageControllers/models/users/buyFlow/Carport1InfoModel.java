package app.web.pageControllers.models.users.buyFlow;

import app.web.entities.Carport;
import app.web.entities.Order;
import app.web.exceptions.EmptyInputException;
import app.web.exceptions.NumberTooLargeException;
import app.web.exceptions.NumberTooSmallException;
import app.web.exceptions.WebInvalidInputException;

public interface Carport1InfoModel
{
    Integer checkNumberAttributeValidity( String attributeInCmAsString, int minSize, int maxSize ) throws NumberTooLargeException, NumberTooSmallException, EmptyInputException;
    
}
