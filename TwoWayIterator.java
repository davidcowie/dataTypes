import java.util.Iterator;
import java.util.NoSuchElementException;

/** An iterator that allows the programmer to traverse the list in either direction.
* Initial the cursor position is before the first element in the list.
* 
*/
public interface TwoWayIterator<E> extends Iterator<E>{
			
			
		 /**
	     * Tests whether the iterator has a previous object.
	     * @return Returns true if the iterator has more elements when traversing the list in the reverse direction.
	     */
		
		public boolean hasPrevious();
	
		/**
	     * Returns the previous element in the list and moves the cursor position backwards.
	     * @return previous object
	     * @throws NoSuchElementException if there are no previous elements
	     */
		public E previous();
		
		
}