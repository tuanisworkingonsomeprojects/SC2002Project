package sce.sc2002.FinalProject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Camp File
 */
public class Camp{
	/**
	 * The Camp Info
	 */
	private CampInformation campInfo;
	/**
	 * The Scanner to get user input
	 */
	
	private Scanner sc = new Scanner(System.in);
	/**
	 * Constructor for camp
	 * @param campList Camplist
	 */
	public Camp(CampList campList) { //constructor for Camp object
		campInfo = new CampInformation(campList);
	}
	
	/**
	 * 
	 * @param campList The camplist
	 * @param campName The camp's name
	 * @param startDate The start date of the camp
	 * @param endDate The end date of the camp
	 * @param closingDate The closing registration date
	 * @param faculty The faculty of the camp
	 * @param location The location of the camp
	 * @param attendeeSlot The amount of attendee that can register for the camp
	 * @param committeeSlot The amount of committee that can register for the camp
	 * @param detail The details about the camp
	 * @param visibility The visibility of the camp to students
	 * @param staffInCharge The staff that created the camp and is in charge of the camp.
	 */
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



	/**
	 * This method adds the student that has signed up as a committee for the camp
	 * @param new_committee The new committee for the camp
	 */
	public void addCommittee(Committee new_committee) {campInfo.getCommitteeList().add(new_committee);}

	/**
	 * This method adds the student that has signed up as an attendee for the camp
	 * @param new_attendee The new attendee for the camp
	 */
	public void addAttendee(Student new_attendee)     {campInfo.getAttendeeList().add(new_attendee);}
	
	/**
	 * This method will add the student into a blacklist to ensure that the student is unable to register back into the same camp.
	 * @param new_blacklist The new blacklist
	 */
	public void addBlacklist(Student new_blacklist)   {campInfo.getBlackList().add(new_blacklist);}
	
	/**
	 * This method will let the student attendee submit a new enquiry for a camp.
	 * @param new_enquiry The enquiry made 
	 */
	public void addEnquiry(Enquiry new_enquiry)		  {campInfo.getEnquiries().add(new_enquiry);}
	
	/**
	 * This method will let the student committee submit a new suggestion for the camp
	 * @param new_suggestion The suggestion made
	 */
	public void addSuggesstion(Suggestion new_suggestion) {campInfo.getSuggestions().add(new_suggestion);}
	
	/**
	 * This getter method gets the camp name
	 * @return returns the camp name
	 */
	public String  getCampName()		{return campInfo.getCampName();}
	
	/**
	 * This getter method gets the name of the staff in charge of the camp
	 * @return Gets the staff in charge
	 */
	public String  getStaffInCharge()	{return campInfo.getStaffInCharge();}
	
	/**
	 * This getter method will get back the visibility of the camp
	 * @return Gets the visibility of the camp
	 */
	public boolean getVisibility()		{return campInfo.getVisibility();}
	
	/**
	 * This getter method will get the start date of the camp
	 * @return Gets the start date of the camp
	 */
	public Date	   getStartDate()		{return campInfo.getStartDate();}
	
	/**
	 * This getter method will get the end date of the camp
	 * @return The end date of the camp
	 */
	public Date    getEndDate()			{return campInfo.getEndDate();}
	
	/**
	 * This getter method will get the closing date for registration of this camp
	 * @return The registration closing date
	 */
	public Date	   getClosingDate()		{return campInfo.getClosingDate();}
	
	/**
	 * This method will get the faculty of the camp
	 * @return The faculty of the camp
	 */
	public String  getFaculty()			{return campInfo.getFaculty();}
	
	/**
	 * This method will get the amount of committee slots there is in the camp
	 * @return The committee slots
	 */
	public int	   getCommitteeSlot()	{return campInfo.getCampCommSlot();}
	
	/**
	 * This method will get the amount of attendee slots there is in the camp
	 * @return The attendee slots
	 */
	public int	   getAttendeeSlot()	{return campInfo.getAttendeeSlot();}
	
	/**
	 * This method will get the description of the camp
	 * @return This is the description of the camp
	 */
	public String  getDescription()			{return campInfo.getDescription();}
	
	/**
	 * This method will get the location of the camp
	 * @return The location of the camp
	 */
	public String  getLocation()		{return campInfo.getLocation();}
	
