package com.labuhn.minesweeper.application;

import com.labuhn.minesweeper.domain.Cell;
import com.labuhn.minesweeper.domain.FlagStatus;

import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.List;

public class CellValidator {

    private static List<FlagStatus> MARKED_STATUSES= Arrays.asList(FlagStatus.MARKED_AS_MINE,
            FlagStatus.MARKED_AS_UNKNOWN);

    public static void validateCell(Cell cell){
        if(cell.getSurroundingMines() < 0 || cell.getSurroundingMines() > 8){
            throw new InvalidParameterException(String.format("Surrounding Mines must be in " +
                    "range of 0-8. Actual mines %d", cell.getSurroundingMines()));
        }
        if(MARKED_STATUSES.contains(cell.getFlagStatus()) && !cell.isCovered()){
            throw new InvalidParameterException(String.format("Uncovered cell cannot be marked " +
                    "but got %s", cell.getFlagStatus()));
        }
    }
}
