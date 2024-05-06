package app.web.entities;

import java.math.BigDecimal;

public class Plank
{
    
    //Types
    public static int BOARD = 0; //Brædt
    public static int LATH = 1; //Lægte
    public static int BEAM = 2; //Reglar
    public static int RAFTER = 3; //Spærtræ
    public static int POST = 4; //Stolpe
    
    private Integer id = null;
    private Integer height = null;
    private Integer width = null;
    private Integer length = null;
    private Integer type = null;
    private Integer amount = null;
    private Integer price = null;
    private BigDecimal pricePrMm = null;
    
    public Plank()
    {
    }
    
    public Plank( Integer id, Integer height, Integer width, Integer length, Integer type, Integer pricePrMm )
    {
        this.id = id;
        this.height = height;
        this.width = width;
        this.length = length;
        this.type = type;
        this.price = pricePrMm * length;
    }
    
    //Getters and Setters
    public Integer getId()
    {
        return this.id;
    }
    
    public void setId( Integer id )
    {
        this.id = id;
    }
    
    public Integer getHeight()
    {
        return this.height;
    }
    
    public void setHeight( Integer height )
    {
        this.height = height;
    }
    
    public Integer getWidth()
    {
        return this.width;
    }
    
    public void setWidth( Integer width )
    {
        this.width = width;
    }
    
    
    public Integer getLength()
    {
        return this.length;
    }
    
    public void setLength( Integer length )
    {
        this.length = length;
    }
    
    public Integer getAmount()
    {
        return this.amount;
    }
    
    public void setAmount( Integer amount )
    {
        this.amount = amount;
    }
    
    public Integer getPrice()
    {
        return this.price;
    }
    
    public void setPrice( Integer price )
    {
        this.price = price;
    }
    
    public Integer getType()
    {
        return this.type;
    }
    
    public void setType( Integer type )
    {
        this.type = type;
    }
    
    public BigDecimal getPricePrMm()
    {
        if ( this.pricePrMm == null && this.price != null && this.length != null ) {
            this.pricePrMm = new BigDecimal( this.price ).divide( new BigDecimal( this.length ) );
        }
        return this.pricePrMm;
    }
    
    public void setPricePrMm( BigDecimal pricePrMm )
    {
        this.pricePrMm = pricePrMm;
    }
    
}
