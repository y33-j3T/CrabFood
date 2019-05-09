package crabfood;

import crabfood.MyGoogleMap.Position;
import java.util.ArrayList;
import java.util.HashMap;

class Restaurant {

    private String name;
    private Character mapSymbol;
    private ArrayList<Position> positions;
    private ArrayList<Dish> allAvailableDishes;
    private ArrayBag<restaurantOrder> allRestaurantOrders;

    public Restaurant() {
        this.mapSymbol = '#';
        this.name = "name not set";
    }

    public Restaurant(String name) {
        this.name = name;
    }
    
    public Restaurant(Character mapSymbol, String name, ArrayList<Position> positions, ArrayList<Dish> allAvailableDishes, ArrayBag<restaurantOrder> allRestaurantOrders) {
        this.mapSymbol = mapSymbol;
        this.name = name;
        this.positions = positions;
        this.allAvailableDishes = allAvailableDishes;
        this.allRestaurantOrders = allRestaurantOrders;
    }
    
    public Character getMapSymbol() {
        return mapSymbol;
    }

    public void setMapSymbol(Character mapSymbol) {
        this.mapSymbol = mapSymbol;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Position> getPositions() {
        return positions;
    }

    public void setPositions(ArrayList<Position> positions) {
        this.positions = positions;
    }

    public ArrayList<Dish> getAllAvailableDishes() {
        return allAvailableDishes;
    }

    public void setAllAvailableDishes(ArrayList<Dish> allAvailableDishes) {
        this.allAvailableDishes = allAvailableDishes;
    }

    public ArrayBag<restaurantOrder> getAllRestaurantOrders() {
        return allRestaurantOrders;
    }

    public void setAllRestaurantOrders(ArrayBag<restaurantOrder> allRestaurantOrders) {
        this.allRestaurantOrders = allRestaurantOrders;
    }

    class restaurantOrder {

        private HashMap<Dish, Integer> restaurantOrders;
        private String orderTime;

        public restaurantOrder(HashMap<Dish, Integer> restaurantOrders, String orderTime) {
            this.restaurantOrders = restaurantOrders;
            this.orderTime = orderTime;
        }

        public String getOrderTime() {
            return orderTime;
        }

        public void setOrderTime(String orderTime) {
            this.orderTime = orderTime;
        }

        public HashMap<Dish, Integer> getRestaurantOrders() {
            return restaurantOrders;
        }

        public void setRestaurantOrders(HashMap<Dish, Integer> restaurantOrders) {
            this.restaurantOrders = restaurantOrders;
        }
    }

    class Dish {

        private String name;
        private int foodPrepareDuration;

        public Dish(String name, int foodPrepareDuration) {
            this.name = name;
            this.foodPrepareDuration = foodPrepareDuration;
        }

        public Dish(String name) {
            this.name = name;
            
        }
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getFoodPrepareDuration() {
            return foodPrepareDuration;
        }

        public void setFoodPrepareDuration(int foodPrepareDuration) {
            this.foodPrepareDuration = foodPrepareDuration;
        }
    }
}
