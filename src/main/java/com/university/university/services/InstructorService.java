package com.university.university.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.university.university.models.Instructor;
import com.university.university.repositories.InstructorRepository;

@Component
public class InstructorService {
    
    @Autowired
    private InstructorRepository instructorRepository;

    public void save(Instructor i){
        instructorRepository.save(i);
    }

    public void saveInstructor(List<Instructor> i){
        instructorRepository.saveAll(i);
    }

    public <T> List<T> getInstructors(){
        return (List<T>) instructorRepository.findAll();
    }

    public <T> T getInstructorById(int id){
        return (T) instructorRepository.findById(id).get();
    }
}
