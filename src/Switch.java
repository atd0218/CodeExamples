public class Switch {
    
    public static void main(String[] args){

        String dow = "tew";

        // compare the variable for equality to a value if there is a match then it will print out the code. Otherwise it will move down. If there is no match use the default if not do nothing. 
        switch(dow) {
            case "Sunday": System.out.println("It is Sunday");
            break;

            case "Monday": System.out.println("It is Monday");
            break;

            case "Tuesday": System.out.println("It is Tuesday");
            break;

            case "Wednesday": System.out.println("It is Wednesday");
            break;

            case "Thursday": System.out.println("It is Thursday");
            break;

            case "Friday": System.out.println("It is Friday");
            break;

            case "Saturday": System.out.println("It is Saturday");
            break;

            default: System.out.println("That is not a day");
        
        }
    }
}
