//import
import java.util.Scanner;

public class MathClassTester {
    
    public static void main(String[] args){

        double x = 2.14;
        double y = 73.44;

        double z = Math.max(x,y);

        double t = Math.min(x,y);

        double v = Math.abs(y); // absolute value

        double c = Math.sqrt(y); //square root

        double vv = Math.round(x); 

        double gg = Math.ceil(y);


        // find hyptoenuse of triangle

        double ie;
        double ei;
        double ee;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter side ie");
        ie = scanner.nextDouble();

        System.out.println("Enter side ei");
        ei = scanner.nextDouble();

        ee = Math.sqrt((ie * ie)+(ei * ei));

        System.out.println("The Hypotenuse of this triangle is "+ee);

        //good practice to close scanner
        scanner.close();


    }
}
