package sce.sc2002.FinalProject;

import java.io.Console;
import java.util.Scanner;

/**
 * Important Password class for login
 */

public class Password {
	/**
	 * Private Student Data
	 */
	private UserData studentData;
	/**
	 * Private Staff Data
	 */
	private UserData staffData;
	/**
	 * Private user Data
	 */
	private UserData userData;
	/**
	 * boolean granted
	 */
	private boolean granted;
	/**
	 * String userID
	 */
	private String userid;
	/**
	 * String role
	 */
	private String role;
	/**
	 * String name
	 */
	private String name;
	/**
	 * Secret key to help in safe keeping password
	 */
	private final static String secretKey = "asdfljknqwe6238745fg23av3gv246hrejbq4tybetjysdafg5y";
	
	/**
	 * Password constructor
	 */
	public Password() {
		studentData = new UserData("student");
		staffData = new UserData("staff");
		granted = false;
		userid = null;
		role = null;
	}
	/**
	 * This getter method will return the userID
	 * @return
	 */
	public String getUserid(){
		if (granted) return userid;
		return null;
	}
	/**
	 * This getter method will return the role of the user
	 * @return
	 */
	public String getRole(){
		if (granted) return role;
		return null;
	}
	/**
	 * This getter method will return the faculty of the user
	 * @return
	 */
	public String getFaculty(){
		if (granted) return userData.getFaculty(userid);
		return null;
	}
	/**
	 * This boolean method will checked if the user is logged in
	 * @return
	 */
	public boolean logedIn() {
		return granted;
	}
	/**
	 * This method will allow the user to sign out
	 */
	public void signout() {
		granted = false;
		userid = null;
	}
	
	/**
	 * This method will validate the users userid and password for login
	 * @param userid users userid
	 * @param password users password
	 */
	protected void validate(String userid, String password) {
		if (studentData.getPassword(userid, secretKey) == null && staffData.getPassword(userid, secretKey) == null) return;
		
		if (studentData.getPassword(userid, secretKey) != null && studentData.getPassword(userid, secretKey).equals(password)) {
			role = "student";
			granted = true;
			this.userid = userid;
			userData = studentData;
			
			if (studentData.getPassword(userid, secretKey).equals("password")) changePassword();
			return;
		}
		else if (staffData.getPassword(userid, secretKey) != null && staffData.getPassword(userid, secretKey).equals(password)) {
			role = "staff";
			granted = true;
			this.userid = userid;
			userData = staffData;
			if (staffData.getPassword(userid, secretKey).equals("password")) changePassword();
			return;
		}

		return;
	}
	
	/**
	 * This method will read the password that is keyed in
	 * @return
	 */
	protected String readPassword() {
		Console console = System.console();
		
		if (console == null) {

			Scanner sc = new Scanner(System.in);

			String password = sc.nextLine();

			return password;
		}
		
		char[] passwordArray = console.readPassword();
		return new String(passwordArray);
	}
	/**
	 * This method will help to change password for the user
	 */
	public void changePassword() {
		Scanner sc = new Scanner(System.in);
		String newPassword;
		boolean changed = false;
		
		while (!changed) {
			System.out.println("-------Please change your password-------");
			System.out.print("New password: ");
			newPassword = readPassword();
			System.out.print("Confirm new password: ");
			String confirmPassword = readPassword();
			
			if (newPassword.equals(confirmPassword) && !newPassword.equals(userData.getPassword(userid, secretKey))) {
				userData.changePassword(userid, newPassword, secretKey);
				changed = true;
			}
			else {
				System.out.println("Confirmation Failed, please try again!");
			}
		}
		
	}
	/**
	 * This method will allow the user to log out
	 */
	protected void logOut(){
		granted = false;
	}
	
	
}
