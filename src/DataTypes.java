public class DataTypes {
    
    public static void main(String[] args) {

        //Data Types

        //Boolean - Holds a true or false value Size (1 bit)
        //int - represents a number Size (4 bytes) - 2 billion to 2 billion
        //float - this value must be stored with an f
        //douuble - Hold decimals and large numbers Size (8 bytes)
        //char - single character/letter/ASCII value Size (2 byes) Surrounded by pair of single quotes
        //String - a sequence of characters Size (varies) Surrounded by double quotes

        //Primitive 
            // 8 types
            // everything above but String
            // can only hold 1 value
            // less memory used
            // faster retreival

        //Reference
            // user defined unlimmited amount
            // stores an address
            // can hold more than 1 value
            // more memory used
            // slower retreival

        //How to create variables

        int intTest; //declaring one

        intTest = 1234; //Assing variable

        int x = 123; // initialization = declare and assign the variable at same time

        // have to assign variable with an L afterwards but store a higher number than int
        long t = 8297349283749L;

        //double - stores number with fractional portion
        double d = 3.241341;

        // must add f after value
        float z = 3.14f;

        //boolean - only stores true or false

        boolean ty = true;
        boolean tii = false;

        //char - stores single characters character must be within single quotes
        char symbol = 't';
        char symbol2 = '@';

        //String - all reference data types start with capital letter

        String name = "Test me Please Bro";



        System.out.println(x + intTest);
        System.out.println("My Number is: "+x);




    }
}
