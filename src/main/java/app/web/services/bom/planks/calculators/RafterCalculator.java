package app.web.services.bom.planks.calculators;

import app.web.entities.Plank;
import app.web.exceptions.WebInvalidInputException;

import java.util.List;
import java.util.Map;

public interface RafterCalculator
{
    
    Plank findShortestUsableRafter( Map< Integer, Plank > validRafters, int carportSegmentWidth ) throws WebInvalidInputException;
    
    List< Plank > findShortestUsableRafter( Map< Integer, Plank> rafters, Integer width, int rowAmount, int splitCarportSegmentIntoTwoSegmentsAtThisWidth ) throws WebInvalidInputException;
    
}
