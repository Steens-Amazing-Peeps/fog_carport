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
    private UnitConversion unitConversion;
    private final Svg carportSvg = new Svg(this.origoXYValue,this.origoXYValue,"0 0 900 700", "100%","auto"); //the viewbox is a made to match the html page's div

    private final String rectStandardStyle = "stroke: #000000; stroke-width: 1px; fill: #ffffff";

    private final Bom bom;
    private final List<Plank> rafters;
    private final List<Plank> beams;
    private final List<Plank> posts;
    private double totalBeamFillingLength;
    private final double drawWidth = UnitConversion.DRAW_WIDTH.intValue();
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
        System.out.println(this.bom);

        this.unitConversion = new UnitConversion(carport.getWidth(),carport.getLength());

        this.rafters = this.bom.getRafters().values().stream().toList();
        this.beams = this.bom.getBeams().values().stream().toList();
        this.posts = this.bom.getPosts().values().stream().toList();
    }


    public String drawCarport(){
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

        this.carportSvg.addRectangle(this.origoXYValue, this.origoXYValue,outerSvgRectHeight,outerSvgRectWidth,outerSvgRectStyle);
        this.carportSvg.addArrow(heightArrowXValue, heightArrowFirstYValue,heightArrowXValue,heightArrowSecondYValue);
        this.carportSvg.addArrow(widthArrowFirstXValue, widthArrowYValue,widthArrowSecondXValue,widthArrowYValue);
        this.carportSvg.addText(heightTextXValue,heightTextYValue,heightTextRotationValue, MetricConversion.mmToCm(this.unitConversion.getCarportHeight())+" cm");
        this.carportSvg.addText(widthTextXValue,widthTextYValue,widthTextRotationValue,MetricConversion.mmToCm(this.unitConversion.getCarportWidth())+" cm");

//        inner svg setup
        String innerSvgHeight = "600";
        String innerSvgWidth = "800";

        String innerSvgRectStyle = "stroke: #000000; stroke-width: 1px; fill: #ffffff";
        int innerSvgRectHeight = 600; //constant width for the canvas
        int innerSvgRectWidth = 800; //constant width for the canvas

        this.carportSvg.addSvg(80,20,"0 0 800 600", "800", "600");
        this.carportSvg.addRectangle(this.origoXYValue, this.origoXYValue,innerSvgRectHeight,innerSvgRectWidth,innerSvgRectStyle);

//        methods for drawing the actual carport
        this.rafterDrawer();
        this.beamDrawer();
        this.rafterInnerDrawer();
        this.postDrawer();

        return this.carportSvg.toString();
    }

    public void rafterDrawer(){
        this.rafterWidth = this.rafters.get(0).getDrawWidth(this.unitConversion);
        this.rafterHeight = this.rafters.get(0).getDrawHeight(this.unitConversion);
        this.rafterAmount = this.rafters.get(0).getAmount();
        int amountIfFixedSideRafters = 2;

        this.carportSvg.addRectangle(this.origoXYValue, this.origoXYValue, this.rafterHeight, this.rafterWidth, this.rectStandardStyle);
        this.carportSvg.addRectangle(this.drawWidth - this.rafterWidth, this.origoXYValue, this.rafterHeight, this.rafterWidth, this.rectStandardStyle);
        this.rafterAmount = this.rafterAmount - amountIfFixedSideRafters; //removes the amount of rafters used from the total amount of rafters
    }

    public void rafterInnerDrawer(){
        int mathEqualSpacingDifferenceConstant = 1;
        double distanceEach = (this.totalBeamFillingLength / (this.rafterAmount + mathEqualSpacingDifferenceConstant) + this.rafterWidth / this.rafterAmount);

        for (int i = 0; i < this.rafterAmount; i++) {
            this.carportSvg.addRectangle(this.xCenterFigure((distanceEach * (i+1)), this.rafterWidth), this.origoXYValue, this.rafterHeight, this.rafterWidth, this.rectStandardStyle);
        }
    }

    public void beamDrawer(){
        this.beamHeight = this.beams.get(0).getDrawHeight(this.unitConversion);
        int multiplyValueToGetBothRafterWidths = 2;
        this.totalBeamFillingLength = this.drawWidth - (this.rafterWidth * multiplyValueToGetBothRafterWidths);

        this.carportSvg.addRectangle(this.rafterWidth, this.yCenterFigure(this.offsetFromTop, this.beamHeight), this.beamHeight, this.totalBeamFillingLength, this.rectStandardStyle);
        this.carportSvg.addRectangle(this.rafterWidth, this.yCenterFigure(this.offsetFromBottom, this.beamHeight), this.beamHeight, this.totalBeamFillingLength, this.rectStandardStyle);
    }

    public void postDrawer(){
        
        double postWidth = this.posts.get(0).getDrawWidth(this.unitConversion);
        double postHeight = this.posts.get(0).getDrawHeight(this.unitConversion);
        double postAmount = this.posts.get(0).getAmount();
        int amountOfFixedCornerPosts = 4;

        this.carportSvg.addRectangle(this.offsetFromLeft + this.rafterWidth, this.yCenterFigure(this.offsetFromTop,postHeight) , postHeight,postWidth, this.rectStandardStyle);
        this.carportSvg.addRectangle(this.offsetFromRight - this.rafterWidth - postWidth, this.yCenterFigure(this.offsetFromTop,postHeight), postHeight,postWidth, this.rectStandardStyle);
        this.carportSvg.addRectangle(this.offsetFromLeft + this.rafterWidth, this.yCenterFigure(this.offsetFromBottom,postHeight), postHeight,postWidth, this.rectStandardStyle);
        this.carportSvg.addRectangle(this.offsetFromRight - this.rafterWidth - postWidth, this.yCenterFigure(this.offsetFromBottom,postHeight), postHeight,postWidth, this.rectStandardStyle);
        postAmount = postAmount - amountOfFixedCornerPosts; //removes the amount of posts used from the total amount of posts

        double xValuePosts = 0;
        for (Plank beam : this.beams) {
            this.beamAmount = beam.getAmount();
            this.beamWidth = beam.getDrawWidth(this.unitConversion);
            int beamAmountDivideToHalfNumber = 2;
            int foriLoopAmount = (int) (this.beamAmount / beamAmountDivideToHalfNumber);
            int amountOfPostsPlacedPerLoop = 2;

            for (int i = 0; i < foriLoopAmount; i++) {
//                    if (0 < totalBeamFillingLength - ((beamWidth * (i+1)) + postWidth) && 1 < postAmount){ //checks if there is space left to place posts and checks if there are any posts left
                    if (1 < postAmount){ //checks if there is space left to place posts and checks if there are any posts left

                        xValuePosts = xValuePosts + this.beamWidth;

//                        if (i == 0){
                        this.carportSvg.addRectangle(this.xCenterFigure(xValuePosts,postWidth), this.yCenterFigure(this.offsetFromTop,postHeight), postHeight,postWidth, this.rectStandardStyle);
                        this.carportSvg.addRectangle(this.xCenterFigure(xValuePosts,postWidth), this.yCenterFigure(this.offsetFromBottom,postHeight), postHeight,postWidth, this.rectStandardStyle);
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

    private double xCenterFigure(double xValue, double figureWidth){
        return xValue - (figureWidth / 2);
    }

    private double yCenterFigure(double yValue, double figureHeight){
        return yValue - (figureHeight / 2);
    }

}
