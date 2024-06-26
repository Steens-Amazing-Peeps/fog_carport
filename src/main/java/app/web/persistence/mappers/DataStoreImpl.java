package app.web.persistence.mappers;


import app.web.exceptions.DatabaseException;
import app.web.exceptions.NoIdKeyReturnedException;
import app.web.exceptions.UnexpectedResultDbException;
import app.web.persistence.GetConnectionIf;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.Map;

/**
 * <p>
 * All the shared crud operations
 * Security needs to be tight on this thing, it accepts SQL in string form to send to the database
 */
//TODO: Make Security 'tight'
public final class DataStoreImpl implements DataStore
{
    private GetConnectionIf connectionPool = null;
    
    public DataStoreImpl( GetConnectionIf connectionPool )
    {
        this.setConnectionPool( connectionPool );
    }
    
    
    /**
     * @param connectionPool How do we connect to database? With this!
     */
    @Override
    public void setConnectionPool( GetConnectionIf connectionPool )
    {
        if ( this.connectionPool == null ) {
            this.connectionPool = connectionPool;
        }
    }
    
    /**
     * @param rawSql           SQL for this request
     * @param entity           Entity for this request
     * @param parametersForSql We replace the '?' in rawSQL with parametersForSql
     * @param entityCreator       How to we make this Entity? By using this!
     * @return Amount of Affected Rows
     * @throws DatabaseException           If we have issues with connecting to the Database or a coding error/SQL code error happens
     * @throws UnexpectedResultDbException If we receive the wrong result (IE result-set on create) or if we affect more than 1 row
     * @throws NoIdKeyReturnedException    If there wasn't, for some reason. an id returned from database
     */
    //Default Access
    @Override
    public int create( String rawSql, Object entity, Object[] parametersForSql, EntityCreator entityCreator ) throws DatabaseException, UnexpectedResultDbException, NoIdKeyReturnedException
    { //TODO: Make SQL safe, somehow
        String checkedSql = checkSql( rawSql, parametersForSql.length );
        
        try ( Connection connection = this.connectionPool.getConnection() ) {
            try ( PreparedStatement ps = connection.prepareStatement( checkedSql, Statement.RETURN_GENERATED_KEYS ) ) {
                for ( int i = 0; i < parametersForSql.length; i++ ) {
//                    if ( parametersForSql[ i ] instanceof Date ) {
//                        ps.setDate( i + 1, new java.sql.Date( ( ( Date ) parametersForSql[ i ] ).getTime() ) );
//
//                    } else {
                        ps.setObject( i + 1, parametersForSql[ i ] );
//                    }
                }
                
                boolean shouldBeFalse = ps.execute();
                
                
                if ( shouldBeFalse ) {
                    throw new UnexpectedResultDbException( "Database Fejl: Under indsætning af data til databasen, returnerede uforventet data", "Database Error: " + "at Create: " + "Insert CRUD had a return set for Entity=" + entity );
                }
                
                int rowsAdded = ps.getUpdateCount();

//            if ( rowsAdded == 0 ) {
//                throw new UnexpectedResultDbException( Lang_Crud.createErrorUser_noRowsAdded(), Lang_Crud.createErrorSys_noRowsAdded( entity ) );
//            }
                
//                if ( rowsAdded > 1 ) {
//                    throw new UnexpectedResultDbException( "Database Fejl: Under indsætning af data til databasen, flere kopier indsat", "Database Error: " + "at Create: " + "More than 1 row affected for Entity=" + entity );
//                }
                
                ResultSet keySet = ps.getGeneratedKeys();
                
                if ( !keySet.next() ) {
                    throw new NoIdKeyReturnedException( "Database Fejl: Kunne ikke skaffe det nye ID fra databasen", "Database Error: " + "at Create: " + "Failed to retrieve generated key (ID) for Entity=" + entity );
                }
                
                int resKey = keySet.getInt( 1 );
                entityCreator.setId( entity, resKey );
                System.out.println( "Database: Added row for Entity=" + entity );
                
                return rowsAdded;
            }
            
        } catch ( SQLException e ) {
            e.printStackTrace();
            throw new DatabaseException( "Fata database fejl, er Databasen nede?", e.getMessage() );
        }
        
    }
    
