package sce.sc2002.FinalProject;

import java.util.*;

public class EnquiryManager {
    private ArrayList<Enquiry> enquiries;

    public EnquiryManager() {
        this.enquiries = new ArrayList<>();
    }

    public void createEnquiry(Camp camp, Student author, String subject, String description) {
        Enquiry enquiry = new Enquiry(camp, author, subject, description, null, "", false);
        enquiry.setDescription(description);
        enquiries.add(enquiry);
    }
    

    public void editDescription(Enquiry enquiry, String newDescription) {
        enquiry.setDescription(newDescription);
    }

    public void markEnquiryAsResolved(Enquiry enquiry) {
        enquiry.markAsResolved();
    }
    
    public void editEnquiryReply(Enquiry enquiry, String newReply) {
        enquiry.setReply(newReply);
    }

    public ArrayList<Enquiry> getEnquiries() {
        return enquiries;
    }

    public List<Enquiry> getUnansweredEnquiries() {
        List<Enquiry> unansweredEnquiries = new ArrayList<>();
        for (Enquiry enquiry : enquiries) {
            if (!enquiry.getResolved()) {
                unansweredEnquiries.add(enquiry);
            }
        }
        return unansweredEnquiries;
    }

    public List<Enquiry> getAnsweredEnquiries() {
        List<Enquiry> answeredEnquiries = new ArrayList<>();
        for (Enquiry enquiry : enquiries) {
            if (enquiry.getResolved()) {
                answeredEnquiries.add(enquiry);
            }
        }
        return answeredEnquiries;
    }

}

