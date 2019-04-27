package crabfood;

import crabfood.MyGoogleMap.Position;
import crabfood.Restaurant.Dish;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

class CrabFoodOperator {

    public static ArrayList<Restaurant> partnerRestaurants;
    public static MyGoogleMap masterMap;
    public static ArrayList<DeliveryGuy> allDeliveryGuys;
    public static ArrayBag<CrabFoodOrder> allCrabFoodOrders;

    public CrabFoodOperator() {
        // load previously saved map
        masterMap = new MyGoogleMap();
        
        // load previously saved partner restaurants
        partnerRestaurants = new ArrayList<>();
        try {
            Scanner s = new Scanner(new FileInputStream("crabfood-io/partner-restaurant.txt"));

            while (s.hasNextLine()) {
                Restaurant restaurant = new Restaurant();

                // read restaurant name
                String restaurantName = s.nextLine();

                // read restaurant positions & dishes
                ArrayBag<Position> restaurantPositions = new ArrayBag<>();
                ArrayList<Dish> dishes = new ArrayList<>();
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
                System.out.println(restaurant.getName());
                System.out.println(restaurant.getPositions());
                System.out.println(restaurant.getAllAvailableDishes().get(0).getName());
            }

        } catch (FileNotFoundException e) {
            System.out.println("\"partner-restaurant.txt\" not found.");
        }
        
        // update map 
        // in case of changes to "partner-restaurant.txt" 
        // or in case of corrupted "map.txt"
//        masterMap.updateMap();
        
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

    class CrabFoodOrder {

        private int customerId;
        private SimulatedTime orderTime;
        private Restaurant restaurant;
        private HashMap<Dish, Integer> crabFoodOrders;
        private Position deliveryLocation;
        

        
    }
}
