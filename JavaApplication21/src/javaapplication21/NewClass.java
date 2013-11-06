package javaapplication21;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.management.FileSystem;

/**
 * This is a practice program to work with Path and other file related classes.
 *
 * @author bjmaclean
 * @since 20121205
 */

public class NewClass {
 
static String printText;

    public NewClass(String printText) {
        this.printText = printText;
        
        main();
    }

    /**
     * @param args the command line arguments
     */
    public static void main() {

        //java 7
        // FileSystem fileSystem = FileSystems.getDefault();
        // Path filePath = Paths.get("C:\\Test\\Test.txt");
        //WONT BE USING THIS, BUT IT WOULD WORK SIMILARLY
        
        //java 6
       
        
        File newFile = new File("c:\\test", "Test.txt");
        System.out.println("File exists=" + newFile.exists());
        if (!newFile.exists()) {
            try {
                newFile.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("File exists=" + newFile.exists());
        }

        
        FileReader fileReader = null;

        System.out.println("**************************************************");
        System.out.println("*******FileReader*********************************");
        System.out.println("**************************************************");

        try {
            fileReader = new FileReader(newFile);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Read the file
        char[] fileCharacters = new char[1000];
        int NUMBER_TO_READ = 2;
        int currentLocation = 0;
        int DONE = -1;
        int numberRead = 0;
        while (numberRead != -1) {
            try {
                numberRead = fileReader.read(fileCharacters, currentLocation, NUMBER_TO_READ);
                if (numberRead > 0) {
                    currentLocation += numberRead;
                }
            } catch (IOException ex) {
                Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            fileReader.close();
        } catch (IOException ex) {
            Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        String fileString = String.valueOf(fileCharacters);
        System.out.println(fileString);



        //********************************************************************************************
        // BufferedReader
        //********************************************************************************************

        System.out.println("**************************************************");
        System.out.println("*******BufferedReader *********************************");
        System.out.println("**************************************************");

        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(newFile)));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            String name = "x";
            while (name != null) {
                name = br.readLine();
                System.out.println("number 2 example read:" + name);
            }
        } catch (IOException ex) {
            Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
        }

        

        
        
        System.out.println("**************************************************");
        System.out.println("*******BufferedWriter ****************************");
        System.out.println("**************************************************");
        
        
        File newOutputFile = new File("c:\\test", "TestOutput.txt");
        System.out.println("File exists inially=" + newFile.exists());
        if (!newOutputFile.exists()) {
            try {
                newOutputFile.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("File exists=" + newOutputFile.exists());
        }        
        BufferedWriter bufferedWriter = null;
        try {
            //********************************************************************************************
            // BufferedWriter
            //********************************************************************************************
        
            bufferedWriter = new BufferedWriter(new FileWriter(newOutputFile, true)); //Note true used to append - different oveloaded constructor.
            String fileOutputString = "Total: "+printText.toString();
            bufferedWriter.write(fileOutputString);
            bufferedWriter.newLine();
        } catch (IOException ex) {
            Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                bufferedWriter.close();
            } catch (IOException ex) {
                Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
    }//end main

    

  
 

   
}
