package application;

import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/*


This is the JavaFX class used to generate the user GUI for this library database application.

*/

public class MainForm extends Application {
	//Create the class fields
	//This is the ListView for the listing of all books
	private static ListView<String> bookListView;
	//These are the buttons in the controls for patrons
	private static Button btBookInfo;
	private static Button btCheckOutBook;
	private static Button btReturnBook;
	private static Button btCheckedBooks;
	//These are the buttons for the admin controls
	private static Button btAddBook;
	private static Button btRemoveBook;
	private static Button btAddPatron;
	private static Button btRemovePatron;
	private static Button btPatronInfo;
	
	//User object currently logged in
	Librarian userLibrarian;
	Patron userPatron;
	
	//User name for window display
	public static String currentUserName = "";
	
	//Start method
	@Override
	public void start(Stage primaryStage) {
		LoginForm();
	}
	
	//Main method
	public static void main(String[] args) {
		launch(args);
	}
	
	//A method to generate primaryForm post-login 
	public void PrimaryForm() throws IOException {
		
		//These are the declarations of the JavaFx elements created above
		//declaration must be done here or otherwise there is a .jar creation error with JavaFX
		bookListView = new ListView<String>();
		btBookInfo = new Button("Get Book Information");
		btCheckOutBook = new Button("Check Out Book");
		btReturnBook = new Button("Return Book");
		btCheckedBooks = new Button("Your Checked Out Books");
		btAddBook = new Button("Add Book");
		btRemoveBook = new Button("Remove Book");
		btAddPatron = new Button("Add Patron");
		btRemovePatron = new Button("Remove Patron");
		btPatronInfo = new Button("Patron Info");
		
		//Create a pane for the UI
		BorderPane borderPane = new BorderPane();
		borderPane.setPrefSize(900, 700);
		
		//Create a subpane for the patron controls
		HBox bookControls = new HBox();
		bookControls.getChildren().addAll(btBookInfo,btCheckOutBook,btReturnBook,btCheckedBooks);
		btBookInfo.setPrefSize(175,75);
		btCheckOutBook.setPrefSize(175,75);
		btReturnBook.setPrefSize(175,75);
		btCheckedBooks.setPrefSize(175,75);
		bookControls.setAlignment(Pos.BASELINE_CENTER);
		bookControls.setSpacing(50);
		bookControls.setPadding(new Insets(75,25,75,25));
		
		//Create a subpane for the adminUserControls
		VBox adminUserControls = new VBox();
		adminUserControls.getChildren().addAll(btAddPatron,btRemovePatron,btPatronInfo);
		btAddPatron.setMinSize(100, 60);
		btRemovePatron.setMinSize(100, 60);
		btPatronInfo.setMinSize(100, 60);
		adminUserControls.setAlignment(Pos.TOP_CENTER);
		adminUserControls.setSpacing(35);
		adminUserControls.setPadding(new Insets(35,30,35,30));
		
		//Create a subpane for the adminBookControls
		VBox adminBookControls = new VBox();
		adminBookControls.getChildren().addAll(btAddBook,btRemoveBook);
		btAddBook.setMinSize(100, 80);
		btRemoveBook.setMinSize(100, 80);
		adminBookControls.setAlignment(Pos.TOP_CENTER);
		adminBookControls.setSpacing(35);
		adminBookControls.setPadding(new Insets(35,30,35,30));
		
		//Create rules to disable and hide admin controls for patrons
		if (!Librarian.isAdmin()) {
			btAddPatron.setDisable(true);
			btRemovePatron.setDisable(true);
			btPatronInfo.setDisable(true);
			btAddBook.setDisable(true);
			btRemoveBook.setDisable(true);
			btAddPatron.setVisible(false);
			btRemovePatron.setVisible(false);
			btPatronInfo.setVisible(false);
			btAddBook.setVisible(false);
			btRemoveBook.setVisible(false);
		}
		
		//Create a header with current user name
		HBox header = new HBox();
		header.getChildren().addAll(
				new Label("Welcome to the JavaLibrary application! \n\nYou are currently logged in as : " + currentUserName)
				);
		header.setPadding(new Insets(60,30,30,30));
		header.setAlignment(Pos.CENTER);
		
		//Assign subpanes
		borderPane.setTop(header);
		borderPane.setBottom(bookControls);
		borderPane.setCenter(bookListView);
		borderPane.setRight(adminUserControls);
		borderPane.setLeft(adminBookControls);
		bookListView.setMaxSize(550,450);
		bookListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		//borderPane.setMargin(bookListView, new Insets(25,25,25,25));
		
		//Fill ListView with BookArrayList
		if (Librarian.isAdmin() || Librarian.isUser()) {
			fillBookListView();
		}
		
		//Create scene, place in stage
		Scene LibraryCatalog = new Scene(borderPane);
		Stage primaryStage = new Stage();
		primaryStage.setTitle("LibraryCatalog");
		primaryStage.setScene(LibraryCatalog);
		primaryStage.show();
		
		//Event handler for btCheckOutBook
		EventHandler<ActionEvent> checkOutBook = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) { 
				checkOutBook();
			}
		};
		
		//Event handler for btReturnBook
		EventHandler<ActionEvent> returnBook = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) { 
				returnBook();
			}
		};
		
		//Event handler for btBookInfo
		EventHandler<ActionEvent> bookInfo = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) { 
				getBookInfo();
			}
		};
		
		//Event handler for btBookInfo
		EventHandler<ActionEvent> getCheckedBooks = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) { 
				getCheckedBooks();
			}
		};
		
		//Event handler for btBookInfo
		EventHandler<ActionEvent> addBook = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) { 
				if (Librarian.isAdmin()) {
					try {
						AddBookForm();
					} catch (Exception eAddBook) {
						eAddBook.printStackTrace();
					}
				}			
			}
		};
		
		//Event handler for btBookInfo
		EventHandler<ActionEvent> removeBook = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) { 
				//Create an alert
				Alert a = new Alert(AlertType.INFORMATION);
				//get the selected ListView
				int index = bookListView.getSelectionModel().getSelectedIndex();
				
				if (Librarian.isAdmin()) {
					if (index != -1 && index < LibraryData.getBookArrayListSize())
					LibraryData.removeBookAtIndex(index);
					try {
						LibraryData.writeLibrary();
						fillBookListView();
						//set alert type 
				        a.setAlertType(AlertType.CONFIRMATION); 
				        //set content text 
				        a.setContentText("Book successfully removed!");
				        //show the dialog 
				        a.show();
					} catch (IOException eRemoveBook) {
						eRemoveBook.printStackTrace();
					}
				}
			}
		};
		
		//Event handler for btBookInfo
		EventHandler<ActionEvent> addPatron = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) { 
				AddPatronForm();
			}
		};
				
		//Event handler for btBookInfo
		EventHandler<ActionEvent> removePatron = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) { 
				RemovePatronForm();
			}
		};
		
		//Event handler for btBookInfo
		EventHandler<ActionEvent> infoPatron = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) { 
				PatronInfoForm();
			}
		};
		
		//Assign eventhandlers to buttons
		btCheckOutBook.setOnAction(checkOutBook);
		btReturnBook.setOnAction(returnBook);
		btBookInfo.setOnAction(bookInfo);
		btCheckedBooks.setOnAction(getCheckedBooks);
		btAddBook.setOnAction(addBook);
		btRemoveBook.setOnAction(removeBook);
		btAddPatron.setOnAction(addPatron);
		btRemovePatron.setOnAction(removePatron);
		btPatronInfo.setOnAction(infoPatron);
		
		primaryStage.setOnCloseRequest( event -> CloseAttempt() );
	} 
	
	//method to populate the ListView
	public void fillBookListView() throws IOException {
		//Wipe ListView
		bookListView.getItems().clear();
		
		//Read LibraryFile
		try {
			LibraryData.readLibrary();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//For each book in BookArrayList, add an item to the ListView
		for (int i = 0; i < LibraryData.getBookArrayListSize() ; i++) {
			Book tempBook = LibraryData.getBookAtIndex(i);
			
			//Formatting for Book items
			bookListView.getItems().add(tempBook.getBookStatus() + " | " + tempBook.getBookAuthor() 
			+ " | " + tempBook.getBookTitle() + " | " + tempBook.getYear()
			+ " | " + tempBook.getGenre() + " | " + tempBook.getMediaType()  + "\n");
		}
	}
	
	//method to check out a book selected from the ListView
	public void checkOutBook() {
		Alert a = new Alert(AlertType.NONE);
		int index = bookListView.getSelectionModel().getSelectedIndex();
		if (index == -1) {
			//set alert type 
            a.setAlertType(AlertType.ERROR); 
            //set content text 
            a.setContentText("No book selected! \nPlease select a book by clicking on it, and try again.");
            //show the dialog 
            a.show();
		}
		else if (index <= LibraryData.getBookArrayListSize()) {
			if (!LibraryData.getBookAtIndex(index).isAvailable()) {
				
				//set alert type 
	            a.setAlertType(AlertType.ERROR); 
	            //set content text 
	            a.setContentText("Book is already checked out! \nPlease select a valid book.");
	            //show the dialog 
	            a.show();
				return;
			}
			

			if (Librarian.isAdmin()) {
				LibraryData.checkOutBook(LibraryData.getBookAtIndex(index), userLibrarian);
			}
			
			else if (Librarian.isUser()) {
				LibraryData.checkOutBook(LibraryData.getBookAtIndex(index), userPatron);
			}
				
			try {
				LibraryData.writeLibrary();
				fillBookListView();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			//set alert type 
            a.setAlertType(AlertType.CONFIRMATION); 
            //set content text 
            a.setContentText("Book successfully checked out! \nPlease return the book within the due date.");
            //show the dialog 
            a.show();
				
			
		}
	}
	
	//method to return the book selected from the ListView
	public void returnBook() {
		Alert a = new Alert(AlertType.NONE);
		int index = bookListView.getSelectionModel().getSelectedIndex();
		
		if (index == -1) {
			//set alert type 
            a.setAlertType(AlertType.ERROR); 
            //set content text 
            a.setContentText("No book selected! \nPlease select a book by clicking on it, and try again.");
            //show the dialog 
            a.show();
		}
		
		else if (index <= LibraryData.getBookArrayListSize()) {
			
			//Check to see if book is available
			if (LibraryData.getBookAtIndex(index).isAvailable()) {
				
				//set alert type 
	            a.setAlertType(AlertType.ERROR); 
	            //set content text 
	            a.setContentText("Book is not checked out! \nPlease select a valid book.");
	            //show the dialog 
	            a.show();
				return;
			}
			
			if (Librarian.isAdmin()) {
				//Check to make sure the current user is the one who previously checked out the book
				if (!LibraryData.getBookAtIndex(index).getHolder().equals(userLibrarian.getHolderID())) {
					//set alert type 
		            a.setAlertType(AlertType.ERROR); 
		            //set content text 
		            a.setContentText("You have not checked this book out! \nPlease select a valid book.");
		            //show the dialog 
		            a.show();
					return;
				}
			}
			else if (Librarian.isUser()) {
				//Check to make sure the current user is the one who previously checked out the book
				if (!LibraryData.getBookAtIndex(index).getHolder().equals(userPatron.getHolderID())) {
					//set alert type 
		            a.setAlertType(AlertType.ERROR); 
		            //set content text 
		            a.setContentText("You have not checked this book out! \nPlease select a valid book.");
		            //show the dialog 
		            a.show();
					return;
				}
			}
			
			if (Librarian.isAdmin()) {
				userLibrarian.returnCheckedBook(LibraryData.getBookAtIndex(index));
			}
			
			else if (Librarian.isUser()) {
				userPatron.returnCheckedBook(LibraryData.getBookAtIndex(index));
			}
				
			try {
				LibraryData.writeLibrary();
				fillBookListView();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			//set alert type 
            a.setAlertType(AlertType.CONFIRMATION); 
            //set content text 
            a.setContentText("Book successfully returned! \nThank you for returning.");
            //show the dialog 
            a.show();
		}
	}
	
	//This method creates an alert dialog to display the full information
	//on the selected book in the bookListView on the primaryStage
	public void getBookInfo() {
		//Create an alert
		Alert a = new Alert(AlertType.INFORMATION);
		//get the selected ListView
		int index = bookListView.getSelectionModel().getSelectedIndex();
		
		//Determine if the index is valid
		if (index != -1 && index <= LibraryData.getBookArrayListSize()) {
			//If it is, then display all of the book's info in an alert dialog.
			String holderIDInfo = "";
			if (Librarian.isAdmin()) {
				holderIDInfo = LibraryData.getBookAtIndex(index).getHolder();
			}
			a.setTitle("Info");
			a.setHeaderText("Book Information");
			
			//IF the user is an admin, then also display the book's current holder
			if (Librarian.isAdmin() && !LibraryData.getBookAtIndex(index).isAvailable()) {
				a.setContentText(LibraryData.getBookAtIndex(index).infoDisplay()+"\nCurrent holder: "+holderIDInfo);
			}
			else {
				a.setContentText(LibraryData.getBookAtIndex(index).infoDisplay());
			}
			
			//show the dialog
			a.show();
		}
	}
	
	//This method creates an alert dialog with a list of 
	//all of the current user's checked out books
	public void getCheckedBooks() {
		//Create an alert
		Alert a = new Alert(AlertType.INFORMATION);
		//Modify alert so that it will resize for long lists of checked out books
		a.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
		a.setTitle("Checked Out Books");
		a.setHeaderText("Your Checked Out Books : ");
		
		//String to hold generated list
		String tempList = "";
		
		//Counter for # of books out
		int checkedBooksOut = 0;
		
		//Search BookArrayList for a book with a holderID that matches the userObj holderId
		if (Librarian.isAdmin()) {
			for (int i = 0; i < LibraryData.getBookArrayListSize() ; i++) {
				if (userLibrarian.getHolderID().equals(LibraryData.getBookAtIndex(i).getHolder())) {
					tempList += LibraryData.getBookAtIndex(i).infoShort() + "\n\n";
					checkedBooksOut++;
				}
			}
			tempList += "\nTotal number of books checked out : " + checkedBooksOut;
			a.setContentText(tempList);
		}
		
		//If not a Librarian, search Patrons instead
		if (Librarian.isUser() && !Librarian.isAdmin()) {
			for (int i = 0; i < LibraryData.getBookArrayListSize() ; i++){
				if (userPatron.getHolderID().equals(LibraryData.getBookAtIndex(i).getHolder())){
					tempList += LibraryData.getBookAtIndex(i).infoShort() + "\n\n";
					checkedBooksOut++;
				}
			}
			tempList += "\nTotal number of books checked out : " + checkedBooksOut;
			a.setContentText(tempList);
		}
		
		//show the dialog
		a.show();
	}
	
	//This method creates the first form the user sees: a simple login form with three fields
	//After the user has input their info and clicked Login, it will run Librarian.loginAdmin
	//to confirm that their profile is in the system.
	protected void LoginForm() {
		
		//The button for checking given user info
		Button btUserLogin = new Button("Login");
		btUserLogin.setPrefSize(100,50);
		
		//Label to welcome users
		Label welcomeLabel1 = new Label("Welcome to JavaLibrary!");
		Label welcomeLabel2 = new Label("Please enter your information below and click Login.");
		
		//GridPane to hold all of the login fields + labels
		GridPane welcomeUser = new GridPane();
		welcomeUser.setAlignment(Pos.BASELINE_CENTER);
		welcomeUser.setHgap(15);
		welcomeUser.setVgap(15);
		TextField loginFirst = new TextField();
		TextField loginLast = new TextField();
		TextField loginID = new TextField();
		Label lblFirst = new Label("First Name ");
		Label lblLast = new Label("Last Name ");
		Label lblID = new Label("User ID ");
		welcomeUser.add(lblFirst,0,1);
		welcomeUser.add(lblLast,0,2);
		welcomeUser.add(lblID,0,3);
		welcomeUser.add(loginFirst,1,1);
		welcomeUser.add(loginLast,1,2);
		welcomeUser.add(loginID,1,3);
		
		//Create a VBox to hold subpanes and the button
        VBox loginFormVBox = new VBox();
        loginFormVBox.setPadding(new Insets(25,25,25,25));
        loginFormVBox.setAlignment(Pos.CENTER);
        loginFormVBox.setSpacing(25);
        loginFormVBox.getChildren().addAll(welcomeLabel1,welcomeLabel2,welcomeUser,btUserLogin);

        Scene loginScene = new Scene(loginFormVBox);

        // New window (Stage)
        Stage loginWindow = new Stage();
        loginWindow.setTitle("User Login");
        loginWindow.setScene(loginScene);

        loginWindow.show();
        
        //This is the handler for the Login button
        //If the user's login info is correct, will set up the library database program main window accordingly 
        EventHandler<ActionEvent> loginAttempt = new EventHandler<ActionEvent>() { 

			public void handle(ActionEvent e) { 
            	try {
            		//Read in the two ArrayLists
        			LibrarianData.readLibrarianFile();
        			PatronData.readPatronFile();
        			
        			//Test the user login to see if it matches an entry in either list
        			Librarian.loginAdmin(loginLast.getText(),loginFirst.getText(),loginID.getText());
        			currentUserName = loginFirst.getText() + " " + loginLast.getText();
        			
        		} catch (IOException e1) {
        			e1.printStackTrace();
        		}
            	
            	//Create an alert to tell if Login worked. defined by else if tree below
    	        Alert a = new Alert(AlertType.NONE); 
    			
    	        //For admin (Librarian) login
    			if (Librarian.isAdmin() && Librarian.isUser()) {
    				//set alert type 
                    a.setAlertType(AlertType.CONFIRMATION); 
                    //set content text 
                    a.setContentText("You have successfully logged in as Librarian: \n"
                    		+loginFirst.getText() + " " + loginLast.getText());
                    //Set user object
                    userLibrarian = new Librarian(loginLast.getText(), loginFirst.getText(), loginID.getText());
                    
                    //Open PrimaryForm
                    try {
                    	PrimaryForm();
            		} catch (IOException e1) {
            			e1.printStackTrace();
            		}
                    
                    //show the dialog
                    a.show();
                    loginWindow.close();
                    
    			}
    			//For user (Patron) login
    			else if (!Librarian.isAdmin() && Librarian.isUser()) {
    				//set alert type 
                    a.setAlertType(AlertType.CONFIRMATION); 
                    //set content text 
                    a.setContentText("You have successfully logged in as Patron: \n"
                    		+loginFirst.getText() + " " + loginLast.getText());
                    
                    //Set the user object
                    userPatron = new Patron(loginLast.getText(), loginFirst.getText(), loginID.getText());

                    //Open PrimaryForm
                    try {
                    	PrimaryForm();
            		} catch (IOException e1) {
            			e1.printStackTrace();
            		}
                    
                    //show the dialog
                    a.show();
                    loginWindow.close();
    			}
    			//For failed login
    			else if (!Librarian.isAdmin() && !Librarian.isUser()) {
    				//set alert type 
                    a.setAlertType(AlertType.ERROR); 
                    //set content text 
                    a.setContentText("User not found. Please try again, or seek technical support.");
                    //show the dialog 
                    a.show();
    			}
            } 
        }; 
  
        // when button is pressed 
        btUserLogin.setOnAction(loginAttempt); 
        
        //Call the CloseAttempt() method defined below
        loginWindow.setOnCloseRequest( event -> CloseAttempt() );
	}
	
	//If the login window is closed without successfully logging in, then close entire application
	protected void CloseAttempt() {
    	if (!Librarian.isAdmin() & !Librarian.isUser()) {
    		System.exit(0);
    	}
    	else if (Librarian.isAdmin()) {
    		try {
    			LibrarianData.writeLibrarianFile();
        		PatronData.writePatronFile();
    		} catch (IOException ex) {
    			ex.printStackTrace();
    		}
    		System.exit(0);
    	}
    	else if (Librarian.isUser()) {
    		try {
        		PatronData.writePatronFile();
    		} catch (IOException ex) {
    			ex.printStackTrace();
    		}
    		System.exit(0);
    	}
    }
	
	//This method creates the Add Book form for Librarians, creating a second window with textfields
	//After the user has input all info, they click the add book button and the
	//created Book object is added to the LibraryData ArrayList and the primaryStage's ListView
	protected void AddBookForm() {
		//The button for adding book
		Button btAddBook = new Button("Add Book");
		btAddBook.setPrefSize(100,50);
		
		//GridPane to hold all of the login fields + labels
		GridPane addBookPane = new GridPane();
		addBookPane.setAlignment(Pos.BASELINE_CENTER);
		addBookPane.setHgap(15);
		addBookPane.setVgap(15);
		TextField loginFirst = new TextField();
		TextField loginLast = new TextField();
		TextField loginID = new TextField();
		TextField loginYear = new TextField("(numbers only)");
		TextField loginGenre = new TextField();
		TextField loginPublisher = new TextField();
		TextField loginSection = new TextField();
		TextField loginMedia = new TextField();
		TextField loginPages = new TextField("(numbers only)");
		TextField loginLang = new TextField();
		TextField loginCopyNum = new TextField("(numbers only)");
		Label lblFirst = new Label("Author : ");
		Label lblLast = new Label("Title : ");
		Label lblID = new Label("ISBN : ");
		Label lblYear = new Label("Year Published : ");
		Label lblGenre = new Label("Genre : ");
		Label lblPublisher = new Label("Publisher : ");
		Label lblSection = new Label("Section (Flr/Sec) : ");
		Label lblMedia = new Label("Media type : ");
		Label lblPages = new Label("Page count : ");
		Label lblLang = new Label("Language : ");
		Label lblCopyNum = new Label("Copy # : ");
		addBookPane.add(lblFirst,0,1);
		addBookPane.add(lblLast,0,2);
		addBookPane.add(lblID,0,3);
		addBookPane.add(lblYear,0,4);
		addBookPane.add(lblGenre,0,5);
		addBookPane.add(lblPublisher,0,6);
		addBookPane.add(lblSection,0,7);
		addBookPane.add(lblMedia,0,8);
		addBookPane.add(lblPages,0,9);
		addBookPane.add(lblLang,0,10);
		addBookPane.add(lblCopyNum,0,11);
		addBookPane.add(loginFirst,1,1);
		addBookPane.add(loginLast,1,2);
		addBookPane.add(loginID,1,3);
		addBookPane.add(loginYear,1,4);
		addBookPane.add(loginGenre,1,5);
		addBookPane.add(loginPublisher,1,6);
		addBookPane.add(loginSection,1,7);
		addBookPane.add(loginMedia,1,8);
		addBookPane.add(loginPages,1,9);
		addBookPane.add(loginLang,1,10);
		addBookPane.add(loginCopyNum,1,11);
		
		//VBox to hold subpanes + button
        VBox AddBookFormVBox = new VBox();
        AddBookFormVBox.setPadding(new Insets(25,25,25,25));
        AddBookFormVBox.setAlignment(Pos.CENTER);
        AddBookFormVBox.setSpacing(25);
        AddBookFormVBox.getChildren().addAll(addBookPane,btAddBook);

        Scene addBookScene = new Scene(AddBookFormVBox);

        //Create an alert to tell if adding book worked. defined by else if tree below
        Alert a = new Alert(AlertType.NONE); 
        
        //New window (Stage)
        Stage addBookWindow = new Stage();
        addBookWindow.setTitle("Add A Book");
        addBookWindow.setScene(addBookScene);

        addBookWindow.show();
        
        //Event handler for add button
        EventHandler<ActionEvent> addBook = new EventHandler<ActionEvent>() { 
			public void handle(ActionEvent e) { 
				int test1 = 0;
    			int test2 = 0;
    			int test3 = 0;
            	try {
        			if (!Librarian.isAdmin()) {
        				return;
        			}
        			//Check for empty fields
            		if (loginFirst.getText().equals("") || loginLast.getText().equals("") || loginID.getText().equals("") ||
            				loginYear.getText().equals("") || loginGenre.getText().equals("") || loginPublisher.getText().equals("") ||
            				loginSection.getText().equals("") || loginMedia.getText().equals("") || loginPages.getText().equals("") || 
            				loginLang.getText().equals("") || loginCopyNum.getText().equals("")) {
            			//set alert type 
                        a.setAlertType(AlertType.ERROR); 
                        //set content text 
                        a.setContentText("All forms must be filled with appropriate data."
                        		+ "\nPlease check all forms and try again. \n");
                        a.show();
        				return;
            		}
            		//Check to see if fields are proper formats (no letters in num-only fields)
            		//if not, throw an error and have user try again 
            		try {
            			test1 = Integer.parseInt(loginYear.getText());
            			test2 = Integer.parseInt(loginPages.getText());
            			test3 = Integer.parseInt(loginCopyNum.getText());
            		} catch (NumberFormatException numE) {
            			numE.getMessage();
            			numE.printStackTrace();
            			//set alert type 
                        a.setAlertType(AlertType.ERROR); 
                        //set content text 
                        a.setContentText("All forms must be filled with appropriate data (use only numbers "
                        		+ "\nfor years/pages/copy#). Please check all forms and try again. \n");
                        a.show();
                        addBookWindow.close();
        				return;
            		}
            		//If the user's input is formatted correctly, process in as new Book object
            		//and update the listview in primaryStage
        			try {
        				//Create a new book from the user fields
		        		Book newBook = new Book(loginFirst.getText(), loginLast.getText(), loginID.getText(), test1, 
		        				loginGenre.getText(), loginPublisher.getText(), loginSection.getText(), loginMedia.getText(), 
		        				test2, loginLang.getText(), test3);
		        		//Add the book to the ArrayList, sort, write to file, fill Listview
		        		LibraryData.addBook(newBook);
		        		LibraryData.sortBookArrayList();
		        		LibraryData.writeLibrary();
		        		fillBookListView();
		        		//Create an alert to show success
		        		//set alert type 
		                a.setAlertType(AlertType.CONFIRMATION); 
		                //set content text 
		                a.setContentText("Book successfully created!");
		                //show the dialog 
		                a.show();
        			} catch (Exception exc) {
        				exc.printStackTrace();
        			}
            		
        		} catch (Exception e1) {
        			e1.printStackTrace();
        		}
			}
        };
        
        //Add event handler to button
        btAddBook.setOnAction(addBook);
	}
	
	//This form allows a Librarian to add a new Patron into the PatronData ArrayList.
	protected void AddPatronForm() {
		//The button for adding book
		Button btAddPatron = new Button("Add Patron");
		btAddBook.setPrefSize(100,50);
		
		//GridPane to hold all of the login fields + labels
		GridPane addPatronPane = new GridPane();
		addPatronPane.setAlignment(Pos.BASELINE_CENTER);
		addPatronPane.setHgap(15);
		addPatronPane.setVgap(15);
		TextField loginFirst = new TextField();
		TextField loginLast = new TextField();
		TextField loginID = new TextField();
		Label lblFirst = new Label("First Name : ");
		Label lblLast = new Label("Last Name : ");
		Label lblID = new Label("UserID : ");
		addPatronPane.add(lblFirst,0,1);
		addPatronPane.add(lblLast,0,2);
		addPatronPane.add(lblID,0,3);
		addPatronPane.add(loginFirst,1,1);
		addPatronPane.add(loginLast,1,2);
		addPatronPane.add(loginID,1,3);
		
		//VBox to hold subpanes + button
        VBox AddPatronFormVBox = new VBox();
        AddPatronFormVBox.setPadding(new Insets(25,25,25,25));
        AddPatronFormVBox.setAlignment(Pos.CENTER);
        AddPatronFormVBox.setSpacing(25);
        AddPatronFormVBox.getChildren().addAll(addPatronPane,btAddPatron);

        Scene addPatronScene = new Scene(AddPatronFormVBox);

        //Create an alert to tell if adding book worked. defined by else if tree below
        Alert a = new Alert(AlertType.NONE); 
        
        //New window (Stage)
        Stage addPatronWindow = new Stage();
        addPatronWindow.setTitle("Add A Patron");
        addPatronWindow.setScene(addPatronScene);
        addPatronWindow.show();
        
        //Event handler for add button
        EventHandler<ActionEvent> addPatron = new EventHandler<ActionEvent>() { 
			public void handle(ActionEvent e) { 
            	try {
        			if (!Librarian.isAdmin()) {
        				return;
        			}
            		if (loginFirst.getText().equals("") || loginLast.getText().equals("") || loginID.getText().equals("")) {
            			//set alert type 
                        a.setAlertType(AlertType.ERROR); 
                        //set content text 
                        a.setContentText("All forms must be filled with appropriate data."
                        		+ "\nPlease check all forms and try again. \n");
                        a.show();
        				return;
            		}
            		
        			try {
        				//Create a new book from the user fields
		        		Patron newPatron = new Patron(loginLast.getText(), loginFirst.getText(), loginID.getText());
		        		//Add the book to the ArrayList, sort, write to file, fill Listview
		        		PatronData.addPatron(newPatron);
		        		PatronData.writePatronFile();
		        		//Create an alert to show success
		        		//set alert type 
		                a.setAlertType(AlertType.CONFIRMATION); 
		                //set content text 
		                a.setContentText("Patron records successfully created!");
		                //show the dialog 
		                a.show();
		                addPatronWindow.close();
        			} catch (Exception exc) {
        				exc.printStackTrace();
        			}
            		
        		} catch (Exception e1) {
        			e1.printStackTrace();
        		}
			}
        };
        
        btAddPatron.setOnAction(addPatron);
	}
	
	//Create and populate the form for removing patron objects from the library database
	protected void RemovePatronForm() {
		//The button for adding book
		Button btRemovePatron = new Button("Remove Patron");
		btRemovePatron.setPrefSize(100,50);
		
		//Create an ArrayList to hold Strings representing all
		//patron objects, which are displayed in a ComboBox
		ArrayList<String> patronComboBox = new ArrayList<String>();
		for (int i = 0; i < PatronData.getPatronArrayListSize() ; i++) {
			String temp = PatronData.getPatronAtIndex(i).PatronInfoDisplay();
			patronComboBox.add(temp);
		}
		
		//Create a ComboBox to display all Patrons
		ComboBox<String> patronCombo = new ComboBox<String>(FXCollections.observableArrayList(patronComboBox));
		
		//Create and style removePatronWindow
        VBox removePatronFormVBox = new VBox();
        removePatronFormVBox.setPadding(new Insets(25,25,25,25));
        removePatronFormVBox.setAlignment(Pos.CENTER);
        removePatronFormVBox.setSpacing(25);
        removePatronFormVBox.getChildren().addAll(patronCombo,btRemovePatron);

        Scene secondScene = new Scene(removePatronFormVBox);

        //Create an alert to tell if adding book worked. defined by else if tree below
        Alert a = new Alert(AlertType.NONE); 
        
        // New window (Stage)
        Stage removePatronWindow = new Stage();
        removePatronWindow.setTitle("Remove A Patron");
        removePatronWindow.setScene(secondScene);
        removePatronWindow.show();
        
      //Event handler for add button
        EventHandler<ActionEvent> removePatron = new EventHandler<ActionEvent>() { 
			public void handle(ActionEvent e) {
            	try {
        			if (!Librarian.isAdmin()) {
        				return;
        			}
        			if (Librarian.isAdmin()) {
        				//Get patron at index and remove them
        				int index = patronCombo.getSelectionModel().getSelectedIndex();
        				PatronData.removePatron(index);
        				try {
        					//Write new file after patron removal
        					PatronData.writePatronFile();
        					//Create an alert to show success
    		        		//set alert type 
    		                a.setAlertType(AlertType.CONFIRMATION); 
    		                //set content text 
    		                a.setContentText("Patron records successfully updated, patron removed.");
    		                //show the dialog 
    		                a.show();
    		                removePatronWindow.close();
        				} catch (IOException eRemovePatron) {
        					eRemovePatron.printStackTrace();
        				}
        			}
            	} catch (Exception e1) {
            		e1.printStackTrace();
            	}
			}
        };
        
        //Assign event handler to button click
        btRemovePatron.setOnAction(removePatron);
	}
	
	//This form allows a Librarian to see all info on a patron,
	//including the books they currently have checked out.
	protected void PatronInfoForm() {
		//The button for adding book
		Button btGetInfo = new Button("Get Patron Info");
		btRemovePatron.setPrefSize(100,50);
		
		//Create an ArrayList to hold Strings representing all
		//patron objects, which are displayed in a ComboBox
		ArrayList<String> infoComboBox = new ArrayList<String>();
		for (int i = 0; i < PatronData.getPatronArrayListSize() ; i++) {
			String temp = PatronData.getPatronAtIndex(i).PatronInfoDisplay();
			infoComboBox.add(temp);
		}
		
		ComboBox<String> infoCombo = new ComboBox<String>(FXCollections.observableArrayList(infoComboBox));
		
        VBox removePatronFormVBox = new VBox();
        removePatronFormVBox.setPadding(new Insets(25,25,25,25));
        removePatronFormVBox.setAlignment(Pos.CENTER);
        removePatronFormVBox.setSpacing(25);
        removePatronFormVBox.getChildren().addAll(infoCombo,btGetInfo);

        Scene secondScene = new Scene(removePatronFormVBox);

        //Create an alert to tell if adding book worked. defined by else if tree below
        @SuppressWarnings("unused")
		Button btCancel = new Button();
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
		a.setTitle("Patron Info");
		a.setHeaderText("Patron Info & Checked Out Books");
        
        // New window (Stage)
        Stage removePatronWindow = new Stage();
        removePatronWindow.setTitle("Get Patron Info");
        removePatronWindow.setScene(secondScene);

        removePatronWindow.show();
        
      //Event handler for add button
        EventHandler<ActionEvent> infoPatron = new EventHandler<ActionEvent>() { 
			public void handle(ActionEvent e) { 
				
				//String to hold generated list
				String tempList = "";
				
				//Counter for # of books out
				int checkedBooksOut = 0;
				
            	try {
        			if (!Librarian.isAdmin()) {
        				return;
        			}
        			if (Librarian.isAdmin()) {
        				//Get index of Patron in PatronArrayList
        				int index = infoCombo.getSelectionModel().getSelectedIndex();
        				
        				//Display their Info
        				tempList += "Name: "+PatronData.getPatronAtIndex(index).getName()
        						+ "\nID: "+PatronData.getPatronAtIndex(index).getID()+"\n\nBooks Checked Out: \n\n";
        				
        				//Use for loop to display all of the books the patron has checked out
        				for (int i = 0; i < LibraryData.getBookArrayListSize() ; i++) {
        					if (PatronData.getPatronAtIndex(index).getHolderID().equals(LibraryData.getBookAtIndex(i).getHolder())) {
        						tempList += LibraryData.getBookAtIndex(i).infoShort() + "\n\n";
        						checkedBooksOut++;
        					}
        				}
        				
        				//Display total number of books checked out
        				tempList += "Total number of books checked out : " + checkedBooksOut;
        				a.setContentText(tempList);
        				a.showAndWait();
        			}
            	} catch (Exception e1) {
            		e1.printStackTrace();
            	}
			}
        };
        
        //Assign event handler to button click
        btGetInfo.setOnAction(infoPatron);
	}
}