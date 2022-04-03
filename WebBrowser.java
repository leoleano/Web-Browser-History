package assign06;

import java.net.URL;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class represents a web browser and includes the usual behaviors
 * of keeping track of URLs as a history of previously visited and forward/next visted URLs,
 * methods that replicate the functionality of the 'back' and 'forward' buttons, visiting sites,
 * and retrieving the history of the web browser.
 * 
 * @author Leonardo Leano & Tristen Kilgrow
 * @version March 5th, 2022
 */

public class WebBrowser
{
	//Fields
    private URL current;

    private LinkedListStack<URL> next;
    private LinkedListStack<URL> previous;
    private LinkedListStack<URL> temp;

    /**
     * This constructor creates a new web browser with no previously-visited 
     * webpages and no webpages to visit next.
     */
    public WebBrowser() {
        previous = new LinkedListStack<URL>();
        next = new LinkedListStack<URL>();
    }

    /**
     * This constructor creates a new web browser with a preloaded history of visited webpages, 
     * given as a list of URL (Links to an external site.) objects.  
     * The first webpage in the list is the "current" webpage visited, and the remaining webpages 
     * are ordered from most recently visited to least recently visited.
     * @param history
     */
    public WebBrowser(SinglyLinkedList<URL> history) {
        //Create iterator that will iterate through the history singly linked list.
    	Iterator<URL> it = history.iterator();
    	//history() is built so that the first node is intended to be the current page in Web browser.
    	current = it.next();
    	//Initialize next and previous linked lists.
        next = new LinkedListStack<URL>();
        previous = new LinkedListStack<URL>();
        /*
         * Temp linked list is used to read in the history (excluding the current) so that
         * the previous linked list can read in the values in the correct order.  
         */
        temp = new LinkedListStack<URL>();
        for (int x = 0; x < history.size() - 1; x++) 
        	temp.push(it.next());
        
        /**
         * The singly linked list provided can't be read by .push(it.next()) within the loop,
         * so temp is used to reverse the order so that previous can read it in correctly.
        */
        for (int x = 0; x < history.size() - 1; x++)
        	previous.push(temp.pop());
    }

    /**
     * This method simulates visiting a webpage, given as a URL. The next/forward history is cleared
     * since there is no longer any URL to visit next.
     * @param webpage
     */
    public void visit(URL webpage) {
    	next.clear();
    	//Check if current is null. This is to avoid sending null to .push().
    	if (!(current == null))
    		previous.push(current);
    	current = webpage;
    }

    /**
     * This method simulates using the back button, returning the URL visited.
     * @return URL
     * @throws NoSuchElementException is thrown if there is no previously-visited URL.
     */
    public URL back() throws NoSuchElementException {
        if (previous.isEmpty())
        	throw new NoSuchElementException();
        
        next.push(current);
        current = previous.pop();
    	
    	return current;
    }

    /**
     * This method simulates using the forward button, returning the URL visited
     * @return URL
     * @throws NoSuchElementException is thrown if there is no URL to visit next.
     */
    public URL forward() throws NoSuchElementException {
        if (next.isEmpty())
        	throw new NoSuchElementException();
        
        previous.push(current);
        current = next.pop();
        
    	return current;
    }

    /**
     * This method generates a history of URLs visited, as a list of URL objects ordered 
     * from most recently visited to least recently visited 
     * (including the "current" webpage visited), without altering subsequent behavior 
     * of this web browser.  "Forward" URLs are not included.  
     * The behavior of the method must be O(N), where N is the number of URLs.
     * @return SinglyLinkedList
     */
    public SinglyLinkedList<URL> history() {
    	
    	SinglyLinkedList<URL> URLHistory = new SinglyLinkedList<URL>();
    	
    	int sizeOfHistory = previous.size();
    	
    	/**
    	 * For loop calls back() to get all elements in previous linked list and stores it
    	 * in the singly linked list.
    	 */
    	for (int x = 0; x < sizeOfHistory; x++)
    		URLHistory.insert(x, back());
    	
    	/**
    	 * For loop reverts the change of calling back() in previous for loop so 
    	 * subsequent behavior of this web browser isn't affected.
    	 */
    	for (int x = 0; x < sizeOfHistory; x++)
    		forward();
    	
    	
    	URLHistory.insertFirst(current);
    	
        return URLHistory;

    }


}