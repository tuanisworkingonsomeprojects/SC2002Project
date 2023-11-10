package sce.sc2002.FinalProject;

import java.util.*;

public class CampInformation {
	private String 		campName;
	private int    		startDate;
	private int    		endDate;
	private int    		registrationClosingDate;
	private String 		avaialbleTo;
	private String 		location;

	private int    		attendeeSlot = 40;
	private int    		campCommSlots = 10;
	private String 		description;
	private String 		staffIn_Charge; //creator of the camp
	private boolean 	visibility = false; // Default to false

	private ArrayList<Student> 	  studentAttendees;
	private ArrayList<Committee>  committeeList;
	private ArrayList<Enquiry>    enquiries;
	private ArrayList<Suggestion> suggestions;
	private ArrayList<Student>    blacklist;
	
	// Initializes the list in the constructors
	public CampInformation() {
		studentAttendees = new ArrayList<Student>();	// list of 'Student' objects
		committeeList    = new ArrayList<Committee>();		//list of 'Committee' objects
		enquiries        = new ArrayList<Enquiry>();			// list of 'Enquiry' objects
		suggestions      = new ArrayList<Suggestion>();		//list of 'Suggestion' objects
		blacklist        = new ArrayList<Student>(); 			// list of 'Student' objects
	}
	

	
	public int getVisibility() {
		if(visibility) {return 1;}
		else return -1;
	}

	// Method to add a new enquiry
    public void createEnquiry(Camp camp, Student author, String subject, String description) {
        Enquiry enquiry = new Enquiry(camp, author, subject, description, null, null, false);
        enquiries.add(enquiry);
    }

    // Method to get a list of enquiries
    public ArrayList<Enquiry> getEnquiries() {
        return enquiries;
    }

    // Method to get an enquiry by ID
    public Enquiry getEnquiryById(int enquiryID) {
        for (Enquiry enquiry : enquiries) {
            if (enquiry.getEnquiryID() == enquiryID) {
                return enquiry;
            }
        }
        return null; // Enquiry not found
    }

    // Method to delete an enquiry
    public void deleteEnquiry(int enquiryID) {
        Enquiry enquiry = getEnquiryById(enquiryID);
        if (enquiry != null) {
            enquiries.remove(enquiry);
        }
    }
	
	
}
