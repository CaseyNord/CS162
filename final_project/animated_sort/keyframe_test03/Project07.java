import com.sun.java_cup.internal.runtime.virtual_parse_stack;

import javafx.animation.KeyFrame;

import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import javafx.scene.control.Button;
import javafx.geometry.HPos;


public class Project07 extends Application
{
    final int SCALE = 16;

    final int WIDTH = 50;
    final int HEIGHT = 25;

    Timeline sortLoop;

    private SortScreen display;

    int frameCounter = 0; // Keeps tracks of frames generated (so we don't try to write over them when resetting the KeyFrame loop index to 0)
    int loopIndex = 100;
    int sortIdentifier = 0;

    int keyi;
    int keyj;
    int keyhigh;

    int i;
    int j;
    int pivot;
    int high;
    int low;
    int top;
    int[] stack;
    int temp;
    int p;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        display = new SortScreen(WIDTH, HEIGHT, SCALE);

        int[] myArray = MyArray.createRandomArray(WIDTH, HEIGHT);
        
        Button sortButton = new Button("Start Sort!");
        Button bubbleSortButton = new Button("Bubble Sort");
        Button quickSortButton = new Button("Quick Sort");
        Button mergeSortButton = new Button("Merge Sort");
        Button heapSortButton = new Button("Heap Sort");

        GridPane buttonGrid = new GridPane();
        buttonGrid.add(bubbleSortButton, 0, 0);
        buttonGrid.add(quickSortButton, 0, 1);
        buttonGrid.add(mergeSortButton, 0, 2);
        buttonGrid.add(heapSortButton, 0, 3);

        GridPane grid = new GridPane();
        grid.add(display, 0, 0);
        grid.add(buttonGrid, 1, 0);
        grid.add(sortButton, 0, 1);
        GridPane.setHalignment(sortButton, HPos.CENTER);

        primaryStage.setTitle("Sort");
        primaryStage.setScene(new Scene(grid, WIDTH*SCALE+100, HEIGHT*SCALE+31));
        primaryStage.show();

        sortLoop = new Timeline();

        // Shows initial unsorted array prior to sorting
        display.clear();
        display.drawBars(myArray);
        display.render();

        bubbleSortButton.setOnAction(new EventHandler<ActionEvent>()
        {            
            @Override
            public void handle(ActionEvent event)
            {
                sortIdentifier = 1;
            }
        });

        quickSortButton.setOnAction(new EventHandler<ActionEvent>()
        {            
            @Override
            public void handle(ActionEvent event)
            {
                sortIdentifier = 2;
            }
        });

        mergeSortButton.setOnAction(new EventHandler<ActionEvent>()
        {            
            @Override
            public void handle(ActionEvent event)
            {
                sortIdentifier = 3;
            }
        });

        heapSortButton.setOnAction(new EventHandler<ActionEvent>()
        {            
            @Override
            public void handle(ActionEvent event)
            {
                sortIdentifier = 4;
            }
        });

