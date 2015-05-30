/*Write a class called "Scores".  Its main method should use a Scanner to input the name and 5 quiz scores for each of 10 different people.  
   Store the names in a 1D-array, and the scores in a 2D-array.  Then print each name and the average score of the student.  */

import java.util.Scanner;

public class Scores {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/*--------------------------Array-Memory Allocation----------------------------*/

		String name[] = new String[10];
		Double allanswers[][] = new Double[10][5];
		Double sum = 0.0;
		
		/*-------------------------- Creating Scanner----------------------------------*/
		
		Scanner input = new Scanner(System.in);
		
		/*---------------Input Data and Calculation for Average Score-----------------*/
		
		for(int i=0;i<10;i++){
			
			System.out.println(" Enter name  " + i + " : ");
			
			name[i] = input.next();
		
			System.out.println(" Enter 5 scores ");
			for(int j=0; j<5; j++){
				
				allanswers[i][j] = input.nextDouble();
				
				sum = sum + allanswers[i][j];
			}
				System.out.println("Average score of " + name[i] + " is : " + (sum/5));
			
			int j = 0;
			sum = 0.0;
		}		 
		
	}

}
