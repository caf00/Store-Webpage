package com.alejandro.storewebpage.web.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class UpdateCategoryRequest implements Serializable {

    @NotNull(message = "Category id must not be null")
    private Integer id;
    @NotBlank(message = "Category name must not be null")
    private String name;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