    /**
     * @param rawSql     SQL for this request
     * @param entityCreator How to we make this Entity? By using this!
     * @return A LinkedHashMap of <Id, Entity>
     * @throws DatabaseException If we have issues with connecting to the Database or a coding error/SQL code error happens
     */
    //Default Access
    @Override
    public Map< Integer, ? > readAll( String rawSql, EntityCreator entityCreator ) throws DatabaseException
    { //TODO: Make SQL safe, somehow
        return this.readAll( rawSql, new Object[ 0 ], entityCreator );
    }
    
    /**
     * @param rawSql     SQL for this request
     * @param id         find all with this foreign key id
     * @param entityCreator How to we make this Entity? By using this!
     * @return A LinkedHashMap of <Id, Entity>
     * @throws DatabaseException If we have issues with connecting to the Database or a coding error/SQL code error happens
     */
    //Default Access
    @Override
    public Map< Integer, ? > readAll( String rawSql, Integer id, EntityCreator entityCreator ) throws DatabaseException
    {
        return this.readAll( rawSql, new Object[]{ id }, entityCreator );
    }
    
    /**
     * @param rawSql           SQL for this request
     * @param parametersForSql We replace the '?' in rawSQL with parametersForSql
     * @param entityCreator       How to we make this Entity? By using this!
     * @return A LinkedHashMap of <Id, Entity>
     * @throws DatabaseException If we have issues with connecting to the Database or a coding error/SQL code error happens
     */
    //Default Access
    @Override
    public Map< Integer, ? > readAll( String rawSql, Object[] parametersForSql, EntityCreator entityCreator ) throws DatabaseException
    { //TODO: Make SQL safe, somehow
        String checkedSql = checkSql( rawSql, parametersForSql.length );
        
        Map< Integer, ? > resMap;
        
        try ( Connection connection = this.connectionPool.getConnection() ) {
            try ( PreparedStatement ps = connection.prepareStatement( checkedSql, Statement.RETURN_GENERATED_KEYS ) ) {
                for ( int i = 0; i < parametersForSql.length; i++ ) {
                    ps.setObject( i + 1, parametersForSql[ i ] );
                }
                
                ResultSet rs = ps.executeQuery();
                
                resMap = entityCreator.createEntityMultiple( rs );
                
            }
            
        } catch ( SQLException e ) {
            e.printStackTrace();
            throw new DatabaseException( "Fatal database fejl, er Databasen nede?", e.getMessage() );
        }
        
        return resMap;
    }
    
    /**
     * @param rawSql     SQL for this request
     * @param id         Primary key to find
     * @param entityCreator How to we make this Entity? By using this!
     * @return The Entity
     * @throws DatabaseException If we have issues with connecting to the Database or a coding error/SQL code error happens
     */
    //Default Access
    @Override
    public Object readSingle( String rawSql, Integer id, EntityCreator entityCreator ) throws DatabaseException
    { //TODO: Make SQL safe, somehow
        String checkedSql = checkSql( rawSql, 1 );
        
        Object resObject;
        
        try ( Connection connection = this.connectionPool.getConnection() ) {
            try ( PreparedStatement ps = connection.prepareStatement( checkedSql, Statement.RETURN_GENERATED_KEYS ) ) {
                ps.setInt( 1, id );
                
                ResultSet rs = ps.executeQuery();
                boolean hasNext = rs.next();
                
                if ( !hasNext ) {
                    return null;
                }
                
                resObject = entityCreator.createEntity( rs );
            }
            
        } catch ( SQLException e ) {
            e.printStackTrace();
            throw new DatabaseException( "Fatal database fejl, er Databasen nede?", e.getMessage() );
        }
        
        return resObject;
    }
    
    
    /**
     * @param rawSql           SQL for this request
     * @param entity           Entity for this request
     * @param parametersForSql We replace the '?' in rawSQL with parametersForSql
     * @return Amount of Affected Rows
     * @throws DatabaseException           If we have issues with connecting to the Database or a coding error/SQL code error happens
     * @throws UnexpectedResultDbException If there wasn't, for some reason. an id returned from database
     */
    //Default Access
    @Override
    public int update( String rawSql, Object entity, Object[] parametersForSql ) throws DatabaseException, UnexpectedResultDbException
    { //TODO: Make SQL safe, somehow
        String checkedSql = checkSql( rawSql, parametersForSql.length );
        
        int resRowsAffected;
        
        try ( Connection connection = this.connectionPool.getConnection() ) {
            try ( PreparedStatement ps = connection.prepareStatement( checkedSql, Statement.RETURN_GENERATED_KEYS ) ) {
                for ( int i = 0; i < parametersForSql.length; i++ ) {
//                    if ( parametersForSql[ i ] instanceof Date ) {
//                        ps.setDate( i + 1, new java.sql.Date( ( ( Date ) parametersForSql[ i ] ).getTime() ) );
//
//                    } else {
                        ps.setObject( i + 1, parametersForSql[ i ] );
//                    }
                }
                
                resRowsAffected = ps.executeUpdate();

//            if ( rowsAffected == 0 ) {
//                throw new UnexpectedResultDbException( Lang_Crud.updateErrorUser_noRowsAffected(), Lang_Crud.updateErrorSys_noRowsAffected( entity ) );
//            }

//                if ( resRowsAffected > 1 ) {
//                    throw new UnexpectedResultDbException( Lang_Crud.updateErrorUser_tooManyRowsAffected(), Lang_Crud.updateErrorSys_tooManyRowsAffected( entity ) );
//                }
                
                System.out.println( "Database: Updated Entity=" + entity );
                
                
                
            }
            
        } catch ( SQLException e ) {
            e.printStackTrace();
            throw new DatabaseException( "Fatal database fejl, er Databasen nede?", e.getMessage() );
        }
        
        return resRowsAffected;
    }
    
