package com.labuhn.minesweeper.application;

import com.labuhn.minesweeper.domain.Field;
import com.labuhn.minesweeper.domain.FlagStatus;

import java.security.InvalidParameterException;

public class FieldValidator {

    public static void validateField(Field field){
        if(field.getSurroundingMines() < 0 || field.getSurroundingMines() > 8){
            throw new InvalidParameterException(String.format("Surrounding Mines must be in " +
                    "range of 0-8. Actual fields %d", field.getSurroundingMines()));
        }
        if((field.getFlagStatus().equals(FlagStatus.MARKED_AS_MINE)||
                field.getFlagStatus().equals(FlagStatus.MARKED_AS_UNKNOWN))
                && field.isCovered() == false){
            throw new InvalidParameterException(String.format("Uncovered fields cannot be marked " +
                    "but got %s", field.getFlagStatus()));
        }
    }
}
