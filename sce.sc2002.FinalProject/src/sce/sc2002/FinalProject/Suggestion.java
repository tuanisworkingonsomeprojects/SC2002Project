package sce.sc2002.FinalProject;

import java.util.*;


enum SuggestionStatus {OPEN, DISCARDED, ACCEPTED}

public class Suggestion 
{
    private static int suggestionCount = 0;
    private final int suggestionID;
    private final Committee author;
    private String description;
    private SuggestionStatus status;
    private boolean resolved;

    public Suggestion(Committee author, String description) {
        this.suggestionID = suggestionCount++;
        this.author = author;
        this.description = description;
        this.status = SuggestionStatus.OPEN;
        this.resolved = false;
    }
    
    // Getter methods
    public int getSuggestionID() {return suggestionID;}

    public Committee getAuthor() {return author;}

    public String getDescription() {return description;}

    public SuggestionStatus getStatus() {return status;}

    public boolean getResolved(){return resolved;}

    // Setter methods
    public void setDescription(String description) {
        if (nullOrEmpty(description)) {
            System.out.println("Description cannot be null or empty.");
            return;
        }

        this.description = description;
    }

    public void approve(){
        if (resolved) {
            System.out.println("You cannot change decision");
            return;
        }
        status = SuggestionStatus.ACCEPTED;
        resolved = true;
        author.addPoint();
    }

    public void discard(){
        if (resolved) {
            System.out.println("You cannot change decision");
            return;
        }

        status = SuggestionStatus.DISCARDED;
        resolved = true;
    }
    
    // Private helper methods
    private boolean nullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }
    
}
