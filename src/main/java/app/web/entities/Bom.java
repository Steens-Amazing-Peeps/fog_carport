package app.web.entities;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Bom
{
    private Integer bomId; //TODO: Remove this
    private Integer carportId; //TODO: Remove this
    private Integer variantId; //TODO: Remove this
    private Integer amount; //TODO: Remove this


    private List< Plank > planks = new ArrayList<>();     //TODO check where/if this is used
    
    private Map< Integer, Plank > posts = new LinkedHashMap<>();
    private Map< Integer, Plank > beams = new LinkedHashMap<>();
    private Map< Integer, Plank > rafters = new LinkedHashMap<>();
    private int rowAmount = 2;
    
    
    
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



    public List< Plank > getPlanks()
    {
        return this.planks;
    }
    
    public void setPlanks( List< Plank > planks )
    {
        this.planks = planks;
    }
    
    
    //Actually used stuff
    
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
    
    public int getRowAmount()
    {
        return this.rowAmount;
    }
    
    public void setRowAmount( int rowAmount )
    {
        this.rowAmount = rowAmount;
    }
    
    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        
        stringBuilder.append( "------BOM------" ).append( System.lineSeparator() );
        
        stringBuilder.append( System.lineSeparator() );
        stringBuilder.append( "--Misc Info--" ).append( System.lineSeparator() );
        
        stringBuilder.append( "Amount of Rows = " ).append( this.rowAmount ).append( System.lineSeparator() );
        
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

        int sumLength = 0;
        for ( Plank beam : this.getBeams().values() ) {
            sumLength = sumLength + ( beam.getLength() * beam.getAmount() );
        }

        sumLength = sumLength / this.getRowAmount();

        stringBuilder.append(System.lineSeparator());
        stringBuilder.append("Beam sum length = ").append(sumLength);
        stringBuilder.append(System.lineSeparator());
        
        return stringBuilder.toString();
    }
    
}
