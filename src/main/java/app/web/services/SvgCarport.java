package app.web.services;

import app.util.MetricConversion;
import app.util.UnitConversion;
import app.web.entities.Bom;
import app.web.entities.Carport;
import app.web.entities.Plank;
import app.web.exceptions.WebInvalidInputException;

import java.util.List;
import java.util.Locale;

public class SvgCarport {
    private static int id = 0;
    private UnitConversion unitConversion;
    private Svg carportSvg = new Svg(this.origoXYValue,this.origoXYValue,"0 0 900 700", "100%","auto"); //the viewbox is a made to match the html page's div

    private final String rectStandardStyle = "stroke: #000000; stroke-width: 1px; fill: #ffffff";

    private Bom bom;
    private List<Plank> rafters;
    private List<Plank> beams;
    private List<Plank> posts;
    private double totalBeamFillingLength;
    private double drawWidth = unitConversion.DRAW_WIDTH.intValue();
    private final int origoXYValue = 0;

    private final int offsetFromRight = 750;
    private final int offsetFromLeft = 50;
    private final int offsetFromTop = 50;
    private final int offsetFromBottom = 550;

    private double rafterWidth = 0; //it gets set later
    private double rafterHeight = 0; //it gets set later
    private double rafterAmount = 0; //it gets set later
    private double beamWidth = 0;
    private double beamHeight = 0;
    private double beamAmount = 0;

    public SvgCarport(Carport carport) throws WebInvalidInputException {

        this.bom = carport.calcBom();
        System.out.println(bom);

        unitConversion = new UnitConversion(carport.getWidth(),carport.getLength());

        rafters = bom.getRafters().values().stream().toList();
        beams = bom.getBeams().values().stream().toList();
        posts = bom.getPosts().values().stream().toList();
    }


    public String drawCarport(){ //TODO: make constants for all manually placed numbers
        Locale.setDefault(new Locale("US")); //needed if you want to use decimals for svg stuff
//        outer svg setup
        carportSvg.addRectangle(origoXYValue,origoXYValue,700,900,"stroke: #000000; stroke-width: 1px; fill: none");
        carportSvg.addArrow(50, 620,50,20);
        carportSvg.addArrow(80, 650,880,650);
        carportSvg.addText(30,300,-90, MetricConversion.mmToCm(unitConversion.getCarportHeight())+" cm");
        carportSvg.addText(470,680,0,MetricConversion.mmToCm(unitConversion.getCarportWidth())+" cm");

//        inner svg setup
        carportSvg.addSvg(80,20,"0 0 800 600", "800", "600");
        carportSvg.addRectangle(origoXYValue,origoXYValue,600,800,"stroke: #000000; stroke-width: 1px; fill: #ffffff");
//        methods for drawing the actual carport
        rafterDrawer();
        beamDrawer();
        rafterInnerDrawer();
        postDrawer();

        return carportSvg.toString();
    }

    public void rafterDrawer(){
        rafterWidth = rafters.get(0).getDrawWidth(unitConversion);
        rafterHeight = rafters.get(0).getDrawHeight(unitConversion);
        rafterAmount = rafters.get(0).getAmount();
        int amountIfFixedSideRafters = 2;

        carportSvg.addRectangle(origoXYValue, origoXYValue, rafterHeight, rafterWidth, rectStandardStyle);
        carportSvg.addRectangle(drawWidth - rafterWidth, origoXYValue, rafterHeight, rafterWidth, rectStandardStyle);
        rafterAmount = rafterAmount - amountIfFixedSideRafters;
    }

    public void rafterInnerDrawer(){
        int mathEqualSpacingDifferenceConstant = 1;
        double distanceEach = (totalBeamFillingLength / (rafterAmount + mathEqualSpacingDifferenceConstant) + rafterWidth / rafterAmount );

        for (int i = 0; i < rafterAmount; i++) {
            carportSvg.addRectangle(xCenterFigureNegative((distanceEach * (i+1)),rafterWidth), origoXYValue,rafterHeight,rafterWidth,rectStandardStyle);
        }
    }

