package com.alejandro.storewebpage.app.domain.entity;

import com.alejandro.storewebpage.app.exception.InvalidArgumentException;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "categories")
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, unique = true)
    private String name;

    protected Category() {
    }

    public Category(String name) {
        this.setName(name);
    }

    public Category(Integer id, String name) {
        this.setId(id);
        this.setName(name);
    }

    public static Category newCategory(String name) {
        return new Category(name);
    }

    public static Category newCategory(Integer id, String name) {
        return new Category(id, name);
    }

    public void updateFrom(String name) {
        this.setName(name);
    }

    private void setId(Integer id) {
        this.id = id;
    }

    private void setName(String name) {
        if(name==null||name.trim().equals("")) {
            throw new InvalidArgumentException("Category Name must not be null or an empty string");
        }
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
