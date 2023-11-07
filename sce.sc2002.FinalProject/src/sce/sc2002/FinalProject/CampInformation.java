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
	private boolean 	visibility;

	private ArrayList<Student> 	  studentAttendees;
	private ArrayList<Committee>  committeeList;
	private ArrayList<Enquiry>    enquiries;
	private ArrayList<Suggestion> suggestions;
	private ArrayList<Student>    blacklist;
	
	// Initializes the list in the constructors
	public void campInformation() {
		studentAttendees = new ArrayList<Student>();	// list of 'Student' objects
		committeeList    = new ArrayList<Committee>();		//list of 'Committee' objects
		enquiries        = new ArrayList<Enquiry>();			// list of 'Enquiry' objects
		suggestions      = new ArrayList<Suggestion>();		//list of 'Suggestion' objects
		blacklist        = new ArrayList<Student>(); 			// list of 'Student' objects
	}
	

	
	public int getvisibility() {
		if(visibility) {return 1;}
		else return -1;
	}
	
}
