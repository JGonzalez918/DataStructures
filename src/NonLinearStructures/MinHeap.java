package NonLinearStructures;

import java.util.Arrays;

public class MinHeap<T extends Comparable<T>>
{
	private Comparable<T>[] heap;
	
	private int size;
	
	public MinHeap() 
	{
		this(10);
	}
	
	public MinHeap(int size) 
	{
		heap = new Comparable[size];
		size = 0;
	}
	
	public MinHeap(Comparable<T>[] heap) 
	{
		heapify(heap);
		size = heap.length;
	}
	
	public void add(T element) 
	{
		grow();
		heap[size] = element;
		heapUp(size);
		size++;
	}
	
	private void heapUp(int index) 
	{
		while(index != 0 && heap[getParent(index)].compareTo((T) heap[index]) > 0) 
		{
			swap(getParent(index), index);
			index  = getParent(index);
		}
	}
	
	public T poll() 
	{
		if(size < 0) return null;
		T save = (T) heap[0];
		swap(0,--size);
		heapDown(0);
		return save;
	}
	
	private void heapDown(int index) 
	{
		while(getLeftChild(index) < size) 
		{
			int compareIndex = getLeftChild(index);
			if(compareIndex + 1 < size && heap[compareIndex].compareTo((T) heap[compareIndex + 1]) > 0) 
			{
				compareIndex++;
			}
			
			if(heap[index].compareTo((T) heap[compareIndex]) < 0 ) 
			{
				return;
			}
			swap(index, compareIndex);
			index = compareIndex;
		}
	}
	
	public void heapify(Comparable<T>[] heap) 
	{
		for(int i = getParent(heap.length - 1); i >= 0; i--) 
		{
			heapDown(i);
		}
	}
	private void swap(int index1, int index2) 
	{
		T save = (T) heap[index1];
		heap[index1] = heap[index2];
		heap[index2] = save;
	}
	
	
	private int getParent(int index) 
	{
		return (index - 1)/2;
	}
	
	private int getLeftChild(int index) 
	{
		return index * 2 + 1;
	}
	
	private int getRightChild(int index) 
	{
		return index * 2 + 2;
	}
	
	public int size() 
	{
		return size;
	}
	
	public boolean isEmpty() 
	{
		return size == 0;
	}
	
	
	public String toString() 
	{
		StringBuilder s = new StringBuilder();
		for(int i = 0; i < size - 1; i++) 
		{
			s.append((T)heap[i] + ", ");
		}
		if(isEmpty() == false) s.append((T)heap[size - 1]);
		return s.toString();
	}
	
	private void grow() 
	{
		if(size < heap.length) return;
		
		heap = Arrays.copyOf(heap, size * 2);
	}
}