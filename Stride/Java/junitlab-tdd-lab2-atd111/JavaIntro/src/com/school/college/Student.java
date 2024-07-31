package com.school.college;

public class Student {

  private String name; 
  private int age;
  private int yearsStudent;
  private boolean stateRes;

  Student(String name, int age, int yearsStudent, boolean stateRes) {
    this.name = name;
    this.age = age;
    this.yearsStudent = yearsStudent;
    this.stateRes = stateRes;
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

  @Override
  public String toString() {
    return "Student [name= " + name + ", age= " + age + ", years registered: " + yearsStudent + ", state resident: " + stateRes
            + "]";
  }


  
  
}
