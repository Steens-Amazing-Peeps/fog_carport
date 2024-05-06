package app.web.services.bom;

import app.web.entities.Plank;

import java.util.Map;

public class ValidPlanksImpl implements ValidPlanks  //TODO: Finish this
{
    Map< Integer, Plank > boards; //Brædt
    Map< Integer, Plank > laths; //Lægter
    Map< Integer, Plank > beams; //Reglar
    Map< Integer, Plank > rafters; //Spærtræ
    Map< Integer, Plank > posts; //Stolpe
    

    //Getters and Setters
    @Override
    public Map< Integer, Plank > getBoards()
    {
        return this.boards;
    }
    
    @Override
    public void setBoards( Map< Integer, Plank > boards )
    {
        this.boards = boards;
    }
    
    @Override
    public Map< Integer, Plank > getLaths()
    {
        return this.laths;
    }
    
    @Override
    public void setLaths( Map< Integer, Plank > laths )
    {
        this.laths = laths;
    }
    
    @Override
    public Map< Integer, Plank > getBeams()
    {
        return this.beams;
    }
    
    @Override
    public void setBeams( Map< Integer, Plank > beams )
    {
        this.beams = beams;
    }
    
    @Override
    public Map< Integer, Plank > getRafters()
    {
        return this.rafters;
    }
    
    @Override
    public void setRafters( Map< Integer, Plank > rafters )
    {
        this.rafters = rafters;
    }
    
    @Override
    public Map< Integer, Plank > getPosts()
    {
        return this.posts;
    }
    
    @Override
    public void setPosts( Map< Integer, Plank > posts )
    {
        this.posts = posts;
    }
    
}
