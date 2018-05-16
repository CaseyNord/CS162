import java.util.Random;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/*

Use AnimationTimer & KeyFrames to animate sort
Change colors of selected bars while sorting

*/

public class Project06v1 extends Application
{
    final int SCALE = 16;

    final int WIDTH = 64;
    final int HEIGHT = 32;

    int[][] pixels;
    int[] myArray;

    // Test array
    //int[] myArray = {1, 6, 26, 21, 27, 16, 22, 30};

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        pixels = new int[WIDTH][HEIGHT];

        // WIDTH based array
        myArray = new int[WIDTH];

        Random rand = new Random();
        for (int i = 0; i < WIDTH; i++)
        {
            myArray[i] = rand.nextInt((HEIGHT) + 1);
        }

        /*
        // Sort array
        for (int i = 0; i < myArray.length - 1; i++)
        {
            if (myArray[i] > myArray[i + 1])
            {
                int swap = myArray[i];
                myArray[i] = myArray[i + 1];
                myArray[i + 1] = swap;
                i = -1;
            }
        }
        */

        // 0 out pixel array
        for (int i = 0; i < WIDTH; i++)
        {
            for (int j = 0; j < HEIGHT; j++)
            {
                pixels[i][j] = 0;
            }
        }

        /*
        // Test fill array with some white pixels
        for (int i = 0; i < WIDTH; i+=2)
        {
            for (int j = 0; j < HEIGHT; j++)
            {
                pixels[i][j] = 1;
            }3
        }
        */
       
        // Draw array with vertical bars
        for (int i = 0; i < myArray.length; i++)
        {
            //for (int j = 0; j < myArray[i]; j++)
            for (int j = (HEIGHT - 1); j > ((HEIGHT - myArray[i]) - 1); j--)
            {
                pixels[i][j] = 1;   
            }
        }
        
        
        Canvas display = new Canvas (WIDTH*SCALE, HEIGHT*SCALE); // Build canvas

        GraphicsContext gc = display.getGraphicsContext2D(); // Draw commands are issued through this graphics context
        gc.fillRect(0, 0, WIDTH*SCALE, HEIGHT*SCALE);

        for (int i = 0; i < WIDTH; i++)
        {
            for (int j = 0; j < HEIGHT; j++)
            {
                if (pixels[i][j] == 1)
                {
                    gc.setFill(Color.WHITE);
                    gc.fillRect((i*SCALE), (j*SCALE), SCALE, SCALE);
                }
                else
                {
                    gc.setFill(Color.BLACK);
                    gc.fillRect((i*SCALE), (j*SCALE), SCALE, SCALE);
                }
            }
        }

        GridPane grid = new GridPane();
        grid.add(display, 0, 0);

        primaryStage.setTitle("Display");
        primaryStage.setScene(new Scene(grid, WIDTH*SCALE, HEIGHT*SCALE));
        primaryStage.show();
    }
    

    public static void main(String[] args)
    { 
        launch(args);
    }

}