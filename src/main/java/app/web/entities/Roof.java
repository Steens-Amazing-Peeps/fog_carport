package app.web.entities;

public class Roof
{
    private Integer roofId;
    private Integer carportId;
    private Integer material;   // TODO convert since its a string in DB
    private Integer tiltInDegrees;
    
    
    //Getters and Setters

    public Integer getRoofId() {
        return this.roofId;
    }

    public void setRoofId(Integer roofId) {
        this.roofId = roofId;
    }

    public Integer getCarportId() {
        return this.carportId;
    }

    public void setCarportId(Integer carportId) {
        this.carportId = carportId;
    }

    public Integer getMaterial() {
        return this.material;
    }

    public void setMaterial(Integer material) {
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
