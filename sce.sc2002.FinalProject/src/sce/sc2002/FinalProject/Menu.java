package sce.sc2002.FinalProject;

/**
 * Abstract Menu class
 */

public abstract class Menu {
	/**
	 * Boolean value of true or false to check if the user wants to exit the menu
	 */
    protected boolean exit = false;
    /**
     * Check the the user that is logged in
     */
    protected Login currentUser;
    /**
     * Camplist
     */
    protected static CampList campList;
    /**
     * Menu of the camplist
     * @param campList
     */
    public Menu(CampList campList){
        Menu.campList = campList;
    }
    /**
     * This method will run the menu for display
     */
    abstract public void runMenu();
    /**
     * This method will display the menu
     */
    abstract public void display();
    /**
     * This method will clear the screen on the output to make it look more neat
     */
    public void clearScreen(){
        for (int i = 0; i < 100; i ++)
            System.out.println();
    }
}


