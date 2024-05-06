package app.services.bom;

import app.web.entities.Plank;

import java.util.List;
import java.util.Map;

public interface ValidPlanks  //TODO: Finish this
{
    
    //Getters and Setters
    Map< Integer, Plank > getBoards();
    
    void setBoards(  Map< Integer, Plank > boards );
    
    Map< Integer, Plank > getLaths();
    
    void setLaths(  Map< Integer, Plank > laths );
    
    Map< Integer, Plank > getBeams();
    
    void setBeams(  Map< Integer, Plank > beams );
    
    Map< Integer, Plank > getRafters();
    
    void setRafters(  Map< Integer, Plank > rafters );
    
    Map< Integer, Plank > getPosts();
    
    void setPosts(  Map< Integer, Plank > posts );
    
}
