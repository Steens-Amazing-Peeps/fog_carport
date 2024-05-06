package app.web.entities;

import java.util.ArrayList;
import java.util.List;

public class Bom
{
    
    private List< Plank > planks = new ArrayList<>();
    
    
    
    //Getters and Setters
    public List< Plank > getPlanks()
    {
        return this.planks;
    }
    
    public void setPlanks( List< Plank > planks )
    {
        this.planks = planks;
    }
    
}
