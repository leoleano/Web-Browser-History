package assign06;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class represents a re-implemented singly linked class
 * and implements the List interface.
 * 
 * @author Leonardo Leano & Tristen Kilgrow
 * @version March 5th, 2022
 */

public class SinglyLinkedList<T> implements List<T> {
	
	/**
	 * 
	 * Node class initializes any nodes that are created.
	 *
	 * @param <T>
	 */
	private class Node<E> {
		public T data;
		
		public Node<E> next;
		
		public Node(T data, Node<E> next)
		{
			this.data = data;
			this.next = next;
		}
	}
	
	//Fields
	Node<T> head;
	
	private int elementCount;
	
	
	//Constructor that sets element count to 0;
	public SinglyLinkedList()
	{	
		this.elementCount = 0;
	}
	
	/**
	 * Inserts an element at the beginning of the list.
	 * O(1) for a singly-linked list.
	 * 
	 * @param element - the element to add
	 */
	@Override
	public void insertFirst(T element) {
		head = new Node<T>(element, head);
		elementCount++;
	}
	
	/**
	 * Helper method to insert a new node containing element immediately 
	 * after prevNode.
	 * 
	 * @param newData
	 * @param prevNode
	 */
	private void insert (T element, Node<T> prevNode)
	{
		prevNode.next = new Node<T>(element, prevNode.next);
		elementCount++;
	}
	
	/**
	 * Inserts an element at a specific position in the list.
	 * O(N) for a singly-linked list.
	 * 
	 * @param index - the specified position
	 * @param element - the element to add
	 * @throws IndexOutOfBoundsException if index is out of range (index < 0 || index > size())
	 */
	@Override
	public void insert(int index, T element) throws IndexOutOfBoundsException {
		if (index < 0 || index > size())
			throw new IndexOutOfBoundsException();
		
		if (index == 0)
			insertFirst(element);
		else 
			insert (element, getPrevNode(index));
	}
	
	/**
	 * Helper method to retrieve the node occurring in the list before the
	 * node at the given position.
	 * 
	 * NOTE: It is a precondition that pos > 0.
	 * 
	 * @param index 0-indexed position of the node
	 * @throws IndexOutOfBoundsException if index is out of range (index < 0 || index > size())
	 * @return node at position index-1
	 */
	private Node<T> getPrevNode (int index) {
		if (index < 0 || index > size())
			throw new IndexOutOfBoundsException();
		
		Node<T> temp = head;
		//Bring temporary node from head to become the node just before the provided index.
		for(int i = 0; i < index - 1; i++)
			temp = temp.next;
		return temp;
	}
	
	/**
	 * Gets the first element in the list.
	 * O(1) for a singly-linked list.
	 * 
	 * @return the first element in the list
	 * @throws NoSuchElementException if the list is empty
	 */
	@Override
	public T getFirst() throws NoSuchElementException {
		if (isEmpty() == true)
			throw new NoSuchElementException();
		return head.data;
	}

	/**
	 * Gets the element at a specific position in the list.
	 * O(N) for a singly-linked list.
	 * 
	 * @param index - the specified position
	 * @return the element at the position
	 * @throws IndexOutOfBoundsException if index is out of range (index < 0 || index >= size())
	 */
	@Override
	public T get(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index > size())
			throw new IndexOutOfBoundsException();
		
