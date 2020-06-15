package org.proven.escaperoomproven.model;

public class Diamond {

    String color;
    int value;

    public Diamond(String color, int value){
        this.color = color;
        this.value = value;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void incValue(){
         value++;

         if(value >= 10){
             value = 0;
         }
    }



}
