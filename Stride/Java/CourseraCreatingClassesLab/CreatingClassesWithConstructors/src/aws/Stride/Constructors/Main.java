package aws.Stride.Constructors;

public class Main {

    public static void main(String[] args) {
        Box box = new Box(7.0, 6.0, 5.0);
        Box boxcubed = new Box(6);

        box.printBox();
        boxcubed.printBox();
    }
    
}
