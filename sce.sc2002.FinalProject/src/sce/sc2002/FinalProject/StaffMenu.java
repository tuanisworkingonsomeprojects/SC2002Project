package sce.sc2002.FinalProject;

import java.util.*;

/**
 After the user has logged into the CAMP management system
 if the user has Staff ID, the instance of 
 this class will run to create a Staff Menu
 @author Randy Tan
 @version 1.0
 @since 11/4/2023
*/


public class StaffMenu extends Menu{
	/**
	 * StaffMenu constructor
	 * @param user Current user that is Logged in
	 * @param campList The Camplist
	 */
    public StaffMenu(Login user, CampList campList){
        super(campList);
        currentUser = user;
    }


	/**
	 * This method will display the menu once the user has logged in.
	 */
	public void runMenu() {
		printHeader();

		while(!exit) {
			display();
			int choice = getMenuChoice();
			performAction(choice);
		}
	}
	/**
	 * This will print the header of the Camp App.
	 */
	private void printHeader() {
		System.out.println("+-----------------------------------+");
        System.out.println("|           Welcome to              |");
        System.out.println("|        Awesome Camp App           |");
        System.out.println("+-----------------------------------+");
	}
	/**
	 * This is the display of the staff's version of menu.
	 */
	public void display() {
        String[] options = {"Change Password",
                            "Create Camps",
                            "View Camps",
                            "Edit Camp",
                            "Delete Camp",
                            "View Student List",
                            "View Enquiries",
                            "Reply Enquiries",
                            "View Suggestions",
                            "Consider Suggestions",
                            "Generate Reports",
                            "Log out",
                            "Exit"};


		System.out.println("Staff Main Portal: ");
        System.out.println();


        for (int i = 0; i < options.length; i++){
            System.out.println((i + 1) + ". " + options[i]);
        }
            
	}
	
	/**
	 * This method will display the menu once the user has logged in.
	 * @return returning the option the user has keyed in.
	 */
    private int getMenuChoice() {
        Scanner keyboard = new Scanner(System.in);
        int choice = -1;
        do {
            System.out.print("Enter your choice: ");
            try {
                choice = Integer.parseInt(keyboard.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid selection. Numbers only please.");
                continue;
            }

            if (choice < 0) System.out.println("Valid number only");

        } while (choice < 0);
        return choice;
    }
    /**
     * Displays the different options that the student has in the menu screen.
     * @param choice The option that the user has keyed in.
     */
    private void performAction(int choice) {
        switch (choice) {

            // Change password option
            case 1:
                clearScreen();
                System.out.println("Change password Menu:");
                currentUser.changePassword();
                break;

            case 2: 
                System.out.println("Creating camp...");
                campList.createCamp();
                break;

            case 3: 
                System.out.println("Viewing Camps...");
                campList.viewCamp();
                break;

            case 4:
                System.out.println("Editing Camp...");
                campList.chooseCampToEdit();
                break;

            case 5:
                System.out.println("Deleting Camp...");
                campList.deleteCamp();
                break;

            case 6:
                System.out.println("Viewing student list...");
                campList.viewStudentList();
                break;

            case 7:
                System.out.println("Viewing enquries...");
                campList.viewEnquiry();
                break;

            case 8:
                System.out.println("Replying Enquiry...");
                campList.replyEnquiry();
                break;
            
            case 9:
                System.out.println("Viewing suggestions...");
                campList.viewSuggestion();
                break;

            case 10:
                System.out.println("Considering suggestion...");
                campList.replySuggestion();
                break;
            case 11:
            	System.out.println("Generating report...");
            	campList.generateReport();
                break;

            
            case 12:
                System.out.println("Logging out...");
                currentUser.logOut();
                campList.exportData();
                exit = true;
                clearScreen();
                break;
            
            case 13:
                for (int i = 0; i < 100; i++) System.out.println();
                
                currentUser.logOut();
                campList.exportData();
                clearScreen();
                System.out.println("Exiting...");
                System.exit(0);
                break;
            default:
                System.out.println("Choice out of range!");
        }
    }
}
