package com.alejandro.storewebpage.app.usecase.dto;

import java.io.Serializable;

public final class WebPageDto implements Serializable {
    private final Integer id;
    private final String title;
    private final String url;
    private final String description;
    private final CategoryDto category;

    public WebPageDto(Integer id, String title, String url, String description, CategoryDto category) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.description = description;
        this.category = category;
    }

    public WebPageDto(String title, String url, String description, CategoryDto category) {
        this(null, title, url, description, category);
    }

    public WebPageDto(String title, String url, String description) {
        this(null, title, url, description, null);
    }

    public WebPageDto(String title, String url, CategoryDto category) {
        this(null, title, url, null, category);
    }

    public static WebPageDto createDto(Integer id, String title, String url, String description, CategoryDto category) {
        return new WebPageDto(
                id,
                title,
                url,
                description,
                category
        );
    }
    public static WebPageDto createDto(String title, String url, String description, CategoryDto category) {
        return new WebPageDto(
                title,
                url,
                description,
                category
        );
    }
    public static WebPageDto createDto(String title, String url, CategoryDto category) {
        return new WebPageDto(
                title,
                url,
                category
        );
    }

    public static WebPageDto createDto(String title, String url, String description) {
        return new WebPageDto(
                title,
                url,
                description
        );
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getDescription() {
        return description;
    }

    public CategoryDto getCategory() {
        return category;
    }

    public Integer getCategoryId() {
        return category.getId();
    }

    public String getCategoryName() {
        return category.getName();
    }
}
