package com.labuhn.minesweeper.ui.controller;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.labuhn.minesweeper.domain.Cell;
import com.labuhn.minesweeper.domain.FlagStatus;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.testfx.framework.junit5.ApplicationExtension;


@ExtendWith({MockitoExtension.class, ApplicationExtension.class})
public class MineCellCreatorTest {

    private final IconCreator iconProvider = mock(IconCreator.class);

    private final MineFieldCreator mineFieldCreator  = new MineFieldCreator(iconProvider);

    private final EventHandler<MouseEvent>  emptyMouseHandler = event -> {};

    @Test
    public void createUncoveredEmptyField(){
        Label cell = mineFieldCreator.createCell(new Cell(false, false, 0), null);

        assertThat(cell.getMinHeight()).isEqualTo(25);
        assertThat(cell.getMaxHeight()).isEqualTo(25);
        assertThat(cell.getMinWidth()).isEqualTo(25);
        assertThat(cell.getMaxHeight()).isEqualTo(25);
        assertThat(cell.getText()).isEqualTo("");
        assertThat(cell.getAlignment()).isEqualTo(Pos.CENTER);
        assertThat(cell.getId()).isEqualTo("uncovered");
        assertThat(cell.getOnMouseClicked()).isNull();
    }

    @Test
    public void createCoveredEmptyField(){
        Label cell = mineFieldCreator.createCell(new Cell(true, false, 0), emptyMouseHandler);

        assertThat(cell.getMinHeight()).isEqualTo(25);
        assertThat(cell.getMaxHeight()).isEqualTo(25);
        assertThat(cell.getMinWidth()).isEqualTo(25);
        assertThat(cell.getMaxHeight()).isEqualTo(25);
        assertThat(cell.getText()).isEqualTo("");
        assertThat(cell.getId()).isEqualTo("covered");
        assertThat(cell.getOnMouseClicked()).isEqualTo(emptyMouseHandler);
    }

    @Test
    public void createUncoveredFieldWithSurroundingMines(){
        Label cell = mineFieldCreator.createCell(new Cell(false, false, 4), null);

        assertThat(cell.getMinHeight()).isEqualTo(25);
        assertThat(cell.getMaxHeight()).isEqualTo(25);
        assertThat(cell.getMinWidth()).isEqualTo(25);
        assertThat(cell.getMaxHeight()).isEqualTo(25);
        assertThat(cell.getText()).isEqualTo("4");
        assertThat(cell.getId()).isEqualTo("uncovered");
        assertThat(cell.getOnMouseClicked()).isNull();
    }

    @Test
    public void createCoveredFieldWithSurroundingMines(){
        Label cell = mineFieldCreator.createCell(new Cell(true, false, 4), emptyMouseHandler);

        assertThat(cell.getMinHeight()).isEqualTo(25);
        assertThat(cell.getMaxHeight()).isEqualTo(25);
        assertThat(cell.getMinWidth()).isEqualTo(25);
        assertThat(cell.getMaxHeight()).isEqualTo(25);
        assertThat(cell.getText()).isEqualTo("");
        assertThat(cell.getId()).isEqualTo("covered");
        assertThat(cell.getOnMouseClicked()).isEqualTo(emptyMouseHandler);
    }


    @Test
    public void createUncoveredMineField(){
        ImageView expectedImageView = new ImageView();
        when(iconProvider.createImage(Icons.MINE,25,25)).thenReturn(expectedImageView);
        Label cell = mineFieldCreator.createCell(new Cell(false, true, 4), null);

        assertThat(cell.getMinHeight()).isEqualTo(25);
        assertThat(cell.getMaxHeight()).isEqualTo(25);
        assertThat(cell.getMinWidth()).isEqualTo(25);
        assertThat(cell.getMaxHeight()).isEqualTo(25);
        assertThat(cell.getId()).isEqualTo("bomb");
        verify(iconProvider, times(1)).createImage(Icons.MINE,25,25);
        assertThat(cell.getGraphic()).isEqualTo(expectedImageView);
        assertThat(cell.getOnMouseClicked()).isNull();
    }

    @Test
    public void createCoveredMineField(){
        Label cell  = mineFieldCreator.createCell(new Cell(true, true, 4), emptyMouseHandler);

        assertThat(cell.getMinHeight()).isEqualTo(25);
        assertThat(cell.getMaxHeight()).isEqualTo(25);
        assertThat(cell.getMinWidth()).isEqualTo(25);
        assertThat(cell.getMaxHeight()).isEqualTo(25);
        assertThat(cell.getText()).isEqualTo("");
        assertThat(cell.getId()).isEqualTo("covered");
        assertThat(cell.getOnMouseClicked()).isEqualTo(emptyMouseHandler);
    }

    @Test
    public void createMarkAsMineField(){
        ImageView expectedImageView = new ImageView();
        when(iconProvider.createImage(Icons.MINE_MARKER,25,25)).thenReturn(expectedImageView);
        Cell cellMarkedAsMine = new Cell(true, true, 4);
        cellMarkedAsMine.setFlagStatus(FlagStatus.MARKED_AS_MINE);

        Label cell  = mineFieldCreator.createCell(cellMarkedAsMine, emptyMouseHandler);

        assertThat(cell.getMinHeight()).isEqualTo(25);
        assertThat(cell.getMaxHeight()).isEqualTo(25);
        assertThat(cell.getMinWidth()).isEqualTo(25);
        assertThat(cell.getMaxHeight()).isEqualTo(25);
        verify(iconProvider, times(1)).createImage(Icons.MINE_MARKER,25,25);
        assertThat(cell.getGraphic()).isEqualTo(expectedImageView);
        assertThat(cell.getText()).isEqualTo("");
        assertThat(cell.getId()).isEqualTo("covered");
        assertThat(cell.getOnMouseClicked()).isEqualTo(emptyMouseHandler);
    }

    @Test
    public void createMarkAsUnknownField(){
        ImageView expectedImageView = new ImageView();
        when(iconProvider.createImage(Icons.QUESTION_MARK_MARKER,25,25)).thenReturn(expectedImageView);
        Cell cellMarkedAsMine = new Cell(true, true, 4);
        cellMarkedAsMine.setFlagStatus(FlagStatus.MARKED_AS_UNKNOWN);

        Label cell  = mineFieldCreator.createCell(cellMarkedAsMine, emptyMouseHandler);

        assertThat(cell.getMinHeight()).isEqualTo(25);
        assertThat(cell.getMaxHeight()).isEqualTo(25);
        assertThat(cell.getMinWidth()).isEqualTo(25);
        assertThat(cell.getMaxHeight()).isEqualTo(25);
        verify(iconProvider, times(1)).createImage(Icons.QUESTION_MARK_MARKER,25,25);
        assertThat(cell.getGraphic()).isEqualTo(expectedImageView);
        assertThat(cell.getText()).isEqualTo("");
        assertThat(cell.getId()).isEqualTo("covered");
        assertThat(cell.getOnMouseClicked()).isEqualTo(emptyMouseHandler);
    }
}
