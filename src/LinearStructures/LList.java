package LinearStructures;

public class LList<T> implements AbstractList<T>
{
	private Link<T> head;
	
	private Link<T> tail;
	
	private int size;
	
	public LList() 
	{
		head = new Link<>();
		tail = head;
		size = 0;
	}
	
	@Override
	public T get(int index)
	{
		if(index < 0 || index >= size) 
		{
			throw new IndexOutOfBoundsException();
		}
		
		Link<T> curr = head.next;
		while(index != 0) 
		{
			curr = curr.next;
			index--;
		}
		return curr.element;
	}

	@Override
	public void append(T item)
	{
		tail.next = new Link<T>(item, null);
		tail = tail.next;
		size++;
	}

	@Override
	public void insert(int index, T item)
	{
		if(index < 0 || index > size) 
		{
			throw new IndexOutOfBoundsException();
		}
		Link<T> curr = head;
		while(index != 0) 
		{
			curr = curr.next;
			index--;
		}
		curr.next = new Link<>(item,curr.next);
		if(curr == tail) tail = tail.next;
		size++;
	}

	@Override
	public void clear()
	{
		head.next = null;
		tail = head;
	}

	@Override
	public T remove(int index)
	{
		if(index < 0 || index >= size) 
		{
			throw new IndexOutOfBoundsException();
		}
		Link<T> curr = head;
		while(index != 0) 
		{
			curr = curr.next;
			index--;
		}
		if(curr.next == tail) tail = curr;
		curr.next = curr.next.next;
		size--;
		return null;
	}

	@Override
	public int size()
	{
		return size;
	}

	@Override
	public boolean isEmpty()
	{
		return tail == head;
	}
	
	public String toString() 
	{
		StringBuilder s = new StringBuilder();
		Link<T> curr = head.next;
		s.append("[");
		while(curr != null && curr.next != null) 
		{
			s.append(curr.element + ", ");
			curr = curr.next;
		}
		if(isEmpty() == false) s.append(curr.element);
		s.append("]");
		return s.toString();
	}

}
