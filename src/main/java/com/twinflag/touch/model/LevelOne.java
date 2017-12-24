package com.twinflag.touch.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "t_level_one")
@Data
@NoArgsConstructor
public class LevelOne {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "normal_pic")
    private Material normalPic;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "selected_pic")
    private Material selectedPic;

    private String url;

    @ManyToOne
    @JoinColumn(name = "program_id")
    private Program program;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "levelOne")
    private List<LevelTwo> levelTwos;

}
