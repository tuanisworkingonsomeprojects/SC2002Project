package sce.sc2002.FinalProject;
import java.util.ArrayList;

public class Camp {
    private String campName;
    private String dates;
    private String registrationClosingDate;
    private String userGroup;
    private String location;
    private int totalSlots;
    private int campCommitteeSlots;
    private String description;
    private Staff staffInCharge;
    private ArrayList<Student> attendees;
    private ArrayList<Student> campCommitteeMembers;
    private ArrayList<Enquiry> enquiries;

    public Camp(String campName, String dates, String registrationClosingDate, String userGroup, String location,
                int totalSlots, int campCommitteeSlots, String description, Staff staffInCharge) {
        this.campName = campName;
        this.dates = dates;
        this.registrationClosingDate = registrationClosingDate;
        this.userGroup = userGroup;
        this.location = location;
        this.totalSlots = totalSlots;
        this.campCommitteeSlots = campCommitteeSlots;
        this.description = description;
        this.staffInCharge = staffInCharge;
        this.attendees = new ArrayList<>();
        this.campCommitteeMembers = new ArrayList<>();
        this.enquiries = new ArrayList<>();
    }

    // Getter and setter methods for all attributes

    public void addAttendee(Student student) {
        if (attendees.size() < totalSlots) {
            attendees.add(student);
        } else {
            System.out.println("Camp is full. Cannot add more attendees.");
        }
    }

    public void removeAttendee(Student student) {
        attendees.remove(student);
    }

    public void addCampCommitteeMember(Student student) {
        if (campCommitteeMembers.size() < campCommitteeSlots) {
            campCommitteeMembers.add(student);
        } else {
            System.out.println("Camp committee is full. Cannot add more members.");
        }
    }

    public void removeCampCommitteeMember(Student student) {
        campCommitteeMembers.remove(student);
    }

    public void addEnquiry(Enquiry enquiry) {
        enquiries.add(enquiry);
    }

    public ArrayList<Enquiry> getEnquiries() {
        return enquiries;
    }
}

