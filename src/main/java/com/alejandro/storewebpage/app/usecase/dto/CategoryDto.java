package com.alejandro.storewebpage.app.usecase.dto;

import java.io.Serializable;

public final class CategoryDto implements Serializable {
    private final Integer id;
    private final String name;

    public CategoryDto(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public CategoryDto(String name) {
        this(null, name);
    }

    public static CategoryDto createDto(Integer id, String name) {
        return new CategoryDto(id, name);
    }

    public static CategoryDto createDto(String name) {
        return new CategoryDto(name);
    }

    public static CategoryDto createDto(Integer id) {
        return new CategoryDto(id, null);
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
