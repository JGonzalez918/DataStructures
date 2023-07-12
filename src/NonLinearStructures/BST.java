package NonLinearStructures;

public class BST<Type extends Comparable<Type>>
{
	public Node<Type> root;
	
	private int length;
	
	public BST() {}
	
	public boolean search(Type element) 
	{
		return recursiveSearch(root,element);
	}
	
	private boolean recursiveSearch(Node<Type> root, Type object) 
	{
		if(root == null) 
		{
			return false;
		}
		int compare = object.compareTo(root.element);
		if(compare < 0) 
		{
			return recursiveSearch(root.left,object);
		}
		else if(compare > 0)
		{
			return recursiveSearch(root.right,object);
		}
		else 
		{
			return true;
		}
	}

	public void append(Type element) 
	{
		root = recursiveAppend(root,element);
		
	}
	
	private Node<Type> recursiveAppend(Node<Type> root, Type object) 
	{
		if(root == null) 
		{
			Node<Type> newNode = new Node<Type>(object);
			return newNode;
		}
		int compareResult = object.compareTo(root.element);
		if(compareResult > 0) 
		{
			root.right = recursiveAppend(root.right,object);
		}
		else if(compareResult < 0) 
		{
			root.left = recursiveAppend(root.left,object);
		}
		return root;
	}
	
	public Type remove(Type object)
	{
		Node<Type> dummy = new Node<Type>(null);
		root = recursiveRemove(root,object,dummy);
		return dummy.element;
	}
	
	private Node<Type> recursiveRemove(Node<Type> curr, Type object, Node<Type> dummy) 
	{
		if(curr == null) 
		{
			dummy.element = null;
		}
		int compareResult = object.compareTo(curr.element);
		if(compareResult > 0) 
		{
			curr.right = recursiveRemove(curr.right,object,dummy);
		}
		else if(compareResult < 0)
		{
			curr.left = recursiveRemove(curr.left,object,dummy);
		}
		else 
		{
			dummy.element = curr.element;
			if(isLeaf(curr)) 
			{
				return null;
			}
			else if(curr.left != null && curr.right == null) 
			{
				return curr.left;
			}
			else if(curr.right != null && curr.left == null) 
			{
				return curr.right;
			}
			else 
			{
				Node<Type> dummy2 = new Node<Type>();
				curr.right = removeSuccsessor(curr.right,dummy2);
				curr.element = dummy2.element;
			}
		}
		return curr;
	}
	
	

	private Node<Type> removeSuccsessor(Node<Type> curr, Node<Type> dummy)
	{
		if(curr.left == null) 
		{
			dummy.element = curr.element;
			return curr.right;
		}
		else 
		{
			curr.left = removeSuccsessor(curr.left, dummy);
		}
		return curr;
	}

	private boolean isLeaf(Node<Type> node) 
	{
		return node.left == null && node.right == null;
	}
	
	public int getHeight() 
	{
		return depthFinder(root);
	}
	
	
	private int depthFinder(Node<Type> root) 
	{
		if(root == null) 
		{
			return 0;
		}
		return Math.max(depthFinder(root.left), depthFinder(root.right)) + 1;
	}

	public String toString() 
	{
		StringBuilder s = new StringBuilder();
		recursiveToString(root,s);
		return new String(s);
	}

	private void recursiveToString(Node<Type> root, StringBuilder s) 
	{
		if(root == null) return;
		recursiveToString(root.left,s);
		s.append(root.element.toString() + " ");
		recursiveToString(root.right,s);
		
	}
	
}
