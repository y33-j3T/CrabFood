package crabfood;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

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
            hour = hour == 23 ? 0 : hour++;
            minute = 0;
        } else {
            minute++;
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
