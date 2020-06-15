package org.proven.escaperoomproven.model;

import java.util.ArrayList;

public class Bin {

    boolean open;

    // diamonds
    ArrayList<Diamond> diamonds;

    public Bin(){
        open = false;
        diamonds = new ArrayList<Diamond>();
    }


    /**
     * Add Diamond to bin
     */

    public void addDiamond(Diamond diamond){
        diamonds.add(diamond);
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public ArrayList<Diamond> getDiamonds() {
        return diamonds;
    }

}
