package sce.sc2002.FinalProject;

import java.io.Console;
import java.util.Scanner;

public class Password {
	private UserData studentData;
	private UserData staffData;
	private UserData userData;
	private boolean granted;
	private String userid;
	private String role;
	
	
	public Password() {
		studentData = new UserData("student");
		staffData = new UserData("staff");
		granted = false;
		userid = null;
		role = null;
	}
	
	public boolean logedIn() {
		return granted;
	}
	
	public void signout() {
		granted = false;
		userid = null;
	}
	
	
	protected boolean validate(String userid, String password) {
		if (studentData.getPassword(userid) == null && staffData.getPassword(userid) == null) return false;
		
		if (studentData.getPassword(userid) != null && studentData.getPassword(userid).equals(password)) {
			role = "student";
			granted = true;
			this.userid = userid;
			userData = studentData;
			if (studentData.getPassword(userid).equals("password")) changePassword();
			return true;
		}
		else if (staffData.getPassword(userid) != null && staffData.getPassword(userid).equals(password)) {
			role = "staff";
			granted = true;
			this.userid = userid;
			userData = studentData;
			if (staffData.getPassword(userid).equals("password")) changePassword();
			return true;
		}

		return false;
	}
	
	protected String readPassword() {
		Console console = System.console();
		
		if (console == null) {

			Scanner sc = new Scanner(System.in);

			String password = sc.next();

			return password;
		}
		
		char[] passwordArray = console.readPassword();
		return new String(passwordArray);
	}
	
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
			
			if (newPassword.equals(confirmPassword) && !newPassword.equals(userData.getPassword(userid))) {
				userData.changePassword(userid, newPassword);
				changed = true;
			}
			else {
				System.out.println("Confirmation Failed, please try again!");
			}
		}
		
	}
	
	
	
}
