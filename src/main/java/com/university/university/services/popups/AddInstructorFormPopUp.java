package com.university.university.services.popups;

import java.time.LocalDate;
import java.util.List;

import com.university.university.controllers.StartController;
import com.university.university.models.Instructor;
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

public class AddInstructorFormPopUp extends Stage{

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
    private Label instructorIdLabel = new Label("INSTRUCTOR ID");
    private Label departmentLabel = new Label("DEPARTMENT");

    private TextField firstNameField = new TextField();
    private TextField lastNameField = new TextField();
    private TextField dobField = new TextField();
    private TextField emailField = new TextField();
    private TextField addressField = new TextField();
    private TextField genderField = new TextField();
    private TextField instructorIdField = new TextField();
    private TextField departmentField = new TextField();

    // Buttons
    private Button addButton = new Button("Add");
    private Button clearButton = new Button("Clear");

    {
        sc = StartController.getApplicationContext().getBean("startController", StartController.class);

        // Stage Config
        this.setScene(scene);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setTitle("Instructor Form");
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
        gridPane.add(instructorIdLabel, 0, 10);
        gridPane.add(instructorIdField, 0, 11);
        gridPane.add(departmentLabel, 1, 10);
        gridPane.add(departmentField, 1, 11);
        
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
            String instructorId = instructorIdField.getText();
            String department = departmentField.getText();

            // Creating Instructor
            Instructor instructor = new Instructor(0, firstName, lastName, dob, email, address, gender, instructorId, department, new HashSet<>());
            sc.getInstructorService().save(instructor);

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
            instructorIdField.clear();
            departmentField.clear();
        });
    }
}
