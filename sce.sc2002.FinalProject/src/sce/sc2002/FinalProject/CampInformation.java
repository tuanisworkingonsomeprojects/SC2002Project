package sce.sc2002.FinalProject;

import java.util.*;

public class CampInformation {
	private String camp_Name;
	private int start_Date;
	private int end_Date;
	private int registration_Closing_Date;
	private String avaialbleTo;
	private String location;
	private int total_Slots;
	private int max = total_Slots;
	private int camp_Comm_Slots = 10;
	private String description;
	private String staff_In_Charge; //creator of the camp
	private boolean visibility;
	private ArrayList<Student> studentAttendees;
	private ArrayList<Committee> committeeList;
	private ArrayList<Enquiry> enquiries;
	private ArrayList<Suggestion> suggestions;
	private ArrayList<Student> blacklist;
	
	// Initializes the list in the constructors
	public void campInformation() {
		studentAttendees = new ArrayList<Student>();	// list of 'Student' objects
		committeeList = new ArrayList<Committee>();		//list of 'Committee' objects
		enquiries = new ArrayList<Enquiry>();			// list of 'Enquiry' objects
		suggestions = new ArrayList<Suggestion>();		//list of 'Suggestion' objects
		blacklist = new ArrayList<Student>(); 			// list of 'Student' objects
	}
	
	/* Getter methods for each attribute
	 * in CampInformation
	 * */
	public String getcamp_Name() {return camp_Name;}
	
	public int getstart_Date() {return start_Date;}
	
	public int getend_Date() {return end_Date;}
	
	public int getregistration_Closing_Date() {return registration_Closing_Date;}
	
	public String getavaialbleTo() {return avaialbleTo;}
	
	public String getlocation() {return location;}
	
	public int gettotal_Slots() {return total_Slots;}
	
	public int getcamp_Comm_Slots() {return camp_Comm_Slots;}
	
	public String getdescription() {return description;}
	
	public String getstaff_In_Charge() {return staff_In_Charge;}
	
	public int getvisibility() {
		if(visibility) {return 1;}
		else return -1;
	}
	
	
	/*Setter methods for each attribute 
	 * in CampInformation
	 * */
	public void setcamp_Name(String camp_Name) {this.camp_Name = camp_Name;}
	
	public void setstart_Date(int start_Date) {this.start_Date = start_Date;}
	
	public void setend_Date(int end_Date) {this.end_Date = end_Date;}
	
	public void setregistration_Closing_Date(int registration_Closing_Date) {this.registration_Closing_Date = registration_Closing_Date;}
	
	public void setavaialbleTo(String avaialbleTo) {this.avaialbleTo = avaialbleTo;}
	
	public void setlocation(String location) {this.location = location;}
	
	public void settotal_Slots(int total_Slots) {this.total_Slots = total_Slots;}
	
	public void setcamp_Comm_Slots(int camp_Comm_Slots) {this.camp_Comm_Slots = camp_Comm_Slots;}
	
	public void setdescription(String description) {this.description = description;}
	
	public void setstaff_In_Charge(String staff_In_Charge) {this.staff_In_Charge = staff_In_Charge;}
	
	public void setvisibility(boolean visibility) {this.visibility = visibility;}
	
	
	// When student registers for camp, this will decrease the total number of slots in camp by 1
	public int registerAttendee(int total_Slots) {
		if (total_Slots ==0){
			return -1; // returns -1 to signify that camp is full
		}
		return total_Slots-=1;
	}
	
	// When student deregisters for camp, this will increase the total number of slots in camp by 1
	public int deregisterAttendee(int total_Slots) {
		if(total_Slots <= max) {
			return total_Slots+=1;
		}
		else return -1;
	}
	
	/* When student registers as camp committee, 
	 * this will decrease total number of camp committee slots by 1
	 **/
	public int registerCommittee(int camp_Comm_Slots) {
		if (camp_Comm_Slots == 0) {
			return -1; // returns -1 to signify that camp Committee is full
		} 
		return camp_Comm_Slots-=1;
	}
	
	
    //Methods to manage attendees from the list
    public void addStudentAttendee(Student student) {
    	while(registerAttendee(total_Slots) != -1) {
    		studentAttendees.add(student);
    	} 
    }

    public void removeStudentAttendee(Student student) {
    	while(deregisterAttendee(total_Slots) != -1) {
    		studentAttendees.remove(student);
    		blacklist.add(student);
    	}    
    }

    public ArrayList<Student> getStudentAttendees() {
        return studentAttendees;
    }
    
    
    // Methods to manage committee members
    public void addCommitteeMember(Committee member) {
    	while(registerCommittee(camp_Comm_Slots) != -1){
    		committeeList.add(member);
    	}
    }
   
    public ArrayList<Committee> getCommitteeMembers() {
        return committeeList;
    }
    
    
    // Methods to manage enquiries
    public void addEnquiry(Enquiry enquiry) {
        enquiries.add(enquiry);
    }

    public void removeEnquiry(Enquiry enquiry) {
        enquiries.remove(enquiry);
    }

    public ArrayList<Enquiry> getEnquiries() {
        return enquiries;
    }
    
    
    // Methods to manage suggestions
    public void addSuggestion(Suggestion suggestion) {
    	suggestions.add(suggestion);
    }
    
    public void removeSuggestion(Suggestion suggestion) {
    	suggestions.remove(suggestion);
    }
    
    public ArrayList<Suggestion> getSuggestions(){
    	return suggestions;
    }
    
    
}
