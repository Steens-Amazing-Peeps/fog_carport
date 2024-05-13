package app.web.persistence.mappers;

import app.web.entities.ContactInfo;
import app.web.exceptions.DatabaseException;
import app.web.exceptions.NoIdKeyReturnedException;
import app.web.exceptions.UnexpectedResultDbException;

import java.util.Map;


public interface ContactMapper
{
    void setDataStore( DataStore dataStore );
    int create( ContactInfo contactInfo ) throws DatabaseException, NoIdKeyReturnedException, UnexpectedResultDbException;

    Map< Integer, ContactInfo > readAll() throws DatabaseException;

    Map< Integer, ContactInfo > readAllByUserId( Integer user_id ) throws DatabaseException;

    ContactInfo readSingle( Integer contact_id ) throws DatabaseException;

    int update( ContactInfo contactInfo ) throws DatabaseException, UnexpectedResultDbException;

    int delete( Integer contact_id ) throws DatabaseException, UnexpectedResultDbException;

}
