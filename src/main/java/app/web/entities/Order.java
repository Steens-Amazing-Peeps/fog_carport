package app.web.entities;

import app.util.CarportMath;
import app.util.PriceInOereAndDkk;
import app.web.constants.Config;

import javax.swing.text.DateFormatter;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Order
{
    
    private Integer orderId;
    private Integer priceSuggested;
    private Integer priceActual;
    private LocalDateTime dateRequested;
    private LocalDateTime dateApproved;
    private LocalDateTime dateFinished;
    private String status;
    private String comment;
    
    private Carport carport;
    private AccountInfo accountInfo;
    
    
    public String getString()
    { //TODO
        return this.getString( new StringBuilder() );
    }
    
    public String getString( StringBuilder stringBuilder )
    {
        stringBuilder.append( "Ordre Id: " ).append( this.orderId );
        stringBuilder.append( " - Pris Råmaterialer: " ).append( this.getPriceRawMaterialsPretty() );
        stringBuilder.append( " - Forslået Pris: " ).append( this.getPriceSuggestedPretty() );
        stringBuilder.append( " - Pris: " ).append( Objects.requireNonNullElse( this.getPriceActualPretty(), "Afventer Vurdering" ) );
        
        
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern( "dd/MM/yyyy" );
        
        stringBuilder.append( " - Dato Bestilt: " ).append( dateTimeFormatter.format( this.dateRequested ) );
        
        stringBuilder.append( " - Dato Godkendt: " );
        if ( this.dateApproved != null ) {
            stringBuilder.append( dateTimeFormatter.format( this.dateApproved ) );
        } else {
            stringBuilder.append( "Afventer Godkendelse" );
        }
        
        stringBuilder.append( " - Dato Betalt: " );
        if ( this.dateFinished != null ) {
            stringBuilder.append( dateTimeFormatter.format( this.dateFinished ) );
        } else if ( this.dateApproved == null ) {
            stringBuilder.append( "Afventer Godkendelse" );
        } else {
            stringBuilder.append( "Afventer Betaling" );
        }
        
        
        stringBuilder.append( " - Status: " ).append( this.status );
        stringBuilder.append( " - Kundens Kommentar: " ).append( Objects.requireNonNullElse( this.comment, "N/A" ) );
        stringBuilder.append( System.lineSeparator() );
        
        this.carport.getString( stringBuilder );
        
        return stringBuilder.toString();
    }
    
    
    
    public String getStringUser()
    { //TODO
        return this.getStringUser(  new StringBuilder() );
    }
    
    public String getStringUser( StringBuilder stringBuilder )
    {
        stringBuilder.append( "Ordre Id: " ).append( this.orderId );
        stringBuilder.append( " - Estimeret Pris: " ).append( this.getPriceSuggestedPretty() );
        stringBuilder.append( " - Pris: " ).append( Objects.requireNonNullElse( this.getPriceActualPretty(), "Afventer Vurdering" ) );
        
        
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern( "dd/MM/yyyy" );
        
        stringBuilder.append( " - Dato Bestilt: " ).append( dateTimeFormatter.format( this.dateRequested ) );
        
        stringBuilder.append( " - Dato Godkendt: " );
        if ( this.dateApproved != null ) {
            stringBuilder.append( dateTimeFormatter.format( this.dateApproved ) );
        } else {
            stringBuilder.append( "Afventer Godkendelse" );
        }
        
        stringBuilder.append( " - Dato Betalt: " );
        if ( this.dateFinished != null ) {
            stringBuilder.append( dateTimeFormatter.format( this.dateFinished ) );
        } else if ( this.dateApproved == null ) {
            stringBuilder.append( "Afventer Godkendelse" );
        } else {
            stringBuilder.append( "Afventer Betaling" );
        }
        
        
        stringBuilder.append( " - Status: " ).append( this.status );
        stringBuilder.append( " - Din Kommentar: " ).append( Objects.requireNonNullElse( this.comment, "N/A" ) );
        stringBuilder.append( System.lineSeparator() );
        
        if ( this.dateFinished == null ) {
            this.carport.getStringUnpaid( stringBuilder );
        } else {
            this.carport.getStringPaid( stringBuilder );
        }
        
        return stringBuilder.toString();
    }
    
    public String getConfirm()
    { //TODO
        StringBuilder stringBuilder = new StringBuilder();
        
        stringBuilder.append( "Ordre" );
        stringBuilder.append( " - Estimeret Pris: " ).append( this.getPriceSuggestedPretty() );
        stringBuilder.append( " - Din Kommentar: " ).append( Objects.requireNonNullElse( this.comment, "N/A" ) );
        stringBuilder.append( System.lineSeparator() );
        
        this.carport.getStringUnpaid( stringBuilder );
        stringBuilder.append( this.accountInfo.getString() );
        
        return stringBuilder.toString();
    }
    
    public String getReceipt()
    { //TODO
        
        return this.getStringUser() + this.accountInfo.getString();
    }
    
    public String getBill()
    { //TODO
        StringBuilder stringBuilder = new StringBuilder();
        
        this.toStringPretty( stringBuilder );
        this.carport.getBill( stringBuilder );
        this.accountInfo.toStringPretty( stringBuilder );
        
        return stringBuilder.toString();
    }
    
    public String toStringPretty( StringBuilder stringBuilder )
    { //TODO
        
        stringBuilder.append( this.toString() ).append( System.lineSeparator() );
        
        return stringBuilder.toString();
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
               ", comment='" + this.comment + '\'' +
               '}';
    }
    
    public void calcPriceSuggested()
    {
        BigDecimal serviceFee = CarportMath.percentageToDecimal( Config.Carport.SERVICE_FEE_PERCENTAGE_FOR_SUGGESTED_PRICE );
        this.priceSuggested = serviceFee.multiply( BigDecimal.valueOf( this.getEstimatedRawMaterialsPrice() ) ).intValue();
    }
    
    public PriceInOereAndDkk getPriceActualPretty()
    {
        if ( this.priceActual == null || this.priceActual <= 0 ) {
            return null;
        }
        PriceInOereAndDkk pricePretty = new PriceInOereAndDkk();
        pricePretty.setPriceInOere( this.priceActual );
        return pricePretty;
    }
    
    public PriceInOereAndDkk getPriceSuggestedPretty()
    {
        if ( this.priceSuggested == null || this.priceSuggested <= 0 ) {
            return null;
        }
        PriceInOereAndDkk pricePretty = new PriceInOereAndDkk();
        pricePretty.setPriceInOere( this.priceSuggested );
        return pricePretty;
    }
    
    public PriceInOereAndDkk getPriceRawMaterialsPretty()
    {
        Integer estimatedRawMaterialsPrice = this.getEstimatedRawMaterialsPrice();
        if ( estimatedRawMaterialsPrice == null || estimatedRawMaterialsPrice <= 0 ) {
            return null;
        }
        
        PriceInOereAndDkk pricePretty = new PriceInOereAndDkk();
        pricePretty.setPriceInOere( estimatedRawMaterialsPrice );
        return pricePretty;
    }
    
    public void setDateRequestedToNow()
    {
        this.dateRequested = LocalDateTime.now();
    }
    
    public void setDateFinishedToNow()
    {
        this.dateFinished = LocalDateTime.now();
    }
    
    public void setStatusToPending()
    {
        this.status = "pending";
    }
    
    public void setStatusToFinished()
    {
        this.status = "finished";
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
        return this.carport.getPrice();
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
    
    public String getComment()
    {
        return this.comment;
    }
    
    public void setComment( String comment )
    {
        this.comment = comment;
    }
    
}
