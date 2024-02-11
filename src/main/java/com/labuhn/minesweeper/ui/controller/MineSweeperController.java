package com.labuhn.minesweeper.ui.controller;

import com.labuhn.minesweeper.domain.Cell;
import com.labuhn.minesweeper.domain.FlagStatus;
import com.labuhn.minesweeper.ui.time.Clock;
import com.labuhn.minesweeper.ui.time.TimeUtil;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;

public class MineSweeperController {
    @FXML
    private GridPane mineSweeperGrid;
    @FXML
    private Label timeLabel;
    @FXML
    Button restartButton;

    private MineFieldCreator mineFieldCreator;
    private Clock clock;
    private IconCreator iconProvider;


    public void setIconProvider(IconCreator iconCreator){
        this.iconProvider = iconCreator;
        this.restartButton.setGraphic(iconProvider.createImage(Icons.RESTART_ICON,25,25));
    }
    public void setMineFieldCreator(MineFieldCreator mineFieldCreator) {
        this.mineFieldCreator = mineFieldCreator;
    }

    public void startGame(int width, int height) {
        this.clock.start();
        render(createDummyGrid(width, height));
    }

    protected Clock createClock() {
        return new Clock(this::updateTime);
    }

    private void render(Cell[][] grid) {
        this.mineSweeperGrid.getChildren().clear();
        for (int y = 0; y < grid.length; y++) {
            Cell[] gridRow = grid[y];
            Label[] row = new Label[gridRow.length];
            for (int x = 0; x < gridRow.length; x++) {
                row[x] = this.mineFieldCreator.createCell(gridRow[x], onFieldClicked(x, y));
            }
            this.mineSweeperGrid.addRow(y, row);
        }
    }

    private void updateTime(int elapsedTimeInSeconds) {
        this.timeLabel.setText(TimeUtil.toTimeFormat(elapsedTimeInSeconds));
    }

    private EventHandler<MouseEvent> onFieldClicked(int x, int y) {
        return event -> {
            MouseButton pressedButton = event.getButton();
            System.out.printf("Field %s:%s pressed with button %s%n", x, y, pressedButton);
        };
    }

    private static Cell[][] createDummyGrid(int width, int height) {
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

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                grid[y][x] = cells.get((int) (Math.random() * cells.size()));
            }
        }

        return grid;
    }

    @FXML
    public void initialize() {
        this.clock = this.createClock();
        this.restartButton.setOnMouseClicked((e) -> {
            this.clock.stop();
            startGame(this.mineSweeperGrid.getColumnCount(),this.mineSweeperGrid.getRowCount());
        });
    }

}
