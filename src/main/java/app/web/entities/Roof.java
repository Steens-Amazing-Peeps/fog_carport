package app.web.entities;

public class Roof
{
    private Integer material;
    private Integer tiltInDegrees;
    
    
    //Getters and Setters
    public Integer getMaterial()
    {
        return this.material;
    }
    
    public void setMaterial( Integer material )
    {
        this.material = material;
    }
    
    public Integer getTiltInDegrees()
    {
        return this.tiltInDegrees;
    }
    
    public void setTiltInDegrees( Integer tiltInDegrees )
    {
        this.tiltInDegrees = tiltInDegrees;
    }
    
}
