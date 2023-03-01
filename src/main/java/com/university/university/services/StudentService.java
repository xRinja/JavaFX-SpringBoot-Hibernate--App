package com.university.university.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.university.university.models.Student;
import com.university.university.repositories.StudentRepository;
import java.util.Optional;

@Component
public class StudentService {
    
    @Autowired
    private StudentRepository studentRepository;

    public void save(Student s){
        studentRepository.save(s);
    }

    public void saveStudents(List<Student> s){
        studentRepository.saveAll(s);
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(int id){
        Optional<Student> optionalStudent = studentRepository.findById(id);
        return optionalStudent;
    }

    public void updateStudent(Student s){
        Student serverStudent  = getStudentById(s.getA_id()).get();
        serverStudent.setA_id(s.getA_id());
        serverStudent.setB_firstName(s.getB_firstName());
        System.out.println("New Name is: " + s.getC_lastName());
        serverStudent.setC_lastName(s.getC_lastName());
        serverStudent.setD_dob(s.getD_dob());
        serverStudent.setE_email(s.getE_email());
        serverStudent.setF_address(s.getF_address());
        serverStudent.setG_gender(s.getG_gender());
        studentRepository.flush();
        System.out.println("Saving into repo...");
        save(serverStudent);
    }
    
    public void deleteStudent(Student s){
        studentRepository.delete(s);
    }
}
