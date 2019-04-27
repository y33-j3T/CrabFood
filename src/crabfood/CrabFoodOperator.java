package crabfood;

import crabfood.MyGoogleMap.Position;
import crabfood.Restaurant.Dish;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

class CrabFoodOperator {

    public static ArrayBag<Restaurant> partnerRestaurants;
    public static MyGoogleMap masterMap;
    public static ArrayList<DeliveryGuy> allDeliveryGuys;
    public static ArrayBag<CrabFoodOrder> allCrabFoodOrders;

    public CrabFoodOperator() {
        // load previously saved map
        masterMap = new MyGoogleMap();
        
        // load previously saved partner restaurants
        partnerRestaurants = new ArrayBag<>();
        try {
            Scanner s = new Scanner(new FileInputStream("crabfood-io/partner-restaurant.txt"));

            while (s.hasNextLine()) {
                Restaurant restaurant = new Restaurant();

                // read restaurant name
                String restaurantName = s.nextLine();

                // read restaurant positions & dishes
                ArrayBag<Position> restaurantPositions = new ArrayBag<>();
                ArrayBag<Dish> dishes = new ArrayBag<>();
                while (s.hasNextLine()) {
                    String input = s.nextLine();

                    if (Pattern.matches("(\\s)*([0-9])+(\\s)+([0-9])+(\\s)*", input)) {
                        String[] coordinateStr = input.trim().split("\\s");
                        int posX = Integer.parseInt(coordinateStr[0]);
                        int posY = Integer.parseInt(coordinateStr[1]);
                        restaurantPositions.add(masterMap.new Position(posX, posY));
                    } else {
                        if (!input.isEmpty()) {
                            dishes.add(restaurant.new Dish(input, Integer.parseInt(s.nextLine())));
                        } else {
                            break;
                        }
                    }
                }

                // after reading, set name, positions & dishes
                restaurant.setName(restaurantName);
                restaurant.setPositions(restaurantPositions);
                restaurant.setAllAvailableDishes(dishes);

                partnerRestaurants.add(restaurant);
            }

        } catch (FileNotFoundException e) {
            System.out.println("\"partner-restaurant.txt\" not found.");
        }
        
        // update map 
        // in case of changes to "partner-restaurant.txt" 
        // or in case of corrupted "map.txt"
        masterMap.updateMap();

        CrabFoodOperator.allDeliveryGuys = new ArrayList<>();
        CrabFoodOperator.allCrabFoodOrders = new ArrayBag<>();

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

    public static MyGoogleMap getMasterMap() {
        return masterMap;
    }

    public static void setMasterMap(MyGoogleMap masterMap) {
        CrabFoodOperator.masterMap = masterMap;
    }

    public ArrayBag<Restaurant> getPartnerRestaurants() {
        return partnerRestaurants;
    }

    public void setPartnerRestaurants(ArrayBag<Restaurant> partnerRestaurants) {
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

    class CrabFoodOrder {

        private SimulatedTime orderTime;
        private Restaurant restaurant;
        private HashMap<Restaurant.Dish, Integer> crabFoodOrders;
        private MyGoogleMap deliveryLocation;

        public CrabFoodOrder(SimulatedTime orderTime, Restaurant restaurant, HashMap<Restaurant.Dish, Integer> crabFoodOrders, MyGoogleMap deliveryLocation) {
            this.orderTime = orderTime;
            this.restaurant = restaurant;
            this.crabFoodOrders = crabFoodOrders;
            this.deliveryLocation = deliveryLocation;
        }

        public SimulatedTime getOrderTime() {
            return orderTime;
        }

        public void setOrderTime(SimulatedTime orderTime) {
            this.orderTime = orderTime;
        }

        public Restaurant getRestaurant() {
            return restaurant;
        }

        public void setRestaurant(Restaurant restaurant) {
            this.restaurant = restaurant;
        }

        public HashMap<Restaurant.Dish, Integer> getCrabFoodOrders() {
            return crabFoodOrders;
        }

        public void setCrabFoodOrders(HashMap<Restaurant.Dish, Integer> crabFoodOrders) {
            this.crabFoodOrders = crabFoodOrders;
        }

        public MyGoogleMap getDeliveryLocation() {
            return deliveryLocation;
        }

        public void setDeliveryLocation(MyGoogleMap deliveryLocation) {
            this.deliveryLocation = deliveryLocation;
        }
    }
}
