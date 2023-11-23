package sce.sc2002.FinalProject;

/**
 * Committee Class
 */
public class Committee extends Student{

	/**
	 * Integer for point system
	 */
    private int point;
    
    /**
     * Constructor for Committee that sets point to 0
     * @param id Committee ID
     * @param faculty Committee faculty
     */
    public Committee(String id, String faculty){
        super(id, faculty);
        point = 0;
    }
    /**
     * Constructor for committee that sets their points 
     * @param id Committee ID
     * @param faculty Committee Faculty
     * @param point Committee points
     */
    public Committee(String id, String faculty, int point){
        super(id, faculty);
        this.point = point;
    }

    /**
     * This getter method will return the points
     * @return
     */
    public int getPoint(){return point;}

    /**
     * This method will add points
     */
    public void addPoint(){point++;}
}
