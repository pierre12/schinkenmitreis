package com.labuhn.minesweeper.domain;


import java.security.InvalidParameterException;

public class Field {


    Cell[][] cells;

    public Field(int rowCount,int columnCount){
        validate(rowCount,columnCount);
        cells = new Cell[rowCount][columnCount];
        initialize();
    }

    private void validate(int rowCount, int columnCount) {
        if(rowCount <=0 || columnCount <= 0){
            throw new InvalidParameterException(String.format("Initialized field with non " +
                    "positive value. Row count:%d,column count:%d",rowCount,columnCount));
        }
    }

    private void initialize() {
        for (int i = 0; i < cells.length ; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                cells[i][j] = new Cell(true,false,0);
            }
        }
    }

    public Cell[][] getCells() {
        return cells;
    }

}
