/**
 * @author Ashton Daniels
 * Date: 02/06/2024
 * JDK Version: 8
 * Description: Recursively find the depth of each element and then find the max depth and return that
 * to the user. 
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

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

	public class TreeHeight {
		int n;
		int parent[];
		
		/**
		 * Take user input and add it into the parent array at time of creating the array. 
		 * @throws IOException
		 */
		void read() throws IOException {
			FastScanner in = new FastScanner();
			n = in.nextInt();
			parent = new int[n];
			for (int i = 0; i < n; i++) {
				parent[i] = in.nextInt();
			}
		}

		/**
		 * 
		 * @param parent - parent array obtained from user
		 * @param i - the index of the element to focus on to see if depth needs
		 * to be calculated
		 * @param depth - depth array to keep track of the depth of all elements.
		 * 
		 * Recursively go through the elements and calculate depth of all elements.
		 * Then iterate through depth array and find highest value. 
		 */
		void fillDepth(int parent[], int i, int depth[])
		{
			

			/*
			 * implement memoization here by seeing if the depth has already been tracked
			 * and if it has then skip this iteration and move to the next. 
			 */
			// If depth is already filled
			if (depth[i] != 0) {
				return;
			}
	
			// if root is found then set depth to 1
			if (parent[i] == -1) {
				depth[i] = 1;
				return;
			}
	
			//make the call to recursively find the depth of the parent
			//once that is known you can find children depth. 
			if (depth[i] == 0) {
				fillDepth(parent, parent[i], depth);
			}
	
			// Depth of this node is depth of parent plus 1
			depth[i] = depth[parent[i]] + 1;
		}
		int computeHeight()
		{
	
			// Create an array to store depth of all nodes/ and
			// initialize depth of every node as 0 (an invalid
			// value). Depth of root is 1
			int depth[] = new int[n];
			for (int i = 0; i < n; i++) {
				depth[i] = 0;
			}
	
			// fill depth of all nodes
			for (int i = 0; i < n; i++) {
				fillDepth(parent, i, depth);
			}
	
			// The height of binary tree is maximum of all
			// depths. Find the maximum value in depth[] and
			// assign it to ht.
			int ht = depth[0];
			for (int i = 1; i < n; i++) {
				if (ht < depth[i]) {
					ht = depth[i];
				}
			}
			return ht;
		}
	}


	static public void main(String[] args) throws IOException {
		//threaded as that was how it was presented to us
		//threading allows for multiple tasks to be running at once in different
		//threads
           new Thread(null, new Runnable() {
                    public void run() {
                        try {
			    			Long startTime = System.nanoTime();
                            new myMain().run();
  		            		System.out.println(System.nanoTime()-startTime);
                        } catch (IOException e) {
                        }
                    }
                }, "1", 1 << 26).start();
	}
	public void run() throws IOException {
		TreeHeight tree = new TreeHeight();
		tree.read();
		System.out.println(tree.computeHeight());
	}
}
