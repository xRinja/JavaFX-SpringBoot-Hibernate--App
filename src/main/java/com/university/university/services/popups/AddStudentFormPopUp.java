package com.university.university.services.popups;

import java.time.LocalDate;
import java.util.List;

import com.university.university.controllers.StartController;
import com.university.university.models.Course;
import com.university.university.models.Student;
import com.university.university.services.TableService;
import java.util.HashSet;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddStudentFormPopUp extends Stage{

    private StartController sc;

    // Fx Components
    private BorderPane pane = new BorderPane();
    private GridPane gridPane = new GridPane();
    private HBox hBox = new HBox(15);
    private Scene scene = new Scene(pane, 700, 280);

    // Form Information
    private Label firstNameLabel = new Label("FIRST NAME");
    private Label lastNameLabel = new Label("LAST NAME");
    private Label dobLabel = new Label("DOB yyyy-mm-dd");
    private Label emailLabel = new Label("EMAIL");
    private Label addressLabel = new Label("ADDRESS");
    private Label genderLabel = new Label("GENDER");
    private Label studentIdLabel = new Label("STUDENT ID");
    private Label degreeLabel = new Label("DEGREE");

    private TextField firstNameField = new TextField();
    private TextField lastNameField = new TextField();
    private TextField dobField = new TextField();
    private TextField emailField = new TextField();
    private TextField addressField = new TextField();
    private TextField genderField = new TextField();
    private TextField studentIdField = new TextField();
    private TextField degreeField = new TextField();

    // Buttons
    private Button addButton = new Button("Add");
    private Button clearButton = new Button("Clear");

    {
        sc = StartController.getApplicationContext().getBean("startController", StartController.class);

        // Stage Config
        this.setScene(scene);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setTitle("Student Form");
        pane.setPadding(new Insets(5, 5, 5, 5));

        // Grid config
        gridPane.setPadding(new Insets(10, 10, 5, 10));
        gridPane.setVgap(5);
        gridPane.setHgap(50);
        gridPane.setAlignment(Pos.TOP_CENTER);

        // Grid placement
        gridPane.add(firstNameLabel, 0, 0);
        gridPane.add(firstNameField, 0, 1);
        gridPane.add(lastNameLabel, 1, 0);
        gridPane.add(lastNameField, 1, 1);
        gridPane.add(dobLabel, 2, 0);
        gridPane.add(dobField, 2, 1);
        gridPane.add(emailLabel, 0, 5);
        gridPane.add(emailField, 0, 6);
        gridPane.add(addressLabel, 1, 5);
        gridPane.add(addressField, 1, 6);
        gridPane.add(genderLabel, 2, 5);
        gridPane.add(genderField, 2, 6);
        gridPane.add(studentIdLabel, 0, 10);
        gridPane.add(studentIdField, 0, 11);
        gridPane.add(degreeLabel, 1, 10);
        gridPane.add(degreeField, 1, 11);
        
        // BorderPane placement
        BorderPane.setAlignment(gridPane, Pos.TOP_CENTER);
        BorderPane.setMargin(gridPane, new Insets(10,10,10,10));
        pane.setCenter(gridPane);

        hBox.getChildren().addAll(List.of(addButton, clearButton));
        hBox.setAlignment(Pos.TOP_CENTER);
        BorderPane.setAlignment(hBox, Pos.TOP_CENTER);
        BorderPane.setMargin(hBox, new Insets(5,10,10,10));
        pane.setBottom(hBox);

        // Button Config
        buttonHandler(addButton, clearButton);

        // Show stage
        this.showAndWait();

    }

    private void buttonHandler(Button addButton, Button clearButton){
        addButton.setOnAction(event -> {
            // Fields
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String[] dobString = dobField.getText().split("-");
            LocalDate dob = LocalDate.of(Integer.parseInt(dobString[0]), Integer.parseInt(dobString[1]), Integer.parseInt(dobString[2]));
            String email = emailField.getText();
            String address = addressField.getText();
            char gender = genderField.getText().charAt(0);
            String studentId = studentIdField.getText();
            String degree = degreeField.getText();

            // Adding Student
            Student student = new Student(0, firstName, lastName, dob, email, address, gender, studentId, degree, new HashSet<Course>());
            sc.getStudentService().save(student);
            System.out.println("Right before adding item");
            TableService.clearTable(sc.getTableView());
            TableService.syncTables();
            sc.showTable(sc.getListViewDB().getSelectionModel().getSelectedIndex());

            this.close();
        });

        clearButton.setOnAction(event -> {
            firstNameField.clear();
            lastNameField.clear();
            dobField.clear();
            emailField.clear();
            addressField.clear();
            genderField.clear();
            studentIdField.clear();
            degreeField.clear();
        });
    }
}
