package com.university.university.services;

import java.util.List;

import com.university.university.controllers.StartController;
import com.university.university.models.Instructor;
import com.university.university.models.Person;
import com.university.university.models.Student;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommitService {

    public static void commit(StartController startController){
        System.out.println("Commiting Change");
        for(List<Person> pl : startController.getQueueService().getListOfQueues()){
            for(Person p : pl){
                String classSimpleName = p.getClass().getSimpleName().toUpperCase();
                System.out.println("Simple Name is: " + classSimpleName);

                switch(classSimpleName){
                    case "STUDENT":
                    // Saving Student
                    System.out.println("Saved In Student");
                    startController.getStudentService().save((Student) p);
                    break;

                    case "INSTRUCTOR":
                    System.out.println("Saved In Instructor");
                    startController.getInstructorService().save((Instructor) p);
                    break;

                    case "FACULTY":
                    System.out.println("Saved In Faculty");
                    break;
                    default:

                    break;
                }
            }
            // Updating Tables
            TableService.syncTables();

            // Clear List After Adding Everyone
            pl.clear();
            startController.getTableView().refresh();
        }
    }
}
