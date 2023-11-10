package sce.sc2002.FinalProject;
import java.util.*;

/**

 @author Chong Wen Rong, Chelson
 @version 1.0
 @since 11/2/2023
*/

public class StudentMenu extends Menu{

    public StudentMenu(Login user, CampList campls){
        super(campls);
        currentUser = user;
    }


    private void printHeader() {
		System.out.println("+-----------------------------------+");
        System.out.println("|           Welcome to              |");
        System.out.println("|        Awesome Camp App           |");
        System.out.println("+-----------------------------------+");
	}



    public void runMenu() {
		printHeader();

		while(!exit) {
			display();
			int choice = getMenuChoice();
			performAction(choice);
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
            if (choice < 0 || choice > 7) {
                System.out.println("Choice outside of range. Please chose again.");
            }
        } while (choice < 0 || choice > 7);
        return choice;
    }

    public void display(){
        System.out.println("Student Attendee Portal: ");
        System.out.println();
        System.out.println("1. Change password");
        System.out.println("2. View all camp");
        System.out.println("3. View Regisetered Camps");
        System.out.println("4. Register for Camp");
        System.out.println("5. Exit");
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
                System.out.println("Viewing all camps...");
                //  calls display camp methods
                // 	Can have option to create, edit or delete the camps
                
                break;
            case 3:
                System.out.println("Viewing registerd camps...");
                // calls method to display all create camps
                break;
            case 4:
                System.out.println("Register for camp");
                // calls method to display all enquires to camps created by staff
                // Can reply to enquires
                break;
            case 5:
                System.out.println("Submit Enquiry");
                // calls method to display all suggestions by CC to camps created by staff
                // Can choose to reply to suggestions
                break;
            case 6:
            	System.out.println("Withdraw from camp");
            	// calls method to generate report
            	// has option to choose what kind of report to generate
            	// attendance report; performance report; enquire report
                break;
            case 7:
                System.out.println("Exiting...");
                exit = true;
                break;
            default:
                System.out.println("Unknown error has occured.");
        }
    }

    public void registerCamp(){

    }

    public void viewAvailableCamp(){

    }

    public void viewRegisteredCamp(){

    }
    
    public void chooseCamp(){

    }
}
