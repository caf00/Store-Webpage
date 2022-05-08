package com.alejandro.storewebpage.web.response;

public class WebPageResponse {

    private Integer id;
    private String title;
    private String url;
    private String description;
    private CategoryResponse categoryResponse;

    public WebPageResponse(Integer id,
                           String title,
                           String url,
                           String description,
                           CategoryResponse categoryResponse) {
        this.setId(id);
        this.setTitle(title);
        this.setUrl(url);
        this.setDescription(description);
        this.setCategoryResponse(categoryResponse);
    }

    public static WebPageResponse createResponse(Integer id,
                                              String title,
                                              String url,
                                              String description,
                                              CategoryResponse categoryResponse) {
        return new WebPageResponse(id, title, url, description, categoryResponse);
    }

    public Integer getId() {
        return id;
    }

    private void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    private void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    private void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    private void setDescription(String description) {
        this.description = description;
    }

    public CategoryResponse getCategoryResponse() {
        return categoryResponse;
    }

    private void setCategoryResponse(CategoryResponse categoryResponse) {
        this.categoryResponse = categoryResponse;
    }
}
