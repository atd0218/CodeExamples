/**
 * @author Ashton Daniels
 * Date: 02/06/2024
 * JDK Version: 8
 * Description: Take an input string from the user containing brackets to 
 * and then perform a check to make sure they are balanced and if not 
 * let the user know. 
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Stack;


/**
 * Bracket class will create a constructor to pass in two variables.
 * The character to store and the position to store it in. Then it 
 * declares these variables
 * 
 * Match function is used to check if the opening bracket in the stack
 * has a matching closing bracket. 
 */
class Bracket {
    Bracket(char type, int position) {
        this.type = type;
        this.position = position;
    }

    boolean Match(char c) {
        if (this.type == '[' && c == ']')
            return true;
        if (this.type == '{' && c == '}')
            return true;
        if (this.type == '(' && c == ')')
            return true;
        return false;
    }

    char type;
    int position;
}

/**
 * Take input from user
 * 
 * Create a stack to hold the opening brackets
 * 
 * loop through the length of the input and every time there
 * is an opening bracket store it. When you come accross a closing
 * bracket then check to see if it matches the most recent open
 * if so then pop the stack. 
 * 
 * After the loop check to see if the stack is empty and if so 
 * then the brackets are balanced. 
 */
class check_brackets {
    public static void main(String[] args) throws IOException {
        InputStreamReader input_stream = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input_stream);
        String text = reader.readLine();
        
        //having this outside the loop allows me to account for the scenario when
        //there are extra ending brackets. 
        int position;

        Stack<Bracket> opening_brackets_stack = new Stack<Bracket>();
        for (position = 0; position < text.length(); ++position) {
            Character next = text.charAt(position);

            if (next == '(' || next == '[' || next == '{') {
                // Process opening bracket, write your code here
                Bracket open = new Bracket(next, position);
                opening_brackets_stack.push(open);
            }

            if (next == ')' || next == ']' || next == '}') {
                // Process closing bracket, write your code here
                if(opening_brackets_stack.empty() ) {
                    break;
                }

                Bracket recentClose = opening_brackets_stack.peek();
                if(recentClose.Match(next)) {
                    opening_brackets_stack.pop();
                }
                
                // if (next == ')' && recentClose.type == '('
                // ||  next == ']' && recentClose.type == '['
                // ||  next == '}' && recentClose.type == '{'){
                //     opening_brackets_stack.pop();
                // }
            }
        }

        // Printing answer, write your code here
        if (opening_brackets_stack.empty() && position == text.length()) {
            System.out.println("The Brackets Are Balanced");
        }
        else {
            System.out.println("The Brackets Are Not Balanced");
        }

    }
}

