package com.twinflag.touch.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "t_role")
public class RoleBean {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<UserBean> users;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<UserBean> getUsers() {
        return users;
    }

    public void setUsers(Set<UserBean> users) {
        this.users = users;
    }
}
