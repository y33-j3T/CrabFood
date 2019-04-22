package crabfood;

import java.util.ArrayList;

public class MyGoogleMap {

    private int[][] map;
    private ArrayList<Position> allRestaurantPositions;

    public MyGoogleMap() {
        // populate map from txt
        // populate all restaurant positions from txt
    }

    public int[][] getMap() {
        return map;
    }

    public void setMap(int[][] map) {
        this.map = map;
    }

    public ArrayList<Position> getAllRestaurantPositions() {
        return allRestaurantPositions;
    }

    public void setAllRestaurantPositions(ArrayList<Position> allRestaurantPositions) {
        this.allRestaurantPositions = allRestaurantPositions;
    }
    
    private class Position {

        private int posX;
        private int posY;

        public Position(int posX, int posY) {
            this.posX = posX;
            this.posY = posY;
        }

        public void setPosition(int posX, int posY) {
            this.posX = posX;
            this.posY = posY;
        }

        public int getPosX() {
            return posX;
        }

        public void setPosX(int posX) {
            this.posX = posX;
        }

        public int getPosY() {
            return posY;
        }

        public void setPosY(int posY) {
            this.posY = posY;
        }
    }
}
