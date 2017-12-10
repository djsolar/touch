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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "normal_pic")
    private Source normalPic;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "selected_pic")
    private Source selectedPic;

    private String url;

    @ManyToOne
    @JoinColumn(name = "program_id")
    private Program program;

    @Column(name = "level_order")
    private Integer levelOrder;

    @Column(name = "updatetime")
    @LastModifiedDate
    private Date updateTime;

    @Column(name = "createtime")
    @CreatedDate
    private Date createTime;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "levelOne")
    private List<LevelTwo> levelTwos;

}
