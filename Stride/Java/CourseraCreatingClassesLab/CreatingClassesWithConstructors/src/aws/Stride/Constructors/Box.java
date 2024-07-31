/**
 * Name: Ashton Daniels
 * Filename: Box.java
 * JDK Version: 8
 * Description: Constructor Lab with Class Creation. 
 */

package aws.Stride.Constructors;

public class Box {

    //Create Box Constructors

    public Box(double height, double width, double length) {
        this.height = height;
        this.width = width;
        this.length = length;
    }

    //create a cube by setting length, width, and height to the same number
    public Box(double cube) {
        //need implementation here
        this(cube, cube, cube);
    }
    

    //create attributes for the box as well as the getters and setters
    private double height;
    public double getHeight() {
        return height;
    }
    public void setHeight(double height) {
        this.height = height;
    }

    private double width;
    public double getWidth() {
        return width;
    }
    public void setWidth(double width) {
        this.width = width;
    }

    private double length;
    public double getLength() {
        return length;
    }
    public void setLength(double length) {
        this.length = length;
    }

    //Methods

    public double getVolume () {
        double length = getLength();
        double height = getHeight();
        double width = getWidth();
        return length * width * height;
    }

    public double getSurfaceArea () {
        double length = getLength();
        double width = getWidth();
        double height = getHeight();
        //surfaceArea is the sum of the area on all six sides
        //here I calculate three sides and times it by two
        return (((length * width) + (width * height) + (length * height)) * 2);
    }

    public void printBox () {
        double length = getLength();
        double width = getWidth();
        double height = getHeight();

        if (length <= 0 || width <= 0 || height <= 0) {
            System.out.println("The box contains invalid properties");
        }
        else {
            System.out.println("Length = " + length);
            System.out.println("Width = " + width);
            System.out.println("Height = " + height);
            System.out.println("Volume = " + getVolume());
            System.out.println("Surface Area = " + getSurfaceArea());
        }
    }

}
