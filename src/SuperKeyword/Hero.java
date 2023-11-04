package SuperKeyword;

public class Hero extends Person{

    String power;

    // Hero(String name, int age, String power)
    // {
    //     this.name = name;
    //     this.age = age;
    //     this.power = power;

    //     System.out.println("");
    // }

    //more correct way

    Hero(String name, int age, String power)
    {

        super(name, age);
        this.power = power;
    }

    public String toString()
    {
        //you can call a method in the parent class using the super keyword as well. 
        return super.toString()+this.power;
    }
    
}
