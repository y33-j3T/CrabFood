package crabfood;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public class SimulatedTime {

    private volatile static int hour = 0;
    private volatile static int minute = 0;

    public SimulatedTime() {
        SimulatedTime.hour = 0;
        SimulatedTime.minute = 0;
    }

    public SimulatedTime(int hour, int minute) {
        SimulatedTime.hour = hour;
        SimulatedTime.minute = minute;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        SimulatedTime.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        SimulatedTime.minute = minute;
    }

    public void tick() {
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
    
    public void tickFor(int minutesToTick) {
        for (int i=0 ; i<minutesToTick ; i++) {
            this.tick();
        }
    }

    public boolean equalsTime(int hour, int minute) {
        return this.hour == hour && this.minute == minute;
    }

    public static String parseTime(String time) {
        if (Pattern.matches("(//s)*([0-9])+(//s)*:(//s)*([0-9])+(//s)*", time)) {
            return String.format("%02d:%02d",
                    Integer.parseInt(time.split(":")[0]),
                    Integer.parseInt(time.split(":")[1]));
        } else {
            return String.format("%02d:00", Integer.parseInt(time));
        }
    }

    public String getTime() {
        return this.toString();
    }

    public void addTime(int hourToAdd, int minuteToAdd) {
        // must setHour first as setMinute will affect the result of setHour
        this.setHour((this.getHour() + ((this.getMinute() + minuteToAdd) / 60) + hourToAdd) % 24);
        this.setMinute((this.getMinute() + minuteToAdd) % 60);
    }

    public String getTimeAfter(int hourPassed, int minutePassed) {
        SimulatedTime tempClock = new SimulatedTime(this.getHour(), this.getMinute());
        tempClock.addTime(hourPassed, minutePassed);
        return tempClock.getTime();
    }

    @Override
    public String toString() {
        return String.format("%02d:%02d", hour, minute);
    }
}
