package app.web.persistence.mappers;

import app.web.entities.Bom;
import app.web.exceptions.DatabaseException;
import app.web.exceptions.NoIdKeyReturnedException;
import app.web.exceptions.UnexpectedResultDbException;

import java.util.Map;


public interface BomMapper
{
    void setDataStore( DataStore dataStore );
    
    int create( Bom bom, Integer carportId ) throws DatabaseException, NoIdKeyReturnedException, UnexpectedResultDbException;
    
}
