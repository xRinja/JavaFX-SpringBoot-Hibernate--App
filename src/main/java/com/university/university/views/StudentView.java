package com.university.university.views;

import com.university.university.controllers.StartController;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.university.university.models.Student;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.util.StringConverter;
import javafx.util.converter.CharacterStringConverter;
import javafx.util.converter.LocalDateStringConverter;
import lombok.Getter;
import lombok.Setter;

public class StudentView<T>{
    @Getter @Setter
    TableView<Student> table = new TableView<>();
    @Getter @Setter
    TableView<Student> serverTable = new TableView<>();

    TableColumn<Student, Integer> id = new TableColumn<>("id");
    TableColumn<Student, String> firstName = new TableColumn<>("first_name");
    TableColumn<Student, String> lastName = new TableColumn<>("last_name");
    TableColumn<Student, LocalDate> dob = new TableColumn<>("dob");
    TableColumn<Student, String> email = new TableColumn<>("email");
    TableColumn<Student, String> address = new TableColumn<>("address");
    TableColumn<Student, Character> gender = new TableColumn<>("gender");
    TableColumn<Student, String> studentId = new TableColumn<>("studentId");
    TableColumn<Student, String> degree = new TableColumn<>("degree");

    @Getter @Setter
    List<Student> queueOfStudents = new ArrayList<>();

    public StudentView(){
        
        /* Mapping Columns with Model Fields 
        Cell Value Config
        */
        
        configId(); 
        configFirstName();
        configLastName();

        dob.setCellValueFactory(new PropertyValueFactory<>("d_dob"));
        dob.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));
        email.setCellValueFactory(new PropertyValueFactory<>("e_email"));
        address.setCellValueFactory(new PropertyValueFactory<>("f_address"));
        gender.setCellValueFactory(new PropertyValueFactory<>("g_gender"));
        gender.setCellFactory(TextFieldTableCell.forTableColumn(new CharacterStringConverter()));
        studentId.setCellValueFactory(new PropertyValueFactory<>("a_sId"));
        degree.setCellValueFactory(new PropertyValueFactory<>("degree"));

        table.getColumns().addAll(List.of(id, firstName, lastName, dob, email, address, gender, studentId, degree));
        serverTable.getColumns().addAll(List.of(id, firstName, lastName, dob, email, address, gender, studentId, degree));
        addEditCommit();
    }

    private void addEditCommit(){
        for(TableColumn<Student, ?> tc : table.getColumns()){
            tc.setEditable(true);
        }
    }
    
    private void configId(){
        id.setCellValueFactory(new PropertyValueFactory<>("a_id"));
        //id.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    }
    
    private void configFirstName(){
        firstName.setCellValueFactory(new PropertyValueFactory<>("b_firstName"));
        firstName.setCellFactory(e -> {
            return new TextFieldTableCell<>(){
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
                        Student s = e.getRowValue();
                        s.setB_firstName(e.getNewValue());
                        table.getItems().set(e.getTablePosition().getRow(), s);
                        
                        if(s.getB_firstName().equals(serverTable.getItems().get(e.getTablePosition().getRow()).getB_firstName())){
                            this.setStyle("-fx-background-color: null");
                            if(queueOfStudents.contains(s)){
                                queueOfStudents.remove(s);
                            }
                        } else {
                            this.setBackground(new Background(new BackgroundFill(Color.WHEAT, null, getInsets())));
                            queueOfStudents.add(s);
                            System.out.println("Queue of students is: " + queueOfStudents.toString());
                        }
                    });
                    
                    super.updateItem(item, empty);
                }
            };
        });
    }
    
    private void configLastName(){
        lastName.setCellValueFactory(new PropertyValueFactory<>("c_lastName"));
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
                Student s = e.getRowValue();
                s.setC_lastName(e.getNewValue());
                table.getItems().set(e.getTablePosition().getRow(), s);

                if(s.getC_lastName().equals(serverTable.getItems().get(e.getTablePosition().getRow()).getC_lastName())){
                    this.setStyle("-fx-background-color: null");
                            if(queueOfStudents.contains(s)){
                                queueOfStudents.remove(s);
                            }
                } else {
                    this.setBackground(new Background(new BackgroundFill(Color.WHEAT, null, getInsets())));
                    queueOfStudents.add(s);
                    System.out.println("Queue of students is: " + queueOfStudents.toString());
                }
                });

                super.updateItem(item, empty);
            }
        }); 
    }

}