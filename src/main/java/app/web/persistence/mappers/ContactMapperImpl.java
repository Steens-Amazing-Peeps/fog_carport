package app.web.persistence.mappers;



import app.web.entities.AccountInfo;
import app.web.exceptions.DatabaseException;
import app.web.exceptions.NoIdKeyReturnedException;
import app.web.exceptions.UnexpectedResultDbException;

import java.sql.*;
import java.util.*;


public final class ContactMapperImpl implements ContactMapper
{


    private DataStore dataStore = null;
    private static final EntityCreatorImpl ENTITY_CREATOR = new EntityCreatorImpl();


    public ContactMapperImpl( DataStore dataStore )
    {
        this.setDataStore( dataStore );
    }

    @Override
    public void setDataStore( DataStore dataStore )
    {
        this.dataStore = dataStore;
    }

    @Override
    public int create( AccountInfo accountInfo, Integer userId ) throws DatabaseException, NoIdKeyReturnedException, UnexpectedResultDbException
    {
        String sql =
                "INSERT INTO public.contact_info " +
                        "   ( full_name, address, zip_code, city, phone_number, email, user_id ) " +
                        "VALUES " +
                        "   (?, ?, ?, ?, ?, ?, ?);";

        Object[] parametersForSql = new Object[ 7 ];
        parametersForSql[ 0 ] = accountInfo.getFullName();
        parametersForSql[ 1 ] = accountInfo.getAddress();
        parametersForSql[ 2 ] = accountInfo.getZip();
        parametersForSql[ 3 ] = accountInfo.getCity();
        parametersForSql[ 4 ] = accountInfo.getPhoneNumber();
        parametersForSql[ 5 ] = accountInfo.getEmail();
        parametersForSql[ 6 ] = userId;

        return this.dataStore.create( sql, accountInfo, parametersForSql, ENTITY_CREATOR );
    }
    
    @Override
    public int create( AccountInfo accountInfo ) throws DatabaseException, NoIdKeyReturnedException, UnexpectedResultDbException
    {
        String sql =
                "INSERT INTO public.contact_info " +
                "   ( full_name, address, zip_code, city, phone_number, email ) " +
                "VALUES " +
                "   (?, ?, ?, ?, ?, ? );";
        
        Object[] parametersForSql = new Object[ 6 ];
        parametersForSql[ 0 ] = accountInfo.getFullName();
        parametersForSql[ 1 ] = accountInfo.getAddress();
        parametersForSql[ 2 ] = accountInfo.getZip();
        parametersForSql[ 3 ] = accountInfo.getCity();
        parametersForSql[ 4 ] = accountInfo.getPhoneNumber();
        parametersForSql[ 5 ] = accountInfo.getEmail();

        
        return this.dataStore.create( sql, accountInfo, parametersForSql, ENTITY_CREATOR );
    }

    @Override
    public Map< Integer, AccountInfo > readAll() throws DatabaseException
    {
        String sql =
                "SELECT " +
                        "   * " +
                        "FROM " +
                        "   public.contact_info " +
                        "ORDER BY " +
                        "   contact_info_id;";


        return ( Map< Integer, AccountInfo > ) this.dataStore.readAll( sql, ENTITY_CREATOR );
    }

    @Override
    public Map< Integer, AccountInfo > readAllByUserId( Integer user_id ) throws DatabaseException
    {
        String sql =
                "SELECT " +
                        "   * " +
                        "FROM " +
                        "   public.contact_info " +
                        "WHERE " +
                        "   user_id = ?;";

        return ( Map< Integer, AccountInfo > ) this.dataStore.readAll( sql, new Object[]{ user_id }, ENTITY_CREATOR );
    }

    @Override
    public AccountInfo readSingle( Integer contact_id ) throws DatabaseException
    {
        String sql =
                "SELECT " +
                        "   * " +
                        "FROM " +
                        "   public.contact_info " +
                        "WHERE " +
                        "   contact_info_id = ?;";

        return ( AccountInfo ) this.dataStore.readSingle( sql, contact_id, ENTITY_CREATOR );
    }

    @Override
    public int update( AccountInfo accountInfo, Integer userId ) throws DatabaseException, UnexpectedResultDbException
    {
        String sql =
                "UPDATE public.contact_info " +
                        "SET full_name = ?, address = ?, zip_code = ?, city = ?, phone_number = ?, email = ?, user_id = ? " +
                        "WHERE contact_info_id = ?;";

        Object[] parametersForSql = new Object[ 8 ];
        parametersForSql[ 0 ] = accountInfo.getFullName();
        parametersForSql[ 1 ] = accountInfo.getAddress();
        parametersForSql[ 2 ] = accountInfo.getZip();
        parametersForSql[ 3 ] = accountInfo.getCity();
        parametersForSql[ 4 ] = accountInfo.getPhoneNumber();
        parametersForSql[ 5 ] = accountInfo.getEmail();
        parametersForSql[ 6 ] = userId;
        parametersForSql[ 7 ] = accountInfo.getContactId();


        return this.dataStore.update( sql, accountInfo, parametersForSql );
    }

    @Override
    public int delete( Integer contact_id ) throws DatabaseException, UnexpectedResultDbException
    {
        String sql =
                "DELETE FROM public.contact_info " +
                        "WHERE contact_info_id = ?;";

        return this.dataStore.delete( sql, "contact_info", contact_id );
    }






    private static class EntityCreatorImpl implements EntityCreator
    {

        private EntityCreatorImpl()
        {

        }

        @Override
        public Object createEntity( ResultSet rs ) throws SQLException
        {
            AccountInfo accountInfo;

            accountInfo = new AccountInfo();
            accountInfo.setContactId( rs.getInt( "contact_info_id" ) );
            accountInfo.setFullName( rs.getString( "full_name" ) );
            accountInfo.setAddress( rs.getString( "address" ) );
            accountInfo.setZip( rs.getInt( "zip_code" ) );
            accountInfo.setCity( rs.getString( "city" ) );
            accountInfo.setPhoneNumber( rs.getInt( "phone_number" ) );
            accountInfo.setEmail( rs.getString( "email" ) );

            return accountInfo;

        }

        @Override
        public Map< Integer, ? > createEntityMultiple( ResultSet rs ) throws SQLException
        {
            Map< Integer, AccountInfo > contactMap = new LinkedHashMap<>();
            AccountInfo accountInfo;

            while ( rs.next() ) {
                accountInfo = new AccountInfo();
                accountInfo.setContactId( rs.getInt( "contact_info_id" ) );
                accountInfo.setFullName( rs.getString( "full_name" ) );
                accountInfo.setAddress( rs.getString( "address" ) );
                accountInfo.setZip( rs.getInt( "zip_code" ) );
                accountInfo.setCity( rs.getString( "city" ) );
                accountInfo.setPhoneNumber( rs.getInt( "phone_number" ) );
                accountInfo.setEmail( rs.getString( "email" ) );

                contactMap.put( accountInfo.getContactId(), accountInfo );
            }

            return contactMap;
        }

        @Override
        public Object setId( Object entity, Integer id )
        {
            AccountInfo accountInfo = ( AccountInfo ) entity;
            accountInfo.setContactId( id );
            return accountInfo; //Which is the same as returning entity
        }

    }

}