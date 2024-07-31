/**
 * @author Ashton Daniels
 * Date: 02/06/2024
 * JDK Version: 8 
 * Description: This program allows you to add, remove, and find the max item in a stack. 
 */
import java.util.*;
import java.io.*;

public class myMain {
    class FastScanner {
        StringTokenizer tok = new StringTokenizer("");
        BufferedReader in;

        FastScanner() {
            in = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() throws IOException {
            while (!tok.hasMoreElements())
                tok = new StringTokenizer(in.readLine());
            return tok.nextToken();
        }
        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

    /**
     * allows you to push and pop from a stack. 
     * @throws IOException
     * the max portion works as follows:
     * 
     * 1. Create a secondary(Auxiliary) stack called "maxStack"
     * 2. when you push an item for the first time add it to both stacks. 
     * 3. Going forward for pushes compare the current value with the maxStack peek value. 
     *     if the value is greater then push that value to maxStack as well as main Stack
     *     if value is less then push the peek value again to the maxStack
     * 4. Each time an item is pop from the main stack pop an item from maxStack as well. 
     * 5. When max is called I simply print the peek value of maxStack. 
     */
    public void solve() throws IOException {
        FastScanner scanner = new FastScanner();
        int queries = scanner.nextInt();
        Stack<Integer> stack = new Stack<Integer>();
        Stack<Integer> maxVal = new Stack<Integer>();

        for (int qi = 0; qi < queries; ++qi) {
            String operation = scanner.next();
            if ("push".equals(operation)) {
                if (stack.empty()) {
                    int value = scanner.nextInt();
                    stack.push(value);
                    maxVal.push(value);
                }
                else
                {
                    int value = scanner.nextInt();
                    stack.push(value);
                    if (stack.peek() > maxVal.peek()) {
                        maxVal.push(value);
                    }
                    else {
                        int val = maxVal.peek();
                        maxVal.push(val);
                    }
                }
            } else if ("pop".equals(operation)) {
                stack.pop();
                maxVal.pop();
            } else if ("max".equals(operation)) {
                //TODO: return the max value from the stack
                System.out.println(maxVal.peek());
            }
        }
    }

    static public void main(String[] args) throws IOException {
        new myMain().solve();
    }
}
