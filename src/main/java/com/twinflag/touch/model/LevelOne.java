package com.twinflag.touch.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "t_level_one")
@Data
@NoArgsConstructor
public class LevelOne {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "normal_pic")
    private Material normalPic;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "selected_pic")
    private Material selectedPic;

    private String url;

    @ManyToOne
    @JoinColumn(name = "program_id")
    private Program program;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, mappedBy = "levelOne")
    private List<LevelTwo> levelTwos;

}
