package app.web.pageControllers.models.users.buyFlow;

import app.util.MetricConversion;
import app.util.UnitConversion;
import app.web.constants.Config;
import app.web.entities.Carport;
import app.web.exceptions.EmptyInputException;
import app.web.exceptions.NumberTooLargeException;
import app.web.exceptions.NumberTooSmallException;
import app.web.exceptions.WebInvalidInputException;


import java.math.BigDecimal;
import java.math.RoundingMode;

public class Carport1InfoModelImpl implements Carport1InfoModel
{
    @Override
    public Integer checkNumberAttributeValidity( String attributeInCmAsString, int minSize, int maxSize ) throws NumberTooLargeException, NumberTooSmallException, EmptyInputException
    {
        try {
            if ( attributeInCmAsString == null || attributeInCmAsString.isEmpty() || attributeInCmAsString.isBlank() ) {
                throw new EmptyInputException("");
            }
            BigDecimal attributeInM = new BigDecimal( attributeInCmAsString );
            attributeInM = attributeInM.setScale( MetricConversion.COMMA_DIGITS_IN_M, RoundingMode.HALF_UP );
            
            int attributeInMm = MetricConversion.mToMm( attributeInM );
            
            if ( attributeInMm < minSize ) {
                throw new NumberTooSmallException( "" );
            }
            
            if ( attributeInMm > maxSize ) {
                throw new NumberTooLargeException( "" );
            }
            
            return attributeInMm;
            
        } catch ( NumberTooSmallException | NumberTooLargeException | EmptyInputException | RuntimeException e ) {
            throw e;
        }
    }
    
}
