package com.twinflag.touch.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import javax.persistence.*;

@Entity
@Table(name = "t_material")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"bytes"})
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(DataTablesOutput.View.class)
    private Integer id;

    @Column(name = "origin_name")
    @JsonView(DataTablesOutput.View.class)
    private String originName;

    @JsonView(DataTablesOutput.View.class)
    @Column(name = "mac_name")
    private String macName;

    private String path;

    private Integer type; // 0: 图片 1：文本文件

    @ManyToOne
    @JoinColumn(name = "achieve_id")
    @JsonIgnore
    private Achieve achieve;
}
