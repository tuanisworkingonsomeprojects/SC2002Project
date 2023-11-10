package sce.sc2002.FinalProject;

public class Student{

    //Not sure if needed
    private String name;

    private String id;

    private String faculty;

    private String password;
    
    public Student(String name, String id, String faculty,String password,int committee){
        super();
    }

    public void fillDetails(String name, String id, String faculty, String password){
        this.name=name;
        this.id=id;
        this.faculty=faculty;
        this.password=password;
    }
}
