/************************************************************************************************************
	    				*******************************	 
						*        Project : 2	      *	
						* Submitted by : Binal Kamani *	
						*    bxk131030@utdallas.edu   *	
	 					*******************************	
***********************************************************************************************************/

/************************************************************************************************************
Modify the author's "MyLinkedList" class to add the following methods:
  

20 points each (a-e)

   a.  swap
        receives two index positions as parameters, and swaps the values at
        these positions, provided both positions are within the current size
        
   b.  reverse
        returns a new MyLinkedList that has the elements in reverse order.
     
   c.  erase 
        receives an index position and number of elements as parameters, and
        removes elements beginning at the index position for the number of 
        elements specified, provided the index position is within the size
        and together with the number of elements does not exceed the size
  
	d.  insertList
        receives a List and an index position as parameters, and copies all of the 
        passed list into the existing list at the position specified by the parameter,
        provided the index position does not exceed the size
        
    e.  main
        add code to the main method to demonstrate each of your methods

/************************************************************************************************************


import java.util.Iterator;


/**
 * LinkedList class implements a doubly-linked list.
 */

public class MyLinkedList<AnyType> implements Iterable<AnyType>
{
    /**
     * Construct an empty LinkedList.
     */
    public MyLinkedList( )
    {
        doClear( );
    }
    
    /*------------------------------------------SWAP METHOD-------------------------------------------------*/

    public AnyType swap( int idx, int newidx) 
	{
		Node<AnyType> p = getNode( idx );
		AnyType tempval1 = p.data;
		
		Node<AnyType> q = getNode ( newidx );
		AnyType tempval2 = q.data;
		
		p.data = tempval2;
		q.data = tempval1;
		
		return null;
		
	}
    /*------------------------------------------------------------------------------------------------------*/
    
    /*------------------------------------------REVERSE METHOD----------------------------------------------*/
   
    public void reverselist(MyLinkedList <AnyType> list ) 		// Used Method for REVERSING LIST
	 {	
		 int num2 = 1;
		 int tempsize = list.size();
		 
		 for (int temp = 0; temp < tempsize-1 ; temp++)
		   {
			
			 list.add(0, list.get(num2));
		  	 list.remove(num2+1);
			 num2 = num2+1 ;
		  
		   }
	 }
    
    public void tempreverselist( ) 								// Another Method for same task	
	 {	
		 
		   Node<AnyType> t = beginMarker; 
		   beginMarker = endMarker; 
		   endMarker = t; 
		   
		  Node<AnyType> r = beginMarker; 

		  while(r!=null) 
		    { 
		      t = r.next; 
		      r.next = r.prev; 
		      r.prev = t; 
		      r = r.next;
		    }
		 }
	 /*------------------------------------------------------------------------------------------------------*/
	 
	 /*------------------------------------------ERASE METHOD------------------------------------------------*/

	 public AnyType Erase( int idx, int totalelements) 
		{
		
		 int l = idx;
		 
		 for (int m = l ; m<=totalelements; m++)
		 {
			 
			Node<AnyType> s1 = getNode( idx );
			s1.next.prev = s1.prev;
	        s1.prev.next = s1.next; 
					
		 }
			return null;
			
		}
	 
	 /*------------------------------------------------------------------------------------------------------*/

	 /*------------------------------------------INSERT METHOD-----------------------------------------------*/
 
	 
	public AnyType insertlist( int index  , MyLinkedList<AnyType> lst1, MyLinkedList<AnyType> lst) 
	{
		int num = 0;
   
		  for (int o = 0; o < lst1.size(); o++)
		   {
			   lst.add(index, lst1.get(num)); 
			   num = num+1;
			   index++;
		   }
		  
		return null;
		   		   
	}   
	
	 /*------------------------------------------------------------------------------------------------------*/

	 		
    /**
     * Change the size of this collection to zero.
     */
    public void doClear( )
    {
        beginMarker = new Node<AnyType>( null, null, null ); 					   	// head = node(int number or digit, previous node, next node)
        endMarker = new Node<AnyType>( null, beginMarker, null );					// tail = same as above	
        beginMarker.next = endMarker;
        
        theSize = 0;
        modCount++;
    }
    
