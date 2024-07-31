package com.lq.exercises;

/**
 * @name Ashton Daniels
 * Date: 03/05/2024
 * JDK Version: 8 
 * Description 
 * 
 * ExercisePrint.java
 * 
 * Class that stores the main method for this assignement which creates an array of Shape Objects and displays them 
 * using the overrided toString methods. 
 * 
 * Runtime will be O(1) constant in this case as there is a defined array being created only containing 5 slots so N will
 * always be 5. 
 * 
 * Space complexity will be O(1) as well as there is a constant amount of memory to store the 5 array items. 
 * 
 * Square.java
 * 
 * This class is a sub class of Rectangle and is used to define the characteristics of a square where all sides are equal. 
 * 
 * The toString method used in this class is inherited from Rectangle. 
 * 
 * Shape.java
 * 
 * Parent abstract class that provides implementation for the getters and setters for name and color of each shape. 
 * 
 * Box.java
 * 
 * Box class is a subclass of shape that defines the characteristics of a three dimensional box. This class also 
 * implements threeDimensional and therefore has to provide implementation for the interface methods. 
 * 
 */

public class ExercisePrint {

    public static void main(String[] args) {
        Shape[] shapes = new Shape[5];
		
		shapes[0] = new Rectangle("Name","Red",2, 4);
		shapes[1] = new Square("Name","Red", 3);
		shapes[2] = new Cube(1,"Name","Red");
		shapes[3] = new Box(2,4,6, "Name","Red");
        shapes[4] = new Circle(6, "Name","Red");

        for (Shape shape : shapes) {

            System.out.println(shape.toString());
        }

    }

}
