package com.stride.codingchallenge;

public class ChickenAlfredo extends Pasta {

    //constructor
    public ChickenAlfredo(String pasta, String pastaSauce, String dishName, String cookingMethod, int cookTime) {
        super(pasta, pastaSauce, dishName);
        this.setCookTime(cookTime);
        this.setCookingMethod(cookingMethod);
    }

    //variables
    private String cookingMethod;
    private int cookTime;

    //getters and setters
    public String getCookingMethod() {
        return cookingMethod;
    }
    public void setCookingMethod(String cookingMethod) {
        this.cookingMethod = cookingMethod;
    }
    public int getCookTime() {
        return cookTime;
    }
    public void setCookTime(int cookTime) {
        this.cookTime = cookTime;
    }

    public void describe() {
        System.out.println(getCookTime());
        System.out.println(getCookingMethod());
    }
}
