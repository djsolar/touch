package com.twinflag.touch.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "t_source")
@Data
@NoArgsConstructor
public class Source {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "absolute_path")
    private String absolutePath;

    @Column(name = "relative_path")
    private String relativePath;

    @ManyToOne
    @JoinColumn(name = "content_id")
    private Content content;
}
