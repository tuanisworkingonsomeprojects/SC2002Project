package sce.sc2002.FinalProject;
import java.util.Scanner;

public class CampApp {
	
	public static void main(String[] args) {
		//User user;
		
		
		
		Login loginScreen = new Login();
		loginScreen.display();

		
		//Displaying Student Menu after login
		StudentMenu StudentLoginScreen = new StudentMenu();
		StudentLoginScreen.StudentMenuDisplay();
		int studentChoice;
		Scanner sc = new Scanner(System.in);

		do{
			System.out.println("Please Select Your Choice Of Action");
			studentChoice=sc.nextInt();
            
            switch(studentChoice){
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
            }
            

        }while(studentChoice<6);
	}
}
