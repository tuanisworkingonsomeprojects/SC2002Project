package sce.sc2002.FinalProject;

public class CampApp {
	
	public static void main(String[] args) {
		//User user;
		
		
		
		Login loginScreen = new Login();
		
		while (!loginScreen.accessGranted()) {
			loginScreen.display();
		}
		
		System.out.println("hi");
		
		
	}
}
