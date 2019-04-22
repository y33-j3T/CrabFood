package crabfood;

import java.time.Duration;
import java.time.LocalTime;

class DeliveryGuy {

    private MyGoogleMap deliveryStartPosition;
    private MyGoogleMap deliveryEndPosition;
    private Duration deliveryDuration;
    private LocalTime deliveryEndTime;
    private CrabFoodOperator.CrabFoodOrder CrabFoodOrderTBD;

    public DeliveryGuy(MyGoogleMap deliveryStartPosition, MyGoogleMap deliveryEndPosition, Duration deliveryDuration, LocalTime deliveryEndTime, CrabFoodOperator.CrabFoodOrder CrabFoodOrderTBD) {
        this.deliveryStartPosition = deliveryStartPosition;
        this.deliveryEndPosition = deliveryEndPosition;
        this.deliveryDuration = deliveryDuration;
        this.deliveryEndTime = deliveryEndTime;
        this.CrabFoodOrderTBD = CrabFoodOrderTBD;
    }

    public MyGoogleMap getDeliveryStartPosition() {
        return deliveryStartPosition;
    }

    public void setDeliveryStartPosition(MyGoogleMap deliveryStartPosition) {
        this.deliveryStartPosition = deliveryStartPosition;
    }

    public MyGoogleMap getDeliveryEndPosition() {
        return deliveryEndPosition;
    }

    public void setDeliveryEndPosition(MyGoogleMap deliveryEndPosition) {
        this.deliveryEndPosition = deliveryEndPosition;
    }

    public Duration getDeliveryDuration() {
        return deliveryDuration;
    }

    public void setDeliveryDuration(Duration deliveryDuration) {
        this.deliveryDuration = deliveryDuration;
    }

    public LocalTime getDeliveryEndTime() {
        return deliveryEndTime;
    }

    public void setDeliveryEndTime(LocalTime deliveryEndTime) {
        this.deliveryEndTime = deliveryEndTime;
    }

    public CrabFoodOperator.CrabFoodOrder getCrabFoodOrderTBD() {
        return CrabFoodOrderTBD;
    }

    public void setCrabFoodOrderTBD(CrabFoodOperator.CrabFoodOrder CrabFoodOrderTBD) {
        this.CrabFoodOrderTBD = CrabFoodOrderTBD;
    }
}
