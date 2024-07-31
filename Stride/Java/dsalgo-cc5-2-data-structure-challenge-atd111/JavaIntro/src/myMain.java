import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Ashton Daniels
 * Date: 02/08/2024
 * JDK Version: 8
 * Description: Implemented my own version of stack and queue using array and arraylist. 
 * Also implemented the stack class as a generic class to practice working with generics.
 * 
 * In general you can enqueue items as well as display them and you can push items as well as display them. 
 */
public class myMain {

	public static void main(String[] args) {
		// prompt user or as many numbers as they would like to enter. 
		//auto close scanner using try_with_resources
		try(Scanner scanner = new Scanner(System.in);) {
			System.out.println("Please enter a number indicating the amount of integers you plan to provide.");
			int n = scanner.nextInt();
			System.out.println("Please enter " + n + " numbers.");
			int[] nums = new int[n];
			for (int i = 0; i < n; i++) {
				nums[i] = scanner.nextInt();
			}

			numberComputations(n, nums);
		}

		

	}

	static void numberComputations(int size, int[] nums) {
		// TODO Auto-generated method stub

		Queue queue = new Queue(size);
		Stack stack = new Stack(size);
		int queueSum = 0;
		int stackSum = 0;

		for(int i = 0; i < nums.length; i++) {
			if(nums[i] % 2 == 0) {
				queue.queueEnqueue(nums[i]);
				queueSum += nums[i];
			}
			else {
				stack.Push(nums[i]);
				stackSum += nums[i];
			}
		}

		//display queue items in the order they were entered.
		System.out.println("Queue Elements in order they were entered."); 
		queue.displayElements();

		//display sum of all queue elements. 
		System.out.println("The sum of all numbers in the queue is: "+ queueSum + "\n");

		//display all stack items in reverse order they were entered. 
		System.out.println("Stack elements in reverse order.");
		System.out.println(stack.toString());

		//display sum of all stack elements. 
		System.out.println("The sum of all numbers in the stack are: " + stackSum);
		
	}

}

class Queue {
	static private int front, rear, size;
	static private int queue[];

	Queue(int c) {
		front = 0;
		rear = 0;
		size = c;
		queue = new int[size];
	}

	// function to insert an element
    // at the rear of the queue
    void queueEnqueue(int data)
    {
        // check queue is full or not
        if (size == rear) {
            System.out.printf("Full Queue");
            return;
        }
 
        // insert element at the rear
        else {
            queue[rear] = data;
            rear++;
        }
        return;
    }

	void displayElements() {
        if (front == rear) {
            System.out.printf("Empty Queue");
            return;
        }
 
        // traverse front to rear and print elements
        for (int i = front; i < rear; i++) {
            System.out.printf(" %d ->", queue[i]);
        }
        System.out.println("\n");
	}
}

class Stack<T>{

	private int top,size;
	
	ArrayList<T> stack;

	Stack(int size){
		this.size = size;
		top = -1;
		stack = new ArrayList<T>(size);
	}

	void Push(T num) {

		// Checking if array is full
        if (top + 1 == size) {
 
            // Display message when array is full
            System.out.println("Stack is Full");
        }
        else {
 
            // Increment top to go to next position
            top = top + 1;
 
            // Over-writing existing element
            if (stack.size() > top)
                stack.set(top, num);
 
            else
 
                // Creating new element
                stack.add(num);
        }

	}

    // @Override
    public String toString()
    {
 
        String stackReverse = "";
 
        for (int i = top; i >= 0; i--) {
            stackReverse += String.valueOf(stack.get(i)) + " -> ";
        }
		System.out.println("\n");
        return stackReverse;
    }
}
