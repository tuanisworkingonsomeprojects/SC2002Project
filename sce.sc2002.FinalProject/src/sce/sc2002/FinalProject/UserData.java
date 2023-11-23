package sce.sc2002.FinalProject;

import java.io.*;
import java.util.*;

/**
 * User Data class that contains the user datas
 */

public class UserData {
	
	// This will in the format Name, Email, Faculty, Password, userID
	/**
	 * String user data
	 */
	private String[][] userData;
	/**
	 * int number of users
	 */
	private int numberOfUser; 
	/**
	 * Acces Path
	 */
	private String accessPath;
	
	/**
	 * Constructor for user data
	 * @param role role of the user
	 */
	public UserData(String role) {
		numberOfUser = countRow(getDataFromCSV(role));
		userData = new String[numberOfUser][5];
		constructUserData(getDataFromCSV(role));
		
	}
	/**
	 * scanner to scan the data from csv file
	 * @param role role of the user
	 * @return
	 */
	private Scanner getDataFromCSV(String role) {
		Scanner sc = null;
		
		// Get studentList.csv file
		if (role.equals("student")) {
			try {
				sc = new Scanner(new File(System.getProperty("user.dir") + "/src/sce/sc2002/FinalProject/UserAndPassword/studentList.csv"));
				accessPath = System.getProperty("user.dir") + "/src/sce/sc2002/FinalProject/UserAndPassword/studentList.csv";
			}
			catch (final Exception e) {
				try {
					sc = new Scanner(new File(System.getProperty("user.dir") + "/sce.sc2002.FinalProject/src/sce/sc2002/FinalProject/UserAndPassword/studentList.csv"));
					accessPath = System.getProperty("user.dir") + "/sce.sc2002.FinalProject/src/sce/sc2002/FinalProject/UserAndPassword/studentList.csv";
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
				accessPath = System.getProperty("user.dir") + "/src/sce/sc2002/FinalProject/UserAndPassword/staffList.csv";
			}
			catch (final Exception e) {
				try {
					sc = new Scanner(new File(System.getProperty("user.dir") + "/sce.sc2002.FinalProject/src/sce/sc2002/FinalProject/UserAndPassword/staffList.csv"));
					accessPath = System.getProperty("user.dir") + "/sce.sc2002.FinalProject/src/sce/sc2002/FinalProject/UserAndPassword/staffList.csv";
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}
			
			sc.useDelimiter(",");
		}
		
		return sc;
	}
	/**
	 * row counter for the csv file containing the user data
	 * @param csvFile
	 * @return
	 */
	private int countRow(Scanner csvFile) {
		csvFile.nextLine();
		int i = 0;
		
		while (csvFile.hasNextLine()) {
			i++;
			csvFile.nextLine();
		}
		
		return i;
		
	}

	/**
	 * Constructing the user data from the csvfile
	 * @param csvFile
	 */
	private void constructUserData(Scanner csvFile) {
		csvFile.nextLine();
		int u = 0;
		
		while (csvFile.hasNext()) {
			String nameData = csvFile.next();
			nameData.replaceAll("\\s", "");
			userData[u][0] = nameData;

			
			String emailData = csvFile.next();
			emailData.replaceAll("\\s", "");
			userData[u][1] = emailData;
			
			String facultyData = csvFile.next();
			facultyData.replaceAll("\\s", "");
			userData[u][2] = facultyData;
			
			String passwordData = csvFile.next();
			passwordData.replaceAll("\\s", "");
			userData[u][3] = passwordData;
			
			String useridData = "";
			
			
			// Create useridData from email
			for (int i = 0; i < emailData.length(); i++) {
				Character c = emailData.charAt(i);
				if (c.equals('@')) break;
				useridData = useridData + c;
			}
			userData[u][4] = useridData;
			
			u++;
		}
			
	}
	/**
	 * This getter method will get the user password
	 * @param userid
	 * @param key
	 * @return
	 */
	public String getPassword(String userid, String key) {
		if (key.equals("asdfljknqwe6238745fg23av3gv246hrejbq4tybetjysdafg5y")){
			for (int i = 0; i < numberOfUser; i++) {
				if (userData[i][4].equals(userid)) 
					return userData[i][3];
			}
		}
		return null;
	}
	/**
	 * This getter method will get the user faculty
	 * @param userid
	 * @return
	 */
	public String getFaculty(String userid){
		for (int i = 0; i < numberOfUser; i++) {
			if (userData[i][4].equals(userid)) 
				return userData[i][2];
		}
		
		return null;
	}
	/**
	 * This method will help the user change  password
	 * @param userid
	 * @param new_password
	 * @param key
	 */
	public void changePassword(String userid, String new_password, String key) {
		if (key.equals("asdfljknqwe6238745fg23av3gv246hrejbq4tybetjysdafg5y")){
			for (int i = 0; i < numberOfUser; i++) {
				if (userData[i][4].equals(userid)) {
					userData[i][3] = new_password;
					break;
				} 
			}
			
			try (PrintWriter writer = new PrintWriter(new File(accessPath))) {
				StringBuilder stringbuilder = new StringBuilder();
				
				
				stringbuilder.append("Name,Email,Faculty,Password,\n");
				for (int i = 0; i < numberOfUser; i++) {
					for (int j = 0; j < 4; j++) {
						stringbuilder.append(userData[i][j]);
						stringbuilder.append(',');
					}
				}

				writer.write(stringbuilder.toString());
				//System.out.println(accessPath);
				System.out.println("done!");
			} 
			catch (FileNotFoundException exception) {
				System.out.println(exception.getMessage());
			}
		}
		else {
			System.out.println("The class does not have enough authority");
		}
	}
	
	
}
