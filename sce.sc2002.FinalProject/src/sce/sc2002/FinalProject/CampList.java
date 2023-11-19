package sce.sc2002.FinalProject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.text.SimpleDateFormat;


public class CampList{

    private String accessPath;

    private Login currentUser;
    private ArrayList<Camp> campList;

    public CampList(Login user){
        currentUser = user;
        campList    = new ArrayList<Camp>();
    }

    private Scanner sc = new Scanner(System.in);
    
    public ArrayList<Camp> getCampList(){return campList;}


    public boolean isCampCommittee(){
        for (int i = 0; i < campList.size(); i++){
            Camp camp_ith = campList.get(i);

            if (camp_ith.isCommittee(currentUser)){
                // TODO: delete this test
                System.out.println("This is a commitee");
                return true;
            }
        }

        return false;
    }

    private Scanner getDataFromCSV(String role) {
		Scanner sc = null;
		
		// TODO: change the path and add new folder to store the campList data.
        // TODO: create each file for each camp with the name of the csv file is the name of the camp
        // The first line is the data that are not stored in the ArrayList type
        // The seconde line is the member of the first ArrayList members and the format later will be like this!
        try {
            sc = new Scanner(new File(System.getProperty("user.dir") + "/src/sce/sc2002/FinalProject/UserAndPassword/studentList.csv"));
            accessPath = System.getProperty("user.dir") + "/src/sce/sc2002/FinalProject/UserAndPassword/studentList.csv";
        }
        catch (final Exception e) {
            try {
                sc = new Scanner(new File(System.getProperty("user.dir") + "/sce.sc2002.FinalProject/src/sce/sc2002/FinalProject/UserAndPassword/studentList.csv"));
                accessPath = System.getProperty("user.dir") + "/sce.sc2002.FinalProject/src/sce/sc2002/FinalProject/UserAndPassword/studentList.csv";
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
        }
        
        sc.useDelimiter(",");

		
		
		
		return sc;
	}

    private void tempDelay(){
        System.out.println("Press Enter to go Back!");
        sc.nextLine();

        // Pseudo clear screen
        for (int i = 0; i < 100; i++){
            System.out.println();
        }

        return;
    }

    public void createCamp(){
        addCamp(Camp.createCamp(currentUser, this));
    }

    private void addCamp(Camp camp){
        System.out.println("Adding Camp to the list...");
        campList.add(camp);
    }

    public void deleteCamp(){
        System.out.println("Deleting Camp Screen: ");
        Scanner sc = new Scanner(System.in);
        Camp camp_ith;

        if (currentUser.getRole().equals("staff")){
            
            System.out.print("Camp's name: ");
            String delCampName = sc.nextLine();

            for (int i = 0; i < campList.size(); i++){
                camp_ith = campList.get(i);

                if (camp_ith.getCampName().equals(delCampName)){
                    System.out.print("Confirm delete (Y/N):");
                    String choice = sc.next();

                    if (choice.equals("Y") || choice.equals("y")){
                        campList.remove(i);
                        System.out.println("Camp remove Successfully!");
                        return;
                    }
                    else {
                        System.out.println("Cancelling...");
                        return;
                    }
                }
            }

            System.out.println("Could not find the matching name!");
        }
        else {
            System.out.println("You don't have enough authority to delete the Camp!");
        }

        tempDelay();
    }

    public void chooseCampToEdit(){
        System.out.println("Editing Camp Screen:");

        if (currentUser.getRole().equals("staff")){
            Scanner sc = new Scanner(System.in);
            viewCreatedCamp();
            System.out.print("Camp's name': ");
            String campName = sc.nextLine();

            for (int i = 0; i < campList.size(); i++){

                // Check matching camp's name AND matching staft UserID
                if (campName.equals(campList.get(i).getCampName()) &&
                    currentUser.getUserid().equals(campList.get(i).getStaffInCharge())){
                    
                    campList.get(i).editCamp(currentUser);
                }

                break;
            }
            
            System.out.println("There is no camp available to edit!");
        }
        else {
            System.out.println("You don't have enough authority to edit camp");
        }

        tempDelay();
    }

    public void viewCreatedCamp(){
        int index = 1;

        if (currentUser.getRole().equals("staff")){
            for (int i = 0; i < campList.size(); i++){

                // Check for matching UserID
                if (campList.get(i).isStaffInCharge(currentUser)){
                    System.out.println(index + ". " + campList.get(i).getCampName());
                    index++;
                }
            }
        }
        else {
            System.out.println("You don't have enough authority to view created camp");
        }

        if (index == 1){
            System.out.println("You haven't created any camp!\n\n\n");
        }

        tempDelay();
    }

    public void viewAllCamp(){
        int index = 1;
        for (int i = 0; i < campList.size(); i++){
            Camp camp_ith = campList.get(i);

            if (camp_ith.allowToView(currentUser)){
                System.out.print(index + ". " + camp_ith.getCampName() + "\t");

                if (camp_ith.isCommittee(currentUser)){
                    System.out.print("[Commitee]");
                }
                else if (camp_ith.isAttendee(currentUser)){
                    System.out.print("[Attendee]");
                }
                else if (camp_ith.isStaffInCharge(currentUser)){
                    System.out.print("[In Charge]");
                }

                System.out.println();
                index++;
            }
        }

        if (index == 1){
            System.out.println("There is no camp to view");
        }

        tempDelay();
        
        
    }

    // This method that I've implemented will ask the Staff to choose to print the list of
    // Attendee or the list of Committee already so you don't have to worry about it!
    public void viewStudentList(){

        for (int i = 0; i < 100; i++){
            System.out.println();
        }

        if (currentUser.getRole().equals("student")){
            System.out.println("You don't have enough authority to view the Student list");
            tempDelay();
            return;
        }

        System.out.println("View Student List Window:");
        viewCreatedCamp();

        System.out.print("Camp's name: ");
        String campName = sc.nextLine();

        // Scan throught the list of camps to check the matching Camp base on the name provided
        for (int i = 0; i < campList.size(); i++){
            Camp currentCamp = campList.get(i);

            if (currentCamp.getCampName().equals(campName)){
                System.out.println("View Options:");
                System.out.println("1. Attendee");
                System.out.println("2. Commitee");

                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        currentCamp.printAttendeeList(currentUser);
                        break;
                
                    case 2:
                        currentCamp.printCommitteList(currentUser);
                        break;

                    default:
                        System.out.println("Invalid choice");
                        System.out.println("Going Back!...");
                        sc.nextLine();

                        tempDelay();
                }
                return;
            }
        }
    }

