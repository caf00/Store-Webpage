package com.alejandro.storewebpage.app.domain.entity;

import com.alejandro.storewebpage.app.exception.InvalidArgumentException;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "web_pages")
public class WebPage implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, unique = true)
    private String title;
    @Column(nullable = false, unique = true)
    private String url;
    private String description;
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    protected WebPage() {
    }

    public WebPage(Integer id, String title, String url, String description, Category category) {
        this.setId(id);
        this.setTitle(title);
        this.setUrl(url);
        this.setDescription(description);
        this.setCategory(category);
    }

    public WebPage(String title, String url, String description, Category category) {
        this.setTitle(title);
        this.setUrl(url);
        this.setDescription(description);
        this.setCategory(category);
    }

    public WebPage(String title, String url, Category category) {
        this.setUrl(url);
        this.setDescription(description);
        this.setCategory(category);
    }

    public static WebPage newWebPage(Integer id, String title, String url, String description, Category category) {
        return new WebPage(id, title, url, description, category);

    }

    public static WebPage newWebPage(String title, String url, String description, Category category) {
        return new WebPage(title, url, description, category);

    }

    public static WebPage newWebPage(String title, String url, Category category) {
        return new WebPage(title, url, category);
    }

    public void updateFrom(WebPage webPage) {
        if(webPage==null) {
            throw new InvalidArgumentException("Webpage must not be null");
        }
        this.setTitle(webPage.getTitle());
        this.setUrl(webPage.getUrl());
        this.setDescription(webPage.getDescription());
        this.setCategory(webPage.getCategory());
    }

    private void setId(Integer id) {
        this.id = id;
    }

    private void setTitle(String title) {
        if(title==null||title.trim().equals("")) {
            throw new InvalidArgumentException("Webpage Title must not be null or an empty string");
        }
        this.title = title;
    }

    private void setUrl(String url) {
        if(url==null||url.trim().equals("")) {
            throw new InvalidArgumentException("Webpage URL must not be null or an empty string");
        }
        this.url = url;
    }

    private void setDescription(String description) {
        this.description = description;
    }

    private void setCategory(Category category) {
        if(category==null) {
            throw new InvalidArgumentException("WebPage Category must not be null");
        }
        this.category = category;
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

    public Category getCategory() {
        return category;
    }

    public Integer getCategoryId() {
        return this.category.getId();
    }

    public String getCategoryName() {
        return this.category.getName();
    }
}
