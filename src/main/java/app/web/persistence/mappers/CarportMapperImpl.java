package app.web.persistence.mappers;



import app.web.entities.Bom;
import app.web.entities.Carport;
import app.web.exceptions.DatabaseException;
import app.web.exceptions.NoIdKeyReturnedException;
import app.web.exceptions.UnexpectedResultDbException;

import java.sql.*;
import java.util.*;


public final class CarportMapperImpl implements CarportMapper
{
    
    private DataStore dataStore = null;
    private static final EntityCreatorImpl ENTITY_CREATOR = new EntityCreatorImpl();
    
    private BomMapper bomMapper;
    
    public CarportMapperImpl( DataStore dataStore, BomMapper bomMapper )
    {
        this.setDataStore( dataStore );
        this.bomMapper = bomMapper;
    }
    
    @Override
    public void setDataStore( DataStore dataStore )
    {
        this.dataStore = dataStore;
    }
    
    @Override
    public int create( Carport carport, Integer orderId ) throws DatabaseException, NoIdKeyReturnedException, UnexpectedResultDbException
    {
        String sql =
                "INSERT INTO public.carport " +
                "   ( order_id, height_in_mm, length_in_mm, width_in_mm) " +
                "VALUES " +
                "   (?, ?, ?, ?);";
        
        Object[] parametersForSql = new Object[ 4 ];
        parametersForSql[ 0 ] = orderId;
        parametersForSql[ 1 ] = carport.getHeight();
        parametersForSql[ 2 ] = carport.getLength();
        parametersForSql[ 3 ] = carport.getWidth();
        
        return this.dataStore.create( sql, carport, parametersForSql, ENTITY_CREATOR );
    }
    
    @Override
    public int createFull( Carport carport, Integer orderId ) throws DatabaseException, NoIdKeyReturnedException, UnexpectedResultDbException
    {
        int rowsAffected = 0;
        
        rowsAffected = rowsAffected + this.create(  carport, orderId );
        
        rowsAffected = rowsAffected + this.bomMapper.create( carport.getBom(), carport.getCarportId() );
        
        return rowsAffected;
    }
    
    @Override
    public Map< Integer, Carport > readAll() throws DatabaseException
    {
        String sql =
                "SELECT " +
                "   * " +
                "FROM " +
                "   public.carport " +
                "ORDER BY " +
                "   carport_id;";
        
        
        return ( Map< Integer, Carport > ) this.dataStore.readAll( sql, ENTITY_CREATOR );
    }
    
    @Override
    public Map< Integer, Carport > readAllByOrderId( Integer orderId ) throws DatabaseException
    {
        String sql =
                "SELECT " +
                "   * " +
                "FROM " +
                "   public.carport " +
                "WHERE " +
                "   order_id = ?;";
        
        return ( Map< Integer, Carport > ) this.dataStore.readAll( sql, new Object[]{ orderId }, ENTITY_CREATOR );
    }
    
    @Override
    public Carport readAllByOrderIdFull( Integer orderId ) throws DatabaseException
    {
        Map<Integer, Carport> oneCarportMap = this.readAllByOrderId( orderId );
        Carport carport = null;
        
        for ( Carport carportOne : oneCarportMap.values() ) {
            carport = carportOne;
            break;
        }
        
        assert carport!=null;
        Bom bom = this.bomMapper.readAllByCarportId( carport.getCarportId() );
        carport.setBom( bom );
        
        return carport;
    }
    
    @Override
    public Carport readSingle( Integer carportId ) throws DatabaseException
    {
        String sql =
                "SELECT " +
                "   * " +
                "FROM " +
                "   public.carport " +
                "WHERE " +
                "   carport_id = ?;";
        
        return ( Carport ) this.dataStore.readSingle( sql, carportId, ENTITY_CREATOR );
    }
    
    @Override
    public int update( Carport carport, Integer orderId ) throws DatabaseException, UnexpectedResultDbException
    {
        String sql =
                "UPDATE public.carport " +
                "SET order_id = ?, height_in_mm = ?, length_in_mm = ?, width_in_mm = ?" +
                "WHERE carport_id = ?;";
        
        Object[] parametersForSql = new Object[ 5 ];
        parametersForSql[ 0 ] = orderId;
        parametersForSql[ 1 ] = carport.getHeight();
        parametersForSql[ 2 ] = carport.getLength();
        parametersForSql[ 3 ] = carport.getWidth();
        parametersForSql[ 4 ] = carport.getCarportId();
        
        
        return this.dataStore.update( sql, carport, parametersForSql );
    }
    
    @Override
    public int delete( Integer carportId ) throws DatabaseException, UnexpectedResultDbException
    {
        String sql =
                "DELETE FROM public.carport " +
                "WHERE carport_id = ?;";
        
        return this.dataStore.delete( sql, "carport", carportId );
    }
    
    
    
    
    
    
    private static class EntityCreatorImpl implements EntityCreator
    {
        
        private EntityCreatorImpl()
        {
        
        }
        
        @Override
        public Object createEntity( ResultSet rs ) throws SQLException
        {
            Carport carport;
            
            carport = new Carport(); //TODO: Figure out a good fix for this
            carport.setCarportId( rs.getInt( "carport_id" ) );
            carport.setHeight( rs.getInt( "height_in_mm" ) );
            carport.setLength( rs.getInt( "length_in_mm" ) );
            carport.setWidth( rs.getInt( "width_in_mm" ) );
            
            return carport;
            
        }
        
        @Override
        public Map< Integer, ? > createEntityMultiple( ResultSet rs ) throws SQLException
        {
            Map< Integer, Carport > carportMap = new LinkedHashMap<>();
            Carport carport;
            
            while ( rs.next() ) {
                carport = new Carport(); //TODO: Figure out a good fix for this
                carport.setCarportId( rs.getInt( "carport_id" ) );
                carport.setHeight( rs.getInt( "height_in_mm" ) );
                carport.setLength( rs.getInt( "length_in_mm" ) );
                carport.setWidth( rs.getInt( "width_in_mm" ) );
                
                carportMap.put( carport.getCarportId(), carport );
            }
            
            return carportMap;
        }
        
        @Override
        public Object setId( Object entity, Integer id )
        {
            Carport carport = ( Carport ) entity;
            carport.setCarportId( id );
            return carport; //Which is the same as returning entity
        }
        
    }
    
}