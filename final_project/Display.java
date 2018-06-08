import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Display extends Canvas
{
    final int SCALE = 16;

    final int WIDTH = 64;
    final int HEIGHT = 32;

    private GraphicsContext gc;

    public int[][] pixels = new int[WIDTH][HEIGHT];;


    public Display()
    {
        super(800, 400);
        setFocusTraversable(true);

        /*

        setFocusTraversable allow the Display to be the focus of the computer's
        input so that it can respond to things like keyboard input

        */

        // Initialize with empty black screen
        clear();
        render();
    }

    public void clearCanvas()
    {
        for (int i = 0; i < WIDTH; i++)
        {
            for (int j = 0; j < HEIGHT; j++)
            {
                pixels[i][j] = 0;
            }

            /*

            This method will run through the entire array and assign a 0 to
            each value.  This will subsequently cause all pixels to be drawn
            as black rectangles on a renderCanvas() call, "clearing" the screen.

            */
        }
    }

    public void renderCanvas()
    {
        for (int i = 0; i < WIDTH; i++)
        {
            for (int j = 0; j < HEIGHT; j++)
            {
                // White pixels are drawn for array values of 1
                if (pixels[i][j] == 1)
                {
                    gc.setFill(Color.WHITE);
                }
                // Black pixels are drawn for array values of 0
                else
                {
                    gc.setFill(Color.BLACK);
                }

                gc.fillRect((i*SCALE), (j*SCALE), SCALE, SCALE);

                /*

                As the for loop executes it will decide what color pixel to draw
                based on the value held in the array, 0 or 1.

                The order of operations is as follows:

                1: check array value
                2: Assign fill color
                3: draw rectangle at specified position on canvas

                */
            }
        }
    }

    public int getPixel(int x, int y)
    {
        return pixels[x][y];
    }

    public void setPixel(int x, int y)
    {
        pixels[x][y] ^= 1;
    }
}

    /*

    Getters and setters are very standard methods in object oriented programming.

    The idea is to keep the variables themselves private so that they can't be
    undesirably manipulated.

    In this case I want to allow the appropriate opcodes of my emulator to access
    the canvas array so values can be assigned to draw on the screen.

    This is accomplished through the public methods above.

    Because I am working with a two-dimensional array, each method takes a
    pair of values (x, y coordinates) to get or set a "pixel" in the array.

    Because the opcodes of the emulator manipulate data on a bitwise level, the
    setPixel() method uses the ^= XOR operator to set the values 1 or 0 of the pixel
    array.

    This can be outlined in the following table:

    1 XOR 1 = 0
    1 XOR 0 = 1
    0 XOR 1 = 1
    0 XOR 0 = 0

    What this means for the display canvas implemented here is that calling
    setPixel() will cause the value in the array to swap from 0 to 1, or
    1 to 0 respectively.

    Setting a pixel will always make it the opposite color (value) that
    it is currently set at.

    */