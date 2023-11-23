package sce.sc2002.FinalProject;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * This is the Camp Information class
 */
public class CampInformation {
	/**
	 * This Name of the camp
	 */
	private String 		campName;

	
	
	/**
	 * The start date of the camp
	 */
	private Date        startDate;
	/**
	 * The end date of the camp
	 */
	private Date    	endDate;
	/**
	 * The registration deadline of the camp
	 */
	private Date    	closingDate;

	/**
	 * The visibility of who the camp is available to 
	 */
	private String 		avaialbleTo;
	/**
	 * The location of the camp
	 */
	private String 		location;

	/**
	 * The max Student Attendee slot
	 */
	private int    		attendeeSlot = 40;
	/**
	 * The max Camp Committee slot
	 */
	private int    		campCommSlots = 10;
	/**
	 * The creator of the camp
	 */
	private String 		staffInCharge; //creator of the camp
	/**
	 * The visibility of the camp (True or false), defaulted to false
	 */
	private boolean 	visibility = false; // Default to false
	/**
	 * The details of the camp
	 */
	private String		detail;

	/**
	 * The Array list of student attendee
	 */
	private ArrayList<Student> 	  studentAttendees;
	/**
	 * The Array list of student committee
	 */
	private ArrayList<Student>    committeeList;
	/**
	 * The Array list of enquires
	 */
	private ArrayList<Enquiry>    enquiries;
	/**
	 * The Array list of suggestions
	 */
	private ArrayList<Suggestion> suggestions;
	/**
	 * The Array list of black listed students for withdrawing from the camp
	 */
	private ArrayList<Student>    blacklist;
	/**
	 * The camp list
	 */
	private CampList 				campList;  // The Camp need to know the campList to check for clashing.
	
	/**
	 * The constructor for camp information
	 * @param campList camplist
	 */
	// Initializes the list in the constructors
	public CampInformation(CampList campList) {
		studentAttendees = new ArrayList<Student>();	 // list of 'Student' objects
		committeeList    = new ArrayList<Student>();     // list of 'Committee' objects
		enquiries        = new ArrayList<Enquiry>();	 // list of 'Enquiry' objects
		suggestions      = new ArrayList<Suggestion>();	 // list of 'Suggestion' objects
		blacklist        = new ArrayList<Student>(); 	 // list of 'Student' objects
		this.campList    = campList;
	}
	
	/**
	 * Scanner to take in the user input
	 */
	private Scanner sc = new Scanner(System.in);

	/**
	 * The setter method to set the camp name
	 * @param campName The name of the camp
	 */
	// Setter methods, please do not delete Tuan :") 
	public void setCampName(String campName)   		{this.campName      = campName;}
	/**
	 * This method will set the start date of the camp
	 * @param startDate The start date of the camp
	 */
 	public void setStartDate(String startDate)      {this.startDate     = fromStringToDate(startDate);} 
 	/**
 	 * This method will set the end date of the camp
 	 * @param endDate The end date of the camp
 	 */
 	public void setEndDate(String endDate)    		{this.endDate       = fromStringToDate(endDate);}
 	/**
 	 * This method will set the deadline date for registering for the camp
 	 * @param closingDate The deadline date to register for camp
 	 */
 	public void setClosingDate(String closingDate)  {this.closingDate   = fromStringToDate(closingDate);} 

 	/**
 	 * This method will set who the camp is available to
 	 * @param availableTo The availability of camp to who
 	 */
	public void setAvailableTo(String availableTo)  {this.avaialbleTo   = availableTo;}
	/**
	 * This method will set the location of the camp
	 * @param location The location of the camp
	 */
 	public void setLocation(String location)   		{this.location      = location;} 
 	/**
 	 * This method will set the amount of slots the camp can hold for attendees
 	 * @param attendeeSlot Attendee Slots
 	 */
	public void setAttendeeSlot(int attendeeSlot)  	{this.attendeeSlot  = attendeeSlot ;}
	/**
	 * This method will set the amount of slots the camp can hold for committee
	 * @param campCommSlots Committee Slots
	 */
	public void setCampCommSlot(int campCommSlots)  {this.campCommSlots = campCommSlots;}
	/**
	 * This method will set the staff in charge of the camp (Creator of the camp)
	 * @param currentUser The user logged in
	 */
	public void setStaffInCharge(Login currentUser) {this.staffInCharge = currentUser.getUserid();} 
	/**
	 *This method will set the staff in charge of the camp (Creator of the camp)
	 */
	public void setStaffInCharge(String staffInCharge) {this.staffInCharge = staffInCharge;}

	/**
	 * This method will set the new details of the camp
	 * @param newDetail new details of camp
	 */
	public void setDetail(String newDetail)			{this.detail		= newDetail;}
	/**
	 * This method will set the visibility of the camp
	 * @param x Visibility of the camp
	 */
 	public void setVisibility(char x) {  
		if(x == 'Y') {
    		visibility = true;  }
  		else {visibility = false;} 
	}
	/**
	 * This method will set the new visibility of the camp
	 * @param new_visibility new visibility of camp
	 */
	public void setVisibility(boolean new_visibility){
		visibility = new_visibility;
	}


