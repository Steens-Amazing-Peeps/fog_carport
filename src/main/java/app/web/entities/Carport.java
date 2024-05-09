package app.web.entities;

import app.web.services.bom.BoardCalculator;
import app.web.services.bom.ValidPlanks;

public class Carport
{
    private Integer carportId;
    private Integer orderId;

    private Integer heightMm;
    private Integer lengthMm;
    private Integer widthMm;


    //TODO check where these are used and if they are in the right place
    private boolean isOpen;

    private Shed shed;
    private Roof roof;
    
    private Bom bom;



    BoardCalculator boardCalculator;
    ValidPlanks validPlanks;
    
    public Carport( BoardCalculator boardCalculator, ValidPlanks validPlanks )
    {
        this.boardCalculator = boardCalculator;
        this.validPlanks = validPlanks;
    }

    public Carport() {

    }

    //Getters and Setters

    public Integer getCarportId() {
        return this.carportId;
    }

    public void setCarportId( Integer carportId ) {
        this.carportId = carportId;
    }

    public Integer getOrderId() {
        return this.orderId;
    }

    public void setOrderId( Integer orderId ) {
        this.orderId = orderId;
    }

    public Integer getHeightMm() {
        return this.heightMm;
    }

    public void setHeightMm( Integer heightMm ) {
        this.heightMm = heightMm;
    }

    public Integer getLengthMm() {
        return this.lengthMm;
    }

    public void setLengthMm( Integer lengthMm ) {
        this.lengthMm = lengthMm;
    }

    public Integer getWidthMm() {
        return this.widthMm;
    }

    public void setWidthMm( Integer widthMm ) {
        this.widthMm = widthMm;
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
