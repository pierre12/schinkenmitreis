package com.labuhn.minesweeper.application;

import com.labuhn.minesweeper.domain.Cell;

public class CellFactory {

    public Cell createCell(boolean isMine, int surroundingMines){
        Cell cell = new Cell(true,isMine, surroundingMines);
        CellValidator.validateCell(cell);
        return cell;
    }
}
