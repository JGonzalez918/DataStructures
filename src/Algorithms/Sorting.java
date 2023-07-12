package Algorithms;
import java.lang.reflect.Array;

public class Sorting 
{
	/**
	 * Sort a list using bubble sort
	 * 
	 * <p> bubble sort works by moving the largest element in the SubArray 
	 * to the end of the list. The SubArray is between zero and what the
	 * current value of i - 1 is. For example on the first iteration the
	 * sub array would be 0 to list.length - 2. We then move the largest element
	 * to what index of i for that particular iteration. In that same example we
	 * we would move the largest element to the index list.length - 1
	 * 
	 * <p> we move the largest element by constantly swapping elements when
	 * the element at j is > than the element at j + 1. This in turn will take
	 * the largest element an place it at the end
	 * 
	 * <p> the variable lastSwapindex is used to ensure that we don't waste
	 * resources checking already sorted elements. When we make a swap the variable
	 * gets sets to what j + 1 is as it will be decrement in the next iteration 
	 * making it j then we move the next largest element up to j 
	 * 
	 * @param <T> The object that the list holds
	 * @param list The list to be sorted
	 */
	public static <T extends Comparable<T>> 
	void bubbleSort(T[] list) 
	{
		for(int i = list.length - 1; i > 0; i--) 
		{
			int lastSwapIndex = 0;
			for(int j = 0; j < i; j++) 
			{
				if(list[j].compareTo(list[j + 1]) > 0) 
				{
					swap(list, j, j + 1);
					lastSwapIndex = j + 1;
				}
			}
			i = lastSwapIndex;
		}
	}
	
	/**
	 * Sort a list using insertion sort
	 * 
	 *<p> Insertion sort works by separating the array into two parts.
	 * the left part is sorted while the right part is unsorted
	 * Initially the left part will only contain one element. (A list
	 * of only one element is sorted) From every iteration onwards we take 
	 * the element that borders the sorted array and unsorted array and find its
	 * sorted position in the sorted array
	 * 
	 * <p> we can find its sorted position by iterating down until that 
	 * value we are looking at is less the element we are holding. This works 
	 * because the left array is sorted therefore once we find an element that is 
	 * less than the one we have it means that the one in front is greater than or 
	 * equal to the current one meaning it is its sorted position.
	 *
	 *
	 * @param <T> The object that the list holds
	 * @param list The list to be sorted
	 */
	public static <T extends Comparable<T>> void 
	insertionSort(T[] list) 
	{
		for(int i = 1; i < list.length; i++) 
		{
			T key = list[i];
			int j = i;
			while(j > 0 && key.compareTo(list[j - 1]) < 0) 
			{
				swap(list, j, j - 1);
				j--;
			}
		}
	}
	
	
	/**
	 * 
	 * <p> selection sort works by going through the array and 
	 * finding the largest element index once we finish iterating through the
	 * SubArray the largest element is swapped with the index of i
	 * 
	 * 
	 * @param <T> The object that the list holds
	 * @param list The list to be sorted
	 */
	public static <T extends Comparable<T>> void 
	selectionSort(T[] list) 
	{
		for(int i = list.length - 1; i > 0; i--) 
		{
			int largestIndex = i;
			for(int j = 0; j < i; j++) 
			{
				if(list[largestIndex].compareTo(list[j]) < 0) 
				{
					largestIndex = j;
				}
			}
			swap(list, i, largestIndex);
		}
	}
	
	/**
	 * 
	 * <p> merge sort works by making recursive calls to the merge() 
	 * function and diving the lists into two halves until there is one 
	 * element inside them. then the function returns to the call of merge() 
	 * before there was one element and calls sort() on the original list that 
	 * has two elements and two one element lists 
	 * 
	 * <p> given that two one element list are sorted the sort() function picks 
	 * the least element inside them and places it into the original list until there are
	 * no more elements to be placed
	 * 
	 * <p> now the original list is sorted and can be merged with its original 
	 * list this pattern continues until that full list is sorted
	 * 
	 * @param <T> The object that the list holds
	 * @param list The list to be sorted
	 */
	//===========Functions for merge sort=================
	public static <T extends Comparable<T>> void 
	mergeSort(T[] list) 
	{
		merge(list);
	} 
	
	public static <T extends Comparable<T>> void 
	merge(T[] list) 
	{
		if(list.length == 1) 
		{
			return;
		}
		
		int midpoint = list.length/2;
		T[] left = (T[]) Array.newInstance(list[0].getClass(), midpoint);
		T[] right = (T[]) Array.newInstance(list[0].getClass(), list.length - midpoint);
		for(int i = 0; i < midpoint; i++) 
		{
			left[i] = list[i];
		}
		for(int i = midpoint, j = 0; i < list.length; i++,j++) 
		{
			right[j] = list[i];
		}
		merge(left);
		merge(right);
		sort(list,left,right);
		
	} 
	
