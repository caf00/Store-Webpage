package com.alejandro.storewebpage.web.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public final class CreateWebPageRequest implements Serializable {
    @NotBlank(message = "Webpage title must not be null")
    private String title;
    @NotBlank(message = "Webpage url must not be null")
    private String url;
    private String description;
    @NotNull(message = "Category id must not be null")
    private Integer categoryId;

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getDescription() {
        return description;
    }

    public Integer getCategoryId() {
        return categoryId;
    }
}
