package com.twinflag.touch.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "t_archive")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Achieve implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "create_user")
    private User createUser;

    @Column(name = "create_date")
    private Date createDate;

    private int authority; // 0：私有 1：共享

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "achieve")
    private List<Material> materials;
}
