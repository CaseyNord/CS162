import java.util.Random;

public class MyArray
{
    int[] myArray;

    public static int[] createTestArray()
    {
        int[] myArray = {32,28,22,20,16,13,10,9,5,3};

        return myArray;
    }

    public static int[] createRandomArray(int arraySize, int maxHeight)
    {
        int[] myArray = new int[arraySize];

        Random rand = new Random();
        for (int i = 0; i < arraySize; i++)
        {
            myArray[i] = rand.nextInt((maxHeight) + 1);
        }

        return myArray;
    }
}