/* Write a class called "Square".  It should store the length of one side. It should have two 
 * constructors, one that has no parameters and sets the side length to one. The other
 *  constructor should take one parameter and set the side length to the parameter value. 
 *  The class should also have a method call "getArea" that returns the area of the square.

   Write a separate class called "TestSquare" that creates two squares, one with each 
   constructor, and prints the area of each. */

import java.util.Scanner;

public class Square {
	
private int length;
	
   /*--------------------------- Using Constructors -------------------------------------*/

	public Square()
	{
		length = 1;
	}


	public Square(int len)
	{
		length = len;
	}
	
	/*------------------------------Using Methods--------------------------------------*/
	
	public int getArea()
	{
	
		return length*length;
	} 
	
	public int getLen()
	{
		return length;		
		
	}
	
}
