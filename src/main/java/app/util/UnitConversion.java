package app.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class UnitConversion
{
    public static final BigDecimal DRAW_HEIGHT = new BigDecimal(600).setScale(10,RoundingMode.HALF_UP);
    public static final BigDecimal DRAW_WIDTH = new BigDecimal(800).setScale(10,RoundingMode.HALF_UP);

    
    int carportHeight;
    int carportWidth;

    
    BigDecimal heightDrawUnitsPrMm;
    BigDecimal widthDrawUnitsPrMm;
    public UnitConversion(  int carportHeight, int carportWidth )
    {
        this.carportHeight = carportHeight;
        this.carportWidth = carportWidth;
        
        this.heightDrawUnitsPrMm = DRAW_HEIGHT.divide(BigDecimal.valueOf(this.carportHeight), RoundingMode.HALF_UP);
        this.widthDrawUnitsPrMm = DRAW_WIDTH.divide(BigDecimal.valueOf(this.carportWidth), RoundingMode.HALF_UP);
    }
    
    public double heightMmToDrawUnits( int mm ) {
        return this.conversion( mm, this.heightDrawUnitsPrMm ).doubleValue();
    }
   
    public double widthMmToDrawUnits(int mm) {
        return this.conversion( mm, this.widthDrawUnitsPrMm ).doubleValue();
    }
    private BigDecimal conversion(int mm, BigDecimal unitsPrMm){
        return unitsPrMm.multiply(BigDecimal.valueOf(mm));
    }

    public int getCarportHeight() {
        return this.carportHeight;
    }

    public int getCarportWidth() {
        return this.carportWidth;
    }

}
