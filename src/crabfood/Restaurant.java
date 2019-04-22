package crabfood;

import java.time.Duration;
import java.time.LocalTime;
import java.util.HashMap;

class Restaurant {

    private String name;
    private MyGoogleMap position;
    private ArrayBag<Dish> allAvailableDishes;
    private ArrayBag<restaurantOrder> allRestaurantOrders;

    public Restaurant(String name, MyGoogleMap position, ArrayBag<Dish> allAvailableDishes, ArrayBag<restaurantOrder> allRestaurantOrders) {
        this.name = name;
        this.position = position;
        this.allAvailableDishes = allAvailableDishes;
        this.allRestaurantOrders = allRestaurantOrders;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MyGoogleMap getPosition() {
        return position;
    }

    public void setPosition(MyGoogleMap position) {
        this.position = position;
    }

    public ArrayBag<Dish> getAllAvailableDishes() {
        return allAvailableDishes;
    }

    public void setAllAvailableDishes(ArrayBag<Dish> allAvailableDishes) {
        this.allAvailableDishes = allAvailableDishes;
    }

    public ArrayBag<restaurantOrder> getAllRestaurantOrders() {
        return allRestaurantOrders;
    }

    public void setAllRestaurantOrders(ArrayBag<restaurantOrder> allRestaurantOrders) {
        this.allRestaurantOrders = allRestaurantOrders;
    }

    private class restaurantOrder {

        private HashMap<Dish, Integer> restaurantOrders;
        private LocalTime orderTime;

        public restaurantOrder(HashMap<Dish, Integer> restaurantOrders, LocalTime orderTime) {
            this.restaurantOrders = restaurantOrders;
            this.orderTime = orderTime;
        }

        public LocalTime getOrderTime() {
            return orderTime;
        }

        public void setOrderTime(LocalTime orderTime) {
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
        private Duration foodPrepareDuration;

        public Dish(String name, Duration foodPrepareDuration) {
            this.name = name;
            this.foodPrepareDuration = foodPrepareDuration;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Duration getFoodPrepareDuration() {
            return foodPrepareDuration;
        }

        public void setFoodPrepareDuration(Duration foodPrepareDuration) {
            this.foodPrepareDuration = foodPrepareDuration;
        }
    }
}
