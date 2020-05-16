package linkedlist;

/**
 * this interface is used on ADT (Abstract Data Type) like ArrayList, LinkedList and etc.
 * 
 * @author Shahriar (Shawn) Emami
 * @version Mar 30, 2020
 */
public interface CSTList< R> {

	/**
	 * if index is not between zero and list size throw an exception.
	 * can be used in any method that takes an argument of int index to error check. 
	 * @param index - index to check
	 * @throws IndexOutOfBoundsException if index is not between zero and list size
	 */
	//	private void isIndexValid(int index);

	/**
	 * return the node before the given index. very similar to {@link CSTList#get(int)}.
	 * can be used in other methods like insert and remove to get the node before index.
	 * @param index - index of the node to find the node before it
	 * @return the node before the index of the given index. if index is zero or size, null.
	 * @throws IndexOutOfBoundsException if given index is not valid
	 */
	//	private Node<R> getNodeBefore( int index);

	/**
	 * create a list by copying the data from given list
	 * @param list - source of data to be copied
	 */
	//	public CSTLinkedList( List< R> list);

	/**
	 * is empty
	 */
	//	public CSTLinkedList();


	/**
	 * add the the given element to the beginning of the list.
	 * can simply call the methods {@link CSTList#insert(Object, int)}.
	 * @param r - the new element to be added
	 */
	void addFirst( R r);
	/**
	 * add the the given element to the end of the list
	 * can simply call the method {@link CSTList#insert(Object, int)}.
	 * @param r - the new element to be added
	 */
	void addLast( R r);
	/**
	 * add the the given element to the at provided index. the old index will be pushed forward.
	 * @param r - the new element to be added
	 * @param index - index of position to be inserted
	 */
	void insert( R r, int index);
	/**
	 * remove the first element.
	 * can simply call the method {@link CSTList#remove(int)}.
	 * @return removed element
	 */
	R removeFirst();
	/**
	 * remove the last element.
	 * can simply call the method {@link CSTList#remove(int)}.
	 * @return removed element
	 */
	R removeLast();
	/**
	 * remove the element at the given index
	 * @return element as the given index after removal.
	 * @throws IndexOutOfBoundsException if given index is not valid
	 */
	R remove( int index);
	/**
	 * @return the first element in the list.
	 * can simply call the method {@link CSTList#get(int)}.
	 * @throws IndexOutOfBoundsException if given index is not valid
	 */
	R getFirst();
	/**
	 * @return the last element in the list.
	 * can simply call the method {@link CSTList#get(int)}.
	 * @throws IndexOutOfBoundsException if given index is not valid
	 */
	R getLast();
	/**
	 * @return the element as given index, 
	 * @throws IndexOutOfBoundsException if given index is not valid
	 */
	R get( int index);
	/**
	 * @return true of list is empty
	 */
	boolean isEmpty();
	/**
	 *@return current size of list
	 */
	int size();
	/**
	 * clear the current list by setting head and tail to null and size to zero.
	 */
	void clear();
}
