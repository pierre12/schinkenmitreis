package com.labuhn.minesweeper.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class CellTest {
    @Test
    void shouldBeInitializedAsNotMarked(){
        Cell cell = new Cell(true,true,0);
        assertThat(cell.getFlagStatus()).isEqualTo(FlagStatus.NOT_MARKED);
    }

    @Test
    void shouldBeInitializedWithCorrectValues(){
        Cell cell = new Cell(true,true,0);
        assertThat(cell.isMine()).isEqualTo(true);
        assertThat(cell.isCovered()).isEqualTo(true);
        assertThat(cell.getSurroundingMines()).isEqualTo(0);
    }

}