    public void viewAvailableCamp(){
        int index = 1;

        for (int i = 0; i < campList.size(); i++){
            Camp camp_ith = campList.get(i);



            if (camp_ith.isAvailable()){
                System.out.print(index + ". " + camp_ith.getCampName());
                if (camp_ith.isBlackListed(currentUser)){
                    System.out.println("[blacklisted]");
                }
                else {
                    System.out.println();
                }
                index++;
            }
        }

        if (index == 1) System.out.println("There is no available camp.");

        tempDelay();
    }

    
    public void viewRegisteredCamp(){
        System.out.println("View Registered Camp:");
        int index = 1;

        for (int i = 0; i < campList.size(); i++){
            Camp camp_ith = campList.get(i);

            if (camp_ith.isMemberOfCamp(currentUser)){
                System.out.print(index + ". " + camp_ith.getCampName());

                if (camp_ith.isAttendee(currentUser)) System.out.println("[Attendee]");
                if (camp_ith.isCommittee(currentUser)) System.out.println("[Committee]");
                index++;

            }
        }

        if (index == 1) System.out.println("You haven't registered any camp!");
        tempDelay();
    }


    // This method allow camp committee to view detail of a camp
    public void viewCampDetail(){
        if (isCampCommittee()){
            System.out.println("View camp detail screen:");
            viewCampCommittee();
            System.out.print("Camp's name: ");
            String campName = sc.nextLine();
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");


            for (int i = 0; i < campList.size(); i++){
                Camp camp_ith = campList.get(i);
                if (camp_ith.getCampName().equals(campName)){

                    for (int j = 0; j < 100; j++){
                        System.out.println();
                    }

                    System.out.println("Camp's Detail:");
                    System.out.println("Camp Name: \t" + camp_ith.getCampName());
                    System.out.println("Camp start date: \t" + format.format(camp_ith.getStartDate()));
                    System.out.println("Camp end date: \t" + format.format(camp_ith.getEndDate()));
                    System.out.println("Last registration date: \t" + format.format(camp_ith.getClosingDate()));
                    System.out.println("Available to: \t" + camp_ith.getFaculty());
                    System.out.println("Location: \t" + camp_ith.getLocation());
                    System.out.println("Attendee Slot: \t" + camp_ith.getAttendeeSlot());
                    System.out.println("Attendee list: ");
                    for (int k = 0; k < camp_ith.getAttendeeList().size(); k++){
                        Student attendee_ith = camp_ith.getAttendeeList().get(k);
                        System.out.println("\t-" + attendee_ith.getID());
                    }
                    System.out.println("Committee Slot: \t" + camp_ith.getCommitteeSlot());
                    System.out.println("Committee list:");
                    for (int k = 0; k < camp_ith.getCommitteeList().size(); k++){
                        Student committee_ith = camp_ith.getCommitteeList().get(k);
                        System.out.println("\t-" + committee_ith.getID());
                    }

                    break;
                }
            }
        }
        else {
            System.out.println("You are not a camp Committee of any camp!");

        }
        tempDelay();
    }

