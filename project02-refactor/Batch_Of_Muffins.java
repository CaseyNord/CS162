// ***************************************************************************
// Name: Sophia Kaeufel
// Class: CS162 Spring 2018
// Class Time: 10:00am
// Date: 4/9/2018
// Project #: Project 1
// Driver Name: Batch_Of_Muffins
// Program describtion: using a menu to get user input to fill the attributes and variables of a muffin
// *********************************************************************************************

public class Batch_Of_Muffins
{
	private int Amount;
	private String Topping;
	private String Size;

	public Batch_Of_Muffins(int Amt, String Topp, String size)
	{
		Amount = Amt;
		Topping = Topp;
		Size = size;
	}

	public String toString()
	{	String Result;
		Result = ("You choose to bake " + Amount + " " + Size + " muffins, with the topping " + Topping);
		return Result;
	}

	public int Temperature(String size)
	{
		int temp;

		if (size.equals("small"))
		{	temp = 350;
		}
		else if (size.equals("medium"))
		{ 	temp = 380;
		}
			else
				temp = 410;

		return temp;
	}

	public int Amount_Topping(int num)
	{
		int NumOfToppings = num * 4;

		return NumOfToppings;
	}

}

