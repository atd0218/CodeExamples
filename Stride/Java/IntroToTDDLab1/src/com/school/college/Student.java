package com.school.college;

public class Student {

  private String name; 
  private int age;
  private int yearsStudent;
  private boolean stateRes;
  private int weeksOfClass;

  Student(String name, int age, int yearsStudent, boolean stateRes, int weeksOfClass) {
    this.name = name;
    this.age = age;
    this.yearsStudent = yearsStudent;
    this.stateRes = stateRes;
    this.weeksOfClass = weeksOfClass;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public int getYearsStudent() {
    return yearsStudent;
  }

  public void setYearsStudent(int yearsStudent) {
    this.yearsStudent = yearsStudent;
  }

  public boolean isStateRes() {
    return stateRes;
  }

  public void setStateRes(boolean stateRes) {
    this.stateRes = stateRes;
  }

  public int getWeeksOfClass() {
    return weeksOfClass;
  }

  public void setWeeksOfClass(int weeksOfClass) {
    this.weeksOfClass = weeksOfClass;
  }

  
  
}