    /**
     * Returns the number of items in this collection.
     * @return the number of items in this collection.
     */
    public int size( )
    {
        return theSize;
    }
    
    public boolean isEmpty( )
    {
        return size( ) == 0;
    }
    
    /**
     * Adds an item to this collection, at the end.
     * @param x any object.
     * @return true.
     */
    public boolean add( AnyType x )
    {
        add( size( ), x );   
        return true;         
    }
    
    /**
     * Adds an item to this collection, at specified position.
     * Items at or after that position are slid one position higher.
     * @param x any object.
     * @param idx position to add at.
     * @throws IndexOutOfBoundsException if idx is not between 0 and size(), inclusive.
     */
    public void add( int idx, AnyType x )
    {
        addBefore( getNode( idx, 0, size( ) ), x );			// getNode(int index, int lower, int upper)
    }
    
    /**
     * Adds an item to this collection, at specified position p.
     * Items at or after that position are slid one position higher.
     * @param p Node to add before.
     * @param x any object.
     * @throws IndexOutOfBoundsException if idx is not between 0 and size(), inclusive.
     */    
    private void addBefore( Node<AnyType> p, AnyType x )
    {
        Node<AnyType> newNode = new Node<AnyType>( x, p.prev, p );
        newNode.prev.next = newNode;
        p.prev = newNode;         
        theSize++;
        modCount++;
    }   
    
    
    /**
     * Returns the item at position idx.
     * @param idx the index to search in.
     * @throws IndexOutOfBoundsException if index is out of range.
     */
    public AnyType get( int idx )
    {
        return getNode( idx ).data;
    }
        
    /**
     * Changes the item at position idx.
     * @param idx the index to change.
     * @param newVal the new value.
     * @return the old value.
     * @throws IndexOutOfBoundsException if index is out of range.
     */
    public AnyType set( int idx, AnyType newVal )
    {
        Node<AnyType> p = getNode( idx );
        AnyType oldVal = p.data;
        
        p.data = newVal;   
        return oldVal;
    }
    
    /**
     * Gets the Node at position idx, which must range from 0 to size( ) - 1.
     * @param idx index to search at.
     * @return internal node corresponding to idx.
     * @throws IndexOutOfBoundsException if idx is not between 0 and size( ) - 1, inclusive.
     */
    private Node<AnyType> getNode( int idx )
    {
        return getNode( idx, 0, size( ) - 1 );
    }

    /**
     * Gets the Node at position idx, which must range from lower to upper.
     * @param idx index to search at.
     * @param lower lowest valid index.
     * @param upper highest valid index.
     * @return internal node corresponding to idx.
     * @throws IndexOutOfBoundsException if idx is not between lower and upper, inclusive.
     */    
    private Node<AnyType> getNode( int idx, int lower, int upper )
    {
        Node<AnyType> p;
        
        if( idx < lower || idx > upper )
            throw new IndexOutOfBoundsException( "getNode index: " + idx + "; size: " + size( ) );
            
        if( idx < size( ) / 2 )
        {
            p = beginMarker.next;
            for( int i = 0; i < idx; i++ )
                p = p.next;            
        }
        else
        {
            p = endMarker;
            for( int i = size( ); i > idx; i-- )
                p = p.prev;
        } 
        
        return p;
    }
    
    /**
     * Removes an item from this collection.
     * @param idx the index of the object.
     * @return the item was removed from the collection.
     */
    public AnyType remove( int idx )
    {
        return remove( getNode( idx ) );
    }
    
    /**
     * Removes the object contained in Node p.
     * @param p the Node containing the object.
     * @return the item was removed from the collection.
     */
    private AnyType remove( Node<AnyType> p )
    {
        p.next.prev = p.prev;
        p.prev.next = p.next;
        theSize--;
        modCount++;
        
        return p.data;
    }
    
    /**
     * Returns a String representation of this collection.
     */
    public String toString( )
    {
        StringBuilder sb = new StringBuilder( "[ " );

        for( AnyType x : this )
            sb.append( x + " " );
        sb.append( "]" );

        return new String( sb );
    }

