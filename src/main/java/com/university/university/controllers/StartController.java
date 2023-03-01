package com.university.university.controllers;

import com.university.university.StageListener;
import com.university.university.configurations.LoggerConfig;
import com.university.university.models.Target;
import com.university.university.services.AddListenersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.university.university.services.CommitService;
import com.university.university.services.FormService;
import com.university.university.services.InstructorService;
import com.university.university.services.PopUpService;
import com.university.university.services.QueueService;
import com.university.university.services.StudentService;
import com.university.university.services.TableService;
import com.university.university.views.InstructorView;
import com.university.university.views.StudentView;

import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import lombok.Getter;   
import lombok.Setter;

@Getter
@Setter
@Component
public class StartController {

    // Tree Structure
    @FXML
    private TreeView<String> listViewDB;

    // Persons Nodes
    private TreeItem<String> studentNode = new TreeItem<String>("Students");
    private TreeItem<String> instructorNode = new TreeItem<String>("Instructors");
    private TreeItem<String> facultyNode = new TreeItem<String>("Faculty");

    // Courses Node
    private TreeItem<String> courseNode = new TreeItem<String>("Courses");

    // Node selected index
    private int previousIndex = 0;

    // Table
    @FXML
    private TableView tableView;

    // Buttons
    @FXML
    private Button okayButton;

    // Services
    @Autowired
    private StudentService studentService;
    @Autowired
    private InstructorService instructorService;
    private QueueService queueService;

    // Views
    private StudentView studentView;
    private InstructorView instructorView;

    // State
    private Target state = Target.NA;

    // Application Context
    private static ApplicationContext applicationContext;
        
    @Autowired
    private StageListener stageListener;

    // Node
    private TreeItem<String> rootNode = new TreeItem<String>("Sys Database Management");
    private TreeItem<String> categoryPersonNode = new TreeItem<String>("Persons");
    private TreeItem<String> categoryCoursesNode = new TreeItem<String>("Courses");

    // Bools
    private boolean isCurrentNode;
    
    @FXML
    private BorderPane loginfxml;
    @FXML
    private Button addButton;
    @FXML
    private Button deleteHolder;

    public void initialize() {
        // Create subject nodes
        applicationContext = stageListener.getContext();
        init();
    }

    private void init() {
        // Views Init for Table
        studentView = new StudentView();
        instructorView = new InstructorView();

        // Queue Service
        queueService = new QueueService(studentView, instructorView);

        // Table Service Extracting Data From Server
        TableService.syncTables();

        // Tree List Config
        rootNode.setExpanded(true);
        categoryPersonNode.setExpanded(true);
        categoryCoursesNode.setExpanded(true);

        // Adding Children Nodes
        categoryPersonNode.getChildren().addAll(List.of(studentNode, instructorNode, facultyNode));
        categoryCoursesNode.getChildren().addAll(List.of(courseNode));
        rootNode.getChildren().addAll(List.of(categoryPersonNode, categoryCoursesNode));

        // Setting Root
        listViewDB.setRoot(rootNode);
        listViewDB.setShowRoot(false);
        AddListenersService.addTreeViewListeners(this);
    }

    // Save to database
    @FXML
    public void commitChanges(){
            CommitService.commit(this);
    }

    // Add item
    @FXML
    public void addItem(){
            System.out.println("Add Menu");
            FormService.forms(this);
    }

    // Delete Item
    @FXML
    public void deleteItem(){
            System.out.println("Delete Menu");
            PopUpService.showDeletePopUp();
    }

    private void showStudentTable(){
            tableView.getColumns().addAll(studentView.getTable().getColumns());
            tableView.getItems().addAll(studentView.getTable().getItems());
            tableView.refresh();
    }

    private void showInstructorTable(){
            tableView.getColumns().addAll(instructorView.getTable().getColumns());
            tableView.getItems().addAll(instructorView.getTable().getItems());
            tableView.refresh();
    }
    
    private void showCoursesTable(){
            tableView.getColumns().addAll(instructorView.getTable().getColumns());
            tableView.getItems().addAll(instructorView.getTable().getItems());
            tableView.refresh();
    }
        
    public void showTable(int index){
        System.out.println("index is" + index);
        
        switch(index){
            case 1:
                showStudentTable();
                break;
                
            case 2:
                showInstructorTable();
                break;
                
            case 3:
                
                break;
            case 5:
                
                break;
        }
    }
	
    public static ApplicationContext getApplicationContext(){
            return applicationContext;
    }
}
