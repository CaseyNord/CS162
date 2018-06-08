import java.util.ArrayList;

public class Sorts
{
    ArrayList<Integer> arrayList = new ArrayList<Integer>();

    public static int bubbleSort(int[] sortArray)
    {
        int count = 0;

        for (int i = 0; i < sortArray.length; i++)
        {
            if (sortArray[i] > sortArray[i + 1])
            {
                int swap = sortArray[i];
                sortArray[i] = sortArray[i + 1];
                sortArray[i + 1] = swap;
                count++;
            }
        }
        return count;
    }
}