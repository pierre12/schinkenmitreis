package com.labuhn.minesweeper;

import com.labuhn.minesweeper.application.CellValidator;
import com.labuhn.minesweeper.domain.Cell;
import com.labuhn.minesweeper.domain.Field;
import com.labuhn.minesweeper.domain.FlagStatus;
import javafx.scene.effect.Light;
import javafx.util.Pair;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FieldController {

    Field field;
    public FieldController(int rowCount,int columnCount,int minesCount) {
        initializeField(rowCount,columnCount,minesCount);
    }

    public Field getField(){
        return field;
    }
    public FlagStatus markCell(int rowPosition, int columnPosition){
        //TODO interate through statuses
       return field.getCells()[rowPosition][columnPosition].getFlagStatus();
    }

    public void uncoverCell(int row,int column){
        Cell cell = field.getCells()[row][column];
        CellValidator.validateCellToBeUncovered(cell);
        field.getCells()[row][column].setCovered(false);
    }

    public void initializeField(int rowCount,int columnCount,int mineCount){
        if(mineCount > rowCount * columnCount){
            throw new InvalidParameterException(String.format("Too many mines %d in a %sx%s field",
                    mineCount,rowCount,columnCount));
        }

        field = new Field(rowCount,columnCount);
        Set<Pair<Integer,Integer>> mines = randomizeMines(rowCount, columnCount, mineCount);

        for(Pair<Integer,Integer> minePosition: mines){
            field.getCells()[minePosition.getKey()][minePosition.getValue()].setMine(true);
            incrementSurroundingMines(minePosition.getKey(),minePosition.getValue());
        }

    }

    private void incrementSurroundingMines(int rowPosition, int columnPosition) {

        for (int i = rowPosition - 1; i <= rowPosition + 1  ; i++) {
            if(i < 0 || i > field.getCells().length) {
                //Illegal field just continue with next
                continue;
            }
            for (int j = columnPosition - 1; j <= columnPosition +1 ; j++) {
                if(j < 0 || i > field.getCells()[0].length){
                    //Illegal field just continue with next
                    continue;
                }
                if(i == rowPosition && j == columnPosition){
                    //Its actual cell and no surrounding cell
                    continue;
                }
                Cell cell = getField().getCells()[rowPosition][columnPosition];
                cell.setSurroundingMines(cell.getSurroundingMines() + 1);
            }
        }
    }

    private Set<Pair<Integer,Integer>> randomizeMines(int rowCount, int columnCount, int mineCount) {
        Set<Pair<Integer,Integer>> mines = new HashSet<>(mineCount);
        while (mines.size() < mineCount) {
            Integer row = (int)(Math.random() * rowCount);
            Integer column = (int)(Math.random() * columnCount);
            mines.add(new Pair<>(row,column));
        }
        return mines;
    }

}
