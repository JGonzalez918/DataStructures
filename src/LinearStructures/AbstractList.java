package LinearStructures;

/**
 * Interface that defines general functions that a list should
 * support
 *
 * @param <T>
 */
public interface AbstractList<T>
{
	public T get(int index);
	
	public void append(T item);
	
	public void insert(int index, T item);
	
	public void clear();
	
	public T remove(int index);
	
	public int size();
	
	public boolean isEmpty();
}
