package app.web.entities;

import java.util.Map;

public class FullHistory
{
    
    User user;
    AccountInfo accountInfo;
    Map< Integer, Order > orders;
    
    
    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        
        stringBuilder.append( "--FULL HISTORY--" ).append( System.lineSeparator() );
        
        stringBuilder.append( this.user ).append( System.lineSeparator() );
        
        stringBuilder.append( this.accountInfo ).append( System.lineSeparator() );
        
        if ( this.orders != null && !this.orders.isEmpty() ) {
            for ( Order order : this.orders.values() ) {
                stringBuilder.append( order ).append( System.lineSeparator() );
            }
        } else {
            stringBuilder.append( "null" ).append( System.lineSeparator() );
        }
        
        return stringBuilder.toString();
    }
    
    public User getUser()
    {
        return this.user;
    }
    
    public void setUser( User user )
    {
        this.user = user;
    }
    
    public AccountInfo getAccountInfo()
    {
        return this.accountInfo;
    }
    
    public void setAccountInfo( AccountInfo accountInfo )
    {
        this.accountInfo = accountInfo;
    }
    
    public Map< Integer, Order > getOrders()
    {
        return this.orders;
    }
    
    public void setOrders( Map< Integer, Order > orders )
    {
        this.orders = orders;
    }
    
}
