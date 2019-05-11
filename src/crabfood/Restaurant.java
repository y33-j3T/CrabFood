package crabfood;

import crabfood.MyGoogleMap.Position;
import java.util.ArrayList;
import java.util.HashMap;

class Restaurant {

    private String name;
    private Character mapSymbol;
    private ArrayList<Position> positions;
    private ArrayList<Dish> allAvailableDishes;

    public Restaurant() {
        this.mapSymbol = '#';
        this.name = "name not set";
    }

    public Restaurant(String name) {
        this.name = name;
    }

    public Restaurant(Character mapSymbol, String name, ArrayList<Position> positions, ArrayList<Dish> allAvailableDishes) {
        this.mapSymbol = mapSymbol;
        this.name = name;
        this.positions = positions;
        this.allAvailableDishes = allAvailableDishes;
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
