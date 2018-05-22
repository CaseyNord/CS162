public class SortScreen extends Display
{
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