	/**
	 * The array of Student Attendees
	 * @return The student attendees
	 */
	public ArrayList<Student> getAttendeeList() {return campInfo.getAttendeeList();}
	
	/**
	 * The array of Student Committees
	 * @return The Student Committees
	 */
	public ArrayList<Student> getCommitteeList(){return campInfo.getCommitteeList();}
	
	/**
	 * The array of blacklisted student for withdrawing from a camp
	 * @return The blacklist
	 */
	public ArrayList<Student> getBlackList()	{return campInfo.getBlackList();}
	
	/**
	 * The array of enquires made by student
	 * @return The array of enquires
	 */
	public ArrayList<Enquiry> getEnquiries()	{return campInfo.getEnquiries();}
	
	/**
	 * The array of suggestions made by Student Committee
	 * @return The array of suggestions
	 */
	public ArrayList<Suggestion> getSuggestions() {return campInfo.getSuggestions();}

	/**
	 * This method will determine if the student is a member of a camp
	 * @param currentUser The current user logged in
	 * @return returns if the person is a member of a camp
	 */
	// A person is a member of a camp if he/she is a committee or attendee of that camp.
	public boolean isMemberOfCamp(Login currentUser)	{return campInfo.isCommittee(currentUser) || campInfo.isAttendee(currentUser);} 

	/**
	 * This method will return if the student is a committee of a camp
	 * @param currentUser The current user logged in
	 * @return returns if the student is a committee or not
	 */
	public boolean isCommittee	   (Login currentUser)	{return campInfo.isCommittee(currentUser);}
	
	/**
	 * This method will return if the student is an attendee of a camp
	 * @param currentUser The current user logged in
	 * @return returns if the student is a attendee or not
	 */
	public boolean isAttendee	   (Login currentUser)	{return campInfo.isAttendee(currentUser);}
	
	/**
	 * This method will return if the user is the staff in charge of the camp (creator of the camp)
	 * @param currentUser The current user logged in
	 * @return returns if the user is the staff in charge of the camp or not
	 */
	public boolean isStaffInCharge (Login currentUser)	{return campInfo.getStaffInCharge().equals(currentUser.getUserid());}
	
	/**
	 * This method will return if the user is blacklisted from the camp
	 * @param currentUser The current user logged in
	 * @return returns if the user is blacklisted from the camp
	 */
	public boolean isBlackListed   (Login currentUser)	{return campInfo.isBlackListed(currentUser);}
	
	/**
	 * This method will check if there is still available slots for the student to register for the camp (Attendee and Committee)
	 * @return returns if the camp is full or available
	 */
	public boolean isAvailable	   ()	{
		if (getCommitteeSlot() > 0 || getAttendeeSlot() > 0) return true;

		return false;
	}
	
	/**
	 * This method will create the camp created by a staff
	 * @param currentUser The current user logged in
	 * @param campList The camp list
	 * @return returns the newly created camp
	 */
	public static Camp createCamp(Login currentUser, CampList campList){
		Camp newCamp = new Camp(campList);
		newCamp.createCampManager(currentUser);
		return newCamp;
	}

	/**
	 * This method will tag the camp creator to the camp that was created
	 * @param currentUser The current user logged in
	 */
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
	
	/**
	 * This method will allow the staff to edit the camp if he or she is the staff that created that camp
	 * @param currentUser The user logged in
	 */
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

	/**
	 * This method will display the choices the user has to edit the camps
	 */
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
	
	/**
	 * This will return the choice that the user has selected.
	 */
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
	
    /**
     * Displays the different options that the staff has when editing the camp details and allow the editing to proceed.
     * @param choice This is the staff chosen choice.
     */
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
	
	/**
	 * This method will determine if the user is able to view the camp 
	 * @param currentUser The current user logged in
	 * @return If the user is able to view the camp
	 */
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
	
	/**
	 * This method will show the attendee list for the camp only if the user is a staff or the Student Committee of that camp
	 * @param currentUser The user logged in
	 */
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

	/**
	 * This method will display the list of committee for the camp
	 * @param currentUser The user logged in
	 */
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

	/**
	 * This method will allow the student to register for the camp that is available to him or her
	 * @param currentUser The user logged in
	 */
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
	
	/**
	 * This method will let the user withdraw from the camp that he or she has signed up for
	 * @param currentUser The user logged in
	 */
	public void withdawFromCamp(Login currentUser){
		campInfo.attendeeWithdrawal(currentUser);
	}

