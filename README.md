# JavaLibrary
This program creates a GUI library database with patron and detailed book objects. Uses Java and JavaFX 13.

This program is a library management system. You are able to create, delete, save,
check out, and return books (as well as create, view and remove patron profiles). There 
are two kinds of Users in this system: Patrons, and Librarians.


### Librarian account:

Name: Jane Doe

UserID: 12345abc

### Patron account:

Name: John Doe

UserID: 54321cba

Experiment with both Patron and Librarian accounts to see their limits and privileges
(for example, only a librarian can add a book to the library).

JavaLibrary manages a datafile for the books in its library, and separate data files
for Patrons and Librarians. These are .dat files stored in the Data folder. If they
are deleted, default files will be made to replace them (recreating the default profiles
for Jane and John Doe, and the default 10 books).


# How to Run
There is an executable file named JavaLibrary.jar in the root directory of this project. To open the project, see the following steps:

1. Download the .zip file and unzip. This unzipped folder is the program root folder.

2. Create a command prompt for the root folder # Example: C:\Users\(Your Username Here)\Desktop\JavaLibrary-master>

3. Run the JavaLibrary.jar file with the following command (Java 13 must be installed).
> java -jar JavaLibrary.jar

4. The program window should open, and you'll be able to login with the default accounts listed above.
