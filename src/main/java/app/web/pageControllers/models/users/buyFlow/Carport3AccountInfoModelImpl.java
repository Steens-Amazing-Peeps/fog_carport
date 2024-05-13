package app.web.pageControllers.models.users.buyFlow;

import app.web.constants.Config;
import app.web.entities.ContactInfo;
import app.web.exceptions.DatabaseException;
import app.web.exceptions.NoIdKeyReturnedException;
import app.web.exceptions.UnexpectedResultDbException;
import app.web.exceptions.WebInvalidInputException;
import app.web.persistence.mappers.ContactInfoMapper;


public class Carport3AccountInfoModelImpl implements Carport3AccountInfoModel
{
    ContactMapper contactMapper;

    public Carport3AccountInfoModelImpl( ContactMapper contactMapper )
    {
        this.contactMapper = contactMapper;
    }

    @Override
    public ContactInfo createContactInfo( String fullName, String address, Integer zip, String city, Integer phoneNumber, String email, Integer user ) throws WebInvalidInputException
    {
        ContactInfo contactInfo = new ContactInfo();
        user.setEmail( email );
        user.setPassword( password );
        user.setRole( role );

        this.userMapper.create( user );

        globalUserMap.put( user.getUserId(), user );

        return user;
    }

    @Override
    public ContactInfo createContactInfo( String fullName, String address, Integer zip, String city, Integer phoneNumber, String email ) throws WebInvalidInputException
    {
        return this.createContactInfo( fullName, address, zip, city, phoneNumber, email, null );
    }


}
