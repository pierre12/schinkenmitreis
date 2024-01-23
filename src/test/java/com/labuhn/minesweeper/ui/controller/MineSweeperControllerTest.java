package com.labuhn.minesweeper.ui.controller;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;


import com.labuhn.minesweeper.domain.Field;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.testfx.framework.junit5.ApplicationExtension;

import java.util.List;


@ExtendWith({MockitoExtension.class, ApplicationExtension.class})
public class MineSweeperControllerTest {

    @Mock
    GridPane mineSweeperGrid;

    @Mock
    MineFieldCreator mineFieldCreator;

    @InjectMocks
    MineSweeperController mineSweeperController;

    @Captor
    ArgumentCaptor<Button>  captor;


    @BeforeEach
    public void setup(){
        mineSweeperController.setMineFieldCreator(mineFieldCreator);
    }

    @Test
    public void rendersOneRowMineField(){
        Field field = new Field(true, false, 3);
        Button button1 = new Button("1");
        mineSweeperController.setDimension(10,1);
        when(mineFieldCreator.createField(Mockito.any(),Mockito.any())).thenReturn(button1);

        mineSweeperController.render(List.of(field));

        Mockito.verify(mineSweeperGrid, times(1)).addRow(Mockito.anyInt(),captor.capture());
        List<Button> values = captor.getAllValues();
        assertThat(values).hasSize(10);
        assertThat(values).contains(button1);
    }

    @Test
    public void rendersOneColumnMineField(){
        Field field = new Field(true, false, 3);
        Button button1 = new Button("1");
        mineSweeperController.setDimension(1,10);
        when(mineFieldCreator.createField(Mockito.any(),Mockito.any())).thenReturn(button1);

        mineSweeperController.render(List.of(field));

        Mockito.verify(mineSweeperGrid, times(10)).addRow(Mockito.anyInt(),captor.capture());
        List<Button> values = captor.getAllValues();
        assertThat(values).hasSize(10);
        assertThat(values).contains(button1);
    }

    @Test
    public void renders16x16TileMineField(){
        Field field = new Field(true, false, 3);
        Button button1 = new Button("1");
        when(mineFieldCreator.createField(Mockito.any(),Mockito.any())).thenReturn(button1);
        mineSweeperController.setDimension(16,16);

        mineSweeperController.render(List.of(field));

        Mockito.verify(mineSweeperGrid, times(16)).addRow(Mockito.anyInt(),captor.capture());
        List<Button> values = captor.getAllValues();
        assertThat(values).hasSize(256);
    }

}
