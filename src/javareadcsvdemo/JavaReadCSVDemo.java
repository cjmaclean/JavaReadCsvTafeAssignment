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
import java.util.ArrayList;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class JavaReadCSVDemo extends Application {

    private final TableView table = new TableView();

    // Loads header and data, then sets up the TableView to display it.
    public void loadTable(String fileName, ArrayList<String> headerList, ObservableList<Person> dataList) {

        loadCSVToHeadersAndData(fileName, headerList, dataList);

        table.setEditable(true);

        table.getColumns().clear();
        TableColumn columnName = new TableColumn(headerList.get(0));
        TableColumn columnFavouriteColour = new TableColumn(headerList.get(1));
        TableColumn columnSecondFavouriteColour = new TableColumn(headerList.get(2));

        table.getColumns().addAll(columnName, columnFavouriteColour, columnSecondFavouriteColour);
        table.setPrefWidth(320);

        columnName.setCellValueFactory(new PropertyValueFactory<Person, String>("name"));
        columnFavouriteColour.setCellValueFactory(new PropertyValueFactory<Person, String>("favouriteColour"));
        columnSecondFavouriteColour.setCellValueFactory(new PropertyValueFactory<Person, String>("secondFavouriteColour"));

        table.setItems(dataList);
    }

    String initFileName = "favourite_colours.csv";
    String workingFileName = "test.csv";

    @Override
    public void start(Stage stage) {

        // Objects for storing headers and table data
        ObservableList<Person> data = FXCollections.observableArrayList();
        ArrayList<String> headers = new ArrayList<String>();

        // Main window structure
        VBox vbox = new VBox();
        vbox.setSpacing(8);
        vbox.setPadding(new Insets(5, 0, 0, 5));
        Group root = new Group(vbox);
        Scene scene = new Scene(root, 400, 600);

        // Add a heading
        Label titleLabel = new Label("Table");
        titleLabel.setFont(new Font("Arial", 18));
        vbox.getChildren().add(titleLabel);
        
        // Load from file and save to working copy. Add it for display
        loadTable(initFileName, headers, data);
        saveCSV(workingFileName, headers, data);
        vbox.getChildren().add(table);

        // Add a button to add an example row.
        Button addButton = new Button("Add Bob");
        addButton.setOnAction((ActionEvent event) -> {
            data.add(new Person("Bob", "Red", "White"));
        });
        vbox.getChildren().add(addButton);

        // Add a load button.
        Button loadButton = new Button("Load");
        loadButton.setOnAction((ActionEvent event) -> {
            loadTable(workingFileName, headers, data);
        });
        vbox.getChildren().add(loadButton);

        // Add a save button.
        Button saveButton = new Button("Save");
        saveButton.setOnAction((ActionEvent event) -> {
            saveCSV(workingFileName, headers, data);
        });
        vbox.getChildren().add(saveButton);

        // Complete the window setup.
        stage.setTitle("CSV Contents");
        stage.setScene(scene);
        stage.show();
    }

    // Saves header row and a table of Person data, from the given ArrayList and ObservableList
    // into a CSV file.
    public void saveCSV(String fileName, ArrayList<String> headerList, ObservableList<Person> dataList) {
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            CSVWriter csvWriter = new CSVWriter(fileWriter);
            String[] topLine = {headerList.get(0), headerList.get(1), headerList.get(2)};
            csvWriter.writeNext(topLine, true);
            for (Person person : dataList) {
                String[] rowStrings = new String[]{person.getName(), person.getFavouriteColour(), person.getSecondFavouriteColour()};
                csvWriter.writeNext(rowStrings, true);
            }
            csvWriter.close();
            fileWriter.close();
            System.out.println("good: javareadcsvdemo.JavaReadCSVDemo.saveCSV()");
        } catch (Exception e) {
            System.out.println("error: javareadcsvdemo.JavaReadCSVDemo.saveCSV()");
        }
    }

    // Loads header row and a table of Person data, into the given ArrayList and ObservableList
    public void loadCSVToHeadersAndData(String fileName, ArrayList<String> headerList, ObservableList<Person> dataList) {
        dataList.clear();
        CSVReader csvReader = null;
        try {
            csvReader = new CSVReader(new FileReader(fileName));
            String[] csvRow;

            csvRow = csvReader.readNext(); // header row
            if (csvRow.length == 3) {

                headerList.clear();
                headerList.add(csvRow[0]);
                headerList.add(csvRow[1]);
                headerList.add(csvRow[2]);
            } else {
                // use default header row if header is invalid
                headerList.clear();
                headerList.add("_Name");
                headerList.add("_Favourite Colour");
                headerList.add("_Second Favourite Colour");
            }
            while ((csvRow = csvReader.readNext()) != null) {
                if (csvRow.length == 3) {
                    dataList.add(new Person(csvRow[0], csvRow[1], csvRow[2]));
                } else {
                    dataList.add(new Person("invalid entry", "", ""));
                }
            }
        } catch (IOException e) {
            System.out.println("Couldn't read the table: " + e);
        }

    }

    public static void main(String[] args) {
        launch(args);
    }

}
