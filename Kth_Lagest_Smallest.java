/*Find the Kth Largest/Smallest Element in an Array.
  eg: Array=[ 12, 11, 13, 5, 6, 7]
      ->Find 2nd Largest Number.
      First Sort the Array=[5,6,7,11,12,13]
      Hence 2nd Smallest number is 6
      ->Find 2nd Largest Number
        2nd Largest Number is 12

*/
// Java program for Merge Sort
import java.io.*;
import java.util.Scanner;
class Kth_Lagest_Smallest{
    // Merges two subarrays of arr[].
    // First subarray is arr[l..m].
    // Second subarray is arr[m+1..r].
    static void merge(int arr[], int l, int m, int r)
    {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        // Create temp arrays
        int L[] = new int[n1];
        int R[] = new int[n2];

        // Copy data to temp arrays
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];

        // Merge the temp arrays

        // Initial indices of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarray array
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            }
            else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements of L[] if any
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        // Copy remaining elements of R[] if any
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }
    // Main function that sorts arr[l..r] using
    // merge()
    static void sort(int arr[], int l, int r)
    {
        if (l < r) {

            // Find the middle point
            int m = l + (r - l) / 2;

            // Sort first and second halves
            sort(arr, l, m);
            sort(arr, m + 1, r);

            // Merge the sorted halves
            merge(arr, l, m, r);
        }
    }
    // A utility function to print array of size n
    static void printArray(int arr[])
    {
        int n = arr.length;
        for (int i = 0; i < n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }
    
    static void findSmallest(int arr[], int k){
        System.out.println(k+"KTh smallest number is:"+arr[k-1]);
    }
    static void findLargest(int arr[],int n, int k2){
        System.out.println(k2+"KTh largest number is:"+arr[n-k2]);
    }
    // Driver code
    public static void main(String args[])
    {
        int arr[] = { 12, 11, 13, 5, 6, 7 };
        System.out.println("Given array is");
        printArray(arr);
        sort(arr, 0, arr.length - 1);
        System.out.println("\nSorted array is");
        printArray(arr);
        Scanner i = new Scanner(System.in);
        System.out.println("Enter the K value for smallest number:");
        int k = i.nextInt();
        findSmallest(arr,k);
        
        System.out.println("Enter the K value for largest number number:");
        int k2 = i.nextInt();
        findLargest(arr,arr.length,k2);
        
    }
}
