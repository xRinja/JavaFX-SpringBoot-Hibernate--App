package com.university.university.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.university.university.models.Faculty;
import com.university.university.models.Instructor;
import com.university.university.models.Student;

@SpringBootTest
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    void saveMethod(){
        LocalDate dob = LocalDate.of(1994, 05, 19);
        Student azeem = new Student(0, "Azeem", "Afridi", dob, "azeemafridibk@gmail.com", "1110 State Road", 'M', "", null, null);
        Random r = new Random();
        String randomNumber = String.format("%04d", (Object) Integer.valueOf(r.nextInt(1001)));
        System.out.println(randomNumber);
        azeem.setA_sId((""+azeem.getB_firstName().charAt(0)) + (""+azeem.getC_lastName().charAt(0)) + 
        azeem.getD_dob().getYear() + randomNumber);
        // Save
        studentRepository.save(azeem);
        // Display
        System.out.println(azeem.getB_firstName() + azeem.getC_lastName());
    }

    @Test
    void updateUsingSaveMethod(){
        // Find and retrieve entitry by id
        int id = 1;
        Student person = studentRepository.findById(id).get();

        // Update entity information
        person.setB_firstName("Shah");
        person.setF_address("321 High St");
        // Save updated entity
        studentRepository.save(person);   
    }

    @Test
    void findByIdMethod(){
        int id = 1;
        Student person = studentRepository.findById(id).get();      
        System.out.println(person);  
    }

    @Test
    void saveAllMethod(){
        // Create persons
        Student azeem = new Student();
        azeem.setB_firstName("Rehan");
        azeem.setC_lastName("Afridi");
        azeem.setD_dob(LocalDate.of(1984, 03, 8));
        azeem.setE_email("rehan.afridi@aol.com");
        azeem.setF_address("1110 State Road");
        azeem.setG_gender('M');
        
        Random r = new Random();
        String randomNumber = String.format("%04d", (Object) Integer.valueOf(r.nextInt(1001)));
        azeem.setA_sId((""+azeem.getB_firstName().charAt(0)) + (""+azeem.getC_lastName().charAt(0)) + 
        azeem.getD_dob().getYear() + randomNumber);

        Student stephania = new Student();
        stephania.setB_firstName("Stephania");
        stephania.setC_lastName("Barrios");
        stephania.setD_dob(LocalDate.of(1991, 05, 9));
        stephania.setE_email("stephania.barrios@outlook.com");
        stephania.setF_address("1110 State Road");
        stephania.setG_gender('F');
        
        r = new Random();
        randomNumber = String.format("%04d", (Object) Integer.valueOf(r.nextInt(1001)));
        stephania.setA_sId((""+stephania.getB_firstName().charAt(0)) + (""+stephania.getC_lastName().charAt(0)) + 
        stephania.getD_dob().getYear() + randomNumber);

        studentRepository.saveAll(List.of(azeem, stephania));
    }

    @Test
    void findAllMethod(){
        List<Student> persons = studentRepository.findAll();
        for(Student p : persons){
            System.out.println(p);
        }
    }

    @Test 
    void deleteByIdMethod(){
        int id = 1;
        studentRepository.deleteById(id);
    }

    @Test
    void deleteMethod(){
        // Find entity
        int id = 2;
        Student person = studentRepository.findById(id).get();
        // Delete entity
        studentRepository.delete(person);
    }

    @Test
    void deleteAllMethod(){
        studentRepository.deleteAll(); // Deletes all or put an argument inside to delete specifics
    }

    @Test
    void countMethod(){
        long count = studentRepository.count();
        System.out.println("Count is:" + count);
    }

    @Test
    void existsByIdMethod(){
        int id = 7;
        boolean isTrue = studentRepository.existsById(id);
        System.out.println("Boolean is: " + isTrue);
    }

    @Test
    void getColumnNames(){

    }
}
