package com.labuhn.minesweeper.application;

import com.labuhn.minesweeper.domain.Cell;
import com.labuhn.minesweeper.domain.FlagStatus;

import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.List;

public class CellValidator {

    private static List<FlagStatus> MARKED_STATUSES= Arrays.asList(FlagStatus.MARKED_AS_MINE,
            FlagStatus.MARKED_AS_UNKNOWN);

    public static void validateCellForMineCount(Cell cell){
        if(cell.getSurroundingMines() < 0 || cell.getSurroundingMines() > 8){
            throw new InvalidParameterException(String.format("Surrounding Mines must be in " +
                    "range of 0-8. Actual mines %d", cell.getSurroundingMines()));
        }
    }

    public static void validateCellToBeUncovered(Cell cell){
        if(MARKED_STATUSES.contains(cell.getFlagStatus())){
            throw new InvalidParameterException(String.format("Cannot uncover a marked cell"));
        }
    }
}
