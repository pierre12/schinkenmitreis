package com.labuhn.minesweeper.ui.time;

public class TimeUtil {

    private TimeUtil(){}

    public static String toTimeFormat(int elapsedTimeInSeconds){
        int seconds = elapsedTimeInSeconds % 60;
        int minutes = elapsedTimeInSeconds / 60;

        return String.format("%s:%s",pad(minutes),pad(seconds));
    }

    private static String pad(int number) {
        if(number < 10){
            return '0' + String.valueOf(number) ;
        }

        return String.valueOf(number);
    }

}
