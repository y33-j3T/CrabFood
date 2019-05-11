package crabfood;

import crabfood.MyGoogleMap.Position;
import java.util.ArrayList;

class Restaurant {

    private String name;
    private Character mapSymbol;
    private Position position;
    private ArrayList<Dish> allAvailableDishes;
    private ArrayList<RestaurantOrder> allRestaurantOrders;

    public Restaurant() {
        this.mapSymbol = '#';
        this.name = "name not set";
        this.position = new Position(0, 0);
        this.allAvailableDishes = new ArrayList<>();
        this.allRestaurantOrders = new ArrayList<>();
    }

    public Restaurant(String name) {
        this.name = name;
    }

    public Restaurant(Character mapSymbol, String name, Position position, ArrayList<Dish> allAvailableDishes) {
        this.mapSymbol = mapSymbol;
        this.name = name;
        this.position = position;
        this.allAvailableDishes = allAvailableDishes;
        this.allRestaurantOrders = new ArrayList<>();
    }

    public int getCookTime(String dishName) {
        for (Dish dish : allAvailableDishes) {
            if(dishName.equals(dish.getName())){
                return dish.getFoodPrepareDuration();
            }
        }
        return -1;
    }

    public ArrayList<RestaurantOrder> getAllRestaurantOrders() {
        return allRestaurantOrders;
    }

    public void setAllRestaurantOrders(ArrayList<RestaurantOrder> allRestaurantOrders) {
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

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public ArrayList<Dish> getAllAvailableDishes() {
        return allAvailableDishes;
    }

    public void setAllAvailableDishes(ArrayList<Dish> allAvailableDishes) {
        this.allAvailableDishes = allAvailableDishes;
    }

    class RestaurantOrder {
        private String startTime = "0";
        private String endTime = "0";
        private int duration = SimulatedTime.parseTimeToSimulatedTime(startTime).differenceTime(endTime);

        public RestaurantOrder(String startTime, String endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
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
            this.foodPrepareDuration = -1;
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
