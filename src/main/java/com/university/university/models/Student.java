package com.university.university.models;

import com.university.university.configurations.LoggerConfig;
import jakarta.persistence.CascadeType;
import java.time.LocalDate;
import java.util.List;

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
@ToString(exclude = {"courses"})
@Table( // To change table settings like name
    name = "students",
    //schema = "university",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "id_unique",
            columnNames = "student_id"
        )
    }
    ) 

public class Student extends Person implements Cloneable{
    @Id // Primary keys in tables
    @Column(name = "student_id", nullable = false)
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "student_generator"    
    )
    @SequenceGenerator(
        name = "student_generator",
        sequenceName = "student_id_sequence",
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
    
    @Column(name = "student_identity")
    private String a_sId;

    @Column(name = "degree")
    private String degree;
    @ManyToMany(mappedBy = "students", fetch = FetchType.EAGER)
    private Set<Course> courses;
    
    public Student(int a_id, String b_firstName, String c_lastName, LocalDate d_dob, String e_email, String f_address,
            char g_gender, String a_sId, String degree, Set<Course> courses) {
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

        this.a_sId = ("" + b_firstName.charAt(0) + c_lastName.charAt(0) + d_dob.getYear() + randomNumber + 'S');
        this.degree = degree;
        this.courses = courses;
    }

    public Student(Student student){
        new Student(student.getA_id(), student.getB_firstName(), student.getC_lastName(), student.getD_dob(), student.getE_email(), 
        student.getF_address(), student.getG_gender(), student.getA_sId(), student.getDegree(), student.getCourses());
    }
    
    public void addCourse(Course course){
        if(this.courses == null){
            courses = new HashSet<>();
        } 
        if(courses.contains(course)){
            LoggerConfig.logger.info("The set already has the course: " + course);
        } else {
            course.getStudents().add(this);
            courses.add(course);
            LoggerConfig.logger.info("The courses " + course + " has been added");
        }
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((a_sId == null) ? 0 : a_sId.hashCode());
        result = prime * result + ((degree == null) ? 0 : degree.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Student other = (Student) obj;
        if (a_sId == null) {
            if (other.a_sId != null)
                return false;
        } else if (!a_sId.equals(other.a_sId))
            return false;
        if (degree == null) {
            if (other.degree != null)
                return false;
        } else if (!degree.equals(other.degree))
            return false;
        return true;
    }
    
    @Override
    public String toString(){
        return this.getB_firstName() + " " + this.getC_lastName();
    }
}
