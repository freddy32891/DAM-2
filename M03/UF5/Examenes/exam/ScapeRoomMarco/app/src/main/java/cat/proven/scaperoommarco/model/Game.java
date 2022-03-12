package cat.proven.scaperoommarco.model;

import java.util.ArrayList;

/**
 * initialize drawers
 * 4 drawers: every drawer can have various diamonds of different colours
 * solution: 3 or 4 diamonds
 * comprove if the solution is correct
 */
public class Game {
    ArrayList<Drawer> drawers;
    ArrayList<Diamond> diamondsResult;

    public Game() {
        drawers = new ArrayList<>();
        diamondsResult = new ArrayList<>();
    }

    /**
     * Search diamond and retrive this.
     *
     * @param index to search
     * @return a Diamond from the list by index
     */
    public Diamond getDiamond(int index) {
        Diamond d = null;
        if (index < diamondsResult.size()) {
            d = diamondsResult.get(index);
        }
        return d;
    }

    /**
     * Initialize the drawers for the game
     */
    public void init4Drawers(){
        diamondsResult.clear();
        drawers.clear();
        //drawer 0: 3 diamondBlue
        Drawer c0 = new Drawer();
        c0.setOpen(false);
        Diamond d0 = new Diamond(Color._BLUE,3);
        c0.addDiamond(d0);

        //drawer 1: 4 diamondRed
        Drawer c1 = new Drawer();
        c1.setOpen(false);
        Diamond d1 = new Diamond(Color._RED,4);
        c1.addDiamond(d1);

        //drawer 2: 1 diamondRed, 1 diamondGreen, 3 diamondGrey
        Drawer c2 = new Drawer();
        c2.setOpen(false);
        Diamond d2a = new Diamond(Color._RED,1);
        Diamond d2b = new Diamond(Color._GREEN,1);
        Diamond d2c = new Diamond(Color._GREY,3);
        c2.addDiamond(d2a);
        c2.addDiamond(d2b);
        c2.addDiamond(d2c);

        //drawer 3: 6 diamondGreen
        Drawer c3 = new Drawer();
        c3.setOpen(false);
        Diamond d3 = new Diamond(Color._GREEN,6);
        c3.addDiamond(d3);

        //Add drawers to list
        drawers.add(c0);
        drawers.add(c1);
        drawers.add(c2);
        drawers.add(c3);

        //Diamonds
        Diamond dia1 = new Diamond(Color._BLUE, 0);
        Diamond dia2 = new Diamond(Color._GREEN, 0);
        Diamond dia3 = new Diamond(Color._BLUE, 0);
        Diamond dia4 = new Diamond(Color._GREY, 0);
        diamondsResult.add(dia1);
        diamondsResult.add(dia2);
        diamondsResult.add(dia3);
        diamondsResult.add(dia4);
    }

    /**
     * Method to check value of diamond with diamonds drawers
     *
     * @return true if is game is passed, false if not.
     */
    public boolean codeSuccessful() {
        boolean result = true;
        //tour all diamonds
        for (int i = 0; i < diamondsResult.size() && result; i++) {
            //for every diamonds
            Diamond d = diamondsResult.get(i);
            //Sum of diamonds in all bins
            int sum = sumDiamond(d);
            //Compare sum with diamond value
            if (sum != d.getValue()) {
                result = false;
            }
        }
        return result;
    }

    /**
     * Sum diamonds to the same color
     * @param d
     * @return diamonts with same color
     */
    private int sumDiamond(Diamond d) {
        int sum = 0;
        for (Drawer draw : drawers) {
            for (Diamond diamond : draw.getDiamonds()) {
                //if the colors of the diamond are the same
                if (d.getColor().equals(diamond.getColor())) {
                    sum += diamond.getValue();
                }
            }
        }
        return sum;
    }

    /**
     * Retrive drawer to index
     * @param index
     * @return draw if index is correct, null if index is worong
     */
    public Drawer getDraw(int index) {
        Drawer d = null;
        if (index < drawers.size()) {
            d = drawers.get(index);
        }
        return d;
    }

    //check if drawer is open
    public boolean checkOpen() {
        boolean b = false;
        for (Drawer d : drawers) {
            if (d.isOpen()) {
                b = true;
                break;
            }
        }
        return b;
    }
}
