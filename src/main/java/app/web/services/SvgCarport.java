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
        String outerSvgRectStyle = "stroke: #000000; stroke-width: 1px; fill: none";
        int outerSvgRectHeight = 700; //100 units bigger than the canvas
        int outerSvgRectWidth = 900; //100 units bigger than the canvas

        int heightArrowXValue = 50;
        int heightArrowFirstYValue = 620;
        int heightArrowSecondYValue = 20;
        int heightTextXValue = 30;
        int heightTextYValue = 300;
        int heightTextRotationValue = -90;

        int widthArrowYValue = 650;
        int widthArrowFirstXValue = 80;
        int widthArrowSecondXValue = 880;
        int widthTextXValue = 470;
        int widthTextYValue = 680;
        int widthTextRotationValue = 0;

        carportSvg.addRectangle(origoXYValue,origoXYValue,outerSvgRectHeight,outerSvgRectWidth,outerSvgRectStyle);
        carportSvg.addArrow(heightArrowXValue, heightArrowFirstYValue,heightArrowXValue,heightArrowSecondYValue);
        carportSvg.addArrow(widthArrowFirstXValue, widthArrowYValue,widthArrowSecondXValue,widthArrowYValue);
        carportSvg.addText(heightTextXValue,heightTextYValue,heightTextRotationValue, MetricConversion.mmToCm(unitConversion.getCarportHeight())+" cm");
        carportSvg.addText(widthTextXValue,widthTextYValue,widthTextRotationValue,MetricConversion.mmToCm(unitConversion.getCarportWidth())+" cm");

//        inner svg setup
        String innerSvgHeight = "600";
        String innerSvgWidth = "800";

        String innerSvgRectStyle = "stroke: #000000; stroke-width: 1px; fill: #ffffff";
        int innerSvgRectHeight = 600; //constant width for the canvas
        int innerSvgRectWidth = 800; //constant width for the canvas

        carportSvg.addSvg(80,20,"0 0 800 600", "800", "600");
        carportSvg.addRectangle(origoXYValue,origoXYValue,innerSvgRectHeight,innerSvgRectWidth,innerSvgRectStyle);

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
        rafterAmount = rafterAmount - amountIfFixedSideRafters; //removes the amount of rafters used from the total amount of rafters
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

        carportSvg.addRectangle(rafterWidth,yCenterFigure(offsetFromTop,beamHeight),beamHeight,totalBeamFillingLength,rectStandardStyle);
        carportSvg.addRectangle(rafterWidth,yCenterFigure(offsetFromBottom,beamHeight),beamHeight,totalBeamFillingLength,rectStandardStyle);
    }

    public void postDrawer(){
        
        double postWidth = posts.get(0).getDrawWidth(unitConversion);
        double postHeight = posts.get(0).getDrawHeight(unitConversion);
        double postAmount = posts.get(0).getAmount();
        int amountOfFixedCornerPosts = 4;

        carportSvg.addRectangle(offsetFromLeft + rafterWidth,yCenterFigure(offsetFromTop,postHeight) , postHeight,postWidth,rectStandardStyle);
        carportSvg.addRectangle(offsetFromRight - rafterWidth,yCenterFigure(offsetFromTop,postHeight), postHeight,postWidth,rectStandardStyle);
        carportSvg.addRectangle(offsetFromLeft + rafterWidth,yCenterFigure(offsetFromBottom,postHeight), postHeight,postWidth,rectStandardStyle);
        carportSvg.addRectangle(offsetFromRight - rafterWidth,yCenterFigure(offsetFromBottom,postHeight), postHeight,postWidth,rectStandardStyle);
        postAmount = postAmount - amountOfFixedCornerPosts; //removes the amount of posts used from the total amount of posts

        double xValuePosts = 0;
        for (Plank beam : beams) {
            beamAmount = beam.getAmount();
            beamWidth = beam.getDrawWidth(unitConversion);
            int beamAmountDivideToHalfNumber = 2;
            int foriLoopAmount = (int) (beamAmount / beamAmountDivideToHalfNumber);
            int amountOfPostsPlacedPerLoop = 2;

            for (int i = 0; i < foriLoopAmount; i++) {
//                    if (0 < totalBeamFillingLength - ((beamWidth * (i+1)) + postWidth) && 1 < postAmount){ //checks if there is space left to place posts and checks if there are any posts left
                    if (1 < postAmount){ //checks if there is space left to place posts and checks if there are any posts left

                        xValuePosts = xValuePosts + beamWidth;

//                        if (i == 0){
                            carportSvg.addRectangle(xCenterFigureNegative(xValuePosts,postWidth),yCenterFigure(offsetFromTop,postHeight), postHeight,postWidth,rectStandardStyle);
                            carportSvg.addRectangle(xCenterFigureNegative(xValuePosts,postWidth),yCenterFigure(offsetFromBottom,postHeight), postHeight,postWidth,rectStandardStyle);
//                        } else {
//                            carportSvg.addRectangle(xValuePosts,yCenterFigure(offsetFromTop,beamHeight), postHeight,postWidth,rectStandardStyle);
//                            carportSvg.addRectangle(xValuePosts,yCenterFigure(offsetFromBottom,beamHeight), postHeight,postWidth,rectStandardStyle);
//                        }
//                        System.out.println("posts placed at: "+xValuePosts); //tells you that the placement of posts was successful and informs you of the location
                        postAmount = postAmount - amountOfPostsPlacedPerLoop; //removes the amount of posts used from the total amount of posts
                    }
                    else {
//                        System.out.println("couldn't place posts at: "+xValuePosts); //primitive error message used to indicate if posts could not get placed and informs about the location
                    }
//                    System.out.println("amount of posts left: "+postAmount);
                    if (postAmount == 0){
                        break; //breaks out of the loop if there are no more posts left
                    }
            }
        }

    }

    private double xCenterFigureNegative(double xValue, double figureWidth){
        return xValue - (figureWidth / 2);
    }

    private double yCenterFigure(double yValue, double figureHeight){
        return yValue - (figureHeight / 2);
    }

}
