package crabfood;

import crabfood.CrabFoodOperator.CrabFoodOrder;
import crabfood.MyGoogleMap.Position;
import java.util.ArrayList;

class DeliveryGuy {
    
    private int deliveryGuyId = 0;
    private Position currentPosition;
    private ArrayList<DeliverySession> allDeliverySession;
    
    public DeliveryGuy(int deliveryGuyId, Position currentPosition) {
        this.deliveryGuyId = deliveryGuyId;
        this.currentPosition = currentPosition;
        this.allDeliverySession = new ArrayList<>();
    }
    
    public DeliveryGuy(int deliveryGuyId) {
        this.deliveryGuyId = deliveryGuyId;
        this.currentPosition = new Position(0, 0);
        this.allDeliverySession = new ArrayList<>();
    }
    
    public static void initPosition() {
        int x = 0;
        int y = 0;
        int count = 0;
        for (Restaurant restaurant : CrabFoodOperator.getPartnerRestaurants()) {
            x += restaurant.getPosition().getPosX();
            y += restaurant.getPosition().getPosY();
            count++;
        }
        
        x = x / count;
        y = y / count;
        
        for (DeliveryGuy guy : CrabFoodOperator.getAllDeliveryGuys()) {
            guy.setCurrentPosition(new Position(x, y));
        }
    }
    
    @Override
    public String toString() {
        return String.format("Delivery Man ID: %d\nCurrent Position: %s", deliveryGuyId, currentPosition.toString());
    }
    
    public int getDeliveryGuyId() {
        return deliveryGuyId;
    }
    
    public void setDeliveryGuyId(int deliveryGuyId) {
        this.deliveryGuyId = deliveryGuyId;
    }
    
    public Position getCurrentPosition() {
        return currentPosition;
    }
    
    public void setCurrentPosition(Position currentPosition) {
        this.currentPosition = currentPosition;
    }
    
    public ArrayList<DeliverySession> getAllDeliverySession() {
        return allDeliverySession;
    }
    
    public void setAllDeliverySession(ArrayList<DeliverySession> allDeliverySession) {
        this.allDeliverySession = allDeliverySession;
    }
    
    static class DeliverySession {
        
        private CrabFoodOrder CrabFoodOrderTBD;
        private Position deliveryStartPosition;
        private Position deliveryEndPosition;
        private String deliveryStartTime;
        private String deliveryEndTime;
        private int deliveryDuration;
        
        public DeliverySession(CrabFoodOrder CrabFoodOrderTBD, String deliveryStartTime, String deliveryEndTime) {
            this.CrabFoodOrderTBD = CrabFoodOrderTBD;
            this.deliveryStartPosition = CrabFoodOrderTBD.getBranchLocation();
            this.deliveryEndPosition = CrabFoodOrderTBD.getDeliveryLocation();
            this.deliveryStartTime = deliveryStartTime;
            this.deliveryEndTime = deliveryEndTime;
            this.deliveryDuration = SimulatedTime.compareStringTime(deliveryStartTime, deliveryEndTime);
        }
        
        public int getDeliveryDuration() {
            return deliveryDuration;
        }
        
        public void setDeliveryDuration(int deliveryDuration) {
            this.deliveryDuration = deliveryDuration;
        }
        
        public CrabFoodOrder getCrabFoodOrderTBD() {
            return CrabFoodOrderTBD;
        }
        
        public void setCrabFoodOrderTBD(CrabFoodOrder CrabFoodOrderTBD) {
            this.CrabFoodOrderTBD = CrabFoodOrderTBD;
        }
        
        public Position getDeliveryStartPosition() {
            return deliveryStartPosition;
        }
        
        public void setDeliveryStartPosition(Position deliveryStartPosition) {
            this.deliveryStartPosition = deliveryStartPosition;
        }
        
        public Position getDeliveryEndPosition() {
            return deliveryEndPosition;
        }
        
        public void setDeliveryEndPosition(Position deliveryEndPosition) {
            this.deliveryEndPosition = deliveryEndPosition;
        }
        
        public String getDeliveryStartTime() {
            return deliveryStartTime;
        }
        
        public void setDeliveryStartTime(String deliveryStartTime) {
            this.deliveryStartTime = deliveryStartTime;
        }
        
        public String getDeliveryEndTime() {
            return deliveryEndTime;
        }
        
        public void setDeliveryEndTime(String deliveryEndTime) {
            this.deliveryEndTime = deliveryEndTime;
        }
        
    }
}
