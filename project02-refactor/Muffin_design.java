// ***************************************************************************
// Name: Sophia Kaeufel
// Class: CS162 Spring 2018
// Class Time: 10:00am
// Date: 4/9/2018
// Project #: Project 1
// Driver Name: Muffin_design
// Program describtion: using a menu to get user input to fill the attributes and variables of a muffin
// Test Oracle: check postive and negative numbers when asking for user input, input a different string input than asked for, using upper and lower case letters.
//				check if you can quit the program. check if the loop brings you back to the menu.
// *********************************************************************************************
import java.util.Scanner;

public class Muffin_design
{
	public static void main(String[] args)
	{
		Scanner scan = new Scanner (System.in);

		char Ans = 'Y';
		while (Ans == 'Y' || Ans == 'y')
		{
			System.out.println("Menu: \t Press 1 to make a batch of muffins \n\t Press 2 to quit the program \n");
			int choice = scan.nextInt();
			if (choice == 1)
			{
				System.out.print("\nToday we are baking muffins! \n\n");
				System.out.println("How many muffins would you like to bake today? ");
				int Amt = scan.nextInt();
					while (Amt < 1)
					{	System.out.println("You entered a number out of range, please enter a positive number of muffins you want to bake ");
						System.out.println("How many muffins would you like to bake today? ");
						Amt = scan.nextInt();
					}

				System.out.println("Which of these toppings would you like? Rasberry, Blueberry, Chocolate-Chip");
				String Topp = scan.next();
				Topp = Topp.toLowerCase();
					while (!Topp.equals("rasberry") && !Topp.equals("blueberry") && !Topp.equals("chocolate-chip"))
					{	System.out.println("You entered a topping not available, please choose between Rasberry, Blueberry, and Chocolate-Chip");
						Topp = scan.next();
						Topp = Topp.toLowerCase();
					}

				System.out.println("What size do you want your muffins to be? Small, Medium, Large");
				String size = scan.next();
				size = size.toLowerCase();
					while (!size.equals("small") && !size.equals("medium") && !size.equals("large"))
					{	System.out.println("You entered a size not available, please choose between Small, Medium, and Large ");
						size = scan.next();
						size = size.toLowerCase();
					}

				Batch_Of_Muffins Batch_1 = new Batch_Of_Muffins (Amt, Topp, size);
				System.out.println("\n" + Batch_1 + "\n\n");

				System.out.println("******************************************************************************************************************");

				System.out.println("For " + Amt + " muffins, you need " + Batch_1.Amount_Topping(Amt) + " " + Topp +"s \n");
				System.out.println("You need to cook your batch of muffins at " + Batch_1.Temperature(size) + " fahrenheit.");

				System.out.println("******************************************************************************************************************");

				System.out.println("\n\nDo you want to go back to the menu? (Y/N) \n ");
				String Word = scan.next();
				Word = Word.toUpperCase();
				Ans = Word.charAt(0);
			}

			else if (choice != 1)
			{	System.exit(2);
			}
		}

	} // end main
} // end class