package sce.sc2002.FinalProject;

public class CampApp {
	
	public static void main(String[] args) {
		Login loginScreen = new Login();
		
		loginScreen.display();


		//Pseudo clear screen
		for (int i = 0; i < 100; i++){
			System.out.println();
		}

		Menu currentSection = returnMenu(loginScreen);

		currentSection.runMenu();
		
		

		
		
	}

	public static Menu returnMenu(Login currentUser){
		if (currentUser.getRole().equals("staff")){
			return new StaffMenu(currentUser);
		}
		else {
			return new StudentMenu(currentUser);
		}
	}


	
}
