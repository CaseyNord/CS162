import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Display extends Application
{
    final int SCALE = 16;

    final int WIDTH = 64;
    final int HEIGHT = 32;

    int[][] pixels;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        pixels = new int[WIDTH][HEIGHT];

        // 0 out pixel array
        for (int i = 0; i < WIDTH; i++)
        {
            for (int j = 0; j < HEIGHT; j++)
            {
                pixels[i][j] = 0;
            }
        }

        // Test fill array with some white pixels
        for (int i = 0; i < WIDTH; i+=2)
        {
            for (int j = 0; j < HEIGHT; j++)
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