package com.school.college;

import java.util.ArrayList;

public class Aclass {

  private String className;
  private ArrayList<Student> students;
  private int classLength;

  Aclass(String className, ArrayList<Student> students, int classLength) {
    this.className = className;
    this.students = students;
    this.classLength = classLength;
  }

  boolean addStudent(Student student)
  {
    boolean added = students.add(student);
    return added; 
  }

  boolean Student(Student student)
  {
    boolean removed = students.add(student);
    return removed; 
  }

  public String getClassName() {
    return className;
  }

  public void setClassName(String className) {
    this.className = className;
  }

  public ArrayList<Student> getStudents() {
    return students;
  }

  public void setStudents(ArrayList<Student> students) {
    this.students = students;
  }

  public int getClassLength() {
    return classLength;
  }

  public void setClassLength(int classLength) {
    this.classLength = classLength;
  }

  

}
