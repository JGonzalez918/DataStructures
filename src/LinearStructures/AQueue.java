package LinearStructures;

public class AQueue<T> implements AbstractQueue<T>
{
	private T[] q;
	
	private int front;
	
	private int back;
	
	private int size;
	
	public AQueue(int size) 
	{
		q = (T[]) new Object[size];
		front = 0;
		back = 0;
		size = 0; 
	}
	
	@Override
	public void enqueue(T value)
	{
		if(isFull()) return;
		q[back] = value;
		back = (back + 1) % q.length;
		size++;
	}

	@Override
	public T dequeue()
	{
		if(isEmpty()) return null;
		T save = q[front];
		front = (front + 1) % q.length;
		size--;
		return save;
	}

	@Override
	public boolean isEmpty()
	{
		return size == 0;
	}

	@Override
	public int size()
	{
		return size;
	}
	
	public boolean isFull() 
	{
		return size == q.length;
	}
	
	public String toString() 
	{
		StringBuilder s = new StringBuilder();
		s.append("Front of queue -> [");
		for(int i = front, j = 0; j < size - 1;i = (i + 1) % q.length ,j++)
		{
			s.append(q[i] + ", ");
		}
		if(isEmpty() == false) s.append(q[back - 1]);
		s.append("]");
		return s.toString();
	}

}
