package com.twinflag.touch.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "t_material")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "origin_name")
    private String originName;

    @Column(name = "mac_name")
    private String macName;

    private String path;

    private int type; // 0: 图片 1：文本文件

    @ManyToOne
    @JoinColumn(name = "achieve_id")
    private Achieve achieve;
}
