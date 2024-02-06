package com.labuhn.minesweeper.ui.time;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class TimeUtilTest {

    @ParameterizedTest
    @MethodSource("getSecondsWithRespectiveTimeFormats")
    public void testElapsedTimeToTimeFormat(int elapsedTime, String expectedTimeFormat){
        String actual = TimeUtil.toTimeFormat(elapsedTime);

        assertThat(actual).isEqualTo(expectedTimeFormat);
    }

    public static Stream<Arguments> getSecondsWithRespectiveTimeFormats(){
        return Stream.of(Arguments.of(9,"00:09"),
                Arguments.of(15,"00:15"),
                Arguments.of(60,"01:00"),
                Arguments.of(75,"01:15"),
                Arguments.of(600,"10:00"),
                Arguments.of(666,"11:06"),
                Arguments.of(3720,"62:00")
        );
    }
}
