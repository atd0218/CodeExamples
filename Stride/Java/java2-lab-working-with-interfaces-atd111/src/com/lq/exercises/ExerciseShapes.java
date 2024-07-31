package com.lq.exercises;

/**
 * @name Ashton Daniels
 * Date: 03/04/2024
 * Description:
 * 
 * Box.java
 * 
 * The time complexity of the Box methods is O(1) because it performs a constant number of operations 
 * regardless of the input size for the methods
 * 
 * The space complexity of the Box class is O(1) because it only stores a fixed number of double values (length, width, height) 
 * regardless of the input size.
 * 
 * Rectangle.java
 * 
 * The time complexity of the `getArea()` and `getPerimeter()` methods in the Rectangle class is O(1) because the calculations 
 * involved (multiplication and addition) are constant time operations regardless of the size of the input. 
 * 
 * Similarly, the space complexity of the Rectangle class is also O(1) because it only stores two double values (length and width)
 * and a few strings (color and name) regardless of the size of the input. 
 * 
 * Overall, the time and space complexity of the Rectangle class is constant, O(1).
 * 
 * Circle.java
 * 
 * The time complexity of the getArea() and getPerimeter() methods in the Circle class is O(1) because the calculations 
 * involve simple mathematical operations that do not depend on the size of any input. 
 * 
 * The space complexity of the Circle class is also O(1) because it only stores a single double value for the radius, regardless 
 * of the size of the input.
 * 
 * Square.java
 * 
 * The time complexity of the Square class constructor is O(1) because it only performs a constant number of operations 
 * regardless of the size of the input. The space complexity is also O(1) because it only requires a constant amount of 
 * memory to store the sides of the square.
 * 
 * ExcerciseShapes.java
 * 
 * The time complexity of this program is O(n), where n is the total number of shapes created. This is because the program 
 * iterates through each shape in the array of shapes and performs a constant number of operations for each shape.
 * 
 * The space complexity of this program is also O(n), as the program creates arrays of shapes and two-dimensional shapes 
 * with a size of 8 and 3 respectively. Additionally, the program creates individual instances of shapes within these arrays. 
 * Therefore, the space complexity is directly proportional to the number of shapes created.
 * 
 * ThreeDimensional.java
 * 
 * Interface which requires anything implementing it to implement getVolume and getSurfaceArea methods as well. 
 * 
 * TwoDimensional.java
 * 
 * Interface which requires anything implementing it to implement getArea and getPerimeter methods as well. 
 */

public class ExerciseShapes {

    public static void main(String[] args) {
        Shape[] shapes = new Shape[8];
        TwoDimensional[] twoDs = new TwoDimensional[3];

        shapes[0] = new Rectangle(10, 10);
        shapes[1] = new Rectangle(10, 20, "Blue", "RecTest");

        shapes[2] = new Square(10);
        shapes[3] = new Square(20);

        shapes[4] = new Cube(25);
        shapes[5] = new Cube(10);

        shapes[6] = new Box(25);
        shapes[7] = new Box(10, 20, 30);

        twoDs[0] = new Circle(10);
        twoDs[1] = new Rectangle(10, 20);
        twoDs[2] = new Square(25);

        for (Shape shape : shapes) {
            shape.setColor("Blue");
            System.out.println(shape.getColor());
        }

        for (TwoDimensional twoDimensional : twoDs) {
            System.out.println(twoDimensional.getArea());

            System.out.println(twoDimensional.getPerimeter());
        }

        // for (Shape shape : shapes) {
        //     ThreeDimensional temp = (ThreeDimensional)shape;
        //     System.out.println(temp.getVolume());
        // }

    }

}
