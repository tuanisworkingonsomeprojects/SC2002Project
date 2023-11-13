package sce.sc2002.FinalProject;

import java.util.*;

public class EnquiryMenu {
    private EnquiryManager enquiryManager;
    private Student currentUser;
    private Scanner scanner;

    public EnquiryMenu(Student currentUser, EnquiryManager enquiryManager) {
        this.currentUser = currentUser;
        this.enquiryManager = enquiryManager;
        this.scanner = new Scanner(System.in);
    }

    public void runMenu() {
        int choice;

        do {
            try {
                System.out.println("Enquiry Menu:");
                System.out.println("1. View Unanswered Enquiries");
                System.out.println("2. Edit Enquiry");
                System.out.println("3. Delete Enquiry");
                System.out.println("4. View Answered Enquiries");
                System.out.println("5. Back to Main Menu");
                System.out.println();
                System.out.print("Enter your choice: ");

                choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        viewUnansweredEnquiries();
                        break;
                    case 2:
                        editEnquiry();
                        break;
                    case 3:
                        deleteEnquiry();
                        break;
                    case 4:
                        viewAnsweredEnquiries();
                        break;
                    case 5:
                        System.out.println("Returning to the Main Menu.");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine();
                choice = 0;
            }
        } while (choice != 5);

        scanner.close();
    }

    private Enquiry findEnquiryById(int enquiryId, List<Enquiry> enquiries) {
        for (Enquiry enquiry : enquiries) {
            if (enquiry.getEnquiryID() == enquiryId) {
                return enquiry;
            }
        }
        return null; // Enquiry with the specified ID not found
    }

    private void editEnquiry() {
        System.out.print("Enter the ID of the enquiry to edit: ");
        int enquiryId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        Enquiry enquiryToEdit = findEnquiryById(enquiryId, enquiryManager.getEnquiries());

        if (enquiryToEdit != null) 
        {
            System.out.print("Enter the new description: ");
            String newDescription = scanner.nextLine();
            enquiryManager.editDescription(enquiryToEdit, newDescription);
            System.out.println("Enquiry edited successfully.");
        } else 
        {
            System.out.println("Enquiry not found.");
        }
    }

    private void deleteEnquiry() 
    {
        System.out.print("Enter the ID of the enquiry to delete: ");
        int enquiryId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        Enquiry enquiryToDelete = findEnquiryById(enquiryId, enquiryManager.getEnquiries());

        if (enquiryToDelete != null) 
        {
            enquiryManager.getEnquiries().remove(enquiryToDelete);
            System.out.println("Enquiry deleted successfully.");
        } else {
            System.out.println("Enquiry not found.");
        }
    }

    private void viewAnsweredEnquiries() 
    {
        List<Enquiry> answeredEnquiries = enquiryManager.getAnsweredEnquiries();
        if (answeredEnquiries.isEmpty()) {
            System.out.println("No answered enquiries.");
        } else {
            System.out.println("Answered Enquiries:");
            for (Enquiry enquiry : answeredEnquiries) {
                System.out.println(enquiry);
            }
        }
    }
    
    private void viewUnansweredEnquiries() {
        List<Enquiry> unansweredEnquiries = enquiryManager.getUnansweredEnquiries();
        if (unansweredEnquiries.isEmpty()) {
            System.out.println("No unanswered enquiries.");
        } else {
            System.out.println("Unanswered Enquiries:");
            for (Enquiry enquiry : unansweredEnquiries) {
                System.out.println(enquiry);
            }
        }
    }
}
