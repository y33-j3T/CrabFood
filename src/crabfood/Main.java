package crabfood;

import crabfood.CrabFoodOperator.CrabFoodOrder;
import crabfood.MyGoogleMap.Position;
import crabfood.Restaurant.Dish;
import java.util.HashMap;
import java.util.Map;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
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

    public volatile static SimulatedTime clock = new SimulatedTime();
    public static CrabFoodOperator operator = new CrabFoodOperator();

    public static void main(String[] args) {
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

        Thread timeThread = new Thread(new Runnable() {

            @Override
            public void run() {
                Runnable updater = new Runnable() {

                    @Override
                    public void run() {
                        if (clock.getTime().equals("00:00")) {
                            // update process
                            CrabFoodOperator.getProcess().set(clock.getTime() + " A new day has started!");

                            // update log
                            String logHeader = String.format("\n| %11s | %10s | %21s | %14s | %16s | %16s | %10s | %6s | %4s",
                                    "Customer ID", "Order Time", "Finished Cooking Time",
                                    "Delivered Time", "Cooking Duration", "Deliver Duration",
                                    "Restaurant", "Branch", "Dish");
                            CrabFoodOperator.appendToLog(logHeader);
                        }
                        clock.tick();
                    }
                };

                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                    }

                    Platform.runLater(updater);
                }
            }

        });
        timeThread.setDaemon(true);
        timeThread.start();

        // MENU
        makeSceneMenu(primaryStage);

        // MANAGE RESTAURANT
        makeSceneMR(primaryStage);

        // MANAGE DELIVERY
        makeSceneMD(primaryStage);

        // VIEW ORDER LOG
        makeSceneVOL(primaryStage);

        // SIMULATE CUSTOMER
        makeSceneSC(primaryStage);

        // EDIT RESTAURANT
        makeSceneER(primaryStage);

        // EDIT DISHES
        makeSceneEDs(primaryStage);

        // EDIT DISH
        makeSceneED(primaryStage);

        // primary stage property
        primaryStage.setMinHeight(876);
        primaryStage.setMinWidth(802);
        primaryStage.setScene(sceneMenu);
        primaryStage.setTitle("CrabFood");
