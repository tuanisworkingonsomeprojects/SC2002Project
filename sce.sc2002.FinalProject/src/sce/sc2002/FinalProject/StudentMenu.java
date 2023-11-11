package sce.sc2002.FinalProject;
import java.util.*;

/**
 @author Chong Wen Rong, Chelson
 @version 1.0
 @since 11/2/2023
*/

public class StudentMenu extends Menu{

    private ArrayList<Student> registeredStudents = new ArrayList<>();

    public StudentMenu(Login user, CampList campList){
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
            }
            if (choice < 0 || choice > 8) {
                System.out.println("Choice outside of range. Please chose again.");
            }
        } while (choice < 0 || choice > 8);
        return choice;
    }

	/**
	 * This is the display of the student's version of menu.
	 *----------------------------------------------------------------------------------- */
    public void display(){
        System.out.println("Student Attendee Portal: ");
        System.out.println();
        System.out.println("1. Change password");
        System.out.println("2. View all Camp");
        System.out.println("3. View Registered Camp");
        System.out.println("4. Register for Camp");
        System.out.println("5. Withdraw from Camp");
        System.out.println("6. Submit Enquiry");
        System.out.println("7: Exit~");
    }

    /**
	 * This method perform the actions that the student has selected
	 *----------------------------------------------------------------------------------- */
    private void performAction(int choice) {
        switch (choice) {

            // Change password option
            case 1: 
                clearScreen();
                System.out.println("Change password Menu:");
                currentUser.changePassword();
                break;
            case 2: 
                System.out.println("Viewing all camps");  
                //Function to view all camps
                break;
            case 3:
                System.out.println("Viewing all Registered Camps");
                //Function to view all registered camps
                break;
            case 4:
                 //Function to register for camp
                 System.out.println("Registered for camp!");
                exit = true;
                break;
            case 5:
                //Function to withdraw from camp
                System.out.println("Withdrawed from camp!");
            case 6:
                //Function to submit enquiry
                System.out.println("Enquiry Submitted!");
            case 7:
                System.out.println("Exiting program!");
                exit = true;
            default:
                System.out.println("Unknown error has occured.");
        }
    }

    /**
	 * This method register the student into the camp.
	 *----------------------------------------------------------------------------------- */
    public void registerCamp(Login currentUser){
        if(currentUser.getRole().equals("student")){
            //First Show all camp the student is able to register for
            //Then submit to register for the camp
        }
    }

	/**
	 * This method will display all available camps for the student to view.
	 *----------------------------------------------------------------------------------- */
    public void viewAllCamp(Login currentUser){
        campList.viewAllCamp();
    }
}
