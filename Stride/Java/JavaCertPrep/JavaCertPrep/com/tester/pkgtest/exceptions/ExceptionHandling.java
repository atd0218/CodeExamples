package com.tester.pkgtest.exceptions;

import java.io.IOException;

public class ExceptionHandling {

    public static void main(String[] args) {

        System.out.println(c());

    }

    public static String a() {

        try {

        }catch (Exception e) {
            System.out.println("Exception Occured ");
            return "exception";
        }
        finally {
            System.out.println("finally section");
            return "finally";
        }

    }

    public static String b() {

        try {
            return "OK";
        }
        catch (Exception e) {
            System.out.println("Exception Occured");
            return "exception";
        }
        finally {
            System.out.println("finally section");
            return "Finally";
        }

    }

    public static String c() {

        try{
            throw new Exception();
        }catch (Exception e) {
            System.out.println("Exception Occured");
            return "exception";
        }finally {
            System.out.println("finally section");
            return "finally";
        }



    }

    public static String d() {
        try{
            return "OK";

        }catch (Exception e) {
            System.out.println("Exception Occured");
            return "exception";
        }
    }
}

