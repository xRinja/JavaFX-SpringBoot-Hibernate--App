package com.university.university.services;

import java.util.ArrayList;
import java.util.List;

import com.university.university.models.Person;
import com.university.university.views.InstructorView;
import com.university.university.views.StudentView;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class QueueService {
    
    @Getter @Setter
    private List<List<Person>> listOfQueues = new ArrayList<>();
    @Getter @Setter
    private boolean isQueueAvailable;

    public QueueService(StudentView studentView, InstructorView instructorView) {
        listOfQueues.add(studentView.getQueueOfStudents());
        listOfQueues.add(instructorView.getQueueOfInstructors());
    }

    public void checkAllQueues(){
        for(List<Person> p: listOfQueues){
            if(p.isEmpty()){
                isQueueAvailable = false;
            } else {
                isQueueAvailable = true;
                break;
            }
        }
    }

    public void clearAllQueues(){
        for(List<Person> p: listOfQueues){
            p.clear();
        }
        checkAllQueues();
    }
}
