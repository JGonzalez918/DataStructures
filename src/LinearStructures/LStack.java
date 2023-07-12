package LinearStructures;

public class LStack<T> implements AbstractStack<T>
{
	private Link<T> head;
	
	private int size;
	
	public LStack(){}
	
	@Override
	public void push(T item)
	{
		head = new Link(item, head);
		size++;
	}

	@Override
	public T pop()
	{
		if(isEmpty()) return null;
		T save = head.element;
		head = head.next;
		size--;
		return save;
	}

	@Override
	public T top()
	{
		if(isEmpty()) return null;
		return head.element;
	}

	@Override
	public T get(int index)
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
		return curr.element;
		
	}

	@Override
	public int size()
	{
		return size;
	}

	@Override
	public boolean isEmpty()
	{
		return head == null;
	}
	
	public String toString() 
	{
		StringBuilder s = new StringBuilder();
		s.append("Top of stack -> ");
		s.append("[");
		Link<T> curr = head;
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
