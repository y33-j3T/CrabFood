package crabfood;

import crabfood.CrabFoodOperator.CrabFoodOrder;
import crabfood.MyGoogleMap.Position;

class DeliveryGuy {

    private int deliveryGuyId = 0;
    private Position currentPosition;

    public DeliveryGuy(int deliveryGuyId, Position currentPosition) {
        this.deliveryGuyId = deliveryGuyId;
        this.currentPosition = currentPosition;
    }

    public DeliveryGuy(int deliveryGuyId) {
        this.deliveryGuyId = deliveryGuyId;
        this.currentPosition = new Position(0, 0);
    }

    @Override
    public String toString() {
        return String.format("Delivery Man ID: %d\nCurrent Position: %s", deliveryGuyId, currentPosition.toString());
    }

    class DeliverySession {

        private CrabFoodOrder CrabFoodOrderTBD;
        private Position deliveryStartPosition = currentPosition;
        private Position deliveryEndPosition = CrabFoodOrderTBD.getDeliveryLocation();
        private String deliveryStartTime;
        private String deliveryEndTime;

    }
}
