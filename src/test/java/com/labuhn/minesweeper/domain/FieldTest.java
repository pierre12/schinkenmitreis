package com.labuhn.minesweeper.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class FieldTest {
    @Test
    void shouldBeInitializedAsNotMarked(){
        Field field = new Field(true,true,0);
        assertThat(field.getFlagStatus()).isEqualTo(FlagStatus.NOT_MARKED);
    }

    @Test
    void shouldBeInitializedWithCorrectValues(){
        Field field = new Field(true,true,0);
        assertThat(field.isMine()).isEqualTo(true);
        assertThat(field.isCovered()).isEqualTo(true);
        assertThat(field.getSurroundingMines()).isEqualTo(0);
    }

}