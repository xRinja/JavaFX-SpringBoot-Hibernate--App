package com.university.university.views;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.university.university.models.Student;

import javafx.scene.control.TableColumn;

@Configuration
public class StudentViewPortConfig {
    
    @Bean(name = "studentViewPortTwo")
    public StudentViewPort getStudentViewPort(){
        
        TableColumn<Student, Integer> id = new TableColumn<>("id");
        TableColumn<Student, String> firstName = new TableColumn<>("first_name");
        TableColumn<Student, String> lastName = new TableColumn<>("last_name");
        TableColumn<Student, LocalDate> dob = new TableColumn<>("dob");
        TableColumn<Student, String> email = new TableColumn<>("email");
        TableColumn<Student, String> address = new TableColumn<>("address");
        TableColumn<Student, Character> gender = new TableColumn<>("gender");
        TableColumn<Student, String> studentId = new TableColumn<>("studentId");
        System.out.println("In config class id is: " + id.getText());
        return new StudentViewPort(id);
    }

    @Bean(name = "studentIdColumn")
    @Autowired
    public TableColumn<Student, Integer> getStudentIdColumn(){
        return new TableColumn<>("id");
    }
}
