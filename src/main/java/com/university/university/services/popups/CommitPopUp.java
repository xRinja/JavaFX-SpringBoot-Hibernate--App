package com.university.university.services.popups;

import java.util.List;

import com.university.university.controllers.StartController;
import com.university.university.services.TableService;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CommitPopUp extends Stage{
    
    private StartController sc = StartController.getApplicationContext().getBean("startController", StartController.class);;

    // Fx Components
    private BorderPane pane;
    private Scene scene;
    private Label message;
    private Button yesButton;
    private Button noButton;
    private HBox hBox;

    {
        // Pop Up
        pane = new BorderPane();
        scene = new Scene(pane, 500, 100);
        this.setScene(scene);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setTitle("Uncommitted Changes Found!");

        // Button HBox
        hBox = new HBox();
        hBox.setSpacing(10);

        // Label
        message = new Label("There are uncommited changes, are you sure you want to leave?");

        // Buttons
        yesButton = new Button("Yes");
        noButton = new Button("No");

        // Assigning handlers to buttons
        buttonHandler(yesButton, noButton);

        // Adding all items to pane
        BorderPane.setAlignment(message, Pos.BOTTOM_CENTER);
        BorderPane.setMargin(message, new Insets(10,10,10,10));
        pane.setTop(message);
        
        hBox.getChildren().addAll(List.of(yesButton, noButton));
        hBox.setAlignment(Pos.TOP_CENTER);
        BorderPane.setAlignment(hBox, Pos.TOP_CENTER);
        BorderPane.setMargin(hBox, new Insets(10, 10, 10, 10));
        pane.setBottom(hBox);

        // Show
        this.showAndWait();
    }

    private void buttonHandler(Button yesButton, Button noButton){
        // Yes Button Handler
        yesButton.setOnAction(event -> {
            System.out.println("Pressed Yes");

            // Clear Queue List
            sc.getQueueService().clearAllQueues();

            // Focus the node too...
            sc.getListViewDB().requestFocus();
            sc.getListViewDB().getFocusModel().focus(sc.getListViewDB().getSelectionModel().getSelectedIndex());

            sc.setCurrentNode(false);

            // ReSyncing Tables
            TableService.syncTables();
            this.close();
       });

        // No button handler
        noButton.setOnAction(event -> {
            System.out.println("Pressed No");
            sc.setCurrentNode(true);
            
            // Focus the node too...
            sc.getListViewDB().requestFocus();
            sc.getListViewDB().getSelectionModel().select(sc.getPreviousIndex());
            sc.getListViewDB().getFocusModel().focus(sc.getListViewDB().getSelectionModel().getSelectedIndex());

            sc.setCurrentNode(false);
            this.close();
       });
    }

}
