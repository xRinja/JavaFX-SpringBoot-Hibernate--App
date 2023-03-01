package com.university.university;

import java.io.IOException;
import java.net.URL;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.university.university.controllers.StartController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Getter;

@Component
public class StageListener implements ApplicationListener<StageReadyEvent>{

    private final String applicationTitle;
    private final Resource fxml;
    @Getter
    private final ApplicationContext context;
    
    StageListener(@Value("${spring.application.ui.title}") String applicationTitle,
                  @Value("classpath:/controllers/startui.fxml") Resource resource, ApplicationContext context){
        this.applicationTitle = applicationTitle;
        this.fxml = resource;
        this.context = context;
    }

    @Override
    public void onApplicationEvent(StageReadyEvent event) {
        try {
            Stage stage = event.getStage();
            URL url = this.fxml.getURL();
            FXMLLoader fxmlLoader = new FXMLLoader(url);
            fxmlLoader.setControllerFactory(param -> context.getBean(param));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root, 1200, 600);
            stage.setScene(scene);
            stage.setTitle(applicationTitle);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
