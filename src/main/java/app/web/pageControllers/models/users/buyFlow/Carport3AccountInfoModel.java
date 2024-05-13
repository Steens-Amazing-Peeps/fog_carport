package app.web.pageControllers.models.users.buyFlow;

import app.web.entities.ContactInfo;
import app.web.exceptions.DatabaseException;
import app.web.exceptions.NoIdKeyReturnedException;
import app.web.exceptions.UnexpectedResultDbException;
import app.web.exceptions.WebInvalidInputException;

public interface Carport3AccountInfoModel
{
    ContactInfo createContactInfo( String fullName, String address, Integer zip, String city, Integer phoneNumber, String email, Integer user ) throws DatabaseException, WebInvalidInputException, UnexpectedResultDbException, NoIdKeyReturnedException;

}
