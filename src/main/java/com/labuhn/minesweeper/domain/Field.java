package com.labuhn.minesweeper.domain;

/**
* Field contains status information such as
* <ul>
 * <li>isMine</li>
 * <li>covered</li>
 * <li>flagStatus</li>
 * <li>surroundingMines</li>
* </ul>
*
*/
public class Field {

    private boolean covered;
    private boolean isMine;
    private FlagStatus flagStatus;
    private int surroundingMines;

    public Field(boolean uncovered, boolean isMine, int surroundingMines) {
        this.covered = uncovered;
        this.isMine = isMine;
        this.surroundingMines = surroundingMines;
        this.flagStatus= FlagStatus.NOT_MARKED;
    }

    public boolean isCovered() {
        return covered;
    }

    public void setCovered(boolean covered) {
        this.covered = covered;
    }

    public boolean isMine() {
        return isMine;
    }

    public void setMine(boolean mine) {
        isMine = mine;
    }

    public FlagStatus getFlagStatus() {
        return flagStatus;
    }

    public void setFlagStatus(FlagStatus flagStatus) {
        this.flagStatus = flagStatus;
    }

    public int getSurroundingMines() {
        return surroundingMines;
    }

    public void setSurroundingMines(int surroundingMines) {
        this.surroundingMines = surroundingMines;
    }
}
