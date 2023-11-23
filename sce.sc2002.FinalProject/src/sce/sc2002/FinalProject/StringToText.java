package sce.sc2002.FinalProject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Class to convert string to text
 */
public class StringToText {
    
	/**
	 * Constructor for StringToText
	 * @param text text
	 * @param filePath filepath 
	 */
    public static void stringToText(String text, String filePath){
        
        
        
        try (PrintWriter writer = new PrintWriter(new File(filePath))) {
            writer.write(text.toString());
            //System.out.println(filePath);
            System.out.println("done!");
        } 
        catch (FileNotFoundException exception) {
            System.out.println(exception.getMessage());
        }
    }

}
