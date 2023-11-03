package sce.sc2002.FinalProject;

public class CampInformation {
	private String camp_Name;
	private int start_Date;
	private int end_Date;
	private int registration_Closing_Date;
	private String camp_Available_To;
	private String location;
	private int total_Slots;
	private int camp_Comm_Slots;
	private String description;
	private String staff_In_Charge; //creator of the camp
	
	
	public String getcamp_Name() {
		return camp_Name;
	}
	
	public void setcamp_Name(String camp_Name) {
		this.camp_Name = camp_Name;
	}
	
	
}
