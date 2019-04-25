package crabfood;

public class SimulatedTime {

    private int hour = 0;
    private int minute = 0;

    public SimulatedTime(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }
    
    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    
    public void tick() {
        if (minute==59) {
            if (hour==23) {
                hour=0;
            } else {
                hour++;
            }
            minute = 0;
        } else {
            minute++;
        }
    }

    public boolean compareTime(int hour, int minute) {
        return this.hour == hour && this.minute == minute;
    }

    @Override
    public String toString() {
        return String.format("%02d:%02d", hour, minute);
    }
    
    public static void main(String[] args) {
        SimulatedTime time = new SimulatedTime(0, 0);
        System.out.println(time);
    }
}