    /**
     * Obtains an Iterator object used to traverse the collection.
     * @return an iterator positioned prior to the first element.
     */
    public java.util.Iterator<AnyType> iterator( )
    {
        return new LinkedListIterator( );
    }

    
    /**
     * This is the implementation of the LinkedListIterator.
     * It maintains a notion of a current position and of
     * course the implicit reference to the MyLinkedList.
     */
    private class LinkedListIterator implements java.util.Iterator<AnyType>
    {
        private Node<AnyType> current = beginMarker.next;
        private int expectedModCount = modCount;
        private boolean okToRemove = false;
        
        public boolean hasNext( )
        {
            return current != endMarker;
        }
        
        public AnyType next( )
        {
            if( modCount != expectedModCount )
                throw new java.util.ConcurrentModificationException( );
            if( !hasNext( ) )
                throw new java.util.NoSuchElementException( ); 
                   
            AnyType nextItem = current.data;
            current = current.next;
            okToRemove = true;
            return nextItem;
        }
        
        public void remove( )
        {
            if( modCount != expectedModCount )
                throw new java.util.ConcurrentModificationException( );
            if( !okToRemove )
                throw new IllegalStateException( );
                
            MyLinkedList.this.remove( current.prev );
            expectedModCount++;
            okToRemove = false;       
        }
    }
    
    /**
     * This is the doubly-linked list node.
     */
    private static class Node<AnyType>
    {
        public Node( AnyType d, Node<AnyType> p, Node<AnyType> n )
        {
            data = d; prev = p; next = n;
        }
        
        public AnyType data;
        public Node<AnyType>   prev;
        public Node<AnyType>   next;
    }
    
    private int theSize;
    private int modCount = 0;
    private Node<AnyType> beginMarker;
    private Node<AnyType> endMarker;
}

class TestLinkedList
{
    public static void main( String [ ] args )
    {
        MyLinkedList<Integer> lst = new MyLinkedList<Integer>( );
        
        MyLinkedList<Integer> lst1 = new MyLinkedList<Integer>( );
        
        for( int n = 40; n < 46; n++)
        		lst1.add(n);
        
        for( int i = 0; i < 10; i++ )
                lst.add( i );
        for( int i = 20; i < 30; i++ )
                lst.add( 0, i );

        System.out.println("-------------------------------------------------------------------");
        System.out.println("*** DEMONSTRATION OF IMPLEMENTED METHODS ***\n");
        
        System.out.println("# Main List : " + lst);
        System.out.println("\n# Sub List to be inserted : " + lst1);
         /*------------------------------------------SWAP METHOD--------------------------------------------*/
        
        System.out.println("-------------------------------------------------------------------");
        System.out.println( "*** 1.SWAP METHOD (Swaping 6th and 7th Node) ***" );
        
        /* Receives two index positions as parameters, and swaps the values at  *
         * these positions, provided both positions are within the current size */
        
        lst.swap( 6, 7 ); 					
        System.out.println( lst );
        System.out.println("-------------------------------------------------------------------");
        
        
        /*------------------------------------------REVERSE METHOD------------------------------------------*/
        
        System.out.println( "*** 2.REVERSE METHOD (Reversing Main List) ***" );
        
        /* Returns a new MyLinkedList that has the elements in reverse order */
        
        lst.reverselist(lst); 
        
        System.out.println( lst ); 
        System.out.println("-------------------------------------------------------------------");
                
        //lst1.tempreverselist();
        //System.out.println(lst1);
        
        /*------------------------------------------ERASE METHOD--------------------------------------------*/
        
        System.out.println( "*** 3.ERASE METHOD (Erasing Node 0 to Node 5 of Main List) ***" );
        
        /* receives an index position and number of elements as parameters, and 
         * removes elements beginning at the index position for the number of 
         * elements specified */
        
        lst.Erase(0, 5);        
        System.out.println( lst );
        System.out.println("-------------------------------------------------------------------");

        /*------------------------------------------INSERT METHOD-------------------------------------------*/
        
        /*  receives a List and an index position as parameters, and copies all of the 
         *  passed list into the existing list at the position specified by the parameter */
        
        lst.insertlist(6, lst1, lst);  // Insert lst1 from 6th index of lst
        
        System.out.println( "*** 4.INSERT METHOD (Inserting sub List to Main List) ***" );
        System.out.println( lst );
        System.out.println("-------------------------------------------------------------------");
        
    }
}
