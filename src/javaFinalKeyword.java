public class javaFinalKeyword {
    
    public static void main(String[] args)
    {
        // anything declared with the final keyword can't be changed or updated later in the program

        // common thing to do is make all variables letters uppercase
        final double PI = 3.1415927;

        // this does not work because we declared the variable with final initially so it can't be changed. 
        double x = PI + 4.32;

        System.out.println(PI);
    }
}
