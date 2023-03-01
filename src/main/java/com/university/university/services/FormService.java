package com.university.university.services;

import com.university.university.controllers.StartController;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FormService {
    
    public static void forms(StartController sc){
        String nodeSelected = sc.getListViewDB().getSelectionModel().getSelectedItem().getValue();

        switch(nodeSelected){
            case "Students":
            PopUpService.showStudentFormPopUp();
            break;

            case "Instructors":
            PopUpService.showInstructorFormPopUp();
            break;

            case "Faculty":

            break;

            default:
            System.out.println("Nothing Selected");
        }
    }
}
