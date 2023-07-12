package NonLinearStructures;

public class Node<Type extends Comparable<Type>>
{
	Type element;
	
	Node<Type> left;
	
	Node<Type> right;
	
	public Node() {}
	
	public Node(Type element) 
	{
		this.element = element;
	}
	
	public Node(Type element, Node<Type> left, Node<Type> right) 
	{
		this.element = element;
		this.left = left;
		this.right = right;
	}
	
	
	public String toString() 
	{
		return element == null ? "Null" : element.toString();
	}
}