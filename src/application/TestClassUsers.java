package application;
import java.io.IOException;
import java.util.Scanner;

public class TestClassUsers {
	/*												   *
	 *												   *
	 * 			  Testing the User Classes			   *
	 *	     (UserAccount, Librarian, Patron)   	   *
	 *												   *
	 *												   */
	public static void main(String[] args) throws IOException  {
		//Have test program run at the Librarian administrator level
		LibrarianData.readLibrarianFile();
		Librarian.loginAdmin("Doe", "Jane", "12345abc");
		
		//Clear ArrayLists before test begins
		LibrarianData.clearLibArrayList();
		PatronData.clearPatronArrayList();
				
		//Outline step 1: Library test.
		System.out.println(
				"STEP 1 OF TESTING: UserAccount Class & Subclasses\n\n"
				+ "a.) Read ArrayLists (2) from file. If file doesn't exist, add the default objects to each ArrayList\n"
				+ "b.) Add Librarian (give first name via Scanner, everything else precreated) \n"
				+ "c.) Create a patron(first name given), display their info\n"
				+ "d.) test loginAdmin with Patron and then Librarian\n"
				+ "e.) Add book, checkout Book\n"
				+ "f.) REMOVED\n"
				+ "g.) Write both user ArrayLists to file, clear both ArrayLists\n"
				+ "h.) Read both user ArrayLists from files, display both\n"
				+ "STEP 1 BEGINNING: \n");
		
		//a.)Read ArrayLists (2) from file. If file doesn't exist, add the default objects to each ArrayList
		System.out.print("a.) ");
		LibrarianData.readLibrarianFile();
		PatronData.readPatronFile();
		System.out.println(LibrarianData.displayLibrarianArrayList());
		System.out.println(PatronData.displayPatronArrayList());
		
		//b.) Add Librarian (give first name via Scanner, everything else precreated)
		Scanner input = new Scanner(System.in);
		System.out.println("\nb.) Please enter a first name for testing: ");
		String scannerFirstNameL = input.nextLine();
		
		Librarian testLib = new Librarian(scannerFirstNameL,"Smith","41235cde");
		LibrarianData.addLibrarian(testLib);
		
		int testLibIndex = LibrarianData.searchLibrarianIndex(testLib);
		
		System.out.println(LibrarianData.getLibAtIndex(testLibIndex).toString());
		
		//c.) Create a patron(first name given), display their info
		System.out.println("\nc.) Please enter a first name for testing: ");
		String scannerFirstNameP = input.nextLine();
		input.close();
		
		Patron testPatron = new Patron(scannerFirstNameP,"Smithson","71543ffe");
		PatronData.addPatron(testPatron);
		
		int testPatronIndex = PatronData.searchPatronIndex(testPatron);
		
		System.out.println(PatronData.getPatronAtIndex(testPatronIndex).toString());
		
		//d.) test loginAdmin with Patron and then Librarian
		Librarian.loginAdmin(scannerFirstNameP,"Smithson","71543ffe");
		System.out.println("\nd.)\nTesting with Patron login. Is program currently running at Admin level? "+Librarian.isAdmin());
		
		Librarian.loginAdmin(scannerFirstNameL,"Smith","41235cde");
		System.out.println("Testing with Admin login. Is program currently running at Admin level? "+Librarian.isAdmin());
	
		//e.) Add book, checkout Book
		Book mobyTest = new Book("Melville, Herman", "Moby-Dick; or, The Whale (Original Printing)", 
				"9781976530738", 1851, "Fiction", "Harper & Brothers", 
				"1/A","Hardcover",432,"English",1);
		LibraryData.addBook(mobyTest);
		LibraryData.checkOutBook(mobyTest, testPatron);

		System.out.println("\ne.)\nBook added and checked out");
		
		//g.) Write both user ArrayLists to file, clear both ArrayLists
		LibrarianData.writeLibrarianFile();
		PatronData.writePatronFile();
		
		LibrarianData.clearLibArrayList();
		PatronData.clearPatronArrayList();
		System.out.println("\ng.) Written and cleared both ArrayLists.");
		
		//h.) Read both user ArrayLists from files, display both
		LibrarianData.readLibrarianFile();
		PatronData.readPatronFile();
		
		System.out.println("\nReading and displaying both ArrayLists: "+LibrarianData.getLibAtIndex(0).toString());
		
		System.out.println("\nh.) Reading and displaying both ArrayLists: ");
		
		System.out.println(LibrarianData.displayLibrarianArrayList());
		System.out.println(PatronData.displayPatronArrayList());
		
	}
}
