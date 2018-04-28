public class Model extends java.util.Observable { // <-- what is Observable?
	
    private int temp1;
    private int temp2;
    private int userTemp;

    public Model()
    {
        temp1 = 59;
        temp2 = 72;
        userTemp = 0;
	}

    public void setUserTemp(int value) 
    {
		userTemp = value;
	}
	
    public int getUserTemp()
    {
        return UserTemp;
	}

    public void setTemp1(int value) 
    {
		temp1 = value;
	}
	
    public int getTemp1()
    {
        return temp1;
	}
    
    public void setTemp2(int value) 
    {
		temp1 = value;
	}
	
    public int getTemp2()
    {
        return temp2;
	}
}