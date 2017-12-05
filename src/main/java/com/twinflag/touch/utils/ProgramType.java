package com.twinflag.touch.utils;

public enum ProgramType {

    TEMPLATE(1), PROGRAM(2);

    private int type;

    ProgramType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
