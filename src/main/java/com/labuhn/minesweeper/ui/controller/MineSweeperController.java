package com.labuhn.minesweeper.ui.controller;

import com.labuhn.minesweeper.domain.Field;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;

public class MineSweeperController {
    @FXML
    private GridPane mineSweeperGrid;

    private int width = 16;
    private int height = 16;
    private MineFieldCreator mineFieldCreator;

    private EventHandler<MouseEvent> onFieldClicked(int x, int y) {
        return event -> {
            MouseButton pressedButton = event.getButton();
            System.out.println(String.format("Field %s:%s pressed with button %s", x, y, pressedButton));
        };
    }

    public void setMineFieldCreator(MineFieldCreator mineFieldCreator){
        this.mineFieldCreator = mineFieldCreator;
    }

    public void setDimension(int width, int height){
        this.width = width;
        this.height = height;
    }
    public void render(List<Field> fields) {
            for (int x = 0; x < height; x++) {
                Control[] row = new Control[width];
                for (int y = 0; y < width; y++) {
                    Field randomField = fields.get((int) (Math.random() * fields.size()));
                    Control field = this.mineFieldCreator.createField(randomField,onFieldClicked(x, y));
                    row[y] = field;
                }
                mineSweeperGrid.addRow(x, row);
            }
    }

}
