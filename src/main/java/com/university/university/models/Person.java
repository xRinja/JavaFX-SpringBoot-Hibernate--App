package com.university.university.models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//@Entity // For objects to use with rdb tables
//@MappedSuperclass
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table( // To change table settings like name
    name = "persons",
    //schema = "university",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "id_unique",
            columnNames = "id"
        )
    }
    ) 
public abstract class Person {
    @Id // Primary keys in tables
    @Column(name = "id", nullable = false)
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "persons_generator"    
    )
    @SequenceGenerator(
        name = "persons_generator",
        sequenceName = "persons_id_sequence",
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
}
