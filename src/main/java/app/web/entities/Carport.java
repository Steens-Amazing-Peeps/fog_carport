package app.web.entities;

import app.util.MetricConversion;
import app.web.constants.attributes.WebGlobalAttributes;
import app.web.exceptions.WebInvalidInputException;
import app.web.services.bom.planks.calculators.*;
import app.web.services.bom.planks.ValidPlanks;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Carport
{
    private Integer carportId;
    
    private Integer height;
    private Integer length;
    private Integer width;
    

    
    
    //TODO check where these are used and if they are in the right place
    private boolean isOpen;
    
    private Shed shed;
    private Roof roof;
    
    private Bom bom;
    
    
    PlankCalculator plankCalculator;
    
    ValidPlanks validPlanks;
    
    public Carport() //TODO:Use something better than this temp fix?
    {
        this.plankCalculator = new PlankCalculatorImpl( new PostCalculatorImpl(), new BeamCalculatorImpl(), new RafterCalculatorImpl() );
        this.validPlanks = WebGlobalAttributes.VALID_PLANKS;
    }
    
    public Carport( PlankCalculator plankCalculator, ValidPlanks validPlanks )
    {
        this.plankCalculator = plankCalculator;
        this.validPlanks = validPlanks;
    }
    
    
    //Bom Calculation
    public Bom calcBom() throws WebInvalidInputException
    {
        this.bom = this.plankCalculator.calcBom( this.validPlanks, this );
        return this.bom;
    }
    
    public StringBuilder getConfirm()
    { //TODO
        return this.getConfirm( new StringBuilder() );
    }
    
    public StringBuilder getConfirm( StringBuilder stringBuilder )
    {  //TODO
        
        this.toStringPretty( stringBuilder );
        
        return stringBuilder;
        
    }
    
    public StringBuilder getReceipt()
    { //TODO
        return this.getReceipt( new StringBuilder() );
    }
    
    public StringBuilder getReceipt( StringBuilder stringBuilder )
    { //TODO
        
        this.toStringPretty( stringBuilder );
        
        return stringBuilder;
    }

    public StringBuilder getBill( StringBuilder stringBuilder )
    { //TODO

        this.toStringPretty( stringBuilder );
        this.bom.toString( stringBuilder );

        return stringBuilder;
    }
    
    public StringBuilder toStringPretty( StringBuilder stringBuilder )
    { //TODO
        
        stringBuilder.append( this ).append( System.lineSeparator() );
        
        return stringBuilder;
    }
    
    @Override
    public String toString()
    {
        return "Carport{" +
               "price=" + this.getPrice() +
               ", carportId=" + this.carportId +
               ", height=" + this.height +
               ", length=" + this.length +
               ", width=" + this.width +
               ", isOpen=" + this.isOpen +
               ", shed=" + this.shed +
               ", roof=" + this.roof +
               '}';
    }
    
    //Getters and Setters
    
    public Integer getCarportId()
    {
        return this.carportId;
    }
    
    public void setCarportId( Integer carportId )
    {
        this.carportId = carportId;
    }
    
    public Integer getPrice()
    {
        return this.bom.getEstimatedRawMaterialPrice();
    }
    public Integer getHeight()
    {
        return this.height;
    }
    
    public BigDecimal getHeightInM()
    {
        if ( this.height == null ) {
            return null;
        }
        
        return MetricConversion.mmToM( this.height ).setScale( MetricConversion.COMMA_DIGITS_IN_STRING_M, RoundingMode.HALF_UP );
    }
    
    public void setHeight( Integer height )
    {
        this.height = height;
    }
    
    public Integer getLength()
    {
        return this.length;
    }
    
    public BigDecimal getLengthInM()
    {
        if ( this.length == null ) {
            return null;
        }
        
        return MetricConversion.mmToM( this.length ).setScale( MetricConversion.COMMA_DIGITS_IN_STRING_M, RoundingMode.HALF_UP );
    }
    
    public void setLength( Integer length )
    {
        this.length = length;
    }
    
    public Integer getWidth()
    {
        return this.width;
    }
    
    public BigDecimal getWidthInM()
    {
        if ( this.width == null ) {
            return null;
        }
        
        return MetricConversion.mmToM( this.width ).setScale( MetricConversion.COMMA_DIGITS_IN_STRING_M, RoundingMode.HALF_UP );
    }
    
    public void setWidth( Integer width )
    {
        this.width = width;
    }
    
    // TODO check if/when/where below getters and setters are needed
    
    public boolean isOpen()
    {
        return this.isOpen;
    }
    
    public void setOpen( boolean open )
    {
        this.isOpen = open;
    }
    
    public Shed getShed()
    {
        return this.shed;
    }
    
    public void setShed( Shed shed )
    {
        this.shed = shed;
    }
    
    public Roof getRoof()
    {
        return this.roof;
    }
    
    public void setRoof( Roof roof )
    {
        this.roof = roof;
    }
    
    public Bom getBom()
    {
        return this.bom;
    }
    
    public void setBom( Bom bom )
    {
        this.bom = bom;
    }
    
}
