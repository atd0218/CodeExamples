/**
 * @author Ashton Daniels
 * Date: 02/08/2024
 * JDK Version: 8
 * Description: Run InOrder, PostOrder, and PreOrder traversal on a hardcoded tree that is added in. 
 */
public class myMain {

	public static void main(String[] args) {
		TreeTraversal tree = new TreeTraversal();

		tree.root = new Node(3);
        tree.root.left = new Node(5);
        tree.root.right = new Node(6);
        tree.root.left.left = new Node(27);
        tree.root.left.right = new Node(34);
		tree.root.left.left.left = new Node(64);
		tree.root.left.right.right = new Node(73);

		System.out.println("In Order Traversal:");
		tree.inOrder(tree.root);
		System.out.println("\n\nPre Order Traversal:");
		tree.preOrder(tree.root);
		System.out.println("\n\nPost Order Traversal:");
		tree.postOrder(tree.root);
		
	}

}

class Node {
	int value;
	Node left;
	Node right;

	Node(int value) {
		this.value = value;
		right = null;
		left = null;
	}
}

class TreeTraversal{

	Node root;

	TreeTraversal() {root = null;}

	/**
	 * 
	 * @param n node passed in to be ordered. 
	 * 
	 * traverse all of the left of the tree starting from root
	 * then print the root when that is all completed
	 * then traverse all of the right side of the tree
	 */
	void inOrder(Node n){
		if (n == null)
			return;
 
		// First recur on left child
		inOrder(n.left);
 
		// Then print the data of node
		System.out.print(n.value + " ");
 
		// Now recur on right child
		inOrder(n.right);
	}

	/**
	 * 
	 * @param n - node passed in to be ordered. 
	 * 
	 * First print the value of the root
	 * then traverse through left of subtree for values
	 * then traverse through right of subtree for values
	 */
	void preOrder(Node n)
    {
        if (n == null)
            return;
 
        // First print data of node
        System.out.print(n.value + " ");
 
        // Then recur on left subtree
        preOrder(n.left);
 
        // Now recur on right subtree
        preOrder(n.right);
    }
	/**
	 * 
	 * @param n - node passed in to be ordered
	 * 
	 * Traverse left subtree first and print
	 * traverse right subtree then print
	 * then print root. 
	 */
	void postOrder(Node n)
    {
        if (n == null)
            return;
 
        // First recur on left subtree
        postOrder(n.left);
 
        // Then recur on right subtree
        postOrder(n.right);
 
        // Now deal with the node
        System.out.print(n.value + " ");
    }
}
