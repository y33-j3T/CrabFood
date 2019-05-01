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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
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

    Scene sceneMenu, sceneMR, sceneMD, sceneVOL, sceneSC, sceneER, sceneEDs, sceneED;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Stage window = primaryStage;

        // =====================================================================
        // MENU
        // =====================================================================
        // Manage Restaurants, Manage Delivery, View Order Log
        Button btnMR = new Button("Manage Restaurants");
        btnMR.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btnMR.setOnAction(fn -> window.setScene(sceneMR));

        Button btnMD = new Button("Manage Delivery");
        btnMD.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btnMD.setOnAction(fn -> window.setScene(sceneMD));

        Button btnVOL = new Button("View Order Log");
        btnVOL.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btnVOL.setOnAction(fn -> window.setScene(sceneVOL));

        Button btnSC = new Button("Simulate Customer");
        btnSC.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btnSC.setOnAction(fn -> window.setScene(sceneSC));

        // #
        VBox layoutMenuLeft = new VBox(10, btnMR, btnMD, btnVOL, btnSC);

        // Process Log
        TextArea txtareaPL = new TextArea();
        txtareaPL.setMinSize(500, 400);
        txtareaPL.setEditable(false);

        // Order Status
        TableColumn<CrabFoodOrder, Integer> colCustomerId = new TableColumn<>("Customer ID");
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));

        TableColumn<CrabFoodOrder, SimulatedTime> colOrderTime = new TableColumn<>("Order Time");
        colOrderTime.setCellValueFactory(new PropertyValueFactory<>("orderTime"));

        TableColumn<CrabFoodOrder, Restaurant> colRestaurant = new TableColumn<>("Restaurant");
        colRestaurant.setCellValueFactory(new PropertyValueFactory<>("restaurant"));

        TableColumn<CrabFoodOrder, Dish> colDish = new TableColumn<>("Dish");
        colDish.setCellValueFactory(new PropertyValueFactory<>("dish"));

        TableColumn<CrabFoodOrder, Integer> colQuantity = new TableColumn<>("Quantity");
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        TableColumn<CrabFoodOrder, String> colStatus = new TableColumn<>("Status");
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        TableView tableOS = new TableView();
        tableOS.setMinSize(500, 400);
        tableOS.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableOS.getColumns().addAll(colCustomerId, colOrderTime, colRestaurant, colDish, colQuantity, colStatus);
//        tableOS.setItems(getData());

        // #
        VBox layoutMenuRight = new VBox(10, txtareaPL, tableOS);
        VBox.setVgrow(txtareaPL, Priority.ALWAYS);
        VBox.setVgrow(tableOS, Priority.ALWAYS);

        // ##
        GridPane layoutMenu = new GridPane();
        GridPane.setVgrow(layoutMenuRight, Priority.ALWAYS);
        GridPane.setHgrow(layoutMenuRight, Priority.ALWAYS);
        GridPane.setConstraints(layoutMenuLeft, 0, 0);
        GridPane.setConstraints(layoutMenuRight, 1, 0);
        layoutMenu.setPadding(new Insets(10, 10, 10, 10));
        layoutMenu.setHgap(10);
        layoutMenu.getChildren().addAll(layoutMenuLeft, layoutMenuRight);

        sceneMenu = new Scene(layoutMenu, 1080, 828);

        // =====================================================================
        // MANAGE RESTAURANT
        // =====================================================================
        // Restaurant List
        ListView listRestaurant = new ListView();
        listRestaurant.getItems().add("restaurant 1");
        listRestaurant.getItems().add("restaurant 2");
        listRestaurant.getItems().add("restaurant 3");

        // Buttons
        Button btnMR_EDIT = new Button("Edit");
        btnMR_EDIT.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btnMR_EDIT.setOnAction(fn -> window.setScene(sceneER));
//        ObservableList selectedIndices = listView.getSelectionModel().getSelectedIndices();

        Button btnMR_DELETE = new Button("Delete");
        btnMR_DELETE.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
