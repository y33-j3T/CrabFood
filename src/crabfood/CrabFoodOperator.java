package crabfood;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

class CrabFoodOperator {

    private static MyGoogleMap masterMap;
    private ArrayBag<Restaurant> partnerRestaurants;
    private ArrayBag<CrabFoodOrder> allCrabFoodOrders;
    private ArrayList<DeliveryGuy> allDeliveryGuys;

    public CrabFoodOperator() {
        this.allDeliveryGuys = new ArrayList<>(3);
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
        this.partnerRestaurants = partnerRestaurants;
    }

    public ArrayBag<CrabFoodOrder> getAllCrabFoodOrders() {
        return allCrabFoodOrders;
    }

    public void setAllCrabFoodOrders(ArrayBag<CrabFoodOrder> allCrabFoodOrders) {
        this.allCrabFoodOrders = allCrabFoodOrders;
    }

    public ArrayList<DeliveryGuy> getAllDeliveryGuys() {
        return allDeliveryGuys;
    }

    public void setAllDeliveryGuys(ArrayList<DeliveryGuy> allDeliveryGuys) {
        this.allDeliveryGuys = allDeliveryGuys;
    }

    class CrabFoodOrder {

        private LocalTime orderTime;
        private Restaurant restaurant;
        private HashMap<Restaurant.Dish, Integer> crabFoodOrders;
        private MyGoogleMap deliveryLocation;

        public CrabFoodOrder(LocalTime orderTime, Restaurant restaurant, HashMap<Restaurant.Dish, Integer> crabFoodOrders, MyGoogleMap deliveryLocation) {
            this.orderTime = orderTime;
            this.restaurant = restaurant;
            this.crabFoodOrders = crabFoodOrders;
            this.deliveryLocation = deliveryLocation;
        }

        public LocalTime getOrderTime() {
            return orderTime;
        }

        public void setOrderTime(LocalTime orderTime) {
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
