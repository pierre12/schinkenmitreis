package com.labuhn.minesweeper.ui.controller;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;



public class IconCreator {

    public ImageView createImage(String imagePath, int width, int height){
        ImageView value = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath))));
        value.setPreserveRatio(true);
        value.setFitWidth(width);
        value.setFitHeight(height);
        return value;
    }

}
