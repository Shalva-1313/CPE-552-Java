/******************************************************************************

                            Online Java Compiler.
                Code, Compile, Run and Debug java program online.
Write your code in this editor and press "Run" button to execute it.

*******************************************************************************/
import java.util.Scanner;

public class Main
{
	public static void main(String[] args) 
	{
	    int bottom = 0;
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter odd integer in the range 1 - 15: ");
		int top = scan.nextInt();
		boolean validTop = isOddInt(top);
		
		while(validTop == false)
		{
            System.out.println("Enter odd integer in the range 1 - 15: ");
		    top = scan.nextInt();
		    
		    if(top % 2 != 0 && top >= 1 && top <= 15)
		    {
		        validTop = true;
		    }
		}
		
		System.out.println("Enter odd integer (base 2> base1) in the range of 3 - 17: ");
		bottom = scan.nextInt();
		boolean validbottom = isBottomValid(bottom, top);
		while(validbottom == false)
		{
            System.out.println("Enter odd integer (base 2> base1) in the range of 3 - 17: ");
		    bottom = scan.nextInt();
		    
		    if(bottom % 2 != 0 && bottom >= 3 && bottom <= 17 && bottom > top)
		    {
		        validbottom = true;
		    }
		}
		
		System.out.println("Please enter a printable character to draw trapezoid: ");
		String trapImage = scan.next();
		
		drawTrap(top, bottom, trapImage);
	}
	
	public static boolean isOddInt(int num)
		    {
		        while(true)
		            {
		              if(num % 2 == 0 || num < 1 || num > 15)
		              {   
		                 return false;
		              }
		              else
		              {
		               	return true;   
		              }
		            }
            }
            
    public static boolean isBottomValid(int b, int a)
		    {
		        while(true)
		            {
		              if(b >= 3 && b <= 17 && b > a && b%2 != 0)
		              {   
		                 return true;
		              }
		              else
		              {
		               	return false;   
		              } 
		            }
            }
            
    public static void drawTrap(int a, int b, String image)
    {
        int height = (b - a) / 2;

        for (int i = a; i <= b; i += 2) 
        {
            for (int k = height; k > 0; k--) 
            {
                System.out.print(" ");
            }
            height--;
    
            for (int j = 0; j < i; j++) 
            {
                System.out.print(image);
            }
            System.out.println();
        }
    }
}