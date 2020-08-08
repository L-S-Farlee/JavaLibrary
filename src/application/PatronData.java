package application;
import java.io.*;
import java.util.ArrayList;

//This class handles the management of the ArrayList holding
//all Patron objects in the library system
public class PatronData {
	//The ArrayList for Patron objects
	static private ArrayList<Patron> PatronArrayList = new ArrayList<Patron>();
	
	//Default Patron object, used for testing
	private static Patron defaultPatron = new Patron("Doe", "John", "54321cba");
	
	//This is the static variable used to check if the LibrarianArrayList file exists
	static private File dataFilePatron = new File("Data/PatronData.dat");
	
	//Method for checking if Patron's data file exists
	static boolean checkPatronFileExists() {
		boolean fileStatus = dataFilePatron.isFile();
		return fileStatus;
	}
	
	//Get ArrayList size
	static int getPatronArrayListSize() {
		return PatronArrayList.size();
	}

	//Get the Patron object at a specific index
	static Patron getPatronAtIndex(int i) {
		if (i < PatronArrayList.size() && i > -1) {
			return PatronArrayList.get(i);
		}
		else {
			return null;
		}
	};
	
	
	//Add a new Patron to the ArrayList
	public static void addPatron(Patron p) {
		if (Librarian.isAdmin()) {
			if (!PatronData.searchPatron(p) && !(p.getHolderID().equals("~~~"))) {
				PatronArrayList.add(p);
			}
		}
	}

	//Search ArrayList to see if specific Patron object exists
	public static boolean searchPatron(Patron p) {
		for (int i = 0 ; i < getPatronArrayListSize() ; i++) {
			if (p.getHolderID().equals(getPatronAtIndex(i).getHolderID())) {
				return true;
			}
		}
		return false;
	}

	//Search ArrayList to find index of specific Patron object
	public static int searchPatronIndex(Patron p) {
		int counter = -1;
		
		for (int i = 0 ; i < PatronArrayList.size() ; i++) {
			if (p.getHolderID().equals(getPatronAtIndex(i).getHolderID())) {
				counter = i;
				return counter;
			}
		}
		return -1;
	}

	//This method reads in the Patron data file
	@SuppressWarnings("unchecked")
	protected static void readPatronFile() throws IOException {
		//This code checks if the file already exists
		//if the answer is no, then it skips loading the ArrayList
			if (dataFilePatron.isFile()) {
				try {
					//These three lines are how this program
					//attempts to find and read the list of all books
				    FileInputStream inFilePatron = new FileInputStream("Data/PatronData.dat");
				    BufferedInputStream inBufferPatron = new BufferedInputStream(inFilePatron);
				    ObjectInputStream inObjPatron = new ObjectInputStream(inBufferPatron);
				    
				    PatronArrayList = (ArrayList<Patron>) inObjPatron.readObject();
				    //System.out.println("READ RESULT : "+PatronArrayList.toString());
				    inObjPatron.close();
				} 
				catch (IOException e) {
				     e.printStackTrace();
				}
				catch (ClassNotFoundException e) {
				     e.printStackTrace();
				}
			}
			else {
				System.out.println("DATA FILE READ FAILED: No data file found for Patron class. Continuing without reading.");
				
			}
			
			if (PatronArrayList.size() == 0) {
				PatronArrayList.add(defaultPatron);
		    }
	}

	//This method writes the contents of the PatronArrayList into the Patron data file
	protected static void writePatronFile() throws IOException {
		//This code creates a new .dat file with the contents of PatronArrayList
		if (Librarian.isAdmin()) {
			try {
				FileOutputStream outFilePatron = new FileOutputStream("Data/PatronData.dat");
				BufferedOutputStream outBufferPatron = new BufferedOutputStream(outFilePatron);
				ObjectOutputStream outObjPatron = new ObjectOutputStream(outBufferPatron);
				outObjPatron.writeObject(PatronArrayList);
				outObjPatron.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	//Reset the ArrayList, using .clear() to wipe all elements
	public static void clearPatronArrayList() {
		if (Librarian.isAdmin()) {
			PatronArrayList.clear();
		}
	}
	
	//Remove a Patron object at a specific index
	public static void removePatron(int i) {
		if (Librarian.isAdmin()) {
			PatronArrayList.remove(i);
		}
	}
	
	//Give ArrayList in full as a String separate line-by-line
	public static String displayPatronArrayList() {
		String temp = "";
		
		for (int i = 0 ; i < PatronData.getPatronArrayListSize() ; i++) {
			temp += "\n"+getPatronAtIndex(i).toString();
		}
		
		return temp;
	}
}
