import java.util.Scanner;

//By: Shasha Alvares

public class detect_cycle {
	
	public static class linkedList{
		private Node head;
		
		//node constructor  
		public class Node{
			int data;
			Node next;
			
			public Node(int data){
				this.data = data;
				this.next = null;
			}
		}
		
		public void createList(int nodes, Scanner scnr)
		{
			if(nodes<=0)
			{
				System.out.println("No nodes to create a linked list.");
				return;
			}
			
			//adds the first value to the empty list bc it is easier to do this outside a loop. 
			//inside a loop we would have to check for if the head is empty each iteration.
			System.out.print("Enter the values of the nodes: ");
			int value = scnr.nextInt();
			head = new Node(value); //creates the first node of the linkedlist 
			Node current = head; //creates current node which initially points to the head to help keep adding new nodes. 

			//adds the rest of the values to the list
			for (int i = 1; i < nodes; i++) {
			    value = scnr.nextInt(); //scans for user value
			    Node newNode = new Node(value); //create new node with user value
			    current.next = newNode; //adds new node to the list by pointing the tail of the last added node to this newly created one
			    current = newNode; //updates current pointer to newly added node which is at the end of the list
			}
			
		}
		
		public void createCycle(int position)
		{
			if(position == -1)
			{
				return; //nothing to do since user does not want a cycle to be created
			}
			
			Node cycleNode = null; //will eventually store the node we will create the cycle on
	        Node current = head; //start traversal at the head of the list
	        int index = 0; //keeps track of current nodes position

	        //the while loop traverses the entire list up until the last value, and at the end of the loop the current pointer 
	        //is pointing to the tail value. if the position is found while traversing, then that node is stored in cycleNode, 
	        //otherwise cycleNode remains null.
	        while (current.next != null) {
	            if (index == position) {
	                cycleNode = current;
	            }
	            current = current.next;
	            index++;
	        }
	        
	        //current is pointing to the tail of the list, and that is why we can create the loop by pointing it to 
	        //cycleNode where we stored the node at the position (if it exists) indicated by the user
	        if (cycleNode != null) {
	            current.next = cycleNode; // Create cycle
	        } else {
	            System.out.println("Invalid position; no cycle created.");
	        }
		}
		
		public Node detectCycleStart()
		{
			Node fastPointer = head;
			Node slowPointer = head;
			
			//keep iterating through the linkedList, unless we reach the end (which means there is no cycle)
			while(fastPointer != null && fastPointer.next != null)
			{
				//if fast and slow pointer meet, where fast pointer is one ahead of slow pointer, there is a cycle. 
				slowPointer = slowPointer.next;
				fastPointer = fastPointer.next.next;
			
				if(slowPointer == fastPointer)
				{
					//cycle detected so we will return the starting node
					//starting node is the node where fastPointer and slowPointer meet when both move by one node 
					//at a time and slowPointer starts from the head
					slowPointer = head;
			        while (slowPointer != fastPointer) {
			        	slowPointer = slowPointer.next;
			        	fastPointer = fastPointer.next;
			        }

			        return slowPointer; //start node for cycle has been found
				}
			}
			return null; //No cycle detected
		}
	
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scnr = new Scanner(System.in);
		linkedList ll = new linkedList();
		
		//get number of nodes in the linkedList
		System.out.println("Enter the number of nodes in the linked list: ");
		int numNodes = scnr.nextInt();
		if(numNodes == 0)
		{
			System.out.println("No nodes to create a linked list.");
			return;
		}

		//create linkedList
		ll.createList(numNodes, scnr);
		
		//get position from user the LinkedList will cycle from to create the cycle
		System.out.print("Enter the position to create a cycle (-1 for no cycle): ");
        int position = scnr.nextInt();
        ll.createCycle(position);
        
        //if there is a cycle, output what node it is occurring on
        //ll.detectCycleStart() returns the slowPointer or null. if slowPointer is returned we get the node bc 
        //ll.detectCycleStart().data is the evaluated as slowPointer.data
        if (ll.detectCycleStart() != null) {
            System.out.println("Cycle detected. Cycle starts at node with value: " + ll.detectCycleStart().data);
        } else {
            System.out.println("No cycle detected.");
        }

        scnr.close();
		
	}

}