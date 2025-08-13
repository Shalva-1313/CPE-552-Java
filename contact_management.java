import java.util.Scanner;

//By: Shasha Alvares

public class contact_management {

	public class Contact
	{
		protected String firstName;
		protected String lastName;
		protected String phone;
		protected String email;
		protected Contact next;			//pointer that points to next Contact
		
		public Contact(String fname, String lname, String phonenum, String emailad)
		{
			this.firstName = fname;
			this.lastName = lname;
			this.phone = phonenum;
			this.email = emailad;
			this.next = null;
		}
	}
	
	public Contact createAndAddContact(Contact head)
	{
		/*
		 * This function should create a new contact, prompt the user to input contact details (First Name, Last Name, Phone Number, Email Address), 
		 * and add it to the singly linked list. Return the new head of the linked list.
		 */
		Scanner scnr = new Scanner(System.in);
		System.out.println("Enter First Name: ");
		String firstName = scnr.nextLine();
		
		System.out.println("Enter Last Name: ");
		String lastName = scnr.nextLine();
		
		System.out.println("Enter Phone Number: ");
		String phone = scnr.nextLine();
		
		System.out.println("Enter Email Address: ");
		String email = scnr.nextLine();
		
		System.out.println("Contact added successfully.");
		
		//Create new contact to linked list
		Contact newContact = new Contact(firstName, lastName, phone, email);
		
		//Add new contact to head of list
		newContact.next = head; //new contacts pointer points to the current head of list
		head = newContact; //head is updated to be the new contact and new contact become the first element in the list
		
		return head;
	}
	
	public static void displayContacts(Contact head)
	{
		/*
		 * This function should display all contacts in the singly linked list, including their First Name, Last Name, Phone Number, and Email Address.
		 */
		Contact current = head;
		while(current != null) //iterate to the end of the contact list 
		{
			System.out.println("First name: " + current.firstName);
			System.out.println("Last name: "+ current.lastName);
			System.out.println("Phone number: " + current.phone);
			System.out.println("Email Address: " + current.email);
			System.out.println("-------------------");
			
			current=current.next; //moves on to next contact to display
		}
	}
	public static void searchContact(Contact head, String name)
	{
		/* 
		 * This function should search for a contact by First Name or Last Name and display their contact details if found.
		 */
		Contact current = head;
		boolean found = false;
		
		while(current != null) //iterate through entire linked list
		{
			if (current.firstName.equalsIgnoreCase(name) || current.lastName.equalsIgnoreCase(name)) //check first and last names for a match in either
			{
				System.out.println("Contact found: ");
				System.out.println("First name: " + current.firstName);
				System.out.println("Last name: "+ current.lastName);
				System.out.println("Phone number: " + current.phone);
				System.out.println("Email Address: " + current.email);
				break; //exit loop after finding contact
			}
			current = current.next; //go to next contact
		}
		
		//iterated through entire contact list and contact not found
		if(!found)
		{
			System.out.println("Contact not found");
		}
	}
	
	

	public static void main(String[] args) 
	{
		
		Scanner scnr = new Scanner(System.in);
		contact_management manager = new contact_management(); //create object of contact_management class
		contact_management.Contact headNode = null; //headNode points to the first element of the empty linked list 
		
		boolean keepGoing = true;

		while(keepGoing)
		{
			System.out.println("1. Add Contact\n"
					+ "2. Display Contacts\n"
					+ "3. Search Contact\n"
					+ "4. Exit");
			String userChoice = scnr.nextLine();
			
			if(userChoice.equals("1"))
			{
				headNode = manager.createAndAddContact(headNode);
			}
			else if(userChoice.equals("2"))
			{
				manager.displayContacts(headNode);
			}
			else if(userChoice.equals("3"))
			{
				System.out.println("Enter the name to search for: ");
				String nameToSearch = scnr.nextLine();
				manager.searchContact(headNode, nameToSearch);
			}
			else if(userChoice.equals("4"))
			{
				scnr.close();
				keepGoing = false;
				break;
			}
			else
			{
				System.out.println("Invalid input - enter a choice from 1-4.");
			}
		}
	}

}
