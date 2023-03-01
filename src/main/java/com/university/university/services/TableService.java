package com.university.university.services;

import java.util.List;

import com.university.university.controllers.StartController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TableService<T> {

    private static void syncStudentTable(){
        // Linking Student Tables
        StartController sc = StartController.getApplicationContext().getBean("startController", StartController.class);
        getData(sc.getStudentView().getServerTable(), sc.getStudentService().getStudents());
		getData(sc.getStudentView().getTable(), sc.getStudentService().getStudents());
    }

    private static void syncIntructorTable(){
        StartController sc = StartController.getApplicationContext().getBean("startController", StartController.class);
        // Linking Instructor Tables
        getData(sc.getInstructorView().getServerTable(), sc.getInstructorService().getInstructors());
		getData(sc.getInstructorView().getTable(), sc.getInstructorService().getInstructors());
    }

    private static <T> void getData(TableView<T> tableView, List<T> list){
        ObservableList<T> data = (ObservableList<T>) FXCollections.observableArrayList(list);
        tableView.setItems(data);
        tableView.refresh();
    }

    public static void syncTables(){
        syncIntructorTable();
        syncStudentTable();
    }

    public static <T> void clearTable(TableView<T> tableView){
        tableView.getColumns().clear();
        tableView.getItems().clear();

        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tableView.setEditable(true);
    }
}
