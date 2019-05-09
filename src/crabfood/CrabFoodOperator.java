package crabfood;

import static crabfood.Main.clock;
import crabfood.MyGoogleMap.Position;
import crabfood.Restaurant.Dish;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

class CrabFoodOperator {

    public static ArrayList<Restaurant> partnerRestaurants;
    public static MyGoogleMap masterMap;
    public static ArrayList<DeliveryGuy> allDeliveryGuys;
    public static ArrayBag<CrabFoodOrder> allCrabFoodOrders;

    public CrabFoodOperator() {

        // set partner partner restaurants
        CrabFoodOperator.partnerRestaurants = new ArrayList<>();
        readPartnerRestaurants();

        // set & update master map
        CrabFoodOperator.masterMap = new MyGoogleMap();
        masterMap.updateMap();

        // set all delivery guys
        CrabFoodOperator.allDeliveryGuys = new ArrayList<>();
        readAllDeliveryGuys();

        // set all Crabfood orders
        CrabFoodOperator.allCrabFoodOrders = new ArrayBag<>();
        readAllCrabFoodOrders();

        // populate txt for restaurant, crabfood orders and delivery guys
    }

    public static boolean isNumeric(String strNum) {
        try {
            Double.parseDouble(strNum);
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
        return true;
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
                while (s.hasNextLine()) {
                    String input = s.nextLine();

                    if (Pattern.matches("(\\s)*([0-9])+(\\s)+([0-9])+(\\s)*", input)) {
                        String[] coordinateStr = input.trim().split("\\s");
                        int posX = Integer.parseInt(coordinateStr[0]);
                        int posY = Integer.parseInt(coordinateStr[1]);
                        restaurantPositions.add(new Position(posX, posY));
                    } else {
                        if (!input.isEmpty()) {
                            dishes.add(restaurant.new Dish(input, Integer.parseInt(s.nextLine())));
                        } else {
                            break;
                        }
                    }
                }

                // after reading, set name, map symbol, positions & dishes
                restaurant.setName(restaurantName);
                restaurant.setMapSymbol(restaurantMapSymbol);
                restaurant.setPositions(restaurantPositions);
                restaurant.setAllAvailableDishes(dishes);

                partnerRestaurants.add(restaurant);
                System.out.println(restaurant.getMapSymbol());
                System.out.println(restaurant.getName());
                System.out.println(restaurant.getPositions());
                System.out.println(restaurant.getAllAvailableDishes().get(0).getName());
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

            for (int i = 0; i < numDeliveryGuy; i++) {
                DeliveryGuy deliveryGuy = new DeliveryGuy(i);
                allDeliveryGuys.add(deliveryGuy);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("\"delivery-guy.txt\" not found.");
        }
    }

    public static void readAllCrabFoodOrders() {
        try {
            Scanner s = new Scanner(new FileInputStream("crabfood-io/crabfood-order.txt"));

            while (s.hasNextLine()) {
                CrabFoodOrder crabFoodOrder = new CrabFoodOrder();

                // read order time
                String orderTime = SimulatedTime.parseTime(s.nextLine());

                // read restaurant
                String restaurant = s.nextLine();

                // read dish orders & quantity
                HashMap<Dish, Integer> dishOrders = new HashMap<>();
                
                // read delivery location
                Position deliveryLocation = new Position();
                
                while (s.hasNextLine()) {
                    String input = s.nextLine();
                    if (Pattern.matches("((\\s)*[A-Za-z]+(\\s)*)+((\\s)+[0-9]+(\\s)*)$", input)) {
                        String dishName = input.replaceFirst("[0-9]+(\\s)*$", "").trim();
                        int quanitity = Integer.parseInt(input.replaceAll("\\D+", ""));

                        dishOrders.put(new Restaurant(restaurant).new Dish(dishName), quanitity);
                    } else if (Pattern.matches("((\\s)*[A-Za-z]+(\\s)*)+", input)) {
                        dishOrders.put(new Restaurant(restaurant).new Dish(input.trim()), 1);
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
                crabFoodOrder.setRestaurant(new Restaurant(restaurant));
                crabFoodOrder.setDishOrders(dishOrders);
                crabFoodOrder.setDeliveryLocation(deliveryLocation);
                System.out.println(crabFoodOrder);
            }
        } catch (FileNotFoundException e) {
            System.out.println("\"crabfood-order.txt\" not found.");
        }
    }

    public static MyGoogleMap getMasterMap() {
        return masterMap;
    }

    public static void setMasterMap(MyGoogleMap masterMap) {
        CrabFoodOperator.masterMap = masterMap;
    }

    public ArrayList<Restaurant> getPartnerRestaurants() {
        return partnerRestaurants;
    }

    public void setPartnerRestaurants(ArrayList<Restaurant> partnerRestaurants) {
        CrabFoodOperator.partnerRestaurants = partnerRestaurants;
    }

    public ArrayBag<CrabFoodOrder> getAllCrabFoodOrders() {
        return allCrabFoodOrders;
    }

    public void setAllCrabFoodOrders(ArrayBag<CrabFoodOrder> allCrabFoodOrders) {
        CrabFoodOperator.allCrabFoodOrders = allCrabFoodOrders;
    }

    public ArrayList<DeliveryGuy> getAllDeliveryGuys() {
        return allDeliveryGuys;
    }

    public void setAllDeliveryGuys(ArrayList<DeliveryGuy> allDeliveryGuys) {
        CrabFoodOperator.allDeliveryGuys = allDeliveryGuys;
    }

    static class CrabFoodOrder {

        private static int customerId = 0;
        private String orderTime;
        private Restaurant restaurant;
        private HashMap<Dish, Integer> dishOrders;
        private Position deliveryLocation;

        public CrabFoodOrder(Restaurant restaurant, HashMap<Dish, Integer> dishOrders, Position deliveryLocation) {
            CrabFoodOrder.customerId++;
            this.orderTime = clock.getTime();
            this.restaurant = restaurant;
            this.dishOrders = dishOrders;
            this.deliveryLocation = deliveryLocation;
        }

        public CrabFoodOrder() {
            CrabFoodOrder.customerId++;
            this.orderTime = clock.getTime();
        }

        @Override
        public String toString() {
            String result = "";
            result += orderTime + "\n";
            result += restaurant.getName() + "\n";
            for (Map.Entry<Dish, Integer> entry : dishOrders.entrySet()) {
                result += entry.getKey().getName() + " ";
                result += entry.getValue() + "\n";
            }
            result += deliveryLocation + "\n";
            return result;
        }

        public static int getCustomerId() {
            return customerId;
        }

        public static void setCustomerId(int customerId) {
            CrabFoodOrder.customerId = customerId;
        }

        public String getOrderTime() {
            return orderTime;
        }

        public void setOrderTime(String orderTime) {
            this.orderTime = orderTime;
        }

        public Restaurant getRestaurant() {
            return restaurant;
        }

        public void setRestaurant(Restaurant restaurant) {
            this.restaurant = restaurant;
        }

        public HashMap<Dish, Integer> getDishOrders() {
            return dishOrders;
        }

        public void setDishOrders(HashMap<Dish, Integer> dishOrders) {
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
