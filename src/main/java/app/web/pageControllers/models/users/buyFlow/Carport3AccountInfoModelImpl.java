package app.web.pageControllers.models.users.buyFlow;

import app.web.entities.ContactInfo;
import app.web.exceptions.DatabaseException;
import app.web.exceptions.NoIdKeyReturnedException;
import app.web.exceptions.UnexpectedResultDbException;
import app.web.exceptions.WebInvalidInputException;
import app.web.persistence.mappers.ContactMapper;


public class Carport3AccountInfoModelImpl implements Carport3AccountInfoModel
{
    ContactMapper contactMapper;

    public Carport3AccountInfoModelImpl( ContactMapper contactMapper )
    {
        this.contactMapper = contactMapper;
    }

    public Carport3AccountInfoModelImpl() {

    }

    @Override
    public ContactInfo createContactInfo( ContactInfo contactInfo, String fullName, String address, String zip, String city, String phoneNumber, String email, String user ) throws DatabaseException, WebInvalidInputException, NoIdKeyReturnedException, UnexpectedResultDbException
    { //TODO make changes to this if we make it so being logged in will autofill form
        // Missing input
        if ( fullName == null ) {
            throw new WebInvalidInputException( "Navn ikke udfyldt" );
        }

        if ( address == null ) {
            throw new WebInvalidInputException( "Adresse ikke udfyldt" );
        }

        if ( zip == null ) {
            throw new WebInvalidInputException( "Postnr. ikke udfyldt" );
        }

        if ( city == null ) {
            throw new WebInvalidInputException( "By ikke udfyldt" );
        }

        if ( phoneNumber == null ) {
            throw new WebInvalidInputException( "Tlf. ikke udfyldt" );
        }

        if ( email == null ) {
            throw new WebInvalidInputException( "Email ikke udfyldt" );
        }

        if ( !email.contains("@") && !email.contains(".") ) {
            throw new WebInvalidInputException( "Gyldig email ikke udfyldt" );
        }

        // Hopefully valueOf works here. Decided on parseInt for userid.
        contactInfo.setFullName( fullName );
        contactInfo.setAddress( address );
        contactInfo.setZip( Integer.valueOf( zip ) );
        contactInfo.setCity( city );
        contactInfo.setPhoneNumber( Integer.valueOf( phoneNumber ) );
        contactInfo.setEmail( email );

        int userId;
        userId = Integer.parseInt( user );
        contactInfo.setUser( userId );

        this.contactMapper.create( contactInfo );

        return contactInfo;
    }

}