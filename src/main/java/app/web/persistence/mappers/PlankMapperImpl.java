//package app.web.persistence.mappers;
//
//import app.web.entities.Plank;
//import app.web.exceptions.DatabaseException;
//import app.web.exceptions.NoIdKeyReturnedException;
//import app.web.exceptions.UnexpectedResultDbException;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.time.LocalDateTime;
//import java.util.LinkedHashMap;
//import java.util.Map;
//
//public class PlankMapperImpl implements PlankMapper
//{
//
//
//    private DataStore dataStore = null;
//    private static final app.web.persistence.mappers.PlankMapperImpl.EntityCreatorImpl ENTITY_CREATOR = new app.web.persistence.mappers.PlankMapperImpl.EntityCreatorImpl();
//
//
//    public PlankMapperImpl( DataStore dataStore )
//    {
//        this.setDataStore( dataStore );
//    }
//
//    @Override
//    public void setDataStore( DataStore dataStore )
//    {
//        this.dataStore = dataStore;
//    }
//
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
//
//    @Override
//    public Map< Integer, Plank > readAll() throws DatabaseException
//    {
//        String sql =
//                "SELECT " +
//                "   * " +
//                "FROM " +
//                "   public.full_material " +
//                "ORDER BY " +
//                "   variant_id;";
//
//
//        return ( Map< Integer, Plank > ) this.dataStore.readAll( sql, ENTITY_CREATOR );
//    }
//
//    @Override
//    public Map< Integer, Plank > readAllByUserId( Integer user_id ) throws DatabaseException
//    {
//        String sql =
//                "SELECT " +
//                "   * " +
//                "FROM " +
//                "   public.full_material " +
//                "WHERE " +
//                "   variant_id = ?;";
//
//        return ( Map< Integer, Plank > ) this.dataStore.readAll( sql, new Object[]{ user_id }, ENTITY_CREATOR );
//    }
//
//    @Override
//    public Plank readSingle( Integer plank_id ) throws DatabaseException
//    {
//        String sql =
//                "SELECT " +
//                "   * " +
//                "FROM " +
//                "   public.plank " +
//                "WHERE " +
//                "   plank_id = ?;";
//
//        return ( Plank ) this.dataStore.readSingle( sql, plank_id, ENTITY_CREATOR );
//    }
//
//    @Override
//    public int update( Plank plank ) throws DatabaseException, UnexpectedResultDbException
//    {
//        String sql =
//                "UPDATE public.plank " +
//                "SET user_id = ?, price_suggested_in_oere = ?, price_actual_in_oere = ?, date_requested = ?, " +
//                "date_approved = ?, date_finished = ?, status = ? " +
//                "WHERE plank_id = ?;";
//
//        Object[] parametersForSql = new Object[ 8 ];
//        parametersForSql[ 0 ] = plank.getUserId();
//        parametersForSql[ 1 ] = plank.getPriceSuggested();
//        parametersForSql[ 2 ] = plank.getPriceActual();
//        parametersForSql[ 3 ] = plank.getDateRequested();
//        parametersForSql[ 4 ] = plank.getDateApproved();
//        parametersForSql[ 5 ] = plank.getDateFinished();
//        parametersForSql[ 6 ] = plank.getStatus();
//        parametersForSql[ 7 ] = plank.getPlankId();
//
//
//        return this.dataStore.update( sql, plank, parametersForSql );
//    }
//
//    @Override
//    public int delete( Integer plank_id ) throws DatabaseException, UnexpectedResultDbException
//    {
//        String sql =
//                "DELETE FROM public.plank " +
//                "WHERE plank_id = ?;";
//
//        return this.dataStore.delete( sql, "plank", plank_id );
//    }
//
//
//
//
//
//
//    private static class EntityCreatorImpl implements EntityCreator
//    {
//
//        private EntityCreatorImpl()
//        {
//
//        }
//
//        @Override
//        public Object createEntity( ResultSet rs ) throws SQLException
//        {
//            Plank plank;
//
//            plank = new Plank();
//            plank.setPlankId( rs.getInt( "plank_id" ) );
//            plank.setUserId( rs.getInt( "user_id" ) );
//            plank.setPriceSuggested( rs.getInt( "price_suggested_in_oere" ) );
//            plank.setPriceActual( rs.getInt( "price_actual_in_oere" ) );
//            plank.setDateRequested( ( LocalDateTime ) rs.getObject( "date_requested" ) );
//            plank.setDateApproved( ( LocalDateTime ) rs.getObject( "date_approved" ) );
//            plank.setDateFinished( ( LocalDateTime ) rs.getObject( "date_finished" ) );
//            plank.setStatus( rs.getString( "status" ) );
//
//            return plank;
//
//        }
//
//        @Override
//        public Map< Integer, ? > createEntityMultiple( ResultSet rs ) throws SQLException
//        {
//            Map< Integer, Plank > plankMap = new LinkedHashMap<>();
//            Plank plank;
//
//            while ( rs.next() ) {
//                plank = new Plank();
//                plank.setPlankId( rs.getInt( "plank_id" ) );
//                plank.setUserId( rs.getInt( "user_id" ) );
//                plank.setPriceSuggested( rs.getInt( "price_suggested_in_oere" ) );
//                plank.setPriceActual( rs.getInt( "price_actual_in_oere" ) );
//                plank.setDateRequested( ( LocalDateTime ) rs.getObject( "date_requested" ) );
//                plank.setDateApproved( ( LocalDateTime ) rs.getObject( "date_approved" ) );
//                plank.setDateFinished( ( LocalDateTime ) rs.getObject( "date_finished" ) );
//                plank.setStatus( rs.getString( "status" ) );
//
//                plankMap.put( plank.getPlankId(), plank );
//            }
//
//            return plankMap;
//        }
//
//        @Override
//        public Object setId( Object entity, Integer id )
//        {
//            Plank plank = ( Plank ) entity;
//            plank.setPlankId( id );
//            return plank; //Which is the same as returning entity
//        }
//
//    }
//
//}
//
//
