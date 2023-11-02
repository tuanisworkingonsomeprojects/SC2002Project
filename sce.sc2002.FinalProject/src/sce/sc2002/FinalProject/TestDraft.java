package sce.sc2002.FinalProject;

import java.io.*;
import java.util.Scanner;

public class TestDraft {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub

		try {
        Scanner sc = new Scanner(new File(System.getProperty("user.dir") + "/src/sce/sc2002/FinalProject/UserAndPassword/studentList.csv"));

		sc.useDelimiter(",");   //sets the delimiter pattern  
		while (sc.hasNext())  //returns a boolean value  
		{  
		System.out.print(sc.next());  //find and returns the next complete token from this scanner  
		}   
		sc.close();  //closes the scanner 
        }

        catch (final Exception e){
            Scanner sc = new Scanner(new File(System.getProperty("user.dir") + "/sce.sc2002.FinalProject/src/sce/sc2002/FinalProject/UserAndPassword/studentList.csv"));
            System.out.println("\n\n\n" + System.getProperty("user.dir"));
            		sc.useDelimiter(",");   //sets the delimiter pattern  
            while (sc.hasNext())  //returns a boolean value  
            {  
            System.out.print(sc.next());  //find and returns the next complete token from this scanner  
            }   
            sc.close(); 
        }
        
	}

}
