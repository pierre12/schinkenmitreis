package com.labuhn.minesweeper.ui.controller;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.testfx.framework.junit5.ApplicationExtension;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@ExtendWith({MockitoExtension.class, ApplicationExtension.class})
public class MineSweeperControllerTest {

    @Mock
    private GridPane mineSweeperGrid;

    @Mock
    private MineFieldCreator mineFieldCreator;

    @InjectMocks
    private MineSweeperController mineSweeperController;

    @Captor
    private ArgumentCaptor<Label[]> captor;


    @BeforeEach
    public void setup() {
        mineSweeperController.setMineFieldCreator(mineFieldCreator);
    }

    @Test
    public void rendersOneRowMineField() {
        Label cell = new Label("1");
        when(mineFieldCreator.createCell(Mockito.any(), Mockito.any())).thenReturn(cell);

        mineSweeperController.startGame(10,1);

        Mockito.verify(mineSweeperGrid, times(1)).addRow(Mockito.anyInt(), captor.capture());
        List<Label> labels = captor.getAllValues().stream().flatMap(Arrays::stream).collect(Collectors.toList());
        assertThat(labels).hasSize(10);
        assertThat(labels).contains(cell);
    }

    @Test
    public void rendersOneColumnMineField() {
        Label cell = new Label("1");
        when(mineFieldCreator.createCell(Mockito.any(), Mockito.any())).thenReturn(cell);

        mineSweeperController.startGame(1,10);

        Mockito.verify(mineSweeperGrid, times(10)).addRow(Mockito.anyInt(), captor.capture());
        List<Label> labels = captor.getAllValues().stream().flatMap(Arrays::stream).collect(Collectors.toList());
        assertThat(labels).hasSize(10);
        assertThat(labels).contains(cell);
    }

    @Test
    public void renders16x16TileMineField() {
        Label cell = new Label("1");
        when(mineFieldCreator.createCell(Mockito.any(), Mockito.any())).thenReturn(cell);

        mineSweeperController.startGame(16,16);

        Mockito.verify(mineSweeperGrid, times(16)).addRow(Mockito.anyInt(), captor.capture());
        List<Label> labels = captor.getAllValues().stream().flatMap(Arrays::stream).collect(Collectors.toList());
        assertThat(labels).hasSize(2561);
        assertThat(labels).contains(cell);
    }

    @Test
    public void renders30x30TileMineField() {
        Label cell = new Label("1");
        when(mineFieldCreator.createCell(Mockito.any(), Mockito.any())).thenReturn(cell);

        mineSweeperController.startGame(30,30);

        Mockito.verify(mineSweeperGrid, times(30)).addRow(Mockito.anyInt(), captor.capture());
        List<Label> labels = captor.getAllValues().stream().flatMap(Arrays::stream).collect(Collectors.toList());
        assertThat(labels).hasSize(900);
        assertThat(labels).contains(cell);
    }

}