//        btnMR_EDIT.setOnAction(fn -> window.setScene(sceneED));

        Button btnMR_ADD = new Button("Add");
        btnMR_ADD.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btnMR_ADD.setOnAction(fn -> window.setScene(sceneER));

        Button btnMR_DONE = new Button("Done");
        btnMR_DONE.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btnMR_DONE.setOnAction(fn -> window.setScene(sceneMenu));

        // #
        HBox layoutMRBottom = new HBox(10, btnMR_EDIT, btnMR_DELETE, btnMR_ADD, btnMR_DONE);
        layoutMRBottom.setAlignment(Pos.CENTER);

        // ##
        GridPane layoutMR = new GridPane();
        GridPane.setVgrow(listRestaurant, Priority.ALWAYS);
        GridPane.setHgrow(listRestaurant, Priority.ALWAYS);
        GridPane.setConstraints(listRestaurant, 0, 0);
        GridPane.setConstraints(layoutMRBottom, 0, 1);
        layoutMR.setPadding(new Insets(10, 10, 10, 10));
        layoutMR.setVgap(10);
        layoutMR.getChildren().addAll(listRestaurant, layoutMRBottom);

        sceneMR = new Scene(layoutMR, 1080, 828);

        // =====================================================================
        // MANAGE DELIVERY
        // =====================================================================
        // Number of Delivery Man
        Label labelNumDeliveryMan = new Label("Number of Delivery Man : ");

        Spinner spinnerNumDeliveryMan = new Spinner(1, 100, 1);
        spinnerNumDeliveryMan.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_HORIZONTAL);
        spinnerNumDeliveryMan.setEditable(true);

        // #
        HBox layoutMDTop = new HBox(10, labelNumDeliveryMan, spinnerNumDeliveryMan);
        layoutMDTop.setAlignment(Pos.CENTER);

        // Button
        Button btnMD_DONE = new Button("Done");
        btnMD_DONE.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btnMD_DONE.setOnAction(fn -> window.setScene(sceneMenu));

        // #
        HBox layoutMDBottom = new HBox(btnMD_DONE);
        layoutMDBottom.setAlignment(Pos.CENTER);

        // ##
        GridPane layoutMD = new GridPane();
        GridPane.setVgrow(layoutMDTop, Priority.ALWAYS);
        GridPane.setHgrow(layoutMDTop, Priority.ALWAYS);
        GridPane.setConstraints(layoutMDTop, 0, 0);
        GridPane.setConstraints(layoutMDBottom, 0, 1);
        layoutMD.setPadding(new Insets(10, 10, 10, 10));
        layoutMD.getChildren().addAll(layoutMDTop, layoutMDBottom);

        sceneMD = new Scene(layoutMD, 1080, 828);

        // =====================================================================
        // VIEW ORDER LOG
        // =====================================================================
        // Order Log
        TextArea txtareaOrderLog = new TextArea();
        txtareaOrderLog.setEditable(false);
        
        // Button
        Button btnVOL_BACK = new Button("Back");
        btnVOL_BACK.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btnVOL_BACK.setOnAction(fn -> window.setScene(sceneMenu));

        // #
        HBox layoutVOLBottom = new HBox(btnVOL_BACK);
        layoutVOLBottom.setAlignment(Pos.CENTER);

        // ##
        GridPane layoutVOL = new GridPane();
        GridPane.setVgrow(txtareaOrderLog, Priority.ALWAYS);
        GridPane.setHgrow(txtareaOrderLog, Priority.ALWAYS);
        GridPane.setConstraints(txtareaOrderLog, 0, 0);
        GridPane.setConstraints(layoutVOLBottom, 0, 1);
        layoutVOL.setVgap(10);
        layoutVOL.setPadding(new Insets(10, 10, 10, 10));
        layoutVOL.getChildren().addAll(txtareaOrderLog, layoutVOLBottom);

        sceneVOL = new Scene(layoutVOL, 1080, 828);

        // =====================================================================
        // SIMULATE CUSTOMER
        // =====================================================================
//        sceneSC = new Scene(layoutSC, 1080, 828);
        // =====================================================================
        // EDIT RESTAURANT
        // =====================================================================
//        sceneER = new Scene(layoutER, 1080, 828);
        // =====================================================================
        // EDIT DISHES
        // =====================================================================
//        sceneEDs = new Scene(layoutEDs, 1080, 828);
        // =====================================================================
        // EDIT DISH
        // =====================================================================
//        sceneED = new Scene(layoutED, 1080, 828);

        window.setMinHeight(876);
        window.setMinWidth(802);
        window.setScene(sceneMenu);
        window.setTitle("CrabFood");
        window.show();
    }

}