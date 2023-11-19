package sce.sc2002.FinalProject;

public class CampApp {
	
	public static void main(String[] args) {
		Login loginScreen = new Login();
		
		boolean quit = false;

		loginScreen.display();
		CampList campList = new CampList(loginScreen);
		campList.loadData();

		while(!quit){

			//Pseudo clear screen
			for (int i = 0; i < 100; i++){
				System.out.println();
			}

			Menu currentSection = returnMenu(loginScreen, campList);

			currentSection.runMenu();
			loginScreen.display();
		}

		
		

		
		
	}

	public static Menu returnMenu(Login currentUser, CampList campList){
		if (currentUser.getRole().equals("staff")){
			return new StaffMenu(currentUser, campList);
		}
		else if (campList.isCampCommittee()){
			return new CommitteeMenu(currentUser, campList);
		}
		else {
			return new AttendeeMenu(currentUser, campList);
		}
	}


	
}
