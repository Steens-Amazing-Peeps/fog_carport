package app.web.entities;

import app.util.UnitConversion;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.Objects;

/**
 * "Note: this class has a natural ordering that is
 * inconsistent with equals."
 **/
public class Plank implements Comparable< Plank >
{
    
    //Types
    public static int BOARD = 0; //Brædt
    public static String BOARD_STRING = "brædt"; //Brædt
    public static int LATH = 1; //Lægte
    public static String LATH_STRING = "lægte"; //Lægte
    public static int BEAM = 2; //Reglar
    public static String BEAM_STRING = "reglar"; //Reglar
    public static int RAFTER = 3; //Spærtræ
    public static String RAFTER_STRING = "spærtræ"; //Spærtræ
    public static int POST = 4; //Stolpe
    public static String POST_STRING = "stolpe"; //Stolpe
    
    private Integer id = null;
    private Integer height = null;
    private Integer width = null;
    private Integer length = null;
    private Integer type = null;
    private Integer amount = null;
    private Integer price = null;
    private String material;
    private String treatment;
    
    private BigDecimal pricePrMm = null;
    private int postPrice = 0;
    
    
    public Plank()
    {
    }
    
    public Plank( Integer id, Integer height, Integer width, Integer length, Integer type, Integer pricePrMm )
    {
        this.id = id;
        this.height = height;
        this.width = width;
        this.length = length;
        this.type = type;
        this.price = pricePrMm * length;
    }
    
    public Plank( Plank plankToCopy )
    {
        this.id = plankToCopy.id;
        this.height = plankToCopy.height;
        this.width = plankToCopy.width;
        this.length = plankToCopy.length;
        this.type = plankToCopy.type;
        this.amount = plankToCopy.amount;
        this.price = plankToCopy.price;
        this.pricePrMm = plankToCopy.pricePrMm;
        this.postPrice = plankToCopy.postPrice;
        this.material = plankToCopy.material;
        this.treatment = plankToCopy.treatment;
    }
    
    public static int convertTypeToInt( String type )
    {
        if ( Objects.equals( type, BOARD_STRING ) ) {
            return BOARD;
        }
        
        if ( Objects.equals( type, LATH_STRING ) ) {
            return LATH;
        }
        
        if ( Objects.equals( type, BEAM_STRING ) ) {
            return BEAM;
        }
        
        if ( Objects.equals( type, RAFTER_STRING ) ) {
            return RAFTER;
        }
        
        if ( Objects.equals( type, POST_STRING ) ) {
            return POST;
        }
        
        return -1;
    }
    
    public static String convertTypeToString( int type )
    {
        if ( Objects.equals( type, BOARD ) ) {
            return BOARD_STRING;
        }
        
        if ( Objects.equals( type, LATH ) ) {
            return LATH_STRING;
        }
        
        if ( Objects.equals( type, BEAM ) ) {
            return BEAM_STRING;
        }
        
        if ( Objects.equals( type, RAFTER ) ) {
            return RAFTER_STRING;
        }
        
        if ( Objects.equals( type, POST ) ) {
            return POST_STRING;
        }
        
        return null;
    }
    

    
    //Getters and Setters
    public Integer getId()
    {
        return this.id;
    }
    
    public void setId( Integer id )
    {
        this.id = id;
    }
    
    public Integer getHeight()
    {
        return this.height;
    }
    
    public void setHeight( Integer height )
    {
        this.height = height;
    }
    
    public Integer getWidth()
    {
        return this.width;
    }
    
    public void setWidth( Integer width )
    {
        this.width = width;
    }
    
    
    public Integer getLength()
    {
        return this.length;
    }
    
    public void setLength( Integer length )
    {
        this.length = length;
    }
    
    public Integer getAmount()
    {
        return this.amount;
    }
    
    public void setAmount( Integer amount )
    {
        this.amount = amount;
    }
    
    public Integer getPrice()
    {
        return this.price;
    }
    
    public void setPrice( Integer price )
    {
        this.price = price;
    }
    
    public Integer getType()
    {
        return this.type;
    }
    
    public void setType( Integer type )
    {
        this.type = type;
    }
    
    public BigDecimal getPricePrMm()
    {
        return this.pricePrMm;
    }
    
