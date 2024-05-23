package app.web.entities;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class FullHistory
{
    
    User user;
    Map< AccountInfo, Map< Integer, Order > > OrdersByAccountInfo;
    
    
    
    public String toStringFull()
    {
        StringBuilder stringBuilder = new StringBuilder();
        
        stringBuilder.append( "--FULL HISTORY--" ).append( System.lineSeparator() );
        
        stringBuilder.append( this.user ).append( System.lineSeparator() );
        
        //Map isn't null or empty
        if ( ( this.OrdersByAccountInfo != null && !this.OrdersByAccountInfo.isEmpty() ) ) {
            for ( Map.Entry< AccountInfo, Map< Integer, Order > > accountInfoSetAndOrdersMapEntry : this.OrdersByAccountInfo.entrySet() ) {
                
                //All Account Infos are the same on any given entry, so get just the key.
                stringBuilder.append( accountInfoSetAndOrdersMapEntry.getKey() ).append( System.lineSeparator() );
                
                //Get All the orders that use this info
                for ( Order order : accountInfoSetAndOrdersMapEntry.getValue().values() ) {
                    stringBuilder.append( order.getString( stringBuilder ) ).append( System.lineSeparator() );
                }
            }
            
            
        } else {
            stringBuilder.append( "null" ).append( System.lineSeparator() );
        }
        
        return stringBuilder.toString();
    }
    
    public String getString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        
        //Map isn't null or empty
        if ( ( this.OrdersByAccountInfo != null && !this.OrdersByAccountInfo.isEmpty() ) ) {
            for ( Map.Entry< AccountInfo, Map< Integer, Order > > accountInfoSetAndOrdersMapEntry : this.OrdersByAccountInfo.entrySet() ) {
                
                //All Account Infos are the same on any given entry, so get just the key.
                stringBuilder.append( accountInfoSetAndOrdersMapEntry.getKey() ).append( System.lineSeparator() );
                
                //Get All the orders that use this info
                for ( Order order : accountInfoSetAndOrdersMapEntry.getValue().values() ) {
                    stringBuilder.append( order.getString() ).append( System.lineSeparator() );
                }
            }
        }
        return stringBuilder.toString();
    }
    
    
    @Override
    public String toString()
    {
        return "FullHistory{" +
               "user=" + user +
               ", OrdersByAccountInfo=" + OrdersByAccountInfo +
               '}';
    }
    
    public void addAccountInfo( AccountInfo accountInfo )
    {
        if ( this.OrdersByAccountInfo == null ) {
            this.OrdersByAccountInfo = new LinkedHashMap<>();
        }
        
        this.OrdersByAccountInfo.putIfAbsent( accountInfo, new LinkedHashMap<>() );
    }
    
    public void addOrderMapWithAccountInfo( Map< Integer, Order > orderMapWithAccountInfo )
    {
        if ( this.OrdersByAccountInfo == null ) {
            this.OrdersByAccountInfo = new TreeMap<>();
        }
        
        for ( Order order : orderMapWithAccountInfo.values() ) {
            this.OrdersByAccountInfo.putIfAbsent( order.getAccountInfo(), new LinkedHashMap<>() );
            
            this.OrdersByAccountInfo.get( order.getAccountInfo() ).put( order.getOrderId(), order );
        }
    }
    
    
    //Getters and Setters
    public User getUser()
    {
        return this.user;
    }
    
    public void setUser( User user )
    {
        this.user = user;
    }
    
    public Map< AccountInfo, Map< Integer, Order > > getOrdersByAccountInfo()
    {
        return this.OrdersByAccountInfo;
    }
    
    public void setOrdersByAccountInfo( Map< AccountInfo, Map< Integer, Order > > ordersByAccountInfo )
    {
        this.OrdersByAccountInfo = ordersByAccountInfo;
    }
    
}
