package app.web.entities;

import app.util.CarportMath;
import app.web.constants.Config;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Order
{
    
    private Integer orderId;
    private Integer priceSuggested;
    private Integer priceActual;
    private LocalDateTime dateRequested;
    private LocalDateTime dateApproved;
    private LocalDateTime dateFinished;
    private String status;
    
    private Carport carport;
    private AccountInfo accountInfo;
    
    
    public StringBuilder getFullHistory()
    { //TODO
        StringBuilder stringBuilder = new StringBuilder();
        
        this.toStringPretty( stringBuilder );
        this.carport.getConfirm( stringBuilder );
        
        return stringBuilder;
    }
    
    public StringBuilder getConfirm()
    { //TODO
        StringBuilder stringBuilder = new StringBuilder();
        
        this.toStringPretty( stringBuilder );
        this.carport.getConfirm( stringBuilder );
        this.accountInfo.toStringPretty( stringBuilder );
        
        return stringBuilder;
    }
    
    public StringBuilder getReceipt()
    { //TODO
        StringBuilder stringBuilder = new StringBuilder();
        
        this.toStringPretty( stringBuilder );
        this.carport.getReceipt( stringBuilder );
        this.accountInfo.toStringPretty( stringBuilder );
        
        return stringBuilder;
    }
    
    public StringBuilder toStringPretty( StringBuilder stringBuilder )
    { //TODO
        
        stringBuilder.append( this.toString() ).append( System.lineSeparator() );
        
        return stringBuilder;
    }
    
    @Override
    public String toString()
    {
        return "Order{" +
               "orderId=" + this.orderId +
               ", priceSuggested=" + this.priceSuggested +
               ", priceActual=" + this.priceActual +
               ", dateRequested=" + this.dateRequested +
               ", dateApproved=" + this.dateApproved +
               ", dateFinished=" + this.dateFinished +
               ", status='" + this.status + '\'' +
               '}';
    }
    
    public void calcPriceSuggested()
    {
        BigDecimal serviceFee = CarportMath.percentageToDecimal( Config.Carport.SERVICE_FEE_PERCENTAGE_FOR_SUGGESTED_PRICE );
        this.priceSuggested = serviceFee.multiply( BigDecimal.valueOf( this.getEstimatedRawMaterialsPrice() ) ).intValue();
    }
    
    public void setDateRequestedToNow()
    {
        this.dateRequested = LocalDateTime.now();
    }
    
    public void setStatusToPending()
    {
        this.status = "pending";
    }
    
    //Getters and Setters
    
    public Integer getOrderId()
    {
        return this.orderId;
    }
    
    public void setOrderId( Integer orderId )
    {
        this.orderId = orderId;
    }
    
    public Integer getEstimatedRawMaterialsPrice()
    {
        return this.carport.getPrice() ;
    }
    
    public Integer getPriceSuggested()
    {
        return this.priceSuggested;
    }
    
    public void setPriceSuggested( Integer priceSuggested )
    {
        this.priceSuggested = priceSuggested;
    }
    
    public Integer getPriceActual()
    {
        return this.priceActual;
    }
    
    public void setPriceActual( Integer priceActual )
    {
        this.priceActual = priceActual;
    }
    
    public LocalDateTime getDateRequested()
    {
        return this.dateRequested;
    }
    
    public void setDateRequested( LocalDateTime dateRequested )
    {
        this.dateRequested = dateRequested;
    }
    
    public LocalDateTime getDateApproved()
    {
        return this.dateApproved;
    }
    
    public void setDateApproved( LocalDateTime dateApproved )
    {
        this.dateApproved = dateApproved;
    }
    
    public LocalDateTime getDateFinished()
    {
        return this.dateFinished;
    }
    
    public void setDateFinished( LocalDateTime dateFinished )
    {
        this.dateFinished = dateFinished;
    }
    
    public String getStatus()
    {
        return this.status;
    }
    
    public void setStatus( String status )
    {
        this.status = status;
    }
    
    public Carport getCarport()
    {
        return this.carport;
    }
    
    public void setCarport( Carport carport )
    {
        this.carport = carport;
    }
    
    public AccountInfo getAccountInfo()
    {
        return this.accountInfo;
    }
    
    public void setAccountInfo( AccountInfo accountInfo )
    {
        this.accountInfo = accountInfo;
    }
    
}
