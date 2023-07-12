package LinearStructures;

public interface AbstractQueue<T>
{
	public void enqueue(T value);
	
	public T dequeue();
	
	public boolean isEmpty();
	
	public int size();
	
	public String toString();
}
