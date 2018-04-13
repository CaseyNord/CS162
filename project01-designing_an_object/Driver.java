//*****************************************************************
// Name: Casey Nord
// Class: CS162 Spring 2018
// Class Time: Mon/Wed/Fri 10:00am
// Date: April 09, 2018
// Project #1
// Driver Name: Driver.java
// Program Description: Driver program for Project #1
// Test Oracle: n/a
//*****************************************************************

/*
Tests for this program mostly revolve around input validation.

Tests on the Bag class were needed to ensure that the open/close bag methods
worked correctly and that the correct output would be displayed depending
on the current condition of the bag.

I also had to test through some different approaches to designing a menu.
You can see their artifacts commented out below.

I ended up cheating my way through a clearConsole() method because from
what I've learned through research, it doesn't seem like there is a
recommended way to clear the console (nor does it seem practical in
languages like Java).
*/

import java.util.Scanner;

public class Driver
{
    public static void main(String[] args)
    {
        Bag myBag = new Bag();
        Scanner scan = new Scanner(System.in);

        //String returnToMenu = "Y";
        //while (returnToMenu.matches("[Yy]"))
        clearConsole();
        Boolean menuLoop = true;
        while (menuLoop)
        {   
            System.out.println("Please select from the following options:\n");
            System.out.println("1: Open bag");
            System.out.println("2: Close bag");
            System.out.println("3: Add a marble");
            System.out.println("4: Remove a marble");
            System.out.println("5: Display bag information");
            System.out.println("0: Exit\n");

            String input = scan.next();
            while (!(input.matches("[012345]")))
            {
                System.out.println("That is not a valid input.");
                input = scan.next();
            }
            int selection = Integer.parseInt(input);

            clearConsole();

            switch (selection)
            {
                case 0:
                    System.out.println("Goodbye!\n");
                    System.exit(0);
                
                case 1:
                    myBag.openBag();
                    break;

                case 2:
                    myBag.closeBag();
                    break;

                case 3:
                    myBag.addMarble(1);
                    break;

                case 4:
                    myBag.removeMarble(0);
                    break;

                case 5:
                    System.out.println(myBag);
                    break;
            }
            
            /*
            System.out.println("\nPress enter to continue...");
            scan.nextLine();
            */

            /*
            System.out.println("Return to menu? (Y/N)");
            returnToMenu = scan.next();
            while (!(returnToMenu.matches("[YyNn]")))
            {
                System.out.println("That is not a valid input. Enter (Y/N).");
                returnToMenu = scan.next();
            }
            */
        }
    }

    static void clearConsole()
    {
        // No good way to clear the console in Java?
        for (int i = 0; i < 1000; i++)
            System.out.println();
    }
}   