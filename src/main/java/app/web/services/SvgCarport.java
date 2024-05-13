package app.web.services;

import app.util.UnitConversion;
import app.web.entities.Bom;
import app.web.entities.Plank;

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

    public SvgCarport(Bom bom) {
        this.bom = bom;
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
        carportSvg.addText(30,300,-90,unitConversion.getCarportHeight()+" cm");
        carportSvg.addText(470,680,0,unitConversion.getCarportWidth()+" cm");

//        inner svg setup
        carportSvg.addSvg(80,20,"0 0 800 600", "800", "600");
        carportSvg.addRectangle(0,0,600,800,"stroke: #000000; stroke-width: 1px; fill: #ffffff");
//        methods for drawing the actual carport
        rafterDrawer();
        beamDrawer();
        postDrawer();
        rafterInnerDrawer();

        return carportSvg.toString();
    }

    public void rafterDrawer(){
        carportSvg.addRectangle(0, 0, rafters.get(0).getDrawHeight(unitConversion), rafters.get(0).getDrawWidth(unitConversion), rectStandardStyle);
        carportSvg.addRectangle(800 - (rafters.get(0).getDrawWidth(unitConversion)), 0, rafters.get(0).getDrawHeight(unitConversion), rafters.get(0).getDrawWidth(unitConversion), rectStandardStyle);
        rafters.get(0).setAmount(rafters.get(0).getAmount()-2);
    }

    public void rafterInnerDrawer(){
        double distanceEach = (beamToOtherMath / (rafters.get(0).getAmount() + 1) + rafters.get(0).getDrawWidth(unitConversion) / (rafters.get(0).getAmount() + 1) );

        for (int i = 1; i < rafters.get(0).getAmount()+1; i++) {
            carportSvg.addRectangle((distanceEach * i), 0,rafters.get(0).getDrawHeight(unitConversion),rafters.get(0).getDrawWidth(unitConversion),rectStandardStyle);
        }
    }

    public void beamDrawer(){
        double boardDrawingFillLength = (unitConversion.DRAW_WIDTH - (rafters.get(0).getDrawWidth(unitConversion) * 2));
        double totalDrawLength = 0;

        for (Plank beam : beams) {
            for (int i = 0; i < beam.getAmount(); i++) {
                totalDrawLength = totalDrawLength + beam.getDrawWidth(unitConversion);
            }
        }

        System.out.println("usable length in total: "+totalDrawLength);
        System.out.println("usable length for each side: "+totalDrawLength/2);
        System.out.println("length needing to be filled for each board side: "+(unitConversion.DRAW_WIDTH - (rafters.get(0).getDrawWidth(unitConversion) * 2)));
        System.out.println("extra length per side: "+((totalDrawLength / 2) - boardDrawingFillLength));

        if ((totalDrawLength / 2) >= (unitConversion.DRAW_WIDTH - (rafters.get(0).getDrawWidth(unitConversion) * 2))){
        carportSvg.addRectangle(rafters.get(0).getDrawWidth(unitConversion),50,beams.get(0).getDrawHeight(unitConversion),boardDrawingFillLength,rectStandardStyle);
        carportSvg.addRectangle(rafters.get(0).getDrawWidth(unitConversion),550,beams.get(0).getDrawHeight(unitConversion),boardDrawingFillLength,rectStandardStyle);
        }

        beamToOtherMath = boardDrawingFillLength;
    }

    public void postDrawer(){

        carportSvg.addRectangle(50 + rafters.get(0).getDrawWidth(unitConversion),50 - (1.5 * beams.get(0).getDrawHeight(unitConversion)), posts.get(0).getDrawHeight(unitConversion),posts.get(0).getDrawWidth(unitConversion),rectStandardStyle);
        carportSvg.addRectangle(750 - rafters.get(0).getDrawWidth(unitConversion),50 - (1.5 * beams.get(0).getDrawHeight(unitConversion)), posts.get(0).getDrawHeight(unitConversion),posts.get(0).getDrawWidth(unitConversion),rectStandardStyle);
        carportSvg.addRectangle(50 + rafters.get(0).getDrawWidth(unitConversion),550 - (1.5 * beams.get(0).getDrawHeight(unitConversion)), posts.get(0).getDrawHeight(unitConversion),posts.get(0).getDrawWidth(unitConversion),rectStandardStyle);
        carportSvg.addRectangle(750 - rafters.get(0).getDrawWidth(unitConversion),550 - (1.5 * beams.get(0).getDrawHeight(unitConversion)), posts.get(0).getDrawHeight(unitConversion),posts.get(0).getDrawWidth(unitConversion),rectStandardStyle);
        posts.get(0).setAmount(posts.get(0).getAmount()-4);

        //TODO: check if it places them correctly on an actual drawing
        for (Plank beam : beams) {
            for (int i = 1; i < beam.getAmount()+1; i++) {
                    if (0 < beamToOtherMath - (unitConversion.widthMmToDrawUnits(beam.getLength()) * i) + rafters.get(0).getDrawWidth(unitConversion) && 1 < posts.get(0).getAmount()){

                    carportSvg.addRectangle((unitConversion.widthMmToDrawUnits(beam.getLength()) * i) + rafters.get(0).getDrawWidth(unitConversion),50 - (1.5 * (unitConversion.heightMmToDrawUnits(beams.get(0).getHeight()))), unitConversion.heightMmToDrawUnits(posts.get(0).getHeight()),posts.get(0).getDrawWidth(unitConversion),rectStandardStyle);
                    carportSvg.addRectangle((unitConversion.widthMmToDrawUnits(beam.getLength()) * i) + rafters.get(0).getDrawWidth(unitConversion),550 - (1.5 * (unitConversion.heightMmToDrawUnits(beams.get(0).getHeight()))), unitConversion.heightMmToDrawUnits(posts.get(0).getHeight()),posts.get(0).getDrawWidth(unitConversion),rectStandardStyle);
                        System.out.println("places posts at: "+(unitConversion.widthMmToDrawUnits(beam.getLength()) * i) + rafters.get(0).getDrawWidth(unitConversion));
                    posts.get(0).setAmount(posts.get(0).getAmount()-2);
                    }
                    else {
                        System.out.println("couldn't place posts at: "+(unitConversion.widthMmToDrawUnits(beam.getLength()) * i) + rafters.get(0).getDrawWidth(unitConversion));
                    }
                System.out.println("amount of posts left: "+posts.get(0).getAmount());
                    if (posts.get(0).getAmount() == 0){
                        break;
                    }
            }
        }

    }

}