    /**
     * @param rawSql    SQL for this request
     * @param tableName Name of the Database table, for error logging purposes (AKA print to console)
     * @param id        Primary key to delete
     * @return Amount of Affected Rows
     * @throws DatabaseException           If we have issues with connecting to the Database or a coding error/SQL code error happens
     * @throws UnexpectedResultDbException If we receive the wrong result (IE result-set on create) or if we affect more than 1 row
     */
    //Default Access
    @Override
    public int delete( String rawSql, String tableName, Integer id ) throws DatabaseException, UnexpectedResultDbException
    { //TODO: Make SQL safe, somehow
        String checkedSql = checkSql( rawSql, 1 );
        
        int resRowsAffected;
        
        try ( Connection connection = this.connectionPool.getConnection() ) {
            try ( PreparedStatement ps = connection.prepareStatement( checkedSql, Statement.RETURN_GENERATED_KEYS ) ) {
                ps.setInt( 1, id );
                
                resRowsAffected = ps.executeUpdate();


//            if ( rowsAffected == 0 ) {
//                throw new UnexpectedResultDbException( Lang_Crud.deleteErrorUser_noRowsAffected(), Lang_Crud.deleteErrorSys_noRowsAffected( tableName, id ) );
//            }

//                if ( resRowsAffected > 1 ) {
//                    throw new UnexpectedResultDbException( Lang_Crud.deleteErrorUser_tooManyRowsAffected(), Lang_Crud.deleteErrorSys_tooManyRowsAffected( tableName, id ) );
//                }
                
                System.out.println( "Database: Deleted '" + tableName + "' row with id=" + id );
                
            }
            
        } catch ( SQLException e ) {
            e.printStackTrace();
            throw new DatabaseException( "Fatal database fejl, er Databasen nede?", e.getMessage() );
        }
        
        return resRowsAffected;
    }
    
    
    
    /**
     * In an attempt to catch errors, we do some basic checks
     *
     * @param rawSql               SQL to check
     * @param parametersForSqlSize Expect '?' in raw SQL
     * @return return rawSql, but at least we checked some of it
     * @throws DatabaseException If the SQL is not valid
     */
    @NotNull
    private static String checkSql( String rawSql, Integer parametersForSqlSize ) throws DatabaseException
    {
        char setableValue = '?';
        int amountOfSetableValues = 0;
        
        for ( int i = 0; i < rawSql.length(); i++ ) {
            if ( rawSql.charAt( i ) == setableValue ) {
                amountOfSetableValues++;
            }
        }
        
        if ( amountOfSetableValues != parametersForSqlSize ) {
            throw new DatabaseException( "Database Fejl: Fejlagtig SQL kode, gerne contact IT-Afdelingen om fejlen", "Database Error: " + "at update: " + "SQL invalid, SQL=" + rawSql );
        }
        
        //If it passes the above, it is valid enough
        String checkedSql = rawSql;
        return checkedSql;
    }
    
}
