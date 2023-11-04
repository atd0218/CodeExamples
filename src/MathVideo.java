public class MathVideo {
    
    public static void main(String[] args){

        //expression = operators & operands combined
        //operands = values, variables, numbers, quantity
        //operators = + - * / %

        //Since integers can only store whole numbers you can't use them in division as it will be inaccurate. It will truncate the remainder to a whole number.
        //you can always cast the output to a double if you have to store numbers as ints for some reason

        int tee = 12;
        int tee2 = 22;

        double tee3 = (double) tee2 / tee;

        int friends = 10;

        friends = friends + 1; //add
        friends++; //shorhand to add

        friends = friends - 1; //subtract
        friends--; //shorthand to minus

        friends = friends * 122; //multiply

        friends = friends / 2; //divide

        friends = friends % 1223; //get the remainder

        System.out.println(friends);
    }
}
