package app.web.entities;

public class Shed
{
    private Integer shedId;
    private Integer carportId;
    private Integer lengthMm;
    private Integer widthMm;
    private String floor;
    private String wallCladding;

    
    //Getters and Setters

    public Integer getShedId() {
        return this.shedId;
    }

    public void setShedId(Integer shedId) {
        this.shedId = shedId;
    }

    public Integer getCarportId() {
        return this.carportId;
    }

    public void setCarportId(Integer carportId) {
        this.carportId = carportId;
    }

    public Integer getLengthMm() {
        return this.lengthMm;
    }

    public void setLengthMm(Integer lengthMm) {
        this.lengthMm = lengthMm;
    }

    public Integer getWidthMm() {
        return this.widthMm;
    }

    public void setWidthMm(Integer widthMm) {
        this.widthMm = widthMm;
    }

    public String getFloor() {
        return this.floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getWallCladding() {
        return this.wallCladding;
    }

    public void setWallCladding(String wallCladding) {
        this.wallCladding = wallCladding;
    }

}