	// Getter methods.
	/**
	 * This getter method will get the camp name
	 * @return The camp name
	 */
	public String  	getCampName()		{return campName;}
	/**
	 * This getter method will get the staff in charge of the camp (Creator)
	 * @return Staff in charge
	 */
	public String  	getStaffInCharge()	{return staffInCharge;}
	/**
	 * This getter method will get the visibility of the camp
	 * @return visibility of camp
	 */
	public boolean 	getVisibility()		{return visibility;}
	/**
	 * This getter method will get the attendee slot
	 * @return Attendee Slot
	 */
	public int 		getAttendeeSlot()	{return attendeeSlot;}
	/**
	 * This getter method will get the committee slot
	 * @return Committee Slot
	 */
	public int 		getCampCommSlot()	{return campCommSlots;}
	/**
	 * This getter method will get the start date of the camp
	 * @return Start date of camp
	 */
	public Date		getStartDate()		{return startDate;}
	/**
	 * This getter method will get the end date of the camp
	 * @return End date of camp
	 */
	public Date		getEndDate()		{return endDate;}
	/**
	 * This getter method will get the closing date for registration of the camp
	 * @return Closing date for registration of the camp
	 */
	public Date		getClosingDate()	{return closingDate;}
	/**
	 * This getter method will get the availability of the camp
	 * @return Availability of camp
	 */
	public String   getFaculty()		{return avaialbleTo;}
	/**
	 * This getter method will get the location of the camp
	 * @return Location of camp
	 */
	public String   getLocation()		{return location;}
	/**
	 * This getter method will get the description of the camp
	 * @return Description of camp
	 */
	public String	getDescription()			{return detail;}

	/**
	 * This is the array list that will contain the attendee list
	 * @return Attendee List
	 */
	public ArrayList<Student> getAttendeeList() 	{return studentAttendees;}
	/**
	 * This is the array list that will contain the committee list
	 * @return Committee List
	 */
	public ArrayList<Student> getCommitteeList()	{return committeeList;}
	/**
	 * This is the array list that will contain the black list to prevent the student from registering from the camp that he or she has withdrawn from
	 * @return Black List
	 */
	public ArrayList<Student> getBlackList()		{return blacklist;}
	
	/**
	 * This boolean method will return if the student has passed the registering date
	 * @return 
	 */
	public boolean isMissClosingDate(){
		Date today = new Date();
		return today.after(closingDate);
	}

	/**
	 * This method will convert the string to date
	 * @param dateText 
	 * @return
	 */
	public Date fromStringToDate(String dateText){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dateOutput = sdf.parse(dateText, new ParsePosition(0));
		return dateOutput;
	}

	/**
	 * This method will allow the user to register as a committee
	 * @param currentUser The user logged in
	 */
	public void committeeRegister(Login currentUser){

		// The student can only a camp Committee of one camp only.
		if (campList.isCampCommittee()) {
			System.out.println("You can be a committee of one camp only!");
			return;
		}


		// 1. Check if there is avalable slot
		
		if (campCommSlots > 0){

			// 2. Check if the current user is an Attendee of this camp
			if (isAttendee(currentUser)){
				System.out.println("You are currently an Attendee of this camp!");
			}

			// 3. Check if the user is blacklisted
			else if (isBlackListed(currentUser)){
				System.out.println("You have withdraw from this camp before.");
				System.out.println("You have been blacklisted");
			}

			// 4. Check for clashing 
			else if (isClash(currentUser)){
				System.out.println("The camp registering is clashing with one of the camp you have registered!");
			}

			// 5. Check if the student have missed the closing date of the camp
			else if (isMissClosingDate()){
				System.out.println("You have missed the closing date of the camp");
			}
			// 6. If not, the user if good to register as a camp commitee
			else{
				committeeList.add(new Committee(currentUser.getUserid(), currentUser.getFaculty()));
				campCommSlots--;
				attendeeSlot--;
			}
		}
		else {
			System.out.println("Out of Slot for Camp Committee!");
		}
	}

	/**
	 * This method will check if the user is a committee of a camp
	 * @param currentUser The user logged in
	 * @return
	 */
	public boolean isCommittee(Login currentUser){
		for (int i = 0; i < committeeList.size(); i++){
			if (committeeList.get(i).getID().equals(currentUser.getUserid())){
				return true;
			}
		}
		return false;
	}

