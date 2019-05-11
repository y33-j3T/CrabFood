package crabfood;

import java.util.regex.Pattern;

public class SimulatedTime {

    private volatile int hour = 0;
    private volatile int minute = 0;

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
        for (int i = 0; i < minutesToTick; i++) {
            this.tick();
        }
    }

    public boolean equalsTime(int hour, int minute) {
        return this.hour == hour && this.minute == minute;
    }

    public static SimulatedTime parseTimeToSimulatedTime(String timeStr) {
        SimulatedTime sim = new SimulatedTime();
        if (Pattern.matches("(//s)*([0-9])+(//s)*:(//s)*([0-9])+(//s)*", timeStr)) {
            sim.setHour(Integer.parseInt(timeStr.split(":")[0]));
            sim.setMinute(Integer.parseInt(timeStr.split(":")[1]));
        } else {
            sim.setHour(Integer.parseInt(timeStr));
        }
        return sim;
    }

    public static String parseTimeToString(String timeStr) {
        if (Pattern.matches("(//s)*([0-9])+(//s)*:(//s)*([0-9])+(//s)*", timeStr)) {
            return String.format("%02d:%02d",
                    Integer.parseInt(timeStr.split(":")[0]),
                    Integer.parseInt(timeStr.split(":")[1]));
        } else {
            return String.format("%02d:00", Integer.parseInt(timeStr));
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

    public void addTime(int minuteToAdd) {
        // must setHour first as setMinute will affect the result of setHour
        this.setHour((this.getHour() + (this.getMinute() + minuteToAdd) / 60) % 24);
        this.setMinute((this.getMinute() + minuteToAdd) % 60);
    }

    public int differenceTime(String timeStr) {
        SimulatedTime time = parseTimeToSimulatedTime(timeStr);
        return Math.abs((this.getHour() - time.getHour()) * 60) + Math.abs(this.getMinute() - time.getMinute());
    }

    public String getTimeAfter(int minutePassed) {
        SimulatedTime tempClock = new SimulatedTime(this.getHour(), this.getMinute());
        tempClock.addTime(minutePassed);
        return tempClock.getTime();
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
