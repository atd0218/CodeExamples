package com.tester.pkgtest.ooa;

 class Soccer extends Run implements BallSport{

     Soccer(double x) {
         super(x);
     }

     public void move() {

         System.out.println("Soccer:moving");

     }
}
