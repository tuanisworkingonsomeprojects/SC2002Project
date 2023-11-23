package sce.sc2002.FinalProject;

import java.util.*;

/**
 * Enum suggestionsStatus
 */
enum SuggestionStatus {OPEN, DISCARDED, ACCEPTED}

/**
 * Suggestion class
 */
public class Suggestion 
{
	/**
	 * Int suggestion Count set to 0
	 */
    private static int suggestionCount = 0;
    /**
     * int suggestion ID
     */
    private int suggestionID;
    /**
     * author of suggestion (Committee Only)
     */
    private Committee author;
    /**
     * Description of suggestion
     */
    private String description;
    /**
     * Suggestion status if its solved or not
     */
    private SuggestionStatus status;
    /**
     * boolean resolved 
     */
    private boolean resolved;
    /**
     * Constructor for suggestion
     * @param author author of suggestion
     * @param description description of suggestion
     */
    public Suggestion(Committee author, String description) {
        this.suggestionID = suggestionCount++;
        this.author = author;
        this.description = description;
        this.status = SuggestionStatus.OPEN;
        this.resolved = false;
    }
    /**
     * Constructor for suggestion
     * @param newID new id
     * @param newAuthor new author
     * @param newDescription new description
     * @param newStatus new status
     * @param newResolve new resolve
     */
    public Suggestion(int newID, Committee newAuthor, String newDescription, SuggestionStatus newStatus, boolean newResolve){
        suggestionID = newID;
        author = newAuthor;
        description = newDescription;
        status = newStatus;
        resolved = newResolve;
        suggestionCount = newID;
    }
    
    /**
     * This getter method will get the suggestion ID
     * @return
     */
    // Getter methods
    public int getSuggestionID() {return suggestionID;}
    /**
     * This getter method will get the author of the suggestion
     * @return
     */
    public Committee getAuthor() {return author;}
    /**
     * This getter method will get the description of the suggestion
     * @return
     */
    public String getDescription() {return description;}
    /**
     * This method will return the suggestion status
     * @return
     */
    public SuggestionStatus getStatus() {return status;}
    /**
     * This method will return the resolve status
     * @return
     */
    public boolean getResolved(){return resolved;}
    /**
     * This setter method will set the description of the  suggestion
     * @param description description of suggestion
     */
    // Setter methods
    public void setDescription(String description) {
        if (nullOrEmpty(description)) {
            System.out.println("Description cannot be null or empty.");
            return;
        }

        this.description = description;
    }
    /**
     * This method will set the approval of a suggestion
     */
    public void approve(){
        if (resolved) {
            System.out.println("You cannot change decision");
            return;
        }
        status = SuggestionStatus.ACCEPTED;
        resolved = true;
        author.addPoint();
    }
    /**
     * This method will discard the suggestion
     */
    public void discard(){
        if (resolved) {
            System.out.println("You cannot change decision");
            return;
        }

        status = SuggestionStatus.DISCARDED;
        resolved = true;
    }
    /**
     * boolean helper method
     * @param str
     * @return
     */
    // Private helper methods
    private boolean nullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }
    
}
