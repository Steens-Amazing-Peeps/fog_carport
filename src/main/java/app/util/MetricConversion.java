package app.util;

import app.web.constants.Config;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MetricConversion
{
    
    public static final int MM_PR_CM = 10;
    public static final int MM_PR_M = 1000;
    
    public static final int COMMA_DIGITS_IN_CM = 1;
    public static final int COMMA_DIGITS_IN_M = 3;
    
    public static final int COMMA_DIGITS_IN_STRING_CM = Config.MetricUnits.COMMA_DIGITS_IN_CM_IN_STRING;
    public static final int COMMA_DIGITS_IN_STRING_M = Config.MetricUnits.COMMA_DIGITS_IN_M_IN_STRING;
    
    public static Integer cmToMm( BigDecimal cm )
    {
        if ( cm == null ) {
            return null;
        }
        
        return cm.multiply( BigDecimal.valueOf( MM_PR_CM ) ).intValue();
    }
    
    public static Integer mToMm( BigDecimal m )
    {
        if ( m == null ) {
            return null;
        }
        
        return m.multiply( BigDecimal.valueOf( MM_PR_M ) ).intValue();
    }
    
    
    public static BigDecimal mmToCm( Integer mm )
    {
        if ( mm == null ) {
            return null;
        }
        
        return BigDecimal.valueOf( mm, COMMA_DIGITS_IN_CM );
    }
    
    public static BigDecimal mmToM( Integer mm )
    {
        if ( mm == null ) {
            return null;
        }
        
        return BigDecimal.valueOf( mm, COMMA_DIGITS_IN_M );
    }
    
}
