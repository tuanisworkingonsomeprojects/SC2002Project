package sce.sc2002.FinalProject;

import java.util.*;

public class CampInformation {
	private String 		campName;
	private int    		startDate;
	private int    		endDate;
	private int    		closingDate;
	private String 		avaialbleTo;
	private String 		location;

	private int    		attendeeSlot = 40;
	private int    		campCommSlot = 10;
	private String 		description;
	private String 		staffInCharge; //creator of the camp
	private boolean 	visibility;

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
<<<<<<< Updated upstream
	
	// Setter methods, please do not delete Tuan :")
	public void setcampName(String campName) 		{this.campName = campName;}
	public void setstartDate(int startDate) 		{this.startDate = startDate;}
	public void setendDate(int endDate) 			{this.endDate = endDate;}
	public void setclosingDate(int closingDate) 	{this.closingDate = closingDate;}
	public void setavailableTo(String availableTo) 	{this.avaialbleTo = availableTo;}
	public void setlocation(String location) 		{this.location = location;}
	public void setattendeeSlot(int attendeeSlot) 	{this.attendeeSlot = attendeeSlot ;}
	public void setdescription(String description) 	{this.description = description;}
	public void setstaffInCharge(Login currentUser) {this.staffInCharge = currentUser.getUserid();}	
	public void setvisibility(char x) {
		if(x == 'Y') {
			visibility = true;
		}
		else {visibility = false;}
	}
	
	
	public int getvisibility() {
=======
		
	public int getVisibility() {
>>>>>>> Stashed changes
		if(visibility) {return 1;}
		else return -1;
	}
	
}
