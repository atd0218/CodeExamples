public class javaMethods {
    
    public static void main(String[] args)
    {

        //method = a block of code that is executed whenver it is called upon
        // if your main method is static then any other methods you make will also need to be static or you will receive an error. 
        // parameters are the rules to be able to call the method you created. 
        // You will need a matching argument that comes from the main method to pass into the method you are calling.

        //local variable - the variable is only recognized by what is inside the same sets of curly braces. 
        //global variables - can be called accross all methods in the program. 

        int x = 3;
        int y = 4;

        int z = add(x,y);

        System.out.println(z);

        String name = "Ash";

        int age = 26;

        hello(name, age);

    }

    // return types allow you to specify what value you expect to be returned by the method. 
    static int add(int x, int y)
    {

        int z = x + y;
        return z;

    }

    static void hello(String name, int age) 
    {
        System.out.println("Hello "+ name + "You are " + age + " years old!");
    }
}
