/**********************************************************************************************************************/
/******************************       Project-4 Hopscotch Hashtable         *******************************************/
/******************************       Submitted By : Binal Kamani           *******************************************/
/******************************            net id : bxk131030               *******************************************/
/**********************************************************************************************************************/



// HopScotch Hash table class
//
// CONSTRUCTION: an approximate initial size or default of 101
//
// ******************PUBLIC OPERATIONS*********************
// bool insert( x )       --> Insert x
// bool contains( x )     --> Return true if x is present



/**
 * Probing table implementation of hash tables.
 * Note that all "matching" is based on the equals method.
 */
public class HopScotchHashTable<AnyType>
{
	//Declaring variables
	
	int n = 0;
	int m = 7;
	int[] hop_element = new int[100];
	int prevPos = 0;
	int takePos = 0;
	int tempPos = 0;
	int save_check = 0;
	int currentPos;
	int[] save_replace = new int[7];
	int[] hop = new int[100];
	int tempPos1;
	int check;
	AnyType value;
	HashEntry<AnyType> x;


	/**
     * Construct the hash table.
     */
    public HopScotchHashTable( )
    {
        this( DEFAULT_TABLE_SIZE );
    }

    /**
     * Construct the hash table.
     * @param size the approximate initial size.
     */
    public HopScotchHashTable( int size )
    {
        allocateArray( size );

    }
    /**
     * Insert into the hash table. 
     * @param x the item to insert.
     */
    
