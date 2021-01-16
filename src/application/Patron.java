package application;
import java.io.Serializable;

//This class represents the patrons of the library,
//who have lesser ability within the system

public class Patron extends UserAccount implements Serializable {
	//Create a serialVersionUID for serializing the objects stored in ArrayLists 
	private static final long serialVersionUID = 2L;
	
	//Field to use as a divider for text strings.
	private static final String DIV = "~~~";

	//Constructor
	Patron(String lastName, String firstName, String givenID) {
		super(lastName+","+firstName, givenID, lastName+","+firstName+DIV+givenID);
	}
	
	public String PatronInfoDisplay() {
		String[] nameArray = this.getName().split(",");
		String patronInfo = nameArray[1] + " " + nameArray[0] + "  |  " + this.getID();
		return patronInfo;
	}
}