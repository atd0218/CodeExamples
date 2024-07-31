package com.tester.pkgtest;

 class Spectacles {

  //Constructor no-args
  Spectacles () {
   numLenses = 2;
  }

  private int numLenses = 0;

  public int getNumLenses() {
   return numLenses;
  }

  public void setNumLenses(int numLenses) {
   this.numLenses = numLenses;
  }
 }
