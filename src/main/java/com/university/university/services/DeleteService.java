package com.university.university.services;

import com.university.university.controllers.StartController;
import com.university.university.models.Student;
import javafx.scene.control.TableView;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 *
 * @author Azeem Afridi
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DeleteService {
    
    public static void deleteCurrentSelected(StartController sc){
        //StartController sc = StartController.getApplicationContext().getBean("startController", StartController.class);
        System.out.println("Right before nodeSelected");
        String nodeSelected = sc.getListViewDB().getSelectionModel().getSelectedItem().getValue();

        switch(nodeSelected){
            case "Students":
            Student student = (Student) sc.getTableView().getSelectionModel().getSelectedItem();
            System.out.println("Selected Item is: " + student);
            sc.getStudentService().deleteStudent(student);
            break;

            case "Instructors":
            
            break;

            case "Faculty":

            break;

            default:
            System.out.println("Nothing Selected");
        }
    }
    
}
