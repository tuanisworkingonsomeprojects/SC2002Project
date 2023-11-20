package sce.sc2002.FinalProject;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/*
    Represents an enquiry related to a camp in the Camp Application and Management System (CAMs).
 */
public class Enquiry {

    private static int counter = 0;

    private int enquiryID;
    private String subject;
    private String description;
    private String reply;
    private boolean resolved;

    private Student author;

    public Enquiry(Student author, String subject, String description) {
        validateNonNull(author, "Author cannot be null");

        this.enquiryID = counter++;
        this.author = author;
        this.subject = subject;
        this.description = description;
        this.resolved = false;  // Default to unresolved
    }

    public Enquiry(int EID, String AID, String faculty, String title, String newDescription, boolean newResolve, String newReply){
        enquiryID = EID;
        author = new Student(AID, faculty);
        subject = title;
        description = newDescription;
        resolved = newResolve;
        reply = newReply;
        counter = EID;
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

    public Student getAuthor() {
        return author;
    }

    public boolean getResolved() {
        return resolved;
    }

    public String getSubject(){
        return subject;
    }

    public void setSubject(String title){
        subject = title;
    }

    public void setDescription(String description) {
        if (!resolved) {
            this.description = description;
        } else {
            throw new IllegalStateException("Cannot change description of resolved enquiry.");
        }
    }
    
    public void setReply(String reply) {
        if (!resolved) {
            this.reply = reply;
            // Optionally, if the reply is being set for the first time, automatically mark the enquiry as resolved
            if (!hasReply()) {
                resolveEnquiry();
            }
        } else {
            throw new IllegalStateException("Cannot change reply of resolved enquiry.");
        }
    }

    public void markAsResolved() 
    {
        resolved = true;
    }

    // Protected to ensure enquiry is not edited after being resolved
    protected void editDescription(String newDescription) 
    {
        if (!resolved) {
            this.description = newDescription;
        } else {
            throw new IllegalStateException("Cannot edit resolved enquiry.");
        }
    }

    // Utility method for null checking
    private void validateNonNull(Object obj, String message) 
    {
        if (obj == null) 
        {
            throw new IllegalArgumentException(message);
        }
    }

    protected void resolveEnquiry() 
    {
        this.resolved = true;
    }

    public boolean hasReply() 
    {
        return reply != null && !reply.isEmpty();
    }
}

