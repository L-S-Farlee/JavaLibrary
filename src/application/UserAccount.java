package application;
import java.io.Serializable;

//This class is the abstract superclass for Library and Patron
public abstract class UserAccount implements Serializable {
	//Create a serialVersionUID for serializing the objects stored in ArrayLists 
	private static final long serialVersionUID = 2L;
	
	//Field to use as a divider for text strings.
	private static final String DIV = "~~~";
	
	//Fields for use in subclasses
	protected String name = "";
	protected String ID = "";
	protected String holderID = name + DIV + ID;
	
	//Constructor
	public UserAccount (String name, String ID, String holderID) {
		this.name = name;
		this.ID = ID;
		this.holderID = holderID;
	}
	
	//field getters
	public String getName() {
		return name;
	}
	
	public String getID() {
		return ID;
	}

	public String getHolderID() {
		return holderID;
	}
	
	//A formatting override for the .toString() method
	@Override
	public String toString() {
		return holderID;
	}
	//Return a book, reset relevant fields
	protected void returnCheckedBook(Book b) {
		if (!b.isAvailable() && Librarian.isUser()) {
			b.setBookAvailable(true);
			b.setBookHolder("");
		}
	}
}
