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
import java.util.Arrays;
class Kth_Lagest_Smallest{
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

        Arrays.sort(arr);
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
