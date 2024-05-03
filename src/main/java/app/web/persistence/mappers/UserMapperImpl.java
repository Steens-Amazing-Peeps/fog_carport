package app.web.persistence.mappers;



import app.web.constants.Config;
import app.web.entities.User;
import app.web.exceptions.DatabaseException;
import app.web.exceptions.NoIdKeyReturnedException;
import app.web.exceptions.UnexpectedResultDbException;

import java.sql.*;
import java.util.*;


public final class UserMapperImpl implements UserMapper
{
    
    
    private DataStore dataStore = null;
    private static final EntityCreatorImpl ENTITY_CREATOR = new EntityCreatorImpl();
    
    
    public UserMapperImpl( DataStore dataStore )
    {
        this.setDataStore( dataStore );
    }
    
    @Override
    public void setDataStore( DataStore dataStore )
    {
        this.dataStore = dataStore;
    }
    
    @Override
    public int create( User user ) throws DatabaseException, NoIdKeyReturnedException, UnexpectedResultDbException
    {
        if ( user.getRole() == null ) {
            user.setRole( Config.User.DEFAULT_USER_ROLE );
        }
        
        
        String sql =
                "INSERT INTO public.user " +
                "   ( email, password, role) " +
                "VALUES " +
                "   (?, ?, ?);";
        
        Object[] parametersForSql = new Object[ 3 ];
        parametersForSql[ 0 ] = user.getEmail();
        parametersForSql[ 1 ] = user.getPassword();
        parametersForSql[ 2 ] = user.getRole();
        
        return this.dataStore.create( sql, user, parametersForSql, ENTITY_CREATOR );
    }
    
    @Override
    public Map< Integer, User > readAll() throws DatabaseException
    {
        String sql =
                "SELECT " +
                "   * " +
                "FROM " +
                "   public.user " +
                "ORDER BY " +
                "   user_id;";
        
        
        return ( Map< Integer, User > ) this.dataStore.readAll( sql, ENTITY_CREATOR );
    }
    
    @Override
    public Map< Integer, User > readAllByEmail( String email ) throws DatabaseException
    {
        String sql =
                "SELECT " +
                "   * " +
                "FROM " +
                "   public.user " +
                "WHERE " +
                "   email = ?;";
        
        return ( Map< Integer, User > ) this.dataStore.readAll( sql, new Object[]{ email }, ENTITY_CREATOR );
    }
    
    @Override
    public User readSingle( Integer id ) throws DatabaseException
    {
        String sql =
                "SELECT " +
                "   * " +
                "FROM " +
                "   public.user " +
                "WHERE " +
                "   user_id = ?;";
        
        return ( User ) this.dataStore.readSingle( sql, id, ENTITY_CREATOR );
    }
    
    
    
    @Override
    public int update( User user ) throws DatabaseException, UnexpectedResultDbException
    {
        String sql =
                "UPDATE public.user " +
                "SET email = ?, password = ?, role = ? " +
                "WHERE user_id = ?;";
        
        Object[] parametersForSql = new Object[ 4 ];
        parametersForSql[ 0 ] = user.getEmail();
        parametersForSql[ 1 ] = user.getPassword();
        parametersForSql[ 2 ] = user.getRole();
        parametersForSql[ 3 ] = user.getUserId();
        
        
        return this.dataStore.update( sql, user, parametersForSql );
    }
    
    @Override
    public int delete( Integer id ) throws DatabaseException, UnexpectedResultDbException
    {
        String sql =
                "DELETE FROM public.user " +
                "WHERE user_id = ?;";
        
        return this.dataStore.delete( sql, "user", id );
    }
    
    
    
    
    
    
    private static class EntityCreatorImpl implements EntityCreator
    {
        
        private EntityCreatorImpl()
        {
            
        }
        
        @Override
        public Object createEntity( ResultSet rs ) throws SQLException
        {
            User user;
            
            user = new User();
            user.setUserId( rs.getInt( "user_id" ) );
            user.setEmail( rs.getString( "email" ) );
            user.setPassword( rs.getString( "password" ) );
            user.setRole( rs.getString( "role" ) );
            
            return user;
            
        }
        
        @Override
        public Map< Integer, ? > createEntityMultiple( ResultSet rs ) throws SQLException
        {
            Map< Integer, User > usersMap = new LinkedHashMap<>();
            User user;
            
            while ( rs.next() ) {
                user = new User();
                user.setUserId( rs.getInt( "user_id" ) );
                user.setEmail( rs.getString( "email" ) );
                user.setPassword( rs.getString( "password" ) );
                user.setRole( rs.getString( "role" ) );
                
                usersMap.put( user.getUserId(), user );
            }
            
            return usersMap;
        }
        
        @Override
        public Object setId( Object entity, Integer id )
        {
            User user = ( User ) entity;
            user.setUserId( id );
            return user; //Which is the same as returning entity
        }
        
    }
    
}