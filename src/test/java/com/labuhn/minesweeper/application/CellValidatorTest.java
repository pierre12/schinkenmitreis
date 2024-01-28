package com.labuhn.minesweeper.application;

import com.labuhn.minesweeper.domain.Cell;
import com.labuhn.minesweeper.domain.FlagStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.security.InvalidParameterException;
import java.util.stream.Stream;

class CellValidatorTest {

    @Test
    void shouldNotThrowExceptionForValidField(){
        Cell cell = new Cell(true,true,0);
        Assertions.assertThatNoException().isThrownBy(() -> CellValidator.validateCellToBeUncovered(cell));
    }

    @ParameterizedTest
    @ValueSource(ints = {-100,-1,9,100})
    void shouldThrowExceptionForInvalidSurroundingMines(int surroundingMines){
        Cell cell = new Cell(true,true,surroundingMines);

        Assertions.assertThatThrownBy(() -> CellValidator.validateCellForMineCount(cell))
                .isInstanceOf(InvalidParameterException.class)
                .hasMessageContaining("Surrounding Mines")
                .hasMessageContaining(Integer.toString(surroundingMines));
    }

    @ParameterizedTest
    @MethodSource("getMarkedStatuses")
    void shouldThrowExceptionForUncoveredFieldWhichIsMarked(FlagStatus flagStatus){
        Cell cell = new Cell(true,true,0);
        cell.setFlagStatus(flagStatus);
        Assertions.assertThatThrownBy(() -> CellValidator.validateCellToBeUncovered(cell))
                .isInstanceOf(InvalidParameterException.class)
                .hasMessageContaining("Cannot uncover a marked cell");
    }

    @ParameterizedTest
    @ValueSource(booleans = {false,true})
    void shouldNotThrowExceptionForUnMarkedFieldInEitherCoveredOrUncovered(boolean covered){
        Cell cell = new Cell(covered,true,0);
        Assertions.assertThatNoException().isThrownBy(() -> CellValidator.validateCellToBeUncovered(cell));
    }

    public static Stream<FlagStatus> getMarkedStatuses(){
        return Stream.of(FlagStatus.MARKED_AS_MINE,FlagStatus.MARKED_AS_UNKNOWN);
    }
}