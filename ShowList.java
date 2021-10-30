import java.util.NoSuchElementException;

public class ShowList {
	public int size;
	public ShowNode head;
	//here we might have a privacy leaks due to the creation of the ShowNode which perform as pointer  and make it public ... 
	//default constructor..
	public ShowList(){
		head = null;
		size = 0;
	}

	//copy constructor...

	public ShowList(ShowList shl){
		size = shl.size;
		head = shl.head;
		head = new ShowNode();
		ShowNode currNode = head;
		ShowList NList = new ShowList();
		for(int i=0; i<shl.size; i++) {
			ShowNode nod = new ShowNode(currNode);
			NList.addToStart(nod.getShow());
			currNode = currNode.getLink();
		}
	}


	//setters...

	public void setSize(int size) {
		this.size = size;
	}
	public void setHead(ShowNode head) {
		this.head = head;
	}

	//getters..

	public ShowNode getHead() {
		return head;
	}
	public int getSize() {
		return size;
	}
	/*
	 * addttStart method accepts one parameter
	 *  @param TVShow object and creates a node with that object
	 *  inserts this node at the head of the list.
	 */

	public void addToStart(TvShow ts){
		head = new ShowNode(ts,head);
		size++;
	}

	/**
	 * This method has  privacy leak since it is public.
	 * Returns the node at a certain index
	 * @param indexToCheck
	 * @param indexMethod
	 * @return
	 */
	public ShowNode nodeAtIndex(int indexToCheck, int indexMethod) {
		try
		{
			// Check if it's a valid index
			if ((indexToCheck < 0 || indexToCheck >= size) || (indexMethod < 0 && indexMethod >= size))
			{
				throw new NoSuchElementException();
			}
			else
			{
				// Return the node at the specified index
				ShowNode node = head;
				for (int i = 0; i < indexToCheck; i++)
				{
					if (node != null)
					{
						node = node.link;
					}
					else
					{
						throw new NullPointerException();
					}
				}

				return node;
			}
		} catch (NoSuchElementException e)
		{
			System.out.println("Error: " + indexMethod + " is an invalid index: " + e.getMessage());
			System.exit(0);
		} catch (NullPointerException e)
		{
			System.out.println("Error: unexpected null node in the middle of the linked list.");
			System.exit(0);
		}
		return null;
	}
	/*
	 * the method is to insert at an index
	 * @param TvShow object..
	 * @param index int is where to add the object...
	 */
	public void insertAtIndex(TvShow data, int index) {
		if (size == 0)
		{
			addToStart(data);
		}
		else
		{
			// Get the  nodes
			ShowNode nodeBefore = nodeAtIndex(index - 1, index);
			ShowNode nodeAtIndex  = nodeBefore.link;

			// Create new node with nodeAtIndex as its nextNode
			ShowNode nodeToInsert = new ShowNode(data, nodeAtIndex);
			size++;

			// Point previous node to new node
			nodeBefore.link = nodeToInsert;
			
			return;
		}
	} 


	public void deleteFromIndex(int index){
		try
		{
			// Checks if there are any elements to delete
			if (size == 0)
			{
				throw new NullPointerException();
			}
			// Get the node  before the one at the given index
			ShowNode nodeBefore = nodeAtIndex(index - 1, index);

			// Skip the next node and go to the one after; assign it to that previous node
			nodeBefore.link = nodeBefore.link.link;
			size--;
			
			return;
		} catch (NullPointerException e)
		{
			System.out.println("Error: the list has no size , can't delete any element.");
			System.exit(0);
		}
	}
	/*
	 * method  deleteFromStart()
	 * deletes the first node in the list 
	 * and handle special cases 
	 */
	public void deleteFromStart() {
		if (size > 1)
		{
			head = head.link;
			size--;
		}
		else if (size == 1)
		{
			head = null;
			size = 1;
		}
		else
		{
			System.out.println("No more elements to delete");
		}
	}

	/*
	 * method replaceAtIndex()accepts two parameters
	 * @param TVShow object
	 * @param int  index. 
	 *  index is not valid @return
	 *   the object in the node at the passed index is to be replaced by the passed object.
	 */

	public void replaceAtIndex(TvShow tvsh, int index) {
		if (index < 0 || index>= size) {
			return;
		} else {
			// Navigate to node at index
			ShowNode nodeAtIndex = nodeAtIndex(index -1 , index);
			// Replace that node
			nodeAtIndex.link = new ShowNode(tvsh, nodeAtIndex.link);
		}
	}

	/*
	 * method find() accepts @param String showID. 
	 * The method searches the list for a ShowNode with that showID. 
	 * If such an object is found, the method @return a pointer to that ShowNode.
	 *  otherwise, the method returns null.
	 *   The method keeps track of how many iterations were made before the search finally finds the course.
	 */

	public TvShow find(String ID){//problem 
		ShowNode current = head;
		int Counter = 1;
		while (current != null) {
			
			if (current.show.getShowID().equalsIgnoreCase(ID)) {
				System.out.println("Iterator NO."+ Counter + " iterations were completed.");
				return current.getShow();
			} else {
				current = current.link;
				Counter++;
			}
		}
		//System.out.println("Iterator NO."+Counter + " iterations were completed.");

		return null ;
	}
	
	/*
	 * method contains() accepts @param StringshowID. 
	 * @return true if a course with that showID is in the list 
	 * otherwise, @return false.
	 */

	public boolean contains(String ID){
		if (find(ID) != null) {
			return false;
		}else {
			return true;
		}
	}
	/*
	 * @override equals() accepts @param type ShowList.
	 *  @return true if the two lists contain similar shows.
	 *   otherwise, @return false. 
	 */
	public boolean equals(ShowList sl){

		ShowNode sn1 = this.head;
		ShowNode sn2 = sl.head;
		while (sn1 != null && sn2 != null)
		{
			if (sn1.show.getEndTime() == sn2.show.getEndTime() &&
					sn1.show.getStartTime() == sn2.show.getStartTime() &&
					sn1.show.getEndTime() == sn2.show.getEndTime()) {
				return true;
			}

			sn1 = sn1.link;
			sn2 = sn2.link;
		}
		return (sn1 == null && sn2 == null);
	}
	//creating the The SHOW NODE class.....
	public static class ShowNode{
		public  TvShow show; 
		public  ShowNode link; //here we might have a privacy leak since the attributes are public...

		//default constructor..
		public ShowNode() {
			this.show = null;
			this.link = null;
		}

		//parameterized  constructor..
		public ShowNode(TvShow show, ShowNode link) {
			this.show = show;
			this.link = link;
		}
		//copy constructor..

		public ShowNode(ShowNode obj){
			show=obj.show;
			link=obj.link;
		}

		//clone method...
		public ShowNode clone()
		{

			return new ShowNode(this);

		}

		//setters....
		public void setLink(ShowNode link) {
			this.link = link;
		}
		public void setShow(TvShow show) {
			this.show = show;
		}

		//getters..
		public ShowNode getLink() {
			return link;
		}
		public TvShow getShow() {
			return show;
		}

		public void printNode() {
			// TODO Auto-generated method stub

		}


	}

}