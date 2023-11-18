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

        String[] options = {"Change Password",
                            "View Camps",
                            "Register for Camp",
                            "Withdraw from Camp",
                            "Submit Suggestion",
                            "View Suggestions",
                            "Edit Suggestion",
                            "Delete Suggestion",
                            "Create Enquiry",
                            "View Enquiries",
                            "Delete Enquiry",
                            "Reply Enquiry",
                            "Generate Camp's report",
                            "Log out",
                            "Exit"};

        for (int i = 0; i < options.length; i ++){
            System.out.println((i + 1) + options[i]);
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
                campList.viewCamp();
                break;
            case 3:
                System.out.println("Registering camp...");
                campList.registerCamp();
                break;
            case 4:
                System.out.println("Withdrawing from camp...");
                campList.withdawFromCamp();
                break;
            case 5:
                System.out.println("Submitting Suggestion...");
                campList.createSuggestion();
                break;
            
            case 6:
                System.out.println("Viewing Suggestion...");
                campList.viewSuggestion();
                break;
            
            case 7:
                System.out.println("Editing Suggestion...");
                campList.editSuggestion();
                break;

            case 8:
                System.out.println("Deleting Suggestion...");
                campList.editSuggestion();
                break;
            
            case 9:
                System.out.println("Creating Enquiry");
                campList.createEnquiry();
                break;
            
            case 10:
                System.out.println("Viewing Camp enquiry...");
                campList.viewEnquiry();
                break;
            
            case 11:
                System.out.println("Deleting Camp report...");
                campList.deletetEnquiry();
                break;

            case 12:
                System.out.println("Replying Enquiry...");
                campList.replyEnquiry();
                break;

            case 13:
                System.out.println("Generate Camp report");
                // TODO: generate the report


            case 14:
                System.out.println("Logging out");
                // TODO: remember to write the databack to the CSV file.
                exit = true;
                currentUser.logOut();
                break;

            case 15:
                // TODO: remember to write the databack to the CSV file.
                currentUser.logOut();
                System.exit(0);
                break;

            default:
                break;
        }
    }

    
}