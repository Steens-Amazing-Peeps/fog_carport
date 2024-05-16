package app.web.services;

import app.util.MetricConversion;
import app.util.UnitConversion;
import app.web.entities.Bom;
import app.web.entities.Carport;
import app.web.entities.Plank;
import app.web.exceptions.WebInvalidInputException;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SvgCarport {
    private static int id = 0;
    private UnitConversion unitConversion = new UnitConversion(600,780);
    private Svg carportSvg = new Svg(0,0,"0 0 900 700", "100%","auto");

    private final String rectStandardStyle = "stroke: #000000; stroke-width: 1px; fill: #ffffff";

    private Bom bom;
    private List<Plank> rafters;
    private List<Plank> beams;
    private List<Plank> posts;
    private double beamToOtherMath;

    private final int offsetFromRight = 750;
    private final int offsetFromLeft = 50;
    private final int offsetFromTop = 50;
    private final int offsetFromBottom = 550;

    public SvgCarport(Carport carport) throws WebInvalidInputException {

        this.bom = carport.calcBom();
        System.out.println(bom);

        unitConversion.setCarportHeight(carport.getWidth());
        unitConversion.setCarportWidth(carport.getLength());
        rafters = bom.getRafters().values().stream().toList();
        beams = bom.getBeams().values().stream().toList();
        posts = bom.getPosts().values().stream().toList();
    }


    public String drawCarport(){
        Locale.setDefault(new Locale("US"));
//        outer svg setup
        carportSvg.addRectangle(0,0,700,900,"stroke: #000000; stroke-width: 1px; fill: none");
        carportSvg.addArrow(50, 620,50,20);
        carportSvg.addArrow(80, 650,880,650);
        carportSvg.addText(30,300,-90, MetricConversion.mmToCm(unitConversion.getCarportHeight())+" cm");
        carportSvg.addText(470,680,0,MetricConversion.mmToCm(unitConversion.getCarportWidth())+" cm");

//        inner svg setup
        carportSvg.addSvg(80,20,"0 0 800 600", "800", "600");
        carportSvg.addRectangle(0,0,600,800,"stroke: #000000; stroke-width: 1px; fill: #ffffff");
//        methods for drawing the actual carport
        rafterDrawer();
        beamDrawer();
        rafterInnerDrawer();
        postDrawer();

        return carportSvg.toString();
    }

    public void rafterDrawer(){
        carportSvg.addRectangle(0, 0, rafters.get(0).getDrawHeight(unitConversion), rafters.get(0).getDrawWidth(unitConversion), rectStandardStyle);
        carportSvg.addRectangle(unitConversion.DRAW_WIDTH - (rafters.get(0).getDrawWidth(unitConversion)), 0, rafters.get(0).getDrawHeight(unitConversion), rafters.get(0).getDrawWidth(unitConversion), rectStandardStyle);
        rafters.get(0).setAmount(rafters.get(0).getAmount()-2);
    }

    public void rafterInnerDrawer(){
        double distanceEach = (beamToOtherMath / (rafters.get(0).getAmount() + 1) + rafters.get(0).getDrawWidth(unitConversion) / (rafters.get(0).getAmount()) );

        for (int i = 1; i < rafters.get(0).getAmount()+1; i++) {
            carportSvg.addRectangle((distanceEach * i) + rafters.get(0).getDrawWidth(unitConversion), 0,rafters.get(0).getDrawHeight(unitConversion),rafters.get(0).getDrawWidth(unitConversion),rectStandardStyle);
        }
    }

    public void beamDrawer(){
        double beamDrawingFillLength = ((unitConversion.getCarportWidth() / 10) - (rafters.get(0).getDrawWidth(unitConversion) * 2));
        double totalUsableLength = 0;
        double totalFillingLength = unitConversion.DRAW_WIDTH - (rafters.get(0).getDrawWidth(unitConversion) * 2);

        for (Plank beam : beams) {
            for (int i = 0; i < beam.getAmount(); i++) {
                totalUsableLength = totalUsableLength + beam.getDrawWidth(unitConversion);
            }
        }

        System.out.println("usable length in total: "+totalUsableLength);
        System.out.println("usable length for each side: "+totalUsableLength/2);
        System.out.println("length needing to be filled for each beam side: " + beamDrawingFillLength);
        System.out.println("filler length: "+totalFillingLength);
        System.out.println("extra length per side: "+((totalUsableLength / 2) - beamDrawingFillLength));

        if ((totalUsableLength / 2) >= beamDrawingFillLength){
        carportSvg.addRectangle(rafters.get(0).getDrawWidth(unitConversion),offsetFromTop,beams.get(0).getDrawHeight(unitConversion),totalFillingLength,rectStandardStyle);
        carportSvg.addRectangle(rafters.get(0).getDrawWidth(unitConversion),offsetFromBottom,beams.get(0).getDrawHeight(unitConversion),totalFillingLength,rectStandardStyle);
        }

        beamToOtherMath = totalFillingLength;
    }

    public void postDrawer(){

        carportSvg.addRectangle(offsetFromLeft + rafters.get(0).getDrawWidth(unitConversion),offsetFromTop - (0.75 * beams.get(0).getDrawHeight(unitConversion)), posts.get(0).getDrawHeight(unitConversion),posts.get(0).getDrawWidth(unitConversion),rectStandardStyle);
        carportSvg.addRectangle(offsetFromRight - rafters.get(0).getDrawWidth(unitConversion),offsetFromTop - (0.75 * beams.get(0).getDrawHeight(unitConversion)), posts.get(0).getDrawHeight(unitConversion),posts.get(0).getDrawWidth(unitConversion),rectStandardStyle);
        carportSvg.addRectangle(offsetFromLeft + rafters.get(0).getDrawWidth(unitConversion),offsetFromBottom - (0.75 * beams.get(0).getDrawHeight(unitConversion)), posts.get(0).getDrawHeight(unitConversion),posts.get(0).getDrawWidth(unitConversion),rectStandardStyle);
        carportSvg.addRectangle(offsetFromRight - rafters.get(0).getDrawWidth(unitConversion),offsetFromBottom - (0.75 * beams.get(0).getDrawHeight(unitConversion)), posts.get(0).getDrawHeight(unitConversion),posts.get(0).getDrawWidth(unitConversion),rectStandardStyle);
        posts.get(0).setAmount(posts.get(0).getAmount()-4);

        double xValue = 0;
        for (Plank beam : beams) {
            int foriLoopAmount = beam.getAmount() / 2;
            for (int i = 0; i < foriLoopAmount; i++) {
                    if (0 < beamToOtherMath - (unitConversion.widthMmToDrawUnits(beam.getLength()) * (i+1)) + rafters.get(0).getDrawWidth(unitConversion) && 1 < posts.get(0).getAmount()){

                        xValue = xValue + unitConversion.widthMmToDrawUnits(beam.getLength());

                        if (i == 0){
                            carportSvg.addRectangle(xValue + (0.25 * rafters.get(0).getDrawWidth(unitConversion)),offsetFromTop - (0.75 * (unitConversion.heightMmToDrawUnits(beams.get(0).getHeight()))), unitConversion.heightMmToDrawUnits(posts.get(0).getHeight()),posts.get(0).getDrawWidth(unitConversion),rectStandardStyle);
                            carportSvg.addRectangle(xValue + (0.25 * rafters.get(0).getDrawWidth(unitConversion)),offsetFromBottom - (0.75 * (unitConversion.heightMmToDrawUnits(beams.get(0).getHeight()))), unitConversion.heightMmToDrawUnits(posts.get(0).getHeight()),posts.get(0).getDrawWidth(unitConversion),rectStandardStyle);
                        } else {
                            carportSvg.addRectangle(xValue,offsetFromTop - (0.75 * (unitConversion.heightMmToDrawUnits(beams.get(0).getHeight()))), unitConversion.heightMmToDrawUnits(posts.get(0).getHeight()),posts.get(0).getDrawWidth(unitConversion),rectStandardStyle);
                            carportSvg.addRectangle(xValue,offsetFromBottom - (0.75 * (unitConversion.heightMmToDrawUnits(beams.get(0).getHeight()))), unitConversion.heightMmToDrawUnits(posts.get(0).getHeight()),posts.get(0).getDrawWidth(unitConversion),rectStandardStyle);
                        }
                    System.out.println("posts placed at: "+xValue);
                    posts.get(0).setAmount(posts.get(0).getAmount()-2);
                    }
                    else {
                        System.out.println("couldn't place posts at: "+xValue);
                    }
                    System.out.println("amount of posts left: "+posts.get(0).getAmount());
                    if (posts.get(0).getAmount() == 0){
                        break;
                    }
            }
        }

    }

}
