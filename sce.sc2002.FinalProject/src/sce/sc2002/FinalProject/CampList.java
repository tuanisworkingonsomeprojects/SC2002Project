package sce.sc2002.FinalProject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.text.SimpleDateFormat;


public class CampList{

    private String accessPath;

    private Login currentUser;
    private ArrayList<Camp> campList;

    private Scanner textFile;
    private String dataPath;
    

    public CampList(Login user){
        currentUser = user;
        campList    = new ArrayList<Camp>();
    }

    private int getMenuChoice() {
        Scanner keyboard = new Scanner(System.in);
        int choice = -1;
        do {
            System.out.print("Enter your choice: ");
            try {
                choice = Integer.parseInt(keyboard.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid selection. Numbers only please.");
                continue;
            }

            if (choice < 0) System.out.println("Valid number only");

        } while (choice < 0);
        return choice;
    }

    public void exportData(){
         StringBuilder stringBuilder = new StringBuilder();

        // First Line will always be the number of camp in the list
        stringBuilder.append(campList.size() + "\n");

        // TODO: remember to delete this
        System.out.println("Num of Camp: " + campList.size());

        for (int i = 0; i < campList.size(); i++){
            Camp camp_ith = campList.get(i);

            // 2 is camp's name
            stringBuilder.append(camp_ith.getCampName() + "\n");

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            // 3 line is the start date
            stringBuilder.append(sdf.format(camp_ith.getStartDate()) + "\n");

            // 4 line is the end date
            stringBuilder.append(sdf.format(camp_ith.getEndDate()) + "\n");

            // 5 line is the registration date
            stringBuilder.append(sdf.format(camp_ith.getClosingDate()) + "\n");

            // 6 line is the School
            stringBuilder.append(camp_ith.getFaculty() + "\n");

            // 7 line is Location
            stringBuilder.append(camp_ith.getLocation() + "\n");

            // 8 line is number of Slot
            stringBuilder.append(camp_ith.getAttendeeSlot() + "\n");

            // 9 line is number of slot
            stringBuilder.append(camp_ith.getCommitteeSlot() + "\n");

            // 10 line is camp detail
            stringBuilder.append(camp_ith.getDescription() + "\n");

            // 11 line is visibility
            stringBuilder.append(camp_ith.getVisibility() + "\n");

            // 12 line is staff in charge
            stringBuilder.append(camp_ith.getStaffInCharge() + "\n");

            // 13 line is number of Camp commitees
            stringBuilder.append(camp_ith.getCommitteeList().size() + "\n");

            for (int j = 0; j < camp_ith.getCommitteeList().size(); j++){
                Committee committee_jth = (Committee) camp_ith.getCommitteeList().get(j);

                // 13.1 is committee ID
                stringBuilder.append(committee_jth.getID() + "\n");

                // 13.2 is committee faculty
                stringBuilder.append(committee_jth.getFaculty() + "\n");

                // 13.3 is committee point
                stringBuilder.append(committee_jth.getPoint() + "\n");
            }

            // 14 line is number of Camp attendees
            stringBuilder.append(camp_ith.getAttendeeList().size() + "\n");

            for (int j = 0; j < camp_ith.getAttendeeList().size(); j++){
                Student student_jth = camp_ith.getAttendeeList().get(j);

                // 14.1 is attendee ID
                stringBuilder.append(student_jth.getID() + "\n");

                // 14.2 is attendee faculty
                stringBuilder.append(student_jth.getFaculty() + "\n");

            }

            // 15 line is number of BlackList students
            stringBuilder.append(camp_ith.getBlackList().size() + "\n");

            for (int j = 0; j < camp_ith.getBlackList().size(); j++){
                Student student_jth = camp_ith.getBlackList().get(i);
                
                // 15.1 is blacklist student ID
                stringBuilder.append(student_jth.getID() + "\n");

                // 15.2 is blacklist student faculty
                stringBuilder.append(student_jth.getFaculty() + "\n");

            }

            // 16 line is number of Enquiry
            stringBuilder.append(camp_ith.getEnquiries().size() + "\n");

            for (int j = 0; j < camp_ith.getEnquiries().size(); j++){
                Enquiry enquiry_jth = camp_ith.getEnquiries().get(i);

                // 16.1 is Enquiry ID
                stringBuilder.append(enquiry_jth.getEnquiryID() + "\n");

                // 16.2 is Enquiry author ID
                stringBuilder.append(enquiry_jth.getAuthor().getID() + "\n");

                // 16.3 is Enquiry author faculty
                stringBuilder.append(enquiry_jth.getAuthor().getFaculty() + "\n");

                // 16.4 is Enquiry subject / Title
                stringBuilder.append(enquiry_jth.getSubject() + "\n");

                // 16.5 is Enquiry description
                stringBuilder.append(enquiry_jth.getDescription() + "\n");

                // 16.6 is Enquiry resolve status
                stringBuilder.append(enquiry_jth.getResolved() + "\n");

                // 16.7 is Enquiry reply
                if (enquiry_jth.getResolved())
                stringBuilder.append(enquiry_jth.getReply() + "\n");
            }

            // 17 line is number of suggestions
            stringBuilder.append(camp_ith.getSuggestions().size() + "\n");

            for (int j = 0; j < camp_ith.getSuggestions().size(); j++){
                Suggestion suggestion_jth = camp_ith.getSuggestions().get(j);

                // 17.1 is suggestion ID
                stringBuilder.append(suggestion_jth.getSuggestionID() + "\n");

                // 17.2 is author ID
                stringBuilder.append(suggestion_jth.getAuthor().getID() + "\n");
                // We just need the author ID so when we retrieve the info we just need to search for his name in the committee list and then assign him in as an author

                // 17.3 is Description
                stringBuilder.append(suggestion_jth.getDescription() + "\n");

                // 17.4 is status
                stringBuilder.append(suggestion_jth.getStatus().toString() + "\n");

                // 17.5 is resolve status
                stringBuilder.append(suggestion_jth.getResolved() + "\n");
            }


        }

        



        String data = stringBuilder.toString();
        StringToText.stringToText(data, dataPath);
    }

    public void loadData(){
        
        
        try {
            textFile = new Scanner(new File(System.getProperty("user.dir") + "/src/sce/sc2002/FinalProject/CampListData/CampListData.txt"));
            dataPath = System.getProperty("user.dir") + "/src/sce/sc2002/FinalProject/CampListData/CampListData.txt";
        }
        catch (final Exception e) {
            try {
                textFile = new Scanner(new File(System.getProperty("user.dir") + "/sce.sc2002.FinalProject/src/sce/sc2002/FinalProject/CampListData/CampListData.txt"));
                dataPath = System.getProperty("user.dir") + "/sce.sc2002.FinalProject/src/sce/sc2002/FinalProject/CampListData/CampListData.txt";
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
        }

        int numOfCamp = Integer.parseInt(textFile.nextLine());

        if (numOfCamp == 0){
            return;
        }

        // TODO: remember to delete this
        System.out.println("Num of camp: " + numOfCamp);

        for (int i = 0; i < numOfCamp; i++){
            String camp_ith_name = textFile.nextLine();

            // TODO: remember to delete this
            System.out.println("Camp's name: " + camp_ith_name);

            String camp_ith_startDate = textFile.nextLine();
            String camp_ith_endDate   = textFile.nextLine();
            String camp_ith_closingDate = textFile.nextLine();

            // TODO: remember to delete this
            System.out.println("start end close: " + camp_ith_startDate + " " + camp_ith_endDate + " " + camp_ith_closingDate);


            String camp_ith_faculty   = textFile.nextLine();
            // TODO: remember to delete this
            System.out.println("Faculty: " + camp_ith_faculty);

            String camp_ith_location  = textFile.nextLine();
            // TODO: remember to delete this
            System.out.println("Location: " + camp_ith_location);

            int    camp_ith_attendeeSlot  = Integer.parseInt(textFile.nextLine());
            int    camp_ith_committeeSlot = Integer.parseInt(textFile.nextLine());
            // Remember to delete this
            System.out.println("Attendee, Commitee: " + camp_ith_attendeeSlot + ", " + camp_ith_committeeSlot);

            String camp_ith_detail    = textFile.nextLine();
            // Remember to delete this
            System.out.println("Camp Detail: " + camp_ith_detail);

            boolean camp_ith_available = Boolean.parseBoolean(textFile.nextLine());
            // Remember to delete this
            System.out.println("Visibility: " + camp_ith_available);

            String camp_ith_staffInCharge = textFile.nextLine();
            // Remember to delete this
            System.out.println("Staff in charge: " + camp_ith_staffInCharge);

            Camp camp_ith = new Camp(this,
                                    camp_ith_name,
                                    camp_ith_startDate,
                                    camp_ith_endDate,
                                    camp_ith_closingDate,
                                    camp_ith_faculty,
                                    camp_ith_location,
                                    camp_ith_attendeeSlot,
                                    camp_ith_committeeSlot,
                                    camp_ith_detail,
                                    camp_ith_available,
                                    camp_ith_staffInCharge);
            addCamp(camp_ith);
            
            
            int noOfCommittee = Integer.parseInt(textFile.nextLine());

            for (int j = 0; j < noOfCommittee; j++){
                String committee_jth_ID = textFile.nextLine();
                String committee_jth_faculty = textFile.nextLine();
                int committee_jth_point = Integer.parseInt(textFile.nextLine());
                camp_ith.addCommittee(new Committee(committee_jth_ID, committee_jth_faculty, committee_jth_point));

                // TODO: remember to delete this:
                System.out.println("Size: " + camp_ith.getCommitteeList().size());
            }

            int noOfAttendee = Integer.parseInt(textFile.nextLine());

            for (int j = 0; j < noOfAttendee; j++){
                String attendee_jth_ID = textFile.nextLine();
                String attendee_jth_faculty = textFile.nextLine();

                camp_ith.addAttendee(new Student(attendee_jth_ID, attendee_jth_faculty));
            }

            int noOfBlacklist = Integer.parseInt(textFile.nextLine());

            for (int j = 0; j < noOfBlacklist; j++){
                String blacklist_jth_ID = textFile.nextLine();
                String blacklist_jth_faculty = textFile.nextLine();

                camp_ith.addBlacklist(new Student(blacklist_jth_ID, blacklist_jth_faculty));
            }

            int noOfEnquiries = Integer.parseInt(textFile.nextLine());

            for (int j = 0; j < noOfEnquiries; j++){
                int enquiry_jth_ID = Integer.parseInt(textFile.nextLine());

                String enquiry_jth_authorID = textFile.nextLine();
                String enquity_jth_author_faculty = textFile.nextLine();
                String enquiry_jth_subject = textFile.nextLine();
                String enquiry_jth_description = textFile.nextLine();
                boolean enquiry_jth_resolve = Boolean.parseBoolean(textFile.nextLine());
                String enquiry_jth_reply = "";
                if (enquiry_jth_resolve){
                    enquiry_jth_reply = textFile.nextLine();
                }
                camp_ith.addEnquiry(new Enquiry(enquiry_jth_ID, 
                                                enquiry_jth_authorID, 
                                                enquity_jth_author_faculty, 
                                                enquiry_jth_subject,
                                                enquiry_jth_description,
                                                enquiry_jth_resolve,
                                                enquiry_jth_reply));
            }

            int noOfSuggestion = Integer.parseInt(textFile.nextLine());

            for (int j = 0; j < noOfSuggestion; j++){
                int suggestion_jth_ID = Integer.parseInt(textFile.nextLine());

                String suggestion_jth_authorID = textFile.nextLine();

                Committee suggestion_jth_author = null;

                for (int k = 0; k < camp_ith.getCommitteeList().size(); k++){
                    Committee committee_kth = (Committee) camp_ith.getCommitteeList().get(k);

                    if (committee_kth.getID().equals(suggestion_jth_authorID)){
                        suggestion_jth_author = committee_kth;
                        break;
                    }
                }

                String suggestion_jth_description = textFile.nextLine();
                SuggestionStatus suggestion_jth_status = SuggestionStatus.valueOf(textFile.nextLine());
                boolean suggestion_jth_resolve = Boolean.parseBoolean(textFile.nextLine());

                camp_ith.addSuggesstion(new Suggestion(suggestion_jth_ID, 
                                                       suggestion_jth_author,
                                                       suggestion_jth_description,
                                                       suggestion_jth_status,
                                                       suggestion_jth_resolve));
            }
        }
    }

    
    private Scanner sc = new Scanner(System.in);
    
    public ArrayList<Camp> getCampList(){return campList;}

    // System method

    public boolean isCampCommittee(){
        for (int i = 0; i < campList.size(); i++){
            Camp camp_ith = campList.get(i);

            if (camp_ith.isCommittee(currentUser)){
                return true;
            }
        }

        return false;
    }

    private void tempDelay(){
        System.out.println("Press Enter to go Back!");
        sc.nextLine();

        // Pseudo clear screen
        pseudoClearScreen();

        return;
    }

    private void pseudoClearScreen(){
        for (int i = 0; i < 100; i++){
            System.out.println();
        }
    }


    // Method for staff

    public void createCamp(){
        pseudoClearScreen();
        addCamp(Camp.createCamp(currentUser, this));
        tempDelay();
    }

    private void addCamp(Camp camp){
        System.out.println("Adding Camp to the list...");

        // If the list have nothing just add the first camp to the list
        if (campList.size() == 0){
            campList.add(camp);
            return;
        }

        else {

            // If the camp name is smaller than the current ith camp just insert it to the ith position.
            for (int i = 0; i < campList.size(); i++){
                Camp camp_ith = campList.get(i);

                if (camp.getCampName().compareTo(camp_ith.getCampName()) < 0){
                    campList.add(i, camp);
                    return;
                }
            }

            // If it reach the end of the list just append it in the end of the list.
            campList.add(camp);
        }
    }

    public void deleteCamp(){
        pseudoClearScreen();
        viewCreatedCamp();
        System.out.println("Deleting Camp Screen: ");
        Scanner sc = new Scanner(System.in);
        Camp camp_ith;

        if (currentUser.getRole().equals("staff")){
            
            System.out.print("Camp's name: ");
            String delCampName = sc.nextLine();

            for (int i = 0; i < campList.size(); i++){
                camp_ith = campList.get(i);

                if (camp_ith.getCampName().equals(delCampName)){

                    if (camp_ith.getAttendeeList().size() > 0 || camp_ith.getCommitteeList().size() > 0){
                        System.out.println("This camp cannot be deleted since it already have attendees/commitees members.");
                        
                        tempDelay();
                        return;
                    }

                    System.out.print("Confirm delete (Y/N):");
                    String choice = sc.next();

                    if (choice.equals("Y") || choice.equals("y")){
                        campList.remove(i);
                        System.out.println("Camp remove Successfully!");
                        tempDelay();
                        return;
                    }
                    else {
                        System.out.println("Cancelling...");
                        tempDelay();
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
        pseudoClearScreen();
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


    // This method will be called by viewCamp()
    private void handleCampFilter(int choice){
        switch(choice){
            case 1:
                viewAllCamp();
                break;
            
            case 2:
                if (currentUser.getRole().equals("staff"))  viewCreatedCamp();
                else viewRegisteredCamp();
                break;

            case 3:
                viewAvailableCamp();
                break;

            case 4:
                viewCampWithLocation();
                break;

            case 5:
                viewCampWithFaculty();
                break;

            case 6:
                if (isCampCommittee() || currentUser.getRole().equals("staff")) viewCampDetail();
                else System.out.println("Unknow Error occured");
                break;
            default:
                System.out.println("Choice out of range");
                break;
        }

    }

    public void viewCamp(){
        pseudoClearScreen();
        System.out.println("Camps view filter: ");
        System.out.println("1. View all camps");
        if (currentUser.getRole().equals("staff")){
            System.out.println("2. View created camps");
        }
        else {
            System.out.println("2. View registered camp");
        }
        System.out.println("3. View available camps");
        System.out.println("4. View camp with location");
        System.out.println("5. View camp with faculty");

        if (isCampCommittee() || currentUser.getRole().equals("staff"))
        System.out.println("6. View camp detail");
        
        System.out.print("Your choice: ");
        handleCampFilter(getMenuChoice());
        tempDelay();
    }


    private void viewAllCamp(){
        pseudoClearScreen();


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
    }

    private void viewCreatedCamp(){
        pseudoClearScreen();
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
    }

    private void viewRegisteredCamp(){
        pseudoClearScreen();

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
    }
    // This method that I've implemented will ask the Staff to choose to print the list of
    // Attendee or the list of Committee already so you don't have to worry about it!

    private void viewAvailableCamp(){
        pseudoClearScreen();

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

    }

    // This method is called by viewCampWithLocation()
    private void printLocation(){
        int index = 1;
        
        System.out.println("Camp Locations:");

        for (int i = 0; i < campList.size(); i++){
            Camp camp_ith = campList.get(i);

            if (camp_ith.allowToView(currentUser)){
                System.out.println(index + ". " + camp_ith.getLocation());
                index++;
            }
        }

        if (index == 1) System.out.println("No camp with such loction has been created in the system.");
    }

    public void viewCampWithLocation(){
        pseudoClearScreen();
        int index = 1;


        System.out.println("View Camp with Location filter:");
        printLocation();

        if (campList.size() == 0) {
            return;
        }

        System.out.print("Location: ");
        String location = sc.nextLine();

        for (int i = 0; i < campList.size(); i++){
            Camp camp_ith = campList.get(i);

            if (camp_ith.getLocation().equals(location) && camp_ith.allowToView(currentUser)){
                System.out.print(index + ". " + camp_ith.getCampName());
                if (camp_ith.isAttendee(currentUser))   System.out.println(" [Attendee]");
                if (camp_ith.isCommittee(currentUser))  System.out.println(" [Committee]");
                if (camp_ith.isStaffInCharge(currentUser))  System.out.println(" [In charge]");
                else System.out.println();
            }
        }
    }

    public void printFaculty(){
        int index = 1;
        
        System.out.println("Camp Faculties:");

        for (int i = 0; i < campList.size(); i++){
            Camp camp_ith = campList.get(i);

            if (camp_ith.allowToView(currentUser)){
                System.out.println(index + ". " + camp_ith.getFaculty());
                index++;
            }
        }

        if (index == 1) System.out.println("No camp with such faculty created in the system.");
    }

    public void viewCampWithFaculty(){
        pseudoClearScreen();
        int index = 1;


        System.out.println("View Camp with faculty filter:");
        printFaculty();

        if (campList.size() == 0) {
            return;
        }

        System.out.print("Faculty: ");
        String faculty = sc.nextLine();

        for (int i = 0; i < campList.size(); i++){
            Camp camp_ith = campList.get(i);

            if (camp_ith.getFaculty().equals(faculty) && camp_ith.allowToView(currentUser)){
                System.out.print(index + ". " + camp_ith.getCampName());
                if (camp_ith.isAttendee(currentUser))   System.out.println(" [Attendee]");
                if (camp_ith.isCommittee(currentUser))  System.out.println(" [Committee]");
                if (camp_ith.isStaffInCharge(currentUser))  System.out.println(" [In charge]");
                else System.out.println();
                index++;
            }
        }        
        if (index == 1) System.out.println("No camp with such faculty created in the system.");
    }

    public void viewStudentList(){

        pseudoClearScreen();
        for (int i = 0; i < 100; i++){
            System.out.println();
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

                int choice = getMenuChoice();

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

    // This method allow camp committee to view detail of a camp
    public void viewCampDetail(){
        System.out.println("View camp detail screen:");
        
        String campName = "INIT CAMPNAME";
        if (currentUser.getRole().equals("staff")){
            campName = sc.nextLine();
        }


        if (isCampCommittee() || currentUser.getRole().equals("staff")){
            
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");


            for (int i = 0; i < campList.size(); i++){
                Camp camp_ith = campList.get(i);
                if (camp_ith.isCommittee(currentUser) || camp_ith.isStaffInCharge(currentUser)){

                    // Pseudo clear screen
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
            System.out.println("You are not a camp Committee or staff in charge of any camp!");

        }
        tempDelay();
    }

    // This method allow to view all the camps of which a student is a committee.
    public void viewCampCommittee(){
        System.out.println("You are currently Camp Committee of this camps:");

        for (int i = 0; i < campList.size(); i++){
            Camp camp_ith = campList.get(i);

            if (camp_ith.isCommittee(currentUser)){
                System.out.println(camp_ith.getCampName());
                tempDelay();
                return;
            }
        }


        System.out.println("You are currently not a commitee of any camp!");
        tempDelay();

    }

    public void registerCamp(){
        pseudoClearScreen();
        System.out.println("Camp Registration");

        if (currentUser.getRole().equals("student")){

            // View the available camp
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
                System.out.print("Camp's name: ");
                String campName = sc.nextLine();

            
            // Find the matching camp to register the Attendee
            
            for (int i = 0; i < campList.size(); i++){
                Camp camp_ith = campList.get(i);

                if (camp_ith.isAvailable() && camp_ith.getCampName().equals(campName)){
                    camp_ith.registerCamp(currentUser);
                    tempDelay();
                    return;
                }
            }

            System.out.println("There is no camp with such name!");
        }
        else {
            System.out.println("You are not a student!");
        }

        tempDelay();
    }
    
    public void withdawFromCamp(){
        pseudoClearScreen();
        System.out.println("Withdraw from camp screen:");
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

    // Enquiry section
    public void createEnquiry(){
        pseudoClearScreen();

        System.out.println("Create Enquiry Screen:");

        System.out.println("Camp's name: ");
        String campName = sc.nextLine();

        for (int i = 0; i < campList.size(); i++){
            Camp camp_ith = campList.get(i);

            if (camp_ith.getCampName().equals(campName)){
                camp_ith.createEnquiry(currentUser);
                tempDelay();
                return;
            }
        }

        System.out.println("There is no camp with such name");
        tempDelay();
    }

    public void viewEnquiry(){
        pseudoClearScreen();

        System.out.println("View Enquiry Screen");

        System.out.print("Camp's name: ");
        String campName = sc.nextLine();

        for (int i = 0; i < campList.size(); i++){
            Camp camp_ith = campList.get(i);

            if (camp_ith.getCampName().equals(campName)){
                camp_ith.viewEnquiry(currentUser);
                tempDelay();
                return;
            }
        }

        System.out.println("There is no camp with such name");
        tempDelay();
    }

    public void editEnquiry(){
        pseudoClearScreen();

        System.out.println("Edit Enquiry Screem");

        System.out.print("Camp's name: ");
        String campName = sc.nextLine();

        for (int i = 0; i < campList.size(); i++){
            Camp camp_ith = campList.get(i);

            if (camp_ith.getCampName().equals(campName)){
                camp_ith.editEnquiry(currentUser);
                tempDelay();
                return;
            }
        }

        System.out.println("There is no camp with such name");
        tempDelay();
    }

    public void deletetEnquiry(){
        pseudoClearScreen();

        System.out.println("Delete Enquiry Screem");

        System.out.print("Camp's name: ");
        String campName = sc.nextLine();

        for (int i = 0; i < campList.size(); i++){
            Camp camp_ith = campList.get(i);

            if (camp_ith.getCampName().equals(campName)){
                camp_ith.deleteEnquiry(currentUser);
                tempDelay();
                return;
            }
        }

        System.out.println("There is no camp with such name");
        tempDelay();
    }

    public void replyEnquiry(){
        pseudoClearScreen();
        String campName = "INIT CAMPNAME";

        if (currentUser.getRole().equals("staff")){
            System.out.println("Reply Enquiry Screem");

            System.out.print("Camp's name: ");
            campName = sc.nextLine();
        }
        else {
            for (int i = 0; i < campList.size(); i++){
                Camp camp_ith = campList.get(i);
                if (camp_ith.isCommittee(currentUser))
                    campName = camp_ith.getCampName();
            }
        }
        
        for (int i = 0; i < campList.size(); i++){
            Camp camp_ith = campList.get(i);

            if (camp_ith.getCampName().equals(campName)){
                if (camp_ith.isStaffInCharge(currentUser) || camp_ith.isCommittee(currentUser)){
                    camp_ith.replyEnquiry(currentUser);
                    tempDelay();
                    return;
                }
                else {
                    System.out.println("You done have authority to reply this enquiry");
                }
            }
        }

        System.out.println("There is no camp with such name");
        tempDelay();
    }

    


    
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


    public void generateReport(){
        pseudoClearScreen();
        System.out.println("Generate Report Screen:");

        String campName = "INIT CAMPNAME";
        
        if (currentUser.getRole().equals("staff")){
            System.out.println("Camp's name: ");
            campName = sc.nextLine();   
        }
        

        for (int i = 0; i < campList.size(); i++){
            Camp camp_ith = campList.get(i);



            if ((camp_ith.getCampName().equals(campName) && camp_ith.isStaffInCharge(currentUser)) || camp_ith.isCommittee(currentUser)){
                camp_ith.generateReportString(currentUser);
            }
            break;
        }
  
    }




}
