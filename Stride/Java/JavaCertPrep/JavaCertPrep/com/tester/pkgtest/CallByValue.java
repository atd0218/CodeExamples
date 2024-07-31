package com.tester.pkgtest;

public class CallByValue
{
    public static void main(String[] args) {

        int x = 10;
        //x does not change when printed out
        System.out.println("First X");
        System.out.println(x);

        //pass primitive sets x to 50 but does not return the value so it remains 10
        passPrimitive(x);
        System.out.println("Second X");
        System.out.println(x);

        //this value will change since the method returns the value of x which
        //is set to 50
        System.out.println("Third X");
        x = passPrimitiveAndReturn(x);
        System.out.println(x);


        //this will show the number 2 since the constructor initializes the object
        //with numlenses set to 2
        Spectacles spectacles = new Spectacles();
        int z = spectacles.getNumLenses();
        System.out.println(z);

        //this method calls the setNumLenses method which means it will be changed
        //to 1 when printed out
        passObjectReference(spectacles);


    }

    public static void passPrimitive(int x) {

        x = 50;

    }

    public static int passPrimitiveAndReturn(int x) {
        x = 50;
        return x;
    }

    public static void passObjectReference(Spectacles spectacles) {

        spectacles.setNumLenses(1);
        System.out.println(spectacles.getNumLenses());

    }
}
