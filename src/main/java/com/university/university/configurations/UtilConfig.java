/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.university.university.configurations;

import com.university.university.StageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Azeem Afridi
 */
@Configuration
@ComponentScan("com.university.university")
public class UtilConfig {
    
    @Autowired
    private StageListener stageListener(StageListener stageListener){
        return stageListener;
    }
}
