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

        

        // TODO: remember to update the exit variable appropriately
		while(!exit) {
			display();
            System.out.print("Your choice: ");
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
        int choice = keyboard.nextInt();
        return choice;
    }

	/**
	 * This is the display of the student's version of menu.
	 *----------------------------------------------------------------------------------- */
    public void display(){
        System.out.println("Student Attendee Portal: ");
        System.out.println();
        System.out.println("1. Change Password");
        System.out.println("2. View all Camp");
        //System.out.println("3. View available Camp");
        System.out.println("4. Register for Camp");
        //System.out.println("5. View Registed Camp");
        System.out.println("6. Withdraw from Camp");
        System.out.println("7. Submit Enquiry");
        System.out.println("8. Edit Enquiry"); // TODO: implement this.
        System.out.println("9. View Reply to Enquiry");
        System.out.println("10. Log out");
        System.out.println("11. Exit");
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
            // case 3:
            //     System.out.println("Viewing avaible camp...");
            //     campList.viewAvailableCamp();
            //     break;
            case 4:
                System.out.println("Registering Camp");
                campList.registerCamp();
                break;
            
            // case 5:
            //     System.out.println("Viewing registered camp...");
            //     campList.viewRegisteredCamp();
            //     break;
            case 6:
                System.out.println("Withdrawing from camp...");
                campList.withdawFromCamp();
                break;
            case 7:
                System.out.println("Submit Enquiry...");
                // TODO: add the Enquiy option.
                break;
            case 8:
                System.out.print("Edit enquiry...");
                // TODO: add edit the enquiry.
                break;
            case 9:
                System.out.println("Viewing reply to enquiry...");
                // TODO: add option
            
            case 10:
                currentUser.logOut();
                // TODO: remember to write the data back to the CSV file
                exit = true;
                break;
            default:
                currentUser.logOut();
                // TODO: remember to write the data back to the CSV file
                System.exit(0);
                break;
        }
    }

}
