package application;
import java.io.Serializable;

//This class represents the librarians,
//who have greater ability within the system
//including creating and removing users and books

public class Librarian extends UserAccount implements Serializable {
	//Create a serialVersionUID for serializing the objects stored in ArrayLists 
	private static final long serialVersionUID = 2L;
	
	//Field to use as a divider for text strings.
	private static final String DIV = "~~~";
	
	//This field is used to see if current user has Admin privileges
	private static boolean isAdmin = false;
	
	//This field is used to see if current user exists (for login attempts)
	private static boolean isUser = false;
	
	//Constructor
	Librarian(String lastName, String firstName, String givenID) {
		super(lastName+","+firstName, givenID, lastName+","+firstName+DIV+givenID);
	}
	
	//Given strings representing login info, this method
	//determines if the user's login info is valid for Admin privileges
	public static void loginAdmin(String lastName, String firstName, String givenID) {
		String tempName = lastName+","+firstName;
		
		for (int i = 0 ; i < LibrarianData.getLibArrayListSize() ; i++) {
			
			if ((tempName+DIV+givenID).equals(LibrarianData.getLibAtIndex(i).getHolderID())) {
				isAdmin = true;
				isUser = true;
				return;
			}
		}
		//If not an admin, check to see if Patron
		for (int i = 0 ; i < PatronData.getPatronArrayListSize() ; i++) {
			
			if ((tempName+DIV+givenID).equals(PatronData.getPatronAtIndex(i).getHolderID())) {
				isAdmin = false;
				isUser = true;
				return;
			}
		}
		//If the two searches fail, nothing.
		isAdmin = false;
		isUser = false;
	}
	
	//To be used when switching from Librarian to Patron
	public static void logoutAdmin() {
		isAdmin = false;
	}
	
	//field getter
	public static boolean isAdmin() {
		return isAdmin;
	}
	
	//field getter
	public static boolean isUser() {
		return isUser;
	}
}
