package com.lq.exercises;

public class Rectangle extends Shape implements TwoDimensional{
    private double length;
    private double width;

    public Rectangle (double length, double width) {
        this.length = length;
        this.width = width;
        setColor("White");
        setName("Unknown");
    }

    public Rectangle (double length, double width, String color, String name) {
        this.length = length;
        this.width = width;
        this.setColor(color);
        setName(name);
    }

    @Override
    public double getArea() {
        // TODO Auto-generated method stub
        return length * width;
    }

    @Override
    public double getPerimeter() {
        // TODO Auto-generated method stub
        return 2 * (length + width);
    }

    
}
