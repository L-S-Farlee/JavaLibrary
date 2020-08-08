package application;
import java.io.*;
import java.util.ArrayList;

//This class handles the management of the ArrayList holding
//all Librarian objects in the library system
public class LibrarianData {

	//The ArrayList for Librarian objects
	static private ArrayList<Librarian> LibrarianArrayList = new ArrayList<Librarian>();
	
	//This is the default Librarian object, used for testing.
	static private Librarian defaultLibrarian = new Librarian("Doe", "Jane", "12345abc");
	
	//This is the static variable used to check if the LibrarianArrayList file exists
	static private File dataFileLibrarian = new File("Data/LibrarianData.dat");
	
	//Get ArrayList size
	static int getLibArrayListSize() {
		return LibrarianArrayList.size();
	}

	//Get the object at a certain index of the ArrayList
	static Librarian getLibAtIndex(int i) {
		if (i < LibrarianArrayList.size() && i > -1) {
			return LibrarianArrayList.get(i);
		}
		else {
			return null;
		}
	};
	
	//Method for checking if Librarian's data file exists
	static boolean checkLibrarianFileExists() {
		boolean fileStatus = dataFileLibrarian.isFile();
		return fileStatus;
	}

	//Search ArrayList to see if Librarian object already exists
	public static boolean searchLibrarian(Librarian lib) {
		
		for (int i = 0 ; i < getLibArrayListSize() ; i++) {
			if (lib.getHolderID().equals(getLibAtIndex(i).getHolderID())) {
				return true;
			}
		}
		return false;
	}
	
	//Search ArrayList to find index of specific Librarian object
	public static int searchLibrarianIndex(Librarian lib) {
		
		for (int i = 0 ; i < LibrarianData.getLibArrayListSize() ; i++) {
			if (lib.getHolderID().equals(getLibAtIndex(i).getHolderID())) {
				return i;
			};
		}
		return -1;
	}
	
	//This method reads in the Librarian data file
	@SuppressWarnings("unchecked")
	protected static void readLibrarianFile() throws IOException {
		//This code checks if the file already exists
		//if the answer is no, then it skips loading the ArrayList
			if (dataFileLibrarian.isFile()) {
				try {
					//These three lines are how this program
					//attempts to find and read the list of all books
				    FileInputStream inFileLib = new FileInputStream("Data/LibrarianData.dat");
				    BufferedInputStream inBufferLib = new BufferedInputStream(inFileLib);
				    ObjectInputStream inObjLib = new ObjectInputStream(inBufferLib);
				    
				    LibrarianArrayList = (ArrayList<Librarian>) inObjLib.readObject();
				    //System.out.println("READ RESULT : "+BookArrayList.toString());
				    inObjLib.close();
				} 
				catch (IOException e) {
				     e.printStackTrace();
				}
				catch (ClassNotFoundException e) {
				     e.printStackTrace();
				}
			}
			else {
				System.out.println("DATA FILE READ FAILED: No data file found for Librarian class. Continuing without reading.");
			}
			
		    if (LibrarianArrayList.size() == 0) {
		    	LibrarianArrayList.add(defaultLibrarian);
		    }
	}
	
	//This method writes the contents of the LibrarianArrayList into the Librarian data file
	protected static void writeLibrarianFile() throws IOException {
		//This code creates a new .dat file with the contents of LibrarianArrayList
		if (Librarian.isAdmin()) {
			try {
				FileOutputStream outFileLib = new FileOutputStream("Data/LibrarianData.dat");
				BufferedOutputStream outBufferLib = new BufferedOutputStream(outFileLib);
				ObjectOutputStream outObjLib = new ObjectOutputStream(outBufferLib);
				outObjLib.writeObject(LibrarianArrayList);
				outObjLib.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	//Add a Librarian profile to the ArrayList
	public static void addLibrarian(Librarian librarian) {
		if (Librarian.isAdmin()) {
			if (!LibrarianData.searchLibrarian(librarian) 
					&& !(librarian.getHolderID().equals("~~~"))
					&& !(librarian.getID().equals(""))) {
				LibrarianArrayList.add(librarian);
			}
		}
	}
	
	//Give ArrayList in full as a String separate line-by-line
	public static String displayLibrarianArrayList() {
		String temp = "";
		
		for (int i = 0 ; i < LibrarianData.getLibArrayListSize() ; i++) {
			temp += "\n"+getLibAtIndex(i).toString();
		}
		
		return temp;
	}

	//Reset the ArrayList, using .clear() to wipe all elements
	public static void clearLibArrayList() {
		if (Librarian.isAdmin()) {
			LibrarianArrayList.clear();
		}
	}
	
}
