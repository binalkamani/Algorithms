/*Write a generic class called "MyFour".  It should use a type parameter of "T".It should have 
 * four fields, item1, item2, item3, item4, all of type T.  Its constructor receives values for
 * setting all four items. It should have a method "allEqual" that returns true if all four items are
    equal according to the "equals" method.  It should have a method called "shiftLeft" that 
    shifts all items up one position, and putting the first item's value into the last item.  
    It should have a "toString" method that prints the items in this format: 
    (item1, item2, item3, item4).

    Finally, add a "main" method.  First it should create a MyFour object of type String, 
    passing it four identical strings.  Print the object, then call and print the results of 
    "allEqual".  Next, create an object of four different Integers. Again print the object 
    and print the results of "allEqual".  After that, shift the items left using your method, 
    and print them again.*/

import java.util.Scanner;

public class MyFour<T> {

	private T item1;
	private T item2;
	private T item3;
	private T item4;
	private T itemnew;

	/*----------------------Set Method for four items-------------------------*/
	
	
	public void setValue(T tempitem1, T tempitem2, T tempitem3, T tempitem4)
	{
		item1 = tempitem1;
		item2 = tempitem2;
		item3 = tempitem3;
		item4 = tempitem4;
	}
	
	/*--------------------------Shift Left Method-----------------------------*/
	
	public void shiftLeft()
	{		
		itemnew = item1;
		item1 = item2;
		item2 = item3;
		item3 = item4;
		item4 = itemnew;
	}
	
	/*----------------------------allEqual Method-----------------------------*/
	
	public boolean allEqual()
	{
		if(item1.equals(item2) && item2.equals(item3) && item3.equals(item4))
			return true;
		else
			return false;
	}

	/*-----------------------Using toString Method----------------------------*/
	
	public  String toString()
	{
		System.out.println("item1 = " + item1 + "; item2 = " + item2 + "; item3 = " + item3 + "; item4 = " + item4 );
		return null;
	}

}
