package com.twinflag.touch.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Integer id;

    @Column(name = "origin_name")
    private String originName;

    @Column(name = "mac_name")
    private String macName;

    private String path;

    private Integer type; // 0: 图片 1：文本文件

    @ManyToOne
    @JoinColumn(name = "achieve_id")
    @JsonIgnore
    private Achieve achieve;
}
