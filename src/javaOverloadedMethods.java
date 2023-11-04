public class javaOverloadedMethods {
    
    public static void main(String[] args)
    {

        //overloaded methods = methods that share the same name but have different parameters (rules to be able to call the method)
        //                     method name + parameters = method signature

        int x = add(1, 2);
        int xx = add(1, 2, 3);
        int xxx = add(1, 2, 3, 4);
        double xxxx = add(1.1, 2.2, 3.3, 4.4);

        System.out.println(x);
        System.out.println(xx);
        System.out.println(xxx);
        System.out.println(xxxx);
    }

    static int add(int a, int b)
    {
        System.out.println("This is overloaded method #1");
        return a + b;
    }

    static int add(int a, int b, int c)
    {
        System.out.println("This is overloaded method #2");
        return a + b + c;
    }

    static int add(int a, int b, int c, int d)
    {
        System.out.println("This is overloaded method #3");
        return a + b + c + d;

    }

    static double add(double a, double b, double c, double d)
    {
        System.out.println("This is overloaded method #4");
        return a + b + c + d;
    }
}
