package com.university.university.models;

import com.university.university.configurations.LoggerConfig;
import jakarta.persistence.CascadeType;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity // For objects to use with rdb tables
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Table( // To change table settings like name
    name = "instructor",
    //schema = "university",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "id_unique",
            columnNames = "instructor_id"
        )
    }
    ) 

public class Instructor{
    @Id // Primary keys in tables
    @Column(name = "instructor_id", nullable = false)
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "instructor_generator"    
    )
    @SequenceGenerator(
        name = "instructor_generator",
        sequenceName = "instructor_id_sequence",
        allocationSize = 1
        
    )
    private int a_id;
    @Column(name = "first_name", nullable = false, length = 30)
    private String b_firstName;
    @Column(name = "last_name", nullable = false, length = 30)
    private String c_lastName;
    @Column(name = "dob", nullable = false)
    private LocalDate d_dob;
    @Column(name = "email", nullable = false, length = 50)
    private String e_email;
    @Column(name = "address", nullable = true, length = 50)
    private String f_address;
    @Column(name = "gender", nullable = false, length = 1)
    private char g_gender;

    @Column(name = "instructor_identity", length = 30)
    private String a_iId;
    @Column(name = "department")
    private String department;
    @OneToMany(mappedBy = "instructor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Course> courses;

    public Instructor(int a_id, String b_firstName, String c_lastName, LocalDate d_dob, String e_email,
            String f_address, char g_gender, String a_iId, String department, Set<Course> courses) {
        //super(a_id, b_firstName, c_lastName, d_dob, e_email, f_address, g_gender);
        
        this.a_id = a_id;
        this.b_firstName = b_firstName;
        this.c_lastName = c_lastName;
        this.d_dob = d_dob;
        this.e_email = e_email;
        this.f_address = f_address;
        this.g_gender = g_gender;
        
        Random r = new Random();
        String randomNumber = String.format("%04d", (Object) Integer.valueOf(r.nextInt(1001)));
        
        this.a_iId = ("" + b_firstName.charAt(0) + c_lastName.charAt(0) + d_dob.getYear() + randomNumber + 'I');
        this.department = department;
        this.courses = courses;
    }

        public void addCourse(Course course){
        if(this.courses == null){
            courses = new HashSet<>();
        } 
        if(courses.contains(course)){
            LoggerConfig.logger.info("The set already has the course: " + course);
        } else {
            courses.add(course);
            LoggerConfig.logger.info("The courses " + course + " has been added");
        }
    }
}
