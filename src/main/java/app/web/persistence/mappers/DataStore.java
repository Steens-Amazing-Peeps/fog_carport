package app.web.persistence.mappers;



import app.web.exceptions.DatabaseException;
import app.web.exceptions.NoIdKeyReturnedException;
import app.web.exceptions.UnexpectedResultDbException;
import app.web.persistence.GetConnectionIf;

import java.util.Map;

public interface DataStore
{
    
    /**
     * @param connectionPool How do we connect to database? With this!
     */
    void setConnectionPool( GetConnectionIf connectionPool );
    
    /**
     * @param rawSql           SQL for this request
     * @param entity           DTO for this request
     * @param parametersForSql We replace the '?' in rawSQL with parametersForSql
     * @param dtoCreator       How to we make this DTO? By using this!
     * @return the Id of created row
     * @throws DatabaseException           If we have issues with connecting to the Database or a coding error/SQL code error happens
     * @throws UnexpectedResultDbException If we receive the wrong result (IE result-set on create) or if we affect more than 1 row
     * @throws NoIdKeyReturnedException    If there wasn't, for some reason. an id returned from database
     */
    //Default Access
    int create( String rawSql, Object entity, Object[] parametersForSql, DtoCreator dtoCreator ) throws DatabaseException, UnexpectedResultDbException, NoIdKeyReturnedException;
    
    /**
     * @param rawSql     SQL for this request
     * @param dtoCreator How to we make this DTO? By using this!
     * @return A LinkedHashMap of <Id, Dto>
     * @throws DatabaseException If we have issues with connecting to the Database or a coding error/SQL code error happens
     */
    //Default Access
    Map< Integer, ? > readAll( String rawSql, DtoCreator dtoCreator ) throws DatabaseException;
    
    /**
     * @param rawSql     SQL for this request
     * @param id         find all with this foreign key id
     * @param dtoCreator How to we make this DTO? By using this!
     * @return A LinkedHashMap of <Id, Dto>
     * @throws DatabaseException If we have issues with connecting to the Database or a coding error/SQL code error happens
     */
    //Default Access
    Map< Integer, ? > readAll( String rawSql, Integer id, DtoCreator dtoCreator ) throws DatabaseException;
    
    /**
     * @param rawSql           SQL for this request
     * @param parametersForSql We replace the '?' in rawSQL with parametersForSql
     * @param dtoCreator       How to we make this DTO? By using this!
     * @return A LinkedHashMap of <Id, Dto>
     * @throws DatabaseException If we have issues with connecting to the Database or a coding error/SQL code error happens
     */
    //Default Access
    Map< Integer, ? > readAll( String rawSql, Object[] parametersForSql, DtoCreator dtoCreator ) throws DatabaseException;
    
    /**
     * @param rawSql     SQL for this request
     * @param id         Primary key to find
     * @param dtoCreator How to we make this DTO? By using this!
     * @return The Dto
     * @throws DatabaseException If we have issues with connecting to the Database or a coding error/SQL code error happens
     */
    //Default Access
    Object readSingle( String rawSql, Integer id, DtoCreator dtoCreator ) throws DatabaseException;
    
    /**
     * @param rawSql           SQL for this request
     * @param entity           DTO for this request
     * @param parametersForSql We replace the '?' in rawSQL with parametersForSql
     * @return Amount of Affected Rows
     * @throws DatabaseException           If we have issues with connecting to the Database or a coding error/SQL code error happens
     * @throws UnexpectedResultDbException If there wasn't, for some reason. an id returned from database
     */
    //Default Access
    int update( String rawSql, Object entity, Object[] parametersForSql ) throws DatabaseException, UnexpectedResultDbException;
    
    /**
     * @param rawSql    SQL for this request
     * @param tableName Name of the Database table, for error logging purposes (AKA print to console)
     * @param id        Primary key to delete
     * @return Amount of Affected Rows
     * @throws DatabaseException           If we have issues with connecting to the Database or a coding error/SQL code error happens
     * @throws UnexpectedResultDbException If we receive the wrong result (IE result-set on create) or if we affect more than 1 row
     */
    //Default Access
    int delete( String rawSql, String tableName, Integer id ) throws DatabaseException, UnexpectedResultDbException;
    
}
