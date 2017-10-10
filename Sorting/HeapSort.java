//Malav Shah - 10415717 - HW2b - HeapSort

import java.util.Scanner;

public class HeapSort
{
    public void sort(int arr[])
    {
        int n = arr.length;
 
        // Building heap
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);
 
        for (int i=n-1; i>=0; i--)
        {
            // Swapping current root to end
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
 
            // calling max heapify on the reduced heap
            heapify(arr, i, 0);
        }
    }
 

    void heapify(int arr[], int n, int i)
    {
        int largest = i;  // Initializing largest as root
        int l = 2*i + 1;  
        int r = 2*i + 2;  
 
        // If left child is larger than root
        if (l < n && arr[l] > arr[largest])
            largest = l;
 
        // If right child is larger than largest so far
        if (r < n && arr[r] > arr[largest])
            largest = r;
 
        // If largest is not root
        if (largest != i)
        {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
 
            heapify(arr, n, largest);
        }
    }
 
    //Printing array
    static void printArray(int arr[])
    {
        int n = arr.length;
        for (int i=0; i<n; ++i)
            System.out.print(arr[i]+" ");
        System.out.println();
    }
 
    public static void main(String args[])
    {
    	System.out.println("Number of test cases :");
    	Scanner scan = new Scanner(System.in);
    	int testCases = scan.nextInt();
    	int arr1[] = new int[100];
    	for(int i=1;i<=testCases;i++)
    	{
    		System.out.println("Number of elements to sort in test " + i);
    		int testElements = scan.nextInt();
    		int[] arr = new int[testElements];
    		for(int j=0;j<testElements;j++)
    		{
    	    	arr[j] = scan.nextInt();
    		}
    		int n = arr.length;
    		 
            HeapSort ob = new HeapSort();
            ob.sort(arr);
            
            arr1 = arr;
            System.out.println("Sorted array is");
            printArray(arr1);
        }
    	
    	scan.close();
    	
    }
    	
}