package application;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

//This class handles the management of the ArrayList holding
//all Book objects in the library system
public class LibraryData {

	//This is the class variable that holds all Book objects in the Library 	
	static private ArrayList<Book> BookArrayList = new ArrayList<Book>();
	
	//This is the static variable used to check if the BookArrayList file exists
	static private File dataFileBook = new File("Data/LibraryData.dat");
	
	//Method for checking if Library's data file exists
		static boolean checkLibraryFileExists() {
			boolean fileStatus = dataFileBook.isFile();
			return fileStatus;
		}
	
	//Get object at index of ArrayList
	static Book getBookAtIndex(int i) {
		if (i < BookArrayList.size()) {
			return BookArrayList.get(i);
		}
		else {
			return null;
		}
	}
	
	//Return an int representing size of ArrayList
	static int getBookArrayListSize() {
		return BookArrayList.size();
	}
	
	//Add a new book to the ArrayList
	public static void addBook(Book book) {
		if (Librarian.isAdmin()) {
			if (!LibraryData.searchBook(book) && book.getBookID() != "") {
				BookArrayList.add(book);
			}
		}
	}
	
	//Remove item of ArrayList at given index
	public static void removeBookAtIndex(int i) {
		BookArrayList.remove(i);
	}
	
	//Replace book at given index
	public static void replaceBookAtIndex(Book book,int index) {
		BookArrayList.set(index,book);
	}
	
	//Search ArrayList to see if book already exists
	public static boolean searchBook(Book book) {
		for (int i = 0 ; i < LibraryData.getBookArrayListSize() ; i++) {
			if (book.getBookID().equals(getBookAtIndex(i).getBookID())) {
				return true;
			};
		}
		return false;
	}
	
	//Search ArrayList to find index of specific book
	public static int searchBookIndex(Book book) {
		int counter = -1;
		
		for (int i = 0 ; i < LibraryData.getBookArrayListSize() ; i++) {
			if (book.getBookID().equals(getBookAtIndex(i).getBookID())) {
				counter = i;
			};
		}
		if (counter > -1) {
			return counter;
		}
		else
			return -1;
	}
	
	//This method reads in the Library data file
	@SuppressWarnings("unchecked")
	protected static void readLibrary() throws IOException {
		//This code checks if the file already exists
		//if the answer is no, then it skips loading the ArrayList
		if (Librarian.isAdmin() || Librarian.isUser()) {
			if (dataFileBook.isFile()) {
				try {
					//These three lines are how this program
					//attempts to find and read the list of all books
				    FileInputStream inFile = new FileInputStream("Data/LibraryData.dat");
				    BufferedInputStream inBuffer = new BufferedInputStream(inFile);
				    ObjectInputStream inObj = new ObjectInputStream(inBuffer);
				    
				    BookArrayList = (ArrayList<Book>) inObj.readObject();
				    //System.out.println("READ RESULT : "+BookArrayList.toString());
				    inObj.close();
				} 
				catch (IOException e) {
				     e.printStackTrace();
				}
				catch (ClassNotFoundException e) {
				     e.printStackTrace();
				}
			}
			else {
				System.out.println("DATA FILE READ FAILED: No data file found for Library class. Continuing without reading.");
				//If no data file found, add the default 10 books to BookArrayList.
				LibraryData.addBook(new Book("Melville, Herman", "Moby-Dick; or, The Whale", 
						"9781976530739", 1851, "Fiction", "Harper & Brothers", 
						"1/A","Hardcover",432,"English",1));
				LibraryData.addBook(new Book("Melville, Herman","Redburn", 
						"9781707919390", 1849, "Fiction", "Harper & Brothers", 
						"1/A","Hardcover",357,"English",1));
				LibraryData.addBook(new Book("Melville, Herman","Pierre; or, The Ambiguities", 
						"9781976105852", 1852, "Fiction", "Harper & Brothers", 
						"1/A","Hardcover",561,"English",1));
				LibraryData.addBook(new Book("Melville, Herman", "Moby-Dick; or, The Whale (Original Printing)", 
						"9781976530738", 1851, "Fiction", "Harper & Brothers", 
						"1/A","Hardcover",432,"English",1));
				LibraryData.addBook(new Book("Owen, Wilfred", "Poems", 
						"9781455404490", 1920, "Poetry", "Chatto and Windus", 
						"1/B", "Paperback", 192, "English", 1));
				LibraryData.addBook(new Book("O'Connor, Flannery", "Wise Blood", 
						"9780374505844", 1952, "Fiction", "Harcourt", 
						"1/A", "Paperback", 238, "English", 1));
				LibraryData.addBook(new Book("Marquez, Gabriel", "One Hundred Years of Solitude", 
						"9789631420494", 1967, "Fiction", "Harper & Row", 
						"1/A", "Hardcover", 345,"English",1));
				LibraryData.addBook(new Book("Woolf, Virginia", "The Waves", 
						"9788308040898", 1931, "Fiction", "Hogarth Press", 
						"1/A", "Hardcover", 324, "English", 1));
				LibraryData.addBook(new Book("Gibran, Kahlil", "The Prophet", 
						"9781484953297", 1923, "Poetry", "Alfred A. Knopf", 
						"1/B", "Hardcover", 107, "English", 1));
				LibraryData.addBook(new Book("Gibran, Kahlil", "The Prophet", 
						"9781484953297", 1923, "Poetry", "Alfred A. Knopf", 
						"1/B", "Hardcover", 107, "English", 2));
			}
		}
	}
	
	//This method writes to the Library data file
	protected static void writeLibrary() throws IOException {
		//This code creates a new .dat file with the contents of BookArrayList
		if (Librarian.isAdmin() || Librarian.isUser()) {
			try {
				FileOutputStream outFile = new FileOutputStream("Data/LibraryData.dat");
				BufferedOutputStream outBuffer = new BufferedOutputStream(outFile);
				ObjectOutputStream outObj = new ObjectOutputStream(outBuffer);
				outObj.writeObject(BookArrayList);
				outObj.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	//Reset the ArrayList, using .clear() to wipe all elements
	public static void clearBookArrayList() {
		if (Librarian.isAdmin()) {
			BookArrayList.clear();
		}
	}

	//Give ArrayList in full as a String separate line-by-line
	public static String displayBookArrayList() {
		String temp = "";
		
		for (int i = 0 ; i < LibraryData.getBookArrayListSize() ; i++) {
			temp += "\n"+getBookAtIndex(i).toString();
		}
		
		return temp;
	}
	
	//Method for sorting the BookArrayList, uses BookComparator class
	public static void sortBookArrayList() {
		Collections.sort(BookArrayList, new BookComparator());
		
	}

	public static void checkOutBook(Book b, UserAccount u) {
		if (b.isAvailable() & Librarian.isUser()) {
			b.setBookAvailable(false);
			b.setBookHolder(u.getHolderID());
		}
	}
}

