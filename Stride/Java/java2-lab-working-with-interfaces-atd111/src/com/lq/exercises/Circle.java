package com.lq.exercises;

public class Circle extends Shape implements TwoDimensional{

    private double radius;

    public Circle (double radius) {
        this.radius = radius;
    }

    @Override
    public double getArea() {
        return 3.14159 * (radius * radius);
    }

    @Override
    public double getPerimeter() {
        return 2 * (3.14159 * radius);
    }

    

}
