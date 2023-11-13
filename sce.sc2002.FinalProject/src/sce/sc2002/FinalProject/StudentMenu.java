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

        boolean quit;

        // TODO: remember to update the exit variable appropriately
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
        System.out.println("3. View available Camp");
        System.out.println("3. Register for Camp");
        System.out.println("4. View Registed Camp");
        System.out.println("5. Withdraw from Camp");
        System.out.println("6. Submit Enquiry");
        System.out.println("7. View Reply to Enquiry");

        if (campList.isCampCommittee()){
            System.out.println("8. View camp's detail");
            System.out.println("9. Submit suggestion");
            System.out.println("10. Edit suggestion");
            System.out.println("11. Delete Suggestion");
            System.out.println("12. View camp's enquiry");
            System.out.println("13. Generate camp's report");
            System.out.println("14. Log out");
            System.out.println("15. Exit");
        }
        else {
            System.out.println("8. Log out");
            System.out.println("9. Exit");
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
                System.out.println("Viewing all camps...");
                campList.viewAllCamp();
                break;
            case 3:
                System.out.println("Viewing avaible camp...");
                campList.viewAvailableCamp();
                break;
            case 4:
                System.out.println("Viewing register camp...");
                campList.viewRegisteredCamp();
                break;
            case 5:
                System.out.println("Withdrawing from camp...");
                campList.withdawFromCamp();
                break;
            case 6:
                // TODO: add the Enquiy option.
                break;
            case 7:
                // TODO: add view reply the enquiry.
                break;
            
            case 8:
                if (campList.isCampCommittee()) performCommitteeAction(choice);
                else exit = true;

            default:
                if (campList.isCampCommittee()) performCommitteeAction(choice);
                // TODO: remember to write the data back to the CSV file
                System.exit(0);
        }
    }

    private void performCommitteeAction(int choice){
        switch(choice){
            case 8:
                System.out.println("Viewing Camp Details...");
                campList.viewCampDetail();
                break;
            
            case 9:
                System.out.println("Submitting Suggestion...");
                // TODO: submitting suggestion option
                break;

            case 10:
                System.out.println("Editing Suggestion...");
                // TODO: editing suggestion option
                break;
            
            case 11:
                System.out.println("Deleting Suggestion...");
                // TODO: deleting suggestion option
                break;
            
            case 12:
                System.out.println("Viewing Camp enquiry...");
                // TODO: view camp enquiry option
                break;
            
            case 13:
                System.out.println("Generating Camp report...");
                // TODO: generate camp report option
                break;

            case 14:
                System.out.println("Logging out");
                exit = true;
                currentUser.logOut();
                break;

            case 15:
                // TODO: remember to write the databack to the CSV file.
                System.exit(0);
                break;

            default:
                break;
        }
    }


}
