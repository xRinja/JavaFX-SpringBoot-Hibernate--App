package com.university.university.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.university.university.models.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{
    

}
