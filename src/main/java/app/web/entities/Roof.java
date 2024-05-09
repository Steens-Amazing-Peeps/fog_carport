package app.web.entities;

public class Roof
{
    private Integer roofId;
    private Integer carportId;
    private String material;
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

    public String getMaterial() {
        return this.material;
    }

    public void setMaterial(String material) {
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
