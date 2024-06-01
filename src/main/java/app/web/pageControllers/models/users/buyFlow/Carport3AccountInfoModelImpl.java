package app.web.pageControllers.models.users.buyFlow;

import app.util.Validators;
import app.web.constants.Config;
import app.web.entities.AccountInfo;
import app.web.entities.Order;
import app.web.entities.User;
import app.web.exceptions.DatabaseException;
import app.web.exceptions.WebInvalidInputException;
import app.web.persistence.mappers.AccountInfoMapper;

import java.util.Map;


public class Carport3AccountInfoModelImpl implements Carport3AccountInfoModel
{
    AccountInfoMapper accountInfoMapper;
    
    
    public Carport3AccountInfoModelImpl( AccountInfoMapper accountInfoMapper )
    {
        this.accountInfoMapper = accountInfoMapper;
    }
    
    @Override
    public void getLastAccountInfo( Order order, User user )
    {
        AccountInfo accountInfo = order.getAccountInfo();
        
        if ( accountInfo == null ) {
            accountInfo = new AccountInfo();
            
            if ( user != null ) {
                AccountInfo mostRecentAccountInfo = null;
                try {
                    mostRecentAccountInfo = this.accountInfoMapper.readSingleByUserIdMostRecent( user.getUserId() );
                } catch ( DatabaseException ignored ) {
                }
                
                if ( mostRecentAccountInfo == null ) {
                    if ( accountInfo.getEmail() == null) {
                        accountInfo.setEmail( user.getEmail() );
                    }
                    
                } else {
                    accountInfo = mostRecentAccountInfo;
                }
                
            }
            
            order.setAccountInfo( accountInfo );
        }
        


    }
    @Override
    public void setFullName( AccountInfo accountInfo, String fullName ) throws WebInvalidInputException
    {
        if ( fullName == null || fullName.isBlank() ) {
            throw new WebInvalidInputException( "Navn ikke udfyldt" );
        }
        if (fullName.length() > Config.AccountInfo.MAXIMUM_FULLNAME_LENGTH){
            throw new WebInvalidInputException("Navn må maks være på 100 tegn");
        }
        
        accountInfo.setFullName( fullName );
    }
    
    @Override
    public void setAddress( AccountInfo accountInfo, String address ) throws WebInvalidInputException
    {
        if ( address == null || address.isBlank() ) {
            throw new WebInvalidInputException( "Adresse ikke udfyldt" );
        }
        if (address.length() > Config.AccountInfo.MAXIMUM_ADDRESS_LENGTH){
            throw new WebInvalidInputException("Adresse må maks være på 50 tegn");
        }
        
        accountInfo.setAddress( address );
    }
    
    @Override
    public void setZip( AccountInfo accountInfo, String zip ) throws WebInvalidInputException
    {
        if ( zip == null || zip.isBlank() ) {
            throw new WebInvalidInputException( "Postnr. ikke udfyldt" );
        }
        if (!(zip.length() == Config.AccountInfo.REQUIRED_ZIP_DK_LENGTH)){
            throw new WebInvalidInputException("Postnr. skal være på 4 cifre");
        }
        
        accountInfo.setZip( Integer.valueOf( zip ) );
    }
    
    @Override
    public void setCity( AccountInfo accountInfo, String city ) throws WebInvalidInputException
    {
        if ( city == null || city.isBlank() ) {
            throw new WebInvalidInputException( "By ikke udfyldt" );
        }
        if (city.length() > Config.AccountInfo.MAXIMUM_CITY_LENGTH){
            throw new WebInvalidInputException("By må maks være på 50 tegn");
        }
        
        accountInfo.setCity( city );
    }
    
    @Override
    public void setPhoneNumber( AccountInfo accountInfo, String phoneNumber ) throws WebInvalidInputException
    {
        if ( phoneNumber == null || phoneNumber.isBlank() ) {
            throw new WebInvalidInputException( "Tlf. ikke udfyldt" );
        }
        if (!(phoneNumber.length() == Config.AccountInfo.REQUIRED_TLF_DK_LENGTH)){
            throw new WebInvalidInputException("Tlf. skal være på 8 cifre");
        }
        
        accountInfo.setPhoneNumber( Integer.valueOf( phoneNumber ) );
    }
    
    @Override
    public void setEmail( AccountInfo accountInfo, String email ) throws WebInvalidInputException
    {
        if ( !Validators.isEmailValid( email ) ) {
            throw new WebInvalidInputException( "Email ikke udfyldt" );
        }
        
        accountInfo.setEmail( email  );
    }
    
    @Override
    public void setConsentToSpam( AccountInfo accountInfo, String consentToSpam ) throws WebInvalidInputException
    {
        if ( consentToSpam == null || consentToSpam.isBlank() ) {
            accountInfo.setConsentToSpam( false );
        }
        
        accountInfo.setConsentToSpam( true );
    }
    
//    @Override
//    public AccountInfo confirmContactInfo( AccountInfo accountInfo, String fullName, String address, String zip, String city, String phoneNumber, String email, String consentToSpam ) throws WebInvalidInputException
//    { //TODO make changes to this if we make it so being logged in will autofill form
//        // Missing input
//        if ( fullName == null || fullName.isBlank() ) {
//            throw new WebInvalidInputException( "Navn ikke udfyldt" );
//        }
//
//        if ( address == null || address.isBlank() ) {
//            throw new WebInvalidInputException( "Adresse ikke udfyldt" );
//        }
//
//        if ( zip == null || zip.isBlank() ) {
//            throw new WebInvalidInputException( "Postnr. ikke udfyldt" );
//        }
//
//        if ( city == null || city.isBlank() ) {
//            throw new WebInvalidInputException( "By ikke udfyldt" );
//        }
//
//        if ( phoneNumber == null || phoneNumber.isBlank() ) {
//            throw new WebInvalidInputException( "Tlf. ikke udfyldt" );
//        }
//
//        if ( email == null || email.isBlank() ) {
//            throw new WebInvalidInputException( "Email ikke udfyldt" );
//        }
//
//        if ( !Validators.isEmailValid( email ) ) {
//            throw new WebInvalidInputException( "Gyldig email ikke udfyldt" );
//        }
//
//        // Hopefully valueOf works here. Decided on parseInt for userid.
//        accountInfo.setFullName( fullName );
//        accountInfo.setAddress( address );
//        accountInfo.setZip( Integer.valueOf( zip ) );
//        accountInfo.setCity( city );
//        accountInfo.setPhoneNumber( Integer.valueOf( phoneNumber ) );
//        accountInfo.setEmail( email );
//
//        if ( consentToSpam != null && !consentToSpam.isBlank() ) {
//            accountInfo.setConsentToSpam( true );
//        } else {
//            accountInfo.setConsentToSpam( false );
//        }
//
//        return accountInfo;
//    }
    
}