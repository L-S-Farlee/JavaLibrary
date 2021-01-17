package application;
import java.io.*;

/*
This class is used to create all books in the library system,
recording their information by providing objects for storage
and manipulation
*/

public class Book implements Serializable {
	
	//Create a serialVersionUID for serializing the objects stored in ArrayLists 
	private static final long serialVersionUID = -386033525547774950L;
	
	//These 10 fields are required to construct a Book object
	private final String bookAuthor;
	private final String bookTitle;
	private final String bookID;
	private final int bookYearPub;
	private final String bookGenre;
	private final String bookPublisher;
	private final String bookLocation;
	private final String bookMediaType;
	private final int bookPageCount;
	private final String bookLanguage;
	
	/*
	These 2 fields are used to determine if the book is checked out
	and if so, which patron has checked it out.
	*/
	
	private boolean bookAvailable = true;
	private String currentHolder = "";

	//Constructor
	public Book(String author, String title, String ISBN, int yearPub,
		String genre, String publisher, String location, 
		String media, int pageNum, String language, int copyNum) {
		
		/* 
		Constructor uses if-else block to test a few conditions before creating new book.
		As a result, the temp strings below are used to prevent exceptions (you can't 
		initialize a final variable inside of an if-else block).
		*/
		String authorTemp = "";
		String bookTitleTemp = "";
		String bookIDTemp = "";
		int bookYearPubTemp = 0;
		String bookGenreTemp = "";
		String bookPublisherTemp = "";
		String bookLocationTemp = "";
		String bookMediaTypeTemp = "";
		int bookPageCountTemp = 0;
		String bookLanguageTemp = "";
		
		//Check if user logged in has correct privilege.
		if (Librarian.isAdmin()) {
			//check to ensure the book doesn't already exist.
			if (!LibraryData.searchBook(ISBN + "_" + copyNum)) {
				//if book does not exist, create book with given information.
				authorTemp = author;
				bookTitleTemp = title;
				bookIDTemp = ISBN + "_" + copyNum;
				bookYearPubTemp = yearPub;
				bookGenreTemp = genre;
				bookPublisherTemp = publisher;
				bookLocationTemp = location;
				bookMediaTypeTemp = media;
				bookPageCountTemp = pageNum;
				bookLanguageTemp = language;
			}
			else {
				System.out.println("ERROR! Book already exists.");
			}
		} else {
			System.out.println("ERROR! User cannot create books if they are not a Librarian.");
		}
		//now that temp strings have been filled (or are blank in case of error), initialize them
		this.bookAuthor = authorTemp;
		this.bookTitle = bookTitleTemp;
		this.bookID = bookIDTemp;
		this.bookYearPub = bookYearPubTemp;
		this.bookGenre = bookGenreTemp;
		this.bookPublisher = bookPublisherTemp;
		this.bookLocation = bookLocationTemp;
		this.bookMediaType = bookMediaTypeTemp;
		this.bookPageCount = bookPageCountTemp;
		this.bookLanguage = bookLanguageTemp;
	}
	
	//method to return a string based on book availability
	public String getBookStatus() {
		String tempStr = "";
		if (bookAvailable) {
			tempStr = "In";
		}
		else if (!bookAvailable) {
			tempStr = "Out";
		}
		return tempStr;
	}
	
	//Compare two Book objects by their unique bookID (ISBN + Copy #)
	public boolean equals(Book book1) {
		if (getBookID().equals(book1.getBookID())) {
			return true;
		}
		return false;
	}
	
	//A formatting override for the .toString() method
	@Override
	public String toString() {
		String tempStr ="~~/~~/~~ " + bookTitle + " | " + bookAuthor + " | " 
				+ bookID + " | " + bookYearPub + " | " + bookGenre + " | " 
				+ bookPublisher + " | " + bookLocation + " | " + bookMediaType + " | " 
				+ bookPageCount + " | " + bookLanguage;
		return tempStr;
	}
	
	//Formatting for Get Book Information window
	public String infoDisplay() {
		String tempStr = "Book Title : " + bookTitle + "\nAuthor : " + bookAuthor + "\nBook ID (ISBN_Copy#) : " 
				+ bookID + "\nYear Published : " + bookYearPub + "\nGenre : " + bookGenre + "\nPublisher : " 
				+ bookPublisher + "\nLocation (Floor/Section) : " + bookLocation + "\nMedia Type : " + bookMediaType 
				+ "\nPage Count : " + bookPageCount + "\nLanguage : " + bookLanguage + "\nStatus : " + getBookStatus() ;
		return tempStr;
	}
	
	//Formatting for Your Checked Books Information window
	public String infoShort() {
		String tempStr =  bookAuthor + " | " + bookTitle + "\n"
				+ bookID;
		return tempStr;
	}
	
	// Getter methods
	
	public String getBookAuthor() {
		return this.bookAuthor;
	}
	
	public String getBookTitle() {
		return this.bookTitle;
	}
	
	public String getBookID() {
		return bookID;
	}

	public int getYear() {
		return this.bookYearPub;
	}
	
	public String getGenre() {
		return this.bookGenre;
	}

	public String getPublisher() {
		return this.bookPublisher;
	}
	
	public String getMediaType() {
		return this.bookMediaType;
	}

	public String getBookLanguage() {
		return this.bookLanguage;
	}
	
	public int getBookPageCount() {
		return this.bookPageCount;
	}
	
	public String getHolder() {
		return currentHolder;
	}
	
	public boolean isAvailable() {
		return this.bookAvailable;
	}
	
	// Setter methods

	public void setBookAvailable(boolean b) {
		if (Librarian.isAdmin() || Librarian.isUser()) {
			bookAvailable = b;
		}
	}
	
	public void setBookHolder(String givenHolderID) {
		if (Librarian.isAdmin() || Librarian.isUser()) {
			currentHolder = givenHolderID;
		}
	}
}
