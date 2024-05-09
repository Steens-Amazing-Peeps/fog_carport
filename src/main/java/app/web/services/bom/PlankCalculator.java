package app.web.services.bom;

import app.web.entities.Plank;

import java.util.List;
import java.util.Map;

public interface PlankCalculator
{
    List< Plank > resList( int totalLength, List< Integer > lengths, int lengthOption, List< Integer > prices,
                           int priceOption );
    
    int calcPostRows( Map<Integer, Plank > validPlanks, int width );
    

    
}
