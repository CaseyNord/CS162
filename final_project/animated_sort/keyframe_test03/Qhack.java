import java.util.*;
 
class Qhack
{ 
/* A[] --> Array to be sorted, 
   l  --> Starting index, 
   h  --> Ending index */
static void quickSortIterative (int arr[], int low, int high)
{
    // Create an auxiliary stack
    int[] stack = new int[high-low+1];
  
    // initialize top of stack
    int top = -1;
  
    // push initial values of l and h to stack
    stack[++top] = low;
    stack[++top] = high;
  
    // Keep popping from stack while is not empty
    while (top >= 0)
    {
        // Pop h and l
        high = stack[top--];
        low = stack[top--];
  
        // Set pivot element at its correct position
        // in sorted array
        int pivot = arr[high];
         
        // index of smaller element
        int i = (low-1); 
        for (int j = low; j <= high-1; j++)
        {
            // If current element is smaller than or
            // equal to pivot
            if (arr[j] <= pivot)
            {
                i++;
 
                // swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
 
        // swap arr[i+1] and arr[high] (or pivot)
        int temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;

        int p = i+1;
  
        // If there are elements on left side of pivot,
        // then push left side to stack
        if (p-1 > low)
        {
            stack[++top] = low;
            stack[++top] = p - 1;
        }
  
        // If there are elements on right side of pivot,
        // then push right side to stack
        if (p+1 < high)
        {
            stack[++top] = p + 1;
            stack[++top] = high;
        }
    }
}
    // Driver code
    public static void main(String args[])
    {
        int arr[] = {4, 3, 5, 2, 1, 3, 2, 3};
        int n = 8;
         
        // Function calling
        quickSortIterative(arr, 0, n-1);
         
        for(int i = 0; i < n; i++){
            System.out.print(arr[i] + " ");
        }
    }
}