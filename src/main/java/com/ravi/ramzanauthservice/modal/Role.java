package com.ravi.ramzanauthservice.modal;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serial;
import java.util.Set;

@Table(name = "role")
@Entity
public class Role implements GrantedAuthority {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    /// Don't create getter setter for this
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "roles")
    private Set<User> roleSet;

    @Override
    public String getAuthority() {
        return name;
    }

    public Role() {}

    public Role(String name, Set<User> roleSet) {
        this.name = name;
        this.roleSet = roleSet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public void setRoleSet(Set<User> roleSet) {
        this.roleSet = roleSet;
    }
}
