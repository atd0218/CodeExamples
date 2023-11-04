package OOPOverloadedConstructors;

public class Main {
    
    public static void main(String[] args)
    {

        //overloaded constructors = multiple constructors within a class have the same name
        //                          each constructor has the same name but different parameters
        //                          name + parameters = constructor method signature

        
        Pizza pizza1 = new Pizza();
        Pizza pizza2 = new Pizza("Thin Crust", "ALfredo");
        Pizza pizza3 = new Pizza("Thin Crust", "ALfredo", "Mozzerella");
        Pizza pizza4 = new Pizza("Thin Crust", "ALfredo", "Mozzerella", "Pepperoni");

        System.out.println("Pizza Ingredients");
        System.out.println(pizza1.bread);
        System.out.println(pizza1.sauce);
        System.out.println(pizza1.cheese);
        System.out.println(pizza1.topping);
        System.out.println();

        System.out.println("Pizza Ingredients");
        System.out.println(pizza2.bread);
        System.out.println(pizza2.sauce);
        System.out.println(pizza2.cheese);
        System.out.println(pizza2.topping);
        System.out.println();

        System.out.println("Pizza Ingredients");
        System.out.println(pizza3.bread);
        System.out.println(pizza3.sauce);
        System.out.println(pizza3.cheese);
        System.out.println(pizza3.topping);
        System.out.println();

        System.out.println("Pizza Ingredients");
        System.out.println(pizza4.bread);
        System.out.println(pizza4.sauce);
        System.out.println(pizza4.cheese);
        System.out.println(pizza4.topping);


    }

}
