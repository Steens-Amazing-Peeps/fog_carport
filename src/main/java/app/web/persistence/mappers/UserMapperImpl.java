package app.web.persistence.mappers;



import app.web.entities.User;
import app.web.exceptions.DatabaseException;
import app.web.exceptions.NoIdKeyReturnedException;
import app.web.exceptions.UnexpectedResultDbException;
import app.web.persistence.GetConnectionIf;

import java.sql.*;
import java.util.*;


public final class UserMapperImpl implements UserMapper
{
    
    public static final int PASSWORD_MAX_LENGTH = 100;
    public static final int PASSWORD_MIN_LENGTH = 4;
    private static final String DEFAULT_USER_ROLE = "user";
    
    
    private DataStore dataStore = null;
    private static final UserMapperImpl.DtoCreatorImpl DTO_CREATOR = new DtoCreatorImpl();
    
    
    private static UserMapperImpl userMapperImpl = null;
    
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
            user.setRole( DEFAULT_USER_ROLE );
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
        
        return this.dataStore.create( sql, user, parametersForSql, DTO_CREATOR );
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
        
        
        return ( Map< Integer, User > ) this.dataStore.readAll( sql, DTO_CREATOR );
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
        
        return ( Map< Integer, User > ) this.dataStore.readAll( sql, new Object[]{ email }, DTO_CREATOR );
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
        
        return ( User ) this.dataStore.readSingle( sql, id, DTO_CREATOR );
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
    
    
    
    
    
    
    private static class DtoCreatorImpl implements DtoCreator
    {
        
        private DtoCreatorImpl()
        {
            
        }
        
        @Override
        public Object createDto( ResultSet rs ) throws SQLException
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
        public Map< Integer, ? > createDtoMultiple( ResultSet rs ) throws SQLException
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