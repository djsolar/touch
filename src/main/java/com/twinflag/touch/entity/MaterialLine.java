package com.twinflag.touch.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.twinflag.touch.model.Material;
import lombok.Data;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

@JsonIgnoreProperties({"bytes"})
@Data
public class MaterialLine {

    @JsonView(DataTablesOutput.View.class)
    private Material first;

    @JsonView(DataTablesOutput.View.class)
    private Material second;
}