		Node<T> temp = head;
		//Bring temporary node from head to become the node at provided index.
		for(int i = 0; i < index; i++)
			temp = temp.next;
		return temp.data;
	}
	
	/**
	 * Deletes and returns the first element from the list.
	 * O(1) for a singly-linked list.
	 * 
	 * @return the first element
	 * @throws NoSuchElementException if the list is empty
	 */
	@Override
	public T deleteFirst() throws NoSuchElementException {
		if (isEmpty() == true)
			throw new NoSuchElementException();
		
		//Save data of head to return after setting the node next to head as new head.
		T temp = head.data;
		head = head.next;
		elementCount--;
		return temp;
	}

	/**
	 * Deletes and returns the element at a specific position in the list.
	 * O(N) for a singly-linked list.
	 * 
	 * @param index - the specified position
	 * @return the element at the position
	 * @throws IndexOutOfBoundsException if index is out of range (index < 0 || index >= size())
	 */
	@Override
	public T delete(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index > size())
			throw new IndexOutOfBoundsException();
		
		T temp = get(index);
		
		//If index is 0, just call deleteFirst().
		if (index == 0)
			deleteFirst();
		else
			getPrevNode(index).next = getPrevNode(index).next.next;
		
		elementCount--;
		return temp;
	}
	
	/**
	 * Determines the index of the first occurrence of the specified element in the list, 
	 * or -1 if this list does not contain the element.
	 * O(N) for a singly-linked list.
	 * 
	 * @param element - the element to search for
	 * @return the index of the first occurrence; -1 if the element is not found
	 */
	@Override
	public int indexOf(Object element) {
		
		Node<T> temp = head;
		
		for (int x = 0; x < size(); x++)
		{
			//Check if data of temp is equal to what we're looking for.
			if (temp.data.equals(element))
				return x;
			//Still haven't found it, continue to next node.
			temp = temp.next;
		}
		
		//Element wasn't found, return -1;
		return -1;
	}

	/**
	 * O(1) for a singly-linked list.
	 * 
	 * @return the number of elements in this list
	 */
	@Override
	public int size() {
		return elementCount;
	}

	/**
	 * O(1) for a singly-linked list.
	 * 
	 * @return true if this collection contains no elements; false, otherwise
	 */
	@Override
	public boolean isEmpty() {
		if (size() == 0)
			return true;
		
		return false;
	}

	/**
	 * Removes all of the elements from this list.
	 * O(1) for a singly-linked list.
	 */
	@Override
	public void clear() {
		head = null;
		elementCount = 0;
	}

	/**
	 * Generates an array containing all of the elements in this list in proper sequence 
	 * (from first element to last element).
	 * O(N) for a singly-linked list.
	 * 
	 * @return an array containing all of the elements in this list, in order
	 */
	@Override
	public Object[] toArray() {
		Object[] arrayForm = new Object[size()];
		Node<T> temp = head;
		for (int x = 0; x < size(); x++)
		{
			arrayForm[x] = temp.data;
			temp = temp.next;
		}
		
		return arrayForm;
	}
	
	/**
	 * @return an iterator over the elements in this list in proper sequence (from first 
	 * element to last element)
	 */
	@Override
	public Iterator<T> iterator() {

		return new SLLIterator();
	}
	
	private class SLLIterator implements Iterator<T>
	{
		private int nextIndex;
		private boolean canRemove;
		Node<T> temp;
		
		public SLLIterator()
		{
			nextIndex = 0;
			canRemove = false;
			temp = head;
		}
		
		@Override
		public boolean hasNext()
		{
			if (nextIndex == size())
					return false;
			return true;
		}

		@Override
		public T next()
		{
			if (!hasNext())
				throw new NoSuchElementException();
			
			T toReturn;
			
			/**
			 * Check if the node that's being passed by iterator is the first node.
			 * 
			 * If so, simply just gather the data and don't move since we do just want to gather
			 * this information.
			 * 
			 * If not, move to next node and gather data.
			 */
			if (nextIndex == 0)
			{
				toReturn = temp.data;
			}
			else
			{
				temp = temp.next;
				toReturn = temp.data;
			}
			
			nextIndex++;
			canRemove = true;

			return toReturn;
		}
		
		public void remove()
		{
			if (!canRemove)
				throw new IllegalStateException();
			
			canRemove = false;
			nextIndex--;
			
			
			if (nextIndex == 0)
				deleteFirst();
			else
				delete(nextIndex);

		}
		
	}

}
