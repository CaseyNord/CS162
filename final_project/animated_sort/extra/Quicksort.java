// Adapted from https://www.geeksforgeeks.org/iterative-quick-sort/

import javafx.animation.KeyFrame;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

class Quicksort
{
    float animationSpeed;
    int frameCounter;

    Quicksort(float animSpd)
    {
        animationSpeed = animSpd;
        frameCounter = 0;
    }

    int partition(int array[], int visualArray[], int low, int high)
    {
        int pivot = array[high];
         
        // index of smaller element
        int i = (low-1); 
        for (int j = low; j <= high-1; j++)
        {
            // If current element is smaller than or
            // equal to pivot
            if (array[j] <= pivot)
            {
                i++;
 
                // swap array[i] and array[j]
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;

                // Scoped variables that can be passed to KeyFrame
                int keyi = i;
                int keyj = j;
                KeyFrame kf2 = new KeyFrame(Duration.seconds(animationSpeed+(frameCounter*animationSpeed)), actionEvent -> {
                
                display.keyFrameSwap(visualArray, keyi, keyj);

                });

                sortLoop.getKeyFrames().add(kf2);
                frameCounter++;
            }
        }
 
        // swap array[i+1] and array[high] (or pivot)
        int temp = array[i+1];
        array[i+1] = array[high];
        array[high] = temp;

        // Scoped variables that can be passed to KeyFrame
        int keyi = i;
        int keyHigh = high;
        KeyFrame kf3 = new KeyFrame(Duration.seconds(animationSpeed+(frameCounter*animationSpeed)), actionEvent -> {

        display.keyFrameSwap(visualArray, keyi + 1, keyHigh);
        
        });

        sortLoop.getKeyFrames().add(kf3);
        frameCounter++;
 
        return i+1;
    }
 
    /* A[] --> Array to be sorted, 
    l  --> Starting index, 
    h  --> Ending index */
    void quicksort(int array[], int visualArray[], int low, int high)
    {
        boolean notInOrder = ArrayManager.checkIfOrdered(sortArray);

        // Create an auxiliary stack
        int[] stack = new int[high-low+1];
    
        // initialize top of stack
        int top = -1;
    
        // push initial values of l and h to stack
        stack[++top] = low;
        stack[++top] = high;
    
        // Keep popping from stack while is not empty
        while (notInOrder)
        {
            // Pop h and l
            high = stack[top--];
            low = stack[top--];
    
            // Set pivot element at its correct position
            // in sorted array
            int p = partition(array, visualArray, low, high);
    
            // If there are elements on left side of pivot,
            // then push left side to stack
            if (p-1 > l)
            {
                stack[++top] = low;
                stack[++top] = p - 1;
            }
    
            // If there are elements on right side of pivot,
            // then push right side to stack
            if (p+1 < h)
            {
                stack[++top] = p + 1;
                stack[++top] = high;
            }

            notInOrder = ArrayManager.checkIfOrdered(sortArray);
        }

        // Final sorted frame to clear highlighted bars
        KeyFrame kf = new KeyFrame(Duration.seconds(animationSpeed+(frameCounter*animationSpeed)), actionEvent -> {

            display.drawArray(myArray);
        
        });

        sortLoop.getKeyFrames().add(kf);
        frameCounter++;
    }
}