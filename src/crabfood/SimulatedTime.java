package crabfood;

import java.util.logging.Level;
import java.util.logging.Logger;

public class SimulatedTime {

    private static int hour = 0;
    private static int minute = 0;

    public SimulatedTime() {
        this.hour = 0;
        this.minute = 0;
    }

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
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(SimulatedTime.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (minute == 59) {
            if (hour == 23) {
                hour = 0;
            } else {
                hour++;
            }
            minute = 0;
        } else {
            minute++;
        }
    }

    public boolean equalsTime(int hour, int minute) {
        return this.hour == hour && this.minute == minute;
    }

    @Override
    public String toString() {
        return String.format("%02d:%02d", hour, minute);
    }
}
