package sce.sc2002.FinalProject;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/*
    Represents an enquiry related to a camp in the Camp Application and Management System (CAMs).
 */
/**
 * Represents an enquiry related to a camp in the Camp Application and Management System (CAMs).
 */
public class Enquiry {
	/**
	 * Scanner
	 */
    private static int counter = 0;
    
    /**
     * This is the Enquiry ID
     */
    private int enquiryID;
    /**
     * This is the subject
     */
    private String subject;
    /**
     * This is the description
     */
    private String description;
    /**
     * This is the reply
     */
    private String reply;
    /**
     * This is the resolved boolean
     */
    private boolean resolved;
    
    /**
     * This is the author 
     */
    private Student author;

    /**
     * Enquiry Constructor
     * @param author author of the enquiry
     * @param subject subject of the enquiry
     * @param description description of the enquiry
     */
    public Enquiry(Student author, String subject, String description) {
        validateNonNull(author, "Author cannot be null");

        this.enquiryID = counter++;
        this.author = author;
        this.subject = subject;
        this.description = description;
        this.resolved = false;  // Default to unresolved
    }

    /**
     * Enquiry Constructor
     * @param EID
     * @param AID
     * @param faculty
     * @param title
     * @param newDescription
     * @param newResolve
     * @param newReply
     */
    public Enquiry(int EID, String AID, String faculty, String title, String newDescription, boolean newResolve, String newReply){
        enquiryID = EID;
        author = new Student(AID, faculty);
        subject = title;
        description = newDescription;
        resolved = newResolve;
        reply = newReply;
        counter = EID;
    }

    /**
     * This getter method will get the description of the enquiry
     * @return
     */
    public String getDescription() {
        return description;
    }
    /**
     * This getter method will get the reply of the enquiry
     * @return
     */
    public String getReply() {
        return reply;
    }
    /**
     * This getter method will get the enquiry ID
     * @return
     */
    public int getEnquiryID() {
        return enquiryID;
    }
    /**
     * This getter method will get the author of the enquiry
     * @return
     */
    public Student getAuthor() {
        return author;
    }
    /**
     * This boolean method will determine if the enquiry is resolved or not
     * @return
     */
    public boolean getResolved() {
        return resolved;
    }
    /**
     * This getter method will get the subject of the enquiry
     * @return
     */
    public String getSubject(){
        return subject;
    }
    /**
     * This method will set the subject of the enquiry
     * @param title
     */
    public void setSubject(String title){
        subject = title;
    }
    /**
     * This method will set the description of the enquiry
     * @param description
     */
    public void setDescription(String description) {
        if (!resolved) {
            this.description = description;
        } else {
            throw new IllegalStateException("Cannot change description of resolved enquiry.");
        }
    }
    /**
     * This method will set the reply to the enquiry
     * @param reply Reply to the enquiry
     */
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
    /**
     * this method will more the enquiry resolved when replied
     */
    public void markAsResolved() 
    {
        resolved = true;
    }
    /**
     * this method will ensure that the enquiry is not edited after it is resolved
     * @param newDescription
     */
    // Protected to ensure enquiry is not edited after being resolved
    protected void editDescription(String newDescription) 
    {
        if (!resolved) {
            this.description = newDescription;
        } else {
            throw new IllegalStateException("Cannot edit resolved enquiry.");
        }
    }
    /**
     * This method is used for null checking
     * @param obj
     * @param message
     */
    // Utility method for null checking
    private void validateNonNull(Object obj, String message) 
    {
        if (obj == null) 
        {
            throw new IllegalArgumentException(message);
        }
    }
    /**
     * This method will aid in checking the enquiry as solved/replied
     */
    protected void resolveEnquiry() 
    {
        this.resolved = true;
    }
    /**
     * This method will check if the enquiry has been replied or not
     * @return
     */
    public boolean hasReply() 
    {
        return reply != null && !reply.isEmpty();
    }
}

