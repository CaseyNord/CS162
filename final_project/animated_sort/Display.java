import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Display extends Canvas
{
    protected static final int WIDTH = 64;
    protected static final int HEIGHT = 32;

    protected static final int SCALE = 16;

    int[][] pixels;

    GraphicsContext gc;


    public Display()
    {
        // Initialize the inherited canvas using super()
        super(WIDTH*SCALE, HEIGHT*SCALE);

        pixels = new int[WIDTH][HEIGHT];

        gc = getGraphicsContext2D();

        // Fill canvas with black pixels to start with blank screen
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, WIDTH*SCALE, HEIGHT*SCALE);

        // Call initial clear() function to zero out pixel array
        clear();
    }

    public Display(int displayWidth, int displayHeight, int displayScale)
    {
        // Initialize the inherited canvas using super()
        super(displayWidth*displayScale, displayHeight*displayScale);

        pixels = new int[WIDTH][HEIGHT];

        gc = getGraphicsContext2D();

        // Fill canvas with black pixels to start with blank screen
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, WIDTH*SCALE, HEIGHT*SCALE);

        // Call initial clear() function to zero out pixel array
        clear();
    }

    public void clear()
    {
        // Clear screen by zeroing out pixel array
        for (int i = 0; i < WIDTH; i++)
        {
            for (int j = 0; j < HEIGHT; j++)
            {
                pixels[i][j] = 0;
            }
        }
    }

    public void render()
    {
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
    }

    public void setPixel(int x, int y, int value)
    {
        pixels[x][y] = value;
    }

    public int getPixel(int x, int y)
    {
        return pixels[x][y];
    }
}