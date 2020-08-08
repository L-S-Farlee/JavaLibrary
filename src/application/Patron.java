package application;
import java.io.Serializable;

//This class represents the patrons of the library,
//who have lesser ability within the system

public class Patron extends UserAccount implements Serializable {
	//Create a serialVersionUID for serializing the objects stored in ArrayLists 
	private static final long serialVersionUID = -3169606166597312778L;

	//Constructor
	Patron(String lastName, String firstName, String givenID) {
		name = lastName+","+firstName;
		ID = givenID;
		holderID = lastName+","+firstName+"~~~"+ID;
	}
	
	public String PatronInfoDisplay() {
		String[] nameArray = this.getName().split(",");
		String patronInfo = nameArray[1] + " " + nameArray[0] + "  |  " + this.getID();
		return patronInfo;
	}

	
}