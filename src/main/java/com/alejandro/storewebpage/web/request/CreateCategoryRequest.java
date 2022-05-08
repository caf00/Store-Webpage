package com.alejandro.storewebpage.web.request;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public final class CreateCategoryRequest implements Serializable {

    @NotBlank(message = "Category name must not be empty")
    private String name;

    public String getName() {
        return name;
    }
}
