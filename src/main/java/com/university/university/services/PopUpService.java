package com.university.university.services;

import com.university.university.controllers.InstructorFormController;
import com.university.university.controllers.StudentFormController;
import com.university.university.services.popups.CommitPopUp;
import com.university.university.services.popups.DeletePopUp;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;

import javafx.stage.Stage;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PopUpService {

    public static void showCommitPopUp(){
        new CommitPopUp();
    }

    public static void showStudentFormPopUp(){
        Stage controller = new StudentFormController();
        try{
        URL url = new URL("classpath:/fxml/studentForm.fxml");
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        controller.setScene(scene);
        controller.initModality(Modality.APPLICATION_MODAL);
        controller.setTitle("Add Student");
        controller.setResizable(false);
        controller.showAndWait();
        } catch(Exception e){
            
        }
    }

    public static void showInstructorFormPopUp(){
        Stage controller = new InstructorFormController();
        try{
        URL url = new URL("classpath:/fxml/instructorForm.fxml");
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        controller.setScene(scene);
        controller.initModality(Modality.APPLICATION_MODAL);
        controller.setTitle("Add Instructor");
        controller.setResizable(false);
        controller.showAndWait();
        } catch(Exception e){
            
        }
    }
    
    public static void showDeletePopUp(){
        new DeletePopUp();
    }
}
