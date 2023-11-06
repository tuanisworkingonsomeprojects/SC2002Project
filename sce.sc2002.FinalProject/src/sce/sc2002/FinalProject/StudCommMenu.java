package sce.sc2002.FinalProject;// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import java.util.*;
public class StudCommMenu extends Committee{
    boolean exit;
    private int enquiries = 10;

    public void runMenu() {
        printHeader();
        while (!exit) {
            display();
            int choice = getMenuChoice();
            performAction(choice);
        }
    }

    private void printHeader() {
        System.out.println("+-----------------------------------+");
        System.out.println("|        Welcome to Mr.V's          |");
        System.out.println("|        Awesome Camp App           |");
        System.out.println("+-----------------------------------+");
    }

    public void display() {

        System.out.println("Student Committee Portal: ");
        System.out.printf("You have %d new queries.\n", enquiries);
        System.out.println("1. Change your password.");
        System.out.println("2. View your camp details");
        System.out.println("3. Submit suggestions");
        System.out.println("4. View suggestions");
        System.out.println("5. Edit suggestions");
        System.out.println("6. Delete suggestions");
        System.out.println("7. Generate attendance report");
        System.out.println("8. View Enquiry List");
        System.out.println("9. Answer Enquiry List");
        System.out.println("10. Quit");
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
            }
            if (choice < 0 || choice > 10) {
                System.out.println("Choice outside of range. Please chose again.");
            }
        } while (choice < 0 || choice > 10);
        return choice;
    }

    private void performAction(int choice) {
        switch (choice) {
            case 1:
                System.out.println("Changing password...");
                // calls method to change password
                changePassword();
                break;
            case 2:
                System.out.println("Viewing camp details...");
                //  calls display camp methods
                //  displays details of User's camp
                viewCampDetails();
                break;
            case 3:
                System.out.println("Submitting Suggestions...");
                // calls method to submit suggestions
                submitSuggestions();
                break;
            case 4:
                System.out.println("Viewing Suggestions...");
                // calls method to view suggestions
                viewSuggestions();
                break;
            case 5:
                System.out.println("Editing Suggestions...");
                // calls method to edit suggestions
                editSuggestions();
                break;
            case 6:
                System.out.println("Deleting Suggestion...");
                // calls method to delete suggestions
                deleteSuggestions();
                break;
            case 7:
                System.out.println("Generating attendance report...");
                // calls method to generate attendance report
                generateAttendanceReport();
                break;
            case 8:
                System.out.println("Viewing enquiry list...");
                // calls method to view enquiry list
                viewEnquiryList();
                break;
            case 9:
                System.out.println("Answering enquiry list...");
                // calls method to answer enquiry list
                answerEnquiryList();
                break;
            default:
                System.out.println("Unknown error has occurred.");
        }

    }
    public void changePassword() {
        // Create an instance of the Password class
        Password passwordManager = new Password();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your new password: ");
        String newPassword = scanner.nextLine();

        // Call the modified changePassword method from the Password class
        passwordManager.changePassword();

        System.out.println("Password Changed");
    }

    public void viewCampDetails()
    {
        System.out.println("Your camp details: ");
    }
    public void submitSuggestions()
    {
        System.out.println("Suggestions submitted");
    }
    public void viewSuggestions()
    {
        System.out.println("Current suggestions: ");
    }
    public void editSuggestions()
    {
        System.out.println("Suggestions edited");
    }
    public void deleteSuggestions()
    {
        System.out.println("Suggestions deleted");
    }
    public void generateAttendanceReport()
    {
        System.out.println("Attendance report generated");
    }
    public void viewEnquiryList()
    {
        System.out.println("Current enquiry list: ");
    }
    public void answerEnquiryList()
    {
        System.out.println("Enquiry list answered");
    }
}
