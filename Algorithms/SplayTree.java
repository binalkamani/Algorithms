/************************************************************************************************************
	    				*******************************	 
						*        Project : 3	      *	
						* Submitted by : Binal Kamani *	
						*    bxk131030@utdallas.edu   *	
	 					*******************************	
***********************************************************************************************************/

/**
 * Implements a Splay tree.
 */

public class SplayTree {

		private class Node {
		public Integer element;
		public Node left, right;
				
		public Node(Integer element) {
			this.element = element;
			left = right = null;
		}
	}

	
	public static Node root;	// Root node
	static int count = 0;
	public static int sum = 0;
	

	/**
	 * Constructor for an empty splay tree.
	 */
	public SplayTree() {
		root = null;
	}

	/*********************************Implementing Methods***************************************/

	 /**
	 * Rotating node with its left child.
	 */
	
	private Node leftChildRotate(Node t) {
		Node newnode = t.left;
		t.left = newnode.right;
		newnode.right = t;
		return newnode;
	}

	/**
	 * Rotating node with its right child.
	 */
	
	private Node rightChildRotate(Node t) {
		Node newnode = t.right;
		t.right = newnode.left;
		newnode.left = t;
		return newnode;
	}

	/**
	 * Implementing a splay operation to bring the node up to the root.
	 */
	
	private void splay(Integer element) {
		Node t = root;
		Node parent = null;
		Node grandparent = null;
		Node temp = null;
		boolean flag = true;

		while (true) {
			if (t == null || element.compareTo(t.element) == 0)
				break;
			else if (t.left != null && element.compareTo(t.element) < 0) {
				// Then rotate this node and set as root
				if (element.compareTo(t.left.element) == 0) {
					t = leftChildRotate(t);
				}
			
				else if (t.left.left != null
						&& element.compareTo(t.left.left.element) == 0) {
					//zig-zig rotation
					
					grandparent = t;
					parent = t.left;
					t = leftChildRotate(grandparent);
					t = leftChildRotate(parent);
					flag = true;
				}
				
				else if (t.left.right != null
						&& element.compareTo(t.left.right.element) == 0) {
					//zig-zag rotation
					
					grandparent = t;
					parent = t.left;
					grandparent.left = rightChildRotate(parent);
					t = leftChildRotate(grandparent);
					flag = true;
				}
				// if none of the above is true then go to left node and check again
				else if (element.compareTo(t.element) < 0) {
					temp = t;
					t = t.left;
				}
			} else if (t.right != null && element.compareTo(t.element) > 0) {
				
				if (element.compareTo(t.right.element) == 0) {
					// Then rotate this node and set as root
					t = rightChildRotate(t);
				}
				
				else if (t.right.right != null
						&& element.compareTo(t.right.right.element) == 0) {
					// zig-zig rotation
					grandparent = t;
					parent = t.right;
					t = rightChildRotate(grandparent);
					t = rightChildRotate(parent);
					flag = true;
				}
				
				else if (t.right.left != null
						&& element.compareTo(t.right.left.element) == 0) {
					// zig-zag rotation
					grandparent = t;
					parent = t.right;
					grandparent.right = leftChildRotate(parent);
					t = rightChildRotate(grandparent);
					flag = true;
				}
				//if none of the above is true then go to right node and check again
				else if (element.compareTo(t.element) > 0) {
					temp = t;
					t = t.right;
				}
			}
			// If still not found then use the last node as current node and continue
			else if ((t.left == null && element.compareTo(t.element) < 0)
					|| (t.right == null && element.compareTo(t.element) > 0)) {
				element = t.element;
				t = root;
				temp = null;
			}

			// Adjustment of nodes after splaying
			if (flag && temp != null) {
				int value = t.element.compareTo(temp.element);
				if (value < 0)
					temp.left = t;
				else if (value > 0)
					temp.right = t;
				t = root;
				temp = null;
				flag = false;
			}
		}
		root = t; // All Done
	}