	/**
	 * This method will allow the user to register for the camp as an attendee
	 * @param currentUser The user logged in
	 */
	public void attendeeRegister(Login currentUser){

		// 1. it check for available slots
		if (attendeeSlot > 0){

			// 2. Check if the user is currently a Camp Committee
			if (isCommittee(currentUser)){
				System.out.println("You are currently an Commitee of this camp!");
			}

			// 3. Check if the user is blacklisted
			else if (isBlackListed(currentUser)){
				System.out.println("You have withdraw from this camp before.");
				System.out.println("You have been blacklisted");
			}

			// 4. Check for clashing 
			else if (isClash(currentUser)){
				System.out.println("The camp registering is clashing with one of the camp you have registered!");
			}

			// 5. Check if the student have missed the closing date of the camp
			else if (isMissClosingDate()){
				System.out.println("You have missed the closing date of the camp");
			}

			// 6. If the user is not blacklisted or clash with other camps then it is oke to register the camp
			else{
				studentAttendees.add(new Student(currentUser.getUserid(), currentUser.getFaculty()));
				attendeeSlot--;
				System.out.println("Camp registration successfully!");
			}
		}
		else {
			System.out.println("Out of Slot for Attendee in this Camp!");
		}
	}

	/**
	 * This method will check if the student is an attendee of the camp
	 * @param currentUser The user logged in
	 * @return
	 */
	public boolean isAttendee(Login currentUser){
		for (int i = 0; i < studentAttendees.size(); i++){
			if (studentAttendees.get(i).getID().equals(currentUser.getUserid())){
				return true;
			}
		}
		return false;
	}

	/**
	 * This method will prevent the user from withdrawing if he or she is a committee but able to as an attendee
	 * @param currentUser The user logged in
	 */
	public void attendeeWithdrawal(Login currentUser){
		if (isCommittee(currentUser)){
			System.out.println("You are a Commitee of this Camp");
			System.out.println("You are not allowed to withdraw");
		}
		else if (isAttendee(currentUser)){
			for (int i = 0; i < studentAttendees.size(); i++){
				if (studentAttendees.get(i).getID().equals(currentUser.getUserid())){
					blacklist.add(studentAttendees.get(i));
					studentAttendees.remove(i);
					attendeeSlot++;
					return;
				}
			}
		}
		else {
			System.out.println("You haven't registered for this camp!");
		}
	}

	/**
	 * This method will check if the user is blacklisted and unable to register for the camp after withdrawing
	 * @param currentUser The user logged in
	 * @return
	 */
	public boolean isBlackListed(Login currentUser){
		for (int i = 0; i < blacklist.size(); i++){
			if (blacklist.get(i).getID().equals(currentUser.getUserid())) return true;
		}
		return false;
	}
	
	/**
	 * This method will check if the date is out of the registering period
	 * @return
	 */
	public boolean isOutOfRegiterDate(){
		Date currentTime = new Date();

		if (currentTime.compareTo(closingDate) > 0){
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * This method will check if the user registering for the camp has a clash in schedule with another camp
	 * @param currentUser The user logged in
	 * @return
	 */
	public boolean isClash(Login currentUser){
		ArrayList<Camp> camps = campList.getCampList();
		Camp camp_ith;

		for (int i = 0; i < camps.size(); i++){
			camp_ith = camps.get(i);

			// Only check overlaping if the currentUser is a member of the ith camp.
			if (camp_ith.isMemberOfCamp(currentUser)){
				if (camp_ith.getCampName().equals(campName)&&
				camp_ith.getStaffInCharge().equals(staffInCharge)) continue;
			
				// Check overlaping
				if (startDate.compareTo(camp_ith.getEndDate()) < 0 && 
					endDate.compareTo(camp_ith.getStartDate()) > 0){
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * This is the array list for enquires
	 * @return
	 */
    public ArrayList<Enquiry> getEnquiries() {
        return enquiries;
    }

    /**
     * This method will let the user create an enquiry to a camp
     * @param enquiry The enquiry for the camp
     */
	public void addEnquiry(Enquiry enquiry){
		enquiries.add(enquiry);
	}

	/**
	 * This is the array list for suggestions
	 * @return
	 */
	public ArrayList<Suggestion> getSuggestions(){
		return suggestions;
	}

	/**
	 * this method will let the user create a suggestion for a camp (Only Committee)
	 * @param newSuggestion
	 */
	public void addSuggesstion(Suggestion newSuggestion){
		suggestions.add(newSuggestion);
	}

	/**
	 * This method will get the enquiry by ID
	 * @param enquiryID Enquiry ID
	 * @return
	 */
    // Method to get an enquiry by ID
    public Enquiry getEnquiryById(int enquiryID) {
        for (Enquiry enquiry : enquiries) {
            if (enquiry.getEnquiryID() == enquiryID) {
                return enquiry;
            }
        }
        return null; // Enquiry not found
    }

    /**
     * This method will delete the enquiry made
     * @param enquiryID Enquiry ID
     */
    // Method to delete an enquiry
    public void deleteEnquiry(int enquiryID) {
        Enquiry enquiry = getEnquiryById(enquiryID);
        if (enquiry != null) {
            enquiries.remove(enquiry);
        }
    }
	
	
}
