package app.web.services;

public class Svg {

    private static final String SVP_TEMPLATE = "<svg x=\"%d\" y=\"%d\" width=\"%s\" height=\"%s\" viewBox=\"%s\">";

    private StringBuilder svg = new StringBuilder();

    public Svg(int x, int y, String viewBox, String width, String height){
        svg.append(String.format(SVP_TEMPLATE, x, y, viewBox, width, height));
    }
    public void addRectangle(int x, int y, double height, double width, String style){}
    public void addLine(int x1, int y1, int x2, int y2, String style){}
    public void addArrow(int x1, int y1, int x2, int y2, String style){}
    public void addText(int x, int y, int rotation, String text){}
    public void addSvg(Svg innerSvg){}

    @Override
    public String toString() {
        return svg.append("</svg>").toString();
    }
}
