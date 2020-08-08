package application;
import java.io.Serializable;

//This class is the abstract superclass for Library and Patron
public abstract class UserAccount implements Serializable {
	//Create a serialVersionUID for serializing the objects stored in ArrayLists 
	private static final long serialVersionUID = 7973319658373072468L;
	
	//Fields for use in subclasses
	protected String name = "";
	protected String ID = "";
	protected String holderID = name + "~~~" + ID;
	
	//field getter
	public String getName() {
		return name;
	}
	
	//field getter
	public String getID() {
		return ID;
	}
	
	//field getter
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
