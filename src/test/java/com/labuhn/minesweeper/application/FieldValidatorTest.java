package com.labuhn.minesweeper.application;

import com.labuhn.minesweeper.domain.Field;
import com.labuhn.minesweeper.domain.FlagStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.security.InvalidParameterException;
import java.util.stream.Stream;

class FieldValidatorTest {

    @Test
    void shouldNotThrowExceptionForValidField(){
        Field field = new Field(true,true,0);
        Assertions.assertThatNoException().isThrownBy(() -> FieldValidator.validateField(field));
    }

    @ParameterizedTest
    @ValueSource(ints = {-100,-1,9,100})
    void shouldThrowExceptionForInvalidSurroundingMines(int surroundingMines){
        Field field = new Field(true,true,surroundingMines);

        Assertions.assertThatThrownBy(() -> FieldValidator.validateField(field))
                .isInstanceOf(InvalidParameterException.class)
                .hasMessageContaining("Surrounding Mines")
                .hasMessageContaining(Integer.toString(surroundingMines));
    }

    @ParameterizedTest
    @MethodSource("getMarkedStatuses")
    void shouldThrowExceptionForUncoveredFieldWhichIsMarked(FlagStatus flagStatus){
        Field field = new Field(false,true,0);
        field.setFlagStatus(flagStatus);
        Assertions.assertThatThrownBy(() -> FieldValidator.validateField(field))
                .isInstanceOf(InvalidParameterException.class)
                .hasMessageContaining("Uncovered fields")
                .hasMessageContaining(flagStatus.toString());
    }

    @ParameterizedTest
    @MethodSource("getMarkedStatuses")
    void shouldNotThrowExceptionForCoveredFieldWhichIsMarked(FlagStatus flagStatus){
        Field field = new Field(true,true,0);
        field.setFlagStatus(flagStatus);
        Assertions.assertThatNoException().isThrownBy(() -> FieldValidator.validateField(field));
    }

    @ParameterizedTest
    @ValueSource(booleans = {false,true})
    void shouldNotThrowExceptionForUnMarkedFieldInEitherCoveredOrUncovered(boolean covered){
        Field field = new Field(covered,true,0);
        Assertions.assertThatNoException().isThrownBy(() -> FieldValidator.validateField(field));
    }

    public static Stream<FlagStatus> getMarkedStatuses(){
        return Stream.of(FlagStatus.MARKED_AS_MINE,FlagStatus.MARKED_AS_UNKNOWN);
    }
}