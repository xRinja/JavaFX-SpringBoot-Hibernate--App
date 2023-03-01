package com.university.university.models;

import com.university.university.configurations.LoggerConfig;
import jakarta.persistence.CascadeType;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cascade;

@Entity // For objects to use with rdb tables
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table( // To change table settings like name
    name = "courses",
    //schema = "university",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "id_unique",
            columnNames = "course_id"
        )
    }) 
public class Course {
    @Id // Primary keys in tables
    @Column(name = "course_id", nullable = false)
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "course_generator"    
    )
    @SequenceGenerator(
        name = "course_generator",
        sequenceName = "course_id_sequence",
        allocationSize = 1
    )
    private long a_cId;
    @Column(name = "course_name", nullable = false, length = 30)
    private String b_cName;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;
    @Column(name = "start_time", nullable = false)
    private Date c_startTime;
    @Column(name = "end_time", nullable = false)
    private Date d_endTime;
    @ManyToMany
    @JoinTable(
            name = "student_courses",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private Set<Student> students;

    public Course(long a_cId, String b_cName, Date c_startTime, Date d_endTime) {
        this.a_cId = a_cId;
        this.b_cName = b_cName;
        this.c_startTime = c_startTime;
        this.d_endTime = d_endTime;
    }
    
    public void addStudent(Student student){
        if(this.students == null){
            students = new HashSet<>();
        } 
        if(students.contains(student)){
            LoggerConfig.logger.info("The set already has the student: " + student);
        } else {
            students.add(student);
            LoggerConfig.logger.info("The currentStudents " + student + " has been added");
        }
    }
}
