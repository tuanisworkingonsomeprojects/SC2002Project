package sce.sc2002.FinalProject;

import java.io.*;
import java.util.Scanner;

/**
 When the program started it require the instance of
 this class to run to create a login option
 @author Nguyen Tuan
 @version 1.0
 @since 11/2/2023
*/

public class Login {


	/**
	 Access Granted or Not Granted.<br>
	 Initialize to false.
	 */
	private boolean granted;
	
	/**
	 * The UserID of the user.<br>
	 * Initialize to null.<br>
	 * Update when access granted.
	 * */
	private String userid;
	
	/**
	 * The user's password.<br>
	 * Initialize to null.<br>
	 * Update when access granted.
	 * */
	private String password;
	
	/**
	 * The user's name.<br>
	 * Initialize to null.<br>
	 * Update when access granted.
	 * */
	private String name;
	
	/**
	 * The user's email.<br>
	 * Initialize to null.<br>
	 * Update when access granted.
	 * */
	private String email;
	
	/**
	 * The user's name.<br>
	 * Initialize to null.<br>
	 * Update when access granted.
	 * */
	private String faculty;
	
	/**
	 * The user's name.<br>
	 * Initialize to null.<br>
	 * Update when access granted based on the UserID.
	 * */
	private String role;
	
	
	/**
	* Constructor of the Login class.<br>
	* Initialize {@code granted} to {@code false}.<br>
	* Initialize remain values to {@code null}.<br>
	*/
	public Login() {
		granted = false;
		userid = null;
		password = null;
		name = null;
		email = null;
		faculty = null;
		role = null;
	}
	
	/**
	 * Return true if access granted and false otherwise*/
	public boolean accessGranted(){
		return granted;
	}
	
	/**
	 * Only return the user's id if the access is granted.
	 * @return userid
	 * @return {@code null}*/
	public String getUserId() {
		if (granted) return userid;
		return null;
	}
	
	public String getPassword() {
		if (granted) return password;
		return null;
	}
	
	public String getName() {
		if (granted) return name;
		return null;
	}
	
	public String getEmail() {
		if (granted) return email;
		return null;
	}
	
	public String getFaculty() {
		if (granted) return faculty;
		return null;
	}
	
	public String getRole() {
		if (granted) return role;
		return null;
	}
	
	//Pseudo clear screen
	private void clearScreen() {
		for (int i = 0; i < 50; i++) {
			System.out.println();
		}
	}
	
	
	/**
	 * This method will base on the role parameter. Then, it will find and return the
	 * appropriate user list with all the users' data needed.<br>
	 * 
	 * @param role
	 * 		  The role of the users in the csv this method will retrieve.
	 * @return The Scanner instance that have the data of the required csv file.
	 * @see #validateControl(String, String)
	 * 
	 **/
	
