package sce.sc2002.FinalProject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Camp{
	
	private CampInformation campInfo;
	private Scanner sc = new Scanner(System.in);
	
	public Camp(CampList campList) { //constructor for Camp object
		campInfo = new CampInformation(campList);
	}

	public Camp(CampList	campList,
				String		campName,
				String		startDate,
				String		endDate,
				String		closingDate,
				String		faculty,
				String		location,
				int			attendeeSlot,
				int			committeeSlot,
				String		detail,
				boolean     visibility,
				String		staffInCharge)
	{
		campInfo = new CampInformation(campList);
		campInfo.setCampName(campName);
		campInfo.setStartDate(startDate);
		campInfo.setEndDate(endDate);
		campInfo.setClosingDate(closingDate);
		campInfo.setAvailableTo(faculty);
		campInfo.setLocation(location);
		campInfo.setAttendeeSlot(attendeeSlot);
		campInfo.setCampCommSlot(committeeSlot);
		campInfo.setDetail(detail);
		campInfo.setVisibility(visibility);
		campInfo.setStaffInCharge(staffInCharge);

	}




	public void addCommittee(Committee new_committee) {campInfo.getCommitteeList().add(new_committee);}
	public void addAttendee(Student new_attendee)     {campInfo.getAttendeeList().add(new_attendee);}
	public void addBlacklist(Student new_blacklist)   {campInfo.getBlackList().add(new_blacklist);}

	public void addEnquiry(Enquiry new_enquiry)		  {campInfo.getEnquiries().add(new_enquiry);}
	public void addSuggesstion(Suggestion new_suggestion) {campInfo.getSuggestions().add(new_suggestion);}

	
	public String  getCampName()		{return campInfo.getCampName();}
	public String  getStaffInCharge()	{return campInfo.getStaffInCharge();}
	public boolean getVisibility()		{return campInfo.getVisibility();}
	public Date	   getStartDate()		{return campInfo.getStartDate();}
	public Date    getEndDate()			{return campInfo.getEndDate();}
	public Date	   getClosingDate()		{return campInfo.getClosingDate();}
	public String  getFaculty()			{return campInfo.getFaculty();}
	public int	   getCommitteeSlot()	{return campInfo.getCampCommSlot();}
	public int	   getAttendeeSlot()	{return campInfo.getAttendeeSlot();}
	public String  getDescription()			{return campInfo.getDescription();}
	public String  getLocation()		{return campInfo.getLocation();}
	public ArrayList<Student> getAttendeeList() {return campInfo.getAttendeeList();}
	public ArrayList<Student> getCommitteeList(){return campInfo.getCommitteeList();}
	public ArrayList<Student> getBlackList()	{return campInfo.getBlackList();}
	public ArrayList<Enquiry> getEnquiries()	{return campInfo.getEnquiries();}
	public ArrayList<Suggestion> getSuggestions() {return campInfo.getSuggestions();}

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

			
			
			do {
				System.out.print("End date (dd/MM/yyyy): ");
				campInfo.setEndDate(sc.nextLine());
				if (getEndDate().before(getStartDate())){
					System.out.println("End date cannot be set before start date!\n");
				}
			} while (getEndDate().before(getStartDate()));
			
			do {
				System.out.print("Registration closing date (dd/MM/yyyy): ");
				campInfo.setClosingDate(sc.nextLine());
				if (campInfo.getClosingDate().after(campInfo.getClosingDate())){
					System.out.println("Closing date cannot be set after closing date\n");
				}

			} while(campInfo.getClosingDate().after(campInfo.getClosingDate()));
			
			
			System.out.print("School: ");
			campInfo.setAvailableTo(sc.nextLine());
			
			System.out.print("Location: ");
			campInfo.setLocation(sc.nextLine());
			
			System.out.print("Slots: ");
			campInfo.setAttendeeSlot(sc.nextInt());
			sc.nextLine(); 

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

	private int getMenuChoice() {
        Scanner keyboard = new Scanner(System.in);
        int choice = -1;
        do {
            System.out.print("Enter your choice: ");
            try {
                choice = Integer.parseInt(keyboard.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid selection. Numbers only please.");
                continue;
            }

            if (choice < 0) System.out.println("Valid number only");

        } while (choice < 0);
        return choice;
    }
	
	private void performActionEditCamp(int choice) {
        switch (choice) {

            // Change password option
            case 1:
                System.out.print("New camp name: ");
                campInfo.setCampName(sc.nextLine());
                break;
            case 2: 
            	System.out.print("New start date (dd/MM/yyyy): ");
				do{
					campInfo.setStartDate(sc.nextLine());
					if (getStartDate().after(getClosingDate())){
						System.out.println("Start day cannot be set after closing date\n");
					}
				}while(getStartDate().after(getClosingDate()));

                break;
            case 3:
            	System.out.print("New end date (dd/MM/yyyy): ");
				do {
					campInfo.setEndDate(sc.nextLine());
					if (getEndDate().before(getClosingDate())){
						System.out.println("End date cannot be set before closing date\n");
					}

					if (getEndDate().before(getStartDate())){
						System.out.println("End date cannot be set befor start date\n");
					}
				} while(getEndDate().before(getClosingDate()) || getEndDate().before(getStartDate()));
            	
                break;
            case 4:
            	System.out.print("New Registration closing date (dd/MM/yyyy): ");
				do {
					campInfo.setClosingDate(sc.nextLine());

					if (getClosingDate().after(getClosingDate())){
						System.out.println("Closing date cannot be set after end date");
					}
				} while (getClosingDate().after(getClosingDate()));
            	
                break;
            case 5:
            	System.out.print("New School: ");
            	campInfo.setAvailableTo(sc.nextLine());
                break;
            case 6:
            	System.out.print("New location: ");
            	campInfo.setLocation(sc.nextLine());
            	break;
            case 7:
            	System.out.println("New slots: ");
            	campInfo.setAttendeeSlot(getMenuChoice());
            	break;

			case 8:
				System.out.print("New Detail: ");
				campInfo.setDetail(sc.nextLine());
				break;

            case 9:
				if (campInfo.getAttendeeList().size() > 0 || campInfo.getCommitteeList().size() > 0){
					System.out.println("You cannot change a vailability on registered camp!");
					break;
				}
            	System.out.println("Availability? (Y/N): ");
            	campInfo.setVisibility(sc.nextLine().charAt(0));
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
		if (isCommittee(currentUser) || isAttendee(currentUser)){
			System.out.println("You have registered for this camp!");
			return;
		}

		System.out.println("Choose your role in Camp:");
		System.out.println("1. Camp Attendee");
		System.out.println("2. Camp Committee");
		int choice = getMenuChoice();

		switch (choice) {
			case 1:
				campInfo.attendeeRegister(currentUser);
				break;
			case 2:
				campInfo.committeeRegister(currentUser);
				break;
			default:
				System.out.println("Choice out of range!");
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


		//if (getEnquiries().size() == 0) sc.nextLine();
		System.out.print("Subject / Title: ");
		String subject = sc.nextLine();
		System.out.print("Your Enquiry (in one line): ");
		String description = sc.nextLine();



		Enquiry enquiry = new Enquiry(new Student(currentUser.getUserid(), currentUser.getFaculty()), subject, description);

		campInfo.addEnquiry(enquiry);
		return;
			
		


	}

	public void viewEnquiry(Login currentUser){
		System.out.println("What do you want to view:");
		System.out.println("1. View all Enquiry");
		System.out.println("2. View Enquiry Detail");

		int choice = getMenuChoice();

		switch (choice){
			case 1:
				viewAllEnquiry(currentUser);
				break;

			case 2:
				viewEnquiryDetail(currentUser);
				break;

			default:
				System.out.println("Choice out of range!");
		}
	}

	public void viewAllEnquiry(Login currentUser){

		
		int test_idx = 0;
		

		for (int i = 0; i < campInfo.getEnquiries().size(); i++){
			Enquiry enquiry_ith = campInfo.getEnquiries().get(i);

			

			if (isStaffInCharge(currentUser) || isCommittee(currentUser)){
				System.out.print("ID: " + enquiry_ith.getEnquiryID() + " ----- Subject: " + enquiry_ith.getSubject());
				if (enquiry_ith.getResolved()) System.out.println(" [solved]");
				else System.out.println();
				test_idx++;
			}

			else {
				// Check if the enquiry is belong to the currentUser
				if (enquiry_ith.getAuthor().getID().equals(currentUser.getUserid())){
					System.out.print("ID: " + enquiry_ith.getEnquiryID() + " ----- Subject: " + enquiry_ith.getSubject());

					if (enquiry_ith.getResolved()) System.out.println(" [solved]");
					else System.out.println();
					test_idx++;
				}
				
			}
		}

		if (test_idx == 0) System.out.println("There is no viewable enquiry in this camp");

	}

	public void viewEnquiryDetail(Login currentUser){

		System.out.print("Enquiry ID: ");
		int enquriyID = getMenuChoice();
		

		for (int i = 0; i < campInfo.getEnquiries().size(); i++){
			Enquiry enquiry_ith = campInfo.getEnquiries().get(i);

			if (enquiry_ith.getEnquiryID() == enquriyID){

				if (isStaffInCharge(currentUser) || isCommittee(currentUser)){
					System.out.println("ID: " + enquiry_ith.getEnquiryID() + ". " + enquiry_ith.getSubject());
					System.out.println("Description: " + enquiry_ith.getDescription());
					if (enquiry_ith.getResolved()){
						System.out.println("Reply: " + enquiry_ith.getReply());
					}
					return;
				}
				else {	
					if (enquiry_ith.getAuthor().getID().equals(currentUser.getUserid())){
						System.out.println("ID: " + enquiry_ith.getEnquiryID() + ". " + enquiry_ith.getSubject());
						System.out.println("Description: " + enquiry_ith.getDescription());
						if (enquiry_ith.getResolved()){
							System.out.println("Reply: " + enquiry_ith.getReply());
						}
						return;
					}
				}
			}
			
		}

		System.out.println("There is no viewable enquiry in this camp");
	}

	public void editEnquiry(Login currentUser){

		viewAllEnquiry(currentUser);

		System.out.print("Enquiry ID: ");

		int enquiryID = sc.nextInt();
		sc.nextLine();

		for (int i = 0; i < campInfo.getEnquiries().size(); i++){
			Enquiry enquiry_ith = campInfo.getEnquiries().get(i);

			if (enquiry_ith.getAuthor().getID().equals(currentUser.getUserid())){
				if (enquiryID == enquiry_ith.getEnquiryID()){
					if (enquiry_ith.getResolved()) {
						System.out.println("You cannot edit resolved enquiry");
						return;
					}

					System.out.println("Edit Options:");
					System.out.println("1. Edit Subject / Title");
					System.out.println("2. Edit description");
					
					System.out.print("Your choice: ");
					int choice = getMenuChoice();

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

		System.out.println("You don't have any enquiry for this camp");
		
	}

	public void deleteEnquiry(Login currentUser){

		viewAllEnquiry(currentUser);

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

		System.out.println("There you have no enquiry for this camp");
	}

	public void replyEnquiry(Login currentUser){
		viewAllEnquiry(currentUser);

		System.out.print("Enquiry ID: ");
		int enquiryID = getMenuChoice();


		for (int i = 0; i < campInfo.getEnquiries().size();i ++){
			Enquiry enquiry_ith = campInfo.getEnquiries().get(i);

			

			if (enquiryID == enquiry_ith.getEnquiryID()){
				if (enquiry_ith.getResolved()){
					System.out.println("You cannot reply an resolved enquiry!");
					return;
				}

				System.out.print("Your reply: ");
				String reply = sc.nextLine();

				enquiry_ith.setReply(reply);
				enquiry_ith.markAsResolved();

				return;
			}

		}

		System.out.println("No matching ID found!");
	}








	public void createSuggestion(Login currentUser){

		for (int i = 0; i < campInfo.getCommitteeList().size(); i++){
			Committee student_ith = (Committee) campInfo.getCommitteeList().get(i);

			if (student_ith.getID().equals(currentUser.getUserid())){
				System.out.print("Your suggestion (one line): ");
				String description = sc.nextLine();

				Suggestion newSuggestion = new Suggestion(student_ith, description);
				campInfo.addSuggesstion(newSuggestion);
				return;
			}
		}

	}

	public void viewSuggestion(Login currentUser){
		System.out.println("Suggestion view options:");
		System.out.println("1. View all Suggestions");
		System.out.println("2. View Suggestion Detail");
		System.out.print("Your choice: ");
		int choice = getMenuChoice();

		switch (choice) {
			case 1:
				viewAllEnquiry(currentUser);
				break;
			case 2:
				viewSuggestionDetail(currentUser);
				break;

			default:
				System.out.println("Choice out of range!");
				break;
		}
	}

	public void viewAllSuggestion(Login currentUser){
		int index = 1;

		for (int i = 0; i < campInfo.getSuggestions().size(); i++){
			Suggestion suggestion_ith = campInfo.getSuggestions().get(i);

			if (currentUser.getRole().equals("student")){
				if (suggestion_ith.getAuthor().getID().equals(currentUser.getUserid())){
					System.out.println("ID: " + suggestion_ith.getSuggestionID() + ", status: [" + suggestion_ith.getStatus().toString() + "]");
					index++;
				}
			}
			else {
				System.out.println("ID: " + suggestion_ith.getSuggestionID() + ", status: [" + suggestion_ith.getStatus().toString() + "]");
				index++;
			}
		}

		if (index == 1){
			System.out.println("There is no enquiry in this camp");
		}
	}

	public void viewSuggestionDetail(Login currentUser){
		viewAllSuggestion(currentUser);
		System.out.print("Suggesstion ID: ");
		int suggestionID = sc.nextInt();
		sc.nextLine();

		for (int i = 0; i < campInfo.getSuggestions().size(); i++){
			Suggestion suggestion_ith = campInfo.getSuggestions().get(i);

			if (suggestion_ith.getSuggestionID() == suggestionID){
				if (currentUser.getRole().equals("student")){
					if (suggestion_ith.getAuthor().getID().equals(currentUser.getUserid())){
						System.out.println("ID: " + suggestion_ith.getSuggestionID() + ", status: [" + suggestion_ith.getStatus().toString() + "]");
						System.out.println("Detail: " + suggestion_ith.getDescription());
						return;
					}
				}
				else {
					System.out.println("ID: " + suggestion_ith.getSuggestionID() + ", status: [" + suggestion_ith.getStatus().toString() + "]");
					System.out.println("Detail: " + suggestion_ith.getDescription());
					return;
				}
			}
		}

		System.out.println("There is no suggestion ID matched the provided one");

	}

	public void editSuggestion(Login currentUser){
		viewAllSuggestion(currentUser);
		System.out.print("Suggesstion ID: ");
		int suggestionID = sc.nextInt();
		sc.nextLine();

		for (int i = 0; i < campInfo.getSuggestions().size(); i++){
			Suggestion suggestion_ith = campInfo.getSuggestions().get(i);

			if (suggestion_ith.getSuggestionID() == suggestionID){
				if (suggestion_ith.getResolved()){
					System.out.println("You cannot edit resolved suggestion");
					return;
				}

				if (currentUser.getRole().equals("student")){
					if (suggestion_ith.getAuthor().getID().equals(currentUser.getUserid())){
						System.out.println("ID: " + suggestion_ith.getSuggestionID() + ", status: [" + suggestion_ith.getStatus().toString() + "]");
						System.out.println("Detail: " + suggestion_ith.getDescription());

						System.out.println("Detail: ");
						String detail = sc.nextLine();

						suggestion_ith.setDescription(detail);

						return;
					}
				}
			}
		}

		System.out.println("There is no matching suggestion for this camp");
	}

	public void deleteSuggestion(Login currentUser){
		viewAllSuggestion(currentUser);
		System.out.print("Suggesstion ID: ");
		int suggestionID = sc.nextInt();
		sc.nextLine();

		for (int i = 0; i < campInfo.getSuggestions().size(); i++){
			Suggestion suggestion_ith = campInfo.getSuggestions().get(i);

			if (suggestion_ith.getSuggestionID() == suggestionID){
				if (suggestion_ith.getResolved()){
					System.out.println("You cannot delete resolved suggestion");
					return;
				}

				if (currentUser.getRole().equals("student")){
					if (suggestion_ith.getAuthor().getID().equals(currentUser.getUserid())){
						System.out.println("ID: " + suggestion_ith.getSuggestionID() + ", status: [" + suggestion_ith.getStatus().toString() + "]");
						System.out.println("Detail: " + suggestion_ith.getDescription());

						System.out.println("");
						campInfo.getSuggestions().remove(i);

						return;
					}
				}
			}
		}

		System.out.println("There is no matching suggestion for this camp");
	}

	public void replySuggestion(Login currentUser){
		viewAllSuggestion(currentUser);

		System.out.print("Suggestion ID: ");

		int suggestionID = sc.nextInt();
		sc.nextLine();

		for (int i = 0; i < campInfo.getEnquiries().size(); i++){
			Suggestion suggestion_ith = campInfo.getSuggestions().get(i);

			if (suggestion_ith.getAuthor().getID().equals(currentUser.getUserid())){
				if (suggestionID == suggestion_ith.getSuggestionID()){
					if (suggestion_ith.getResolved()){
						System.out.println("You cannot delete the resolved suggestion");
						return;
					}

					System.out.print("Confirm deletion (Y / N):");
					String choice = sc.nextLine();

					if (choice == "Y"){
						campInfo.getSuggestions().remove(suggestionID);
						return;
					}
					else {
						return;
					}
				}
			}
		}

		System.out.println("There is no matching suggestion for this camp");
		
	}

	public void generateReportString(Login currentUser){
		System.out.println("Report option: ");
        System.out.println("1. Attendees Report");
        System.out.println("2. Committees Report");
		System.out.println("3. Enquiry Report");
        if (currentUser.getRole().equals("staff")){
            System.out.println("4. Committee performance report");
        }

        int choice = getMenuChoice();

        switch (choice) {
            case 1:
                generateAttendeeReport();
                break;
            case 2:
				generateCommitteeReport();
                break;

			case 3:
				generateEnquiryReport();
				break;
            case 4:
                if (currentUser.getRole().equals("staff")){

                }
                else {
                    System.out.println("Choice out of range");
                }
                break;

            default:
                System.out.println("Choice out of range");
                break;
        }
	}

	public String getCampDetail(){
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Camp name: " + getCampName() + "\n" +
							 "Start Date: " + getStartDate() +"\n" +
							 "End Date: " + getEndDate() + "\n" +
							 "Staff In Charge: " + getStaffInCharge() + "\n" +
							 "Available to: " + getFaculty() + "\n" +
							 "Remaining Total Slot: " + getAttendeeSlot() + "\n" +
							 "Remaining Committee Slot: " + getCommitteeSlot() + "\n");
		return stringBuilder.toString();
	}

	public void generateAttendeeReport(){
		StringBuilder stringBuilder = new StringBuilder();
		
		stringBuilder.append(getCampDetail());
		stringBuilder.append("Total Attendee: " + getAttendeeList().size() + "\n");
		stringBuilder.append("Attendees list: \n");

		
		for (int i = 0; i < getAttendeeList().size(); i++){
			Student student_ith = getAttendeeList().get(i);
			stringBuilder.append((i + 1) + ". " + student_ith.getID() + "\n");
		}

		String reportFile = null;

		try {
			reportFile = System.getProperty("user.dir") + "/src/sce/sc2002/FinalProject/Reports/Report" + getCampName() + "Attendee.txt";
			StringToText.stringToText(stringBuilder.toString(), reportFile);
		}
		catch (Exception exception) {
			reportFile = System.getProperty("user.dir") + "/sce.sc2002.FinalProject/src/sce/sc2002/FinalProject/Reports/Report" + getCampName() + "Attendee.txt";
			StringToText.stringToText(stringBuilder.toString(), reportFile);
		}
	}

	public void generateCommitteeReport(){
		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append(getCampDetail());
		stringBuilder.append("Total Committee: " + getCommitteeList().size() + "\n");
		stringBuilder.append("Committee List: \n");

		for (int i = 0; i < getCommitteeList().size(); i++){
			Committee committee_ith = (Committee) getCommitteeList().get(i);
			stringBuilder.append((i + 1) + ". " + committee_ith.getID() + "\n");
		}

		String reportFile = null;

		try {
			reportFile = System.getProperty("user.dir") + "/src/sce/sc2002/FinalProject/Reports/Report" + getCampName() + "Committee.txt";
			StringToText.stringToText(stringBuilder.toString(), reportFile);
		}
		catch (Exception exception) {
			reportFile = System.getProperty("user.dir") + "/sce.sc2002.FinalProject/src/sce/sc2002/FinalProject/Reports/Report" + getCampName() + "Committee.txt";
			StringToText.stringToText(stringBuilder.toString(), reportFile);
		}
	}

	public void generateEnquiryReport(){
		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append(getCampDetail());
		stringBuilder.append("Total Enquiries: " + getEnquiries().size() + "\n");

		for (int i = 0; i < getEnquiries().size(); i++) {
			Enquiry enquiry_ith = getEnquiries().get(i);

			stringBuilder.append((i + 1) + ". Title: " + enquiry_ith.getSubject() + "\n");
			stringBuilder.append("Description: " + enquiry_ith.getDescription());
			if (enquiry_ith.getResolved()){
				stringBuilder.append("  [RESOLVED]\n");
				stringBuilder.append("Reply: " + enquiry_ith.getReply() + "\n\n");
			}
			else {
				stringBuilder.append("\n\n");
			}
		}

		String reportFile = null;

		try {
			reportFile = System.getProperty("user.dir") + "/src/sce/sc2002/FinalProject/Reports/Report" + getCampName() + "Enquiry.txt";
			StringToText.stringToText(stringBuilder.toString(), reportFile);
		}
		catch (Exception exception) {
			reportFile = System.getProperty("user.dir") + "/sce.sc2002.FinalProject/src/sce/sc2002/FinalProject/Reports/Report" + getCampName() + "Enquiry.txt";
			StringToText.stringToText(stringBuilder.toString(), reportFile);
		}
	}


	public void generatePerformanceReport(){
		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append(getCampDetail());
		
		System.out.println("All committees:");

		if (getCommitteeList().size() == 0){
			System.out.println("There is no camp committee in your camp!");
			return;
		}

		for (int i = 0; i < getCommitteeList().size(); i++){
			Committee commitee_ith = (Committee) getCommitteeList().get(i);
			System.out.println((i + 1) + ". " + commitee_ith.getID());
		}

		System.out.print("Committee User ID: ");
		String committeeName = sc.nextLine();

		for (int i = 0; i < getCommitteeList().size(); i++){
			Committee commitee_ith = (Committee) getCommitteeList().get(i);
			
			if (commitee_ith.getID().equals(committeeName)){
				stringBuilder.append("Committee User ID: " + commitee_ith.getID() + "\n");
				stringBuilder.append("Committee point: " + commitee_ith.getPoint() + "\n");
				System.out.print("Your commment: ");
				String comment = sc.nextLine();

				stringBuilder.append("Commment from staff: " + comment + "\n");

				String reportFile = null;

				try {
					reportFile = System.getProperty("user.dir") + "/src/sce/sc2002/FinalProject/Reports/Report" + getCampName() + "Performance.txt";
					StringToText.stringToText(stringBuilder.toString(), reportFile);
				}
				catch (Exception exception) {
					reportFile = System.getProperty("user.dir") + "/sce.sc2002.FinalProject/src/sce/sc2002/FinalProject/Reports/Report" + getCampName() + "Performance.txt";
					StringToText.stringToText(stringBuilder.toString(), reportFile);
				}


				return;
			}
		}

		System.out.println("There is no committee matched!");

		
	}
}

