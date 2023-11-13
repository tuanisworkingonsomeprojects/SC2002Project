package sce.sc2002.FinalProject;

import java.util.Date;

/*
    Represents an enquiry related to a camp in the Camp Application and Management System (CAMs).
 */
public class Enquiry {
    private static int enquiryCount = 1;

    private int enquiryID;
    private String subject;
    private String description;
    private String reply;
    private boolean resolved;
    private Date timestamp;
    private Student author;
    private Staff replyAuthor;
    private Camp camp;
    private Student responsibleCommitteeMember;

    public Enquiry(Camp camp, Student author, String subject, String description, Staff replyAuthor, String reply, boolean status) 
    {
        if (camp == null || author == null) 
        {
            throw new IllegalArgumentException("Camp and Author cannot be null");
        }
        this.enquiryID = enquiryCount++;
        this.camp = camp;
        this.author = author;
        this.subject = subject;
        this.description = description;
        this.replyAuthor = replyAuthor;
        this.reply = reply;
        this.resolved = status;
        this.timestamp = new Date();
    }

    @Override
    public String toString() 
    {
        return "Enquiry ID: " + enquiryID +
            "\nSubject: " + subject +
            "\nDescription: " + description +
            "\nResolved: " + resolved +
            "\nTimestamp: " + timestamp +
            "\nAuthor: " + author +
            "\nReply Author: " + replyAuthor +
            "\nCamp: " + camp +
            "\nCommittee Member: " + responsibleCommitteeMember;
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

    public void editDescription(String newDescription) {
        this.description = newDescription;
    }

    public void assignToCommitteeMember(Student student, Camp camp) 
    {
        if (student == null || camp == null) 
        {
            throw new IllegalArgumentException("Student and Camp cannot be null");
        }

        if (student.isCampCommittee() && (student.getCommitteeCamp() == null || student.getCommitteeCamp() == camp)) 
        {
            student.setCommitteeCamp(camp);
            this.responsibleCommitteeMember = student;
        }
    }

    public boolean hasReply() {
        return reply != null && !reply.isEmpty();
    }
}

