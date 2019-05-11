package crabfood;

import static crabfood.Main.clock;
import crabfood.MyGoogleMap.Position;
import crabfood.Restaurant.Dish;
import crabfood.Restaurant.RestaurantOrder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

class CrabFoodOperator {

    private static ArrayList<Restaurant> partnerRestaurants;
    private static MyGoogleMap masterMap;
    private static ArrayList<DeliveryGuy> allDeliveryGuys;
    private static ArrayList<CrabFoodOrder> allCrabFoodOrders;
    private static StringProperty log;
    private static StringProperty process;

    public CrabFoodOperator() {
        // set partner partner restaurants (read now & update later)
        CrabFoodOperator.partnerRestaurants = new ArrayList<>();
        readPartnerRestaurants();

        // set & update master map (read now & update now)
        CrabFoodOperator.masterMap = new MyGoogleMap();
        masterMap.updateMap();

        // set all delivery guys (read now & update later)
        CrabFoodOperator.allDeliveryGuys = new ArrayList<>();
        readAllDeliveryGuys();

        // set all Crabfood orders (read now | update later)
        CrabFoodOperator.allCrabFoodOrders = new ArrayList<>();
//        readAllCrabFoodOrders();

        // set log (read now & update later)
        log = new SimpleStringProperty("");
        readLog();

        // set process (update later)
        process = new SimpleStringProperty("");
    }

//    public static boolean isNumeric(String strNum) {
//        try {
//            Double.parseDouble(strNum);
//        } catch (NumberFormatException | NullPointerException e) {
//            return false;
//        }
//        return true;
//    }
    /**
     * Allocate CrabFood orders to restaurants by distance
     */
    public static void allocateOrderByDistance(CrabFoodOrder order) {
        // find closest branch
        int smallestDistance = Integer.MAX_VALUE;
        for (Restaurant restaurant : CrabFoodOperator.getPartnerRestaurants()) {
            if (order.getRestaurantName().equals(restaurant.getName())) {
                int distance = Math.abs(order.getDeliveryLocation().getPosX() - restaurant.getPosition().getPosX())
                        + Math.abs(order.getDeliveryLocation().getPosY() - restaurant.getPosition().getPosY());
                if (smallestDistance > distance) {
                    smallestDistance = distance;
                }
            }
        }

        // allocate to branch
        for (Restaurant restaurant : CrabFoodOperator.getPartnerRestaurants()) {
            if (order.getRestaurantName().equals(restaurant.getName())) {
                int distance = Math.abs(order.getDeliveryLocation().getPosX() - restaurant.getPosition().getPosX())
                        + Math.abs(order.getDeliveryLocation().getPosY() - restaurant.getPosition().getPosY());
                if (smallestDistance == distance) {
                    // make allocation
                    String endTime = SimulatedTime.parseTimeToSimulatedTime(order.getOrderTime()).getTimeAfter(order.getCookTime());
                    restaurant.getAllRestaurantOrders().add(restaurant.new RestaurantOrder(order.getOrderTime(), endTime));

                    // update process
                    CrabFoodOperator.appendToProcess("Branch of "
                            + restaurant.getName() + " at "
                            + restaurant.getPosition() + " take the order.");

                    break;
                    /**
                     * if one or more branch have same distance, maybe we could
                     * allocate by time, but for now, just break loop
                     */
                }
            }
        }
    }

    /**
     * Add new strings to process
     *
     * @param lineToAppend
     */
    public static void appendToProcess(String lineToAppend) {
        // append internally to log
        process.set(process.concat("\n" + clock.getTime() + " " + lineToAppend).get());
    }

    /**
     * load previously saved "log.txt"
     */
    public static void readLog() {
        try {
            Scanner s = new Scanner(new FileInputStream("crabfood-io/log.txt"));
            while (s.hasNextLine()) {
                log.set(log.concat(s.nextLine() + "\n").get());
            }
        } catch (FileNotFoundException ex) {
            System.out.println("\"log.txt\" not found.");
        }
    }

