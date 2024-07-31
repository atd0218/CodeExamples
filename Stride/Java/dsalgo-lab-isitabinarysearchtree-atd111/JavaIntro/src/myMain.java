/**
 * @author Ashton Daniels
 * Date: 02/08/2024
 * JDK Version: 8
 * Description: This program will recursively move through the tree using min and max values
 * to ensure that the critera for a binary search tree is met. 
 * 
 * 1. all left children of the current node are less than the node
 * 2. all right children of the current node are greater than the node. 
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
            if (nodes == 0){
                System.out.println("CORRECT");
            }
            else {
                tree = new Node[nodes];
                for (int i = 0; i < nodes; i++) {
                tree[i] = new Node(in.nextInt(), in.nextInt(), in.nextInt());
                }
        }
        }

        Boolean isBinarySearchTree(Node root) {
          // Implement correct algorithm here

          //find min of tree

          //find max of tree
          return isValidBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }

        boolean isValidBST(Node node, int min, int max) {
            if (node == null) {
                return true;
            } 
            if (node.key < min || node.key >= max) {
                return false;
            }
        
            return isValidBST(getNode(node.left), min, node.key) && 
                   isValidBST(getNode(node.right), node.key, max); 
        }

        Node getNode(int id) {
            // Get node by id
            if (id == -1) {
                return null;
            }
            return tree[id];
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
    public void run() throws IOException, NullPointerException {
        myMain.IsBST tree = new IsBST();
        tree.read();
        try {
            if (tree.isBinarySearchTree(tree.tree[0])) {
                System.out.println("CORRECT");
            } else {
                System.out.println("INCORRECT");
            }
        }
        catch (NullPointerException e) {
            System.out.print(" ");
        }
        
    }
}
