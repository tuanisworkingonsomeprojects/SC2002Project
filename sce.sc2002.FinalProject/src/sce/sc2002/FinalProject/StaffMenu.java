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


    public StaffMenu(Login user, CampList campList){
        super(campList);
        currentUser = user;
    }


	
	public void runMenu() {
		printHeader();

		while(!exit) {
			display();
			int choice = getMenuChoice();
			performAction(choice);
		}
	}
	
	private void printHeader() {
		System.out.println("+-----------------------------------+");
        System.out.println("|           Welcome to              |");
        System.out.println("|        Awesome Camp App           |");
        System.out.println("+-----------------------------------+");
	}

	public void display() {
        String[] options = {"Change Password",
                            "Create Camps",
                            "View Camps",
                            "Delete Camp",
                            "View Student List",
                            "View Enquiries",           // TODO: He can view enquiries from the camp HE CREATED
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
	
	
    private int getMenuChoice() {
        Scanner keyboard = new Scanner(System.in);
        int choice = -1;
        do {
            System.out.print("Enter your choice: ");
            try {
                choice = Integer.parseInt(keyboard.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid selection. Numbers only please.");
            }
        } while (choice < 0);
        return choice;
    }

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
                System.out.println("Deleting Camp...");
                campList.deleteCamp();
                break;

            case 5:
                System.out.println("Viewing student list...");
                campList.viewStudentList();
                break;

            case 6:
                System.out.println("Viewing enquries...");
                campList.viewEnquiry();
                break;

            case 7:
                System.out.println("Replying Enquiry...");
                campList.replyEnquiry();
                break;
            
            case 8:
                System.out.println("Viewing suggestions...");
                campList.viewSuggestion();
                break;

            case 9:
                System.out.println("Considering suggestion...");
                campList.replySuggestion();
                break;
            case 10:
            	System.out.println("Generating report...");
            	campList.generateReport();
                break;

            
            case 11:
                System.out.println("Logging out...");
                currentUser.logOut();
                campList.exportData();
                exit = true;
                break;
            
            case 12:
                for (int i = 0; i < 100; i++) System.out.println();
                System.out.println("Exiting...");
                currentUser.logOut();
                campList.exportData();
                System.exit(0);
                break;
            default:
                System.out.println("Unknown error has occured.");
        }
    }
}
