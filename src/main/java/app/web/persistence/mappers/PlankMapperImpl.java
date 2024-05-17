package app.web.persistence.mappers;

import app.web.entities.Plank;
import app.web.exceptions.DatabaseException;
import app.web.exceptions.UnexpectedResultDbException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public class PlankMapperImpl implements PlankMapper
{


    private DataStore dataStore = null;
    private static final app.web.persistence.mappers.PlankMapperImpl.EntityCreatorImpl ENTITY_CREATOR = new app.web.persistence.mappers.PlankMapperImpl.EntityCreatorImpl();


    public PlankMapperImpl( DataStore dataStore )
    {
        this.setDataStore( dataStore );
    }

    @Override
    public void setDataStore( DataStore dataStore )
    {
        this.dataStore = dataStore;
    }

//    @Override
//    public int create( Plank plank ) throws DatabaseException, NoIdKeyReturnedException, UnexpectedResultDbException
//    {
//        String sql =
//                "INSERT INTO public.variant " +
//                "   (   ) " +
//                "VALUES " +
//                "   (?, ?);";
//
//        Object[] parametersForSql = new Object[ 0 ];
//
//
//        return this.dataStore.create( sql, plank, parametersForSql, ENTITY_CREATOR );
//    }

    @Override
    public Map< Integer, Plank > readAll() throws DatabaseException
    {
        String sql =
                "SELECT " +
                "   * " +
                "FROM " +
                "   \"material_combined\" " +
                "ORDER BY " +
                "   variant_id;";


        return ( Map< Integer, Plank > ) this.dataStore.readAll( sql, ENTITY_CREATOR );
    }

    @Override
    public Map< Integer, Plank > readAllByType( Integer type ) throws DatabaseException
    {
        String sql =
                "SELECT " +
                "   * " +
                "FROM " +
                "   \"material_combined\" " +
                "WHERE " +
                "   type = ? " +
                "   AND is_obsolete = FALSE; ";
        

        return ( Map< Integer, Plank > ) this.dataStore.readAll( sql, new Object[]{ Plank.convertTypeToString( type ) }, ENTITY_CREATOR );
    }

    @Override
    public Plank readSingle( Integer plankId ) throws DatabaseException
    {
        String sql =
                "SELECT " +
                "   * " +
                "FROM " +
                "   \"material_combined\" " +
                "WHERE " +
                "   plank_id = ?;";

        return ( Plank ) this.dataStore.readSingle( sql, plankId, ENTITY_CREATOR );
    }



    @Override
    public int delete( Integer plankId ) throws DatabaseException, UnexpectedResultDbException
    {
        String sql =
                "DELETE FROM \"variant\" " +
                "WHERE variant_id = ?;";

        return this.dataStore.delete( sql, "variant", plankId );
    }






    private static class EntityCreatorImpl implements EntityCreator
    {

        private EntityCreatorImpl()
        {

        }

        @Override
        public Object createEntity( ResultSet rs ) throws SQLException
        {
            Plank plank;

            plank = new Plank();
            
            plank.setId( rs.getInt( "variant_id" ) );
            plank.setLength( rs.getInt( "length_in_mm" ) );
//            rs.getInt( "amount_pr_unit" );
            plank.setPrice( rs.getInt( "price_in_oere" ) );
            plank.setHeight( rs.getInt( "height_in_mm" ) );
            plank.setWidth( rs.getInt( "width_in_mm" ) );
//            rs.getString( "direction" );
//            rs.getInt( "angle_in_degrees" );
//            rs.getString( "category" )
            plank.setType( Plank.convertTypeToInt( rs.getString( "type" ) ) );
            plank.setMaterial( rs.getString( "material" ) );
            plank.setTreatment( rs.getString( "treatment" ) );
//            rs.getString( "unit" );

            return plank;

        }

        @Override
        public Map< Integer, ? > createEntityMultiple( ResultSet rs ) throws SQLException
        {
            Map< Integer, Plank > plankMap = new LinkedHashMap<>();
            Plank plank;

            while ( rs.next() ) {
                plank = new Plank();
                
                plank.setId( rs.getInt( "variant_id" ) );
                plank.setLength( rs.getInt( "length_in_mm" ) );
//            rs.getInt( "amount_pr_unit" );
                plank.setPrice( rs.getInt( "price_in_oere" ) );
                plank.setHeight( rs.getInt( "height_in_mm" ) );
                plank.setWidth( rs.getInt( "width_in_mm" ) );
//            rs.getString( "direction" );
//            rs.getInt( "angle_in_degrees" );
//            rs.getString( "category" )
                plank.setType( Plank.convertTypeToInt( rs.getString( "type" ) ) );
                plank.setMaterial( rs.getString( "material" ) );
                plank.setTreatment( rs.getString( "treatment" ) );
//            rs.getString( "unit" );

                plankMap.put( plank.getId(), plank );
            }

            return plankMap;
        }

        @Override
        public Object setId( Object entity, Integer id )
        {
            Plank plank = ( Plank ) entity;
            plank.setId( id );
            return plank; //Which is the same as returning entity
        }

    }

}


