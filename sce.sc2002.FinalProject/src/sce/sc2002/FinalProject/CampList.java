package sce.sc2002.FinalProject;
import java.util.*;


public class CampList {
	
	private ArrayList<Camp> campList;
	
	public CampList() {
		campList = new ArrayList<Camp>();
	}
	
	public void viewAllCamp(Login currentUser) {
		for(int i=0;i<campList.size();i++)
			if(currentUser.getRole().equals("student") && Camp.visibility == true)
				System.
		
	}

}
