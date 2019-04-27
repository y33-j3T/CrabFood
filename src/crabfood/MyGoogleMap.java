package crabfood;

import static crabfood.CrabFoodOperator.partnerRestaurants;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class MyGoogleMap {

    private int[][] map;
    private ArrayList<Position> allRestaurantPositions;

    public MyGoogleMap() {
        try {
            Scanner s = new Scanner(new FileInputStream("crabfood-io/map.txt"));

            ArrayList<Character[]> charArrList = new ArrayList<>();

            // get saved map from "map.txt"
            while (s.hasNextLine()) {
                String input = s.nextLine();
                if (!input.isEmpty()) {
                    char[] oriCharArr = input.toCharArray();
                    Character[] newCharArr = new Character[oriCharArr.length];
                    for (int i = 0; i < newCharArr.length; i++) {
                        newCharArr[i] = oriCharArr[i];
                    }
                    charArrList.add(newCharArr);
                }
            }

            // if "map.txt" contains a saved map
            if (!charArrList.isEmpty() && charArrList.get(0).length != 0) {
                map = new int[charArrList.size()][charArrList.get(0).length];
                for (int i = 0; i < charArrList.size(); i++) {
                    for (int j = 0; j < charArrList.get(i).length; j++) {
                        map[i][j] = charArrList.get(i)[j];
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("\"map.txt\" not found.");
        }
    }

    // read all restaurant positions again & reprint map into "map.txt"
    public void updateMap() {
        if (!hasOverlappedPositions()) {
            for (Restaurant r : partnerRestaurants.toArray()) {
                for (Position p : r.getPositions().toArray()) {
                    allRestaurantPositions.add(p);
                }
            }
        } else {
            System.out.println("Positions overlap. Unable to update map.");
        }
    }

    public boolean hasOverlappedPositions() {
        ArrayBag<Position> allPositions = new ArrayBag<>();
        for (Restaurant r : partnerRestaurants.toArray()) {
            for (Position p : r.getPositions().toArray()) {
                allPositions.add(p);
            }
        }
        
        for (Position p : allPositions.toArray()){
            if(allPositions.getFrequencyOf(p)>1){
                return true;
            }
        }
        return false;
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

    class Position {

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
