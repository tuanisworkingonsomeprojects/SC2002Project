package sce.sc2002.FinalProject;

import java.util.*;

public class Camp extends Menu {
	
	private CampInformation campInfo;
	
	public Camp() { //constructor for Camp object
		campInfo = new CampInformation();
	}
	
	public void editCamp(Login currentUser) {
		if(currentUser.getRole().equals("staff")) {
			display();
			int choice = getMenuChoice();
			performAction(choice);
		}
	}
	
	
	Scanner sc = new Scanner(System.in);
	
	public void createCamp(Login currentUser) {
		if(currentUser.getRole().equals("staff")) {
			System.out.println("Camp name: ");
			campInfo.setcampName(sc.next());
			
			System.out.println("Start date: ");
			campInfo.setstartDate(sc.nextInt());
			
			System.out.println("End date: ");
			campInfo.setendDate(sc.nextInt());
			
			System.out.println("Registration closing date: ");
			campInfo.setclosingDate(sc.nextInt());
			
			System.out.println("School: ");
			campInfo.setavailableTo(sc.next());
			
			System.out.println("Location: ");
			campInfo.setlocation(sc.next());
			
			System.out.println("Slots: ");
			campInfo.setattendeeSlot(sc.nextInt());
			
			System.out.println("Description: ");
			campInfo.setdescription(sc.next());
			
			System.out.println("Available?(Y/N) ");
			campInfo.setvisibility(sc.next().charAt(0));
			
			campInfo.setstaffInCharge(currentUser);
		}
		else {
			System.out.println("You do not have authorisation to create camps");
		}
		
	}
	


	@Override
	public void runMenu() {
		while(!exit) {
			display();
			int choice = getMenuChoice();
			performAction(choice);
		}
	}

	@Override
	public void display() {
		System.out.println("What would you like to edit?");
		System.out.println();
		System.out.println("1.	Camp name");
		System.out.println("2.	Start date");
		System.out.println("3.	End date");
		System.out.println("4.	Registration closing date");
		System.out.println("5.	School");
		System.out.println("6.	Location");
		System.out.println("7.	Slots");
		System.out.println("8.	Description");
		System.out.println("9.	Available");		
		
	}


	public int getMenuChoice() {
        Scanner keyboard = new Scanner(System.in);
        int choice = -1;
        do {
            System.out.print("Enter your choice: ");
            try {
                choice = Integer.parseInt(keyboard.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid selection. Numbers only please.");
            }
            if (choice < 0 || choice > 9) {
                System.out.println("Choice outside of range. Please chose again.");
            }
        } while (choice < 0 || choice > 9);
        return choice;
    }
	

	private void performAction(int choice) {
        switch (choice) {

            // Change password option
            case 1:
                System.out.println("New camp name: ");
                campInfo.setcampName(sc.next());
                break;
            case 2: 
            	System.out.println("New start date: ");
            	campInfo.setstartDate(sc.nextInt());
                break;
            case 3:
            	System.out.println("New end date: ");
            	campInfo.setendDate(sc.nextInt());
                break;
            case 4:
            	System.out.println("New Registration closign date: ");
            	campInfo.setclosingDate(sc.nextInt());
                break;
            case 5:
            	System.out.println("New School: ");
            	campInfo.setavailableTo(sc.next());
                break;
            case 6:
            	System.out.println("New location: ");
            	campInfo.setlocation(sc.next());
            	break;
            case 7:
            	System.out.println("New slots: ");
            	campInfo.setattendeeSlot(sc.nextInt());
            	break;
            case 8:
            	System.out.println("New description: ");
            	campInfo.setdescription(sc.next());
            	break;
            case 9:
            	System.out.println("Availability? (Y/N): ");
            	campInfo.setvisibility(sc.next().charAt(0));
            	break;
            default:
                System.out.println("Unknown error has occured.");
        }
    }
	
}

