package com.labuhn.minesweeper.application;

import com.labuhn.minesweeper.domain.Field;

public class FieldFactory {

    public Field createField(boolean isMine, int surroundingMines){
        Field field = new Field(true,isMine, surroundingMines);
        FieldValidator.validateField(field);
        return field;
    }
}
