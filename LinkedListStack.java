package assign06;

import java.util.NoSuchElementException;

/**
 * This class represents a linked list stack that is backed by an
 * instance of the generic SinglyLinkedList class.
 * 
 * @author Leonardo Leano & Tristen Kilgrow
 * @version March 5th, 2022
 */

public class LinkedListStack <T> implements Stack<T>
{
	private SinglyLinkedList<T> list = new SinglyLinkedList<T>();
	
	/**
	 * Removes all of the elements from the stack.
	 */
	@Override
	public void clear()
	{
		list.clear();
	}
	
	/**
	 * @return true if the stack contains no elements; false, otherwise.
	 */
	@Override
	public boolean isEmpty()
	{
		return list.isEmpty();
	}
	
	/**
	 * Returns, but does not remove, the element at the top of the stack.
	 * 
	 * @return the element at the top of the stack
	 * @throws NoSuchElementException if the stack is empty
	 */
	@Override
	public T peek() throws NoSuchElementException
	{
		return list.getFirst();
	}

	/**
	 * Returns and removes the item at the top of the stack.
	 * 
	 * @return the element at the top of the stack
	 * @throws NoSuchElementException if the stack is empty
	 */
	@Override
	public T pop() throws NoSuchElementException
	{
		return list.deleteFirst();
	}

	/**
	 * Adds a given element to the stack, putting it at the top of the stack.
	 * 
	 * @param element - the element to be added
	 */
	@Override
	public void push(T element)
	{
		list.insertFirst(element);
	}

	/**
	 * @return the number of elements in the stack
	 */
	@Override
	public int size()
	{
		return list.size();
	}

}
