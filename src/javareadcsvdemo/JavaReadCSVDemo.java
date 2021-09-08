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
public class JavaReadCSVDemo {

    public static void main(String[] args) {
        // To do: print example data from array.
        // Then read from CSV file.
        // Then display in GUI.

        String[][] salutation = {{"Mr. ", "Mrs. ", "Ms. "}, {"Kumar"}};
        //Read more: https://www.java67.com/2014/10/how-to-create-and-initialize-two-dimensional-array-java-example.html#ixzz75ptfMUL8        

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
    }

}
