package app.web.persistence.mappers;

import app.web.entities.Bom;
import app.web.exceptions.DatabaseException;
import app.web.exceptions.NoIdKeyReturnedException;
import app.web.exceptions.UnexpectedResultDbException;

import java.util.Map;


public interface BomMapper
{
    void setDataStore( DataStore dataStore );
    int create( Bom bom ) throws DatabaseException, NoIdKeyReturnedException, UnexpectedResultDbException;

    Map< Integer, Bom > readAll() throws DatabaseException;

    Map< Integer, Bom > readAllByCarportId( Integer carport_id ) throws DatabaseException;

    Bom readSingle( Integer bom_id ) throws DatabaseException;

    int update( Bom bom ) throws DatabaseException, UnexpectedResultDbException;

    int delete( Integer bom_id ) throws DatabaseException, UnexpectedResultDbException;

}
