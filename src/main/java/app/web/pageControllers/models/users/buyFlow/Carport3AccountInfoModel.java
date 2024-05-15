package app.web.pageControllers.models.users.buyFlow;

import app.web.entities.AccountInfo;
import app.web.exceptions.DatabaseException;
import app.web.exceptions.NoIdKeyReturnedException;
import app.web.exceptions.UnexpectedResultDbException;
import app.web.exceptions.WebInvalidInputException;

public interface Carport3AccountInfoModel
{
    void setFullName( AccountInfo accountInfo, String fullName ) throws WebInvalidInputException;
    
    void setAddress( AccountInfo accountInfo, String address ) throws WebInvalidInputException;
    
    void setZip( AccountInfo accountInfo, String Zip ) throws WebInvalidInputException;
    
    void setCity( AccountInfo accountInfo, String city ) throws WebInvalidInputException;
    
    void setPhoneNumber( AccountInfo accountInfo, String phoneNumber ) throws WebInvalidInputException;
    
    void setEmail( AccountInfo accountInfo, String email ) throws WebInvalidInputException;
    
    void setConsentToSpam( AccountInfo accountInfo, String consentToSpam ) throws WebInvalidInputException;
    
}
