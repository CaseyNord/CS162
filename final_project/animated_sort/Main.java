//*****************************************************************
// Name: Casey Nord
// Class: CS162 Spring 2018
// Class Time: Mon/Wed/Fri 10:00am
// Date: June 12, 2018
// Final Project
// Driver Name: Main.java
// Program Description: Animated sorting program
//*****************************************************************

// All sorts adapted from https://www.geeksforgeeks.org/

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application
{
    final int SCALE = 16;

    int width = 50;
    int height = 25;

    private SortScreen display;

    Timeline sortLoop;
    int frameCounter = 0; // Keeps tracks of frames generated (otherwise out KeyFrames won't increment properly)

    float animationSpeed = .25f;  // Default setting to medium sort speed
    int sortIdentifier = 0; // Used with buttons to pick options for sorts

    // Create two copies of an array the sort...
    // One for running the sort and one for displaying the sort
    int[] sortArray = ArrayManager.createRandomArray(width, height);
    int[] visualArray = ArrayManager.copyArray(sortArray);


    @Override
    public void start(Stage primaryStage) throws Exception
    {
        display = new SortScreen(width, height, SCALE);
        sortLoop = new Timeline();

        // Draws initial unsorted array prior to sorting
        display.drawArray(visualArray);
    
        // GUI layout
        Button bubbleSortButton = new Button("Bubble Sort");
        Button quickSortButton = new Button("Quick Sort");
        Button selectionSortButton = new Button("Selection Sort");
        Button insertionSortButton = new Button("Insertion Sort");
        Button combSortButton = new Button("Comb Sort");
        Button gnomeSortButton = new Button("Gnome Sort");

        Button sortButton = new Button("Start/Resume");
        Button stopButton = new Button("Stop");
        Label spacer1 = new Label("         ");
        Button randomizeArray = new Button("Randomize!");
        Label spacer2 = new Label("         ");
        Button setSpeedSlow = new Button("Sort Speed: Slow");
        Button setSpeedMedium = new Button("Sort Speed: Medium");
        Button setSpeedFast = new Button("Sort Speed: Fast");

        GridPane sortButtonGrid = new GridPane();
        sortButtonGrid.add(bubbleSortButton, 0, 0);
        sortButtonGrid.add(quickSortButton, 0, 1);
        sortButtonGrid.add(selectionSortButton, 0, 2);
        sortButtonGrid.add(insertionSortButton, 0, 3);
        sortButtonGrid.add(combSortButton, 0, 4);
        sortButtonGrid.add(gnomeSortButton, 0, 5);

        GridPane bottomButtonsGrid = new GridPane();
        bottomButtonsGrid.add(sortButton, 0, 0);
        bottomButtonsGrid.add(stopButton, 1, 0);
        bottomButtonsGrid.add(spacer1, 2, 0);
        bottomButtonsGrid.add(randomizeArray, 3, 0);
        bottomButtonsGrid.add(spacer2, 4, 0);
        bottomButtonsGrid.add(setSpeedSlow, 5, 0);
        bottomButtonsGrid.add(setSpeedMedium, 6, 0);
        bottomButtonsGrid.add(setSpeedFast, 7, 0);

        GridPane grid = new GridPane();
        grid.add(display, 0, 0);
        grid.add(sortButtonGrid, 1, 0);
        grid.add(bottomButtonsGrid, 0, 1);

        primaryStage.setTitle("Sort");
        primaryStage.setScene(new Scene(grid, width*SCALE+113, height*SCALE+31));
        primaryStage.show();

        // GUI functionality
        randomizeArray.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override public void handle(ActionEvent e)
            {
                // Create two copies of an array the sort...
                // One for running the sort and one for displaying the sort
                sortArray = ArrayManager.createRandomArray(width, height);
                visualArray = ArrayManager.copyArray(sortArray);
                sortLoop.stop(); // Set draw loop back to the beginning
                sortLoop.getKeyFrames().clear(); // Clear out all KeyFrames
                frameCounter = 0; // Reset this to reset animation
                display.drawArray(visualArray);
            }
        });

        stopButton.setOnAction(actionEvent -> sortLoop.pause());
        setSpeedSlow.setOnAction(actionEvent -> animationSpeed = 1);
        setSpeedMedium.setOnAction(actionEvent -> animationSpeed = .25f);
        setSpeedFast.setOnAction(actionEvent -> animationSpeed = .01f);

        bubbleSortButton.setOnAction(actionEvent -> sortIdentifier = 1);
        quickSortButton.setOnAction(actionEvent -> sortIdentifier = 2);
        selectionSortButton.setOnAction(actionEvent -> sortIdentifier = 3);
        insertionSortButton.setOnAction(actionEvent -> sortIdentifier = 4);
        combSortButton.setOnAction(actionEvent -> sortIdentifier = 5);
        gnomeSortButton.setOnAction(actionEvent -> sortIdentifier = 6);

        sortButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {

                switch(sortIdentifier)
                {
                    case 1: // Bubble Sort
                    {
                        System.out.println("Bubble Sort");

                        // Initialize sort to condition to false (or very very unlikely true if we generate a sorted array)
                        boolean notInOrder = ArrayManager.checkIfOrdered(sortArray);

                        // Minus 1 to prevent out of bounds errors
                        int arrayLength = visualArray.length - 1;

                        // KeyFrame loop
                        while (notInOrder)
                        {
                            for (int index = 0; index < arrayLength; index++)
                            {
                                // Index variable with scope that can be passed to KeyFrame
                                int keyIndex = index;
                            
                                // Pre-swap frame
                                KeyFrame kf1 = new KeyFrame(Duration.seconds(animationSpeed+(frameCounter*animationSpeed)), actionEvent -> {
            
                                    display.drawHighlightedArray(visualArray, keyIndex, keyIndex + 1);
                                
                                });
            
                                sortLoop.getKeyFrames().add(kf1);
                                frameCounter++;
                                
                                // Perform bubble sort
                                if (sortArray[index] > sortArray[index + 1])
                                {
                                    int swap = sortArray[index];
                                    sortArray[index] = sortArray[index + 1];
                                    sortArray[index + 1] = swap;

                                    // Swap frame
                                    KeyFrame kf2 = new KeyFrame(Duration.seconds(animationSpeed+(frameCounter*animationSpeed)), actionEvent -> {
                                        
                                        display.keyFrameSwap(visualArray, keyIndex, keyIndex + 1);

                                    });
                
                                    sortLoop.getKeyFrames().add(kf2);
                                    frameCounter++;
                                }

                                // Check if array is ordered and reset for loop if not
                                notInOrder = ArrayManager.checkIfOrdered(sortArray);
                                if (notInOrder)
                                {
                                    if (index == arrayLength - 1)
                                    {
                                        index = -1;
                                        arrayLength--;
                                    }
                                }
                            }
                        }

                        Sorts.sortTest(sortArray);
                        break;
                    }
                    case 2: // Quick Sort
                    {
                        System.out.println("Quick Sort");
                        
                        // Initialize sort to condition to false (or very very unlikely true if we generate a sorted array)
                        boolean notInOrder = ArrayManager.checkIfOrdered(sortArray);

                        int low = 0;
                        int high = sortArray.length - 1;
                        
                        // Create an auxiliary stack
                        int[] stack = new int[high-low+1];

                        // initialize top of stack
                        int top = -1;
                    
                        // push initial values of l and h to stack
                        stack[++top] = low;
                        stack[++top] = high;
                        
                        // KeyFrame loop
                        // Keep popping from stack while is not empty
                        while (notInOrder)
                        {
                            // Pop h and l
                            high = stack[top--];
                            low = stack[top--];
                    
                            // Set pivot element at its correct position
                            // in sorted array
                            int pivot = sortArray[high];
                            
                            // index of smaller element
                            int i = (low-1);
        
                            for (int j = low; j <= high-1; j++)
                            {
                                // If current element is smaller than or
                                // equal to pivot
                                if (sortArray[j] <= pivot)
                                {
                                    i++;
                                    
                                    int temp = sortArray[i];
                                    sortArray[i] = sortArray[j];
                                    sortArray[j] = temp;

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

                            // swap arr[i+1] and arr[high] (or pivot)
                            int temp = sortArray[i+1];
                            sortArray[i+1] = sortArray[high];
                            sortArray[high] = temp;

                            // Scoped variables that can be passed to KeyFrame
                            int keyi = i;
                            int keyHigh = high;
                            KeyFrame kf3 = new KeyFrame(Duration.seconds(animationSpeed+(frameCounter*animationSpeed)), actionEvent -> {

                                display.keyFrameSwap(visualArray, keyi + 1, keyHigh);
                            
                            });
        
                            sortLoop.getKeyFrames().add(kf3);
                            frameCounter++;

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

                            notInOrder = ArrayManager.checkIfOrdered(sortArray);
                        }

                        break;
                    }
                    case 3: // Selection Sort
                    {
                        System.out.println("Selection Sort");
 
                        // One by one move boundary of unsorted subarray CHANGE
                        for (int i = 0; i < sortArray.length - 1; i++)
                        {
                            // Find the minimum element in unsorted array CHANGE
                            int minimumIndex = i;
                            for (int j = i + 1; j < sortArray.length; j++)
                            {
                                if (sortArray[j] < sortArray[minimumIndex])
                                {
                                    minimumIndex = j;
                            
                                }
                            }
                            // Swap the found minimum element with the first CHANGE
                            // element
                            int temp = sortArray[minimumIndex];
                            sortArray[minimumIndex] = sortArray[i];
                            sortArray[i] = temp;

                            // Scoped variables that can be passed to KeyFrame
                            int keyi = i;
                            int keyj = minimumIndex;
                            KeyFrame kf2 = new KeyFrame(Duration.seconds(animationSpeed+(frameCounter*animationSpeed)), actionEvent -> {
                            
                                display.keyFrameSwap(visualArray, keyi, keyj);

                            });

                            sortLoop.getKeyFrames().add(kf2);
                            frameCounter++;
                        }

                        break;
                    }
                    case 4: // Insertion Sort
                    {
                        System.out.println("Insertion Sort");

                        int key, j;
                        for (int i = 1; i < sortArray.length; i++)
                        {
                            key = sortArray[i];
                            j = i - 1;
                      
                            /* Move elements of arr[0..i-1], that are
                               greater than key, to one position ahead
                               of their current position */
                            while (j >= 0 && sortArray[j] > key)
                            {
                                sortArray[j + 1] = sortArray[j];

                                int keyj = j;
                                KeyFrame kf2 = new KeyFrame(Duration.seconds(animationSpeed+(frameCounter*animationSpeed)), actionEvent -> {
                                
                                    visualArray[keyj + 1] = visualArray[keyj];
                                    display.drawHighlightedArray(visualArray, keyj + 1, keyj);
    
                                });
    
                                sortLoop.getKeyFrames().add(kf2);
                                frameCounter++;

                                j = j - 1;
                            }
                            sortArray[j + 1] = key;

                            int keyj = j;
                            int keyKey = key;
                            KeyFrame kf2 = new KeyFrame(Duration.seconds(animationSpeed+(frameCounter*animationSpeed)), actionEvent -> {
                            
                                visualArray[keyj + 1] = keyKey;
                                display.drawHighlightedArray(visualArray, keyj, keyKey);

                            });

                            sortLoop.getKeyFrames().add(kf2);
                            frameCounter++;
                        }

                        break;
                    }
                    case 5: // Comb Sort
                    {
                        System.out.println("Comb Sort");
                        
                        // initialize gap
                        int gap = sortArray.length;
                 
                        // Initialize swapped as true to make sure that
                        // loop runs
                        boolean swapped = true;
                 
                        // Keep running while gap is more than 1 and last
                        // iteration caused a swap
                        while (gap != 1 || swapped == true)
                        {
                            // Find next gap
                            // Shrink gap by Shrink factor
                            gap = (gap*10)/13;
                            if (gap < 1)
                            {
                                gap = 1;
                            }
                 
                            // Initialize swapped as false so that we can
                            // check if swap happened or not
                            swapped = false;
                 
                            // Compare all elements with current gap
                            for (int i = 0; i < sortArray.length - gap; i++)
                            {
                                if (sortArray[i] > sortArray[i + gap])
                                {
                                    // Swap arr[i] and arr[i+gap]
                                    int temp = sortArray[i];
                                    sortArray[i] = sortArray[i + gap];
                                    sortArray[i + gap] = temp;
                 
                                    int keyi = i;
                                    int keyGap = i + gap;
                                    KeyFrame kf2 = new KeyFrame(Duration.seconds(animationSpeed+(frameCounter*animationSpeed)), actionEvent -> {
                                    
                                        display.keyFrameSwap(visualArray, keyi, keyGap);
        
                                    });
        
                                    sortLoop.getKeyFrames().add(kf2);
                                    frameCounter++;

                                    // Set swapped
                                    swapped = true;
                                }
                            }
                        }

                        break;
                    }
                    case 6: // Gnome Sort
                    {
                        System.out.println("Gnome Sort");

                        // Initialize sort to condition to false (or very very unlikely true if we generate a sorted array)
                        boolean notInOrder = ArrayManager.checkIfOrdered(sortArray);

                        int index = 0;

                        while (notInOrder)
                        {
                            if (index == 0)
                            {
                                index++;
                            }
                            if (sortArray[index] >= sortArray[index - 1])
                            {
                                index++;
                            }
                            else
                            {
                                int temp = sortArray[index];
                                sortArray[index] = sortArray[index - 1];
                                sortArray[index - 1] = temp;

                                int keyi = index;
                                KeyFrame kf2 = new KeyFrame(Duration.seconds(animationSpeed+(frameCounter*animationSpeed)), actionEvent -> {
                                
                                    display.keyFrameSwap(visualArray, keyi, keyi - 1);
    
                                });
    
                                sortLoop.getKeyFrames().add(kf2);
                                frameCounter++;

                                index--;
                            }

                            notInOrder = ArrayManager.checkIfOrdered(sortArray);
                        }

                        break;
                    }
                    default:
                    {
                        System.out.println("Please select a sort!");
                    }
                }

                // Final sorted frame to clear highlighted bars
                KeyFrame kf = new KeyFrame(Duration.seconds(animationSpeed+(frameCounter*animationSpeed)), actionEvent -> {
            
                    display.drawArray(visualArray);
                
                });

                sortLoop.getKeyFrames().add(kf);
                frameCounter++;
                
                sortLoop.play();       
            }

        });
    }

    public static void main(String[] args)
    { 
        launch(args);
    }
}