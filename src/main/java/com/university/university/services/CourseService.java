/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.university.university.services;

import com.university.university.models.Course;
import com.university.university.repositories.CoursesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 *
 * @author azeemafridi
 */
@Component
public class CourseService {
    
    @Autowired
    private CoursesRepository coursesRepository;
    
    public void save(Course course){
        coursesRepository.save(course);
    }
}
