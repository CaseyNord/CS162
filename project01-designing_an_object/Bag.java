//*****************************************************************
// Name: Casey Nord
// Class: CS162 Spring 2018
// Class Time: Mon/Wed/Fri 10:00am
// Date: April 09, 2018
// Project #1
// Driver Name: Driver.java
// Program Description: Object class for Project #1
// Test Oracle: n/a
//*****************************************************************
public class Bag
{
    final int MAX_SIZE = 10;
    int[] marbles;
    private int count;
    private Boolean isOpen;

    public Bag()
    {
        marbles = new int[MAX_SIZE];
        count = 0;
        isOpen = false;
    }

    public String toString()
    { 
         return "Holding: " + count + ", Can hold: " + MAX_SIZE + ", Is open: " + isOpen + "\n";
    }

    public void openBag()
    {
        isOpen = true;
        System.out.println("Bag is open.\n");
    }

    public void closeBag()
    {
        isOpen = false;
        System.out.println("Bag is closed.\n");
    }

    public void addMarble(int newMarble)
    {
        if (!(isOpen))
        {
            System.out.println("Bag is closed, cannot add marble.\n");
        }
        else if (count < MAX_SIZE)
        {
            marbles[count] = newMarble;
            count++;
            System.out.println("Added a marble to the bag.\n");
        }
        else
        {
            System.out.println("Bag is full!");
        }
    }

    public int removeMarble(int marbleIndex)
    {
        int marble = marbles[marbleIndex];
        if (!(isOpen))
        {
            System.out.println("Bag is closed, cannot remove marble.\n");
        }
        else if (marble == 0)
        {
            System.out.println("The bag is empty!");
        }
        else
        {
            marbles[marbleIndex] = 0;
            System.out.println("Removed a marble from the bag, looked at it, then");
            System.out.println("put it back in so it doesn't get lost. :)\n");
            marbles[marbleIndex] = 1;
        }
        return marble;
    }
}