        sortButton.setOnAction(new EventHandler<ActionEvent>()
        {

            @Override
            public void handle(ActionEvent event)
            {

                switch(sortIdentifier)
                {
                    case 1:
                    {
                        int forIndex = myArray.length; // Provides a value to make an early exit as the sort is looped through, to avoid generating useless frames.
                        
                        // KeyFrame loop
                        for (int index = 0; index < forIndex; index++)
                        {        
                            int i = index;
        
                            KeyFrame kf = new KeyFrame(Duration.seconds(.01+(frameCounter*.01)), actionEvent -> {
                            //KeyFrame kf = new KeyFrame(Duration.seconds(1+frameCounter), actionEvent -> {
        
                                try
                                {
                                    display.clear();
                                    display.drawBars(myArray);
                                    display.highlightSwap(myArray, i, 2);
                                    display.highlightSwap(myArray, (i + 1), 2);
                                    display.render();
                                }
                                catch (ArrayIndexOutOfBoundsException e)
                                {
                                    System.out.println("Our program tried to highlight bars outside of the bounds of");
                                    System.out.println("the array, but we know this, and it is okay");
                                }
                            
                            });
        
                            sortLoop.getKeyFrames().add(kf);
                            frameCounter++;
        
                            KeyFrame kf2 = new KeyFrame(Duration.seconds(.01+(frameCounter*.01)), actionEvent -> {
                            //KeyFrame kf2 = new KeyFrame(Duration.seconds(1+frameCounter), actionEvent -> {
        
                                // Sort array -- uses KeyFrame loop for index
                                try
                                {
                                    if (myArray[i] > myArray[i + 1])
                                    {
                                        int swap = myArray[i];
                                        myArray[i] = myArray[i + 1];
                                        myArray[i + 1] = swap;
                                    }
                                }
                                catch (ArrayIndexOutOfBoundsException e)
                                {
                                    System.out.println("Our program tried to look at a value outside of the bounds of");
                                    System.out.println("the array, but we know this, and it is okay");
                                }
        
                                try
                                {
                                    display.clear();
                                    display.drawBars(myArray);
                                    display.highlightSwap(myArray, i, 2);
                                    display.highlightSwap(myArray, (i + 1), 2);
                                    display.render();
                                }
                                catch (ArrayIndexOutOfBoundsException e)
                                {
                                    System.out.println("Our program tried to highlight bars outside of the bounds of");
                                    System.out.println("the array, but we know this, and it is okay");
                                }
                            
                            });
        
                            sortLoop.getKeyFrames().add(kf2);
                            frameCounter++;
                        
                            if (loopIndex > 0)
                            {
                                if (index == forIndex - 1)
                                {
                                    index = -1;
                                    forIndex--;
                                }
                                loopIndex--;
                            }            
                        }
                        break;
                    }
                    case 2:
                    {
                        System.out.println("Quicksort");

                        low = 0;
                        high = myArray.length - 1;
                        
                        // Create an auxiliary stack
                        stack = new int[high-low+1];

                        // initialize top of stack
                        top = -1;
                    
                        // push initial values of l and h to stack
                        stack[++top] = low;
                        stack[++top] = high;
                        
                        // KeyFrame loop
                        // Keep popping from stack while is not empty

                        while (top >= 0)
                        {   
                            //System.out.println("I'm alive!");

                            KeyFrame kf = new KeyFrame(Duration.seconds(.01+(frameCounter*.01)), actionEvent -> {
                            //KeyFrame kf = new KeyFrame(Duration.seconds(1+frameCounter), actionEvent -> {
                            
                            // Pop h and l
                            high = stack[top--];
                            low = stack[top--];
                    
                            // Set pivot element at its correct position
                            // in sorted array
                            pivot = myArray[high];
                            
                            // index of smaller element
                            i = (low-1);

                            display.clear();
                            display.drawBars(myArray);
                            display.render();

                            });
        
                            sortLoop.getKeyFrames().add(kf);
                            frameCounter++;

                            for (j = low; j <= high-1; j++)
                            {
                                // If current element is smaller than or
                                // equal to pivot
                                if (myArray[j] <= pivot)
                                {
                                    KeyFrame kf2 = new KeyFrame(Duration.seconds(.01+(frameCounter*.01)), actionEvent -> {
                                    //KeyFrame kf = new KeyFrame(Duration.seconds(1+frameCounter), actionEvent -> {
                                    
                                    i++;

                                    // swap arr[i] and arr[j]
                                    int temp = myArray[i];
                                    myArray[i] = myArray[j];
                                    myArray[j] = temp;
                                
                                    display.clear();
                                    display.drawBars(myArray);
                                    display.render();
    
                                    });
    
                                    sortLoop.getKeyFrames().add(kf2);
                                    frameCounter++;
                                }
                            }       

                            KeyFrame kf3 = new KeyFrame(Duration.seconds(.01+(frameCounter*.01)), actionEvent -> {
                            //KeyFrame kf = new KeyFrame(Duration.seconds(1+frameCounter), actionEvent -> {

                            // swap arr[i+1] and arr[high] (or pivot)
                            temp = myArray[i+1];
                            myArray[i+1] = myArray[high];
                            myArray[high] = temp;
                            
                            display.clear();
                            display.drawBars(myArray);
                            display.render();

                            });
        
                            sortLoop.getKeyFrames().add(kf3);
                            frameCounter++;

                            p = i+1;

                            // If there are elements on left side of pivot,
                            // then push left side to stack
                            if (p-1 > low)
                            {
                                KeyFrame kf4 = new KeyFrame(Duration.seconds(.01+(frameCounter*.01)), actionEvent -> {
                                //KeyFrame kf = new KeyFrame(Duration.seconds(1+frameCounter), actionEvent -> {

                                stack[++top] = low;
                                stack[++top] = p - 1;

                                });

                                sortLoop.getKeyFrames().add(kf4);
                                frameCounter++;
                            }
                    
                            // If there are elements on right side of pivot,
                            // then push right side to stack
                            if (p+1 < high)
                            {
                                KeyFrame kf5 = new KeyFrame(Duration.seconds(.01+(frameCounter*.01)), actionEvent -> {
                                //KeyFrame kf = new KeyFrame(Duration.seconds(1+frameCounter), actionEvent -> {

                                stack[++top] = p + 1;
                                stack[++top] = high;

                                });

                                sortLoop.getKeyFrames().add(kf5);
                                frameCounter++;
                            }
                       
                            if (loopIndex > 0)
                            {
                                loopIndex--;
                            }
                            if (loopIndex <= 0)
                            {
                                break;
                            }  
                        }

                        for(int i = 0; i < myArray.length; i++)
                        {
                            System.out.println(myArray[i]);
                        }
                        break;
                    }
                    case 3:
                    {
                        System.out.println("Mergesort");
                        break;
                    }
                    case 4:
                    {
                        System.out.println("Heapsort");
                        break;
                    }
                    default:
                    {
                        System.out.println("Please select a sort!");
                    }
                }

                sortLoop.play();                
            }

        });
    }

    public static void main(String[] args)
    { 
        launch(args);
    }
}