package crabfood;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {
//    
    public static void main(String[] args) {
        launch(args);
        /**
         *  start gui
         *  start time
         *      crabfood operator
         *          get order
         *              print process
         * 
         *          distribute order to restaurant and delivery
         *              print process
         * 
         *          listen to remaining process
         *              print process
         * 
         *          log customer ID, order time, dish prepare end time, deliver 
         *          end time, dish prepare duration, deliver duration, 
         *          restaurant, branch, dish
         *          
         * 
         *      simulate customer
         * 
         *      simulate traffic
         *  
         */
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(fn -> System.out.println("hello"));
        
        Label label1 = new Label("hello");
        
        StackPane layout = new StackPane();
        layout.getChildren().addAll(btn, label1);
        
        Scene scene = new Scene(layout, 300, 500);
        
        primaryStage.setTitle("CrabFood");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
}
