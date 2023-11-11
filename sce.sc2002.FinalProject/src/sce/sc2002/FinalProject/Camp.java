package sce.sc2002.FinalProject;

import java.util.*;

public class Camp{
	
	private CampInformation campInfo;
	private CampList campList;
	
	public Camp(CampList campList) { //constructor for Camp object
		this.campList = campList;
		campInfo = new CampInformation(campList);
	}
	
	public String  getCampName()		{return campInfo.getCampName();}
	public String  getStaffInCharge()	{return campInfo.getStaffInCharge();}
	public boolean getVisibility()		{return campInfo.getVisibility();}
	public Date	   getStartDate()		{return campInfo.getStartDate();}
	public Date    getEndDate()			{return campInfo.getEndDate();}
	public Date	   getClosingDate()		{return campInfo.getClosingDate();}

	// A person is a member of a camp if he/she is a committee or attendee of that camp.
	public boolean isMemberOfCamp(Login currentUser)	{return campInfo.isCommittee(currentUser) || campInfo.isAttendee(currentUser);} 
	
	Scanner sc = new Scanner(System.in);


	public static Camp createCamp(Login currentUser, CampList campList){
		Camp newCamp = new Camp(campList);
		newCamp.createCampManager(currentUser);
		return newCamp;
	}

	public void createCampManager(Login currentUser) {
		if(currentUser.getRole().equals("staff")) {
			System.out.println("Camp name: ");
			campInfo.setCampName(sc.next());
			
			System.out.print("Start date (dd/MM/yyyy): ");
			campInfo.setStartDate(sc.next());
			
			System.out.print("End date (dd/MM/yyyy): ");
			campInfo.setEndDate(sc.next());
			
			System.out.print("Registration closing date (dd/MM/yyyy): ");
			campInfo.setClosingDate(sc.next());
			
			System.out.print("School: ");
			campInfo.setAvailableTo(sc.next());
			
			System.out.print("Location: ");
			campInfo.setLocation(sc.next());
			
			System.out.print("Slots: ");
			campInfo.setAttendeeSlot(sc.nextInt());
			
			System.out.print("Description: ");
			campInfo.setDescription(sc.next());
			
			System.out.print("Available?(Y/N) ");
			campInfo.setVisibility(sc.next().charAt(0));
			
			campInfo.setStaffInCharge(currentUser);
		}
		else {
			System.out.println("You do not have authorisation to create camps");
		}
		
	}
	
	public void editCamp(Login currentUser) {
		
		int choice = 0;
		while (choice < 10){
			if(currentUser.getRole().equals("staff")) {
				display();
				choice = getMenuChoice();
				performAction(choice);
			}
		}
	}

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
		System.out.println("10. <<Back");		
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
                campInfo.setCampName(sc.next());
                break;
            case 2: 
            	System.out.println("New start date (dd/MM/yyyy): ");
            	campInfo.setStartDate(sc.next());
                break;
            case 3:
            	System.out.println("New end date (dd/MM/yyyy): ");
            	campInfo.setEndDate(sc.next());
                break;
            case 4:
            	System.out.println("New Registration closign date (dd/MM/yyyy): ");
            	campInfo.setClosingDate(sc.next());
                break;
            case 5:
            	System.out.println("New School: ");
            	campInfo.setAvailableTo(sc.next());
                break;
            case 6:
            	System.out.println("New location: ");
            	campInfo.setLocation(sc.next());
            	break;
            case 7:
            	System.out.println("New slots: ");
            	campInfo.setAttendeeSlot(sc.nextInt());
            	break;
            case 8:
            	System.out.println("New description: ");
            	campInfo.setDescription(sc.next());
            	break;
            case 9:
            	System.out.println("Availability? (Y/N): ");
            	campInfo.setVisibility(sc.next().charAt(0));
            	break;
			case 10:
				System.out.println("Exiting...");

				for (int i = 0; i < 100; i++){
					System.out.println();
				}
				break;

			
            default:
                System.out.println("Unknown error has occured.");
        }
    }
	


}

