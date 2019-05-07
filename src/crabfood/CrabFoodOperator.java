package crabfood;

import crabfood.MyGoogleMap.Position;
import crabfood.Restaurant.Dish;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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

        // load previously saved "partner-restaurant.txt"
        partnerRestaurants = new ArrayList<>();
        try {
            Scanner s = new Scanner(new FileInputStream("crabfood-io/partner-restaurant.txt"));

            while (s.hasNextLine()) {
                Restaurant restaurant = new Restaurant();

                // read restaurant name
                String restaurantName = s.nextLine();

                // get restaurant map symbol
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

                // after reading, set name, positions & dishes
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

        /**
         * load previously saved "map.txt" & update it in case of changes to
         * "partner-restaurant.txt" or in case of corrupted "map.txt"
         */
        masterMap = new MyGoogleMap();
        System.out.println(masterMap);
//        masterMap.updateMap();

        // load previously saved "delivery-guy.txt"
        try {
            Scanner s = new Scanner(new FileInputStream("crabfood-io/delivery-guy.txt"));
            int numDeliveryGuy = 0;
            while(s.hasNextInt()) {
                numDeliveryGuy = s.nextInt();
            }
            CrabFoodOperator.allDeliveryGuys = new ArrayList<>(numDeliveryGuy);
        } catch (FileNotFoundException ex) {
            System.out.println("\"delivery-guy.txt\" not found.");
        }
        
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