	public static <T extends Comparable<T>> void 
	sort(T[] list,T[] left, T[] right) 
	{
		int i = 0;
		int j = 0;
		while(i < left.length && j < right.length) 
		{
			if(left[i].compareTo(right[j]) < 0) 
			{
				list[i + j] = left[i];
				i++;
			}
			else 
			{
				list[i + j] =  right[j];
				j++;
			}
		}
		while(i < left.length) 
		{
			list[i + j] = left[i];
			i++;
		}
		
		while(j < right.length) 
		{
			list[i + j] = right[j];
			j++;
		}
	}
	//===========End of functions for merge sort==========
	
	
	/**
	 * heap sort works using the functions of the max heap data structure.
	 * It will first heapify the unsorted list and then call poll() and move 
	 * the extracted value to the end of the list until the heap is empty
	 * the end result is a sorted list
	 * 
	 * @param <T> The object that the list holds
	 * @param list The list to be sorted
	 */
	//===========Functions for heap sort==================
	public static <T extends Comparable<T>> void heapSort(T[] list) 
	{
		heapify(list);
		sort(list);
	}
	
	
	private static  <T extends Comparable<T>> void heapify(T[] list) 
	{
		for(int i = getParent(list.length - 1); i >= 0; i--) 
		{
			heapDown(list,i,list.length);
		}
	}
	
	 private static <T extends Comparable<T>> void 
	 heapDown(T[] heap, int index,int size) 
	 {
		 while(getLeftChild(index) < size) 
		 {
			 int compareIndex = getLeftChild(index);
			 
			 if(getRightChild(index) < size && 
		     heap[compareIndex].compareTo(heap[getRightChild(index)]) < 0) 
			 {
				 compareIndex = getRightChild(index);
			 }
			 
			 if(heap[index].compareTo(heap[compareIndex]) > 0) 
			 {
				 return;
			 }
			 swap(heap, index, compareIndex);
			 index = compareIndex;
		 }
	 }
	 
	 private static <T extends Comparable<T>> void 
	 sort(T[] heap) 
	 {
		 for(int i = 0; i < heap.length; i++) 
		 {
			 poll(heap,heap.length - i);
		 }
	 }	
	 
	 private static <T extends Comparable<T>> void 
	 poll(T[] heap, int length) 
	 {
		 swap(heap, 0, length - 1);
		 heapDown(heap, 0,length - 1);
		 return;
	 }
	 
	 private static int getParent(int index) 
	 {
		 return (index - 1)/2;
	 }
	 
	 private static int getRightChild(int index) 
	 {
		 return index * 2 + 2;
	 }
	 
	 private static int getLeftChild(int index) 
	 {
		 return index * 2 + 1;
	 }
	 

	//===========End of functions for heap sort===========
	 
	//I got this from https://opendsa-server.cs.vt.edu/OpenDSA/Books/CS3/html/Quicksort.html#introduction
    //All credit to them
	/**
	 * 
	 * <p> Quicksort works by taking a look at a given subarray 
	 * from the index i to j. From the given subarray we find a pivot
	 * element that will be used in the partition method. (in this 
	 * implementation it is the middle of the sublist) After we have 
	 * the index of the pivot element we then swap it with the 
	 * element at the end of the sub array. After the sway the pivot
	 * element is at the end of the subarray and i to j - 1 contains
	 * all other elements of the sublist
	 * 
	 * <p>then the list, i, j - 1, and the pivot element are passed
	 * into the partition method. We pass j - 1 because the index j
	 * is where the pivot is. In the partition method we move left and right
	 * pointers towards each other until they cross. Before they cross
	 * we first move the left pointer right until we find an element 
	 * greater than the pivot.  Then the right index moves left
	 * until it finds and element less than the pivot
	 * once they find them the swap. This happens until left and right 
	 * pass each other then the left index is return. The left index is now
	 * the sorted position of the pivot. once we return from the partition
	 * function we swap k with right (k is the sorted pos of the pivot and 
	 * right is the index that has the pivot element) Then we call quicksort 
	 * on the left part of k and right part of k and the process repeats
	 * 
	 * <p>if left doesnt find an element greater than the pivot then 
	 * the left pointer will stop once it hits right + 1 as that is the 
	 * variable j from the calling function which is the pivot then the next
	 * while loop will stop as left is >  right and no swap occurs then the 
	 * overall loop terminates and we return left we than swap k with right which 
	 * point to the same index meaning that the picot was already in its sorted positino
	 * 
	 * <p> if right doesnt find an element smaller than the pivot than it will do 
	 * the same thing as the above paragrpah only this time it moves left and the
	 * loop terminates since left is > right than the pivot is swapped with k which is the
	 * begging of the subarray meaning that the beginign was the sorted pos of the pivot
	 * 
	 * 
	 * @param <T> The object that the list holds
	 * @param list The list to be sorted
	 */
	//===========Start of functions for quick sort =======
	public static <T extends Comparable<T>> void 
	quickSort(T[] list)
	{
		quickSort(list, 0, list.length - 1);
	}
	
