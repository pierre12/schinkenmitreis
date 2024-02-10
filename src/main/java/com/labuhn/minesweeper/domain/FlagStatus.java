package com.labuhn.minesweeper.domain;

import java.util.Arrays;

public enum FlagStatus {
    NOT_MARKED(0),
    MARKED_AS_MINE(1),
    MARKED_AS_UNKNOWN(2);

    int order;

    FlagStatus(int order){
        this.order = order;
    }

    public int getOrder(){
        return order;
    }
    public static FlagStatus next(FlagStatus currentStatus){
        int length = FlagStatus.values().length;
        int next = (currentStatus.getOrder() + 1) % length;
        return Arrays.stream(FlagStatus.values()).filter(status -> status.getOrder() == next)
                .findFirst().get();
    }
}
