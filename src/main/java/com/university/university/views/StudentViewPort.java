package com.university.university.views;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.university.university.models.Student;

import javafx.application.Application;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;


public class StudentViewPort {

    @Autowired
    TableColumn<Student, Integer> idColumn;

    public StudentViewPort(@Qualifier("studentIdColumn") TableColumn<Student,Integer> idColumn){
        this.idColumn = idColumn;
    }

    public TableColumn<Student, Integer> getIdColumn() {
        return idColumn;
    }

    public void setIdColumn(TableColumn<Student, Integer> idColumn) {
        this.idColumn = idColumn;
    }
}
