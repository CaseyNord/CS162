public class Model extends java.util.Observable // <-- what is Observable?
{	
    private static int fahrenheit1 = 59;
    private static int fahrenheit2 = 72;
    private static int celsius1 = 15;
    private static int celsius2 = 22;


    public static int getFahrenheit1()
    {
        return fahrenheit1;
	}
	
    public static int getFahrenheit2()
    {
        return fahrenheit2;
    }
    
    public static int getCelsius1()
    {
        return celsius1;
    }

    public static int getCelsius2()
    {
        return celsius2;
    }
}