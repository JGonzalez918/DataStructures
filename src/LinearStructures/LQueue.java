package LinearStructures;

public class LQueue<T> implements AbstractQueue<T>
{
	private Link<T> head;
	
	private Link<T> tail;
	
	private int size;

	public LQueue() 
	{
		head = new Link<>();
		tail = head;
	}
	
	@Override
	public void enqueue(T value)
	{
		tail.next = new Link<>(value,null);
		tail = tail.next;
		size++;
	}

	@Override
	public T dequeue()
	{
		if(head.next == null) 
		{
			return null;
		}
		T save = head.next.element;
		head.next = head.next.next;
		size--;
		return save;
	}


	@Override
	public boolean isEmpty()
	{
		return head.next == null;
	}

	@Override
	public int size()
	{
		return size;
	}
	
	public String toString() 
	{
		StringBuilder s = new StringBuilder();
		s.append("[");
		Link<T> curr = head.next;
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
