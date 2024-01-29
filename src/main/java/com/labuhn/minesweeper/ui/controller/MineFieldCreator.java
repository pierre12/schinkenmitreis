package com.labuhn.minesweeper.ui.controller;

import com.labuhn.minesweeper.domain.Cell;
import com.labuhn.minesweeper.domain.FlagStatus;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class MineFieldCreator {

    private final int WIDTH_PX = 25;
    private final int HEIGHT_PX = 25;
    private final IconProvider iconProvider;

    public MineFieldCreator(IconProvider iconProvider){
        this.iconProvider = iconProvider;

    }

    public Label createCell(Cell cell, EventHandler<MouseEvent> mouseEventHandler) {
        return cell.isCovered() ? createCoveredField(cell, mouseEventHandler) : createUncoveredCell(cell);
    }

    private Label createCoveredField(Cell cell, EventHandler<MouseEvent> mouseEventHandler) {
        Label label = createStandardLabel();
        label.setId("covered");
        label.setOnMouseClicked(mouseEventHandler);
        if (cell.getFlagStatus() == FlagStatus.MARKED_AS_MINE) {
            label.setGraphic(this.iconProvider.createMineMarkerImage(WIDTH_PX, HEIGHT_PX));
        } else if (cell.getFlagStatus() == FlagStatus.MARKED_AS_UNKNOWN) {
            label.setGraphic(this.iconProvider.createQuestionMarkMarkerImage(  WIDTH_PX, HEIGHT_PX));
        }

        return label;
    }

    private Label createUncoveredCell(Cell cell) {
        Label label = createStandardLabel();
        if (!cell.isMine()) {
            label.setId("uncovered");
            if (cell.getSurroundingMines() > 0) {
                label.setText(String.valueOf(cell.getSurroundingMines()));
            }
        }

        if (cell.isMine()) {
            label.setId("bomb");
            label.setGraphic(this.iconProvider.createMineImage( WIDTH_PX, HEIGHT_PX));
        }

        return label;
    }

    private Label createStandardLabel() {
        Label label = new Label();
        label.setMinWidth(WIDTH_PX);
        label.setMaxWidth(WIDTH_PX);
        label.setMinHeight(HEIGHT_PX);
        label.setMaxHeight(HEIGHT_PX);
        label.setAlignment(Pos.CENTER);
        return label;
    }


}