	private Scanner getDataFromCSV(String role) {
		Scanner sc = null;
		
		// Get studentList.csv file
		if (role.equals("student")) {
			try {
				sc = new Scanner(new File(System.getProperty("user.dir") + "/src/sce/sc2002/FinalProject/UserAndPassword/studentList.csv"));
				
			}
			catch (final Exception e) {
				try {
					sc = new Scanner(new File(System.getProperty("user.dir") + "/sce.sc2002.FinalProject/src/sce/sc2002/FinalProject/UserAndPassword/studentList.csv"));
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}
			
			sc.useDelimiter(",");
		}
		
		// Get staffList.csv file
		else {
			try {
				sc = new Scanner(new File(System.getProperty("user.dir") + "/src/sce/sc2002/FinalProject/UserAndPassword/staffList.csv"));
			}
			catch (final Exception e) {
				try {
					sc = new Scanner(new File(System.getProperty("user.dir") + "/sce.sc2002.FinalProject/src/sce/sc2002/FinalProject/UserAndPassword/staffList.csv"));
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}
			
			sc.useDelimiter(",");
		}
		
		return sc;
	}
	
	/**
	 * This method will mask the password and do not let the user or other parties see the
	 * input password if the program runs outside the IDLE.<br>
	 * (i.e when the program runs on the system terminal or console)<br>
	 * 
	 * @return password.
	 * @see #display()
	 **/
	
	private String readPassword() {
		Console console = System.console();
		
		if (console == null) {

			Scanner sc = new Scanner(System.in);

			String password = sc.next();

			return password;
		}
		
		char[] passwordArray = console.readPassword();
		return new String(passwordArray);
	}
	
	
	/**
	 * This method will validate the input userid and input password by comparing:<br>
	 * 	- The userid created from the email (the part before '@') retrieved from csv file.<br>
	 * 	- The password in the data retreived from csv file.<br>
	 * If the user's is valid, all the appropriate variable in this class will update.
	 * @param input_userid
	 * 		  userid from user's input
	 * @param input_password
	 * 		  password from user's input
	 * @param csvFile
	 * 		  Scanner object that point to the csv file used to check the user's validation.
	 * @see #validateControl(String, String)
	 * @see #userid
	 * @see #password
	 * @see #name
	 * @see #email
	 * @see #faculty
	 * 
	 **/
	
	private void validate(String input_userid, String input_password, Scanner csvFile) {
		
		
		// Remove the header		
		csvFile.nextLine();
		
		while (csvFile.hasNext()) {
			
			String nameData = csvFile.next();
			nameData.replaceAll("\\s", "");
			String emailData = csvFile.next();
			emailData.replaceAll("\\s", "");
			String facultyData = csvFile.next();
			facultyData.replaceAll("\\s", "");
			String passwordData = csvFile.next();
			passwordData.replaceAll("\\s", "");
			String useridData = "";
			
			
			
			// Create useridData from email
			for (int i = 0; i < emailData.length(); i++) {
				Character c = emailData.charAt(i);
				if (c.equals('@')) break;
				useridData = useridData + c;
			}
			
			if (input_userid.equals(useridData)) {
				if (input_password.equals(passwordData)) {
					granted = true;
					
					userid = useridData;
					password = passwordData;
					name = nameData;
					email = emailData;
					faculty = facultyData;
					
					System.out.println("\n\n-----Access Granted!-----\n\n");
					try
				    {
				        Thread.sleep(1000);
				    }
				    catch(InterruptedException ex)
				    {
				        Thread.currentThread().interrupt();
				    }
					clearScreen();
					break;
				}
			}
		}
	}
	
	
	/*-----------------------------------------------------------------------------------
	 * Method : validateControl
	 * Scope  : private
	 * Param  : input_userid, input_userpassword
	 * return : void
	 * 
	 * Short explaination:
	 * This method control the flow of validating by calling validate() method
	 *----------------------------------------------------------------------------------- */
	
	private void validateControl(String input_userid, String input_password){
		System.out.println("Validating...");
		
		Scanner csvFile = getDataFromCSV("student");
		validate(input_userid, input_password, csvFile);
		if (granted) {
			role = "student";
		}
		else {
			csvFile = getDataFromCSV("staff");
			validate(input_userid, input_password, csvFile);
			if (granted) {
				role = "staff";
			}
		}

		if (!granted) {
			System.out.println("UserID or password is incorrect!\n\n");
		}

	}
	
	
	/*-----------------------------------------------------------------------------------
	 * Method : display
	 * Scope  : public
	 * Param  : no param
	 * return : void
	 * 
	 * Short explaination:
	 * This method will control the flow of the login system.
	 *----------------------------------------------------------------------------------- */
	public void display(){
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		
		System.out.println("_________Login Screen__________");
		
		// Input Phase
		while(!granted) {
			System.out.print("UserID: ");
			String input_userid = sc.next();
			
			System.out.print("Password: ");
			String input_password = readPassword();
			
			validateControl(input_userid, input_password);
		}
	}
}
