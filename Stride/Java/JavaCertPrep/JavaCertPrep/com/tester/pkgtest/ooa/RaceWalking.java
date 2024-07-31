package com.tester.pkgtest.ooa;

 class RaceWalking extends Walk implements OlympicSport{

     public RaceWalking(double x) {
         super(x);
     }

     public void move() {

         System.out.println("RaceWalking:moving");

     }
}
