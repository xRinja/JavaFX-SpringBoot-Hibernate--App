/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.university.university.controllers;

import com.university.university.models.Instructor;
import com.university.university.services.TableService;
import com.university.university.services.TextFormatService;

import io.micrometer.common.util.StringUtils;

import java.time.LocalDate;
import java.util.HashSet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author Azeem Afridi
 */
public class InstructorFormController extends Stage{

    private StartController sc;
    
    @FXML
    private BorderPane borderPane;
    @FXML
    private Button addButton;
    @FXML
    private Button clearButton;
    @FXML
    private GridPane gridPane;
    @FXML
    private Label firstNameLabel;
    @FXML
    private TextField firstNameTextField;
    @FXML
    private Label lastNameLabel;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private Label dobLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private TextField dobTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private Label addressLabel;
    @FXML
    private Label genderLabel;
    @FXML
    private Label instructorIdLabel;
    @FXML
    private Label departmentLabel;
    @FXML
    private TextField addressTextField;
    @FXML
    private TextField genderTextField;
    @FXML
    private TextField instructorIdTextField;
    @FXML
    private TextField departmentTextField;
    @FXML
    private Label firstNameError;
    @FXML
    private Label lastNameError;
    @FXML
    private Label dobError;
    @FXML
    private Label emailError;
    @FXML
    private Label addressError;
    @FXML
    private Label genderError;
    @FXML
    private Label instructorIdError;
    @FXML
    private Label departmentError;

    @FXML
    private void clearFields(ActionEvent event) {
            firstNameTextField.clear();
            lastNameTextField.clear();
            dobTextField.clear();
            emailTextField.clear();
            addressTextField.clear();
            genderTextField.clear();
            instructorIdTextField.clear();
            departmentTextField.clear();
    }

    private void clearErrors() {
        firstNameError.setText("");
        lastNameError.setText("");
        dobError.setText("");
        emailError.setText("");
        addressError.setText("");
        genderError.setText("");
        instructorIdError.setText("");
        departmentError.setText("");
    }
    
    public void initialize(){
        sc = StartController.getApplicationContext().getBean("startController", StartController.class);

    }

    @FXML
    private void addInstructor(ActionEvent event) {
        // Check if errors
        boolean isError = false;
        checkErrors(isError);
        
        if(!isError){
            System.out.println("Adding Instructor In Service");
            // Fields
            String firstName = firstNameTextField.getText();
            String lastName = lastNameTextField.getText();
            String[] dobString = dobTextField.getText().split("-");
            LocalDate dob = LocalDate.of(Integer.parseInt(dobString[0]), Integer.parseInt(dobString[1]), Integer.parseInt(dobString[2]));
            String email = emailTextField.getText();
            String address = addressTextField.getText();
            char gender = genderTextField.getText().charAt(0);
            String instructorId = instructorIdTextField.getText();
            String department = departmentTextField.getText();

            // Creating Instructor
            Instructor instructor = new Instructor(0, firstName, lastName, dob, email, address, gender, instructorId, department, new HashSet<>());
            sc.getInstructorService().save(instructor);

            TableService.clearTable(sc.getTableView());
            TableService.syncTables();
            sc.showTable(sc.getListViewDB().getSelectionModel().getSelectedIndex());

            // Closing Window
            Node source = (Node)event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        }
    }

    private void checkErrors(boolean isError){
        // Resetting Errors
        clearErrors();

        // First Name Check
        if(!StringUtils.isEmpty(firstNameTextField.getText())){
            if(!TextFormatService.lettersOnly(firstNameTextField.getText()).equals("")){
                isError = true;
                firstNameError.setText(TextFormatService.lettersOnly(firstNameTextField.getText()));
            } 
        } else {
            isError = true;
            firstNameError.setText(TextFormatService.isEmpty(firstNameTextField.getText()));
        }

        // Last Name Check
        if(!StringUtils.isEmpty(lastNameTextField.getText())){
            if(!TextFormatService.lettersOnly(lastNameTextField.getText()).equals("")){
                isError = true;
                lastNameError.setText(TextFormatService.lettersOnly(lastNameTextField.getText()));
            } 
        } else {
            isError = true;
            lastNameError.setText(TextFormatService.isEmpty(firstNameTextField.getText()));
        }

        // DOB Check
        if(!StringUtils.isEmpty(dobTextField.getText())){
            if(!TextFormatService.numbersOnly(dobTextField.getText()).equals("")){
                isError = true;
                dobError.setText(TextFormatService.numbersOnly(dobTextField.getText()));
            }
        } else {
            isError = true;
            dobError.setText(TextFormatService.isEmpty(firstNameTextField.getText()));
        }

        // Email Check
        if(!StringUtils.isEmpty(emailTextField.getText())){
            if(!TextFormatService.isEmail(emailTextField.getText()).equals("")){
                isError = true;
                emailError.setText(TextFormatService.isEmail(emailTextField.getText()));
            }
        } else {
            isError = true;
            emailError.setText(TextFormatService.isEmpty(emailTextField.getText()));
        }

        // Address Check
        if(!StringUtils.isEmpty(addressTextField.getText())){
       
        } else {
            isError = true;
            addressError.setText(TextFormatService.isEmpty(addressTextField.getText()));
        }

        // Gender Check
        if(!StringUtils.isEmpty(genderTextField.getText())){
            if(!genderTextField.getText().equalsIgnoreCase("M") && !genderTextField.getText().equalsIgnoreCase("F")){
                isError = true;
                genderError.setText("Please enter a valid gender. EX: M/F");
            }
        } else {
            isError = true;
            genderError.setText(TextFormatService.isEmpty(genderTextField.getText()));
        }
    }
    
}
