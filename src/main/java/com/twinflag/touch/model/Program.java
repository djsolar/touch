package com.twinflag.touch.model;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import javax.persistence.*;
import java.io.DataOutput;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "t_program")
@Data
@NoArgsConstructor
public class Program implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(DataTablesOutput.View.class)
    private Integer id;

    @Column(name = "program_name")
    @JsonView(DataTablesOutput.View.class)
    private String programName;

    @Column(name = "zip_path")
    private String zipPath;

    @Column(name = "source_path")
    private String sourcePath;

    @JsonView(DataTablesOutput.View.class)
    private int type;

    @ManyToOne
    @JoinColumn(name = "create_user")
    @JsonView(DataTablesOutput.View.class)
    private User createUser;

    @ManyToOne
    @JoinColumn(name = "update_user")
    private User updateUser;

    @Column(name = "updatetime")
    @LastModifiedDate
    @JsonView(DataTablesOutput.View.class)
    private Date updateTime;

    @Column(name = "template_id")
    private Integer templateId;

    @Column(name = "createtime")
    @CreatedDate
    private Date createTime;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "program")
    private List<LevelOne> levelOnes;

}
