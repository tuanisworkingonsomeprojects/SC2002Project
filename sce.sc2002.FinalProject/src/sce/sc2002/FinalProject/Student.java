package sce.sc2002.FinalProject;

/**
 * Student Class
 */
public class Student{
	/**
	 * String id of student
	 */
    //Not sure if needed
    private String id;
    /**
     * String faculty of student
     */
    private String faculty;
    /**
     * constructor of student
     * @param id student id
     * @param faculty student faculty
     */
    public Student(String id, String faculty){
        this.id      = id;
        this.faculty = faculty;
    }
    /**
     * This getter method will get the student id
     * @return
     */
    public String getID()       {return id;}
    /**
     * This getter method will get the students faculty
     * @return
     */
    public String getFaculty()  {return faculty;}
}
