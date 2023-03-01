/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.university.university.controllers;

import com.university.university.models.Course;
import com.university.university.models.Student;
import com.university.university.services.TableService;
import com.university.university.services.TextFormatService;

import io.micrometer.common.util.StringUtils;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Azeem Afridi
 */
public class StudentFormController extends Stage{
    
    private StartController sc;

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
    private Label studentIdLabel;
    @FXML
    private Label degreeLabel;
    @FXML
    private TextField addressTextField;
    @FXML
    private TextField genderTextField;
    @FXML
    private TextField studentIdTextField;
    @FXML
    private TextField degreeTextField;
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
    private Label studentIdError;
    @FXML
    private Label degreeError;
    @FXML
    private BorderPane borderPane;

    @FXML
    private void addStudent(ActionEvent event) {
        // Check if errors
        boolean isError = false;
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

        if(!isError){
            // Fields 
            String firstName = firstNameTextField.getText();
            String lastName = lastNameTextField.getText();
            String dobString[] = dobTextField.getText().split("-");
            LocalDate dob = LocalDate.of(Integer.parseInt(dobString[0]), Integer.parseInt(dobString[1]), Integer.parseInt(dobString[2]));
            String email = emailTextField.getText();
            String address = addressTextField.getText();
            char gender = genderTextField.getText().charAt(0);
            String studentId = studentIdTextField.getText();
            String degree = degreeTextField.getText();

            // Adding Student
            Student student = new Student(0, firstName, lastName, dob, email, address, gender, studentId, degree, new HashSet<Course>());
            sc.getStudentService().save(student);
            System.out.println("Right before adding item");
            TableService.clearTable(sc.getTableView());
            TableService.syncTables();
            sc.showTable(sc.getListViewDB().getSelectionModel().getSelectedIndex());

            // Closing Window
            Node source = (Node)event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        }
    }

    private void clearErrors() {
        firstNameError.setText("");
        lastNameError.setText("");
        dobError.setText("");
        emailError.setText("");
        addressError.setText("");
        genderError.setText("");
        studentIdError.setText("");
        degreeError.setText("");
    }

    @FXML
    private void clearFields(ActionEvent event) {
        firstNameTextField.clear();
        lastNameTextField.clear();
        dobTextField.clear();
        emailTextField.clear();
        addressTextField.clear();
        genderTextField.clear();
        studentIdTextField.clear();
        degreeTextField.clear();
    }
    
    public void initialize() {
        sc = StartController.getApplicationContext().getBean("startController", StartController.class);
    }
    
}
