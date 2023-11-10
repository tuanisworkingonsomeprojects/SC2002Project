package sce.sc2002.FinalProject;

public abstract class User {
    private String userID; // User's ID
    private String password; // User's password
    private String faculty; // User's faculty information

    public User(String userID, String password, String faculty) {
        this.userID = userID;
        this.password = password;
        this.faculty = faculty;
    }

    public String getUserID() {
        return userID;
    }

    public String getPassword() {
        return password;
    }

    public String getFaculty() {
        return faculty;
    }
}
