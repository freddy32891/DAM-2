package cat.proven.scaperoommarco.model;

import java.util.ArrayList;

public class Drawer {
    boolean open;
    ArrayList<Diamond> diamonds;

    public Drawer() {
        this.open = false;
        this.diamonds = new ArrayList<>();

    }

    public boolean isOpen() { return open; }
    public void setOpen(boolean open) { this.open = open; }
    public ArrayList<Diamond> getDiamonds() { return diamonds; }

    /**
     * add diamond to the diamonds list of drawer
     * @param d diamond to add drawer
     */
    public void addDiamond(Diamond d){diamonds.add(d);}

    /**
     * change the state of drawer
     * @return true if change correctly, false if not
     */
    public boolean swap(){
        if(isOpen()) setOpen(false);
        else         setOpen(true);
        return true;
    }
}
