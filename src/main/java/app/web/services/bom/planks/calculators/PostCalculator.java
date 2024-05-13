package app.web.services.bom.planks.calculators;

import app.web.entities.Plank;

import java.util.Map;

public interface PostCalculator
{
    
    Plank findShortestUsablePost( Map< Integer, Plank > validPosts, int carportHeight );
    
}
