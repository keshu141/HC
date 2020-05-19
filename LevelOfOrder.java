package coding;

import java.util.*;

class Node {
	int data;
	Node left, right;

	public Node(int item) // constructor
	{
		data = item;
		left = right = null;
	}
}

class LevelOfOrder {
	// Root of the Binary Tree
	Node root;

	public LevelOfOrder() {
		root = null;
	}

	// function to print level order traversal of tree
	void printLevelOrder() {
		int h = height(root);
		int i;
		for (i = 1; i <= h; i++)
			printLevel(root, i);
	}

	//Compute the height of a tree .
	 
	int height(Node root) {
		if (root == null)
			return 0;
		else {
			//compute height of each subtree 
			int Leftheight = height(root.left);
			int Rightheight = height(root.right);

			if (Leftheight > Rightheight)
				return (Leftheight + 1);
			else
				return (Rightheight + 1);
		}
	}

	// Print nodes at the given level
	void printLevel(Node root, int level) {
		if (root == null)
			return;
		if (level == 1)
			System.out.print(root.data + " ");
		else if (level > 1) {
			printLevel(root.left, level - 1);
			printLevel(root.right, level - 1);
		}
	}
   //Driver code
	public static void main(String args[]) {
		LevelOfOrder tree = new LevelOfOrder();
		tree.root = new Node(1);
		tree.root.right = new Node(2);
		tree.root.right.right = new Node(5);
		tree.root.right.right.left = new Node(3);
		tree.root.right.right.right = new Node(6);
		tree.root.right.right.left.right = new Node(4);

		tree.printLevelOrder();
	}
}