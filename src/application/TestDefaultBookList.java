package application;

import java.io.IOException;

public class TestDefaultBookList {

	public static void main(String[] args) throws IOException {
		//Login as default librarian
		LibrarianData.readLibrarianFile();
		Librarian.loginAdmin("Doe", "Jane", "12345abc");
		System.out.println(Librarian.isAdmin());
	
		//Read in LibraryData file
		try {
		LibraryData.readLibrary();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println(LibraryData.getBookArrayListSize());
		
		//Add books to Library
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
		
		
		
		System.out.println(LibraryData.getBookArrayListSize());
		
		//Sort Library
		LibraryData.sortBookArrayList("author");
		
		//Print out the new BookArrayList
		System.out.println("The BookArrayList: \n"+LibraryData.displayBookArrayList().toString()+"\n");
		
		//Print out some strings in the style of the ListView
		for (int i = 0; i < LibraryData.getBookArrayListSize() ; i++) {
			Book tempBook = LibraryData.getBookAtIndex(i);
			System.out.println(tempBook.getBookAuthor() + " | " + tempBook.getBookTitle() 
				+ " | " + tempBook.getYear() + " | " + tempBook.getGenre() 
				+ " | " + tempBook.getMediaType() + "\n");
		}
		
		//Write to file
		LibraryData.writeLibrary();
	}
}
