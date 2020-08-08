package application;
import java.io.*;

//This class is used to create all books in the library system,
//recording their information by providing objects for storage
//and manipulation
public class Book implements Serializable {
	
	//Create a serialVersionUID for serializing the objects stored in ArrayLists 
	private static final long serialVersionUID = -386033525547774950L;
	
	//These 10 fields are required to construct a Book object
	private String bookAuthor = "";
	private String bookTitle = "";
	private String bookID = "";
	private int bookYearPub = 0;
	private String bookGenre = "";
	private String bookPublisher = "";
	private String bookLocation = "";
	private String bookMediaType = "";
	private int bookPageCount = 0;
	private String bookLanguage = "";
	
	//These 2 fields are used to determine if the book is checked out
	//and if so, which patron has checked it out
	private boolean bookAvailable = true;
	private String currentHolder = "";

	//Constructor
	public Book(String author, String title, String ISBN, int yearPub,
					String genre, String publisher, String location, 
					String media, int pageNum, String language,int copyNum) {
		if (Librarian.isAdmin()) {
			if (!LibraryData.searchBook(this)) {
				this.bookAuthor = author;
				this.bookTitle = title;
				this.bookID = ISBN+"_"+copyNum;
				this.bookYearPub = yearPub;
				this.bookGenre = genre;
				this.bookPublisher = publisher;
				this.bookLocation = location;
				this.bookMediaType = media;
				this.bookPageCount = pageNum;
				this.bookLanguage = language;
			}
			else {
				System.out.println("ERROR! Book already exists.");
			}
		}
		else if (author.equals("")) {
			System.out.println("ERROR! Book has no listed author.");
		}
	}
	
	//field getter
	public String getBookID() {
		return bookID;
	}
	
	//Compare two Book objects by their unique bookID
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

	//field getter
	public String getBookAuthor() {
		return this.bookAuthor;
	}
	
	//field getter
	public String getBookTitle() {
		return this.bookTitle;
	}
	
	//field getter
	public String getBookLanguage() {
		return this.bookLanguage;
	}
	
	//field getter
	public int getBookPageCount() {
		return this.bookPageCount;
	}
	
	//field getter
	public String getHolder() {
		return currentHolder;
	}
	
	//field getter
	public boolean isAvailable() {
		return this.bookAvailable;
	}

	//field setter
	public void setBookAvailable(boolean b) {
		if (Librarian.isAdmin() || Librarian.isUser()) {
			bookAvailable = b;
		}
	}
	
	//field setter
	public void setBookHolder(String givenHolderID) {
		if (Librarian.isAdmin() || Librarian.isUser()) {
			currentHolder = givenHolderID;
		}
	}

	//field getter
	public int getYear() {
		return this.bookYearPub;
	}

	//field getter
	public String getMediaType() {
		return this.bookMediaType;
	}

	//field getter
	public String getGenre() {
		return this.bookGenre;
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
}
