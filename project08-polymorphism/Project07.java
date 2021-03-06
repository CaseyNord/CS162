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

    final int WIDTH = 32;
    final int HEIGHT = 16;

    Timeline sortLoop;

    private SortScreen sortScreen;
    private Display display;

    int frameCounter = 0; // Keeps tracks of frames generated (so we don't try to write over them when resetting the KeyFrame loop index to 0)
    int loopIndex = 10000;


    @Override
    public void start(Stage primaryStage) throws Exception
    {
        sortScreen = new SortScreen(WIDTH, HEIGHT, SCALE);
        display = new Display(WIDTH, HEIGHT, SCALE);

        int[] myArray = MyArray.createRandomArray(WIDTH, HEIGHT);
        
        Button button = new Button("Start Sort!");

        GridPane grid = new GridPane();
        grid.add(sortScreen, 0, 0);
        grid.add(display, 1, 0);
        grid.add(button, 0, 1);
        GridPane.setHalignment(button, HPos.CENTER);

        primaryStage.setTitle("Sort");
        primaryStage.setScene(new Scene(grid, (WIDTH*SCALE)*2, HEIGHT*SCALE+31));
        primaryStage.show();

        sortLoop = new Timeline();

        // Shows initial unsorted array prior to sorting
        sortScreen.clear();
        sortScreen.drawBars(myArray);
        sortScreen.render();
        
        display.clear();
        for (int i = 0; i < WIDTH; i++)
        {
            for (int j = 0; j < HEIGHT; j++)
            {
                display.setPixel(i, j, sortScreen.getPixel(i, j));
            }
        }
        display.render();
    
        button.setOnAction(new EventHandler<ActionEvent>()
        {            
            @Override
            public void handle(ActionEvent event)
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
                            sortScreen.clear();
                            sortScreen.drawBars(myArray);
                            sortScreen.highlightSwap(myArray, i, 2);
                            sortScreen.highlightSwap(myArray, (i + 1), 2);
                            sortScreen.render();
                            
                            display.clear();
                            for (int j = 0; j < WIDTH; j++)
                            {
                                for (int k = 0; k < HEIGHT; k++)
                                {
                                    display.setPixel(j, k, sortScreen.getPixel(j, k));
                                }
                            }
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
                            sortScreen.clear();
                            sortScreen.drawBars(myArray);
                            sortScreen.highlightSwap(myArray, i, 2);
                            sortScreen.highlightSwap(myArray, (i + 1), 2);
                            sortScreen.render();

                            for (int j = 0; j < WIDTH; j++)
                            {
                                for (int k = 0; k < HEIGHT; k++)
                                {
                                    display.setPixel(j, k, sortScreen.getPixel(j, k));
                                }
                            }
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

                sortLoop.play();                
            }

        });
    }

    public static void main(String[] args)
    { 
        launch(args);
    }
}