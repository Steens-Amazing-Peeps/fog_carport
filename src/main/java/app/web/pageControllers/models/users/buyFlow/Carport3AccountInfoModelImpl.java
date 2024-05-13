package app.web.pageControllers.models.users.buyFlow;

import app.web.constants.Config;
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
    public ContactInfo createContactInfo( String fullName, String address, Integer zip, String city, Integer phoneNumber, String email, Integer user ) throws DatabaseException, WebInvalidInputException, NoIdKeyReturnedException, UnexpectedResultDbException
    {
        ContactInfo contactInfo = new ContactInfo();
        contactInfo.setFullName( fullName );
        contactInfo.setAddress( address );
        contactInfo.setZip( zip );
        contactInfo.setCity( city );
        contactInfo.setPhoneNumber( phoneNumber );
        contactInfo.setEmail( email );
        contactInfo.setUser( user );

        this.contactMapper.create( contactInfo );

        return contactInfo;
    }

    @Override
    public ContactInfo createContactInfo( String fullName, String address, Integer zip, String city, Integer phoneNumber, String email ) throws DatabaseException, WebInvalidInputException, NoIdKeyReturnedException, UnexpectedResultDbException
    {
        return this.createContactInfo( fullName, address, zip, city, phoneNumber, email, null );
    }


}