    public void setPricePrMm( BigDecimal pricePrMm )
    {
        this.pricePrMm = pricePrMm;
    }
    
    public Integer getPostPrice()
    {
        return this.postPrice;
    }
    
    public void setPostPrice( Integer postPrice )
    {
        this.postPrice = postPrice;
    }
    
    public BigDecimal calcPricePrMm()
    {
        if ( this.price == null || this.length == null ) {
            return null;
        }
        
        BigDecimal totalPrice = new BigDecimal( this.price + this.postPrice );
        BigDecimal length = new BigDecimal( this.length );
        
        this.pricePrMm = totalPrice.divide( length, RoundingMode.HALF_UP );
        
        return this.pricePrMm;
    }
    
    public int getPriceWithPole()
    {
        return this.price + this.postPrice;
    }
    
    public int getPriceWithAmount()
    {
        if ( this.amount == null ) {
            return this.price;
        }
        
        return this.price * this.amount;
    }
    
    public String getMaterial()
    {
        return this.material;
    }
    
    public void setMaterial( String material )
    {
        this.material = material;
    }
    
    public String getTreatment()
    {
        return this.treatment;
    }
    
    public void setTreatment( String treatment )
    {
        this.treatment = treatment;
    }
    
    public double getDrawHeight( UnitConversion unitConversion )
    {
        
        if ( this.type == Plank.BOARD ) {
            return unitConversion.heightMmToDrawUnits( this.length );
        }
        
        if ( this.type == Plank.POST ) {
            return unitConversion.heightMmToDrawUnits( this.height );
        }
        
        if ( this.type == Plank.BEAM ) {
            return unitConversion.heightMmToDrawUnits( this.height );
        }
        
        if ( this.type == Plank.RAFTER ) {
            return unitConversion.heightMmToDrawUnits( this.length );
        }
        
        return -1;
    }
    
    public double getDrawWidth( UnitConversion unitConversion )
    {
        
        if ( this.type == Plank.BOARD ) {
            return unitConversion.widthMmToDrawUnits( this.height );
        }
        
        if ( this.type == Plank.POST ) {
            return unitConversion.widthMmToDrawUnits( this.width );
        }
        
        if ( this.type == Plank.BEAM ) {
            return unitConversion.widthMmToDrawUnits( this.length );
        }
        
        if ( this.type == Plank.RAFTER ) {
            return unitConversion.widthMmToDrawUnits( this.height );
        }
        
        return -1;
    }
    
    
    @Override
    public boolean equals( Object o )
    {
        if ( this == o ) {
            return true;
        }
        
        if ( o == null || this.getClass() != o.getClass() ) {
            return false;
        }
        
        Plank plank = ( Plank ) o;
        
        if ( !Objects.equals( this.id, plank.id ) ) {
            return false;
        }
        
        if ( !Objects.equals( this.height, plank.height ) ) {
            return false;
        }
        
        if ( !Objects.equals( this.width, plank.width ) ) {
            return false;
        }
        
        if ( !Objects.equals( this.length, plank.length ) ) {
            return false;
        }
        
        if ( !Objects.equals( this.type, plank.type ) ) {
            return false;
        }
        
        if ( !Objects.equals( this.amount, plank.amount ) ) {
            return false;
        }
        
        if ( !Objects.equals( this.price, plank.price ) ) {
            return false;
        }
        
        if ( !Objects.equals( this.pricePrMm, plank.pricePrMm ) ) {
            return false;
        }
        
        return true;
    }
    
    @Override
    public int compareTo( @NotNull Plank o )
    {
        return this.getPricePrMm().subtract( o.getPricePrMm() ).intValue();
    }
    
    @Override
    public String toString()
    {
        return "Plank{" +
               "id=" + this.id +
               ", height=" + this.height +
               ", width=" + this.width +
               ", length=" + this.length +
               ", type=" + this.type +
               ", amount=" + this.amount +
               ", price=" + this.price +
               ", pricePrMm=" + this.pricePrMm +
               ", polePrice=" + this.postPrice +
               ", PriceWithPole=" + this.getPriceWithPole() +
               '}';
    }
    

    
    public static class compareByLength implements Comparator< Plank >
{
    
    @Override
    public int compare( Plank o1, Plank o2 )
    {
        return o1.length - o2.length;
    }
    
}
    
}
