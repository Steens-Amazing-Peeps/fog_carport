package app.web.entities;

import app.web.services.bom.PlankCalculator;
import app.web.services.bom.ValidPlanks;

public class Carport
{
    private Integer price;
    
    private Integer height;
    private Integer width;
    private Integer length;
    private boolean isOpen;
    
    private Shed shed;
    private Roof roof;
    
    private Bom bom;
    
    
    PlankCalculator plankCalculator;
    ValidPlanks validPlanks;
    
    public Carport( PlankCalculator plankCalculator, ValidPlanks validPlanks )
    {
        this.plankCalculator = plankCalculator;
        this.validPlanks = validPlanks;
    }
    
    //Getters and Setters
    public Integer getPrice()
    {
        return this.price;
    }
    
    public void setPrice( Integer price )
    {
        this.price = price;
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
