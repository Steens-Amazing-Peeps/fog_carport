package app.web.persistence.mappers;



import app.web.entities.Shed;
import app.web.exceptions.DatabaseException;
import app.web.exceptions.NoIdKeyReturnedException;
import app.web.exceptions.UnexpectedResultDbException;

import java.sql.*;
import java.util.*;


public final class ShedMapperImpl implements ShedMapper
{ //TODO: Maybe 'shed' table in DB should just be a part of 'carport' table?


    private DataStore dataStore = null;
    private static final EntityCreatorImpl ENTITY_CREATOR = new EntityCreatorImpl();


    public ShedMapperImpl( DataStore dataStore )
    {
        this.setDataStore( dataStore );
    }

    @Override
    public void setDataStore( DataStore dataStore )
    {
        this.dataStore = dataStore;
    }

    @Override
    public int create( Shed shed ) throws DatabaseException, NoIdKeyReturnedException, UnexpectedResultDbException
    {
        String sql =
                "INSERT INTO public.shed " +
                        "   ( carport_id, length_in_mm, width_in_mm, floor, wall_cladding) " +
                        "VALUES " +
                        "   (?, ?, ?, ?, ?);";

        Object[] parametersForSql = new Object[ 5 ];
        parametersForSql[ 0 ] = shed.getCarportId();
        parametersForSql[ 1 ] = shed.getLength();
        parametersForSql[ 2 ] = shed.getWidth();
        parametersForSql[ 3 ] = shed.getFloor();
        parametersForSql[ 4 ] = shed.getWallCladding();

        return this.dataStore.create( sql, shed, parametersForSql, ENTITY_CREATOR );
    }

    @Override
    public Map< Integer, Shed > readAll() throws DatabaseException
    {
        String sql =
                "SELECT " +
                        "   * " +
                        "FROM " +
                        "   public.shed " +
                        "ORDER BY " +
                        "   shed_id;";


        return ( Map< Integer, Shed > ) this.dataStore.readAll( sql, ENTITY_CREATOR );
    }

    @Override
    public Map< Integer, Shed > readAllByCarportId( Integer carport_id ) throws DatabaseException
    {
        String sql =
                "SELECT " +
                        "   * " +
                        "FROM " +
                        "   public.shed " +
                        "WHERE " +
                        "   carport_id = ?;";

        return ( Map< Integer, Shed > ) this.dataStore.readAll( sql, new Object[]{ carport_id }, ENTITY_CREATOR );
    }

    @Override
    public Shed readSingle( Integer shed_id ) throws DatabaseException
    {
        String sql =
                "SELECT " +
                        "   * " +
                        "FROM " +
                        "   public.shed " +
                        "WHERE " +
                        "   shed_id = ?;";

        return ( Shed ) this.dataStore.readSingle( sql, shed_id, ENTITY_CREATOR );
    }

    @Override
    public int update( Shed shed ) throws DatabaseException, UnexpectedResultDbException
    {
        String sql =
                "UPDATE public.shed " +
                        "SET carport_id = ?, length_in_mm = ?, width_in_mm = ?, floor = ?, wall_cladding = ? " +
                        "WHERE shed_id = ?;";

        Object[] parametersForSql = new Object[ 6 ];
        parametersForSql[ 0 ] = shed.getCarportId();
        parametersForSql[ 1 ] = shed.getLength();
        parametersForSql[ 2 ] = shed.getWidth();
        parametersForSql[ 3 ] = shed.getFloor();
        parametersForSql[ 4 ] = shed.getWallCladding();
        parametersForSql[ 5 ] = shed.getShedId();


        return this.dataStore.update( sql, shed, parametersForSql );
    }

    @Override
    public int delete( Integer shed_id ) throws DatabaseException, UnexpectedResultDbException
    {
        String sql =
                "DELETE FROM public.shed " +
                        "WHERE shed_id = ?;";

        return this.dataStore.delete( sql, "shed", shed_id );
    }






    private static class EntityCreatorImpl implements EntityCreator
    {

        private EntityCreatorImpl()
        {

        }

        @Override
        public Object createEntity( ResultSet rs ) throws SQLException
        {
            Shed shed;

            shed = new Shed();
            shed.setShedId( rs.getInt( "shed_id" ) );
            shed.setCarportId( rs.getInt( "carport_id" ) );
            shed.setLength( rs.getInt( "length_in_mm" ) );
            shed.setWidth( rs.getInt( "width_in_mm" ) );
            shed.setFloor( rs.getInt( "floor" ));
            shed.setWallCladding( rs.getInt( "wall_cladding" ));

            return shed;

        }

        @Override
        public Map< Integer, ? > createEntityMultiple( ResultSet rs ) throws SQLException
        {
            Map< Integer, Shed > shedMap = new LinkedHashMap<>();
            Shed shed;

            while ( rs.next() ) {
                shed = new Shed();
                shed.setShedId( rs.getInt( "shed_id" ) );
                shed.setCarportId( rs.getInt( "carport_id" ) );
                shed.setLength( rs.getInt( "length_in_mm" ) );
                shed.setWidth( rs.getInt( "width_in_mm" ) );
                shed.setFloor( rs.getInt( "floor" ));
                shed.setWallCladding( rs.getInt( "wall_cladding" ) );

                shedMap.put( shed.getShedId(), shed );
            }

            return shedMap;
        }

        @Override
        public Object setId( Object entity, Integer id )
        {
            Shed shed = ( Shed ) entity;
            shed.setShedId( id );
            return shed; //Which is the same as returning entity
        }

    }

}