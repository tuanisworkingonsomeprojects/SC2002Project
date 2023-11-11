package sce.sc2002.FinalProject;
import java.util.*;

public class Enquiry {
    static int enquiryCount = 1;

    private String subject;
    private Date timestamp;
    private String description;
    private String reply;
    private int enquiryID;
    private boolean resolved;
    private Student author;
    private Staff replyAuthor;
    private Camp camp;
    private Student responsibleCommitteeMember;

    public Enquiry(Camp camp, Student author, String subject, String description, Staff replyAuthor, String reply, boolean status) {
        this.enquiryID = enquiryCount++;
        this.camp = camp;
        this.author = author;
        this.subject = subject;
        this.description = description;
        this.resolved = false; // Enquiries start as unresolved
        this.replyAuthor = replyAuthor; // Initialize reply author as null
        this.reply = null; // Initialize reply as null
        this.resolved = status;
        this.timestamp = new Date(); // Set the timestamp when the enquiry is created
    }

    public String getDescription() {
        return description;
    }

    public String getReply() {
        return reply;
    }

    public int getEnquiryID() {
        return enquiryID;
    }

    public Camp getCamp() {
        return camp;
    }

    public Student getAuthor() {
        return author;
    }

    public Staff getReplyAuthor() {
        return replyAuthor;
    }

    public boolean getResolved() {
        return resolved;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public int getEnquiryAgeInDays() {
        Date now = new Date();
        long diff = now.getTime() - timestamp.getTime();
        return (int) (diff / (24 * 60 * 60 * 1000)); // Convert milliseconds to days
    }

    public void markAsResolved() {
        resolved = true;
    }

    public void editEnquiry(String newDescription) {
        this.description = newDescription;
    }

    public void assignToCommitteeMember(Student student, Camp camp) {
        if (student.isCampCommittee() && (student.getCommitteeCamp() == null || student.getCommitteeCamp() == camp)) {
            student.setCommitteeCamp(camp);
            this.responsibleCommitteeMember = student;
        }
    }
    

    public boolean hasReply() {
        return reply != null;
    }

}
