/**
 * @name @atd111 Asthon Daniels
 * Date: 02/20/2024
 * JDK Version: 8
 * 
 * Description: Added a new class called SUV.java that inherits the Cargo class. I created the constructors in that class
 * and then went into main method to create a new SUV object and it printed successfully. 
 */
package com.learnquest.demoapps;

import java.time.LocalDate;

import com.learnquest.demos.transport.Car;
import com.learnquest.demos.transport.SUV;
import com.learnquest.demos.transport.SportsCar;
import com.learnquest.demos.transport.StationWagon;

public class CarApp {
	public static void main(String[] args) {
		Car car54 = new Car();

		car54.setName("Car 54");
		car54.setSpeed(20);
		car54.setGasoline(20);
		car54.setManufactured(LocalDate.of(1961, 9,  17));
	
		Car mach5 = new SportsCar("Mach V", 250, 21, true);
		mach5.setManufactured(LocalDate.of(1967, 4, 2));

		StationWagon wagon = new StationWagon("Wagon Queen Family Truckster");
		wagon.setManufactured(LocalDate.of(1979, 7, 29));
		wagon.setCurrentCargoLoad(500);
		wagon.setSpeed(75);

		SUV suv = new SUV("Escalade", 200, 21, true);
		suv.setManufactured(LocalDate.of(2001, 7, 29));
		suv.setCurrentCargoLoad(700);
		suv.setSpeed(70);

		for (Car car: new Car[] { car54, mach5, wagon, suv } ) {
			System.out.printf("%s is %d years old and is traveling at %d mph%n",
					car.getName(), car.getAge(), car.getSpeed());
		}
	}
}
