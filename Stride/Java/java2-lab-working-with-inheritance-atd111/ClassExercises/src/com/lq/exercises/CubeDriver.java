package com.lq.exercises;

public class CubeDriver {

    public static void main(String[] args) {
        Cube c1 = new Cube(5);
        Cube c2 = new Cube(8);

        System.out.println("Cube 1 Details: ");
        System.out.println(display(c1));
        System.out.println("Cube 2 Details: ");
        System.out.println(display(c2));

        c1.setLength(20);
        c1.printBox();

        c1.setLength(40);
        c1.printBox();

        c2.setWidth(-5);
        c2.printBox();
    }

    public static String display(Cube c) {
        return String.format("Cube length is %f%nCube width is %f%nCube height is %f", c.getSide(), c.getSide(), c.getSide());
    }

    
    
}
