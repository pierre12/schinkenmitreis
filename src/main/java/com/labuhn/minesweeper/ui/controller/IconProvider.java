package com.labuhn.minesweeper.ui.controller;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;



public class IconProvider {
    private static final String MINE_PATH = "/icons/mine.png";
    private static final String MINE_MARKER_PATH = "/icons/mine-marker.png";
    private static final String QUESTION_MARK_MARKER = "/icons/questionmark-marker.png";

    private ImageView createImage(String name, int width, int height) {
        ImageView value = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream(name))));
        value.setPreserveRatio(true);
        value.setFitWidth(width);
        value.setFitHeight(height);
        return value;
    }

    public ImageView createMineImage(int width, int height){
        return createImage(MINE_PATH,width,height);
    }

    public ImageView createMineMarkerImage(int width, int height){
        return createImage(MINE_MARKER_PATH,width,height);
    }
    public ImageView createQuestionMarkMarkerImage(int width, int height){
        return createImage(QUESTION_MARK_MARKER,width,height);
    }

}
