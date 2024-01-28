package com.labuhn.minesweeper.ui.controller;

import com.labuhn.minesweeper.domain.Cell;
import com.labuhn.minesweeper.domain.FlagStatus;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class MineFieldCreator {

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
            label.setGraphic(this.iconProvider.createMineMarkerImage( 25, 25));
        } else if (cell.getFlagStatus() == FlagStatus.MARKED_AS_UNKNOWN) {
            label.setGraphic(this.iconProvider.createQuestionMarkMarkerImage( 25, 25));
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
            label.setGraphic(this.iconProvider.createMineImage( 25, 25));
        }

        return label;
    }

    private Label createStandardLabel() {
        Label label = new Label();
        label.setMinWidth(25);
        label.setMaxWidth(25);
        label.setMinHeight(25);
        label.setMaxHeight(25);
        label.setAlignment(Pos.CENTER);
        return label;
    }


}
