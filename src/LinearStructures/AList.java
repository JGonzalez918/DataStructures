package LinearStructures;

import java.util.Arrays;

public class AList<T> implements AbstractList<T>
{	
	private T[] arr;
	
	private int size;

	public AList() 
	{
		this(10);
	}
	
	
	@SuppressWarnings("unchecked")
	public AList(int size) 
	{
		arr = (T[]) new Object[size]; 
		size = 0;
	}
	
	public AList(AList<T> obj) 
	{
		this.arr = Arrays.copyOf(obj.arr, obj.size);
		this.size = obj.size;
	}
	
	
	/**
	 * Return element at index passed in
	 * Valid indexes are from 0 -> size - 1 if it is empty there is no valid index
	 */
	@Override
	public T get(int index)
	{
		if(index < 0 || index >= size) 
			throw new IndexOutOfBoundsException();
		return arr[index];
	}

	/**
	 * Append an item to the end of the list
	 * If the backing array is full then it will double in size
	 */
	@Override
	public void append(T item)
	{
		growIfNeeded();
		arr[size++] = item;
	}

	/**
	 * Insert an item at a specified index
	 * I don't check if the index is == size since inserting when index == size is the same as appending to the 
	 * end. So to remain consistent I allow inserting at end of the list 
	 */
	@Override
	public void insert(int index, T item)
	{
		if(index < 0 || index > size) return;
		shiftElementsRight(index);
		arr[index] = item;
		size++;
	}
	
	private void shiftElementsRight(int index) 
	{
		growIfNeeded();
		for(int i = size; i > index; i--) 
		{
			arr[i] = arr[i - 1];
		}
	}

	/**
	 * reset size but keep old arr length
	 * 
	 */
	@Override
	public void clear()
	{
		size = 0;
	}

	
	@Override
	public T remove(int index)
	{
		if(index < 0 || index >= size || size == 0) 
		{
			return null;
		}
		T save = arr[index];
		shiftElementsLeft(index);
		size--;
		return save;
	}
	
	private void shiftElementsLeft(int index) 
	{
		for(int i = index; i < size - 1; i++) 
		{
			arr[i] = arr[i + 1];
		}
	}

	@Override
	public int size()
	{
		return size;
	}

	@Override
	public boolean isEmpty()
	{
		return size == 0;
	}
	
	private void growIfNeeded() 
	{
		if(size < arr.length) return;
		arr = Arrays.copyOf(arr, size * 2);
	}
	
	public String toString() 
	{
		StringBuilder s = new StringBuilder();
		s.append("[");
		for(int i = 0; i < size - 1; i++) 
		{
			s.append(arr[i] + ", ");
		}
		if(isEmpty() == false) s.append(arr[size - 1]);
		s.append("]");
		return s.toString();
	}

}