	/**
	 * This method will let the user create an enquiry for the specific camp
	 * @param currentUser The user logged in
	 */
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

	/**
	 * This method will allow the user to view the enquires that were made 
	 * @param currentUser This user logged in
	 */
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

	/**
	 * This method will allow the user to view all enquires made
	 * @param currentUser The user logged in
	 */
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

	/**
	 * This method will allow the user to the enquiry in details
	 * @param currentUser The user logged in
	 */
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

	/**
	 * This method will allow the user to edit the enquiry that was created by him or her 
	 * @param currentUser The user logged in
	 */
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

	/**
	 * This method will allow the user to delete the enquiry that was made by him or her
	 * @param currentUser The user logged in
	 */
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

	/**
	 * This method will allow the user to reply to the enquiry made
	 * @param currentUser The user logged in
	 */
	public void replyEnquiry(Login currentUser){
		viewAllEnquiry(currentUser);

		if (getEnquiries().size() == 0) return;

		System.out.println("Enquiry ID: ");
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
	
	/**
	 * This method will allow the user to create suggestions for the camp (Only student committee is able to)
	 * @param currentUser The user logged in
	 */
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

	/**
	 * This method will allow the user to view the suggestions that were made by the student committee
	 * @param currentUser The user logged in
	 */
	public void viewSuggestion(Login currentUser){
		System.out.println("Suggestion view options:");
		System.out.println("1. View all Suggestions");
		System.out.println("2. View Suggestion Detail");
		int choice = getMenuChoice();

		switch (choice) {
			case 1:
				viewAllSuggestion(currentUser);
				break;
			case 2:
				viewSuggestionDetail(currentUser);
				break;

			default:
				System.out.println("Choice out of range!");
				break;
		}
	}

	/**
	 * This method will allow the user to view all the suggestions that were made for the camp
	 * @param currentUser The user logged in
	 */
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
			System.out.println("There is no suggestion in this camp");
		}
	}

	/**
	 * This method will allow the user to view the suggestions made in details
	 * @param currentUser The user logged in
	 */
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

	/**
	 * This method will allow the user to edit the suggestion that he or she made before it is processed (Only Student Committee)
	 * @param currentUser The user logged in
	 */
	public void editSuggestion(Login currentUser){
		viewAllSuggestion(currentUser);
		System.out.print("Suggesstion ID: ");
		int suggestionID = getMenuChoice();

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

	/**
	 * This method will allow the user to delete the suggestion that he or she made before it is processed (Only Student Committee)
	 * @param currentUser The user logged in
	 */
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

	/**
	 * This will allow the user to reply to the suggestions that were made (Only the Staff that is in charge of the camp - Creator)
	 * @param currentUser The user logged in
	 */
	public void replySuggestion(Login currentUser){
		viewAllSuggestion(currentUser);

		System.out.println("Suggestion ID: ");

		int suggestionID = getMenuChoice();
		for (int i = 0; i < campInfo.getSuggestions().size(); i++){
			Suggestion suggestion_ith = campInfo.getSuggestions().get(i);


			if (suggestion_ith.getSuggestionID() == suggestionID){
				System.out.println("Do you want to approve this suggestion?");
				System.out.print("Your choice (Y / N): ");
				String choice = sc.nextLine();

				switch(choice){
					case "Y":
						suggestion_ith.approve();
						break;
					case "N":
						suggestion_ith.discard();
						break;
					default:
						System.out.println("Not appropriate choice");
						break;
				}
				return;
			}
		}

		System.out.println("There is no matching suggestion for this camp");
		
	}

	/**
	 * This will allow the user to generate the report for the camp (only staff can generate reports 1-3, student committee only report 1 and 3)
	 * @param currentUser The user logged in
	 */
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

	/**
	 * This method will get the camp details
	 * @return The camp details
	 */
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

	/**
	 * This method will generate the attendee report of the camp (Only Student Committee and Staff in charge)
	 */
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

	/**
	 * This method will generate the committee report for the camp (Only Staff in charge - Creator)
	 */
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

	/**
	 * This method will generate the enquiry report of the camp (Only Student Committee and Staff in charge)
	 */
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

	/**
	 * This method will generate the performance report of the committee (Only Staff in charge - Creator)
	 */
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

