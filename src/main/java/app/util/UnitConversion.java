package app.util;

public class UnitConversion
{
    public static final int DRAW_HEIGHT = 600;
    public static final int DRAW_WIDTH = 800;

    
    int carportHeight;
    int carportWidth;

    
    double heightDrawUnitsPrMm;
    double widthDrawUnitsPrMm;
    public UnitConversion(  int carportHeight, int carportWidth )
    {
        this.carportHeight = carportHeight;
        this.carportWidth = carportWidth;

        heightDrawUnitsPrMm = (double) DRAW_HEIGHT / (double) this.carportHeight;
        widthDrawUnitsPrMm = (double) DRAW_WIDTH / (double) this.carportWidth;
    }
    
    public double heightMmToDrawUnits( int mm ) {
        return conversion( mm, this.heightDrawUnitsPrMm );
    }
   
    public double widthMmToDrawUnits(int mm) {
        return conversion( mm, this.widthDrawUnitsPrMm );
    }
    private double conversion(int mm, double unitsPrMm){
        return (mm * unitsPrMm) * 0.1;
    }
}
