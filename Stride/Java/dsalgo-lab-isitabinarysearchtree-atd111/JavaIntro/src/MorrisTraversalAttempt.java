    /**
 * @author Ashton Daniels
 * Date: 02/08/2024
 * JDK Version: 8
 * Description:
 */
import java.util.*;
import java.io.*;

public class MorrisTraversalAttempt {
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

    public class IsBST {
        class Node {
            int key;
            int left;
            int right;

            Node(int key, int left, int right) {
                this.left = left;
                this.right = right;
                this.key = key;
            }
        }

        int nodes;
        Node[] tree;

        void read() throws IOException {
            FastScanner in = new FastScanner();
            nodes = in.nextInt();
            tree = new Node[nodes];
            for (int i = 0; i < nodes; i++) {
                tree[i] = new Node(in.nextInt(), in.nextInt(), in.nextInt());
            }
        }

        Boolean isBinarySearchTree(Node root) {
          // Implement correct algorithm here
          Node current;
          Node pre;
 
          if (root == null)
              return false;
   
          current = pre = root;
          while (current != null) 
          {
              if (current.left == -1) 
              {
                  System.out.print(current.key + " ");
                  current.key = current.right;
              }
              else {
                  /* Find the inorder 
                      predecessor of current
                   */
                  pre.key = current.left;
                  while (pre.right != -1
                         && pre.right != current.key)
                      pre.key = pre.right;
   
                  /* Make current as right 
                     child of its
                   * inorder predecessor */
                  if (pre.right == -1) {
                      pre.right = current.key;
                      current.key = current.left;
                  }
   
                  /* Revert the changes made
                     in the 'if' part
                     to restore the original 
                     tree i.e., fix
                     the right child of predecessor*/
                  else
                  {
                      pre.right = -1;
                      System.out.print(current.key + " ");
                      current.key = current.right;
                  } /* End of if condition pre->right == NULL
                     */
   
              } /* End of if condition current->left == NULL*/
   
          } 
          return true;
        }
    }

    static public void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new myMain().run();
                } catch (IOException e) {
                }
            }
        }, "1", 1 << 26).start();
    }
    public void run() throws IOException {
        myMain.IsBST tree = new IsBST();
        tree.read();
        if (tree.isBinarySearchTree(tree.tree[0])) {
            System.out.println("CORRECT");
        } else {
            System.out.println("INCORRECT");
        }
    }

    
}
