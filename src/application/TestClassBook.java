package application;
import java.io.IOException;

public class TestClassBook {
	/*												   *
	 *												   *
	 * 			   Testing the Book Class 		       *
	 * 												   *
	 *												   */
	public static void main(String[] args) throws IOException  {
		//Have test program run at the Librarian administrator level
		LibrarianData.readLibrarianFile();
		Librarian.loginAdmin("Doe", "Jane", "12345abc");
				
		//Outline step 1: Library test.
		System.out.println(
				"STEP 1 OF TESTING: Book Class\n\n"
				+ "a.) Create a Book object ((and then add to BookArrayList in LibraryData Class)\n"
				+ "b.) Get the value of the Book object's Author, Title, Language, and PageCount\n"
				+ "c.) Get the Book's ID\n"
				+ "d.) Check Book's status (checked out / available) \n"
				+ "e.) Set status and holder, display new status and holder \n"
				+ "f.) Save BookArrayList to file via Library class, void current BookArrayList\n"
				+ "g.) Read BookArrayList from file, display created Book object in full toString() form. Display status and holder.\n"
				+ "h.) Clear BookArrayList.\n"
				+ "STEP 1 BEGINNING: \n");
		
		//a.) Create a Book object (and then add to BookArrayList in LibraryData Class)
		Book testingBookClass = new Book("Melville, Herman", "Moby-Dick; or, The Whale (Original Printing)", 
				"9781976530738", 1851, "Fiction", "Harper & Brothers", 
				"1/A","Hardcover",432,"English",1);
		LibraryData.addBook(testingBookClass);
		System.out.println("a.) Book created.\n");
		
		//b.) Get the value of the Book object's Author, Title, Language, and PageCount
		String testAuthor = testingBookClass.getBookAuthor();
		String testTitle = testingBookClass.getBookTitle();
		String testLanguage = testingBookClass.getBookLanguage();
		int testPages = testingBookClass.getBookPageCount();
		System.out.println("b.)\nAuthor: "+testAuthor+"\nTitle: "+testTitle+"\nLanguage: "+testLanguage+"\nPage Count: "+testPages);
		
		//c.) Get the Book's ID
		String testID = testingBookClass.getBookID();
		System.out.println("\nc.) Book ID is: "+testID+"\n");
		
		//d.) Check Book's status (checked out / available)
		Boolean testAvailable = testingBookClass.isAvailable();
		System.out.println("d.) Is the book available? "+testAvailable+"\n");
		
		//e.) Set status and holder, display new status and holder
		testingBookClass.setBookAvailable(false);
		testingBookClass.setBookHolder("Doe,John~~~54321cba");
		testAvailable = testingBookClass.isAvailable();
		System.out.println("e.) Is the book available? "+testAvailable+"");
		String testHolder = testingBookClass.getHolder();
		System.out.println("Who is holding the book? "+testHolder+"\n");
		
		//f.) Save BookArrayList to file via Library class, void current BookArrayList
		LibraryData.writeLibrary();
		LibraryData.clearBookArrayList();
		System.out.println("f.) Book added. BookArrayList cleared.\n");
		
		//g.) Read BookArrayList from file, display created Book object in full toString() form. Display status and holder.
		System.out.println("g.) The book added to the file is: \n");
		LibraryData.readLibrary();
		
		System.out.println(LibraryData.getBookArrayListSize());
		int indexFinder = LibraryData.searchBookIndex(testingBookClass);
		
		if (testingBookClass.equals(LibraryData.getBookAtIndex(indexFinder))) {
			System.out.println(LibraryData.getBookAtIndex(indexFinder).toString()+"\n");
		}
		
		System.out.println("Is the book available? "+LibraryData.getBookAtIndex(indexFinder).isAvailable()
				+". Who currently holds the book? "+LibraryData.getBookAtIndex(indexFinder).getHolder());
		
		//h.) Clear BookArrayList.
		System.out.println("\nh.) BookArrayList cleared.");
		LibraryData.clearBookArrayList();
	}
}
