package com.twinflag.touch.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "t_program")
@Data
@NoArgsConstructor
public class Program implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "program_name")
    private String programName;

    @Column(name = "zip_path")
    private String zipPath;

    @Column(name = "source_path")
    private String sourcePath;

    private int type;

    @ManyToOne
    @JoinColumn(name = "create_user")
    private User createUser;

    @ManyToOne
    @JoinColumn(name = "update_user")
    private User updateUser;

    @Column(name = "updatetime")
    @LastModifiedDate
    private Date updateTime;

    @Column(name = "template_id")
    private Integer templateId;

    @Column(name = "createtime")
    @CreatedDate
    private Date createTime;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "program")
    @JsonIgnore
    private List<LevelOne> levelOnes;
}
