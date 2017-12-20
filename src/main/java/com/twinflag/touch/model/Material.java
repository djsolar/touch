package com.twinflag.touch.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

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

    @ManyToMany(mappedBy = "materials")
    @JsonIgnore
    private Set<Achieve> achieves;

    @ManyToMany(mappedBy = "materials")
    @JsonIgnore
    private List<Content> contents;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Material material = (Material) o;
        return Objects.equals(macName, material.macName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), macName);
    }
}
