package com.labuhn.minesweeper.ui.controller;

import com.labuhn.minesweeper.domain.Field;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.util.Objects;

public class MineFieldCreator {

    public Button createField(Field field, EventHandler<MouseEvent> mouseEventHandler) {
        Button button = new Button();
        button.setPrefSize(35,35);
        button.setMaxSize(35,35);
        button.setOnMouseClicked(mouseEventHandler);
        button.setStyle("-fx-background-radius: 0;");

        if(isUncoveredField(field)){
            button.setDisable(true);
            button.setStyle("-fx-opacity: 1;-fx-background-color: lightblue;-fx-background-radius: 0;");
            if(field.getSurroundingMines()>0){
                button.setText(String.valueOf(field.getSurroundingMines()));
            }
        }

        if(isUncoveredMine(field )){
            button.setDisable(true);
            button.setStyle("-fx-opacity: 1;-fx-background-color: lightblue;-fx-background-radius: 0;");
            button.setGraphic(new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icons/Bomb.png")))));
        }

        return button;
    }

    private static boolean isUncoveredMine(Field field) {
        return !field.isCovered() && field.isMine();
    }

    private boolean isUncoveredField(Field field) {
        return !field.isCovered() && !field.isMine();
    }

}
