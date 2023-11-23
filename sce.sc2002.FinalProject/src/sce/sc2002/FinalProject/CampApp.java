package sce.sc2002.FinalProject;

/**
 * Main method of campApp
 */
public class CampApp {
	/**
	 * This is main method of the campApp
	 * @param args arguments of the campApp
	 */
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

	/**
	 * This method will return the respective menu, depending on if the user is staff, Student Attendee or Student Committee
	 * @param currentUser The user logged in
	 * @param campList The camplist
	 * @return
	 */
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
