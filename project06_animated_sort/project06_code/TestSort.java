
public class TestSort
{
    public static void main(String[] args) 
    {
       System.out.println("Hello, World\n");

       int[] myArray = {30, 27, 26, 21, 17, 8, 4, 0};

       for (int i = 0; i < myArray.length; i++)
       {
           System.out.print(myArray[i] + " ");
       }

       System.out.println();

       for (int i = 0; i < myArray.length - 1; i++)
       {
           if (myArray[i] > myArray[i + 1])
           {
               int swap = myArray[i];
               myArray[i] = myArray[i + 1];
               myArray[i + 1] = swap;
               i = -1;
           }

           for (int j = 0; j < myArray.length; j++)
           {
               System.out.print(myArray[j] + " ");
           }
    
           System.out.println();
       }

       for (int i = 0; i < myArray.length; i++)
       {
           System.out.print(myArray[i] + " ");
       }
    }
}