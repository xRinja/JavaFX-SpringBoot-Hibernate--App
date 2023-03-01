package com.university.university.repositories;

import com.university.university.configurations.LoggerConfig;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.university.university.models.Instructor;
import java.util.HashMap;
import java.util.HashSet;

@SpringBootTest
public class InstructorRepositoryTest {

    @Autowired
    private InstructorRepository instructorRepository;
    
    @Test 
    public void saveMethod(){
        LocalDate dob = LocalDate.of(1991, 8, 22);
        Instructor instructor = new Instructor(0,"Johnathan", "Rugera", dob, "jeoe@123.com", 
        "321 High St", 'M', "FHO342D", "Math", new HashSet<>());
        LoggerConfig.logger.info(instructorRepository.save(instructor) + "");
        System.out.println("Instructor saved...");
    }

    @Test
    public void updateUsingSaveMethod(){
        int id = 2;
        Instructor person = instructorRepository.findById(id).get();

        person.setC_lastName("Phillipie");
        person.setDepartment("Scienece");
        instructorRepository.save(person);
    }
}
