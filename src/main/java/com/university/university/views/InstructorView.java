package com.university.university.views;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.university.university.models.Instructor;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.util.StringConverter;
import lombok.Getter;
import lombok.Setter;

public class InstructorView<T> {
    @Getter @Setter
    TableView<Instructor> table = new TableView<>();
    @Getter @Setter
    TableView<Instructor> serverTable = new TableView<>();

    TableColumn<Instructor, Integer> id = new TableColumn<>("id");
    TableColumn<Instructor, String> firstName = new TableColumn<>("first_name");
    TableColumn<Instructor, String> lastName = new TableColumn<>("last_name");
    TableColumn<Instructor, LocalDate> dob = new TableColumn<>("dob");
    TableColumn<Instructor, String> email = new TableColumn<>("email");
    TableColumn<Instructor, String> address = new TableColumn<>("address");
    TableColumn<Instructor, Character> gender = new TableColumn<>("gender");
    TableColumn<Instructor, String> instructorId = new TableColumn<>("instructor_id");
    TableColumn<Instructor, String> department = new TableColumn<>("department");

    @Getter @Setter
    List<Instructor> queueOfInstructors = new ArrayList<>();

    public InstructorView(){
        
        /* Mapping Columns with Model Fields 
        Cell Value Config
        */
        
        configId(); 
        configFirstName();
        configLastName();
        
        dob.setCellValueFactory(new PropertyValueFactory<Instructor, LocalDate>("d_dob"));
        email.setCellValueFactory(new PropertyValueFactory<Instructor, String>("e_email"));
        address.setCellValueFactory(new PropertyValueFactory<Instructor, String>("f_address"));
        gender.setCellValueFactory(new PropertyValueFactory<Instructor, Character>("g_gender"));
        instructorId.setCellValueFactory(new PropertyValueFactory<Instructor, String>("a_iId"));
        department.setCellValueFactory(new PropertyValueFactory<Instructor, String>("department"));

        table.getColumns().addAll(List.of(id, firstName, lastName, dob, email, address, gender, instructorId, department));
        serverTable.getColumns().addAll(List.of(id, firstName, lastName, dob, email, address, gender, instructorId, department));
    }
    
    private void configId(){
        id.setCellValueFactory(new PropertyValueFactory<Instructor, Integer>("a_id"));
    }
    
    private void configFirstName(){
        firstName.setCellValueFactory(new PropertyValueFactory<Instructor, String>("b_firstName"));
        firstName.setCellFactory(e -> new TextFieldTableCell<>(){
            // Converter
            @Override
            public void updateItem(String item, boolean empty) {
                this.setConverter(new StringConverter<String>() {
                    @Override
                    public String toString(String object) {
                        
                        return object;
                    }
                    @Override
                    public String fromString(String string) {
                        
                        return string;
                    }
                });
                
                // Edit Start
                e.setOnEditStart(e -> {
                    //
                });
                // Edit Cancel
                e.setOnEditCancel(f -> {
                    //
                });
                // Edit Commit
                e.setOnEditCommit(e -> {
                Instructor s = e.getRowValue();
                s.setB_firstName(e.getNewValue());
                table.getItems().set(e.getTablePosition().getRow(), s);

                if(s.getB_firstName().equals(serverTable.getItems().get(e.getTablePosition().getRow()).getB_firstName())){
                    this.setStyle("-fx-background-color: null");
                    if(queueOfInstructors.contains(s)){
                        queueOfInstructors.remove(s);
                    }
                } else {
                    this.setBackground(new Background(new BackgroundFill(Color.WHEAT, null, getInsets())));
                    queueOfInstructors.add(s);
                    System.out.println("Queue of instructor is: " + queueOfInstructors.toString());
                }
                });

                super.updateItem(item, empty);
            }
        });
    }
    
    private void configLastName(){
        lastName.setCellValueFactory(new PropertyValueFactory<Instructor, String>("c_lastName"));
        lastName.setCellFactory(e -> new TextFieldTableCell<>(){
            // Converter
            @Override
            public void updateItem(String item, boolean empty) {
                this.setConverter(new StringConverter<String>() {
                    @Override
                    public String toString(String object) {
                        
                        return object;
                    }
                    @Override
                    public String fromString(String string) {
                        
                        return string;
                    }
                });
                
                // Edit Start
                e.setOnEditStart(e -> {
                    //
                });
                // Edit Cancel
                e.setOnEditCancel(f -> {
                    //
                });
                // Edit Commit
                e.setOnEditCommit(e -> {
                Instructor s = e.getRowValue();
                s.setC_lastName(e.getNewValue());
                table.getItems().set(e.getTablePosition().getRow(), s);

                if(s.getC_lastName().equals(serverTable.getItems().get(e.getTablePosition().getRow()).getC_lastName())){
                    this.setStyle("-fx-background-color: null");
                    if(queueOfInstructors.contains(s)){
                        queueOfInstructors.remove(s);
                    }
                } else {
                    this.setBackground(new Background(new BackgroundFill(Color.WHEAT, null, getInsets())));
                    queueOfInstructors.add(s);
                    System.out.println("Queue of instructor is: " + queueOfInstructors.toString());
                }
                });

                super.updateItem(item, empty);
            }
        });
    }
    
    
}
