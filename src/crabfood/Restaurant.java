package crabfood;

import crabfood.MyGoogleMap.Position;
import java.util.ArrayList;
import java.util.HashMap;

class Restaurant {

    private String name;
    private ArrayBag<Position> positions;
    private ArrayList<Dish> allAvailableDishes;
    private ArrayBag<restaurantOrder> allRestaurantOrders;

    public Restaurant() {
    }

    public Restaurant(String name, ArrayBag<Position> positions, ArrayList<Dish> allAvailableDishes, ArrayBag<restaurantOrder> allRestaurantOrders) {
        this.name = name;
        this.positions = positions;
        this.allAvailableDishes = allAvailableDishes;
        this.allRestaurantOrders = allRestaurantOrders;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayBag<Position> getPositions() {
        return positions;
    }

    public void setPositions(ArrayBag<Position> positions) {
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
        private SimulatedTime orderTime;

        public restaurantOrder(HashMap<Dish, Integer> restaurantOrders, SimulatedTime orderTime) {
            this.restaurantOrders = restaurantOrders;
            this.orderTime = orderTime;
        }

        public SimulatedTime getOrderTime() {
            return orderTime;
        }

        public void setOrderTime(SimulatedTime orderTime) {
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
