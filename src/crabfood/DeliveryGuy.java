package crabfood;

class DeliveryGuy {

    private MyGoogleMap deliveryStartPosition;
    private MyGoogleMap deliveryEndPosition;
    private int deliveryDuration;
    private SimulatedTime deliveryEndTime;
    private CrabFoodOperator.CrabFoodOrder CrabFoodOrderTBD;

    public DeliveryGuy(MyGoogleMap deliveryStartPosition, MyGoogleMap deliveryEndPosition, int deliveryDuration, SimulatedTime deliveryEndTime, CrabFoodOperator.CrabFoodOrder CrabFoodOrderTBD) {
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

    public int getDeliveryDuration() {
        return deliveryDuration;
    }

    public void setDeliveryDuration(int deliveryDuration) {
        this.deliveryDuration = deliveryDuration;
    }

    public SimulatedTime getDeliveryEndTime() {
        return deliveryEndTime;
    }

    public void setDeliveryEndTime(SimulatedTime deliveryEndTime) {
        this.deliveryEndTime = deliveryEndTime;
    }

    public CrabFoodOperator.CrabFoodOrder getCrabFoodOrderTBD() {
        return CrabFoodOrderTBD;
    }

    public void setCrabFoodOrderTBD(CrabFoodOperator.CrabFoodOrder CrabFoodOrderTBD) {
        this.CrabFoodOrderTBD = CrabFoodOrderTBD;
    }
}
