import java.util.Random;

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


public class Project06v2 extends Application
{
    final int SCALE = 16;

    final int WIDTH = 64;
    final int HEIGHT = 32;

    int[][] pixels;

    Timeline sortLoop;
    
    //int[] myArray = {32,28,22,20,16,13,10,9,5,3};

    int frameCounter = 0; // Keeps tracks of frames generated (so we don't try to write over them when resetting the KeyFrame loop index to 0)
    // Doesn't complete
    //int forIndex = myArray.length - 1;
    // Throws exception on first pass
    //int forIndex = myArray.length; // Provides a value to make an early exit as the sort is looped through, to avoid generating useless frames.
    //int loopIndex = 55; // Poorly calculated total of the amount of frames that will need to be generated to complete the animation.
    int loopIndex = 10000;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        pixels = new int[WIDTH][HEIGHT];

        
        int[] myArray = new int[WIDTH];

        Random rand = new Random();
        for (int i = 0; i < WIDTH; i++)
        {
            myArray[i] = rand.nextInt((HEIGHT) + 1);
        }

        int forIndex = myArray.length;
        

        Canvas display = new Canvas (WIDTH*SCALE, HEIGHT*SCALE); // Build canvas

        GraphicsContext gc = display.getGraphicsContext2D(); // Draw commands are issued through this graphics context

        GridPane grid = new GridPane();
        grid.add(display, 0, 0);

        primaryStage.setTitle("Sort");
        primaryStage.setScene(new Scene(grid, WIDTH*SCALE, HEIGHT*SCALE));
        primaryStage.show();

        sortLoop = new Timeline();
        //sortLoop.setCycleCount(Timeline.INDEFINITE);

        // KeyFrame loop
        for (int index = 0; index < forIndex; index++)
        {        

            int i = index;

            KeyFrame kf = new KeyFrame(Duration.seconds(.003+(frameCounter*.003)), actionEvent -> {
            //KeyFrame kf = new KeyFrame(Duration.seconds(1+frameCounter), actionEvent -> {

                // Clear screen by zeroing out pixel array
                for (int k = 0; k < WIDTH; k++)
                {
                    for (int j = 0; j < HEIGHT; j++)
                    {
                        pixels[k][j] = 0;
                    }
                }

                // Draw array with vertical bars (assign values to canvas array)
                for (int k = 0; k < myArray.length; k++)
                {
                    for (int j = (HEIGHT - 1); j > ((HEIGHT - myArray[k]) - 1); j--)
                    {
                        pixels[k][j] = 1;
                    }
                    for (int j = (HEIGHT - 1); j > ((HEIGHT - myArray[i]) - 1); j--)
                    {
                        pixels[i][j] = 2;
                    }
                    for (int j = (HEIGHT - 1); j > ((HEIGHT - myArray[i + 1]) - 1); j--)
                    {
                        pixels[i + 1][j] = 2;
                    }
                }

                // Render canvas
                for (int k = 0; k < WIDTH; k++)
                {
                    for (int j = 0; j < HEIGHT; j++)
                    {
                        if (pixels[k][j] == 1)
                        {
                            gc.setFill(Color.WHITE);
                            gc.fillRect((k*SCALE), (j*SCALE), SCALE, SCALE);
                        }
                        else if (pixels[k][j] == 2)
                        {
                            gc.setFill(Color.ORANGE);
                            gc.fillRect((k*SCALE), (j*SCALE), SCALE, SCALE);
                        }
                        else
                        {
                            gc.setFill(Color.BLACK);
                            gc.fillRect((k*SCALE), (j*SCALE), SCALE, SCALE);
                        }
                    }
                }

                // Sort array -- uses KeyFrame loop for index
                if (myArray[i] > myArray[i + 1])
                {
                    int swap = myArray[i];
                    myArray[i] = myArray[i + 1];
                    myArray[i + 1] = swap;
                }
        
            });

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

            sortLoop.getKeyFrames().add(kf);

        }

        sortLoop.play();

    }

    public static void main(String[] args)
    { 
        launch(args);
    }
}


