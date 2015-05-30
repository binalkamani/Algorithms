
public class TestMyfour {

	public static void main(String args[]){
		
		/*----------------------------MyFour Object 1---------------------------------*/
		
		MyFour<String> MyFourobj1 = new MyFour<String>();
		MyFourobj1.setValue("string", "string", "string", "string");
		
		MyFourobj1.toString();
		
		System.out.println(MyFourobj1.allEqual());
		
		/*----------------------------MyFour Object 2---------------------------------*/

		
		MyFour<Integer> MyFourobj2 = new MyFour<Integer>();
		MyFourobj2.setValue(123, 456, 123, 456);
		
		MyFourobj2.toString();
		
		System.out.println(MyFourobj2.allEqual());
		
		
		MyFourobj2.shiftLeft();
		MyFourobj2.toString();

	    }
}
