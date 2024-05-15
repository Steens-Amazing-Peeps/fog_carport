package app.web.entities;

import app.util.MetricConversion;
import app.web.constants.attributes.WebGlobalAttributes;
import app.web.exceptions.WebInvalidInputException;
import app.web.services.bom.planks.ValidPlanksImpl;
import app.web.services.bom.planks.calculators.*;
import app.web.services.bom.planks.ValidPlanks;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Carport
{
    
    private Integer price;
    
    private Integer carportId;
    private Integer orderId;
    
    private Integer height;
    private Integer length;
    private Integer width;
    
    private String comment;
    
    
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
    
    
    
    
    //Getters and Setters
    
    public Integer getPrice()
    {
        return this.price;
    }
    
    public Integer getCarportId()
    {
        return this.carportId;
    }
    
    public void setPrice( Integer price )
    {
        this.price = price;
    }
    
    public void setCarportId( Integer carportId )
    {
        this.carportId = carportId;
    }
    
    public Integer getOrderId()
    {
        return this.orderId;
    }
    
    public void setOrderId( Integer orderId )
    {
        this.orderId = orderId;
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
    
    public String getComment()
    {
        return this.comment;
    }
    
    public void setComment( String comment )
    {
        this.comment = comment;
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