    public boolean insert( AnyType x )
    {
    	
    	int currentPos = findPos(x);
    	int check_diff = (currentPos - myhash(x));
    	System.out.println("currentpos - myhash(x) is : " + check_diff);
    	
    		
    	while(check_diff > 7)
    	{
    		System.out.println("Greater than 7");
    		currentPos = findPos(x);
    		check_diff = (currentPos - myhash(x));
    		
    	}
    	
    	if(check_diff < 8){
    	array[ currentPos ] = new HashEntry<>(x,true);
    	System.out.println("Show entry : " + array[currentPos].element);
    	System.out.println("Currently inserted at : " + currentPos);
    	System.out.println("Hop value of the x is " + Integer.toBinaryString(hop[currentPos]));
    	theSize++;
    	}
    	return true;
    }
 private int findPos( AnyType x )
    {
    	n = 0;	//counter for checking hop of 8
        int currentPos = myhash( x );	
        System.out.println("*******************************************");
        System.out.println("Element is hashed to location " + myhash( x ));	
		boolean flag = false;
        tempPos = currentPos; // variable for tracking position
        takePos = currentPos;	//variable for tracking position
        int returnPos = currentPos;
        
        
        // when an element is inserted on the same location it was assigned to be inserted for first time
		// then assigning hop binary value of 1000 0000
        if(array[tempPos] == null){
        	hop[tempPos] = 128; 
        	returnPos = tempPos; 
        
        }
        
        while(array[tempPos] != null){

			tempPos++;
			
			//Finds null position in Hash Table
			if(array[tempPos] == null){
				System.out.println("null position is : " + tempPos);

			// if there is any null space in hop distance of 8 then return that position
			if(tempPos - currentPos < 8) 
			{
			
				System.out.println("null position is in hop distance of 8");
				int diff = 7-(tempPos - currentPos);
				int power = 2^diff;
				hop[currentPos] = hop[currentPos] | power ;
				System.out.println("Modified hop value is : " + hop[currentPos]);
				hop[tempPos] = 0;
				System.out.println("Position taken");
				returnPos = tempPos;

			}
			
			// if there is not any null space in hop distance of 8 then find element to be slide down in any empty space
			if(tempPos - currentPos > 8)
			{
				//System.out.println("null position is out of hop distance 8");
				int newPos = tempPos - 7 ;
				

				// if there is some 8 bit binary value associated with element out of hop of 8 then check it to get idea about
				// which element can be moved down and accordingly change hop values
				
				if((hop[newPos] > 1) && flag == false)
				{
					//System.out.println("Let me know element on this newPos " + array[newPos].element);
					System.out.println("checking.....");
					//System.out.println(hop[newPos]);
					hop[tempPos] = 0;
					
					if(hop[newPos]>127 && hop[newPos]<256)
					{
						
						array[tempPos] = array[newPos]; // slide it down in empty space
						//change hop values here
						hop[newPos] = hop[newPos] + 1 ; // or with 0000 0001
						hop[newPos] = hop[newPos] - 128 ; // and with 0111 1111
						System.out.println("Check with this return Position value : " + returnPos );						
						returnPos = newPos;
						break;
					}
					if(hop[newPos]>63 && hop[newPos]<128)
					{
						
						array[tempPos] = array[newPos+1]; // slide it down in empty space
						//change hop values here
						hop[newPos] = hop[newPos] + 1 ; // or with 0000 0001
						hop[newPos] = hop[newPos] - 64 ; // and with 1011 1111
						
						
						returnPos = newPos+1;
						break;
					}

					if(hop[newPos]>31 && hop[newPos]<64)
					{
						
						array[tempPos] = array[newPos+2];
						hop[newPos] = hop[newPos] + 1 ; // or with 0000 0001
						hop[newPos] = hop[newPos] - 32 ; // and with 1101 1111
						
						returnPos = newPos+2 ;
						break;
					}

					if(hop[newPos]>15 && hop[newPos]<32)
					{
						array[tempPos] = array[newPos+3];
						hop[newPos] = hop[newPos] + 1 ; // or with 0000 0001
						hop[newPos] = hop[newPos] - 16 ; // and with 1110 1111
						
						returnPos = newPos+3 ;
						break;
					}

					if(hop[newPos]>7 && hop[newPos]<16)
					{
						
						array[tempPos] = array[newPos+4];
						hop[newPos] = hop[newPos] + 1 ; // or with 0000 0001
						hop[newPos] = hop[newPos] - 8 ; // and with 1111 0111
						
						returnPos = newPos+4 ;
						break;
					}
					if(hop[newPos]>3 && hop[newPos]<8)
					{
						
						array[tempPos] = array[newPos+5];
						hop[newPos] = hop[newPos] + 1 ; // or with 0000 0001
						hop[newPos] = hop[newPos] - 4 ; // and with 1111 1011
						
						returnPos = newPos+5 ;
						break;
					}
					if(hop[newPos]>1 && hop[newPos]<4)
					{
						array[tempPos] = array[newPos+6];
						hop[newPos] = hop[newPos] + 1 ; // or with 0000 0001
						hop[newPos] = hop[newPos] - 2 ; // and with 1111 1101
						
						returnPos = newPos+6 ;
						break;
					}
					flag = true;
				}
				
				if((hop[newPos+1] > 0) && flag == false)
				{
					hop[tempPos] = 0;
					newPos = newPos + 1 ;
					
					if(hop[newPos]>127 && hop[newPos]<256)
					{
						
						array[tempPos] = array[newPos]; // slide it down in empty space
						//change hop values here
						hop[newPos] = hop[newPos] + 1 ; // or with 0000 0001
						hop[newPos] = hop[newPos] - 128 ; // and with 0111 1111
												
						returnPos = newPos;
						break;
					}
					if(hop[newPos]>63 && hop[newPos]<128)
					{
						
						array[tempPos] = array[newPos+1]; // slide it down in empty space
						//change hop values here
						hop[newPos] = hop[newPos] + 1 ; // or with 0000 0001
						hop[newPos] = hop[newPos] - 64 ; // and with 1011 1111
						
						
						returnPos = newPos+1;
						break;
					}

					if(hop[newPos]>31 && hop[newPos]<64)
					{
						array[tempPos] = array[newPos+2];
						hop[newPos] = hop[newPos] + 1 ; // or with 0000 0001
						hop[newPos] = hop[newPos] - 32 ; // and with 1101 1111
						
						returnPos = newPos+2 ;
						break;
					}

					if(hop[newPos]>15 && hop[newPos]<32)
					{
						array[tempPos] = array[newPos+3];
						hop[newPos] = hop[newPos] + 1 ; // or with 0000 0001
						hop[newPos] = hop[newPos] - 16 ; // and with 1110 1111
						
						returnPos = newPos+3 ;
						break;
					}

					if(hop[newPos]>7 && hop[newPos]<16)
					{
						array[tempPos] = array[newPos+4];
						hop[newPos] = hop[newPos] + 1 ; // or with 0000 0001
						hop[newPos] = hop[newPos] - 8 ; // and with 1111 0111
						
						returnPos = newPos+4 ;
						break;
					}
					if(hop[newPos]>3 && hop[newPos]<8)
					{
						array[tempPos] = array[newPos+5];
						hop[newPos] = hop[newPos] + 1 ; // or with 0000 0001
						hop[newPos] = hop[newPos] - 4 ; // and with 1111 1011
						
						returnPos = newPos+5 ;
						break;
					}
					flag = true;
				}
				if((hop[newPos+2] > 0) && flag == false)
				{
					hop[tempPos] = 0;
					newPos = newPos + 2 ;
					
					if(hop[newPos]>127 && hop[newPos]<256)
					{
						
						array[tempPos] = array[newPos]; // slide it down in empty space
						//change hop values here
						hop[newPos] = hop[newPos] + 1 ; // or with 0000 0001
						hop[newPos] = hop[newPos] - 128 ; // and with 0111 1111
												
						returnPos = newPos;
						break;
					}
					if(hop[newPos]>63 && hop[newPos]<128)
					{
						
						array[tempPos] = array[newPos+1]; // slide it down in empty space
						//change hop values here
						hop[newPos] = hop[newPos] + 1 ; // or with 0000 0001
						hop[newPos] = hop[newPos] - 64 ; // and with 1011 1111
						
						
						returnPos = newPos+1;
						break;
					}

					if(hop[newPos]>31 && hop[newPos]<64)
					{
						array[tempPos] = array[newPos+2];
						hop[newPos] = hop[newPos] + 1 ; // or with 0000 0001
						hop[newPos] = hop[newPos] - 32 ; // and with 1101 1111
						
						returnPos = newPos+2 ;
						break;
					}

					if(hop[newPos]>15 && hop[newPos]<32)
					{
						array[tempPos] = array[newPos+3];
						hop[newPos] = hop[newPos] + 1 ; // or with 0000 0001
						hop[newPos] = hop[newPos] - 16 ; // and with 1110 1111
						
						returnPos = newPos+3 ;
						break;
					}

					if(hop[newPos]>7 && hop[newPos]<16)
					{
						array[tempPos] = array[newPos+4];
						hop[newPos] = hop[newPos] + 1 ; // or with 0000 0001
						hop[newPos] = hop[newPos] - 8 ; // and with 1111 0111
						
						returnPos = newPos+4 ;
						break;
					}
					
					flag = true;
				}
				if((hop[newPos+3] > 0) && flag == false)
				{
					hop[tempPos] = 0;
					newPos = newPos + 3 ;
					
					if(hop[newPos]>127 && hop[newPos]<256)
					{
						
						array[tempPos] = array[newPos]; // slide it down in empty space
						//change hop values here
						hop[newPos] = hop[newPos] + 1 ; // or with 0000 0001
						hop[newPos] = hop[newPos] - 128 ; // and with 0111 1111
												
						returnPos = newPos;
						break;
					}
					if(hop[newPos]>63 && hop[newPos]<128)
					{
						
						array[tempPos] = array[newPos+1]; // slide it down in empty space
						//change hop values here
						hop[newPos] = hop[newPos] + 1 ; // or with 0000 0001
						hop[newPos] = hop[newPos] - 64 ; // and with 1011 1111
						
						
						returnPos = newPos+1;
						break;
					}

					if(hop[newPos]>31 && hop[newPos]<64)
					{
						array[tempPos] = array[newPos+2];
						hop[newPos] = hop[newPos] + 1 ; // or with 0000 0001
						hop[newPos] = hop[newPos] - 32 ; // and with 1101 1111
						
						returnPos = newPos+2 ;
						break;
					}

					if(hop[newPos]>15 && hop[newPos]<32)
					{
						array[tempPos] = array[newPos+3];
						hop[newPos] = hop[newPos] + 1 ; // or with 0000 0001
						hop[newPos] = hop[newPos] - 16 ; // and with 1110 1111
						
						returnPos = newPos+3 ;
						break;
					}
					
					flag = true;
					
				}
				if((hop[newPos+4] > 0) && flag == false)
				{
					hop[tempPos] = 0;
					newPos = newPos + 4 ;
					
					if(hop[newPos]>127 && hop[newPos]<256)
					{
						
						array[tempPos] = array[newPos]; // slide it down in empty space
						//change hop values here
						hop[newPos] = hop[newPos] + 1 ; // or with 0000 0001
						hop[newPos] = hop[newPos] - 128 ; // and with 0111 1111
												
						returnPos = newPos;
						break;
					}
					if(hop[newPos]>63 && hop[newPos]<128)
					{
						
						array[tempPos] = array[newPos+1]; // slide it down in empty space
						//change hop values here
						hop[newPos] = hop[newPos] + 1 ; // or with 0000 0001
						hop[newPos] = hop[newPos] - 64 ; // and with 1011 1111
						
						
						returnPos = newPos+1;
						break;
					}

					if(hop[newPos]>31 && hop[newPos]<64)
					{
						array[tempPos] = array[newPos+2];
						hop[newPos] = hop[newPos] + 1 ; // or with 0000 0001
						hop[newPos] = hop[newPos] - 32 ; // and with 1101 1111
						
						returnPos = newPos+2 ;
						break;
					}

					flag = true;
					
				}
				if((hop[newPos+5] > 0) && flag == false)
				{
					hop[tempPos] = 0;
					newPos = newPos + 5 ;
					
					if(hop[newPos]>127 && hop[newPos]<256)
					{
						
						array[tempPos] = array[newPos]; // slide it down in empty space
						//change hop values here
						hop[newPos] = hop[newPos] + 1 ; // or with 0000 0001
						hop[newPos] = hop[newPos] - 128 ; // and with 0111 1111
												
						returnPos = newPos;
						break;
					}
					if(hop[newPos]>63 && hop[newPos]<128)
					{
						
						array[tempPos] = array[newPos+1]; // slide it down in empty space
						//change hop values here
						hop[newPos] = hop[newPos] + 1 ; // or with 0000 0001
						hop[newPos] = hop[newPos] - 64 ; // and with 1011 1111
						
						
						returnPos = newPos+1;
						break;
					}


					flag = true;
					
				}
				if((hop[newPos+6] > 0) && flag == false)
				{
					hop[tempPos] = 0;
					newPos = newPos + 6 ;
					
					if(hop[newPos]>127 && hop[newPos]<256)
					{
						
						array[tempPos] = array[newPos]; // slide it down in empty space
						//change hop values here
						hop[newPos] = hop[newPos] + 1 ; // or with 0000 0001
						hop[newPos] = hop[newPos] - 128 ; // and with 0111 1111
												
						returnPos = newPos;
						break;
					}

					flag = true;
				}
				
				}
			
			}
			
        }
	System.out.println("returned Position is : " + returnPos);	
    return returnPos;		

    }

