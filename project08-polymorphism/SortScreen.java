public class SortScreen extends Display
{
    // I want to be able to create methods to color and draw my bars in this class

    // It will inherit the methods of the Display class
    // By doing this I will instantiate a Bars object instead of a Display object in Project07.java
    // This allows me to leave my Display class as a general class for creating a screen and also allow me
    // To remove by for loops from the Driver and write them as extensions of the Display, essentially making
    // a program specific Display designed to draw and color bars, and also catch out of bounds exceptions for
    // to handle any weirdness between sorting algorithms and for loops that assign color values to bars.

    // For exceptions it will catch and ignore color calls that are outside of the bounds of my arrays

    public void drawBars(int[] array)
    {
        for (int k = 0; k < array.length; k++)
        {
            for (int j = (HEIGHT - 1); j > ((HEIGHT - array[k]) - 1); j--)
            {
                setPixel(k, j, 1);
            }
        }
    }

    public void highlightSwap(int[] array, int index, int color)
    {
        for (int j = (HEIGHT - 1); j > ((HEIGHT - array[index]) - 1); j--)
        {
            setPixel(index, j, color);
        }
    }
}