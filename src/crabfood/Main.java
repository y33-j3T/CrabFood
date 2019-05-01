package crabfood;

import crabfood.CrabFoodOperator.CrabFoodOrder;
import crabfood.Restaurant.Dish;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
//    

    public static void main(String[] args) {
        CrabFoodOperator operator = new CrabFoodOperator();
        launch(args);

        /**
         * start gui start time crabfood operator get order print process
         *
         * distribute order to restaurant and delivery print process
         *
         * listen to remaining process print process
         *
         * log customer ID, order time, dish prepare end time, deliver end time,
         * dish prepare duration, deliver duration, restaurant, branch, dish
         *
         *
         * simulate customer
         *
         * simulate traffic
         *
         */
    }

    Scene sceneMR, sceneMD, sceneVOL, sceneMenu;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Stage window = primaryStage;
        
        // =====================================================================
        // MENU
        // =====================================================================
        // Manage Restaurants, Manage Delivery, View Order Log
        Button btnMR = new Button();
        btnMR.setText("Manage Restaurants");
        btnMR.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
//        btnMR.setOnAction(fn -> primaryStage.setScene(sceneMR));

        Button btnMD = new Button();
        btnMD.setText("Manage Delivery");
        btnMD.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
//        btnMD.setOnAction(fn -> primaryStage.setScene(sceneMD));

        Button btnVOL = new Button();
        btnVOL.setText("View Order Log");
        btnVOL.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
//        btnVOL.setOnAction(fn -> primaryStage.setScene(sceneVOL));

        Button btnSC = new Button();
        btnSC.setText("Simulate Customer");
        btnSC.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
//        btnVOL.setOnAction(fn -> primaryStage.setScene(sceneVOL));

        VBox MenuLayoutLeft = new VBox(10, btnMR, btnMD, btnVOL, btnSC);
        MenuLayoutLeft.setAlignment(Pos.TOP_LEFT);
        // =====================================================================
        // Process Log
        TextArea txtareaPL = new TextArea();
        txtareaPL.setMinSize(500, 400);
        txtareaPL.setEditable(false);
        
        // =====================================================================
        // Order Status
        TableColumn<CrabFoodOrder, Integer> customerIdCol = new TableColumn<>("Customer ID");
        customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        
        TableColumn<CrabFoodOrder, SimulatedTime> orderTimeCol = new TableColumn<>("Order Time");
        orderTimeCol.setCellValueFactory(new PropertyValueFactory<>("orderTime"));
        
        TableColumn<CrabFoodOrder, Restaurant> restaurantCol = new TableColumn<>("Restaurant");
        restaurantCol.setCellValueFactory(new PropertyValueFactory<>("restaurant"));
        
        TableColumn<CrabFoodOrder, Dish> dishCol = new TableColumn<>("Dish");
        dishCol.setCellValueFactory(new PropertyValueFactory<>("dish"));
        
        TableColumn<CrabFoodOrder, Integer> quantityCol = new TableColumn<>("Quantity");
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        
        TableColumn<CrabFoodOrder, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        
        TableView tableOS = new TableView();
        tableOS.setMinSize(500, 400);
//        tableOS.setItems(getData());
        tableOS.getColumns().addAll(customerIdCol, orderTimeCol, restaurantCol, dishCol, quantityCol, statusCol);
        
        VBox MenuLayoutRight = new VBox(8, txtareaPL, tableOS);
        MenuLayoutRight.setAlignment(Pos.CENTER);
        VBox.setVgrow(txtareaPL, Priority.ALWAYS);
        VBox.setVgrow(tableOS, Priority.ALWAYS);
        // =====================================================================
        GridPane MenuLayout = new GridPane();
        GridPane.setVgrow(MenuLayoutRight, Priority.ALWAYS);
        GridPane.setHgrow(MenuLayoutRight, Priority.ALWAYS);
        GridPane.setConstraints(MenuLayoutLeft, 0, 0);
        GridPane.setConstraints(MenuLayoutRight, 1, 0);
        MenuLayout.setPadding(new Insets(10, 10, 10, 10));
        MenuLayout.setHgap(10);
        MenuLayout.getChildren().addAll(MenuLayoutLeft, MenuLayoutRight);
        
        sceneMenu = new Scene(MenuLayout, 1080, 828);
        
        window.setMinHeight(876);
        window.setMinWidth(802);
        window.setScene(sceneMenu);
        window.setTitle("CrabFood");
        window.show();
    }

}
