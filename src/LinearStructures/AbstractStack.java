package LinearStructures;

public interface AbstractStack<T>
{
	public void push(T item);
	
	public T pop();
	
	public T top();
	
	public T get(int index);
	
	public int size();
	
	public boolean isEmpty();
	
	public String toString();
	
}
