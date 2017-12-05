package com.twinflag.touch.model;

import javax.persistence.*;

@Entity
@Table(name = "t_source")
public class Source {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "absolute_path")
    private String absolutePath;

    @Column(name = "relative_path")
    private String relativePath;

    @ManyToOne
    @JoinColumn(name = "level_one_id")
    private Content content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAbsolutePath() {
        return absolutePath;
    }

    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
    }

    public String getRelativePath() {
        return relativePath;
    }

    public void setRelativePath(String relativePath) {
        this.relativePath = relativePath;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }
}
