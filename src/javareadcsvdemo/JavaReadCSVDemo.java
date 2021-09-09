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

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class JavaReadCSVDemo extends Application {

    public void start(Stage stage) {
        //Group root = new Group();
        //Scene scene = new Scene(root);

        GridPane tablePane = loadCSVToGridPane();
        Scene scene = new Scene(tablePane, 600,450);

        stage.setTitle("CSV Contents");
        stage.setScene(scene);
        stage.show();
    }

    public GridPane loadCSVToGridPane() {

        GridPane gridPane = new GridPane();
        
        CSVReader csvReader = null;
        try {
            csvReader = new CSVReader(new FileReader("favourite_colours.csv"));
            String[] csvRow;
            
            int row = 0;
            while ((csvRow = csvReader.readNext()) != null) {
                int column = 0;
                for (String cell : csvRow) {
                    gridPane.add(new Label(cell), column, row);
                    column++;
                }
                row++;
            }
        } catch (IOException e) {
            System.out.println("Couldn't read the table: " + e);
        }

        return gridPane;
    }

    public static void main(String[] args) {
        launch(args);
    }

}
