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


public class Project06 extends Application
{
    final int SCALE = 16;

    final int WIDTH = 64;
    final int HEIGHT = 32;

    Timeline sortLoop;

    private Display display;

    int frameCounter = 0; // Keeps tracks of frames generated (so we don't try to write over them when resetting the KeyFrame loop index to 0)
    int loopIndex = 10000;


    @Override
    public void start(Stage primaryStage) throws Exception
    {
        display = new Display();

        int[] myArray = MyArray.createRandomArray(WIDTH, HEIGHT);

        int forIndex = myArray.length; // Provides a value to make an early exit as the sort is looped through, to avoid generating useless frames.
        
        GridPane grid = new GridPane();
        grid.add(display, 0, 0);

        primaryStage.setTitle("Sort");
        primaryStage.setScene(new Scene(grid, WIDTH*SCALE, HEIGHT*SCALE));
        primaryStage.show();

        sortLoop = new Timeline();

        // KeyFrame loop
        for (int index = 0; index < forIndex; index++)
        {        
            int i = index;

            KeyFrame kf = new KeyFrame(Duration.seconds(.003+(frameCounter*.003)), actionEvent -> {
            //KeyFrame kf = new KeyFrame(Duration.seconds(1+frameCounter), actionEvent -> {

                display.clear();

                // Assign color values to pixel array before rendering
                for (int k = 0; k < myArray.length; k++)
                {
                    for (int j = (HEIGHT - 1); j > ((HEIGHT - myArray[k]) - 1); j--)
                    {
                        display.setPixel(k, j, 1);
                    }
                    for (int j = (HEIGHT - 1); j > ((HEIGHT - myArray[i]) - 1); j--)
                    {
                        display.setPixel(i, j, 2);
                    }
                    for (int j = (HEIGHT - 1); j > ((HEIGHT - myArray[i + 1]) - 1); j--)
                    {
                        display.setPixel(i + 1, j, 2);
                    }
                }

                display.render();

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