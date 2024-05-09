package app.web.entities;

import java.util.ArrayList;
import java.util.List;

public class Bom
{
    private Integer bomId;
    private Integer carportId;
    private Integer variantId;
    private Integer amount;

    //TODO check where/if this is used
    private List< Plank > planks = new ArrayList<>();
    
    
    
    //Getters and Setters

    public Integer getBomId() {
        return this.bomId;
    }

    public void setBomId(Integer bomId) {
        this.bomId = bomId;
    }

    public Integer getCarportId() {
        return this.carportId;
    }

    public void setCarportId(Integer carportId) {
        this.carportId = carportId;
    }

    public Integer getVariantId() {
        return this.variantId;
    }

    public void setVariantId(Integer variantId) {
        this.variantId = variantId;
    }

    public Integer getAmount() {
        return this.amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    // TODO check if/when/where below getters and setters are needed

    public List< Plank > getPlanks()
    {
        return this.planks;
    }
    
    public void setPlanks( List< Plank > planks )
    {
        this.planks = planks;
    }
    
}
