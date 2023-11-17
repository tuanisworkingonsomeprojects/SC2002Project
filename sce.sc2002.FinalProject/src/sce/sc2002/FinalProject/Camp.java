package sce.sc2002.FinalProject;

import java.util.*;

public class Camp{
	
	private CampInformation campInfo;
	private Scanner sc = new Scanner(System.in);
	
	public Camp(CampList campList) { //constructor for Camp object
		campInfo = new CampInformation(campList);
	}
	
	public String  getCampName()		{return campInfo.getCampName();}
	public String  getStaffInCharge()	{return campInfo.getStaffInCharge();}
	public boolean getVisibility()		{return campInfo.getVisibility();}
	public Date	   getStartDate()		{return campInfo.getStartDate();}
	public Date    getEndDate()			{return campInfo.getEndDate();}
	public Date	   getClosingDate()		{return campInfo.getClosingDate();}
	public String  getFaculty()			{return campInfo.getFaculty();}
	public int	   getCommitteeSlot()	{return campInfo.getCampCommSlot();}
	public int	   getAttendeeSlot()	{return campInfo.getAttendeeSlot();}
	public String  getLocation()		{return campInfo.getLocation();}
	public ArrayList<Student> getAttendeeList() {return campInfo.getAttendeeList();}
	public ArrayList<Student> getCommitteeList() {return campInfo.getCommitteeList();}

	// A person is a member of a camp if he/she is a committee or attendee of that camp.
	public boolean isMemberOfCamp(Login currentUser)	{return campInfo.isCommittee(currentUser) || campInfo.isAttendee(currentUser);} 

	public boolean isCommittee	   (Login currentUser)	{return campInfo.isCommittee(currentUser);}
	public boolean isAttendee	   (Login currentUser)	{return campInfo.isAttendee(currentUser);}
	public boolean isStaffInCharge (Login currentUser)	{return campInfo.getStaffInCharge().equals(currentUser.getUserid());}
	public boolean isBlackListed   (Login currentUser)	{return campInfo.isBlackListed(currentUser);}
	public boolean isAvailable	   ()	{
		if (getCommitteeSlot() > 0 || getAttendeeSlot() > 0) return true;

		return false;
	}
	

	public static Camp createCamp(Login currentUser, CampList campList){
		Camp newCamp = new Camp(campList);
		newCamp.createCampManager(currentUser);
		return newCamp;
	}

