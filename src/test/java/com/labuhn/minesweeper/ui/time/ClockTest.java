package com.labuhn.minesweeper.ui.time;

import javafx.animation.Animation;
import javafx.animation.Timeline;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.testfx.framework.junit5.ApplicationExtension;

import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class, ApplicationExtension.class})
public class ClockTest {

    @Test
    public void testEventHandlerIsCalledWhenClockIsRunning() throws InterruptedException {
        AtomicInteger elapsedTime = new AtomicInteger(-1);
        Clock clock = new Clock(elapsedTime::set);

        clock.start();
        Thread.sleep(2101);

        assertThat(elapsedTime.get()).isEqualTo(2);
    }

    @Test
    public void testStartClockTwiceDoesNothing() throws NoSuchFieldException, IllegalAccessException {
        Clock clock = new Clock((e) -> {});
        Timeline timeline = mock(Timeline.class);
        Field timelineField = Clock.class.getDeclaredField("timeline");
        timelineField.setAccessible(true);
        timelineField.set(clock,timeline);

        clock.start();

        verify(timeline, times(0)).setCycleCount(Animation.INDEFINITE);
        verify(timeline, times(0)).play();
        assertThat(timelineField.get(clock)).isEqualTo(timeline);
    }

    @Test
    public void testStopsTheClock() throws NoSuchFieldException, IllegalAccessException {
        Clock clock = new Clock((e) -> {});
        Timeline timeline = mock(Timeline.class);
        Field timelineField = Clock.class.getDeclaredField("timeline");
        timelineField.setAccessible(true);
        timelineField.set(clock,timeline);

        clock.stop();

        verify(timeline, times(1)).stop();
        assertThat(timelineField.get(clock)).isEqualTo(null);
    }

    @Test
    public void testStopTheWatchBeforeStartHasNoEffect() throws NoSuchFieldException, IllegalAccessException {
        Clock clock = new Clock((e) -> {});
        Field timelineField = Clock.class.getDeclaredField("timeline");
        timelineField.setAccessible(true);

        clock.stop();

        assertThat(timelineField.get(clock)).isNull();
    }

}
