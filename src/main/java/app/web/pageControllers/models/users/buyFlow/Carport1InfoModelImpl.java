package app.web.pageControllers.models.users.buyFlow;

import app.web.constants.Config;
import app.web.entities.Carport;
import app.web.exceptions.NumberTooLargeException;
import app.web.exceptions.NumberTooSmallException;
import app.web.exceptions.WebInvalidInputException;
import app.web.exceptions.carport1Info.CarportHeightException;
import app.web.exceptions.carport1Info.CarportLengthException;
import app.web.exceptions.carport1Info.CarportWidthException;

public class Carport1InfoModelImpl implements Carport1InfoModel
{
    
    @Override
    public Carport checkConfirmInfo( Carport carport, String carportHeight, String carportWidth, String carportLength ) throws WebInvalidInputException, CarportWidthException, CarportLengthException, CarportHeightException
    {
        int res;
        
        if ( carport == null ) {
            carport = new Carport();
        }
        
        //Height
        try {
            res = getNumberAttribute( carportHeight, null, Config.Carport.MINIMUM_HEIGHT_IN_MM, Config.Carport.MAXIMUM_HEIGHT_IN_MM );
            carport.setHeight( res );
        } catch ( NumberTooSmallException e ) {
            throw new CarportHeightException( "Højden er for lav, minimum højden er " + ( Config.Carport.MINIMUM_HEIGHT_IN_MM / 10 ) + " cm" );
        } catch ( NumberTooLargeException e ) {
            throw new CarportHeightException( "Højden er for høj, maximum højden er " + ( Config.Carport.MAXIMUM_HEIGHT_IN_MM / 10 ) + " cm" );
        }  catch ( WebInvalidInputException e ) {
            throw new CarportHeightException( "Højden var ikke indtastet" );
        }
        
        //Length
        try {
            res = getNumberAttribute( carportLength, null, Config.Carport.MINIMUM_LENGTH_IN_MM, Config.Carport.MAXIMUM_LENGTH_IN_MM );
            carport.setLength( res );
        } catch ( NumberTooSmallException e ) {
            throw new CarportLengthException( "Længden er for kort, minimum længden er " + ( Config.Carport.MINIMUM_LENGTH_IN_MM / 10 ) + " cm" );
        } catch ( NumberTooLargeException e ) {
            throw new CarportLengthException( "Længden er for lang, maximum længden er " + ( Config.Carport.MAXIMUM_LENGTH_IN_MM / 10 ) + " cm" );
        }  catch ( WebInvalidInputException e ) {
            throw new CarportLengthException( "Længden var ikke indtastet" );
        }
        
        //Width
        try {
            res = getNumberAttribute( carportWidth, null, Config.Carport.MINIMUM_WIDTH_IN_MM, Config.Carport.MAXIMUM_WIDTH_IN_MM );
            carport.setWidth( res );
        } catch ( NumberTooSmallException e ) {
            throw new CarportWidthException( "Bredden er for smal, minimum bredden er " + ( Config.Carport.MINIMUM_WIDTH_IN_MM / 10 ) + " cm" );
        } catch ( NumberTooLargeException e ) {
            throw new CarportWidthException( "Bredden er for smal, maximum bredden er " + ( Config.Carport.MAXIMUM_WIDTH_IN_MM / 10 ) + " cm" );
        }  catch ( WebInvalidInputException e ) {
            throw new CarportWidthException( "Bredden var ikke indtastet" );
        }
        
        return carport;
    }
    
    @Override
    public Carport checkBackInfo( Carport carport, String carportHeight, String carportWidth, String carportLength )
    {
        int res;
        
        if ( carport == null ) {
            carport = new Carport();
        }
        
        //Height
        try {
            res = getNumberAttribute( carportHeight, carport.getHeight(), Config.Carport.MINIMUM_HEIGHT_IN_MM, Config.Carport.MAXIMUM_HEIGHT_IN_MM );
            carport.setHeight( res );
        } catch ( NumberTooSmallException e ) {
            //We don't care, we are just trying to save some values.
        } catch ( NumberTooLargeException e ) {
            //We don't care, we are just trying to save some values.
        } catch ( WebInvalidInputException e ) {
            //We don't care, we are just trying to save some values.
        }
        
        //Length
        try {
            res = getNumberAttribute( carportLength, carport.getLength(), Config.Carport.MINIMUM_LENGTH_IN_MM, Config.Carport.MAXIMUM_LENGTH_IN_MM );
            carport.setLength( res );
        } catch ( NumberTooSmallException e ) {
            //We don't care, we are just trying to save some values.
        } catch ( NumberTooLargeException e ) {
            //We don't care, we are just trying to save some values.
        } catch ( WebInvalidInputException e ) {
            //We don't care, we are just trying to save some values.
        }
        
        //Width
        try {
            res = getNumberAttribute( carportWidth, carport.getWidth(), Config.Carport.MINIMUM_WIDTH_IN_MM, Config.Carport.MAXIMUM_WIDTH_IN_MM );
            carport.setWidth( res );
        } catch ( NumberTooSmallException e ) {
            //We don't care, we are just trying to save some values.
        } catch ( NumberTooLargeException e ) {
            //We don't care, we are just trying to save some values.
        } catch ( WebInvalidInputException e ) {
            //We don't care, we are just trying to save some values.
        }
        
        return carport;
    }
    
    private int getNumberAttribute( String attributeInCmAsString, Integer backupAttribute, int minSize, int maxSize ) throws NumberTooLargeException, NumberTooSmallException, WebInvalidInputException
    {
        boolean hasBackupAttribute = true;

        
        if ( backupAttribute == null ) {
            hasBackupAttribute = false;
        }
        
        try {
            int attributeInCm = Integer.parseInt( attributeInCmAsString );
            
            int attributeInMm = attributeInCm * 10;
            
            if ( attributeInMm < minSize ) {
                throw new NumberTooSmallException( "" );
            }
            
            if ( attributeInMm > maxSize ) {
                throw new NumberTooLargeException( "" );
            }
            
            return attributeInMm;
            
        } catch ( RuntimeException | NumberTooSmallException | NumberTooLargeException e ) {
            if ( hasBackupAttribute ) {
                return backupAttribute;
            }
            throw new WebInvalidInputException(e.getMessage(), e.getMessage());
        }
    }
    
}
