package sce.sc2002.FinalProject;

public class Committee extends Student{

    private int point;
    
    public Committee(String id, String faculty){
        super(id, faculty);
        point = 0;
    }

    public int getPoint(){return point;}

    public void addPoint(){point++;}
}