    /**
     * Get current size.
     * @return the size.
     */
    public int size( )
    {
        return theSize;
    }

    /**
     * Get length of internal table.
     * @return the size.
     */
    public int capacity( )
    {
        return array.length;
    }

    /**
     * Find an item in the hash table.
     * @param x the item to search for.
     * @return the matching item.
     */
    public boolean contains( AnyType x )
    {
        int currentPos = findPos( x );
        System.out.println(isActive( currentPos ));
        return isActive( currentPos );
    }

    /**
     * Return true if currentPos exists and is active.
     * @param currentPos the result of a call to findPos.
     * @return true if currentPos is active.
     */
    private boolean isActive( int currentPos )
    {
        return array[ currentPos ] != null && array[ currentPos ].isActive;
    }

    
    private int myhash( AnyType x )
    {
        int hashVal = x.hashCode( );

        hashVal %= array.length;
        if( hashVal < 0 )
            hashVal += array.length;

        return hashVal;
    }

    private static class HashEntry<AnyType>
    {
    
		public AnyType  element;   // the element
        public boolean isActive;  // false if marked deleted

       
        public HashEntry( AnyType e, boolean i )
        {
            element  = e;
            isActive = i;
        }
    }

    private static final int DEFAULT_TABLE_SIZE = 101;

