package crabfood;

import crabfood.CrabFoodOperator.CrabFoodOrder;
import static crabfood.CrabFoodOperator.masterMap;
import crabfood.Restaurant.Dish;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
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
        txtareaPL.setText(masterMap.toString());

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
        // Customer ID
        Label labelCustomerID = new Label("Customer ID : ");

        Text txtCustomerID = new Text("999");

        // Order Time
        Label labelOrderTime = new Label("Order Time : ");

        Text txtOrderTime = new Text("10:30");

        // Restaurant
        Label labelRestaurant = new Label("Restaurant : ");

        ComboBox comboRestaurant = new ComboBox();

        // Orders (Dish & Quantity)
        Label labelOrder = new Label("Order : ");

        ComboBox comboOrder = new ComboBox();

        Label labelQuantity = new Label("Quantity : ");

        Spinner spinnerQuantity = new Spinner(1, 20, 1);

        Button btnSC_ADD = new Button("Add");
        btnSC_ADD.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
//        btnSC_ADD.setOnAction(fn -> window.setScene(sceneMenu));

        // Delivery Location
        Label labelDeliveryLoc = new Label("Delivery Location : ");
        Label labelX = new Label("X : ");
        Spinner spinnerX = new Spinner(1, 100, 1);
        Label labelY = new Label("Y : ");
        Spinner spinnerY = new Spinner(1, 100, 1);

        HBox coordinateLabels = new HBox(10, labelX, spinnerX, labelY, spinnerY);
        coordinateLabels.setAlignment(Pos.CENTER);

        // #
        GridPane layoutSCTopLeft = new GridPane();
        GridPane.setConstraints(labelCustomerID, 0, 0);
        GridPane.setConstraints(txtCustomerID, 1, 0);
        GridPane.setConstraints(labelOrderTime, 0, 1);
        GridPane.setConstraints(txtOrderTime, 1, 1);
        GridPane.setConstraints(labelRestaurant, 0, 2);
        GridPane.setConstraints(comboRestaurant, 1, 2);
        GridPane.setConstraints(labelOrder, 0, 3);
        GridPane.setConstraints(comboOrder, 1, 3);
        GridPane.setConstraints(btnSC_ADD, 2, 3);
        GridPane.setConstraints(labelQuantity, 0, 4);
        GridPane.setConstraints(spinnerQuantity, 1, 4);
        GridPane.setConstraints(labelDeliveryLoc, 0, 5);
        GridPane.setConstraints(coordinateLabels, 1, 5);
        layoutSCTopLeft.getChildren().addAll(labelCustomerID, txtCustomerID,
                labelOrderTime, txtOrderTime,
                labelRestaurant, comboRestaurant,
                labelOrder, comboOrder, btnSC_ADD,
                labelQuantity, spinnerQuantity,
                labelDeliveryLoc, coordinateLabels);
        

        // Your Orders
        Label labelYourOrders = new Label("Your orders");

        ListView listOrders = new ListView();
        listOrders.getItems().add("Order 1");
        listOrders.getItems().add("Order 2");
        listOrders.getItems().add("Order 3");

        Button btnSC_REMOVE = new Button("Remove");
        btnSC_REMOVE.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
