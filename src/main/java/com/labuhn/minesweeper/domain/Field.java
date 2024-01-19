package com.labuhn.minesweeper.domain;

public class Field {

    private boolean covered;
    private boolean isMine;
    private FlagStatus flagStatus;
    private int surroundingMines;

    public Field(boolean covered, boolean isMine, int surroundingMines) {
        this.covered = covered;
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
