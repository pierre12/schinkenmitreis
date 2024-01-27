package com.labuhn.minesweeper.ui.controller;

import static org.assertj.core.api.Assertions.*;

import com.labuhn.minesweeper.domain.Cell;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.testfx.framework.junit5.ApplicationExtension;


@ExtendWith({MockitoExtension.class, ApplicationExtension.class})
public class MineCellCreatorTest {

    private final MineFieldCreator mineFieldCreator  = new MineFieldCreator();

    private final EventHandler<MouseEvent>  emptyMouseHandler = event -> {};

    @Test
    public void createUncoveredEmptyField(){
        Button field = mineFieldCreator.createField(new Cell(false, false, 0), null);

        assertThat(field.getStyle()).isEqualTo("-fx-opacity: 1;-fx-background-color: lightblue;-fx-background-radius: 0;");
        assertThat(field.getPrefHeight()).isEqualTo(35);
        assertThat(field.getPrefWidth()).isEqualTo(35);
        assertThat(field.getText()).isEqualTo("");
        assertThat(field.isDisabled()).isEqualTo(true);
        assertThat(field.getOnMouseClicked()).isNull();
    }

    @Test
    public void createCoveredEmptyField(){
        Button field = mineFieldCreator.createField(new Cell(true, false, 0), emptyMouseHandler);

        assertThat(field.getStyle()).isEqualTo("-fx-background-radius: 0;");
        assertThat(field.getPrefHeight()).isEqualTo(35);
        assertThat(field.getPrefWidth()).isEqualTo(35);
        assertThat(field.getText()).isEqualTo("");
        assertThat(field.isDisabled()).isEqualTo(false);
        assertThat(field.getOnMouseClicked()).isEqualTo(emptyMouseHandler);
    }

    @Test
    public void createUncoveredFieldWithSurroundingMines(){
        Button field = mineFieldCreator.createField(new Cell(false, false, 4), null);

        assertThat(field.getStyle()).isEqualTo("-fx-opacity: 1;-fx-background-color: lightblue;-fx-background-radius: 0;");
        assertThat(field.getPrefHeight()).isEqualTo(35);
        assertThat(field.getPrefWidth()).isEqualTo(35);
        assertThat(field.getText()).isEqualTo("4");
        assertThat(field.isDisabled()).isEqualTo(true);
        assertThat(field.getOnMouseClicked()).isNull();
    }

    @Test
    public void createCoveredFieldWithSurroundingMines(){
        Button field = mineFieldCreator.createField(new Cell(true, false, 4), emptyMouseHandler);

        assertThat(field.getStyle()).isEqualTo("-fx-background-radius: 0;");
        assertThat(field.getPrefHeight()).isEqualTo(35);
        assertThat(field.getPrefWidth()).isEqualTo(35);
        assertThat(field.getText()).isEqualTo("");
        assertThat(field.isDisabled()).isEqualTo(false);
        assertThat(field.getOnMouseClicked()).isEqualTo(emptyMouseHandler);
    }


    @Test
    public void createUncoveredMineField(){
        Button field = mineFieldCreator.createField(new Cell(false, true, 4), null);

        assertThat(field.getStyle()).isEqualTo("-fx-opacity: 1;-fx-background-color: lightblue;-fx-background-radius: 0;");
        assertThat(field.getPrefHeight()).isEqualTo(35);
        assertThat(field.getPrefWidth()).isEqualTo(35);
        assertThat(field.isDisabled()).isEqualTo(true);
        assertThat(field.getGraphic().getClass()).isEqualTo(ImageView.class);
        assertThat(field.getOnMouseClicked()).isNull();
    }

    @Test
    public void createCoveredMineField(){
        Button field = mineFieldCreator.createField(new Cell(true, true, 4), emptyMouseHandler);

        assertThat(field.getStyle()).isEqualTo("-fx-background-radius: 0;");
        assertThat(field.getPrefHeight()).isEqualTo(35);
        assertThat(field.getPrefWidth()).isEqualTo(35);
        assertThat(field.getText()).isEqualTo("");
        assertThat(field.isDisabled()).isEqualTo(false);
        assertThat(field.getOnMouseClicked()).isEqualTo(emptyMouseHandler);
    }
}
