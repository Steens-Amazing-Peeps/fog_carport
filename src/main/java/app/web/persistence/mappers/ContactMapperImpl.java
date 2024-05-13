package app.web.persistence.mappers;



import app.web.entities.ContactInfo;
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
    public int create( ContactInfo contactInfo ) throws DatabaseException, NoIdKeyReturnedException, UnexpectedResultDbException
    {
        String sql =
                "INSERT INTO public.contact_info " +
                        "   ( full_name, address, zip_code, city, phone_number, email, user_id ) " +
                        "VALUES " +
                        "   (?, ?, ?, ?, ?, ?, ?);";

        Object[] parametersForSql = new Object[ 7 ];
        parametersForSql[ 0 ] = contactInfo.getFullName();
        parametersForSql[ 1 ] = contactInfo.getAddress();
        parametersForSql[ 2 ] = contactInfo.getZip();
        parametersForSql[ 3 ] = contactInfo.getCity();
        parametersForSql[ 4 ] = contactInfo.getPhoneNumber();
        parametersForSql[ 5 ] = contactInfo.getEmail();
        parametersForSql[ 6 ] = contactInfo.getUser();

        return this.dataStore.create( sql, contactInfo, parametersForSql, ENTITY_CREATOR );
    }

    @Override
    public Map< Integer, ContactInfo > readAll() throws DatabaseException
    {
        String sql =
                "SELECT " +
                        "   * " +
                        "FROM " +
                        "   public.contact_info " +
                        "ORDER BY " +
                        "   contact_info_id;";


        return ( Map< Integer, ContactInfo > ) this.dataStore.readAll( sql, ENTITY_CREATOR );
    }

    @Override
    public Map< Integer, ContactInfo > readAllByUserId( Integer user_id ) throws DatabaseException
    {
        String sql =
                "SELECT " +
                        "   * " +
                        "FROM " +
                        "   public.contact_info " +
                        "WHERE " +
                        "   user_id = ?;";

        return ( Map< Integer, ContactInfo > ) this.dataStore.readAll( sql, new Object[]{ user_id }, ENTITY_CREATOR );
    }

    @Override
    public ContactInfo readSingle( Integer contact_id ) throws DatabaseException
    {
        String sql =
                "SELECT " +
                        "   * " +
                        "FROM " +
                        "   public.contact_info " +
                        "WHERE " +
                        "   contact_info_id = ?;";

        return ( ContactInfo ) this.dataStore.readSingle( sql, contact_id, ENTITY_CREATOR );
    }

    @Override
    public int update( ContactInfo contactInfo ) throws DatabaseException, UnexpectedResultDbException
    {
        String sql =
                "UPDATE public.contact_info " +
                        "SET full_name = ?, address = ?, zip_code = ?, city = ?, phone_number = ?, email = ?, user_id = ? " +
                        "WHERE contact_info_id = ?;";

        Object[] parametersForSql = new Object[ 8 ];
        parametersForSql[ 0 ] = contactInfo.getFullName();
        parametersForSql[ 1 ] = contactInfo.getAddress();
        parametersForSql[ 2 ] = contactInfo.getZip();
        parametersForSql[ 3 ] = contactInfo.getCity();
        parametersForSql[ 4 ] = contactInfo.getPhoneNumber();
        parametersForSql[ 5 ] = contactInfo.getEmail();
        parametersForSql[ 6 ] = contactInfo.getUser();
        parametersForSql[ 7 ] = contactInfo.getContactId();


        return this.dataStore.update( sql, contactInfo, parametersForSql );
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
            ContactInfo contactInfo;

            contactInfo = new ContactInfo();
            contactInfo.setContactId( rs.getInt( "contact_info_id" ) );
            contactInfo.setFullName( rs.getString( "full_name" ) );
            contactInfo.setAddress( rs.getString( "address" ) );
            contactInfo.setZip( rs.getInt( "zip_code" ) );
            contactInfo.setCity( rs.getString( "city" ) );
            contactInfo.setPhoneNumber( rs.getInt( "phone_number" ) );
            contactInfo.setEmail( rs.getString( "email" ) );
            contactInfo.setUser( rs.getInt( "user_id" ) );

            return contactInfo;

        }

        @Override
        public Map< Integer, ? > createEntityMultiple( ResultSet rs ) throws SQLException
        {
            Map< Integer, ContactInfo > contactMap = new LinkedHashMap<>();
            ContactInfo contactInfo;

            while ( rs.next() ) {
                contactInfo = new ContactInfo();
                contactInfo.setContactId( rs.getInt( "contact_info_id" ) );
                contactInfo.setFullName( rs.getString( "full_name" ) );
                contactInfo.setAddress( rs.getString( "address" ) );
                contactInfo.setZip( rs.getInt( "zip_code" ) );
                contactInfo.setCity( rs.getString( "city" ) );
                contactInfo.setPhoneNumber( rs.getInt( "phone_number" ) );
                contactInfo.setEmail( rs.getString( "email" ) );
                contactInfo.setUser( rs.getInt( "user_id" ) );

                contactMap.put( contactInfo.getContactId(), contactInfo );
            }

            return contactMap;
        }

        @Override
        public Object setId( Object entity, Integer id )
        {
            ContactInfo contactInfo = ( ContactInfo ) entity;
            contactInfo.setContactId( id );
            return contactInfo; //Which is the same as returning entity
        }

    }

}