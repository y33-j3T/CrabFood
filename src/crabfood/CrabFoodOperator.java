package crabfood;

import java.util.ArrayList;
import java.util.HashMap;

class CrabFoodOperator {

    private static MyGoogleMap masterMap;
    private static ArrayBag<Restaurant> partnerRestaurants;
    private static ArrayBag<CrabFoodOrder> allCrabFoodOrders;
    private static ArrayList<DeliveryGuy> allDeliveryGuys;

    public CrabFoodOperator() {
        CrabFoodOperator.masterMap = new MyGoogleMap();
        CrabFoodOperator.allDeliveryGuys = new ArrayList<>(allDeliveryGuys.size());
        
        
        // populate txt for restaurant, crabfood orders and delivery guys
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
