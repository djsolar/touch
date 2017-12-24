package com.twinflag.touch.tree;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TreeLevel {

    private String text;

    private int type;

    private Object data;

    private List<TreeLevel> nodes;
}