	/**
	 * Checks if this tree is empty.
	 */
	
	public boolean isEmpty() {
		return root == null;
	}


	/**
	 * add a node with passed value and then splays it to the root
	 */
	
	public void add(Integer value) {
		if (value != null) {
			root = add(value, root);
			splay(value);
		}
	}

	private Node add(Integer value, Node t) {
		if (t == null)
			return new Node(value);
		else {
			int nodeindex = value.compareTo(t.element);
			if (nodeindex < 0)
				t.left = add(value, t.left);
			else if (nodeindex > 0)
				t.right = add(value, t.right);
			return t;
		}
	}

	/**
	 * Removes the node and make it's parent as root node
	 */
	
	public boolean remove(Integer value) {

		if (!isEmpty() && value != null) {
			splay(value);
			if (root != null && root.element.compareTo(value) == 0) {
				if (root.left != null) {
					Node tempnode = root.right;
					root = root.left;
					splay(value);
					root.right = tempnode;
				} else
					root = root.right;
				return true;
			}
		}
		return false;
	}

	/**
	 * Find out if the value is in the tree and return true if it is there.
	 * Then splay so that the found value becomes root
	 */
	

    public boolean find( Integer value )
    {
        return find( value, root );
    }
	
    private boolean find( Integer value, Node t )
    {
        while( t != null )
        {
            int compareResult = value.compareTo( t.element );
            
            if( compareResult < 0 )
                t = t.left;
            else if( compareResult > 0 )
                t = t.right;
            else{
                splay(value);
            	return true;    // Match
                }
        }

        return false;   // No match
    }
	
	/**
	 * Returns the count of all of the leaves in the tree.
	 */
    
	public static int leafcount(Node root) {
		Node t = root;
		
		if (t == null)
			return 0;
		
		else if (t.left == null && t.right == null)
			return 1;
		
		else{ 
			return leafcount(t.left) + leafcount(t.right) ;
			}
		}
	
	/**
	 * Returns the sum of all of the leaves in the tree.
	 */
	
	static int LeafSum( Node root ) {
       
		Node t = root;
		
		if (t == null)
			return 0;
	
		else if (t.left == null && t.right == null)
			return t.element;

		else
			return LeafSum(t.left) + LeafSum(t.right) ;
	}
	
	/**
     * Returns a string of the values of a preorder traversal of the nodes.
	 */
    public String toString( )
    {
        if( isEmpty( ) )
            System.out.println( "Empty tree" );
        else
            toString( root );
		return null;
    }
    
    private static void toString( Node t )
    {
        if( t != null )
        {
        	System.out.println( t.element );
        	toString( t.left );
            toString( t.right );
        }
    }

	/**
     *  Demonstration of all above methods.
	 */    
    
	public static void main( String [ ] args )
    {
		
		SplayTree s = new SplayTree();
			
		System.out.println(" *********** ");
		
		System.out.println(" *****Splay Tree****** ");
		//Make demo tree by adding nodes using add method
		
		s.add(80);
		s.add(90);
		s.add(55);
		s.add(20);
		s.add(45);
		s.add(65);
		s.add(15);
		
		// Printing pre order traversal of nodes of SplayTree
		s.toString();		
		
		//Removing Node with passed value
		s.remove(65);
		s.remove(20);
	
		System.out.println(" *****Splay Tree after using Remove Method****** ");
		s.toString();
		
		//Finds value in tree
		s.find(55);
		
		//Printing pre order traversal of nodes of SplayTree after remove and find method usage
		System.out.println(" *****Splay Tree after using Find Method****** ");
		s.toString();
		
		// Counting number of leaf in Tree
		System.out.println("\nNumber of Leaf in Splay Tree  :  " + leafcount(root));
		System.out.println("*********** \n");
		
		// Summation of all leaf values of a tree
		System.out.println("Summation of all leaf in Splay Tree :  " + LeafSum(root));
		
		System.out.println("*********** ");
		
    }

}