    // This method allow to view all the camps of which a student is a committee.
    public void viewCampCommittee(){
        System.out.println("You are currently Camp Committee of these camps:");

        int index = 1;

        for (int i = 0; i < campList.size(); i++){
            Camp camp_ith = campList.get(i);

            if (camp_ith.isCommittee(currentUser)){
                System.out.println(index + ". " + camp_ith.getCampName());
                index++;
            }
        }

        if (index == 1){
            System.out.println("You are currently not a commitee of any camp!");
            tempDelay();
        }
    }

    public void registerCamp(){
        System.out.println("Camp Registration");

        if (currentUser.getRole().equals("student")){
            viewAvailableCamp();
            System.out.print("Camp's name: ");
            String campName = sc.nextLine();
            
            for (int i = 0; i < campList.size(); i++){
                Camp camp_ith = campList.get(i);

                if (camp_ith.isAvailable() && camp_ith.getCampName().equals(campName)){
                    camp_ith.registerCamp(currentUser);
                    break;
                }
            }
        }
        else {
            System.out.println("You are not a student!");
        }

        tempDelay();
    }
    
    public void withdawFromCamp(){
        if (currentUser.getRole().equals("student")){
            viewRegisteredCamp();
            System.out.print("Camp's name:");
            String campName = sc.nextLine();

            for (int i = 0; i < campList.size(); i++){
                Camp camp_ith = campList.get(i);

                if (camp_ith.getCampName().equals(campName)){
                    camp_ith.withdawFromCamp(currentUser);
                    break;
                }

            }
        }
        tempDelay();
    }

    // Staff will input campName as well as whether 
    // he wants to filter by attendee or committee (StudentRole)
    public void generateStudentReport(String campName, String StudentRole){
        int index = 1;
        
        if(currentUser.getRole().equals("staff")){
            for(int i = 0;i < campList.size(); i++){

                // Check for matching UserID
                if(campList.get(i).isStaffInCharge(currentUser)){
                    System.out.println(index + ". " + campList.get(i).getCampName());
                    // have to add getCampDescription(Camp campName) method
                    System.out.println("Camp Description: " + campList.get(i).getCampDescription());
                    System.out.println("Student Name");

                    switch(StudentRole){
                        // only prints attendee
                        case "attendee":
                            for(int j = 0;j < campList.get(i).getAttendeeSlot();j++){
                                Student currentStudent = campList.get(i).getAttendeeList().get(j);
                                System.out.println(currentStudent.getID() + "[Attendee]");
                            }
                        // only prints commitee
                        case "committee":
                            for(int j = 0;j < campList.get(i).getCommitteeSlot();j++){
                                Student currStudent = campList.get(i).getCommitteeList().get(j);
                                System.out.println(currStudent.getID() + "[Committee]");
                            }
                        // prints both attendee and committee
                        default:
                            for(int j = 0;j < campList.get(i).getAttendeeSlot();j++){
                                    Student currentStudent = campList.get(i).getAttendeeList().get(j);
                                    System.out.println(currentStudent.getID() + "[Attendee]");
                                }
                            for(int k = 0;k < campList.get(i).getCommitteeSlot();k++){
                                    Student currStudent = campList.get(i).getCommitteeList().get(k);
                                    System.out.println(currStudent.getID() + "[Committee]");
                                }
                        }
                    }
                    index++;
                }
            }
        else{
            System.out.println("You are not authorized!");
        }
    }


