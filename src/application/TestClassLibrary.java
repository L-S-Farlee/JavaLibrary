package application;
import java.io.*;

//This is a class designed to test my created classes
//without developing any GUI yet. It will outline each of 
//its steps, providing feedback on each step taken.
public class TestClassLibrary {
	/*												   *
	 *												   *
	 * 			 Testing the LibraryData Class 	   	   *
	 * 												   *
	 *												   */
	public static void main(String[] args) throws IOException  {
		//Have test program run at the Librarian administrator level
		LibrarianData.readLibrarianFile();
		Librarian.loginAdmin("Doe", "Jane", "12345abc");
		
		//Outline step 1: Library test.
		System.out.println(
				"STEP 1 OF TESTING: Library Class\n\n"
				+ "a.) Determine if ArrayList's data file already exists\n"
				+ "b.) Attempt to read ArrayList, \n"
				+ "c.) IF NO ARRAYLIST: Add three books to ArrayList, then display the current size of the ArrayList,\n"
				+ "d.) List the elements of the ArrayList.\n"
				+ "e.) Write ArrayList to file, check if file exists\n"
				+ "f.) Delete ArrayList element at index 0, display resulting ArrayList change\n"
				+ "g.) Read ArrayList from file, display fixed ArrayList from reading file\n"
				+ "h.) Attempt to add a book that is already in the ArrayList. Then clear the ArrayList.\n\n"
				+ "STEP 1 BEGINNING: \n");
		
		//a.) Determine if ArrayList's data file already exists
		System.out.println("a.) Does the libraryData file exist? The answer is: "+LibraryData.checkLibraryFileExists()+".\n");
		
		//b.) Attempt to read ArrayList
		LibraryData.readLibrary();
		System.out.println("b.) ArrayList read.");
		
		//c.) IF NO ARRAYLIST: Add three books to ArrayList, then display the current size of the ArrayList
		if (!LibraryData.checkLibraryFileExists() && LibraryData.getBookArrayListSize() > 2) {
			LibraryData.replaceBookAtIndex(new Book("Melville, Herman", "Moby-Dick; or, The Whale", 
						"9781976530739", 1851, "Fiction", "Harper & Brothers", 
						"1/A","Hardcover",432,"English",1),0);
			LibraryData.replaceBookAtIndex(new Book("Melville, Herman","Redburn", 
						"9781707919390", 1849, "Fiction", "Harper & Brothers", 
						"1/A","Hardcover",357,"English",1),1);
			LibraryData.replaceBookAtIndex(new Book("Melville, Herman","Pierre; or, The Ambiguities", 
						"9781976105852", 1852, "Fiction", "Harper & Brothers", 
						"1/A","Hardcover",561,"English",1),2);
		}
		else if (LibraryData.getBookArrayListSize() < 2) {
			LibraryData.addBook(new Book("Melville, Herman", "Moby-Dick; or, The Whale", 
					"9781976530739", 1851, "Fiction", "Harper & Brothers", 
					"1/A","Hardcover",432,"English",1));
			LibraryData.addBook(new Book("Melville, Herman","Redburn", 
					"9781707919390", 1849, "Fiction", "Harper & Brothers", 
					"1/A","Hardcover",357,"English",1));
			LibraryData.addBook(new Book("Melville, Herman","Pierre; or, The Ambiguities", 
					"9781976105852", 1852, "Fiction", "Harper & Brothers", 
					"1/A","Hardcover",561,"English",1));
		}
		
		System.out.println("\nc.) The size of BookArrayList is: "+LibraryData.getBookArrayListSize()+"\n");
		
		//d.) List the elements of the ArrayList.
		System.out.println("d.) Elements of ArrayList include:");
		System.out.println(LibraryData.displayBookArrayList());
		
		//e.) Write ArrayList to file, check if file exists
		
		LibraryData.writeLibrary();
		LibraryData.checkLibraryFileExists();
		
		System.out.println("\ne.) Does the libraryData file exist? The answer is: "+LibraryData.checkLibraryFileExists()+".\n");
		
		//f.) Delete ArrayList element at index 0, display resulting ArrayList change
		if (LibraryData.getBookArrayListSize() > 0) {
			LibraryData.removeBookAtIndex(0);
		}
		
		System.out.print("f.) The elements of ArrayList are now:\n");
		System.out.println(LibraryData.displayBookArrayList());
		
		//g.) Read ArrayList from file, display fixed ArrayList from reading file
		System.out.print("\ng.) ");
		LibraryData.readLibrary();
		
		System.out.print("After reloading with the data file, the elements of ArrayList are returned to:\n");
		System.out.println(LibraryData.displayBookArrayList());
		
		//h.) Attempt to add a book that is already in the ArrayList. then clear the ArrayList.
		System.out.println("\nh.) Does the book 'Moby-Dick; or, The Whale' already exist? "
					+LibraryData.searchBook(new Book("Moby-Dick; or, The Whale", "Melville, Herman", 
						"9781976530739", 1851, "Fiction", "Harper & Brothers", 
						"1/A","Hardcover",432,"English",1)));
		
		System.out.println(LibraryData.displayBookArrayList());
		LibraryData.writeLibrary();
		
		//LibraryData.clearBookArrayList();
		
		//System.out.println("\nBookArrayList cleared.");
	}
}
