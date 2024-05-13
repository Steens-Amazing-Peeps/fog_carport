package app.web.pageControllers.models.users.buyFlow;

import app.web.constants.Config;
import app.web.entities.Carport;
import app.web.entities.Order;
import app.web.exceptions.WebInvalidInputException;

public class Carport1InfoModelImpl implements Carport1InfoModel
{
    
    @Override
    public Carport checkConfirmInfo( Carport carport, String carportHeight, String carportWidth, String carportLength ) throws WebInvalidInputException
    {
        boolean isOrderHeightValid = false;
        boolean isOrderLengthValid = false;
        boolean isOrderWidthValid = false;
        
        if ( carport == null ) {
            carport = new Carport();
            
        }
//        else {
//            //Do we have a backup value that is valid?
//            if ( order.getCarport().getHeight() != null ) {
//                isOrderHeightValid = true;
//            }
//
//            if ( order.getCarport().getLength() != null ) {
//                isOrderLengthValid = true;
//            }
//
//            if ( order.getCarport().getWidth() != null ) {
//                isOrderWidthValid = true;
//            }
//        }
        
        //Is it null?
        
        if ( !isOrderHeightValid && carportHeight == null ) {
            throw new WebInvalidInputException( "Carportens højde var ikke sat" );
        }
        
        if ( !isOrderLengthValid && carportLength == null ) {
            throw new WebInvalidInputException( "Carportens længde var ikke sat" );
        }
        
        if ( !isOrderWidthValid && carportWidth == null ) {
            throw new WebInvalidInputException( "Carportens bredde var ikke sat" );
        }
        
        //Is it a number?
        
        boolean isNewHeight = true;
        int height;
        try {
            height = Integer.parseInt( carportHeight );
        } catch ( RuntimeException e ) {
            if ( isOrderHeightValid ) {
                height = carport.getHeight();
                isNewHeight = false;
            } else {
                throw e;
            }
        }
        
        boolean isNewLength = true;
        int length;
        try {
            length = Integer.parseInt( carportLength );
        } catch ( RuntimeException e ) {
            if ( isOrderLengthValid ) {
                length = carport.getLength();
                isNewLength = false;
            } else {
                throw e;
            }
            
        }
        
        boolean isNewWidth = true;
        int width;
        try {
            width = Integer.parseInt( carportWidth );
        } catch ( RuntimeException e ) {
            if ( isOrderWidthValid ) {
                width = carport.getWidth();
                isNewWidth = false;
            } else {
                throw e;
            }
        }
        
        //Is the new stuff within bounds?
        
        if ( isNewHeight ) {
            try {
                if ( height < Config.Carport.MINIMUM_HEIGHT_IN_MM ) {
                    throw new WebInvalidInputException( "Højden er for lav, minimum højden er " + ( Config.Carport.MINIMUM_HEIGHT_IN_MM / 10 ) + " cm" );
                }
                
                if ( height > Config.Carport.MAXIMUM_HEIGHT_IN_MM ) {
                    throw new WebInvalidInputException( "Højden er for høj, maximum højden er " + ( Config.Carport.MAXIMUM_HEIGHT_IN_MM / 10 ) + " cm" );
                }
                
                carport.setHeight( height );
                
            } catch ( WebInvalidInputException e ) {
                if ( !isOrderHeightValid ) {
                    throw e;
                }
            }
        }
        
        if ( isNewLength ) {
            try {
                if ( length < Config.Carport.MINIMUM_LENGTH_IN_MM ) {
                    throw new WebInvalidInputException( "Længden er for kort, minimum længden er " + ( Config.Carport.MINIMUM_LENGTH_IN_MM / 10 ) + " cm" );
                }
                
                if ( length > Config.Carport.MAXIMUM_LENGTH_IN_MM ) {
                    throw new WebInvalidInputException( "Længden er for lang, maximum længden er " + ( Config.Carport.MAXIMUM_LENGTH_IN_MM / 10 ) + " cm" );
                }
                
                carport.setLength( length );
                
            } catch ( WebInvalidInputException e ) {
                if ( !isOrderLengthValid ) {
                    throw e;
                }
            }
        }
        
        if ( isNewWidth ) {
            try {
                if ( width < Config.Carport.MINIMUM_WIDTH_IN_MM ) {
                    throw new WebInvalidInputException( "Bredden er for smal, minimum bredden er " + ( Config.Carport.MINIMUM_WIDTH_IN_MM / 10 ) + " cm" );
                }
                
                if ( width > Config.Carport.MAXIMUM_WIDTH_IN_MM ) {
                    throw new WebInvalidInputException( "Bredden er for smal, maximum bredden er " + ( Config.Carport.MAXIMUM_WIDTH_IN_MM / 10 ) + " cm" );
                }
                
                carport.setWidth( width );
                
            } catch ( WebInvalidInputException e ) {
                if ( !isOrderWidthValid ) {
                    throw e;
                }
            }
        }
        
        
        return carport;
    }
    
}
