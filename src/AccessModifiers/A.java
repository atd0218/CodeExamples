package AccessModifiers;

// how to access code from a different package. 
import AccessModifiers.*;

public class A {

    public static void main(String[] args)
    {
        Definitions c = new Definitions();

        System.out.println(c.defaultMessage);
        System.out.println(c.publicMessage);

    }
    
}
