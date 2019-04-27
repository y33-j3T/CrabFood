package crabfood;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {
//    
    public static void main(String[] args) {
        CrabFoodOperator operator = new CrabFoodOperator();
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
    

    Scene scene1, scene2;
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        Button btn = new Button();
        btn.setText("Go to scene 2");
        btn.setOnAction(fn -> primaryStage.setScene(scene2));
        
        StackPane layout1 = new StackPane();
        layout1.getChildren().addAll(btn);
        
        scene1 = new Scene(layout1, 300, 500);
        
        Button btn2 = new Button();
        btn2.setText("go to scene 1");
        btn2.setOnAction(fn -> primaryStage.setScene(scene1));
        
        HBox layout2 = new HBox();
        Label label1 = new Label("helllfsodfos");
        layout2.getChildren().addAll(btn2, label1);
        scene2 = new Scene(layout2, 300, 500);
        
        
        primaryStage.setTitle("CrabFood");
        primaryStage.setScene(scene1);
        primaryStage.show();
    }
    
}
