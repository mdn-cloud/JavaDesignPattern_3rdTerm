package linkedlist;

import java.util.List;

public class CSTLinkedList<R> implements CSTList<R> {

	private Node<R> head;
	private Node<R> tail;
	private int size;

	/**
	 * is empty
	 */
	public CSTLinkedList() {

	}



	/**
	 * create a list by copying the data from given list
	 * @param list - source of data to be copied
	 */
	public CSTLinkedList(List<R> list) {
		for (int i = 0; i < list.size(); i++) {
			insert(list.get(i), i);
		}
	}



	/**
	 * return the node before the given index. very similar to {@link CSTList#get(int)}.
	 * can be used in other methods like insert and remove to get the node before index.
	 * @param index - index of the node to find the node before it
	 * @return the node before the index of the given index. if index is zero or size, null.
	 * @throws IndexOutOfBoundsException if given index is not valid
	 */
	private Node<R> getNodeBefore(int index){
		isIndexValid(index);
		Node<R> n = head;
		for (int i = 0; n.next != null; i++, n = n.next) {
			if (i == index - 1)
				return n;
		}

		return n;

	}

	/**
	 * if index is not between zero and list size throw an exception.
	 * can be used in any method that takes an argument of int index to error check. 
	 * @param index - index to check
	 * @throws IndexOutOfBoundsException if index is not between zero and list size
	 */
	private void isIndexValid(int index) {
		if(index<0 && index>size)
			throw new IndexOutOfBoundsException("Index cannot be negetive and larger than size ");
	}


	@Override
	public String toString() {
		return "CSTLinkedList [head=" + head + ", tail=" + tail + ", size=" + size + "]";
	}


	/**
	 * add the the given element to the beginning of the list.
	 * can simply call the methods {@link CSTList#insert(Object, int)}.
	 * @param r - the new element to be added
	 */
	@Override
	public void addFirst(R r) {
		insert(r, 0);
	}



	/**
	 * add the the given element to the end of the list
	 * can simply call the method {@link CSTList#insert(Object, int)}.
	 * @param r - the new element to be added
	 */
	@Override
	public void addLast(R r) {
		insert(r, size);
	}



	/**
	 * add the the given element to the at provided index. the old index will be pushed forward.
	 * @param r - the new element to be added
	 * @param index - index of position to be inserted
	 */
	@Override
	public void insert(R r, int index) {

		Node<R> n = new Node(r);
		if (index == 0) {
			tail = n;
			head = n;	
		}
		else if (index >= size -1) {
			tail.next = n;
			tail = n;



		}else if (index > 0 || index < size -1) {
			Node<R> temp = getNodeBefore(index);
			n.next = temp.next;
			temp.next = n;


		}else {
			isIndexValid(index);
		}
		size++;
	}




	/**
	 * remove the first element.
	 * can simply call the method {@link CSTList#remove(int)}.
	 * @return removed element
	 */
	@Override
	public R removeFirst() {
		return remove(0);
	}



	/**
	 * remove the last element.
	 * can simply call the method {@link CSTList#remove(int)}.
	 * @return removed element
	 */
	@Override
	public R removeLast() {
		return remove(size-1);
	}



	/**
	 * remove the element at the given index
	 * @return element as the given index after removal.
	 * @throws IndexOutOfBoundsException if given index is not valid
	 */
	@Override
	public R remove(int index) {

		isIndexValid(index);
		Node<R> temp = getNodeBefore(index);
		if (index == 0) {
			Node<R> n = head;
			head = head.next;
			n.next = null;
			size--;
			return n.element;
		}

		else {
			if(index >= size-1) {
				Node<R> node = temp.next;
				tail = temp;
				node.next = null;
				size--;
				return node.element;


			}else {
				Node<R> node = temp.next;
				temp.next = node.next;
				node.next = null;
				size--;
				return node.element;
			}
		}
	}

	/**
	 * @return the first element in the list.
	 * can simply call the method {@link CSTList#get(int)}.
	 * @throws IndexOutOfBoundsException if given index is not valid
	 */
	@Override
	public R getFirst() {

		return get(0);
	}

	/**
	 * @return the last element in the list.
	 * can simply call the method {@link CSTList#get(int)}.
	 * @throws IndexOutOfBoundsException if given index is not valid
	 */
	@Override
	public R getLast(){
		return get(size-1);
	}

	/**
	 * @return the element as given index, 
	 * @throws IndexOutOfBoundsException if given index is not valid
	 */
	@Override
	public R get(int index) {

		isIndexValid(index);
		if(isEmpty()) {
			return null;
		}
		if(index>=size-1) {
			return tail.element;
		}
		if(index==0) {
			return head.element;
		}
		Node<R> n = head;
		for(int i = 0; n.next != null; i++) {

			if(i==index) 
				return n.element;
		}


		return null;
	}



	/**
	 * @return true of list is empty
	 */
	@Override
	public boolean isEmpty() {
		if (size == 0 || head == null) {
			return true;
		}

		return false;
	}


	/**
	 *@return current size of list
	 */
	@Override
	public int size() {
		return size;
	}



	/**
	 * clear the current list by setting head and tail to null and size to zero.
	 */
	@Override
	public void clear() {
		head = null;
		tail = null;
		size = 0;

	}

	public static void main(String[] args) {

	}


	//nested Node class

	public class Node<T> {
		private Node<T> next;
		private T element;

		private Node(T t) {
			this.element = t;
		}

		private Node(T t, Node<T> next) {
			this.element = t;
			this.next = next;
		}


		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((getElement() == null) ? 0 : getElement().hashCode());
			result = prime * result + ((next == null) ? 0 : next.hashCode());
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;

			Node other = (Node) obj;
			if (getElement() == null) {
				if (other.getElement() != null)
					return false;
			} else if (!getElement().equals(other.getElement()))
				return false;
			if (next == null) {
				if (other.next != null)
					return false;
			} else if (!next.equals(other.next))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "Node [next=" + next + ", element=" + getElement() + "]";
		}

		public T getElement() {
			return element;
		}

		public void setElement(T element) {
			this.element = element;
		}


	}

}

