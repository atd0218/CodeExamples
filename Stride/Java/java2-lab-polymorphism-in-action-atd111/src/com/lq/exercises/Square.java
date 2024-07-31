package com.lq.exercises;

public class Square extends Rectangle {
	public Square(double side) {
		super(side, side);
	}

	public Square(String name, String color, double i) {
		//TODO Auto-generated constructor stub
		super(i, i);
		setColor(color);
		setName(name);

	}
}
