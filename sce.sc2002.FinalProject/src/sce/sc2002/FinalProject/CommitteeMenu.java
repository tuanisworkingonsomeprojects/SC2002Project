package sce.sc2002.FinalProject;
import java.util.*;

public class CommitteeMenu extends Menu{


    public CommitteeMenu(Login user, CampList campList){
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
        System.out.println("Student Committee Portal: ");
        System.out.println();
        System.out.println("1. Change Password");
        System.out.println("2. View all Camp");
        System.out.println("3. View available Camp");
        System.out.println("3. Register for Camp");
        System.out.println("4. View Registed Camp");
        System.out.println("5. Withdraw from Camp");
        System.out.println("6. View camp's detail");
        System.out.println("7. Submit suggestion");
        System.out.println("8. Edit suggestion");
        System.out.println("9. Delete Suggestion");
        System.out.println("10. View camp's enquiry");
        System.out.println("11. Generate camp's report");
        System.out.println("12. Log out");
        System.out.println("13. Exit");
        // TODO: the camp committee can create enquiry for other camps.
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
                System.out.println("Viewing Camp Details...");
                campList.viewCampDetail();
                break;
            
            case 7:
                System.out.println("Submitting Suggestion...");
                // TODO: submitting suggestion option
                break;

            case 8:
                System.out.println("Editing Suggestion...");
                // TODO: editing suggestion option
                break;
            
            case 9:
                System.out.println("Deleting Suggestion...");
                // TODO: deleting suggestion option
                break;
            
            case 10:
                System.out.println("Viewing Camp enquiry...");
                // TODO: view camp enquiry option
                break;
            
            case 11:
                System.out.println("Generating Camp report...");
                // TODO: generate camp report option
                break;

            case 12:
                System.out.println("Logging out");
                exit = true;
                currentUser.logOut();
                break;

            case 13:
                // TODO: remember to write the databack to the CSV file.
                currentUser.logOut();
                System.exit(0);
                break;

            default:
                break;
        }
    }

    
}