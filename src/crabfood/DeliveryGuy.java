package crabfood;

import crabfood.CrabFoodOperator.CrabFoodOrder;
import crabfood.MyGoogleMap.Position;

class DeliveryGuy {

    private int deliveryGuyId;
    private Position currentPosition;

    public DeliveryGuy(int deliveryGuyId, Position currentPosition) {
        this.deliveryGuyId = deliveryGuyId;
        this.currentPosition = currentPosition;
    }

    public DeliveryGuy(int deliveryGuyId) {
        this.deliveryGuyId = deliveryGuyId;
        this.currentPosition = new Position(0, 0);
    }
    
    class DeliverySession {

        private CrabFoodOrder CrabFoodOrderTBD;
        private Position deliveryStartPosition;
        private Position deliveryEndPosition;
        private String deliveryStartTime;
        private String deliveryEndTime;

    }
}
