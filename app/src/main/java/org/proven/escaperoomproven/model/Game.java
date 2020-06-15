package org.proven.escaperoomproven.model;

import java.util.ArrayList;
import java.util.Random;

public class Game {

    private static final String _BLUE = "Blue";
    private static final String _RED = "Red";
    private static final String _GREEN = "Green";

    ArrayList<Bin> bins;
    ArrayList<Diamond> diamonds;

    /**
     * The game is defined with 4 bins and 3 diamonds
     */

    public Game() {
        bins = new ArrayList<Bin>();
        diamonds = new ArrayList<Diamond>();

    }


    /**
     *
     * @param index
     * @return a Diamond from the list by index
     *
     */
    public Diamond getDiamond(int index){

        Diamond d = null;

        if (index < diamonds.size()){

            d = diamonds.get(index);

        }

        return d;

    }

    public void initGame(){
        diamonds.clear();
        bins.clear();
        Random rand = new Random();

        Integer number;
        //define bin 1
        Bin bin1 = new Bin();
        bin1.addDiamond(new Diamond(_RED, 4));
        //bin 2
        Bin bin2 = new Bin();
        bin2.addDiamond(new Diamond(_BLUE,3));
        //bin 3
        Bin bin3 = new Bin();
        bin3.addDiamond(new Diamond(_RED,1));
        bin3.addDiamond(new Diamond(_GREEN, 1));
        //bin 4
        Bin bin4 = new Bin();
        bin4.addDiamond(new Diamond(_GREEN,6));
        // Add bins to list
        bins.add(bin1);
        bins.add(bin2);
        bins.add(bin3);
        bins.add(bin4);
        //Diamonds
        Diamond d1 = new Diamond(_RED, number = rand.nextInt(9 - 0));
        Diamond d2 = new Diamond(_GREEN, number = rand.nextInt(9 - 0));
        Diamond d3 = new Diamond(_BLUE, number = rand.nextInt(9 - 0));
        diamonds.add(d1);
        diamonds.add(d2);
        diamonds.add(d3);


    }

    public boolean codeSuccesful(){


        boolean result = true;
        // tour all diamonds
        for(int i=0; i< diamonds.size() && result;i++){
            //for every diamond
            Diamond d = diamonds.get(i);
            // Sum of diamonds in all bins
            int sum = getSumDiamond(d);
            // Compare sum with diamond value
            // if value differs, Code Error
            if(sum != d.getValue()){
                result = false;
            }
            // if not, go forward




        }


        return result;
    }

    /**
     *
     * @param d Diamond
     * @return all diamonds of the same colour
     */

    private int getSumDiamond(Diamond d) {
        //init sum = 0
        int sum = 0;
        //tour all bins list
        /*for(int i = 0; i < drawers.size(); i++){
            Bin bin = bins.get(i);
        }*/

        for(Bin bin:bins){
            //for each bin go through all diamonds
            for(Diamond diamond: bin.getDiamonds()){
                //if the colors of the diamonds are the same
                if(d.getColor().equals(diamond.getColor())){
                    //add value to the sum
                    //sum *= diamond.getValue();
                    sum = sum + diamond.getValue();
                }

            }




        }





        return sum;
    }

    public Bin getBin(int index){
        Bin b = null;

        if(index<bins.size()){
            b=bins.get(index);
        }

        return b;
    }

    public  boolean checkOpen(){
        boolean b = false;

        for(Bin d : bins){
            if(d.isOpen()==true){
                b = true;
            }
        }
        return b;
    }


}