//        btnSC_REMOVE.setOnAction(fn -> window.setScene(sceneMenu));

        // #
        VBox layoutSCTopRight = new VBox(10, labelYourOrders, listOrders, btnSC_REMOVE);
        
        // Button
        Button btnSC_DONE = new Button("Done");
        btnSC_DONE.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btnSC_DONE.setOnAction(fn -> window.setScene(sceneMenu));

        Button btnSC_CANCEL = new Button("Cancel");
        btnSC_CANCEL.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btnSC_CANCEL.setOnAction(fn -> window.setScene(sceneMenu));

        // #
        HBox layoutSCBottom = new HBox(10, btnSC_CANCEL, btnSC_DONE);
        layoutSCBottom.setAlignment(Pos.CENTER);

        GridPane layoutSC = new GridPane();
        GridPane.setConstraints(layoutSCTopLeft, 0, 0);
        GridPane.setConstraints(layoutSCTopRight, 1, 0);
        GridPane.setConstraints(layoutSCBottom, 0, 1, 2, 1);
        layoutSC.getChildren().addAll(layoutSCTopLeft, layoutSCTopRight, layoutSCBottom);
        
        sceneSC = new Scene(layoutSC, 1080, 828);
        // =====================================================================
        // EDIT RESTAURANT
        // =====================================================================
        // Restaurant Name
        Label labelRestaurantName = new Label("Name : ");

        TextArea txtareaRestaurantName = new TextArea();
        txtareaRestaurantName.setPrefHeight(txtareaRestaurantName.DEFAULT_PREF_ROW_COUNT);
        txtareaRestaurantName.setPrefWidth(500);
        txtareaRestaurantName.setPromptText("Enter restaurant name");

        // Restaurant Location
        Label labelRestaurantLoc = new Label("Restaurant Location : ");

        GridPane gridRestaurantLoc = new GridPane();
        gridRestaurantLoc.setPrefSize(700, 600);
        gridRestaurantLoc.setMaxSize(700, 600);
        for (int i = 0; i < masterMap.getWidth(); i++) {
            for (int j = 0; j < masterMap.getHeight(); j++) {
                Tile tile = new Tile(String.valueOf(masterMap.getSymbolAt(i, j)));
                GridPane.setConstraints(tile, i, j);
                gridRestaurantLoc.getChildren().addAll(tile);
            }
        }
        ScrollPane gridPad = new ScrollPane(gridRestaurantLoc);
        gridPad.setMaxSize(700, 600);

        // Dishes
        Label labelDishes = new Label("Dishes : ");

        Button btnER_EDs = new Button("Edit Dishes");
        btnER_EDs.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btnER_EDs.setOnAction(fn -> window.setScene(sceneEDs));

        // #
        GridPane layoutERTop = new GridPane();
        GridPane.setConstraints(labelRestaurantName, 0, 0);
        GridPane.setConstraints(txtareaRestaurantName, 1, 0);
        GridPane.setConstraints(labelRestaurantLoc, 0, 1);
        GridPane.setConstraints(gridPad, 1, 1);
        GridPane.setConstraints(labelDishes, 0, 2);
        GridPane.setConstraints(btnER_EDs, 1, 2);
        GridPane.setHalignment(labelRestaurantName, HPos.RIGHT);
        GridPane.setHalignment(labelRestaurantLoc, HPos.RIGHT);
        GridPane.setHalignment(labelDishes, HPos.RIGHT);
        layoutERTop.setVgap(10);
        layoutERTop.getChildren().addAll(labelRestaurantName, txtareaRestaurantName,
                labelRestaurantLoc, gridPad,
                labelDishes, btnER_EDs);

        layoutERTop.setAlignment(Pos.CENTER);

        // Button
        Button btnER_DONE = new Button("Done");
        btnER_DONE.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btnER_DONE.setOnAction(fn -> window.setScene(sceneMR));

        Button btnER_CANCEL = new Button("Cancel");
        btnER_CANCEL.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btnER_CANCEL.setOnAction(fn -> window.setScene(sceneMR));

        // #
        HBox layoutERBottom = new HBox(10, btnER_CANCEL, btnER_DONE);
        layoutERBottom.setAlignment(Pos.CENTER);

        // ##
        GridPane layoutER = new GridPane();
        GridPane.setConstraints(layoutERTop, 0, 0);
        GridPane.setConstraints(layoutERBottom, 0, 1);
        GridPane.setVgrow(layoutERTop, Priority.ALWAYS);
        GridPane.setHgrow(layoutERTop, Priority.ALWAYS);
        layoutER.setPadding(new Insets(10, 10, 10, 10));
        layoutER.getChildren().addAll(layoutERTop, layoutERBottom);

        sceneER = new Scene(layoutER, 1080, 828);
        // =====================================================================
        // EDIT DISHES
        // =====================================================================
        // Dish List
        ListView listDishes = new ListView();
        listDishes.getItems().add("Dish 1");
        listDishes.getItems().add("Dish 2");
        listDishes.getItems().add("Dish 3");

        // Buttons
        Button btnEDs_EDIT = new Button("Edit");
        btnEDs_EDIT.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btnEDs_EDIT.setOnAction(fn -> window.setScene(sceneED));
//        ObservableList selectedIndices = listView.getSelectionModel().getSelectedIndices();

        Button btnEDs_DELETE = new Button("Delete");
        btnEDs_DELETE.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