	public void createCampManager(Login currentUser) {
		if(currentUser.getRole().equals("staff")) {
			System.out.print("Camp name: ");
			campInfo.setCampName(sc.nextLine());
			
			System.out.print("Start date (dd/MM/yyyy): ");
			campInfo.setStartDate(sc.nextLine());
			
			System.out.print("End date (dd/MM/yyyy): ");
			campInfo.setEndDate(sc.nextLine());
			
			System.out.print("Registration closing date (dd/MM/yyyy): ");
			campInfo.setClosingDate(sc.nextLine());
			
			System.out.print("School: ");
			campInfo.setAvailableTo(sc.nextLine());
			
			System.out.print("Location: ");
			campInfo.setLocation(sc.nextLine());
			
			System.out.print("Slots: ");
			campInfo.setAttendeeSlot(sc.nextInt());

			System.out.print("Camp Detail: ");
			campInfo.setDetail(sc.nextLine());
			
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
				displayEditCamp();
				choice = getMenuChoice();
				performActionEditCamp(choice);
			}
			else {
				System.out.println("You don't have enough authority to edit camp!");
				break;
			}
		}
	}

	public void displayEditCamp() {
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
	
	private void performActionEditCamp(int choice) {
        switch (choice) {

            // Change password option
            case 1:
                System.out.print("New camp name: ");
                campInfo.setCampName(sc.next());
                break;
            case 2: 
            	System.out.print("New start date (dd/MM/yyyy): ");
            	campInfo.setStartDate(sc.next());
                break;
            case 3:
            	System.out.print("New end date (dd/MM/yyyy): ");
            	campInfo.setEndDate(sc.next());
                break;
            case 4:
            	System.out.print("New Registration closign date (dd/MM/yyyy): ");
            	campInfo.setClosingDate(sc.next());
                break;
            case 5:
            	System.out.print("New School: ");
            	campInfo.setAvailableTo(sc.next());
                break;
            case 6:
            	System.out.print("New location: ");
            	campInfo.setLocation(sc.next());
            	break;
            case 7:
            	System.out.print("New slots: ");
            	campInfo.setAttendeeSlot(sc.nextInt());
            	break;

			case 8:
				System.out.print("New Detail: ");
				campInfo.setDetail(sc.nextLine());
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
	
	public boolean allowToView(Login currentUser){

		// If it is staff the visibility is always true
		if (currentUser.getRole().equals("staff")) return true;

		else if (getVisibility()){
			// When the scope of the camp is whole NTU it also return true
			if (getFaculty().equals("NTU")) return true;

			// Check if the camp is visible to corresponding student.
			else if(currentUser.getFaculty().equals(getFaculty())) return true;
		}
		return false;
	}

	public void printAttendeeList(Login currentUser){
		if (currentUser.getRole().equals("staff")){
			System.out.println("Student Attendee List:");

			for (int i = 0; i < campInfo.getAttendeeList().size(); i++){
				Student currentStudent = campInfo.getAttendeeList().get(i);
				System.out.println(i + ". " + currentStudent.getID());
			}
		}
		else {
			System.out.println("You don't have enough authority to view this list!");
		}

		System.out.println("Hit Enter to go back!");
		sc.nextLine();

		// Pseudo clear screen
		for (int i = 0; i < 100; i++){
			System.out.println();
		}
	}

	public void printCommitteList(Login currentUser){
		if (currentUser.getRole().equals("staff")){
			System.out.println("Student Committee List:");

			for (int i = 0; i < campInfo.getCommitteeList().size(); i++){
				Student currentStudent = campInfo.getCommitteeList().get(i);
				System.out.println(i + ". " + currentStudent.getID());
			}
		}
		else {
			System.out.println("You don't have enough authority to view this list!");
		}

		System.out.println("Hit Enter to go back!");
		sc.nextLine();

		// Pseudo clear screen
		for (int i = 0; i < 100; i++){
			System.out.println();
		}
	}


	public void registerCamp(Login currentUser){
		System.out.println("Choose your role in Camp:");
		System.out.println("1. Camp Attendee");
		System.out.println("2. Camp Committee");
		System.out.print("Your choice: ");
		int choice = sc.nextInt();

		switch (choice) {
			case 1:
				campInfo.attendeeRegister(currentUser);
				break;
			case 2:
				campInfo.committeeRegister(currentUser);
				break;
			default:
				System.out.println("Inappropriate choice! Going back...");
				break;
		}
	}

	public void withdawFromCamp(Login currentUser){
		campInfo.attendeeWithdrawal(currentUser);
	}

	public void createEnquiry(Login currentUser){

		if (isCommittee(currentUser)){
			System.out.println("Camp committee cannot create Enquiry for this camp");
			return;
		}

		for (int i = 0; i < getAttendeeList().size(); i++){
			Student student_ith = getAttendeeList().get(i);

			if (student_ith.getID().equals(currentUser.getUserid())){
				System.out.print("Subject / Title: ");
				String subject = sc.nextLine();
				System.out.println("Your Enquiry (in one line): ");
				String description = sc.nextLine();



				Enquiry enquiry = new Enquiry(this, student_ith, subject, description);

				campInfo.addEnquiry(enquiry);
				return;
			}
		}


	}

	public void viewEnquiry(Login currentUser){

		if (campInfo.getEnquiries().size() == 0){
			System.out.println("You don't have any Enquiries for this camp");
			return;
		}

		

		for (int i = 0; i < campInfo.getEnquiries().size(); i++){
			Enquiry enquiry_ith = campInfo.getEnquiries().get(i);

			// Check if the enquiry is belong to the currentUser
			if (enquiry_ith.getAuthor().getID().equals(currentUser.getUserid())){
				System.out.println("ID: " + enquiry_ith.getEnquiryID() + ". " + enquiry_ith.getSubject());
			}
		}

		
	}

	public void editEnquiry(Login currentUser){
		if (campInfo.getEnquiries().size() == 0){
			System.out.println("You don't have any Enquiries for this camp");
			return;
		}

		viewEnquiry(currentUser);

		System.out.print("Enquiry ID: ");

		int enquiryID = sc.nextInt();
		sc.nextLine();

		for (int i = 0; i < campInfo.getEnquiries().size(); i++){
			Enquiry enquiry_ith = campInfo.getEnquiries().get(i);

			if (enquiry_ith.getAuthor().getID().equals(currentUser.getUserid())){
				if (enquiryID == enquiry_ith.getEnquiryID()){
					System.out.println("1. Edit Subject / Title");
					System.out.println("2. Edit description");
					
					System.out.print("Your choice: ");
					int choice = sc.nextInt();
					sc.nextLine();

					switch (choice) {
						case 1:
							System.out.print("Subject / Title: ");
							String title = sc.nextLine();
							enquiry_ith.setSubject(title);;
							break;
					
						case 2:
							System.out.println("Description: ");
							String description = sc.nextLine();
							enquiry_ith.setDescription(description);
							break;

						default:
							System.out.println("Unknow Error has occured");
							break;
					}

					return;
				}
			}
		}
		
	}

	public void deleteEnquiry(Login currentUser){
		
		if (campInfo.getEnquiries().size() == 0){
			System.out.println("You don't have any Enquiries for this camp");
			return;
		}

		viewEnquiry(currentUser);

		System.out.print("Enquiry ID: ");

		int enquiryID = sc.nextInt();
		sc.nextLine();

		for (int i = 0; i < campInfo.getEnquiries().size(); i++){
			Enquiry enquiry_ith = campInfo.getEnquiries().get(i);

			if (enquiry_ith.getAuthor().getID().equals(currentUser.getUserid())){
				if (enquiryID == enquiry_ith.getEnquiryID()){
					if (enquiry_ith.getResolved()){
						System.out.println("You cannot delete the answered enquiry");
						return;
					}

					System.out.print("Confirm deletion (Y / N):");
					String choice = sc.nextLine();

					if (choice == "Y"){
						campInfo.getEnquiries().remove(enquiryID);
						return;
					}
					else {
						return;
					}
				}
			}
		}

		System.out.println("There is no camp with such ID or of your enquiry");
	}

}


