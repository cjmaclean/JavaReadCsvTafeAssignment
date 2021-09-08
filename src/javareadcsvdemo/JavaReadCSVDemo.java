package javareadcsvdemo;

/**
 *
 * @author 30039802 Caspian Maclean
 *
 * Question 6 – JMC receives output from many different programs in CSV format
 * you must write a program to display this data. You need to create a program
 * that uses an external 3rd party library to read and write data to a CSV (as
 * an example https://www.javatpoint.com/how-to-read-csv-file-in-java ) this
 * data must be displayed in a GUI (a table is fine)
 *
 */

// Open CSV - this program uses opecsv-3.8.jar from:
// https://repo1.maven.org/maven2/com/opencsv/opencsv/3.8/opencsv-3.8.jar
// Open CSV Licence: Apache
// See: https://search.maven.org/artifact/com.opencsv/opencsv/3.8/jar

import java.io.FileReader;
import com.opencsv.CSVReader;
import java.io.IOException;

public class JavaReadCSVDemo {

    public static void main(String[] args) {
        // To do:
        // After read from CSV file.
        // Then display in GUI.
        
        // when changing to GUI, check for System.out methods,
        // whether to leave them in or remove or replace them.

        /*
        int width = 2;
        int height = 3;

        //not sure why this doesn't work:
        //String[][] table = new String[height][width];
        //table[0] = {"", ""};
        String[][] table = {
            {"Name", "Favourite Colour"},
            {"John", "Green"},
            {"Penny", "Blue"}
        };
        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                System.out.print(" | ");
                System.out.print(table[row][column]);
            }
            System.out.println(" | ");
        }
        */
        
        String myCurrentDir = System.getProperty("user.dir") + java.io.File.separator + " ... " + System.getProperty("sun.java.command");
        System.out.println("directory " + myCurrentDir);
        
        CSVReader csvReader = null;
        try {
            csvReader = new CSVReader(new FileReader("favourite_colours.csv"));
            String[] csvRow;
            while ((csvRow = csvReader.readNext()) != null) {
                for (String cell : csvRow) {
                    System.out.print(" | ");
                    System.out.print(cell);
                }
                System.out.println(" | ");
            }
        } catch (IOException e) {
            System.out.println("Couldn't read the table: " + e);
        }
    }

}
