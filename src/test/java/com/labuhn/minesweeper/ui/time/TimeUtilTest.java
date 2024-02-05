package com.labuhn.minesweeper.ui.time;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class TimeUtilTest {

    @Test
    public void testReturnTimeFormatForElapsedTimeSmallerThan10Seconds(){
        String actual = TimeUtil.toTimeFormat(9);

        assertThat(actual).isEqualTo("00:09");
    }

    @Test
    public void testReturnTimeFormatForElapsedTimeGreaterThan10SecondsButSmallerThan1Minute(){
        String actual = TimeUtil.toTimeFormat(58);

        assertThat(actual).isEqualTo("00:58");
    }

    @Test
    public void testReturnTimeFormatElapsedTimeEquals1Minute(){
        String actual = TimeUtil.toTimeFormat(60);

        assertThat(actual).isEqualTo("01:00");
    }

    @Test
    public void testReturnTimeFormatElapsedTimeEquals1MinuteAndSomeSeconds(){
        String actual = TimeUtil.toTimeFormat(63);

        assertThat(actual).isEqualTo("01:03");
    }

    @Test
    public void testReturnTimeFormatElapsedTimeEquals10Minutes(){
        String actual = TimeUtil.toTimeFormat(600);

        assertThat(actual).isEqualTo("10:00");
    }

    @Test
    public void testReturnTimeFormatElapsedTimeEquals10MinutesAndSomeSeconds(){
        String actual = TimeUtil.toTimeFormat(666);

        assertThat(actual).isEqualTo("11:06");
    }

}
