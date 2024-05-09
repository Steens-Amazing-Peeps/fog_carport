package app.web.persistence.mappers;



import app.web.entities.Bom;
import app.web.exceptions.DatabaseException;
import app.web.exceptions.NoIdKeyReturnedException;
import app.web.exceptions.UnexpectedResultDbException;

import java.sql.*;
import java.util.*;


public final class BomMapperImpl implements BomMapper
{


    private DataStore dataStore = null;
    private static final EntityCreatorImpl ENTITY_CREATOR = new EntityCreatorImpl();


    public BomMapperImpl( DataStore dataStore )
    {
        this.setDataStore( dataStore );
    }

    @Override
    public void setDataStore( DataStore dataStore )
    {
        this.dataStore = dataStore;
    }

    @Override
    public int create( Bom bom ) throws DatabaseException, NoIdKeyReturnedException, UnexpectedResultDbException
    {
        String sql =
                "INSERT INTO public.bom " +
                        "   ( carport_id, variant_id, amount) " +
                        "VALUES " +
                        "   (?, ?, ?);";

        Object[] parametersForSql = new Object[ 3 ];
        parametersForSql[ 0 ] = bom.getCarportId();
        parametersForSql[ 1 ] = bom.getVariantId();
        parametersForSql[ 2 ] = bom.getAmount();

        return this.dataStore.create( sql, bom, parametersForSql, ENTITY_CREATOR );
    }

    @Override
    public Map< Integer, Bom > readAll() throws DatabaseException
    {
        String sql =
                "SELECT " +
                        "   * " +
                        "FROM " +
                        "   public.bom " +
                        "ORDER BY " +
                        "   bom_id;";


        return ( Map< Integer, Bom > ) this.dataStore.readAll( sql, ENTITY_CREATOR );
    }

    @Override
    public Map< Integer, Bom > readAllByCarportId( Integer carport_id ) throws DatabaseException
    {
        String sql =
                "SELECT " +
                        "   * " +
                        "FROM " +
                        "   public.bom " +
                        "WHERE " +
                        "   carport_id = ?;";

        return ( Map< Integer, Bom > ) this.dataStore.readAll( sql, new Object[]{ carport_id }, ENTITY_CREATOR );
    }

    @Override
    public Bom readSingle( Integer bom_id ) throws DatabaseException
    {
        String sql =
                "SELECT " +
                        "   * " +
                        "FROM " +
                        "   public.bom " +
                        "WHERE " +
                        "   bom_id = ?;";

        return ( Bom ) this.dataStore.readSingle( sql, bom_id, ENTITY_CREATOR );
    }

    @Override
    public int update( Bom bom ) throws DatabaseException, UnexpectedResultDbException
    {
        String sql =
                "UPDATE public.bom " +
                        "SET carport_id = ?, variant_id = ?, amount = ? " +
                        "WHERE bom_id = ?;";

        Object[] parametersForSql = new Object[ 4 ];
        parametersForSql[ 0 ] = bom.getCarportId();
        parametersForSql[ 1 ] = bom.getVariantId();
        parametersForSql[ 2 ] = bom.getAmount();
        parametersForSql[ 3 ] = bom.getBomId();


        return this.dataStore.update( sql, bom, parametersForSql );
    }

    @Override
    public int delete( Integer bom_id ) throws DatabaseException, UnexpectedResultDbException
    {
        String sql =
                "DELETE FROM public.bom " +
                        "WHERE bom_id = ?;";

        return this.dataStore.delete( sql, "bom", bom_id );
    }






    private static class EntityCreatorImpl implements EntityCreator
    {

        private EntityCreatorImpl()
        {

        }

        @Override
        public Object createEntity( ResultSet rs ) throws SQLException
        {
            Bom bom;

            bom = new Bom();
            bom.setBomId( rs.getInt( "bom_id" ) );
            bom.setCarportId( rs.getInt( "carport_id" ) );
            bom.setVariantId( rs.getInt( "variant_id" ) );
            bom.setAmount( rs.getInt( "amount" ) );

            return bom;

        }

        @Override
        public Map< Integer, ? > createEntityMultiple( ResultSet rs ) throws SQLException
        {
            Map< Integer, Bom > bomMap = new LinkedHashMap<>();
            Bom bom;

            while ( rs.next() ) {
                bom = new Bom();
                bom.setBomId( rs.getInt( "bom_id" ) );
                bom.setCarportId( rs.getInt( "carport_id" ) );
                bom.setVariantId( rs.getInt( "variant_id" ) );
                bom.setAmount( rs.getInt( "amount" ) );

                bomMap.put( bom.getBomId(), bom );
            }

            return bomMap;
        }

        @Override
        public Object setId( Object entity, Integer id )
        {
            Bom bom = ( Bom ) entity;
            bom.setBomId( id );
            return bom; //Which is the same as returning entity
        }

    }

}