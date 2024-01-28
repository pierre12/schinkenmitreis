package com.labuhn.minesweeper.ui.controller;

import com.labuhn.minesweeper.domain.Cell;
import com.labuhn.minesweeper.domain.FlagStatus;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;

public class MineSweeperController {
    @FXML
    private GridPane mineSweeperGrid;

    private MineFieldCreator mineFieldCreator;

    private EventHandler<MouseEvent> onFieldClicked(int x, int y) {
        return event -> {
            MouseButton pressedButton = event.getButton();
            System.out.printf("Field %s:%s pressed with button %s%n", x, y, pressedButton);
            // Talk to backend and rerender ui
        };
    }

    public void setMineFieldCreator(MineFieldCreator mineFieldCreator){
        this.mineFieldCreator = mineFieldCreator;
    }

    public void startGame(int width, int height){
        render(createDummyGrid(width,height));
    }

    private void render(Cell[][] grid) {
        for (int y = 0; y < grid.length; y++) {
            Cell[] gridRow = grid[y];
            Label[] row = new Label[gridRow.length];
            for (int x = 0; x < gridRow.length; x++) {
                row[x] = this.mineFieldCreator.createCell(gridRow[x],onFieldClicked(x, y));
            }
            mineSweeperGrid.addRow(y,row);
        }
    }

    private static Cell[][] createDummyGrid(int width,int height) {
        Cell[][] grid = new Cell[height][width];
        List<Cell> cells = new ArrayList<>();
        cells.add(new Cell(false, true, 0));
        cells.add(new Cell(false, false, 1));
        cells.add(new Cell(false, false, 2));
        cells.add(new Cell(false, false, 3));
        cells.add(new Cell(false, false, 4));
        cells.add(new Cell(false, false, 5));
        cells.add(new Cell(false, false, 6));
        cells.add(new Cell(false, false, 7));
        cells.add(new Cell(false, false, 8));
        cells.add(new Cell(true, true, 0));
        cells.add(new Cell(true, false, 0));
        cells.add(new Cell(true, true, 6));
        cells.add(new Cell(true, false, 6));
        Cell markedAsMine = new Cell(true, false, 6);
        markedAsMine.setFlagStatus(FlagStatus.MARKED_AS_MINE);
        cells.add(markedAsMine);
        Cell markedAsUnknown = new Cell(true, false, 6);
        markedAsUnknown.setFlagStatus(FlagStatus.MARKED_AS_UNKNOWN);
        cells.add(markedAsUnknown);

        for (int y = 0;  y < height; y++){
            for (int x = 0;  x < width; x++){
                grid[y][x]= cells.get((int)(Math.random()*cells.size()));
            }
        }

        return grid;
    }

}