	private static <T extends Comparable<T>> void 
	quickSort(T[] list, int left, int right)
	{ 
		if(left >= right) return;
		
		if(right - left <= 3000) 
		{
			for(int i = left + 1; left <= right; i++) 
			{
				try 
				{
				T key = list[i];
				int j = i - 1;
				while(j >= i && list[j].compareTo(key) > 0) 
				{
					list[j + 1] = list[j];
					j--;
				}
				list[j + 1] = key;
				}
				catch (ArrayIndexOutOfBoundsException e) {
					System.out.println("right is " + right);
					System.out.println("i is " + i);
					System.exit(0);
				}
			}
		}
		
		int pivotIndex = (left + right)/2;
		medianOfThreeSort(list, left, right, pivotIndex);
		swap(list, pivotIndex, right - 1);
		int k = partition(list, left, right - 1, list[right - 1]);
		swap(list, k, right - 1);
		
		quickSort(list,left, k -1);
		quickSort(list, k + 1, right);
	}
	private static <T extends Comparable<T>> int 
	partition(T[] list, int left, int right,T pivot) 
	{
		while(left <= right) 
		{
			while(list[left].compareTo(pivot) < 0) 
			{
				left++;
			}
			
			while(left <= right && list[right].compareTo(pivot) >= 0) 
			{
				right--;
			}
			if(left < right) 
			{
				swap(list, left, right);
			}
		}
		return left;
	}
	
	private static <T extends Comparable<T>> void 
	medianOfThreeSort(T[] list, int left, int right, int middle) 
	{
		if(list[middle].compareTo(list[left]) < 0) 
		{
			swap(list, left, middle);
		}
		
		if(list[right].compareTo(list[middle]) < 0) 
		{
			swap(list, right, middle);
		}
		
		if(list[middle].compareTo(list[left]) < 0) 
		{
			swap(list, left, middle);
		}
	}
	//===========End of functions for quick sort==========
	
	
	/**
	 * <p> Find the kth smallest element in a list 
	 * 
	 * <p>Quick select works by using quicksort it follows the same 
	 * process by  picking a pivot and finding its sorted
	 * pos. However once we find the sorted pos of the pivot we only do quicksort 
	 * on the side of the list that has k - 1. That is because if the array was
	 * sorted and we want to find the kth smallest element it would be at k - 1
	 * However that would take O(n log n) times to sort  with quickselect we sort the pivot 
	 * and move towards k - 1 like a binary search until we reach k - 1
	 * 
	 * @param <T> The object that the list holds
	 * @param list The list to be searched
	 * @param k the number of the smallest element you want to find
	 * @return T the kth smallest element
	 */
	//===========Quick Selection Algorithm================
	public static <T extends Comparable<T>> T
	quickSelect(T[] list, int k) 
	{
		return quickSelect(list,k,0,list.length - 1);
	}
	
	
	private static <T extends Comparable<T>> T
	quickSelect(T[] list, int k, int left, int right) 
	{
		if(left <= right) 
		{
			int pivotIndex = (left + right)/2;
			medianOfThreeSort(list, left, right, pivotIndex);
			swap(list, pivotIndex, right - 1);
			int sortedPos = partition(list, left, right - 1, list[right - 1]);
			swap(list, sortedPos, right - 1);
			
			if(sortedPos == k - 1) 
			{
				return list[sortedPos];
			}
			else if(sortedPos > k - 1) 
			{
				return quickSelect(list, k,left, sortedPos - 1);
			}
			else 
			{
				return quickSelect(list, k, sortedPos + 1, right);
			}
		}
		return null;
	}

	//===========End of QuickSelect Algorithm=============
	
	public static <T> void swap(T[] list, int index1,int index2) 
	{
		T save = list[index1];
		list[index1] =  list[index2];
		list[index2] = save;
	}

}