package app.web.entities;

import app.web.exceptions.WebInvalidInputException;

import java.util.LinkedHashMap;
import java.util.Map;

public class Bom
{
    
    private Map< Integer, Plank > posts = new LinkedHashMap<>();
    private Map< Integer, Plank > beams = new LinkedHashMap<>();
    private Map< Integer, Plank > rafters = new LinkedHashMap<>();
    private int rowAmount = 2;
    
    
    
    public int getCombinedMapSize()
    {
        return this.posts.size() + this.beams.size() + this.rafters.size();
    }
    
    public int getTotalPlanks()
    {
        return this.getTotalPosts() + this.getTotalBeams() + this.getTotalRafters();
    }
    
    public int getTotalPosts()
    {
        return this.getTotalPlanks( this.posts );
    }
    
    public int getTotalBeams()
    {
        return this.getTotalPlanks( this.beams );
    }
    
    public int getTotalRafters()
    {
        return this.getTotalPlanks( this.rafters );
    }
    
    private int getTotalPlanks( Map< Integer, Plank > planks )
    {
        int totalPlanks = 0;
        
        for ( Plank plank : planks.values() ) {
            totalPlanks = totalPlanks + plank.getAmount();
        }
        
        return totalPlanks;
    }
    
    public void addPlank( Plank plank ) throws WebInvalidInputException
    {
        int type = plank.getType();
        
        if ( type == Plank.POST ) {
            this.posts.put( plank.getId(), plank );
            return;
        }
        
        if ( type == Plank.BEAM ) {
            this.beams.put( plank.getId(), plank );
            return;
        }
        
        if ( type == Plank.RAFTER ) {
            this.rafters.put( plank.getId(), plank );
            return;
        }
        
        throw new WebInvalidInputException( "Unknown int type of plank = " + type, "Unknown int type of plank = " + type );
        
        
    }
    
    public int getEstimatedRawMaterialPrice(){
        int priceTotal = 0;
        
        for ( Plank plank : this.posts.values() ) {
            priceTotal = priceTotal + plank.getPriceWithAmount();
        }
        
        for ( Plank plank : this.beams.values() ) {
            priceTotal = priceTotal + plank.getPriceWithAmount();
        }
        
        for ( Plank plank : this.rafters.values() ) {
            priceTotal = priceTotal + plank.getPriceWithAmount();
        }
        
        return priceTotal;
    }
    
    @Override
    public String toString()
    {
        return this.toString( new StringBuilder() );
    }
    
    public String toString( StringBuilder stringBuilder )
    {
        stringBuilder.append( "------BOM------" ).append( System.lineSeparator() );
        
        stringBuilder.append( System.lineSeparator() );
        stringBuilder.append( "--Misc Info--" ).append( System.lineSeparator() );
        
        stringBuilder.append( "Amount of Rows = " ).append( this.rowAmount ).append( System.lineSeparator() );
        
        stringBuilder.append( System.lineSeparator() );
        stringBuilder.append( "--Posts--" ).append( System.lineSeparator() );
        
        for ( Plank plank : this.posts.values() ) {
            stringBuilder.append( plank.toString() ).append( System.lineSeparator() );
        }
        
        stringBuilder.append( System.lineSeparator() );
        stringBuilder.append( "--Beams--" ).append( System.lineSeparator() );
        
        for ( Plank plank : this.beams.values() ) {
            stringBuilder.append( plank.toString() ).append( System.lineSeparator() );
        }
        
        stringBuilder.append( System.lineSeparator() );
        stringBuilder.append( "--Rafters--" ).append( System.lineSeparator() );
        
        for ( Plank plank : this.rafters.values() ) {
            stringBuilder.append( plank.toString() ).append( System.lineSeparator() );
        }
        
        int sumLength = 0;
        for ( Plank beam : this.getBeams().values() ) {
            sumLength = sumLength + ( beam.getLength() * beam.getAmount() );
        }
        
        sumLength = sumLength / this.getRowAmount();
        
        stringBuilder.append( System.lineSeparator() );
        stringBuilder.append( "Beam sum length = " ).append( sumLength );
        stringBuilder.append( System.lineSeparator() );
        
        return stringBuilder.toString();
    }
    
    
    
    
    //Getters and Setters
    
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
    
    
    
}
