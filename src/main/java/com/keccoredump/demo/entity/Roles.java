package com.keccoredump.demo.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String roles;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "role_mapper",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users;

    public Roles() {
    }

    public Roles(int id, String roles, List<User> users) {
        this.id = id;
        this.roles = roles;
        this.users = users;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
