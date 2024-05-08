package app.web.services;

import app.util.UnitConversion;
import app.web.entities.Plank;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SvgCarport {
    private static List<Plank> testOrder = new ArrayList<>();
    private static int id = 0;
    private UnitConversion unitConversion = new UnitConversion(600,780);
    private Svg carportSvg = new Svg(0,0,"0 0 900 700", "100%","auto");


    public static List<Plank> getTestOrder() {
        testOrder.add(new Plank(id++,25,200,540,Plank.BOARD,0));
        testOrder.add(new Plank(id++,25,200,540,Plank.BOARD,0));
        testOrder.add(new Plank(id++,25,200,540,Plank.BOARD,0));
        testOrder.add(new Plank(id++,25,200,540,Plank.BOARD,0));

        testOrder.add(new Plank(id++,45,195,600,Plank.RAFTER,0));
        testOrder.add(new Plank(id++,45,195,600,Plank.RAFTER,0));

        testOrder.add(new Plank(id++,97,97,300,Plank.POST,0));
        testOrder.add(new Plank(id++,97,97,300,Plank.POST,0));
        testOrder.add(new Plank(id++,97,97,300,Plank.POST,0));
        testOrder.add(new Plank(id++,97,97,300,Plank.POST,0));
        testOrder.add(new Plank(id++,97,97,300,Plank.POST,0));
        testOrder.add(new Plank(id++,97,97,300,Plank.POST,0));

        testOrder.add(new Plank(id++,97,97,600,Plank.POST,0));

        return testOrder;
    }

    public String drawCarport(List<Plank> Planks){
        Locale.setDefault(new Locale("US"));
//        outer svg setup
        carportSvg.addRectangle(0,0,700,900,"stroke: #000000; stroke-width: 1px; fill: none");
        carportSvg.addArrow(50, 620,50,20);
        carportSvg.addArrow(80, 650,880,650);
        carportSvg.addText(30,300,-90,"600 cm");
        carportSvg.addText(470,680,0,"800 cm");

//        inner svg setup
        carportSvg.addSvg(80,20,"0 0 800 600", "800", "600");
        carportSvg.addRectangle(0,0,600,800,"stroke: #000000; stroke-width: 1px; fill: #ffffff");
//        methods for drawing the actual carport
        rafterDrawer();

        return carportSvg.toString();
    }

    public void rafterDrawer(){
        ArrayList<Plank> rafters = plankOrganiser(getTestOrder(),3);

//        raftersSvgSegment.append(String.format(Svg.SVG_RECT_TEMPLATE, 0, 0, unitConversion.widthMmToDrawUnits(rafters.get(0).getLength()), unitConversion.widthMmToDrawUnits(rafters.get(0).getHeight()), "stroke: #000000; stroke-width: 1px; fill: #ffffff"));
//        raftersSvgSegment.append(String.format(Svg.SVG_RECT_TEMPLATE, 700, 0, unitConversion.widthMmToDrawUnits(rafters.get(0).getLength()), unitConversion.widthMmToDrawUnits(rafters.get(0).getHeight()), "stroke: #000000; stroke-width: 1px; fill: #ffffff"));

        System.out.println(unitConversion.widthMmToDrawUnits(45));
        carportSvg.addRectangle(0,0,600,45,"stroke: #000000; stroke-width: 1px; fill: #ffffff");

    }

    public String boardDrawer(List<Plank> Boards){
        StringBuilder boardsSvgSegment = new StringBuilder();



        return boardsSvgSegment.toString();
    }

    public ArrayList<Plank> plankOrganiser(List<Plank> planks, int type){
        ArrayList<Plank> plankArrayList = new ArrayList<>();
        switch (type) {
            case 0: //board
                for (Plank plank : planks) {
                    if (plank.getType().equals(0)){
                        plankArrayList.add(plank);
                    }
                }
                break;
            case 1: //lath
                for (Plank plank : planks) {
                    if (plank.getType().equals(1)){
                        plankArrayList.add(plank);
                    }
                }
                break;
            case 2: //beam
                for (Plank plank : planks) {
                    if (plank.getType().equals(2)){
                        plankArrayList.add(plank);
                    }
                }
                break;
            case 3: //rafter
                for (Plank plank : planks) {
                    if (plank.getType().equals(3)){
                        plankArrayList.add(plank);
                    }
                }
                break;
            case 4: //post
                for (Plank plank : planks) {
                    if (plank.getType().equals(4)){
                        plankArrayList.add(plank);
                    }
                }
                break;
            default:
                //TODO: throw an error or something
        }
        return plankArrayList;
    }
}