    /**
     * Add new strings to log file
     *
     * @param lineToAppend
     */
    public static void appendToLog(String lineToAppend) {
        // append internally to log
        log.set(log.concat(lineToAppend + "\n").get());

        // append externally to "log.txt"
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new FileOutputStream("crabfood-io/log.txt", true));
            pw.print(lineToAppend);
        } catch (FileNotFoundException ex) {
            System.out.println("\"log.txt\" not found.");
        } finally {
            pw.close();
        }
    }

    /**
     * load previously saved "partner-restaurant.txt"
     */
    public static void readPartnerRestaurants() {
        try {
            Scanner s = new Scanner(new FileInputStream("crabfood-io/partner-restaurant.txt"));

            while (s.hasNextLine()) {
                Restaurant restaurant = new Restaurant();

                // read restaurant name
                String restaurantName = s.nextLine();

                // read restaurant map symbol
                Character restaurantMapSymbol = restaurantName.charAt(0);

                // read restaurant positions & dishes
                ArrayList<Position> restaurantPositions = new ArrayList<>();
                ArrayList<Dish> dishes = new ArrayList<>();
                int posCount = 0;
                while (s.hasNextLine()) {
                    String input = s.nextLine();

                    if (Pattern.matches("(\\s)*([0-9])+(\\s)+([0-9])+(\\s)*", input)) {
                        String[] coordinateStr = input.trim().split("\\s");
                        int posX = Integer.parseInt(coordinateStr[0]);
                        int posY = Integer.parseInt(coordinateStr[1]);
                        restaurantPositions.add(new Position(posX, posY));
                        posCount++;
                    } else {
                        if (!input.isEmpty()) {
                            dishes.add(restaurant.new Dish(input, Integer.parseInt(s.nextLine())));
                        } else {
                            break;
                        }
                    }
                }

                // after reading, set name, map symbol, positions & dishes
                for (int i = 0; i < posCount; i++) {
                    partnerRestaurants.add(new Restaurant(restaurantMapSymbol,
                            restaurantName, restaurantPositions.get(i),
                            (ArrayList<Dish>) dishes.clone()));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("\"partner-restaurant.txt\" not found.");
        }
    }

    /**
     * load previously saved "delivery-guy.txt"
     */
    public static void readAllDeliveryGuys() {
        try {
            Scanner s = new Scanner(new FileInputStream("crabfood-io/delivery-guy.txt"));

            int numDeliveryGuy = 0;
            while (s.hasNextInt()) {
                numDeliveryGuy = s.nextInt();
            }

            for (int i = 1; i <= numDeliveryGuy; i++) {
                DeliveryGuy deliveryGuy = new DeliveryGuy(i);
                allDeliveryGuys.add(deliveryGuy);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("\"delivery-guy.txt\" not found.");
        }
    }

    /**
     * Update "delivery-guy.txt"
     */
    public static void updateAllDeliveryGuys() {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new FileOutputStream("crabfood-io/delivery-guy.txt"));
            pw.print(allDeliveryGuys.size());
        } catch (FileNotFoundException ex) {
            System.out.println("\"log.txt\" not found.");
        } finally {
            pw.close();
        }
    }

    /**
     * Load preset CrabFood orders from "crabfood-order.txt" Time must be sorted
     */
    public static void readAllCrabFoodOrders() {
        try {
            Scanner s = new Scanner(new FileInputStream("crabfood-io/preset-crabfood-order.txt"));

            while (s.hasNextLine()) {
                CrabFoodOrder crabFoodOrder = new CrabFoodOrder();

                // read order time
                String orderTime = SimulatedTime.parseTimeToString(s.nextLine());

                // read restaurant
                String restaurantName = s.nextLine();

                // read dish orders & quantity
                HashMap<String, Integer> dishOrders = new HashMap<>();

                // read delivery location
                Position deliveryLocation = new Position();

                while (s.hasNextLine()) {
                    String input = s.nextLine();
                    if (Pattern.matches("((\\s)*[A-Za-z]+(\\s)*)+((\\s)+[0-9]+(\\s)*)$", input)) {
                        String dishName = input.replaceFirst("[0-9]+(\\s)*$", "").trim();
                        int quanitity = Integer.parseInt(input.replaceAll("\\D+", ""));

                        dishOrders.put(dishName, quanitity);
                    } else if (Pattern.matches("((\\s)*[A-Za-z]+(\\s)*)+", input)) {
                        dishOrders.put(input.trim(), 1);
                    } else if (Pattern.matches("(\\s)*([0-9])+(\\s)+([0-9])+(\\s)*", input)) {
                        String[] coordinateStr = input.trim().split("\\s");
                        int posX = Integer.parseInt(coordinateStr[0]);
                        int posY = Integer.parseInt(coordinateStr[1]);
                        deliveryLocation.setPosition(posX, posY);
                    } else if (input.isEmpty()) {
                        break;
                    }
                }

                crabFoodOrder.setOrderTime(orderTime);
                crabFoodOrder.setRestaurantName(restaurantName);
                crabFoodOrder.setDishOrders(dishOrders);
                crabFoodOrder.setDeliveryLocation(deliveryLocation);

                allCrabFoodOrders.add(crabFoodOrder);
            }
        } catch (FileNotFoundException e) {
            System.out.println("\"crabfood-order.txt\" not found.");
        }
    }

    public static StringProperty getProcess() {
        return process;
    }

    public static void setProcess(StringProperty process) {
        CrabFoodOperator.process = process;
    }

    public static StringProperty getLog() {
        return log;
    }

    public static void setLog(StringProperty log) {
        CrabFoodOperator.log = log;
    }

    public static MyGoogleMap getMasterMap() {
        return masterMap;
    }

    public static void setMasterMap(MyGoogleMap masterMap) {
        CrabFoodOperator.masterMap = masterMap;
    }

    public static ArrayList<Restaurant> getPartnerRestaurants() {
        return partnerRestaurants;
    }

    public static void setPartnerRestaurants(ArrayList<Restaurant> partnerRestaurants) {
        CrabFoodOperator.partnerRestaurants = partnerRestaurants;
    }

    public static ArrayList<CrabFoodOrder> getAllCrabFoodOrders() {
        return allCrabFoodOrders;
    }

    public static void setAllCrabFoodOrders(ArrayList<CrabFoodOrder> allCrabFoodOrders) {
        CrabFoodOperator.allCrabFoodOrders = allCrabFoodOrders;
    }

    public static ArrayList<DeliveryGuy> getAllDeliveryGuys() {
        return allDeliveryGuys;
    }

    public static void setAllDeliveryGuys(ArrayList<DeliveryGuy> allDeliveryGuys) {
        CrabFoodOperator.allDeliveryGuys = allDeliveryGuys;
    }

    static class CrabFoodOrder {

        private static IntegerProperty customerCount = new SimpleIntegerProperty(0);
        private Integer customerId = customerCount.getValue() + 1;
        private String orderTime;
        private String restaurantName;
        private HashMap<String, Integer> dishOrders;
        private Position deliveryLocation;
        private String[] status = {"New Order", "Preparing...", "Delivering...", "Delivered"};
        private int cookTime;

        public CrabFoodOrder(String restaurantName, HashMap<String, Integer> dishOrders, Position deliveryLocation) {
            this.customerCount.set(customerCount.getValue() + 1);
            this.customerId = customerCount.getValue() + 1;
            this.orderTime = clock.getTime();
            this.restaurantName = restaurantName;
            this.dishOrders = dishOrders;
            this.deliveryLocation = deliveryLocation;
        }

        public CrabFoodOrder() {
            this.customerCount.set(customerCount.getValue() + 1);
            this.customerId = customerCount.getValue() + 1;
            this.orderTime = clock.getTime();
        }

        public int calculateCookTime() {
            int duration = -1;
            for (Restaurant restaurant : CrabFoodOperator.getPartnerRestaurants()) {
                if (restaurantName.equals(restaurant.getName())) {
                    for (Map.Entry dish : dishOrders.entrySet()) {
                        int timeNeeded = restaurant.getCookTime(dish.getKey().toString()) * Integer.parseInt(dish.getValue().toString());
                        if (duration < timeNeeded) {
                            duration = timeNeeded;
                        }
                        duration = duration < timeNeeded ? timeNeeded : duration;
                    }
                    break;
                }
            }
            return duration;
        }

        public int getCookTime() {
            return cookTime;
        }

        public void setCookTime(int cookTime) {
            this.cookTime = cookTime;
        }

        public String[] getStatus() {
            return status;
        }

        public void setStatus(String[] status) {
            this.status = status;
        }

        public String toString() {
            String result = "";
            result += customerId + "\n";
            result += orderTime + "\n";
            result += restaurantName + "\n";
            for (Map.Entry<String, Integer> entry : dishOrders.entrySet()) {
                result += entry.getKey() + " ";
                result += entry.getValue() + "\n";
            }
            result += deliveryLocation + "\n";
            return result;
        }

        /**
         * Just in case if need to write to "preset-crabfood-order.txt"
         *
         * @return CrabFoodOrder string
         */
        public String toTxtString() {
            String result = "";
            result += orderTime + "\n";
            result += restaurantName + "\n";
            for (Map.Entry<String, Integer> entry : dishOrders.entrySet()) {
                result += entry.getKey() + " ";
                result += entry.getValue() + "\n";
            }
            result += deliveryLocation + "\n";
            return result;
        }

        public Integer getCustomerId() {
            return customerId;
        }

        public void setCustomerId(Integer customerId) {
            this.customerId = customerId;
        }

        public static IntegerProperty getCustomerCount() {
            return customerCount;
        }

        public static void setCustomerCount(IntegerProperty customerCount) {
            CrabFoodOrder.customerCount = customerCount;
        }

        public String getOrderTime() {
            return orderTime;
        }

        public void setOrderTime(String orderTime) {
            this.orderTime = orderTime;
        }

        public String getRestaurantName() {
            return restaurantName;
        }

        public void setRestaurantName(String restaurantName) {
            this.restaurantName = restaurantName;
        }

        public HashMap<String, Integer> getDishOrders() {
            return dishOrders;
        }

        public void setDishOrders(HashMap<String, Integer> dishOrders) {
            this.dishOrders = dishOrders;
        }

        public Position getDeliveryLocation() {
            return deliveryLocation;
        }

        public void setDeliveryLocation(Position deliveryLocation) {
            this.deliveryLocation = deliveryLocation;
        }

    }
}
