package LinearStructures;

public class Link<T>
{
	public T element;
	
	public Link<T> next;
	
	public Link() {}
	
	public Link(T element, Link<T> next) 
	{
		this.element = element;
		this.next = next;
	}
	
	public String toString() 
	{
		return element.toString();
	}
}
