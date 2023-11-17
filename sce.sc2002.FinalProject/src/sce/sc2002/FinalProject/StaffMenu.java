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
		System.out.println("Staff Portal: ");
        System.out.println();
		System.out.println("1. Change password");
		System.out.println("2. View all camps");              
        System.out.println("3. Create camp");                 
		System.out.println("4. View created camps");          
		System.out.println("5. View Enquiries");   // TODO: He can view enquiries from the camp HE CREATED
        System.out.println("6. Reply Enquiries");                                                                                 
		System.out.println("7. Suggestions");	                												             
		System.out.println("8. Generate report"); 	        													            
        System.out.println("9. Log out"); 
        System.out.println("10. exit");                    
	}
	

	
    private int getMenuChoice() {
        Scanner keyboard = new Scanner(System.in);
        // int choice = -1;
        // do {
        //     System.out.print("Enter your choice: ");
        //     try {
        //         choice = Integer.parseInt(keyboard.nextLine());
        //     } catch (NumberFormatException e) {
        //         System.out.println("Invalid selection. Numbers only please.");
        //     }
        //     if (choice < 0 || choice > 4) {
        //         System.out.println("Choice outside of range. Please chose again.");
        //     }
        // } while (choice < 0 || choice > 4);
        return keyboard.nextInt();
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
                System.out.println("Creating camp...");
                campList.createCamp();
                break;
            case 4:
                System.out.println("Viewing all created camps...");
                campList.viewCreatedCamp();
                break;
            case 5:
                System.out.println("Viewing enquries...");
                // calls method to display all enquires to camps created by staff
                // Can reply to enquires
                break;
            case 6:
                System.out.println("Viewing suggestions...");
                // calls method to display all suggestions by CC to camps created by staff
                // Can choose to reply to suggestions
                break;
            case 7:
            	System.out.println("Which report would you like to generate?");
            	// calls method to generate report
            	// has option to choose what kind of report to generate
            	// attendance report; performance report; enquire report
            case 8:
                System.out.println("Logging out...");
                currentUser.logOut();
                exit = true;
                break;
                // calls method to log out
            default:
                System.out.println("Unknown error has occured.");
        }
    }
}
