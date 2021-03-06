//*****************************************************************
// Name: Casey Nord
// Class: CS162 Spring 2018
// Class Time: Mon/Wed/Fri 10:00am
// Date: June 12, 2018
// Final Project
// Driver Name: Main.java
// Program Description: Animated sorting program
//*****************************************************************

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class SortScreen extends Display implements SortScreenInterface
{    
    int displayWidth;
    int displayHeight;
    int displayScale;

    public SortScreen(int width, int height, int scale)
    {
        super(width, height, scale);

        displayWidth = width;
        displayHeight = height;
        displayScale = scale;

        pixels = new int[displayWidth][displayHeight];

        gc = getGraphicsContext2D();

        // Fill canvas with black pixels to start with blank screen
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, displayWidth*displayScale, displayHeight*displayScale);

        // Call initial clear() function to zero out pixel array
        clear();
    }

    @Override
    public void clear()
    {
        // Clear screen by zeroing out pixel array
        for (int i = 0; i < displayWidth; i++)
        {
            for (int j = 0; j < displayHeight; j++)
            {
                pixels[i][j] = 0;
            }
        }
    }

    @Override
    public void render()
    {
        // Render canvas
        for (int k = 0; k < displayWidth; k++)
        {
            for (int j = 0; j < displayHeight; j++)
            {
                if (pixels[k][j] == 1)
                {
                    gc.setFill(Color.WHITE);
                    gc.fillRect((k*displayScale), (j*displayScale), displayScale, displayScale);
                }
                else if (pixels[k][j] == 2)
                {
                    gc.setFill(Color.ORANGE);
                    gc.fillRect((k*displayScale), (j*displayScale), displayScale, displayScale);
                }
                else
                {
                    gc.setFill(Color.BLACK);
                    gc.fillRect((k*displayScale), (j*displayScale), displayScale, displayScale);
                }
            }
        }
    }

    public void drawBars(int[] array)
    {
        for (int k = 0; k < array.length; k++)
        {
            for (int j = (displayHeight - 1); j > ((displayHeight - array[k]) - 1); j--)
            {
                setPixel(k, j, 1);
            }
        }
    }

    public void highlightSwap(int[] array, int index, int color)
    {
        for (int j = (displayHeight - 1); j > ((displayHeight - array[index]) - 1); j--)
        {
            setPixel(index, j, color);
        }
    }

    public void drawArray(int[] array)
    {
        clear();
        drawBars(array);
        render();
    }

    public void drawHighlightedArray(int[] array, int firstSwap, int secondSwap)
    {
        try
        {
            clear();
            drawBars(array);
            highlightSwap(array, firstSwap, 2);
            highlightSwap(array, secondSwap, 2);
            render();
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            System.out.println("Our program tried to highlight bars outside of the bounds of the array, but we know this, and it is okay :)\n");
        }
    }

    public void keyFrameSwap(int[] array, int swapOne, int swapTwo)
    {
        int keySwap = array[swapOne];
        array[swapOne] = array[swapTwo];
        array[swapTwo] = keySwap;
        drawHighlightedArray(array, swapOne, swapTwo);
    }
}