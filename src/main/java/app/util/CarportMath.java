package app.util;

import app.web.constants.Config;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CarportMath
{
    public static BigDecimal percentageToDecimal(Integer percentage){
        BigDecimal percentagePlus100 = new BigDecimal( 100 + percentage ).setScale( 10, RoundingMode.HALF_UP );
        
        int percentageDivisor = 100;
        
        BigDecimal decimal = percentagePlus100.divide( BigDecimal.valueOf( percentageDivisor ), RoundingMode.HALF_UP );
        return decimal;
    }
}
