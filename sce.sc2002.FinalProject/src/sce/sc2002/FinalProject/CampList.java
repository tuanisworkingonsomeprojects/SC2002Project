package sce.sc2002.FinalProject;

import java.util.*;


public class CampList{

    private Login currentUser;
    private ArrayList<Camp> campList;

    public CampList(Login user){
        currentUser = user;
        campList    = new ArrayList<Camp>();
    }

    
    public ArrayList<Camp> getCampList(){return campList;}

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
            String delCampName = sc.next();

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
    }

    public void chooseCampToEdit(){
        System.out.println("Editing Camp Screen:");

        if (currentUser.getRole().equals("staff")){
            Scanner sc = new Scanner(System.in);
            viewCreatedCamp();
            System.out.print("Camp's name': ");
            String campName = sc.next();

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
    }

    

    

    public void registerCamp(){
        if (currentUser.getRole().equals("student")){
            









        }
    }
    


}
