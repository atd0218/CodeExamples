package com.stride.codingchallenge;

public class Pasta {

    //constructors
    public Pasta(String pasta, String pastaSauce, String dishName) {
        this.setPasta(pasta);
        this.setPastaSauce(pastaSauce);
        this.setDishName(dishName);
    }

    //variables
    private String pasta;
    private String pastaSauce;
    private String dishName;

    //getters and setters
    public String getPasta() {
        return pasta;
    }
    public void setPasta(String pasta) {
        this.pasta = pasta;
    }
    public String getPastaSauce() {
        return pastaSauce;
    }
    public void setPastaSauce(String pastaSauce) {
        this.pastaSauce = pastaSauce;
    }
    public String getDishName() {
        return dishName;
    }
    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public void describe() {
        System.out.println(getPastaSauce());
        System.out.println(getDishName());
        System.out.println(getPasta());
    }
}
