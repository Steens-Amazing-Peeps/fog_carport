package app.web.entities;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Bom
{
    
    private List< Plank > planks = new ArrayList<>();
    
    private Map< Integer, Plank > posts = new LinkedHashMap<>();
    private Map< Integer, Plank > beams = new LinkedHashMap<>();
    private Map< Integer, Plank > rafters = new LinkedHashMap<>();
    
    
    
    //Getters and Setters
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
