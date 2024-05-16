package app.web.persistence.mappers;



import app.web.entities.Order;
import app.web.exceptions.DatabaseException;
import app.web.exceptions.NoIdKeyReturnedException;
import app.web.exceptions.UnexpectedResultDbException;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;


public final class OrderMapperImpl implements OrderMapper
{
    
    
    private DataStore dataStore = null;
    private static final EntityCreatorImpl ENTITY_CREATOR = new EntityCreatorImpl();
    
    private CarportMapper carportMapper;
    private BomMapper bomMapper;
    private ContactMapper contactMapper;
    
    public OrderMapperImpl( DataStore dataStore, CarportMapper carportMapper, BomMapper bomMapper, ContactMapper contactMapper )
    {
        this.dataStore = dataStore;
        this.carportMapper = carportMapper;
        this.bomMapper = bomMapper;
        this.contactMapper = contactMapper;
    }
    
    @Override
    public void setDataStore( DataStore dataStore )
    {
        this.dataStore = dataStore;
    }
    
    @Override
    public int create( Order order, Integer contact_info_id ) throws DatabaseException, NoIdKeyReturnedException, UnexpectedResultDbException
    {
        String sql =
                "INSERT INTO public.order " +
                "   ( contact_info_id, price_suggested_in_oere, price_actual_in_oere, date_requested, date_approved, " +
                "date_finished, status) " +
                "VALUES " +
                "   (?, ?, ?, ?, ?, ?, ?);";
        
        Object[] parametersForSql = new Object[ 7 ];
        parametersForSql[ 0 ] = contact_info_id;
        parametersForSql[ 1 ] = order.getPriceSuggested();
        parametersForSql[ 2 ] = order.getPriceActual();
        parametersForSql[ 3 ] = order.getDateRequested();
        parametersForSql[ 4 ] = order.getDateApproved();
        parametersForSql[ 5 ] = order.getDateFinished();
        parametersForSql[ 6 ] = order.getStatus();
        
        return this.dataStore.create( sql, order, parametersForSql, ENTITY_CREATOR );
    }
    
    
    @Override
    public int createFull( Order order, Integer userId ) throws DatabaseException, NoIdKeyReturnedException, UnexpectedResultDbException
    {
        int rowsAffected = 0;
        
        rowsAffected = rowsAffected + this.contactMapper.create( order.getAccountInfo(), userId );
        
        rowsAffected = rowsAffected + this.create( order, order.getAccountInfo().getContactId() );
        
        rowsAffected = rowsAffected + this.carportMapper.createFull( order.getCarport(), order.getOrderId() );
        
        return rowsAffected;
    }
    
    @Override
    public Map< Integer, Order > readAll() throws DatabaseException
    {
        String sql =
                "SELECT " +
                "   * " +
                "FROM " +
                "   public.order " +
                "ORDER BY " +
                "   order_id;";
        
        
        return ( Map< Integer, Order > ) this.dataStore.readAll( sql, ENTITY_CREATOR );
    }
    
    @Override
    public Map< Integer, Order > readAllByUserId( Integer user_id ) throws DatabaseException
    {
        String sql =
                "SELECT " +
                "   * " +
                "FROM " +
                "   public.order " +
                "WHERE " +
                "   user_id = ?;";
        
        return ( Map< Integer, Order > ) this.dataStore.readAll( sql, new Object[]{ user_id }, ENTITY_CREATOR );
    }
    
    @Override
    public Order readSingle( Integer order_id ) throws DatabaseException
    {
        String sql =
                "SELECT " +
                "   * " +
                "FROM " +
                "   public.order " +
                "WHERE " +
                "   order_id = ?;";
        
        return ( Order ) this.dataStore.readSingle( sql, order_id, ENTITY_CREATOR );
    }
    
    @Override
    public int update( Order order ) throws DatabaseException, UnexpectedResultDbException
    {
        String sql =
                "UPDATE public.order " +
                "SET user_id = ?, price_suggested_in_oere = ?, price_actual_in_oere = ?, date_requested = ?, " +
                "date_approved = ?, date_finished = ?, status = ? " +
                "WHERE order_id = ?;";
        
        Object[] parametersForSql = new Object[ 7 ];
        parametersForSql[ 0 ] = order.getPriceSuggested();
        parametersForSql[ 1 ] = order.getPriceActual();
        parametersForSql[ 2 ] = order.getDateRequested();
        parametersForSql[ 3 ] = order.getDateApproved();
        parametersForSql[ 4 ] = order.getDateFinished();
        parametersForSql[ 5 ] = order.getStatus();
        parametersForSql[ 6 ] = order.getOrderId();
        
        
        return this.dataStore.update( sql, order, parametersForSql );
    }
    
    @Override
    public int delete( Integer order_id ) throws DatabaseException, UnexpectedResultDbException
    {
        String sql =
                "DELETE FROM public.order " +
                "WHERE order_id = ?;";
        
        return this.dataStore.delete( sql, "order", order_id );
    }
    
    
    
    
    
    
    private static class EntityCreatorImpl implements EntityCreator
    {
        
        private EntityCreatorImpl()
        {
        
        }
        
        @Override
        public Object createEntity( ResultSet rs ) throws SQLException
        {
            Order order;
            
            order = new Order();
            order.setOrderId( rs.getInt( "order_id" ) );
//            order.setUserId( rs.getInt( "user_id" ) );
            order.setPriceSuggested( rs.getInt( "price_suggested_in_oere" ) );
            order.setPriceActual( rs.getInt( "price_actual_in_oere" ) );
            order.setDateRequested( ( LocalDateTime ) rs.getObject( "date_requested" ) );
            order.setDateApproved( ( LocalDateTime ) rs.getObject( "date_approved" ) );
            order.setDateFinished( ( LocalDateTime ) rs.getObject( "date_finished" ) );
            order.setStatus( rs.getString( "status" ) );
            
            return order;
            
        }
        
        @Override
        public Map< Integer, ? > createEntityMultiple( ResultSet rs ) throws SQLException
        {
            Map< Integer, Order > orderMap = new LinkedHashMap<>();
            Order order;
            
            while ( rs.next() ) {
                order = new Order();
                order.setOrderId( rs.getInt( "order_id" ) );
//                order.setUserId( rs.getInt( "user_id" ) );
                order.setPriceSuggested( rs.getInt( "price_suggested_in_oere" ) );
                order.setPriceActual( rs.getInt( "price_actual_in_oere" ) );
                order.setDateRequested( ( LocalDateTime ) rs.getObject( "date_requested" ) );
                order.setDateApproved( ( LocalDateTime ) rs.getObject( "date_approved" ) );
                order.setDateFinished( ( LocalDateTime ) rs.getObject( "date_finished" ) );
                order.setStatus( rs.getString( "status" ) );
                
                orderMap.put( order.getOrderId(), order );
            }
            
            return orderMap;
        }
        
        @Override
        public Object setId( Object entity, Integer id )
        {
            Order order = ( Order ) entity;
            order.setOrderId( id );
            return order; //Which is the same as returning entity
        }
        
    }
    
}