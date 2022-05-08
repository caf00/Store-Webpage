package com.alejandro.storewebpage.web.response;

public class CategoryResponse {

    private Integer id;
    private String name;

    public CategoryResponse(Integer id, String name) {
        this.setId(id);
        this.setName(name);
    }

    public static CategoryResponse createResponse(Integer id, String name) {
        return new CategoryResponse(id, name);
    }

    public Integer getId() {
        return id;
    }

    private void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }
}
