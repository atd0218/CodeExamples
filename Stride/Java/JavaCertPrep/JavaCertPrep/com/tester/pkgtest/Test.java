package com.tester.pkgtest;

public class Test {

    public static void main(String args[]){

        int k = 9;  // 'var' equivalent to 'int' here i.e. int k = 9;

        int s = 5;  // 'var' equivalent to 'int' here

        switch(k){

            default :

                if( k == 10) { s = s*2; }

                else{

                    s = s+4;

                    break;

                }

            case 7 : s = s+3;

        }

        System.out.println(s);

    }
}
