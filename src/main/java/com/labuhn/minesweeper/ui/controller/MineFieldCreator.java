package com.labuhn.minesweeper.ui.controller;

import com.labuhn.minesweeper.domain.Cell;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.util.Objects;

public class MineFieldCreator {

    public Label createCell(Cell cell, EventHandler<MouseEvent> mouseEventHandler){
        Label label = new Label();
        label.setMinWidth(25);
        label.setMaxWidth(25);
        label.setMinHeight(25);
        label.setMaxHeight(25);
        label.setAlignment(Pos.CENTER);

        if(cell.isCovered()){
            label.setId("covered");
            label.setOnMouseClicked(mouseEventHandler);
        }

        if(isUncoveredField(cell)){
            label.setId("uncovered");
            if(cell.getSurroundingMines()>0){
                label.setText(String.valueOf(cell.getSurroundingMines()));
            }
        }

        if(isUncoveredMine(cell )){
            label.setId("bomb");
            label.setGraphic(createImage("/icons/Bomb.png",25,25));
        }

        return label;
    }

    private ImageView createImage(String name, int width,int height) {
        ImageView value = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream(name))));
        value.setPreserveRatio(true);
        value.setFitWidth(width);
        value.setFitHeight(height);
        return value;
    }

    private static boolean isUncoveredMine(Cell cell) {
        return !cell.isCovered() && cell.isMine();
    }

    private boolean isUncoveredField(Cell cell) {
        return !cell.isCovered() && !cell.isMine();
    }

}
