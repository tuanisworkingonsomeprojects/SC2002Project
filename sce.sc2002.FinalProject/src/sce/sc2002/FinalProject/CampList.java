package sce.sc2002.FinalProject;

import java.util.*;


public class CampList{

    private Login currentUser;
    private ArrayList<Camp> campList;

    public CampList(Login user){
        currentUser = user;
        campList    = new ArrayList<Camp>();
    }

    public void addCamp(Camp camp){
        System.out.println("Adding Camp to the list...");
        campList.add(camp);
    }

    public CampList getCampList(){return campList;}


    public void chooseCampToEdit(){

        if (currentUser.getRole().equals("staff")){
            Scanner sc = new Scanner(System.in);
            viewCreatedCamp();
            System.out.print("Camp to Edit (name): ");
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
                if (campList.get(i).getStaffInCharge().equals(currentUser.getUserid())){
                    System.out.println(index + ". " + campList.get(i).getCampName());
                }
            }
        }
        else {
            System.out.println("You don't have enough authority to view created camp");
        }
    }

    // public void viewAllCamp(){
    //     for (int i = 0; i < campList.size(); i++){
    //         if (camp)

    //         }
    //     }
    // }

    public void deleteCamp(){

    }

    public void registerCamp(){
        if (currentUser.getRole().equals("student")){
            









        }
    }
    


}