//        btnEDs_EDIT.setOnAction(fn -> window.setScene(sceneED));

        Button btnEDs_ADD = new Button("Add");
        btnEDs_ADD.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btnEDs_ADD.setOnAction(fn -> window.setScene(sceneED));

        Button btnEDs_DONE = new Button("Done");
        btnEDs_DONE.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btnEDs_DONE.setOnAction(fn -> window.setScene(sceneER));

        // #
        HBox layoutEDsBottom = new HBox(10, btnEDs_EDIT, btnEDs_DELETE, btnEDs_ADD, btnEDs_DONE);
        layoutEDsBottom.setAlignment(Pos.CENTER);

        // ##
        GridPane layoutEDs = new GridPane();
        GridPane.setVgrow(listDishes, Priority.ALWAYS);
        GridPane.setHgrow(listDishes, Priority.ALWAYS);
        GridPane.setConstraints(listDishes, 0, 0);
        GridPane.setConstraints(layoutEDsBottom, 0, 1);
        layoutEDs.setPadding(new Insets(10, 10, 10, 10));
        layoutEDs.setVgap(10);
        layoutEDs.getChildren().addAll(listDishes, layoutEDsBottom);

        sceneEDs = new Scene(layoutEDs, 1080, 828);
        // =====================================================================
        // EDIT DISH
        // =====================================================================
        // Dish Name
        Label labelDishName = new Label("Dish Name : ");

        TextArea txtareaDishName = new TextArea();
        txtareaDishName.setPrefHeight(txtareaDishName.DEFAULT_PREF_ROW_COUNT);
        txtareaDishName.setPrefWidth(500);
        txtareaDishName.setPromptText("Enter dish name");

        // Dish Prep Time
        Label labelDishPrepTime = new Label("Dish Preparation Time : ");

        Spinner spinnerDishPrepTime = new Spinner(1, 60, 5);
        spinnerDishPrepTime.setPrefWidth(500);
        spinnerDishPrepTime.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_HORIZONTAL);
        spinnerDishPrepTime.setEditable(true);

        // #
        GridPane layoutEDTop = new GridPane();
        GridPane.setConstraints(labelDishName, 0, 0);
        GridPane.setConstraints(txtareaDishName, 1, 0);
        GridPane.setConstraints(labelDishPrepTime, 0, 1);
        GridPane.setConstraints(spinnerDishPrepTime, 1, 1);
        GridPane.setHalignment(labelDishName, HPos.RIGHT);

        layoutEDTop.setVgap(10);
        layoutEDTop.setHgap(10);
        layoutEDTop.getChildren().addAll(labelDishName, txtareaDishName, labelDishPrepTime, spinnerDishPrepTime);
        layoutEDTop.setAlignment(Pos.CENTER);

        // Button
        Button btnED_DONE = new Button("Done");
        btnED_DONE.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btnED_DONE.setOnAction(fn -> window.setScene(sceneEDs));

        Button btnED_CANCEL = new Button("Cancel");
        btnED_CANCEL.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btnED_CANCEL.setOnAction(fn -> window.setScene(sceneEDs));

        // #
        HBox layoutEDBottom = new HBox(10, btnED_CANCEL, btnED_DONE);
        layoutEDBottom.setAlignment(Pos.CENTER);

        // ##
        GridPane layoutED = new GridPane();
        GridPane.setVgrow(layoutEDTop, Priority.ALWAYS);
        GridPane.setHgrow(layoutEDTop, Priority.ALWAYS);
        GridPane.setConstraints(layoutEDTop, 0, 0);
        GridPane.setConstraints(layoutEDBottom, 0, 1);
        layoutED.setPadding(new Insets(10, 10, 10, 10));
        layoutED.getChildren().addAll(layoutEDTop, layoutEDBottom);
        sceneED = new Scene(layoutED, 1080, 828);

        // =====================================================================
        window.setMinHeight(876);
        window.setMinWidth(802);
        window.setScene(sceneMenu);
        window.setTitle("CrabFood");
        window.show();
    }

    private class Tile extends StackPane {

        public Tile(String value) {
            Rectangle border = new Rectangle(50, 50);
            border.setFill(null);
            border.setStroke(Color.BLACK);

            Text text = new Text(value);
            text.setFont(Font.font(30));

            setAlignment(Pos.CENTER);
            getChildren().addAll(border, text);
        }
    }

}
