package app.web.entities;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Bom
{
    private Integer bomId;
    private Integer carportId;
    private Integer variantId;
    private Integer amount;

    //TODO check where/if this is used
    private List< Plank > planks = new ArrayList<>();
    
    private Map< Integer, Plank > posts = new LinkedHashMap<>();
    private Map< Integer, Plank > beams = new LinkedHashMap<>();
    private Map< Integer, Plank > rafters = new LinkedHashMap<>();
    
    
    
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
    
    public Map< Integer, Plank > getPosts()
    {
        return this.posts;
    }
    
    public void setPosts( Map< Integer, Plank > posts )
    {
        this.posts = posts;
    }
    
    public Map< Integer, Plank > getBeams()
    {
        return this.beams;
    }
    
    public void setBeams( Map< Integer, Plank > beams )
    {
        this.beams = beams;
    }
    
    public Map< Integer, Plank > getRafters()
    {
        return this.rafters;
    }
    
    public void setRafters( Map< Integer, Plank > rafters )
    {
        this.rafters = rafters;
    }
    
    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        
        stringBuilder.append( "------BOM------" ).append( System.lineSeparator() );
        
        stringBuilder.append( System.lineSeparator() );
        stringBuilder.append( "--Planks--" ).append( System.lineSeparator() );
        
        for ( Plank plank : this.planks ) {
            stringBuilder.append( this.planks.toString() ).append( System.lineSeparator() );
        }
        
        stringBuilder.append( System.lineSeparator()  );
        stringBuilder.append( "--Posts--" ).append( System.lineSeparator() );
        
        for ( Plank plank : this.posts.values() ) {
            stringBuilder.append( plank.toString() ).append( System.lineSeparator() );
        }
        
        stringBuilder.append( System.lineSeparator()  );
        stringBuilder.append( "--Beams--" ).append( System.lineSeparator() );
        
        for ( Plank plank : this.beams.values() ) {
            stringBuilder.append( plank.toString() ).append( System.lineSeparator() );
        }
        
        stringBuilder.append( System.lineSeparator()  );
        stringBuilder.append( "--Rafters--" ).append( System.lineSeparator() );
        
        for ( Plank plank : this.rafters.values() ) {
            stringBuilder.append( plank.toString() ).append( System.lineSeparator() );
        }
        
        return stringBuilder.toString();
    }
    
}
