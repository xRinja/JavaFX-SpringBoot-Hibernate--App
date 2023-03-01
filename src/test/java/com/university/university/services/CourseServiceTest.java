/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.university.university.services;

import com.university.university.models.Course;
import com.university.university.models.Instructor;
import com.university.university.models.Student;
import com.university.university.repositories.CoursesRepository;
import com.university.university.repositories.InstructorRepository;
import com.university.university.repositories.StudentRepository;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author azeemafridi
 */
@SpringBootTest
//@Transactional
public class CourseServiceTest {
    
    @Autowired
    private InstructorRepository instructorRepository;
    
    @Autowired
    private CoursesRepository coursesRepository;
    
    @Autowired
    private StudentRepository studentRepository;
    
    public CourseServiceTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of save method, of class CourseService.
     */
    @Test
    public void testSave() {
        System.out.println("save");
        LocalDate dob = LocalDate.of(1991, 8, 22);
        Course course = new Course(0, "ALGEBRA01",
                java.sql.Date.valueOf(LocalDate.of(2023, Month.MARCH, 3)), 
                java.sql.Date.valueOf(LocalDate.of(2023, Month.APRIL, 13)));
        
        // Course Set for Instructor
        Set<Course> courseSet = new HashSet();
        courseSet.add(course);
        
        // Student Set for Courses
        Set<Student> studentSet = new HashSet();
        
        // Building Instructor
        Instructor instructor = new Instructor(0,"Johnathan", "Rugera", dob, "jeoe@123.com", 
        "321 High St", 'M', "FHO342D", "Math", courseSet);
        course.setInstructor(instructor);
        course.setStudents(studentSet);
        
        // Saving to Database
        instructorRepository.save(instructor);
        coursesRepository.save(course);
    }
    
    @Test
    @Transactional(Transactional.TxType.REQUIRED)
    public void addStudent(){
        LocalDate dob = LocalDate.of(1994, 05, 19);
        Student azeem = new Student(0, "Azeem", "Afridi", dob, "azeemafridibk@gmail.com", 
                "1110 State Road", 'M', "", "Computer Science", new HashSet<Course>());
        Course course = coursesRepository.findById(1L).get();
        course.addStudent(azeem);
        azeem.addCourse(course);
        studentRepository.save(azeem);
        coursesRepository.save(course);

    }
}
