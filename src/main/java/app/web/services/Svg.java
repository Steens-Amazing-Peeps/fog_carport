package app.web.services;

public class Svg
{
    
    private static final String SVG_TEMPLATE = "<svg x=\"%d\" y=\"%d\" viewBox=\"%s\" width=\"%s\" height=\"%s\" preserveAspectRatio=\\\"xMinYMin\\\">";
    private static final String SVG_RECT_TEMPLATE = "<rect x=\"%f\" y=\"%f\" height=\"%f\" width=\"%f\" style=\"%s\"/></rect>";
    private static final String SVG_ARROW_DEFS = "<defs>\n" +
                                                 "        <marker id=\"beginArrow\" markerWidth=\"12\" markerHeight=\"12\" refX=\"0\" refY=\"6\" orient=\"auto\"> <path d=\"M0,6 L12,0 L12,12 L0,6\" style=\"fill: #000000;\" /> </marker>\n" +
                                                 "        <marker id=\"endArrow\" markerWidth=\"12\" markerHeight=\"12\" refX=\"12\" refY=\"6\" orient=\"auto\"> <path d=\"M0,0 L12,6 L0,12 L0,0 \" style=\"fill: #000000;\"/> </marker>\n" +
                                                 "</defs>";
    private static final String SVG_LINE_TEMPLATE = "<line x1=\"%d\" y1=\"%d\" x2=\"%d\" y2=\"%d\" style=\"%s\" />";
    private static final String SVG_ARROW_TEMPLATE = "<line x1=\"%d\" y1=\"%d\" x2=\"%d\" y2=\"%d\" style=\"stroke:#000000; marker-end: url(#endArrow); marker-start: url(#beginArrow);\"></line>";
    private static final String SVG_TEXT_TEMPLATE = "<text style=\"text-anchor: middle\" transform=\"translate(%d,%d) rotate(%d)\">%s</text>";
    
    private final StringBuilder svg = new StringBuilder();
    
    public Svg( int x, int y, String viewBox, String width, String height )
    {
        this.svg.append( String.format( SVG_TEMPLATE, x, y, viewBox, width, height ) );
        this.svg.append( SVG_ARROW_DEFS );
    }
    
    public void addRectangle( double x, double y, double height, double width, String style )
    {
        this.svg.append( String.format( SVG_RECT_TEMPLATE, x, y, height, width, style ) );
    }
    
    public void addLine( int x1, int y1, int x2, int y2, String style )
    {
        this.svg.append( String.format( SVG_LINE_TEMPLATE, x1, y1, x2, y2, style ) );
    }
    
    public void addArrow( int x1, int y1, int x2, int y2 )
    {
        this.svg.append( String.format( SVG_ARROW_TEMPLATE, x1, y1, x2, y2 ) );
    }
    
    public void addText( int x, int y, int rotation, String text )
    {
        this.svg.append( String.format( SVG_TEXT_TEMPLATE, x, y, rotation, text ) );
    }
    
    public void addSvg( int x, int y, String viewBox, String width, String height )
    {
        this.svg.append( String.format( SVG_TEMPLATE, x, y, viewBox, width, height ) );
    }
    
    public void addString( String s )
    {
        this.svg.append( s );
    }
    
    @Override
    public String toString()
    {
        return this.svg.append( "</svg>" ).toString();
    }
    
}
