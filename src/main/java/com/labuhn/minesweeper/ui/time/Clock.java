package com.labuhn.minesweeper.ui.time;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Clock {

    private final TimeUpdatedEventHandler eventHandler;
    private int elapsedTimeInSeconds;
    private Timeline timeline;

    public Clock(TimeUpdatedEventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    public void start() {
        if (this.timeline != null) {
            return;
        }

        this.timeline = createTimeline();
        this.timeline.setCycleCount(Animation.INDEFINITE);
        this.timeline.play();
    }

    private Timeline createTimeline() {
        return new Timeline(new KeyFrame(Duration.seconds(1), (e) -> {
                    this.elapsedTimeInSeconds++;
                    this.eventHandler.onUpdate(this.elapsedTimeInSeconds);
                })
        );
    }

    public void stop() {
        if (this.timeline == null) {
            return;
        }

        this.timeline.stop();
        this.timeline = null;
    }

    @FunctionalInterface
    public interface TimeUpdatedEventHandler {
        void onUpdate(int elapsedTime);

    }
}
