/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.university.university.repositories;

import com.university.university.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author azeemafridi
 */
public interface CoursesRepository extends JpaRepository<Course, Long>{
    
}
