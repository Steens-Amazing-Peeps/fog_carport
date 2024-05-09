package app.web.services.bom.planks.calculators;

import app.web.entities.Bom;
import app.web.entities.Carport;
import app.web.entities.Plank;
import app.web.exceptions.WebInvalidInputException;
import app.web.services.bom.planks.ValidPlanks;

import java.util.List;
import java.util.Map;

public interface PlankCalculator
{
    
    int calcPostRows( Map<Integer, Plank > validPlanks, int width );
    
    
    
    Bom calcBom( ValidPlanks validPlanks, Carport carport ) throws WebInvalidInputException;
    
}