    private HashEntry<AnyType> [ ] array; // The array of elements
    private int theSize;                  // Current size

    /**
     * Internal method to allocate array.
     * @param arraySize the size of the array.
     */

	private void allocateArray( int arraySize )
    {
        array = new HashEntry[ nextPrime( arraySize ) ];
    }

    /**
     * Internal method to find a prime number at least as large as n.
     * @param n the starting number (must be positive).
     * @return a prime number larger than or equal to n.
     */
    private static int nextPrime( int n )
    {
        if( n % 2 == 0 )
            n++;

        for( ; !isPrime( n ); n += 2 )
            ;

        return n;
    }

    /**
     * Internal method to test if a number is prime.
     * Not an efficient algorithm.
     * @param n the number to test.
     * @return the result of the test.
     */
    private static boolean isPrime( int n )
    {
        if( n == 2 || n == 3 )
            return true;

        if( n == 1 || n % 2 == 0 )
            return false;

        for( int i = 3; i * i <= n; i += 2 )
            if( n % i == 0 )
                return false;

        return true;
    }


    // Simple main
    public static void main( String [ ] args )
    {
        HopScotchHashTable<String> H = new HopScotchHashTable<>( );

        //Demonstration of methods in HopScotch HashTable
        System.out.println( "Checking... (no more output means success)" );
        
        /**************************Insert Method*************************/
        System.out.println("*************************************************************************");
        
        
        //Inserting 12 elements
        System.out.println("***********checking insert method*************");
        
        H.insert("A"); 
        H.insert("B"); 
        H.insert("C"); 
        H.insert("D"); 
        H.insert("E"); 
        H.insert("F"); 
        H.insert("A");
        H.insert("G"); 
        H.insert("I");
        H.insert("J");
        H.insert("K");
        H.insert("L");
        
        //Inserting same element inserted before to see the result of hopscotch hash table
        H.insert("A"); 
        H.insert("B");
        H.insert("C");
        H.insert("D");
        H.insert("E");

        
        /**************************Contains Method*************************/
        
        System.out.println("*************************************************************************");
        
        System.out.println("***********checking contains method*******************");

        H.contains("B");
        H.contains("C");
        H.contains("I");
        H.contains("X");
        
        System.out.println("*************************************************************************");

    }

}
