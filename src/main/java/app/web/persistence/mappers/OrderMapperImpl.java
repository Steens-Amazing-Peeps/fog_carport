package app.web.persistence.mappers;



import app.web.entities.AccountInfo;
import app.web.entities.Order;
import app.web.exceptions.DatabaseException;
import app.web.exceptions.NoIdKeyReturnedException;
import app.web.exceptions.UnexpectedResultDbException;

import java.sql.*;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.util.*;


public final class OrderMapperImpl implements OrderMapper
{
    
    
    private DataStore dataStore = null;
    private static final EntityCreatorImpl ENTITY_CREATOR = new EntityCreatorImpl();
    
    private CarportMapper carportMapper;
    private BomMapper bomMapper;
    private AccountInfoMapper accountInfoMapper;
    
    public OrderMapperImpl( DataStore dataStore, CarportMapper carportMapper, BomMapper bomMapper, AccountInfoMapper accountInfoMapper )
    {
        this.dataStore = dataStore;
        this.carportMapper = carportMapper;
        this.bomMapper = bomMapper;
        this.accountInfoMapper = accountInfoMapper;
    }
    
    @Override
    public void setDataStore( DataStore dataStore )
    {
        this.dataStore = dataStore;
    }
    
    @Override
    public int create( Order order, Integer contactInfoId ) throws DatabaseException, NoIdKeyReturnedException, UnexpectedResultDbException
    {
        String sql =
                "INSERT INTO public.order " +
                "   ( contact_info_id, price_suggested_in_oere, price_actual_in_oere, date_requested, date_approved, " +
                "date_finished, status) " +
                "VALUES " +
                "   (?, ?, ?, ?, ?, ?, ?);";
        
        Object[] parametersForSql = new Object[ 7 ];
        parametersForSql[ 0 ] = contactInfoId;
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
        
        rowsAffected = rowsAffected + this.accountInfoMapper.create( order.getAccountInfo(), userId );
        
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

//    @Override
//    public Map< Integer, Order > readAllFull() throws DatabaseException
//    {
//        Map<Integer, Order> fullOrderMap = new LinkedHashMap<>();
//
//        Map<Integer, AccountInfo > accountInfoMap = this.accountInfoMapper.readAll();
//
//        for ( AccountInfo accountInfo : accountInfoMap.values() ) {
//            fullOrderMap.put( readSingle( accountInfo ) );
//        }
//
//
//        return fullOrderMap;
//    }
    
    @Override
    public Map< Integer, Order > readAllByAccountInfoId( Integer accountInfoId ) throws DatabaseException
    {
        String sql =
                "SELECT " +
                "   * " +
                "FROM " +
                "   public.order " +
                "WHERE " +
                "   contact_info_id = ?;";
        
        return ( Map< Integer, Order > ) this.dataStore.readAll( sql, new Object[]{ accountInfoId }, ENTITY_CREATOR );
    }
    
    @Override
    public Map< Integer, Order > readAllByAccountInfoIdFull( AccountInfo accountInfo ) throws DatabaseException
    {
        Map< Integer, Order > orderMap = this.readAllByAccountInfoId( accountInfo.getContactId() );
        
        for ( Order order : orderMap.values() ) {
            order.setCarport( this.carportMapper.readAllByOrderIdFull( order.getOrderId() ) );
            order.setAccountInfo( accountInfo );
        }
        
        return orderMap;
    }
    
    @Override
    public Order readSingle( Integer orderId ) throws DatabaseException
    {
        String sql =
                "SELECT " +
                "   * " +
                "FROM " +
                "   public.order " +
                "WHERE " +
                "   order_id = ?;";
        
        return ( Order ) this.dataStore.readSingle( sql, orderId, ENTITY_CREATOR );
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
    public int delete( Integer orderId ) throws DatabaseException, UnexpectedResultDbException
    {
        String sql =
                "DELETE FROM public.order " +
                "WHERE order_id = ?;";
        
        return this.dataStore.delete( sql, "order", orderId );
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
            try {
                order.setDateRequested( ( ( Timestamp ) rs.getObject( "date_requested" ) ).toLocalDateTime() );
            } catch ( NullPointerException e ) {
                order.setDateRequested( null );
            }
            
            try {
                order.setDateApproved( ( ( Timestamp ) rs.getObject( "date_approved" ) ).toLocalDateTime() );
            } catch ( NullPointerException e ) {
                order.setDateApproved( null );
            }
            
            try {
                order.setDateFinished( ( ( Timestamp ) rs.getObject( "date_finished" ) ).toLocalDateTime() );
            } catch ( NullPointerException e ) {
                order.setDateFinished( null );
            }
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
                try {
                    order.setDateRequested( ( ( Timestamp ) rs.getObject( "date_requested" ) ).toLocalDateTime() );
                } catch ( NullPointerException e ) {
                    order.setDateRequested( null );
                }
                
                try {
                    order.setDateApproved( ( ( Timestamp ) rs.getObject( "date_approved" ) ).toLocalDateTime() );
                } catch ( NullPointerException e ) {
                    order.setDateApproved( null );
                }
                
                try {
                    order.setDateFinished( ( ( Timestamp ) rs.getObject( "date_finished" ) ).toLocalDateTime() );
                } catch ( NullPointerException e ) {
                    order.setDateFinished( null );
                }
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