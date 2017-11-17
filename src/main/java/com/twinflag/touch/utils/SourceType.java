package com.twinflag.touch.utils;

public enum SourceType {

    NONE(null, 0), IMAGE(".png", 1), TEXT(".txt", 2);

    private String suffix;

    private int type;

    SourceType(String suffix, int type) {
        this.suffix = suffix;
        this.type = type;
    }

    public String getSuffix() {
        return suffix;
    }

    public int getType() {
        return type;
    }
}
