package com.labuhn.minesweeper.ui.controller;

import com.labuhn.minesweeper.domain.Cell;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.util.Objects;

public class MineFieldCreator {

    public Button createField(Cell cell, EventHandler<MouseEvent> mouseEventHandler) {
        Button button = new Button();
        button.setPrefSize(35,35);
        button.setMaxSize(35,35);
        button.setOnMouseClicked(mouseEventHandler);
        button.setStyle("-fx-background-radius: 0;");

        if(isUncoveredField(cell)){
            button.setDisable(true);
            button.setStyle("-fx-opacity: 1;-fx-background-color: lightblue;-fx-background-radius: 0;");
            if(cell.getSurroundingMines()>0){
                button.setText(String.valueOf(cell.getSurroundingMines()));
            }
        }

        if(isUncoveredMine(cell)){
            button.setDisable(true);
            button.setStyle("-fx-opacity: 1;-fx-background-color: lightblue;-fx-background-radius: 0;");
            button.setGraphic(new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icons/Bomb.png")))));
        }

        return button;
    }

    private static boolean isUncoveredMine(Cell cell) {
        return !cell.isCovered() && cell.isMine();
    }

    private boolean isUncoveredField(Cell cell) {
        return !cell.isCovered() && !cell.isMine();
    }

}
