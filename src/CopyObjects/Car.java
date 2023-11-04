package CopyObjects;

public class Car {
    
    private String make;
    private String model;
    private int year;

    //option 1
    // Car(String make, String model, int year)
    // {
    //     this.make = make;
    //     this.model = model;
    //     this.year = year;
    // }

    //option 2
    Car(String make, String model, int year)
    {
        this.setMake(make);
        this.setModel(model);
        this.setYear(year);
    }

    //overloaded constructor
    Car(Car x)
    {
        this.copy(x);
    }

    // method to copy one object to another. 
    public void copy(Car car)
    {
        this.setMake(car.getMake());
        this.setModel(car.getModel());
        this.setYear(car.getYear());
    }


    public String getMake()
    {
        return make;
    }

     public String getModel()
    {
        return model;
    }

     public int getYear()
    {
        return year;
    }

    public void setMake(String make)
    {
       this.make = make;
    }

    public void setModel(String model)
    {
       this.model = model;
    }

    public void setYear(int year)
    {
        this.year = year;
    }
}

