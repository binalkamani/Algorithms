/* Write a class called "Frequency".  Its main method should use a Scanner to input an integer and a string.  The integer and string should be 
   evaluated to print a frequency range abbreviation according to this table:  http://www.qsl.net/w2va/freq.htm
   For example, if the user enters 100 KHz, your program should write LF. For inputs outside the ranges in the table, just print Unknown.
   Integers can be compared with the == operator, but strings are compared with the equals method.  For example, a == b, if a and b are integers,
   or a.equals(b), if a and b are strings. */

import java.util.Scanner;

public class Frequency {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/*-------------------------- Creating Scanner---------------------------------- */
		
		Scanner input = new Scanner(System.in); 
		
		System.out.println(" Enter Frequency Value without considering unit : "); 		
		double freq = input.nextDouble();
		
		System.out.println(" Enter unit : ");
		String frequnit = input.next();
		
		/*--------------------------Extracting character from String--------------------*/
		
		char unit = frequnit.charAt( 0 );
		
		/*---------------Logic for Identifying frequency Classification-----------------*/
		if(unit == 'H' | unit == 'h'){
						
			{
			if(freq>=30 && freq<300)
				System.out.println(" Classification : ELF ");
			else
				System.out.println(" Classification : VF ");
				
			}	
		}
			
		
		else if(unit == 'K' | unit == 'k'){
						
			{
			if(freq>=3 && freq<30)
				System.out.println(" Classification : VLF ");
			else if(freq>=30 && freq<300)
				System.out.println(" Classification : LF ");
			else
				System.out.println(" Classification : MF ");				
			}	
		}
			
		else if(unit == 'M' | unit == 'm'){
		
			{
			if(freq>=3 && freq<30)
				System.out.println(" Classification : HF ");
			else if(freq>=30 && freq<300)
				System.out.println(" Classification : VHF ");
			else
				System.out.println(" Classification : UHF ");				
			}	
		}
		
			else if(unit == 'G' | unit == 'g' | unit == 'T' | unit == 't'){
											
				{
				if(freq>=3 && freq<30)
					System.out.println(" Classification : SHF ");
				else if(freq>=30 && freq<300)
					System.out.println(" Classification : EHF ");
				else
					System.out.println(" Classification : - ");					
				}	
		}
			
			else
			{
				System.out.println(" Unknown ");
			}
			
	}
}
