package sce.sc2002.FinalProject;
import java.util.*;


public class CampList {
	
	private List<Camp> campList;
	
	public CampList() {
		campList = new ArrayList<Camp>();
	}
	
	public void addCamp(Camp camp) {
		campList.add(camp);
		Camp.createCamp(camp);
	}
	
	public void viewAllCamp(Login currentUser) {
		for(Camp camp: campList) {
			if(currentUser.getRole().equals("student") && camp.visibility)
				System.out.print("Camp Name: " + camp.getcampName());
				for(Committee committee: Camp.getcampInfo().getCommitteeList()) {
					System.out.print("[Committee]");
					break;
				}
					
		
		}
	}
}
