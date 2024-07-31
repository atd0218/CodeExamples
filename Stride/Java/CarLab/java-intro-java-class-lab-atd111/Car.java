/**
 * @author Ashton Daniels
 * FileName: Car.java
 * Description: Create different attributes with getters and setters and call them from the main method by creating car objects. 
 */

//package aws.Stride.CarLab2;

import java.time.LocalDate;
import java.time.Period;

public class Car {
		
	/**
	 * Create variables needed for the Car method as well as getter and setter functions for each. 
	 */
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	private int speed;
	
	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	private int gasolinePercentage;
	
	public int getGasolinePercentage() {
		return gasolinePercentage;
	}

	public void setGasolinePercentage(int gasolinePercentage) {
		this.gasolinePercentage = gasolinePercentage;
	}
	
	private boolean engineState;
	
	public boolean isEngineState() {
		return engineState;
	}

	public void setEngineState(boolean engineState) {
		this.engineState = engineState;
	}
	
	private LocalDate manufactured;
	
	public LocalDate getManufactured() {
		return manufactured;
	}

	public void setManufactured(LocalDate manufactured) {
		this.manufactured = manufactured;
	}
	
	public int getAge() {
		return Period.between(getManufactured(), LocalDate.now()).getYears();
	}

	public static void main(String[] args) {
		
		/**
		 * Main method will be used to create instances/objects of the Car class and print out all the details using setters and getters. 
		 */
		
		Car first = new Car();
		
		first.setName("Ford GT");
		first.setSpeed(385);
		first.setEngineState(true);
		first.setGasolinePercentage(79);
		first.setManufactured(LocalDate.of(2023, 10, 1));
		
		Car second = new Car();
		
		second.setName("Ferrari");
		second.setSpeed(382);
		second.setEngineState(true);
		second.setGasolinePercentage(94);
		second.setManufactured(LocalDate.of(2022, 12, 12));
		
		Car third = new Car();
		
		third.setName("Maclaren");
		third.setSpeed(402);
		third.setEngineState(true);
		third.setGasolinePercentage(43);
		third.setManufactured(LocalDate.of(2018, 10, 24));
		
		for(Car car: new Car[]{first, second, third}) {
			System.out.printf("Here are the cars current information as they are going down the laps. %s is %d years old and is currently going %d mph. The cars engine state is %s there gas level is at %d percent and was manufactured on %tB %n", 
					car.getName(), car.getAge(), car.getSpeed(), car.isEngineState(), car.getGasolinePercentage(), car.getManufactured());
		}
	
	}

}
