package app.web.entities;

public class Shed
{
    private Integer shedId;
    private Integer carportId;
    private Integer length;
    private Integer width;
    private Integer depth;
    private Integer floor;   // TODO conversion for floor and wall as they are strings in DB
    private Integer wallCladding;

    
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

    public Integer getLength() {
        return this.length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getWidth() {
        return this.width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getDepth() {
        return this.depth;
    }

    public void setDepth(Integer depth) {
        this.depth = depth;
    }

    public Integer getFloor() {
        return this.floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Integer getWallCladding() {
        return this.wallCladding;
    }

    public void setWallCladding(Integer wallCladding) {
        this.wallCladding = wallCladding;
    }

}
