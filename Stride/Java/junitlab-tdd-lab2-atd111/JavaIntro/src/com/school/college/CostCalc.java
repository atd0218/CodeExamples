package com.school.college;

import java.util.HashMap;

class CostCalc {

  public HashMap<Student, Integer> calcCost(Aclass aclass) {
    HashMap<Student,Integer> costs = new HashMap<>();
    for (int i = 0; i < aclass.getStudents().size(); i++) {
      Student temp = aclass.getStudents().get(i);
      int cost = weeksCost(aclass.getClassLength());
      cost += residentCost(cost, temp.isStateRes());
      cost -= yearsDiscount(temp.getYearsStudent());
      cost -= classSizeDiscount(aclass.getStudents().size(), cost);
      costs.put(temp, cost);
    }

    return costs;
  }

  int weeksCost(int weeks) {
    int cost = 0;
    switch(weeks) {
      case 3:
        cost = 500;
        break;
      case 4:
        cost = 700;
        break;
      case 6:
        cost = 1100;
        break;
      case 8:
        cost = 1500;
        break;
      case 12:
        cost = 2000;
        break;
    }

    return cost;
  }

  int residentCost(int currentCost, boolean stateRes) {
    int more = 0;
    if(!stateRes) {
      more = (int) (currentCost * 0.1);
    }
    return more;
  }

  int yearsDiscount(int yearsAttended) {
    int discount = yearsAttended * 50;
    return discount; 
  }

  public double classSizeDiscount(int classSize, int cost) {
    
    double discountNum = 0;
    double discount = 0;
    
    if (classSize > 20) {
      classSize -= 20;
      for(int i = 0; i < classSize; i++) {
        discountNum += 0.02;
      }
      discount = (cost*discountNum);
    }
    return discount;
  }
}