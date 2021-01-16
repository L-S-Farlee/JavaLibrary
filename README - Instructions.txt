JavaLibrary

This program is a library management system. You are able to create, delete, save,
check out, and return books (as well as create, view and remove patron profiles). There 
are two kinds of Users in this system: Patrons, and Librarians. There is one default 
Librarian account:

Name: Jane Doe
UserID: 12345abc

There is also one default Patron account:

Name: John Doe
UserID: 54321cba

Experiment with both Patron and Librarian accounts to see their limits and privileges
(for example only a librarian can add a book to the library).

JavaLibrary manages a datafile for the books in its library, and separate data files
for Patrons and Librarians. These are .dat files stored in the Data folder. If they
are deleted, default files will be made to replace them (recreating the default profiles
for Jane and John Doe, and the default 10 books).