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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class JavaReadCSVDemo extends Application {

    private TableView table = new TableView();
    private ObservableList<Person> data = FXCollections.observableArrayList();
    private Person headerData = new Person("_Name", "_Favourite Colour", "_Second Favourite Colour");

    public void loadTable(String fileName) {

        loadCSVToDataAndHeader(fileName);

        table.setEditable(true);

        table.getColumns().clear();
        TableColumn column1 = new TableColumn(headerData.getName());
        TableColumn column2 = new TableColumn(headerData.getFavouriteColour());
        TableColumn column3 = new TableColumn(headerData.getSecondFavouriteColour());

        table.getColumns().addAll(column1, column2, column3);
        table.setPrefWidth(400);

        column1.setCellValueFactory(new PropertyValueFactory<Person, String>("name"));
        column2.setCellValueFactory(new PropertyValueFactory<Person, String>("favouriteColour"));
        column3.setCellValueFactory(new PropertyValueFactory<Person, String>("secondFavouriteColour"));

        table.setItems(data);
    }

    String initFileName = "favourite_colours.csv";
    String workingFileName = "test.csv";

    @Override
    public void start(Stage stage) {
        //Group root = new Group();
        //Scene scene = new Scene(root);

        //GridPane tablePane = loadCSVToGridPane();
        VBox vbox = new VBox();
        vbox.setSpacing(8);
        vbox.setPadding(new Insets(5, 0, 0, 5));
        Group root = new Group(vbox);
        Scene scene = new Scene(root, 640, 512);

        Label titleLabel = new Label("Table");
        titleLabel.setFont(new Font("Arial", 18));

        vbox.getChildren().add(titleLabel);

        // Load from file and save to working copy.
        loadTable(initFileName);
        saveCSV(workingFileName);
        vbox.getChildren().add(table);

        Button addButton = new Button("Add Bob");
        addButton.setOnAction((ActionEvent event) -> {
            data.add(new Person("Bob", "Red", "White"));
        });
        vbox.getChildren().add(addButton);

        Button loadButton = new Button("Load");
        loadButton.setOnAction((ActionEvent event) -> {
            loadTable(workingFileName);
        });
        vbox.getChildren().add(loadButton);

        Button saveButton = new Button("Save");
        saveButton.setOnAction((ActionEvent event) -> {
            saveCSV(workingFileName);
        });
        vbox.getChildren().add(saveButton);

        stage.setTitle("CSV Contents");
        stage.setScene(scene);
        stage.show();
    }

    public void saveCSV(String fileName) {
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            CSVWriter csvWriter = new CSVWriter(fileWriter);
            String[] topLine = {headerData.getName(), headerData.getFavouriteColour(), headerData.getSecondFavouriteColour()};
            csvWriter.writeNext(topLine, true);
            for (Person person : data) {
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

    public void loadCSVToDataAndHeader(String fileName) {

        data.clear();
        CSVReader csvReader = null;
        try {
            csvReader = new CSVReader(new FileReader(fileName));
            String[] csvRow;

            csvRow = csvReader.readNext(); // header row
            if (csvRow.length == 3) {
                headerData = new Person(csvRow[0], csvRow[1], csvRow[2]);
            } else {
                // use default header row if header is invalid
            }
            while ((csvRow = csvReader.readNext()) != null) {

                if (csvRow.length == 3) {
                    data.add(new Person(csvRow[0], csvRow[1], csvRow[2]));
                } else {
                    data.add(new Person("invalid entry", "", ""));
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
