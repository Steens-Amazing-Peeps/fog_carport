package app.web.persistence.mappers;



import app.web.entities.Roof;
import app.web.exceptions.DatabaseException;
import app.web.exceptions.NoIdKeyReturnedException;
import app.web.exceptions.UnexpectedResultDbException;

import java.sql.*;
import java.util.*;


public final class RoofMapperImpl implements RoofMapper
{


    private DataStore dataStore = null;
    private static final EntityCreatorImpl ENTITY_CREATOR = new EntityCreatorImpl();


    public RoofMapperImpl( DataStore dataStore )
    {
        this.setDataStore( dataStore );
    }

    @Override
    public void setDataStore( DataStore dataStore )
    {
        this.dataStore = dataStore;
    }

    @Override
    public int create( Roof roof ) throws DatabaseException, NoIdKeyReturnedException, UnexpectedResultDbException
    {
        String sql =
                "INSERT INTO public.carport_roof " +
                        "   ( carport_id, material, tilt_in_degrees) " +
                        "VALUES " +
                        "   (?, ?, ?);";

        Object[] parametersForSql = new Object[ 3 ];
        parametersForSql[ 0 ] = roof.getCarportId();
        parametersForSql[ 1 ] = roof.getMaterial();
        parametersForSql[ 2 ] = roof.getTiltInDegrees();

        return this.dataStore.create( sql, roof, parametersForSql, ENTITY_CREATOR );
    }

    @Override
    public Map< Integer, Roof > readAll() throws DatabaseException
    {
        String sql =
                "SELECT " +
                        "   * " +
                        "FROM " +
                        "   public.carport_roof " +
                        "ORDER BY " +
                        "   roof_id;";


        return ( Map< Integer, Roof > ) this.dataStore.readAll( sql, ENTITY_CREATOR );
    }

    @Override
    public Map< Integer, Roof > readAllByCarportId( Integer carport_id ) throws DatabaseException
    {
        String sql =
                "SELECT " +
                        "   * " +
                        "FROM " +
                        "   public.carport_roof " +
                        "WHERE " +
                        "   carport_id = ?;";

        return ( Map< Integer, Roof > ) this.dataStore.readAll( sql, new Object[]{ carport_id }, ENTITY_CREATOR );
    }

    @Override
    public Roof readSingle( Integer roof_id ) throws DatabaseException
    {
        String sql =
                "SELECT " +
                        "   * " +
                        "FROM " +
                        "   public.carport_roof " +
                        "WHERE " +
                        "   roof_id = ?;";

        return ( Roof ) this.dataStore.readSingle( sql, roof_id, ENTITY_CREATOR );
    }

    @Override
    public int update( Roof roof ) throws DatabaseException, UnexpectedResultDbException
    {
        String sql =
                "UPDATE public.carport_roof " +
                        "SET carport_id = ?, material = ?, tilt_in_degrees = ?" +
                        "WHERE roof_id = ?;";

        Object[] parametersForSql = new Object[ 4 ];
        parametersForSql[ 0 ] = roof.getCarportId();
        parametersForSql[ 1 ] = roof.getMaterial();
        parametersForSql[ 2 ] = roof.getTiltInDegrees();
        parametersForSql[ 3 ] = roof.getRoofId();


        return this.dataStore.update( sql, roof, parametersForSql );
    }

    @Override
    public int delete( Integer roof_id ) throws DatabaseException, UnexpectedResultDbException
    {
        String sql =
                "DELETE FROM public.carport_roof " +
                        "WHERE roof_id = ?;";

        return this.dataStore.delete( sql, "carport_roof", roof_id );
    }






    private static class EntityCreatorImpl implements EntityCreator
    {

        private EntityCreatorImpl()
        {

        }

        @Override
        public Object createEntity( ResultSet rs ) throws SQLException
        {
            Roof roof;

            roof = new Roof();
            roof.setRoofId( rs.getInt( "roof_id" ) );
            roof.setCarportId( rs.getInt( "carport_id" ) );
            roof.setMaterial( rs.getString( "material" ) );
            roof.setTiltInDegrees( rs.getInt( "tilt_in_degrees" ) );

            return roof;

        }

        @Override
        public Map< Integer, ? > createEntityMultiple( ResultSet rs ) throws SQLException
        {
            Map< Integer, Roof > roofMap = new LinkedHashMap<>();
            Roof roof;

            while ( rs.next() ) {
                roof = new Roof();
                roof.setRoofId( rs.getInt( "roof_id" ) );
                roof.setCarportId( rs.getInt( "carport_id" ) );
                roof.setMaterial( rs.getString( "material" ) );
                roof.setTiltInDegrees( rs.getInt( "tilt_in_degrees" ) );

                roofMap.put( roof.getRoofId(), roof );
            }

            return roofMap;
        }

        @Override
        public Object setId( Object entity, Integer id )
        {
            Roof roof = ( Roof ) entity;
            roof.setRoofId( id );
            return roof; //Which is the same as returning entity
        }

    }

}