//        primaryStage.setOnCloseRequest(fn -> {});
        primaryStage.show();
    }

    private void makeSceneMenu(Stage primaryStage) {
        // Manage Restaurants, Manage Delivery, View Order Log
        Button btnMR = new Button("Manage Restaurants");
        btnMR.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btnMR.setOnAction(fn -> primaryStage.setScene(sceneMR));

        Button btnMD = new Button("Manage Delivery");
        btnMD.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btnMD.setOnAction(fn -> primaryStage.setScene(sceneMD));

        Button btnVOL = new Button("View Order Log");
        btnVOL.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btnVOL.setOnAction(fn -> primaryStage.setScene(sceneVOL));

        Button btnSC = new Button("Simulate Customer");
        btnSC.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btnSC.setOnAction(fn -> primaryStage.setScene(sceneSC));

        // #
        VBox layoutMenuLeft = new VBox(10, btnMR, btnMD, btnVOL, btnSC);

        // Process Log
        TextArea txtareaPL = new TextArea();
        txtareaPL.setMinSize(500, 400);
        txtareaPL.setEditable(false);
        txtareaPL.textProperty().bind(CrabFoodOperator.getProcess());

        // Order Status
        TableColumn<CrabFoodOrder, Integer> colCustomerId = new TableColumn<>("Customer ID");
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));

        TableColumn<CrabFoodOrder, String> colOrderTime = new TableColumn<>("Order Time");
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
    }

    private void makeSceneMR(Stage primaryStage) {
        // Restaurant List
        ListView listRestaurant = new ListView();
        listRestaurant.getItems().add("restaurant 1");
        listRestaurant.getItems().add("restaurant 2");
        listRestaurant.getItems().add("restaurant 3");

        // Buttons
        Button btnMR_EDIT = new Button("Edit");
        btnMR_EDIT.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btnMR_EDIT.setOnAction(fn -> primaryStage.setScene(sceneER));
//        ObservableList selectedIndices = listView.getSelectionModel().getSelectedIndices();

        Button btnMR_DELETE = new Button("Delete");
        btnMR_DELETE.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
//        btnMR_EDIT.setOnAction(fn -> primaryStage.setScene(sceneED));

        Button btnMR_ADD = new Button("Add");
        btnMR_ADD.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btnMR_ADD.setOnAction(fn -> primaryStage.setScene(sceneER));

        Button btnMR_DONE = new Button("Done");
        btnMR_DONE.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btnMR_DONE.setOnAction(fn -> primaryStage.setScene(sceneMenu));

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
    }

    private void makeSceneMD(Stage primaryStage) {
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
        btnMD_DONE.setOnAction(fn -> primaryStage.setScene(sceneMenu));

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
    }

    private void makeSceneVOL(Stage primaryStage) {
        // Order Log
        TextArea txtareaOrderLog = new TextArea();
        txtareaOrderLog.setEditable(false);
        txtareaOrderLog.textProperty().bind(CrabFoodOperator.getLog());

        // Button
        Button btnVOL_BACK = new Button("Back");
        btnVOL_BACK.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btnVOL_BACK.setOnAction(fn -> primaryStage.setScene(sceneMenu));

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
    }

    private void makeSceneSC(Stage primaryStage) {
        // Ordered dish & its quantity to be put into tableSC
        ObservableMap<String, Integer> mapSC = FXCollections.observableHashMap();
        ObservableList<String> mapSCkeys = FXCollections.observableArrayList();

        mapSC.addListener((MapChangeListener.Change<? extends String, ? extends Integer> change) -> {
            boolean removed = change.wasRemoved();
            if (removed != change.wasAdded()) {
                // no put for existing key
                if (removed) {
                    mapSCkeys.remove(change.getKey());
                } else {
                    mapSCkeys.add(change.getKey());
                }
            }
        });

        // Your Orders
        Label labelYourOrders = new Label("Your orders");
        // 
        TableColumn<String, String> colSCDish = new TableColumn<>("Dish");
        colSCDish.setCellValueFactory(cd -> Bindings.createStringBinding(() -> cd.getValue()));

        TableColumn<String, Integer> colSCQuantity = new TableColumn<>("Quantity");
        colSCQuantity.setCellValueFactory(cd -> Bindings.valueAt(mapSC, cd.getValue()));

        TableView<String> tableSC = new TableView<>(mapSCkeys);
        tableSC.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableSC.getColumns().setAll(colSCDish, colSCQuantity);
        //
        Button btnSC_REMOVE = new Button("Remove");
        btnSC_REMOVE.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btnSC_REMOVE.setOnAction(fn -> {
            mapSC.remove(tableSC.getSelectionModel().getSelectedItem());
        });

        // #
        VBox layoutSCTopRight = new VBox(10, labelYourOrders, tableSC, btnSC_REMOVE);
        layoutSCTopRight.setAlignment(Pos.CENTER);
        VBox.setVgrow(tableSC, Priority.ALWAYS);

        // Customer ID
        Label labelCustomerID = new Label("Customer ID : ");

        Text txtCustomerID = new Text();
        int customerId = CrabFoodOrder.getCustomerCount().getValue() + 1;
        txtCustomerID.setText(String.valueOf(customerId));
        CrabFoodOrder.getCustomerCount().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                int customerId = CrabFoodOrder.getCustomerCount().getValue() + 1;
                txtCustomerID.setText(String.valueOf(customerId));
            }
        });

        // Order Time
        Label labelOrderTime = new Label("Order Time : ");

        Text txtOrderTime = new Text();
        Thread txtOrderTimeThread = new Thread(new Runnable() {

            @Override
            public void run() {
                Runnable updater = new Runnable() {

                    @Override
                    public void run() {
                        txtOrderTime.setText(clock.getTime());
                    }
                };

                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                    }

                    // UI update is run on the Application thread
                    Platform.runLater(updater);
                }
            }

        });
        txtOrderTimeThread.setDaemon(true);
        txtOrderTimeThread.start();

        // Restaurant
        Label labelRestaurant = new Label("Restaurant : ");

        ComboBox comboRestaurant = new ComboBox();
        comboRestaurant.setPromptText("Pick a restaurant");
        comboRestaurant.setPrefSize(450, 10);
        for (Restaurant restaurant : operator.getPartnerRestaurants()) {
            comboRestaurant.getItems().add(restaurant.getName());
        }

        // Dish & Quantity
        Label labelDish = new Label("Dish : ");

        ComboBox comboDish = new ComboBox();
        comboDish.setPromptText("Pick a dish");
        comboDish.setPrefSize(450, 10);

        Label labelQuantity = new Label("Quantity : ");

        Spinner spinnerQuantity = new Spinner(1, 20, 1);
        spinnerQuantity.setEditable(true);
        spinnerQuantity.setPrefSize(450, 10);

        Button btnSC_ADD = new Button("Add");
        btnSC_ADD.setPrefSize(75, 75);
        btnSC_ADD.setOnAction(fn -> {
            if (comboDish.getValue() != null) {
                mapSC.put(comboDish.getValue().toString(),
                        Integer.parseInt(spinnerQuantity.getValue().toString()));
            }
        });

        // listeners
        comboRestaurant.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                // reset dish
                if (comboRestaurant.getSelectionModel().getSelectedItem() != null) {
                    for (Restaurant restaurant : CrabFoodOperator.getPartnerRestaurants()) {
                        if (comboRestaurant.getSelectionModel().getSelectedItem().toString().equals(restaurant.getName())) {
                            comboDish.getItems().clear();
                            for (Dish dish : restaurant.getAllAvailableDishes()) {
                                comboDish.getItems().add(dish.getName());
                            }
                        }
                    }
                }

                //reset spinner
                spinnerQuantity.getValueFactory().setValue(1);

                //clear order hash map
                mapSC.clear();
            }
        });

        comboDish.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                //reset spinner
                if (mapSC.containsKey(comboDish.getSelectionModel().getSelectedItem())) {
                    spinnerQuantity.getValueFactory().setValue(mapSC.get(comboDish.getSelectionModel().getSelectedItem()));
                } else {
                    spinnerQuantity.getValueFactory().setValue(1);
                }
            }
        });

        // Delivery Location
        Label labelDeliveryLoc = new Label("Delivery Location : ");
        Label labelX = new Label("X : ");
        Spinner spinnerX = new Spinner(1, 100, 1);
        spinnerX.setEditable(true);
        Label labelY = new Label("Y : ");
        Spinner spinnerY = new Spinner(1, 100, 1);
        spinnerY.setEditable(true);

        HBox coordinateLabels = new HBox(10, labelX, spinnerX, labelY, spinnerY);
        coordinateLabels.setAlignment(Pos.CENTER);

        // #
        GridPane layoutSCTopLeft = new GridPane();
        GridPane.setConstraints(labelCustomerID, 0, 0, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(txtCustomerID, 1, 0);
        GridPane.setConstraints(labelOrderTime, 0, 1, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(txtOrderTime, 1, 1);
        GridPane.setConstraints(labelRestaurant, 0, 2, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(comboRestaurant, 1, 2);
        GridPane.setConstraints(labelDish, 0, 3, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(comboDish, 1, 3);
        GridPane.setConstraints(btnSC_ADD, 2, 3, 1, 2);
        GridPane.setConstraints(labelQuantity, 0, 4, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(spinnerQuantity, 1, 4);
        GridPane.setConstraints(labelDeliveryLoc, 0, 5, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(coordinateLabels, 1, 5);
        layoutSCTopLeft.getChildren().addAll(labelCustomerID, txtCustomerID,
                labelOrderTime, txtOrderTime,
                labelRestaurant, comboRestaurant,
                labelDish, comboDish, btnSC_ADD,
                labelQuantity, spinnerQuantity,
                labelDeliveryLoc, coordinateLabels);
        layoutSCTopLeft.setVgap(10);
        layoutSCTopLeft.setHgap(10);
        layoutSCTopLeft.setAlignment(Pos.CENTER);

        // Button
        Button btnSC_DONE = new Button("Done");
        btnSC_DONE.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btnSC_DONE.setOnAction(fn -> {
            if (comboRestaurant.getSelectionModel().getSelectedItem() != null && !mapSC.isEmpty()) {
                primaryStage.setScene(sceneMenu);

                // add crabfood order to all crabfood orders
                CrabFoodOrder crabFoodOrder = new CrabFoodOrder();
                crabFoodOrder.setRestaurantName(comboRestaurant.getSelectionModel().getSelectedItem().toString());
                HashMap<String, Integer> dishOrders = new HashMap<>();
                dishOrders.putAll(mapSC);
                crabFoodOrder.setDishOrders(dishOrders);
                crabFoodOrder.setDeliveryLocation(new Position(
                        Integer.parseInt(spinnerX.getValue().toString()),
                        Integer.parseInt(spinnerY.getValue().toString())));
                CrabFoodOperator.getAllCrabFoodOrders().add(crabFoodOrder);

                // add order to process
                String processOrder = String.format("Customer %d wants to order ", CrabFoodOrder.getCustomerCount().getValue());
                int count = 0;
                for (Map.Entry mapElement : dishOrders.entrySet()) {
                    if (count == dishOrders.size() - 1) {
                        processOrder += dishOrders.size() == 1 ? "" : " & ";
                        processOrder += mapElement.getValue() + " " + mapElement.getKey() + " ";
                    } else {
                        processOrder += mapElement.getValue() + " " + mapElement.getKey();
                        processOrder += count == dishOrders.size()-2 ? "" : ", ";
                    }
                    count++;
                }
                processOrder += "from " + comboRestaurant.getSelectionModel().getSelectedItem().toString() + ".";
                CrabFoodOperator.appendToProcess(processOrder);
                
                // add restaurant-to-handle-order to process
                

                // reset all components
                comboRestaurant.getSelectionModel().clearSelection();
                comboDish.getSelectionModel().clearSelection();
                spinnerQuantity.getValueFactory().setValue(1);
                spinnerX.getValueFactory().setValue(1);
                spinnerY.getValueFactory().setValue(1);
                mapSC.clear();
            }
        });

        Button btnSC_CANCEL = new Button("Cancel");
        btnSC_CANCEL.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btnSC_CANCEL.setOnAction(fn -> {
            primaryStage.setScene(sceneMenu);
            comboRestaurant.getSelectionModel().clearSelection();
            comboDish.getSelectionModel().clearSelection();
            spinnerQuantity.getValueFactory().setValue(1);
            spinnerX.getValueFactory().setValue(1);
            spinnerY.getValueFactory().setValue(1);
            mapSC.clear();
        });

        // #
        HBox layoutSCBottom = new HBox(10, btnSC_CANCEL, btnSC_DONE);
        layoutSCBottom.setAlignment(Pos.CENTER);

        GridPane layoutSC = new GridPane();
        GridPane.setConstraints(layoutSCTopLeft, 0, 0);
        GridPane.setConstraints(layoutSCTopRight, 1, 0);
        GridPane.setConstraints(layoutSCBottom, 0, 1, 2, 1);
        GridPane.setHgrow(layoutSCTopLeft, Priority.ALWAYS);
        GridPane.setVgrow(layoutSCTopLeft, Priority.ALWAYS);
        GridPane.setHgrow(layoutSCTopRight, Priority.ALWAYS);
        GridPane.setVgrow(layoutSCTopRight, Priority.ALWAYS);
        layoutSC.setVgap(10);
        layoutSC.setHgap(10);
        layoutSC.setPadding(new Insets(10, 10, 10, 10));
        layoutSC.getChildren().addAll(layoutSCTopLeft, layoutSCTopRight, layoutSCBottom);

        sceneSC = new Scene(layoutSC, 1080, 828);
    }

    private void makeSceneER(Stage primaryStage) {
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
        for (int i = 0; i < CrabFoodOperator.getMasterMap().getWidth(); i++) {
            for (int j = 0; j < CrabFoodOperator.getMasterMap().getHeight(); j++) {
                Tile tile = new Tile(String.valueOf(CrabFoodOperator.getMasterMap().getSymbolAt(i, j)));
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
        btnER_EDs.setOnAction(fn -> primaryStage.setScene(sceneEDs));

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
        btnER_DONE.setOnAction(fn -> primaryStage.setScene(sceneMR));

        Button btnER_CANCEL = new Button("Cancel");
        btnER_CANCEL.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btnER_CANCEL.setOnAction(fn -> primaryStage.setScene(sceneMR));

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
    }

    private void makeSceneEDs(Stage primaryStage) {
        // Dish List
        ListView listDishes = new ListView();
        listDishes.getItems().add("Dish 1");
        listDishes.getItems().add("Dish 2");
        listDishes.getItems().add("Dish 3");

        // Buttons
        Button btnEDs_EDIT = new Button("Edit");
        btnEDs_EDIT.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btnEDs_EDIT.setOnAction(fn -> primaryStage.setScene(sceneED));
//        ObservableList selectedIndices = listView.getSelectionModel().getSelectedIndices();

        Button btnEDs_DELETE = new Button("Delete");
        btnEDs_DELETE.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
//        btnEDs_EDIT.setOnAction(fn -> primaryStage.setScene(sceneED));

        Button btnEDs_ADD = new Button("Add");
        btnEDs_ADD.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btnEDs_ADD.setOnAction(fn -> primaryStage.setScene(sceneED));

        Button btnEDs_DONE = new Button("Done");
        btnEDs_DONE.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btnEDs_DONE.setOnAction(fn -> primaryStage.setScene(sceneER));

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
    }

    private void makeSceneED(Stage primaryStage) {
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
        btnED_DONE.setOnAction(fn -> primaryStage.setScene(sceneEDs));

        Button btnED_CANCEL = new Button("Cancel");
        btnED_CANCEL.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btnED_CANCEL.setOnAction(fn -> primaryStage.setScene(sceneEDs));

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
