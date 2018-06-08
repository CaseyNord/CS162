//*****************************************************************
// Name: Casey Nord
// Class: CS162 Spring 2018
// Class Time: Mon/Wed/Fri 10:00am
// Date: June 12, 2018
// Final Project
// Driver Name: Main.java
// Program Description: Animated sorting program
//*****************************************************************

import java.util.Random;

public class ArrayManager
{
    public static int[] createTestArray()
    {
        int[] testArray = {32,28,22,20,16,13,10,9,5,3};

        return testArray;
    }

    public static int[] createRandomArray(int arraySize, int maxHeight)
    {
        int[] newArray = new int[arraySize];

        Random rand = new Random();
        for (int i = 0; i < arraySize; i++)
        {
            newArray[i] = rand.nextInt((maxHeight) + 1);
        }

        return newArray;
    }

    public static int[] copyArray(int[] array)
    {
        int[] arrayCopy = new int[array.length];
        
        for (int i = 0; i < array.length; i++)
        {
            arrayCopy[i] = array[i];
        }
        
        return arrayCopy;
    }

    public static boolean checkIfOrdered(int[] array)
    {
        for (int i = 0; i < array.length - 1; i++)
        {
            if (array[i] > array [i + 1])
            {
                return true;
            }
        }

        return false;
    }
}