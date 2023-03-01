/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.university.university.services;

import com.university.university.configurations.LoggerConfig;
import com.university.university.controllers.StartController;
import com.university.university.models.Target;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TreeItem;

/**
 *
 * @author azeemafridi
 */
public class AddListenersService {
    
    public static void addTreeViewListeners(StartController startController){
        startController.getListViewDB().getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<String>>() {

            @Override
            public void changed(ObservableValue<? extends TreeItem<String>> observable, TreeItem<String> oldValue,
            TreeItem<String> newValue) {		
                
                // Check if any entity is currently in queue		
                startController.getQueueService().checkAllQueues();
                LoggerConfig.logger.info("Selected: " + newValue);
                
                // If Que is avaialble check to clear it
                if(startController.getQueueService().isQueueAvailable()){
                        // If pressed no on command popup
                        if(!startController.isCurrentNode()){
                                PopUpService.showCommitPopUp();
                        }
                } 

                // Else clear the table if there is no queue
                if(!startController.getQueueService().isQueueAvailable()) {
                    // Clear out table 	
                    TableService.clearTable(startController.getTableView());

                    // Which Node Is Selected
                    switch(newValue.getValue()){
                        case "Students": startController.setState(Target.STUDENT);
                        break;
                        case "Instructors": startController.setState(Target.INSTRUCTOR);
                        break;
                        case "Faculty": startController.setState(Target.FACULTY);
                        break;
                        default:
                    }
                    
                    // Changing Column and Items
                    startController.showTable(startController.getListViewDB().getSelectionModel().getSelectedIndex());
                    startController.setPreviousIndex(startController.getListViewDB().getSelectionModel().getSelectedIndex());
                }
            }
        });
    }
    
//    public static void addTreeViewListeners(StartController startController){
//        startController.getListViewDB().getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<String>>() {
//
//            @Override
//            public void changed(ObservableValue<? extends TreeItem<String>> observable, TreeItem<String> oldValue,
//            TreeItem<String> newValue) {		
//                
//                // Check if any entity is currently in queue		
//                startController.getQueueService().checkAllQueues();
//                LoggerConfig.logger.info("Selected: " + newValue);
//                
//                // If Que is avaialble check to clear it
//                if(startController.getQueueService().isQueueAvailable()){
//                        // If pressed no on command popup
//                        if(!startController.isCurrentNode()){
//                                PopUpService.showCommitPopUp();
//                        }
//                } 
//
//                // Else clear the table if there is no queue
//                if(!startController.getQueueService().isQueueAvailable()) {
//                        // Clear out table 	
//                        TableService.clearTable(startController.getTableView());
//                }
//
//                // Which Node Is Selected
//                switch(newValue.getValue()){
//                    case "Students":
//                    if(!startController.getQueueService().isQueueAvailable()){
//                            // Changing State
//                            startController.setState(Target.STUDENT);
//
//                            // Changing Column and Items
//                            startController.showTable(startController.getListViewDB().getSelectionModel().getSelectedIndex());
//                            startController.setPreviousIndex(startController.getListViewDB().getSelectionModel().getSelectedIndex());
//
//                    }
//                    break;
//                    case "Instructors":
//                    if(!startController.getQueueService().isQueueAvailable()){
//                        // Changing State
//                        startController.setState(Target.INSTRUCTOR);
//
//                        // Changing Columns and Items
//                        startController.showTable(startController.getListViewDB().getSelectionModel().getSelectedIndex());
//                        startController.setPreviousIndex(startController.getListViewDB().getSelectionModel().getSelectedIndex());
//                    }
//                    break;
//                    case "Faculty":
//                        startController.setState(Target.FACULTY);
//                    break;
//
//                    default:
//                }
//            }
//        });
//    }
}
