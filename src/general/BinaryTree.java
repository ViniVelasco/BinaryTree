package general;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

public class BinaryTree {
	
	Node root;
	
	private Node addRecursive(Node current, int value) {
		if(current == null) {
			return new Node(value);
		}
		
		if(value < current.value) {
			current.left = addRecursive(current.left, value);
		} else if (value > current.value) {
			current.right = addRecursive(current.right, value);
		} else {
			return current;
		}
		
		return current;
	}
	
	public void add(int value) {
		root = addRecursive(root, value);
	}
	
	private BinaryTree createBinaryTree() {
		BinaryTree bt = new BinaryTree();
		bt.add(6);
		bt.add(4);
		bt.add(8);
	    bt.add(3);
	    bt.add(5);
	    bt.add(7);
	    bt.add(9);
	 
	    return bt;
	}
	
	private boolean containsNodeRecursive(Node current, int value) {
	    if (current == null) {
	        return false;
	    } 
	    if (value == current.value) {
	        return true;
	    } 
	    return value < current.value
	      ? containsNodeRecursive(current.left, value)
	      : containsNodeRecursive(current.right, value);
	}
	
	public boolean containsNode(int value) {
	    return containsNodeRecursive(root, value);
	}
	
	@Test
	public void givenABinaryTree_WhenAddingElements_ThenTreeContainsThoseElements() {
		BinaryTree bt = createBinaryTree();
		assertTrue(bt.containsNode(6));
		assertTrue(bt.containsNode(4));
		
		assertFalse(bt.containsNode(1));
	}
	
	private Node deleteRecursive(Node current, int value) {
	    if (current == null) {
	        return null;
	    }
	 
	    if (value == current.value) {
	        // Node to delete found
	        // ... code to delete the node will go here
	    	if (current.left == null && current.right == null) {
	    	    return null;
	    	}
	    	
	    	if (current.right == null) {
	    	    return current.left;
	    	}
	    	 
	    	if (current.left == null) {
	    	    return current.right;
	    	}
	    	int smallestValue = findSmallestValue(current.right);
	    	current.value = smallestValue;
	    	current.right = deleteRecursive(current.right, smallestValue);
	    	return current;
	    } 
	    if (value < current.value) {
	        current.left = deleteRecursive(current.left, value);
	        return current;
	    }
	    current.right = deleteRecursive(current.right, value);
	    return current;
	}
	
	private int findSmallestValue(Node root) {
	    return root.left == null ? root.value : findSmallestValue(root.left);
	}
	
	public void delete(int value) {
	    deleteRecursive(root, value);
	}
	
	@Test
	public void givenABinaryTree_WhenDeletingElements_ThenTreeDoesNotContainThoseElements() {
	    BinaryTree bt = createBinaryTree();
	 
	    assertTrue(bt.containsNode(9));
	    bt.delete(9);
	    assertFalse(bt.containsNode(9));
	}

}
