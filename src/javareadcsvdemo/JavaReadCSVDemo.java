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

import com.opencsv.CSVWriter;
import java.io.FileWriter;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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

    String fileName = "favourite_colours.csv";

    String saveFileName = "test.csv";

    public void saveCSV() {
        try {
            FileWriter fileWriter = new FileWriter(saveFileName);
            CSVWriter csvWriter = new CSVWriter(fileWriter);
            String[] topLine = {"Hi", "There"} ;
            csvWriter.writeNext(topLine, true);
            csvWriter.writeNext(new String[] {"1","2"}, true);
            csvWriter.writeNext(new String[] {"3","Four 4"},true);
            csvWriter.close();
            fileWriter.close();
            System.out.println("good: javareadcsvdemo.JavaReadCSVDemo.saveCSV()");
        } catch (Exception e) {
            System.out.println("error: javareadcsvdemo.JavaReadCSVDemo.saveCSV()");
        }
    }

    public GridPane loadCSVToGridPane() {

        GridPane gridPane = new GridPane();

        CSVReader csvReader = null;
        try {
            csvReader = new CSVReader(new FileReader(fileName));
            String[] csvRow;

            int row = 0;
            while ((csvRow = csvReader.readNext()) != null) {
                int column = 0;
                for (String cell : csvRow) {
                    // Try putting text instead of Label.
                    gridPane.add(new TextField(cell), column, row);
                    column++;
                }
                row++;
            }

            int csvRows = row;

            gridPane.add(new Label(" "), 0, csvRows);

            Button saveButton = new Button("Save");
            Button loadButton = new Button("Load");
            gridPane.add(saveButton, 0, csvRows + 1);
            gridPane.add(loadButton, 1, csvRows + 1);

            Label fileNameLabel = new Label("File name: ");

//            saveButton.setOnAction(new EventHandler<ActionEvent>() {
//                @Override
//                public void handle(ActionEvent event) {
//                    fileNameLabel.setText("Saving...");
//                }
//            });
            saveButton.setOnAction((ActionEvent event) -> {
                fileNameLabel.setText("Saving...");
                saveCSV();
                fileNameLabel.setText("Saving... progressed");
            });

            ///fileNameLabel.
            gridPane.add(fileNameLabel, 2, csvRows + 1);
            gridPane.add(new TextField("filename"), 3, csvRows + 1);

        } catch (IOException e) {
            System.out.println("Couldn't read the table: " + e);
        }

        return gridPane;
    }

    public static void main(String[] args) {
        launch(args);
    }

}
