package sce.sc2002.FinalProject;
import java.util.*;

/**
 @author Chong Wen Rong, Chelson
 @version 1.0
 @since 11/2/2023
*/

public class AttendeeMenu extends Menu{

    public AttendeeMenu(Login user, CampList campList){
        super(campList);
        currentUser = user;
    }

	/**
	 * This will print the header of the Camp App.
	 *----------------------------------------------------------------------------------- */
    private void printHeader() {
		System.out.println("+-----------------------------------+");
        System.out.println("|           Welcome to              |");
        System.out.println("|        Awesome Camp App           |");
        System.out.println("+-----------------------------------+");
	}

	/**
	 * This method will display the menu once the user has logged in.
	 *----------------------------------------------------------------------------------- */
    public void runMenu() {
		printHeader();

		while(!exit) {
			display();
			int choice = getMenuChoice();
			performAction(choice);
            if (campList.isCampCommittee()){
                exit = true;
            }
		}
	}

	/**
	 * This will return the choice that the user has selected.
	 *----------------------------------------------------------------------------------- */
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
	 * This is the display of the student's version of menu.
	 *----------------------------------------------------------------------------------- */
    public void display(){
        System.out.println("Student Attendee Portal: ");
        System.out.println();

        String[] choices = {"Change Password",
                            "View Camp",
                            "Register for Camp",
                            "Withdraw from Camp",
                            "Submit Enquiry",
                            "View Enquiry",
                            "Edit Enquiry",
                            "Delete Enquiry",
                            "Log Out",
                            "Exit"};

        for (int i = 0; i < choices.length; i++){
            System.out.println((i + 1) + ". " + choices[i]);
        }
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
                System.out.println("Viewing camps...");
                campList.viewCamp();
                break;
            case 3:
                System.out.println("Registering Camp...");
                campList.registerCamp();
                break;
            
            case 4:
                System.out.println("Withdrawing Camp...");
                campList.withdawFromCamp();
                break;

            case 5:
                System.out.println("Submit Enquiry...");
                campList.createEnquiry();
                break;

            case 6:
                System.out.println("Viewing Enquiry...");
                campList.viewEnquiry();
                break;
            case 7:
                System.out.println("Editing Enquiry...");
                campList.editEnquiry();
                break;
            case 8:
                System.out.print("Deleting enquiry...");
                campList.deletetEnquiry();
                break;
            
            case 9:
                currentUser.logOut();
                campList.exportData();
                exit = true;
                clearScreen();
                break;

            case 10:
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
