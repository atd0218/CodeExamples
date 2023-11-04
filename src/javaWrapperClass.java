public class javaWrapperClass {
    
    public static void main(String[] args) {

        //wrapper class = provides a way to use primitive data types as reference data types
        //                reference data types contain useful methods
        //                can be used with collection (ex.ArrayList)

        //primitive     //wrapper
        //boolean       //Boolean
        //char          //Character
        //int           //Integer
        //double        //Double

        //autoboxing = automatic conversion that the java compiler makes between the primitive data type to wrapper object class
        //unboxing = the reverse of autoboxing. Automatic conversion of wrapper class to primitive

        Boolean a = true;
        Character b = '@';
        Integer c = 2;
        Double d = 2.14;
        String e = "Ash";

        

        //unboxing them is using them as if they were primitive
        if(a==true) {
            System.out.println("This true");
        }
    }
}
