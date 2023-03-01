package com.university.university;

import java.io.IOException;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class JavaFxApplication extends Application{

    private ConfigurableApplicationContext context;
    private static ConfigurableApplicationContext staticContext;

    
    @Override
    public void init() throws Exception {
        ApplicationContextInitializer<GenericApplicationContext> initializer = 
        context ->{
                context.registerBean(Application.class, () -> JavaFxApplication.this);
                context.registerBean(Parameters.class, this::getParameters);
                context.registerBean(HostServices.class, this::getHostServices);
        };

        this.context = new SpringApplicationBuilder()
        .sources(UniversityApplication.class).
        initializers(initializer).
        run(getParameters().getRaw().toArray(new String[0]));
    }


    @Override
    public void stop() throws Exception {
        this.context.close();
        Platform.exit();
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        this.context.publishEvent(new StageReadyEvent(primaryStage));
    }

    public static Parent loadFXML(String fxml) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(JavaFxApplication.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
}
class StageReadyEvent extends ApplicationEvent{

    public Stage getStage(){
        return Stage.class.cast(getSource());
    }

    public StageReadyEvent(Stage stage) {
        super(stage);
    }
}
