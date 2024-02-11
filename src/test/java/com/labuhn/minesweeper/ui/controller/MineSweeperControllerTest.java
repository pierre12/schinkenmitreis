package com.labuhn.minesweeper.ui.controller;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.labuhn.minesweeper.ui.time.Clock;
import com.sun.javafx.collections.ObservableListWrapper;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.testfx.framework.junit5.ApplicationExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@ExtendWith({MockitoExtension.class, ApplicationExtension.class})
public class MineSweeperControllerTest {

    @Mock
    private GridPane mineSweeperGrid;

    @Mock
    private MineFieldCreator mineFieldCreator;

    @Mock
    private Clock clock;

    @Mock
    private IconCreator iconCreator;

    @Spy
    @InjectMocks
    private MineSweeperController mineSweeperController;

    @Captor
    private ArgumentCaptor<Label[]> captor;


    @BeforeEach
    public void setup() {
        mineSweeperController.setMineFieldCreator(mineFieldCreator);
        mineSweeperController.restartButton = spy(Button.class);
        Platform.runLater(()-> MockitoAnnotations.openMocks(mineSweeperController));
        lenient().when(mineSweeperGrid.getChildren()).thenReturn(new ObservableListWrapper<>(Collections.emptyList()));
        mineSweeperController.setIconProvider(iconCreator);


    }

    @Test
    public void initializesClock(){
        mineSweeperController.initialize();

        verify(mineSweeperController,times(1)).createClock();
    }

    @Test
    public void initializesRestartButton(){
        doNothing().when(mineSweeperController).startGame(Mockito.anyInt(),Mockito.anyInt());
        doReturn(clock).when(mineSweeperController).createClock();
        mineSweeperController.initialize();


        EventHandler<? super MouseEvent> onMouseClicked = mineSweeperController.restartButton.getOnMouseClicked();
        onMouseClicked.handle(null);

        verify(clock,times(1)).stop();
        verify(mineSweeperController,times(1)).startGame(Mockito.anyInt(),Mockito.anyInt());
    }

    @Test
    public void startsClockWhenANewGameStarts() {
        Label cell = new Label("1");
        when(mineFieldCreator.createCell(Mockito.any(), Mockito.any())).thenReturn(cell);

        mineSweeperController.startGame(10,1);

        verify(clock,times(1)).start();
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
        assertThat(labels).hasSize(256);
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
