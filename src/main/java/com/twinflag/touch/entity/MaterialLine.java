package com.twinflag.touch.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.twinflag.touch.model.Material;
import lombok.Data;

@JsonIgnoreProperties({"bytes"})
@Data
public class MaterialLine {

    private Material first;

    private Material second;
}
