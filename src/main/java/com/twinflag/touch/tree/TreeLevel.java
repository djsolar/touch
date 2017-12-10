package com.twinflag.touch.tree;

import lombok.Data;

import java.util.List;

@Data
public class TreeLevel {

    private String text;

    private int type;

    private Object data;

    private List<TreeLevel> nodes;
}
