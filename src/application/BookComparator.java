package application;

//This class is a comparator for Book objects so that we can sort the BookArrayList
class BookComparator implements java.util.Comparator<Book> {
	public int compare(Book b1, Book b2) {
        String b1Author = b1.getBookAuthor();
        String b2Author = b2.getBookAuthor();
        //uses compareTo method of String class to compare author names
        return b1Author.compareTo(b2Author);
    }
}
