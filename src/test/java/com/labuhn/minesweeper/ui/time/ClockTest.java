package com.labuhn.minesweeper.ui.time;

import javafx.animation.Animation;
import javafx.animation.Timeline;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.testfx.framework.junit5.ApplicationExtension;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class, ApplicationExtension.class})
public class ClockTest {

    @Test
    public void testEventHandlerIsCalledWhenClockIsRunning() {
        AtomicInteger elapsedTime = new AtomicInteger(-1);
        Clock clock = new Clock(elapsedTime::set);

        clock.start();

        Awaitility.await().atMost(3, TimeUnit.SECONDS).untilAsserted(() -> assertThat(elapsedTime.get()).isEqualTo(2) );
    }

    @Test
    public void testStartClockTwiceDoesNothing() {
        Clock clock = new Clock((e) -> {});
        clock.timeline = mock(Timeline.class);

        clock.start();

        verify(clock.timeline, times(0)).setCycleCount(Animation.INDEFINITE);
        verify(clock.timeline, times(0)).play();
    }

    @Test
    public void testStopsTheClock() {
        Clock clock = new Clock((e) -> {});
        Timeline timelineMock = mock(Timeline.class);
        clock.timeline = timelineMock;

        clock.stop();

        verify(timelineMock, times(1)).stop();
        assertThat(clock.timeline).isEqualTo(null);
    }

    @Test
    public void testStopTheWatchBeforeStartHasNoEffect() {
        Clock clock = new Clock((e) -> {});

        clock.stop();

        assertThat(clock.timeline).isNull();
    }

}