    public void beamDrawer(){
        beamHeight = beams.get(0).getDrawHeight(unitConversion);
        int multiplyValueToGetBothRafterWidths = 2;
        totalBeamFillingLength = drawWidth - (rafterWidth * multiplyValueToGetBothRafterWidths);

        carportSvg.addRectangle(rafterWidth,offsetFromTop,beamHeight,totalBeamFillingLength,rectStandardStyle);
        carportSvg.addRectangle(rafterWidth,offsetFromBottom,beamHeight,totalBeamFillingLength,rectStandardStyle);
    }

    public void postDrawer(){
        
        double postWidth = posts.get(0).getDrawWidth(unitConversion);
        double postHeight = posts.get(0).getDrawHeight(unitConversion);
        double postAmount = posts.get(0).getAmount();
        int amountOfFixedCornerPosts = 4;

        carportSvg.addRectangle(offsetFromLeft + rafterWidth,yCenterFigure(offsetFromTop,beamHeight) , postHeight,postWidth,rectStandardStyle);
        carportSvg.addRectangle(offsetFromRight - rafterWidth,yCenterFigure(offsetFromTop,beamHeight), postHeight,postWidth,rectStandardStyle);
        carportSvg.addRectangle(offsetFromLeft + rafterWidth,yCenterFigure(offsetFromBottom,beamHeight), postHeight,postWidth,rectStandardStyle);
        carportSvg.addRectangle(offsetFromRight - rafterWidth,yCenterFigure(offsetFromBottom,beamHeight), postHeight,postWidth,rectStandardStyle);
        postAmount = postAmount - amountOfFixedCornerPosts;

        double xValuePosts = 0;
        for (Plank beam : beams) {
            beamAmount = beam.getAmount();
            beamWidth = beam.getDrawWidth(unitConversion);
            int beamAmountDivideToHalfNumber = 2;
            int foriLoopAmount = (int) (beamAmount / beamAmountDivideToHalfNumber);
            int amountOfPostsPlacedPerLoop = 2;

            for (int i = 0; i < foriLoopAmount; i++) {
                    if (0 < totalBeamFillingLength - (beamWidth * (i+1)) + rafterWidth && 1 < postAmount){

                        xValuePosts = xValuePosts + beamWidth;

                        if (i == 0){
                            carportSvg.addRectangle(xCenterFigurePositive(xValuePosts,rafterWidth),yCenterFigure(offsetFromTop,beamHeight), postHeight,postWidth,rectStandardStyle);
                            carportSvg.addRectangle(xCenterFigurePositive(xValuePosts,rafterWidth),yCenterFigure(offsetFromBottom,beamHeight), postHeight,postWidth,rectStandardStyle);
                        } else {
                            carportSvg.addRectangle(xValuePosts,yCenterFigure(offsetFromTop,beamHeight), postHeight,postWidth,rectStandardStyle);
                            carportSvg.addRectangle(xValuePosts,yCenterFigure(offsetFromBottom,beamHeight), postHeight,postWidth,rectStandardStyle);
                        }
                        System.out.println("posts placed at: "+xValuePosts);
                        postAmount = postAmount - amountOfPostsPlacedPerLoop;
                    }
                    else {
                        System.out.println("couldn't place posts at: "+xValuePosts);
                    }
                    System.out.println("amount of posts left: "+postAmount);
                    if (postAmount == 0){
                        break;
                    }
            }
        }

    }

    private double xCenterFigureNegative(double xValue, double figureWidth){
        return xValue - (figureWidth / 2);
    }

    private double xCenterFigurePositive(double xValue, double figureWidth){
        return xValue + (figureWidth / 2);
    }

    private double yCenterFigure(double yValue, double figureHeight){
        return yValue - (figureHeight / 2);
    }

}
