

import java.util.Scanner;


public class TestSquare {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
			/*------------------------Creating Objects------------------------*/
			Square sqrobj1, sqrobj2;
			int value;
			
			System.out.println(" Length value for Square_1 is '1' ");
			System.out.println(" Enter length value for Square_2 : ");
			
			Scanner input = new Scanner(System.in);
			
			value = input.nextInt();
			
			Square sqr2 = new Square(value);
			
			/*-----------------printing the area of each Square---------------*/
						
			sqrobj1 = new Square();
			System.out.println(" Area of Square_1 is : " + sqrobj1.getArea());
			sqrobj2 = new Square(value);
			System.out.println(" Area of Square_2 is : " + sqrobj2.getArea());


	}

}
