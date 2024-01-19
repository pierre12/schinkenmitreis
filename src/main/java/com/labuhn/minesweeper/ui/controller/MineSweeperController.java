package com.labuhn.minesweeper.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;

public class MineSweeperController {
    @FXML
    private GridPane mineSweeperGrid;

    private int width = 16;
    private int height = 16;

    public void initialize() {
        for (int x = 0; x < height; x++) {
            Control[] fields = new Control[width];
            for (int y = 0; y < width; y++) {
                fields[y] = this.createField(x, y);
            }
            mineSweeperGrid.addRow(x, fields);
        }
    }

    private Button createField(int x, int y) {
        Button button = new Button();
        button.setStyle("-fx-background-radius: 0; -fx-start-margin: 10; -fx-end-margin: 10");
        button.setOnMouseClicked(event -> {
            MouseButton pressedButton = event.getButton();
            System.out.println(String.format("Field %s:%s pressed with button %s",x,y,pressedButton));
        });
        return button;
    }

}
