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

    private final String rectStandardStyle = "stroke: #000000; stroke-width: 1px; fill: #ffffff";

    private ArrayList<Plank> rafters = plankOrganiser(getTestOrder(),3);
    private ArrayList<Plank> boards = plankOrganiser(getTestOrder(),0);



    public static List<Plank> getTestOrder() {
        Plank board = new Plank(id++,25,200,4200,Plank.BOARD,0);
        board.setAmount(4);
        testOrder.add(board);
//        testOrder.add(new Plank(id++,25,200,3600,Plank.BOARD,0));
//        testOrder.add(new Plank(id++,25,200,3600,Plank.BOARD,0));
//        testOrder.add(new Plank(id++,25,200,3600,Plank.BOARD,0));


        testOrder.add(new Plank(id++,45,195,6000,Plank.RAFTER,0));
        testOrder.add(new Plank(id++,45,195,6000,Plank.RAFTER,0));

        testOrder.add(new Plank(id++,97,97,3000,Plank.POST,0));
        testOrder.add(new Plank(id++,97,97,3000,Plank.POST,0));
        testOrder.add(new Plank(id++,97,97,3000,Plank.POST,0));
        testOrder.add(new Plank(id++,97,97,3000,Plank.POST,0));
        testOrder.add(new Plank(id++,97,97,3000,Plank.POST,0));
        testOrder.add(new Plank(id++,97,97,3000,Plank.POST,0));

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
        boardDrawer();

        return carportSvg.toString();
    }

    public void rafterDrawer(){

        carportSvg.addRectangle(0, 0, unitConversion.heightMmToDrawUnits(rafters.get(0).getLength()), unitConversion.widthMmToDrawUnits(rafters.get(0).getHeight()), rectStandardStyle);
        carportSvg.addRectangle((int) (800-unitConversion.widthMmToDrawUnits(rafters.get(0).getHeight())), 0, unitConversion.heightMmToDrawUnits(rafters.get(0).getLength()), unitConversion.widthMmToDrawUnits(rafters.get(0).getHeight()), rectStandardStyle);

    }

    public void boardDrawer(){
        double totalDrawLength = boards.get(0).getAmount() * unitConversion.widthMmToDrawUnits(boards.get(0).getLength()); //4 is a placeholder for the amount
        System.out.println(boards.get(0).getAmount());
        System.out.println(boards.get(0).getLength());
        System.out.println("usable length mm in total: "+totalDrawLength);
        System.out.println("usable length mm for each side: "+totalDrawLength/2);
        System.out.println("length mm needing to be filled for each board side: "+(unitConversion.DRAW_WIDTH - (unitConversion.widthMmToDrawUnits(rafters.get(0).getHeight()) * 2)));
        double boardDrawingFillLength = (unitConversion.DRAW_WIDTH - (unitConversion.widthMmToDrawUnits(rafters.get(0).getHeight()) * 2));
        System.out.println((totalDrawLength / 2) - boardDrawingFillLength);

        if ((totalDrawLength / 2) >= (unitConversion.getCarportWidth() - (unitConversion.widthMmToDrawUnits(rafters.get(0).getHeight()) * 2))){
        carportSvg.addRectangle(unitConversion.widthMmToDrawUnits(rafters.get(0).getHeight()),50,unitConversion.heightMmToDrawUnits(boards.get(0).getHeight()),boardDrawingFillLength,rectStandardStyle);
        carportSvg.addRectangle(unitConversion.widthMmToDrawUnits(rafters.get(0).getHeight()),550,unitConversion.heightMmToDrawUnits(boards.get(0).getHeight()),boardDrawingFillLength,rectStandardStyle);
            System.out.println("success for board");
        }

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
