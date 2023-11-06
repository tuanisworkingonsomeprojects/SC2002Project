package sce.sc2002.FinalProject;
import java.util.*;

/**

 @author Chong Wen Rong, Chelson
 @version 1.0
 @since 11/2/2023
*/

public class StudentMenu extends Menu{


    public void display(){
        int studentChoice;
        Scanner sc = new Scanner(System.in);

        studentDisplay();
        System.out.println("Enter your choice: ");
        studentChoice = sc.nextInt();
        do{
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

    public void studentDisplay(){
        System.out.println("");
        System.out.println("--------------------------------------");
        System.out.println("Welcome to the Student Camp Menu");
        System.out.println("--------------------------------------");
        System.out.println("Choice 1 : List Opened Camps");
        System.out.println("Choice 2 : Register for Camp");
        System.out.println("Choice 3 : Submit Enquiries");
        System.out.println("Choice 4 : View Regisetered Camps");
        System.out.println("Choice 5 : Withdraw From Camp"); 
    }

    public void registerCamp(){

    }

    public void viewAvailableCamp(){

    }

    public void viewRegisteredCamp(){

    }
    
    public void chooseCamp(){

    }
}
