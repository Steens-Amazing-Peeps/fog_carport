package app.web.persistence.mappers;

import app.web.entities.AccountInfo;
import app.web.exceptions.DatabaseException;
import app.web.exceptions.NoIdKeyReturnedException;
import app.web.exceptions.UnexpectedResultDbException;

import java.util.Map;


public interface AccountInfoMapper
{
    void setDataStore( DataStore dataStore );
    
    int create( AccountInfo accountInfo, Integer userId ) throws DatabaseException, NoIdKeyReturnedException, UnexpectedResultDbException;
    
    int create( AccountInfo accountInfo ) throws DatabaseException, NoIdKeyReturnedException, UnexpectedResultDbException;
    
    Map< Integer, AccountInfo > readAll() throws DatabaseException;
    
    Map< Integer, AccountInfo > readAllByUserId( Integer user_id ) throws DatabaseException;
    
    AccountInfo readSingle( Integer contact_id ) throws DatabaseException;
    
    int update( AccountInfo accountInfo, Integer userId ) throws DatabaseException, UnexpectedResultDbException;
    
    int delete( Integer contact_id ) throws DatabaseException, UnexpectedResultDbException;
    
}
