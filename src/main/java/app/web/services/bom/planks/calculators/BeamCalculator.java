package app.web.services.bom.planks.calculators;

import app.web.entities.Plank;

import java.util.List;
import java.util.Map;

public interface BeamCalculator
{
    
    List< Plank > calcBeamsOnPosts( Map< Integer, Plank > validPlanks, int carportLength, int rowAmount, int polePrice );
    
  
}
