/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.university.university.services;

import com.university.university.configurations.LoggerConfig;
import com.university.university.models.Course;
import com.university.university.models.Instructor;
import com.university.university.models.Student;
import com.university.university.repositories.CoursesRepository;
import com.university.university.repositories.InstructorRepository;
import com.university.university.repositories.StudentRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.test.annotation.Rollback;

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
    
    @Autowired
    private EntityManager entityManager;
    
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
    @Transactional
    @Rollback(false) // Or @Commit
    public void addStudent(){
        Session session = entityManager.unwrap(Session.class);
        session.getSessionFactory().openSession();
        
        Student azeem = session.find(Student.class, 3);
        //Student azeem = new Student(studentRepository.findById(3).get());
        Course course = session.find(Course.class, 1l);
        
        azeem.addCourse(course);
        course.addStudent(azeem);
//        azeem.getCourses().add(course);
//        course.getStudents().add(azeem);
        
        LoggerConfig.logger.info("Student is: " + azeem);
        LoggerConfig.logger.info("Course is: " + course);
        session.persist(azeem);
        session.persist(course);
        session.close();
    }
}
