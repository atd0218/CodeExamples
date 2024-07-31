package com.lq.exercises;

public class Cube extends Box {

    //constructor
    public Cube(double side) {
        super(side);
    }

    @Override
    public void setHeight(double height) {
        // TODO Auto-generated method stub
        super.setHeight(height);
        super.setLength(height);
        super.setWidth(height);
    }

    @Override
    public void setLength(double length) {
        // TODO Auto-generated method stub
        super.setHeight(length);
        super.setLength(length);
        super.setWidth(length);
    }

    @Override
    public void setWidth(double width) {
        // TODO Auto-generated method stub
        super.setHeight(width);
        super.setLength(width);
        super.setWidth(width);
    }

    public void setSide(double side) {
       if (side > 0) {
        super.setHeight(side);
        super.setLength(side);
        super.setWidth(side);
       }
       else {
        System.out.println("You have entered an invalid number to set the cube sides to.");
       }
    }

    public double getSide () {
        return super.getHeight();
    }

    
    
}