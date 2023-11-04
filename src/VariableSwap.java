public class VariableSwap {
    
    public static void main(String[] args){

        // to switch variables you first want to store the existing variable somewhere so you do not lose it. 
        String x = "water";
        String y = "Kool-Aid";
        String temp;
        
        temp=x;
        x=y;
        y=temp;

        System.out.println("X: "+x);
        System.out.println("y: "+y);

    }
}
