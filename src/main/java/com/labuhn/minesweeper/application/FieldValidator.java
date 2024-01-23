package com.labuhn.minesweeper.application;

import com.labuhn.minesweeper.domain.Field;
import com.labuhn.minesweeper.domain.FlagStatus;

import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.List;

public class FieldValidator {

    private static List<FlagStatus> MARKED_STATUSES= Arrays.asList(FlagStatus.MARKED_AS_MINE,
            FlagStatus.MARKED_AS_UNKNOWN);

    public static void validateField(Field field){
        if(field.getSurroundingMines() < 0 || field.getSurroundingMines() > 8){
            throw new InvalidParameterException(String.format("Surrounding Mines must be in " +
                    "range of 0-8. Actual fields %d", field.getSurroundingMines()));
        }
        if(MARKED_STATUSES.contains(field.getFlagStatus()) && !field.isCovered()){
            throw new InvalidParameterException(String.format("Uncovered fields cannot be marked " +
                    "but got %s", field.getFlagStatus()));
        }
    }
}