    public void generateCommitteeReport(String campName){
        if(currentUser.getRole().equals("staff")){
            for(int i = 0;i < campList.size();i++){
                if(campList.get(i).isStaffInCharge(currentUser) && campList.get(i).getCampName() == campName){
                    System.out.println("Camp: " + campList.get(i).getCampName());
                    System.out.println("Student Name");
                    for(int j = 0; j < campList.get(i).getCommitteeSlot();j++){
                        Student currentStudent = campList.get(i).getCommitteeList().get(j);
                        // have to add getCommitteeReport() method 
                        System.out.println(currentStudent.getID() + /*getCommitteeReport*/);
                    }
                }
            }
        }
        else{
            System.out.println("You are not authorized!");
        }
    }
    public void generateEnquiryReport(String campName){
        if(currentUser.getRole().equals("staff")){
            for(int i = 0; i <campList.size(); i++){

                Camp tempCamp = campList.get(i);
                if(tempCamp.isStaffInCharge(currentUser) && tempCamp.getCampName() == campName){
                    System.out.println("Camp: " + tempCamp.getCampName());
                    System.out.println("Enquiry" + "   " + "Student" + "   " + "Resolved");
                    for(int j = 0;j < tempCamp.getAttendeeSlot();j++){
                        Student currentStudent = tempCamp.getAttendeeList().get(j);
                        // have to add getEnquiries() to camp.java
                        System.out.println(tempCamp.getEnquiries().getDescription() 
                                            + tempCamp.getEnquiries().getAuthor()
                                            + tempCamp.getEnquiries().getResolved());
                                            
                    }
                }
            }
        }
    }

    
<<<<<<< Updated upstream
=======


    
    // Suggestion section
    public void createSuggestion(){
        pseudoClearScreen();

        System.out.println("Create Suggestion Screen");

        for (int i = 0; i < campList.size(); i++){
            Camp camp_ith = campList.get(i);

            if (camp_ith.isCommittee(currentUser)){
                camp_ith.createSuggestion(currentUser);
                tempDelay();
                return;
            }
        }
    }

    public void viewSuggestion(){
        pseudoClearScreen();

        System.out.println("View Suggestion Screen");

        String campName = "INITIAL CAMPNAME";
        if (!isCampCommittee()){
            System.out.print("Camp's name: ");
            campName = sc.nextLine();
        }

        for (int i = 0; i < campList.size(); i++){
            Camp camp_ith = campList.get(i);
            if (camp_ith.isCommittee(currentUser)){
                camp_ith.viewSuggestion(currentUser);
                tempDelay();
                return;
            }
            else {
                if (camp_ith.getCampName().equals(campName) && camp_ith.isStaffInCharge(currentUser)){
                    camp_ith.viewSuggestion(currentUser);   
                    tempDelay();
                    return;
                }
            }
        }

        tempDelay();
    }

    public void editSuggestion(){
        pseudoClearScreen();

        System.out.println("Edit Suggestion Screen");

        for (int i = 0; i < campList.size(); i++){
            Camp camp_ith = campList.get(i);

            if (camp_ith.isCommittee(currentUser)){
                camp_ith.editSuggestion(currentUser);
                tempDelay();
                return;
            }
        }

    }

    public void deleteSuggestion(){
        pseudoClearScreen();

        System.out.println("Delete Suggestion Screen");

        for (int i = 0; i < campList.size(); i++){
            Camp camp_ith = campList.get(i);

            if (camp_ith.isCommittee(currentUser)){
                camp_ith.deleteSuggestion(currentUser);
            }
        }
    }

    public void replySuggestion(){
        pseudoClearScreen();

        System.out.println("Reply Suggestion Screen:");

        System.out.print("Camp's Name: ");
        String campName = sc.nextLine();

        for (int i = 0; i < campList.size(); i++){
            Camp camp_ith = campList.get(i);

            if (camp_ith.getCampName().equals(campName) && camp_ith.isStaffInCharge(currentUser)){
                camp_ith.replySuggestion(currentUser);   
                tempDelay();
                return;
            }
        }
    }

    public void writeReport(){
        System.out.println("Which camp would you like to view? ");
        viewCreatedCamp();
        System.out.println("Camp Name: ");
        String tempcampName = sc.nextLine();
        System.out.println("Student Name: ");
        String tempStuName = sc.nextLine();
        for (int i = 0; i < campList.size(); i++){
            Camp currentCamp = campList.get(i);

            if (currentCamp.getCampName().equals(tempcampName)){
                for(int j = 0 ;j < currentCamp.getCommitteeList().size(); j++){
                    if(currentCamp.getCommitteeList().get(j).getID().equals(tempStuName)){
                        System.out.println("Report (type 'exit' to stop): ");

                        try {
                            // Create a BufferedWriter to write to a text file
                            // name will be Performance
                            // will be same directory as wherever your program is in
                            BufferedWriter writer = new BufferedWriter(new FileWriter("Performance.txt"));

                            // Read user input until they type 'exit'
                            while (true) {
                                String input = sc.nextLine();

                                if (input.equalsIgnoreCase("exit")) {
                                    break;
                                }

                                // Write the user input to the file
                                writer.write(input);
                                writer.newLine(); // Add a newline for each input
                            }

                            // Close the writer
                            writer.close();
                            System.out.println("Committee Performance report successfully written!");

                        } catch (IOException e) {
                            System.err.println("Error writing to file: " + e.getMessage());
                        }

                    }
                }
            }

            else{
                System.out.println("You did not create this camp!");
            }
        }
    }

}
>>>>>>> Stashed changes
