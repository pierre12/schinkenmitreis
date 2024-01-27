package com.labuhn.minesweeper.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.security.InvalidParameterException;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class FieldTest {

    @ParameterizedTest
    @MethodSource("getFieldSizes")
    public void shouldInitializeField(int rows, int columns){
        Field testee = new Field(rows,columns);
        Cell[][] cells = testee.getCells();
        int count=0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns ; j++) {
                assertThat(cells[i][j]).isNotNull();
                count++;
            }
        }
        assertThat(count).as("Mismatch of initialized fields").isEqualTo(rows *columns);
    }

    @ParameterizedTest
    @ValueSource(ints = {0,-5,-10})
    public void shouldRejectIllegalRowCount(int rowCount){
        Assertions.assertThatThrownBy(() -> new Field(rowCount,1))
                .isInstanceOf(InvalidParameterException.class)
                .hasMessageContaining("Row count:"+rowCount);
    }

    @ParameterizedTest
    @ValueSource(ints = {0,-5,-10})
    public void shouldRejectIllegalColumnCount(int columnCount){
        Assertions.assertThatThrownBy(() -> new Field(1,columnCount))
                .isInstanceOf(InvalidParameterException.class)
                .hasMessageContaining("column count:"+columnCount);
    }

    public static Stream<Arguments> getFieldSizes(){
       return Stream.of(Arguments.of(5,3),
               Arguments.of(8,7),
               Arguments.of(1,1),
               Arguments.of(10,10),
               Arguments.of(100,99));
    }
}