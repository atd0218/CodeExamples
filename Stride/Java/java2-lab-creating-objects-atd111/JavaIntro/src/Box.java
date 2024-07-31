/**
 * @author Ashton Daniels
 * Date: 02/19/2024
 * JDK Version: 8
 * Description: Program that will allow you to create Box objects and assign them values as length, width, height, or you
 * can pass one value in to be set to all of them. Conditions have been added to constructors to check for proper values first. 
 * 
 * added all required methods and did all additional challenges to lock down input and make sure appropriate numbers are being 
 * entered. 
 * 
 * The time complexity of the Box class methods is O(1) because each method performs a constant number of operations regardless 
 * of the input size. 
 * 
 * The space complexity is also O(1) because the amount of memory used by the class does not increase with the input size. 
 */
public class Box {
	//variables
	private int length;
	private int width;
	private int height;

	//getters and setters
	public void setLength(int length) {
		if (length > 0) {
			this.length = length;
		}
		else {
			System.out.println("Length must be greater than 0");
		}
		
	}

	public void setWidth(int width) {
		if (width > 0) {
			this.width = width;
		}
		else {
			System.out.println("Width must be greater than 0");
		}
		
	}

	public void setHeight(int height) {
		if (height > 0) {
			this.height = height;
		}
		else {
			System.out.println("Height must be greater than 0");
		}
		
	}

	public int getLength() {
		return length;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	//constructors
	public Box(int length, int width, int height) {

		if (length > 0 && width > 0 && height > 0) {
			this.length = length;
			this.height = height;
			this.width = width;
		}
		else if (length <= 0 && width > 0 && height > 0) {
			this.length = 1;
			this.height = height;
			this.width = width;
		}
		else if (length > 0 && width <= 0 && height > 0) {
			this.length = length;
			this.height = height;
			this.width = 1;
		}
		else if (length > 0 && width > 0 && height <= 0) {
			this.length = length;
			this.height = 1;
			this.width = width;
		}
	}
	public Box(int value) {
		if (value > 0) {
			length = value;
			height = value;
			width = value;
		}
		else {
			length = 1;
			height = 1;
			width = 1;
		}
	}


	//additional methods
	public static int getVolume(Box b) {
		int length = b.getLength();

		int result = (int)(Math.pow(length, 3));
		return result;
	}

	public static int getSurfaceArea(Box b) {
		int l = b.getLength();
		int w = b.getWidth();
		int h = b.getHeight();

		int result = (2 * l * w) + (2 * l * h) + (2 * h * w);
		return result;
	}

	public static void printBox(Box b) {
		if (b.getLength() > 0 && b.getHeight() > 0 && b.getWidth() > 0) {
			System.out.println("Box Length is: " + b.getLength());
			System.out.println("Box Height is: " + b.getHeight());
			System.out.println("Box Width is: " + b.getWidth());
			System.out.println("Box Volume is: " + getVolume(b));
			System.out.println("Box Surface Area is: " + getSurfaceArea(b)); 
		}
		else {
			System.out.println("The box contains invalid attributes");
		}
	}


	//main method
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Box box1 = new Box(5, 6, 7);
		Box box2 = new Box(10);

		System.out.println("Printing Box1 Measurements: ");
		System.out.println("Height: " + box1.getHeight());
		System.out.println("Width: " + box1.getWidth());
		System.out.println("Length: " + box1.getLength());

		System.out.println("Printing Box2 Measurements: ");
		System.out.println("Height: " + box2.getHeight());
		System.out.println("Width: " + box2.getWidth());
		System.out.println("Length: " + box2.getLength());

		/*
		 * Start of excersize 2
		 */

		 box1.setHeight(5);
		 box1.setWidth(4);
		 box1.setLength(3);

		System.out.println("Printing New Box1 Measurements After Calling Setters: ");
		System.out.println("Height: " + box1.getHeight());
		System.out.println("Width: " + box1.getWidth());
		System.out.println("Length: " + box1.getLength());

		System.out.println("The Volume of Box 1 is: " + getVolume(box1));
		System.out.println("The Surface Area of Box 1 is: " + getSurfaceArea(box1));

		System.out.println("Print Box Method");
		printBox(box1);

		box1.setLength(-5); //no longer works due to conditional placed in setters

		printBox(box1);

		box1.setWidth(-5);//no longer works due to conditional placed in setters

		box1.setHeight(-5);//no longer works due to conditional placed in setters
	}

}
