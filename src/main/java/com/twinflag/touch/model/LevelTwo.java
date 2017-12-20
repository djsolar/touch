package com.twinflag.touch.model;import lombok.Data;import lombok.NoArgsConstructor;import org.springframework.data.annotation.CreatedDate;import org.springframework.data.annotation.LastModifiedDate;import javax.persistence.*;import java.util.Date;import java.util.List;@Entity@Table(name = "t_level_two")@Data@NoArgsConstructorpublic class LevelTwo {    @Id    @GeneratedValue(strategy = GenerationType.AUTO)    private Integer id;    @Column(name = "updatetime")    @LastModifiedDate    private Date updateTime;    @ManyToOne    @JoinColumn(name = "level_one_id")    private LevelOne levelOne;    private String label;    private String title;    @ManyToOne(cascade = CascadeType.ALL)    @JoinColumn(name = "url_id")    private Material url;    @Column(name = "ismany")    private boolean isMany;    @Column(name = "createtime")    @CreatedDate    private Date createTime;    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "levelTwo")    private List<Content> contents;}