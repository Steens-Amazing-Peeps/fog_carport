package app.web.entities;

public class Order
{
    private Integer id;
    private Integer price;
    
    private Carport carport;
    
    
    
    //Getters and Setters
    public Integer getId()
    {
        return this.id;
    }
    
    public void setId( Integer id )
    {
        this.id = id;
    }
    
    public Integer getPrice()
    {
        return this.price;
    }
    
    public void setPrice( Integer price )
    {
        this.price = price;
    }
    
    public Carport getCarport()
    {
        return this.carport;
    }
    
    public void setCarport( Carport carport )
    {
        this.carport = carport;
    }
    
}
