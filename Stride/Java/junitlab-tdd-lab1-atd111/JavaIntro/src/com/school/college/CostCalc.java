package com.school.college;

class CostCalc {

  public int calcCost(Student student) {
    int cost = weeksCost(student.getWeeksOfClass());
    cost += residentCost(cost, student.isStateRes());
    cost -= yearsDiscount(student.getYearsStudent());
    return cost; 
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
}