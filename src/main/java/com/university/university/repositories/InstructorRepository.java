package com.university.university.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.university.university.models.Instructor;

public interface InstructorRepository extends JpaRepository<Instructor, Integer>{
    
}
